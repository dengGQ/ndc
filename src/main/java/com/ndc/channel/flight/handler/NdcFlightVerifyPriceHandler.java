package com.ndc.channel.flight.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ndc.channel.enumtype.BusinessEnum;
import com.ndc.channel.exception.BusinessException;
import com.ndc.channel.exception.BusinessExceptionCode;
import com.ndc.channel.flight.dto.flightSearch.*;
import com.ndc.channel.flight.dto.verifyPrice.CorpApiFlightVerifyPriceData;
import com.ndc.channel.flight.dto.verifyPrice.FeiBaApiVerifyPriceReq;
import com.ndc.channel.flight.dto.verifyPrice.FeibaApiVerificationParams;
import com.ndc.channel.flight.tools.NdcApiTools;
import com.ndc.channel.flight.xmlBean.verifyPrice.common.Error;
import com.ndc.channel.flight.xmlBean.verifyPrice.request.bean.*;
import com.ndc.channel.flight.xmlBean.verifyPrice.request.bean.Arrival;
import com.ndc.channel.flight.xmlBean.verifyPrice.request.bean.DataLists;
import com.ndc.channel.flight.xmlBean.verifyPrice.request.bean.DatedOperatingLeg;
import com.ndc.channel.flight.xmlBean.verifyPrice.request.bean.Dep;
import com.ndc.channel.flight.xmlBean.verifyPrice.request.bean.Desc;
import com.ndc.channel.flight.xmlBean.verifyPrice.request.bean.FareComponent;
import com.ndc.channel.flight.xmlBean.verifyPrice.request.bean.FareDetail;
import com.ndc.channel.flight.xmlBean.verifyPrice.request.bean.MarketingCarrierInfo;
import com.ndc.channel.flight.xmlBean.verifyPrice.request.bean.OperatingCarrierInfo;
import com.ndc.channel.flight.xmlBean.verifyPrice.request.bean.OriginDest;
import com.ndc.channel.flight.xmlBean.verifyPrice.request.bean.OriginDestList;
import com.ndc.channel.flight.xmlBean.verifyPrice.request.bean.PaxJourney;
import com.ndc.channel.flight.xmlBean.verifyPrice.request.bean.PaxJourneyList;
import com.ndc.channel.flight.xmlBean.verifyPrice.request.bean.PaxSegment;
import com.ndc.channel.flight.xmlBean.verifyPrice.request.bean.PaxSegmentList;
import com.ndc.channel.flight.xmlBean.verifyPrice.request.bean.PriceClass;
import com.ndc.channel.flight.xmlBean.verifyPrice.request.bean.PriceClassList;
import com.ndc.channel.flight.xmlBean.verifyPrice.response.bean.*;
import com.ndc.channel.redis.RedisKeyConstants;
import com.ndc.channel.redis.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.swing.text.html.Option;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Component
public class NdcFlightVerifyPriceHandler {

    @Resource
    private NdcApiTools ndcApiTools;

    @Resource
    private RedisUtils redisUtils;

    public static final Tax EMPTY_TAX;
    static {
        final Amount amount = new Amount();
        amount.setValue("0");

        EMPTY_TAX = new Tax();
        EMPTY_TAX.setAmount(amount);
    }

    public CorpApiFlightVerifyPriceData verifyPrice(FeiBaApiVerifyPriceReq verifyPriceParams) {

        final FeibaApiVerificationParams params = verifyPriceParams.getPriceVerificationParams();
        final String flightId = params.getFlightId();
        final String ticketId = params.getTicketId();

        final String redisFlightDataCacheKey = RedisKeyConstants.getRedisFlightDataCacheKey(flightId);
        CorpApiFlightListDataV2 flightData = redisUtils.get(redisFlightDataCacheKey, CorpApiFlightListDataV2.class);

        final String redisTicketDataCacheKey = RedisKeyConstants.getRedisTicketDataCacheKey(flightId);
        CorpApiTicketData ticketData = redisUtils.hGet(redisTicketDataCacheKey, ticketId, CorpApiTicketData.class);

        IATAOfferPriceRQ priceRQ = new IATAOfferPriceRQ();

        Request request = new Request();

        request.setDataLists(getDataLists(flightData, ticketData));

        final CorpApiFlightVerifyPriceData verifyRespData = new CorpApiFlightVerifyPriceData();
        verifyRespData.setSuccess(false);

        request.setPricedOffer(getPriceOffer(flightData, ticketData, ticketData.getOfferItemId()));

        priceRQ.setRequest(request);
        final IATAOfferPriceRS iataOfferPriceRS = ndcApiTools.flightOfferPrice(priceRQ);

        final Error error = iataOfferPriceRS.getError();
        if (error != null){
            throw new BusinessException(BusinessExceptionCode.REQUEST_PARAM_ERROR, error.getDescText());
        }

        final Response response = iataOfferPriceRS.getResponse();

        final com.ndc.channel.flight.xmlBean.verifyPrice.response.bean.DataLists dataLists = response.getDataLists();
        // 服务
        List<ServiceDefinition> serviceDefinitionList = dataLists.getServiceDefinitionList();
        // 行李额
        final List<BaggageAllowance> baggageAllowanceList = dataLists.getBaggageAllowanceList();
        final Map<String, BaggageAllowance> baggageAllowanceMap = baggageAllowanceList.stream().collect(Collectors.toMap(BaggageAllowance::getBaggageAllowanceID, Function.identity()));

        final ShoppingResponse shoppingResponse = response.getShoppingResponse();
        flightData.setShoppingResponseID(shoppingResponse.getShoppingResponseID());

        final Offer adtOffer = response.getOtherOffers().getOffer().stream()
                .filter(offer -> offer.getPTCOfferParameters().getPTCPricedCode().equals("ADT")).findFirst()
                .orElseGet(() -> {
                    throw new BusinessException(BusinessExceptionCode.REQUEST_PARAM_ERROR, "未找到成人报价信息！");
                });

        final String offerID = adtOffer.getOfferID();
        final OfferItem offerItem = adtOffer.getOfferItem();
        final TotalPrice totalPrice = adtOffer.getTotalPrice();

        final String offerItemID = offerItem.getOfferItemID();
        final com.ndc.channel.flight.xmlBean.verifyPrice.response.bean.FareDetail fareDetail = offerItem.getFareDetail();
        // 退改政策
        final String remarkText = fareDetail.getFareComponent().getFareRule().get(0).getRemark().getRemarkText();

        // 票价
        final String ticketPrice = totalPrice.getBaseAmount().getValue();

        //税费
        final List<Tax> tax = totalPrice.getTaxSummary().getTax();
        final Map<String, Tax> taxMap = tax.stream().collect(Collectors.toMap(Tax::getTaxCode, Function.identity()));
        final Tax buildFee = Optional.ofNullable(taxMap.get("CN")).orElse(EMPTY_TAX);
        final Tax oilFee = Optional.ofNullable(taxMap.get("YQ")).orElse(EMPTY_TAX);

        verifyRespData.setTicketPrice(new BigDecimal(ticketPrice));
        verifyRespData.setPurchasePrice(new BigDecimal(ticketPrice));
        verifyRespData.setBuildFee(new BigDecimal(buildFee.getAmount().getValue()));
        verifyRespData.setOilFee(new BigDecimal(oilFee.getAmount().getValue()));

        verifyRespData.setFlightNumber(flightData.getFlightNumber());
        verifyRespData.setSeatClassCode(ticketData.getSeatClassCode());
        verifyRespData.setSuccess(true);

        FlightBaggageInfoData baggageInfoData = new FlightBaggageInfoData();
        verifyRespData.setServiceDefinitionList(getServiceDefinitionList(serviceDefinitionList, baggageAllowanceMap, baggageInfoData));

        CorpApiTicketPolicy policy = new CorpApiTicketPolicy();
        JSONObject policyJson = JSONObject.parseObject(remarkText, JSONObject.class);
        policy.setChangePolicy(policyJson.getString("comment"));
        policy.setRefundPolicy(policyJson.getString("comment"));
        policy.setFlightBaggageInfoData(baggageInfoData);
        verifyRespData.setPolicy(policy);

        flightData.setOfferId(offerID);
        ticketData.setOfferItemId(offerItemID);
        redisUtils.setStrExpire(redisFlightDataCacheKey, JSON.toJSONString(flightData), 1, TimeUnit.DAYS);
        redisUtils.hset(redisTicketDataCacheKey, ticketId, JSONObject.toJSONString(ticketData));

        return verifyRespData;
    }

    private DataLists getDataLists(CorpApiFlightListDataV2 dataV2, CorpApiTicketData ticketData) {
        final DataLists dataLists = new DataLists();

        dataLists.setOriginDestList(getOriginDestList(dataV2));

        dataLists.setPaxJourneyList(getPaxJourneyList(dataV2));

        dataLists.setPaxSegmentList(getPaxSegmentList(dataV2, ticketData));

        dataLists.setPriceClassList(getPriceClassList(ticketData));

        return dataLists;
    }

    private PricedOffer getPriceOffer(CorpApiFlightListDataV2 flightData, CorpApiTicketData ticketData, String offerItemId) {
        final PricedOffer pricedOffer = new PricedOffer();

        final CreateOrderItem createOrderItem = new CreateOrderItem();
        createOrderItem.setOfferItemID(offerItemId);

        final OfferItemType offerItemType = new OfferItemType();

        final FlightItem flightItem = new FlightItem();
        flightItem.setOriginDestRefID(flightData.getOriginDestID());

        final FareDetail fareDetail = new FareDetail();

        final FareComponent fareComponent = new FareComponent();
        fareComponent.setPaxSegmentRefID(flightData.getPaxSegmentID());
        fareComponent.setPriceClassRefID(ticketData.getPriceClassID());
        fareComponent.setFareTypeCode(ticketData.getFareTypeCode());

        fareDetail.setFareComponent(fareComponent);
        fareDetail.setPricingSystemCodeText(ticketData.getPricingSystemCodeText());

        flightItem.setFareDetail(fareDetail);

        offerItemType.setFlightItem(flightItem);
        createOrderItem.setOfferItemType(offerItemType);

        pricedOffer.setCreateOrderItem(createOrderItem);
        return pricedOffer;
    }

    public PaxJourneyList getPaxJourneyList(CorpApiFlightListDataV2 dataV2) {
        final PaxJourneyList paxJourneyList = new PaxJourneyList();
        final PaxJourney paxJourney = new PaxJourney();
        paxJourney.setPaxJourneyID(dataV2.getPaxJourneyID());
        paxJourney.setPaxSegmentRefID(dataV2.getPaxSegmentRefID());
        paxJourneyList.setPaxJourney(paxJourney);
        return paxJourneyList;
    }

    public OriginDestList getOriginDestList(CorpApiFlightListDataV2 dataV2) {
        final OriginDestList originDestList = new OriginDestList();
        final OriginDest originDest = new OriginDest();
        originDest.setOriginCode(dataV2.getDepartureAirportCode());
        originDest.setDestCode(dataV2.getDestinationAirportCode());
        originDest.setOriginDestID(dataV2.getOriginDestID());
        originDest.setPaxJourneyRefID(Arrays.asList(dataV2.getPaxJourneyID()));
        originDestList.setOriginDest(originDest);

        return originDestList;
    }

    private PriceClassList getPriceClassList(CorpApiTicketData ticketData) {
        final PriceClassList priceClassList = new PriceClassList();

        final PriceClass priceClass = new PriceClass();

        priceClass.setPriceClassID(ticketData.getPriceClassID());
        priceClass.setCode(ticketData.getPriceClassCode());
        priceClass.setName(ticketData.getPriceClassName());

        final Desc desc = new Desc();
        desc.setDescText(ticketData.getPriceClassDesc());

        priceClass.setDesc(desc);

        priceClassList.setPriceClass(priceClass);

        return priceClassList;
    }

    private PaxSegmentList getPaxSegmentList(CorpApiFlightListDataV2 flightListDataV2, CorpApiTicketData ticketData) {

        final PaxSegmentList paxSegmentList = new PaxSegmentList();

        PaxSegment paxSegment = new PaxSegment();
        paxSegment.setPaxSegmentID(flightListDataV2.getPaxSegmentID());

        final Dep dep = new Dep();
        dep.setAircraftScheduledDateTime(flightListDataV2.getFlightDate());
        dep.setIATALocationCode(flightListDataV2.getDepartureAirportCode());
        dep.setStationName(flightListDataV2.getDepartureAirport());
        dep.setTerminalName(flightListDataV2.getDestinationTerminal());
        paxSegment.setDep(dep);

        final Arrival arrival = new Arrival();
        arrival.setAircraftScheduledDateTime(flightListDataV2.getFlightDate());
        arrival.setIATALocationCode(flightListDataV2.getDestinationAirportCode());
        arrival.setStationName(flightListDataV2.getDestinationAirport());
        arrival.setTerminalName(flightListDataV2.getDestinationTerminal());
        paxSegment.setArrival(arrival);


        final MarketingCarrierInfo marketingCarrierInfo = new MarketingCarrierInfo();
        marketingCarrierInfo.setCarrierDesigCode(flightListDataV2.getAirlineCode());
        marketingCarrierInfo.setMarketingCarrierFlightNumberText(flightListDataV2.getFlightNumber().substring(2));

        paxSegment.setMarketingCarrierInfo(marketingCarrierInfo);

        OperatingCarrierInfo operatingCarrierInfo = new OperatingCarrierInfo();
        operatingCarrierInfo.setCarrierDesigCode(flightListDataV2.getMainAirlineCode());
        operatingCarrierInfo.setOperatingCarrierFlightNumberText(flightListDataV2.getFlightNumber().substring(2));
        operatingCarrierInfo.setRBDCode(ticketData.getSeatClassCode());
        paxSegment.setOperatingCarrierInfo(operatingCarrierInfo);

        final DatedOperatingLeg datedOperatingLeg = new DatedOperatingLeg();
        datedOperatingLeg.setDatedOperatingLegID("1");
        datedOperatingLeg.setArrival(arrival);
        datedOperatingLeg.setDep(dep);
        paxSegment.setDatedOperatingLeg(datedOperatingLeg);

        paxSegmentList.setPaxSegment(paxSegment);

        return paxSegmentList;
    }

    public List<TicketServiceDefinition> getServiceDefinitionList(List<ServiceDefinition> serviceDefinitionList, Map<String, BaggageAllowance> baggageAllowanceMap, FlightBaggageInfoData baggageInfoData) {
        List<TicketServiceDefinition> ticketServiceDefinitionList = Optional.ofNullable(serviceDefinitionList).orElseGet(Arrays::asList).stream().map(serviceDefinition -> {
            TicketServiceDefinition definition = new TicketServiceDefinition();

            definition.setServiceDefinitionID(serviceDefinition.getServiceDefinitionID());
            definition.setServiceCode(serviceDefinition.getServiceCode());
            definition.setName(serviceDefinition.getName());
            ServiceDefinitionAssociation serviceDefinitionAssociation = serviceDefinition.getServiceDefinitionAssociation();
            BaggageAllowance baggageAllowance = null;
            if (serviceDefinitionAssociation != null && (baggageAllowance = baggageAllowanceMap.get(serviceDefinitionAssociation.getBaggageAllowanceRefID())) != null) {

                // 行李额携带类型
                String typeCode = baggageAllowance.getTypeCode();
                // 行李额重量限制
                WeightAllowance weightAllowance = baggageAllowance.getWeightAllowance();
                // 行李额数量限制
                PieceAllowance pieceAllowance = Optional.ofNullable(baggageAllowance.getPieceAllowance()).orElseGet(PieceAllowance::new);
                // 行李额体积限制 图片地址
                JSONObject jsonObject = JSONObject.parseObject(baggageAllowance.getDescText(), JSONObject.class);
                String volume = jsonObject.getJSONObject("mediaObject").getJSONObject("binaryObject").getString("uniformResourceID");

                MaximumWeightMeasure maximumWeightMeasure = weightAllowance.getMaximumWeightMeasure();
                String unitStr = BusinessEnum.WeightUnit.getMsg(maximumWeightMeasure.getUnitCode());
                if (BusinessEnum.BaggageType.CARRY_ON.getCode().equals(typeCode)) {

                    baggageInfoData.setFreeBaggageWeight(maximumWeightMeasure.getValue() + unitStr);
                    baggageInfoData.setCarryOnBaggageAmount(pieceAllowance.getTotalQty());
                    baggageInfoData.setCarryOnBaggageVolume(volume);
                }else if (BusinessEnum.BaggageType.CHECKED.getCode().equals(typeCode)){

                    baggageInfoData.setCarryOnBaggageWeight(maximumWeightMeasure.getValue() + unitStr);
                    baggageInfoData.setFreeBaggageAmount(pieceAllowance.getTotalQty());
                    baggageInfoData.setFreeBaggageVolume(volume);
                }
            }
            return definition;
        }).collect(Collectors.toList());

        return ticketServiceDefinitionList;
    }
}
