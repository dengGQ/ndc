package com.ndc.channel.flight.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ndc.channel.entity.NdcChannelProductConfig;
import com.ndc.channel.enumtype.BusinessEnum;
import com.ndc.channel.enumtype.ProductRightUtil;
import com.ndc.channel.exception.BusinessException;
import com.ndc.channel.exception.BusinessExceptionCode;
import com.ndc.channel.flight.dto.flightSearch.*;
import com.ndc.channel.flight.tools.DateTimeUtils;
import com.ndc.channel.flight.tools.NdcApiTools;
import com.ndc.channel.flight.xmlBean.flightSearch.common.Error;
import com.ndc.channel.flight.xmlBean.flightSearch.request.bean.*;
import com.ndc.channel.flight.xmlBean.flightSearch.response.RemarkText;
import com.ndc.channel.flight.xmlBean.flightSearch.response.bean.*;
import com.ndc.channel.mapper.NdcChannelProductConfigMapper;
import com.ndc.channel.redis.RedisKeyConstants;
import com.ndc.channel.redis.RedisUtils;
import com.ndc.channel.util.FlightKeyUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.MessageFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Component
public class NdcFlightSearchHandler {

    @Resource
    private NdcApiTools apiTools;

    @Resource
    private RedisUtils redisUtils;

    @Resource
    private NdcChannelProductConfigMapper productConfigMapper;

    public List<CorpApiFlightListDataV2> flightSearch(String flightDate, String depCityCode, String destCityCode) {

        IATAAirShoppingRQ flightSearchParams = getFlightSearchParams(flightDate, depCityCode, destCityCode);

        IATAAirShoppingRS iataAirShoppingRS = apiTools.flightSearch(flightSearchParams);
        Error error = iataAirShoppingRS.getError();
        if (error != null) {
            throw new BusinessException(error.getCode(), error.getDescText());
        }

        return convertFlightData(iataAirShoppingRS.getResponse(), flightDate, depCityCode, destCityCode);
    }

    private List<CorpApiFlightListDataV2> convertFlightData(Response response, String flightDate, String depCityCode, String destCityCode) {

        List<NdcChannelProductConfig> productList = productConfigMapper.queryEnableProductByNdcAccountCode("mu_ndc");
        Map<String, NdcChannelProductConfig> productConfigMap = productList.stream().collect(Collectors.toMap(NdcChannelProductConfig::getProductCode, Function.identity()));
        if (MapUtils.isEmpty(productConfigMap)) {
            return Arrays.asList();
        }

        String everyFlightDateKey=flightDate+depCityCode+destCityCode;

        final DataLists dataLists = response.getDataLists();

        final String shoppingResponseID = response.getShoppingResponse().getShoppingResponseID();
        final List<CorpApiFlightListDataV2> flightDataList = convertToCorpApiFlightDataV2(flightDate, depCityCode, destCityCode, shoppingResponseID, dataLists);

        // 成人票检查
        Pax pax = dataLists.getPaxList().stream()
                .filter(p -> p.getPTC().equals("ADT")).findFirst()
                .orElseGet(()->{
                    log.error("NDC航班未查到成人航班, everyFlightDateKey={}", everyFlightDateKey);
                    throw new BusinessException(BusinessExceptionCode.REQUEST_PARAM_ERROR, "NDC航班未查到成人航班！");
                });
        // 报价信息
        Offer offer = response.getOffersGroup().getCarrierOffers().stream()
                .filter(of -> of.getPtcOfferParameters().get(0).getPTCPricedCode().equals(pax.getPTC()))
                .findFirst().orElseGet(()->{
                    throw new BusinessException(BusinessExceptionCode.REQUEST_PARAM_ERROR, "NDC航班查询未查到成人舱位！");
                });

        final String ownerTypeCode = offer.getOwnerTypeCode();
        final String ownerCode = offer.getOwnerCode();
        final Map<String, String> persistenceFlightDataMap= new HashMap<>(32);
        final Map<String, Map<String, String>> persistenceTicketDataMap = new HashMap<>(32);

        for (Iterator<CorpApiFlightListDataV2> iterator = flightDataList.iterator(); iterator.hasNext();){
            final CorpApiFlightListDataV2 flightData = iterator.next();

            persistenceFlightDataMap.put(RedisKeyConstants.getRedisFlightDataCacheKey(flightData.getFlightId()), JSON.toJSONString(flightData));


            final Map<String, CorpApiTicketData> corpApiTicketMap = new HashMap<>();//ticketDataList.stream().collect(Collectors.toMap(CorpApiTicketData::getTicketId, v -> JSON.toJSONString(v)));
            
            // 遍历报价项目
            for (OfferItem offerItem : offer.getOfferItem()) {

                CorpApiTicketData ticketData = convertToCorpApiTicketData(everyFlightDateKey, flightData, dataLists, pax, offerItem, productConfigMap);
                if (ticketData != null) {

                    corpApiTicketMap.put(ticketData.getTicketId(), ticketData);
                }
            }

            if (corpApiTicketMap.isEmpty()) {
                iterator.remove();
                continue;
            }

            List<CorpApiTicketData> ticketDataList = corpApiTicketMap.values().stream().collect(Collectors.toList());

            flightData.setOfferId(offer.getOfferID());
            flightData.setOwnerCode(ownerCode);
            flightData.setOwnerTypeCode(ownerTypeCode);
            flightData.setBuildFee(ticketDataList.get(0).getBuildFee());
            flightData.setOilFee(ticketDataList.get(0).getOilFee());
            final boolean isPresent = ticketDataList.get(0).getServiceDefinitionList().stream().anyMatch(serviceDef -> BusinessEnum.ServiceName.MEAL.name().equals(serviceDef.getName()));
            flightData.setMealType(isPresent ? "1" : "0");
            flightData.setTickets(ticketDataList);

            final Map<String, String> ticketStrMap = corpApiTicketMap.values().stream().collect(Collectors.toMap(CorpApiTicketData::getTicketId, JSON::toJSONString));
            persistenceTicketDataMap.put(RedisKeyConstants.getRedisTicketDataCacheKey(flightData.getFlightId()), ticketStrMap);
        }

        redisUtils.multiSetAndExpire(persistenceFlightDataMap, TimeUnit.DAYS, 1);
        redisUtils.hPutAndExpireAt(persistenceTicketDataMap, TimeUnit.DAYS, 1);

        return flightDataList;
    }

    private List<CorpApiFlightListDataV2> convertToCorpApiFlightDataV2(String flightDate, String depCityCode, String destCityCode, String shoppingResponseID, DataLists dataLists) {
        // 出发和到达数据
        final OriginDest originDest = dataLists.getOriginDestList().get(0);
        final List<String> paxJourneyRefID = originDest.getPaxJourneyRefID(); // 乘客行程ID

        // 乘客行程
        final List<PaxJourney> paxJourneyList = dataLists.getPaxJourneyList().stream()
                .filter(paxJourney -> paxJourneyRefID.contains(paxJourney.getPaxJourneyID()))
                .collect(Collectors.toList());

        // 乘客航段
        final List<PaxSegment> paxSegmentList = dataLists.getPaxSegmentList();
        // 转换后的航班列表
        List<CorpApiFlightListDataV2> flightDataList = new ArrayList<>();

        // 遍历乘客航段和乘客行程，查找与乘客行程相匹配的航段
        for (PaxSegment paxSegment : paxSegmentList) {
            for (PaxJourney paxJourney : paxJourneyList) {

                if (paxJourney.getPaxSegmentRefID().contains(paxSegment.getPaxSegmentID())) {
                    CorpApiFlightListDataV2 corpApiFlightListDataV2 = flightDataConvertFromPaxSegment(originDest, paxJourney, paxSegment, flightDate, depCityCode, destCityCode);
                    corpApiFlightListDataV2.setShoppingResponseID(shoppingResponseID);
                    flightDataList.add(corpApiFlightListDataV2);
                }
            }
        }

        return flightDataList;
    }

    private CorpApiTicketData convertToCorpApiTicketData(String everyFlightDateKey, CorpApiFlightListDataV2 flightData, DataLists dataLists, Pax pax, OfferItem offerItem, Map<String, NdcChannelProductConfig> productConfigMap) {

        // 服务定义列表
        final List<ServiceDefinition> serviceDefinitionList = dataLists.getServiceDefinitionList();

        // 行李额信息
        List<BaggageAllowance> baggageAllowanceList = dataLists.getBaggageAllowanceList();
        final Map<String, BaggageAllowance> baggageAllowanceMap = baggageAllowanceList.stream().collect(Collectors.toMap(BaggageAllowance::getBaggageAllowanceID, Function.identity()));

        // 价格类型列表
        final List<PriceClass> priceClassList = dataLists.getPriceClassList();
        final Map<String, PriceClass> priceClassMap = priceClassList.stream().collect(Collectors.toMap(PriceClass::getPriceClassID, Function.identity()));

        // 报价详细信息
        FareDetail fareDetail = offerItem.getFareDetail().get(0);

        // 票价计算组件
        FareComponent fareComponent = fareDetail.getFareComponent().get(0);
        // 票价组关联航段ID
        List<String> paxSegmentRefID = fareComponent.getPaxSegmentRefID();
        // 票价组关联价格类型ID
        final String priceClassRefID = fareComponent.getPriceClassRefID();

        final String fareTypeCode = fareComponent.getFareTypeCode();

        final PriceClass priceClass = priceClassMap.get(priceClassRefID);

        NdcChannelProductConfig ndcChannelProductConfig = productConfigMap.get(priceClass.getCode());
        if (ndcChannelProductConfig == null || !paxSegmentRefID.contains(flightData.getPaxSegmentID())) {
            log.error("价格类型与产品匹配失败或未找到与当前航段关联的票价组, everyFlightDateKey={}, PaxSegmentID={}", everyFlightDateKey, flightData.getPaxSegmentID());
            return null;
        }

        // 乘客与服务之间的映射
        Map<String, List<String>> serviceDefinitionRefIDMapping = Optional.ofNullable(offerItem.getService()).orElseGet(Arrays::asList).stream()
                .collect(Collectors.groupingBy(
                        ser -> ser.getPaxRefID() + ser.getServiceAssociations().getServiceDefinitionRef().getPaxSegmentRefID(),
                        Collectors.mapping(ser -> ser.getServiceAssociations().getServiceDefinitionRef().getServiceDefinitionRefID(),
                                Collectors.toList())));

        final List<String> serviceDefinitionIdList = serviceDefinitionRefIDMapping.get(pax.getPaxID() + flightData.getPaxSegmentID());
        if (CollectionUtils.isEmpty(serviceDefinitionIdList)) {
            log.error("服务定义为空, everyFlightDateKey={}, paxSegmentID={}, PaxID={}", everyFlightDateKey, flightData.getPaxSegmentID(), pax.getPaxID());
            return null;
        }

        // 价格来源
        final String pricingSystemCodeText = fareDetail.getPricingSystemCodeText();

        List<ServiceDefinition> paxServiceDefinitionList = serviceDefinitionList.stream().filter(sd -> serviceDefinitionIdList.contains(sd.getServiceDefinitionID())).collect(Collectors.toList());

        CorpApiTicketData ticketData = ticketDataConvertFromCarrierOffer(flightData, offerItem, paxServiceDefinitionList, baggageAllowanceMap);
        ticketData.setPaxId(pax.getPaxID());
        ticketData.setPricingSystemCodeText(pricingSystemCodeText);
        ticketData.setPriceClassID(priceClass.getPriceClassID());
        ticketData.setPriceClassCode(priceClass.getCode());
        ticketData.setPriceClassName(priceClass.getName());
        ticketData.setPriceClassDesc(priceClass.getDesc().getDescText());
        ticketData.setFareTypeCode(fareTypeCode);
        ticketData.setProductCode(priceClass.getCode());
        ticketData.setProductName(priceClass.getName());
        ProductRightDefinition definition = JSONObject.parseObject(priceClass.getDesc().getDescText(), ProductRightDefinition.class);
        List<String> productRights = parseProductRights(definition);

        ticketData.setRightsList(productRights);

        String productNotice = definition.getProductDefinition().getProductNotice();

        ticketData.setProductNotice(productNotice);

        TaxSummary taxSummary = offerItem.getPrice().getTaxSummary();
        final Map<String, Tax> taxMap = taxSummary.getTax().stream().collect(Collectors.toMap(Tax::getTaxCode, Function.identity()));

        ticketData.setBuildFee(Optional.ofNullable(taxMap.get("CN").getAmount().getValue()).orElse(BigDecimal.ZERO));
        ticketData.setOilFee(Optional.ofNullable(taxMap.get("YQ").getAmount().getValue()).orElse(BigDecimal.ZERO));
        return ticketData;
    }

    private List<String> parseProductRights(ProductRightDefinition definition) {

        List<RightDefinition> rightDefinitionList = definition.getRightDefinitionList();

        List<String> rightList = rightDefinitionList.stream().map(rightDefinition -> {
            final String rightDesc = ProductRightUtil.getRightDesc(rightDefinition.getRightCode(), rightDefinition.getRightLevel());
            if (StringUtils.isEmpty(rightDesc)) {
                return "T";
            }
            return rightDesc;
        }).collect(Collectors.toList());

        rightList.remove("T");

        return rightList;
    }

    private CorpApiFlightListDataV2 flightDataConvertFromPaxSegment(OriginDest originDest, PaxJourney paxJourney, PaxSegment paxSegment, String flightDate, String depCityCode, String destCityCode) {

        CorpApiFlightListDataV2 corpApiFlight = new CorpApiFlightListDataV2();

        // 渠道信息
        corpApiFlight.setOriginDestID(originDest.getOriginDestID());
        corpApiFlight.setPaxJourneyRefID(originDest.getPaxJourneyRefID());
        corpApiFlight.setPaxJourneyID(paxJourney.getPaxJourneyID());
        corpApiFlight.setPaxSegmentRefID(paxJourney.getPaxSegmentRefID());
        corpApiFlight.setPaxSegmentID(paxSegment.getPaxSegmentID());

        // 航班信息
        MarketingCarrierInfo marketingCarrierInfo = paxSegment.getMarketingCarrierInfo();
        OperatingCarrierInfo operatingCarrierInfo = paxSegment.getOperatingCarrierInfo();
        Dep dep = paxSegment.getDep();
        Arrival arrival = paxSegment.getArrival();
//        String duration = paxSegment.getDuration();

        Map<String, List<DatedOperatingLeg>> legMapping = paxSegment.getDatedOperatingLeg().stream().collect(Collectors.groupingBy(leg -> StringUtils.isEmpty(leg.getOnGroundDuration()) ? "0" : "1"));
        CarrierAircraftType carrierAircraftType = legMapping.get("0").get(0).getCarrierAircraftType();

        final String carrierDesigCode = marketingCarrierInfo.getCarrierDesigCode();
        String flightNumber = carrierDesigCode + marketingCarrierInfo.getMarketingCarrierFlightNumberText();
        // T: 共享航班 F：非共享航班
        final String operationalSuffixText = marketingCarrierInfo.getOperationalSuffixText();

        String operationCarrierFlightNumber = operatingCarrierInfo.getCarrierDesigCode() + operatingCarrierInfo.getOperatingCarrierFlightNumberText();

        corpApiFlight.setFlightDate(flightDate);
        corpApiFlight.setFlightNumber(flightNumber);

        corpApiFlight.setAirlineCode(carrierDesigCode);
        corpApiFlight.setAirlineShortName(carrierDesigCode.equals("MU")?"东方航空":"上海航空");

//        corpApiFlight.setTpm(null);
        corpApiFlight.setPlaneType(carrierAircraftType.getCarrierAircraftTypeName());
//        corpApiFlight.setMealType(null);

        final String depDateTime = dep.getAircraftScheduledDateTime();
        String departureTime = DateTimeUtils.parseStringToString(depDateTime, DateTimeUtils.PATTEN_YYYY_MM_DDTHH_MM_SS_SSS_XXX, DateTimeUtils.PATTEN_HHMM);
        corpApiFlight.setDepartureTime(departureTime);
        corpApiFlight.setDepartureCityCode(depCityCode);
        corpApiFlight.setDepartureAirportCode(dep.getIATALocationCode());
        corpApiFlight.setDepartureAirport(dep.getStationName());
        corpApiFlight.setDepartureTerminal(dep.getTerminalName());

        final String destDateTime = arrival.getAircraftScheduledDateTime();
        String arriveTime = DateTimeUtils.parseStringToString(destDateTime, DateTimeUtils.PATTEN_YYYY_MM_DDTHH_MM_SS_SSS_XXX, DateTimeUtils.PATTEN_HHMM);
        corpApiFlight.setDestinationTime(arriveTime);
        corpApiFlight.setDestinationCityCode(destCityCode);
        corpApiFlight.setDestinationAirportCode(arrival.getIATALocationCode());
        corpApiFlight.setDestinationAirport(arrival.getStationName());
        corpApiFlight.setDestinationTerminal(arrival.getTerminalName());

        corpApiFlight.setFlyingTime(getFlyTime(depDateTime, destDateTime, DateTimeUtils.PATTEN_YYYY_MM_DDTHH_MM_SS_SSS_XXX));

        corpApiFlight.setIsStopover("0");
        List<DatedOperatingLeg> datedOperatingLegs = legMapping.get("1");
        if (CollectionUtils.isNotEmpty(datedOperatingLegs)) {
            corpApiFlight.setIsStopover("1");
            corpApiFlight.setStopoverInfo(parseFlightStopOver(datedOperatingLegs));
        }

        if (BusinessEnum.OperationalSuffixText.F.getCode().equals(operationalSuffixText)) {

            corpApiFlight.setIsShare("0");
        } else {
            //共享航班
            corpApiFlight.setIsShare("1");
        }

        corpApiFlight.setMainAirlineCode(operatingCarrierInfo.getCarrierDesigCode());

        corpApiFlight.setMainFlightNumber(operationCarrierFlightNumber);
        corpApiFlight.setMainAirlineShortName(operatingCarrierInfo.getCarrierDesigCode().equals("MU")?"东方航空":"上海航空");

        corpApiFlight.setFlightId(FlightKeyUtils.getFlightId(flightDate, departureTime, arriveTime, flightNumber, corpApiFlight.getDepartureAirportCode(), corpApiFlight.getDestinationAirportCode()));
        return corpApiFlight;
    }

    private CorpApiTicketData ticketDataConvertFromCarrierOffer(CorpApiFlightListDataV2 flightData, OfferItem offerItem, List<ServiceDefinition> serviceDefinitionList, Map<String, BaggageAllowance> baggageAllowanceMap) {
        CorpApiTicketData ticketData = new CorpApiTicketData();

        final String offerItemID = offerItem.getOfferItemID();

        final ServiceDefinition seatSaleSd = serviceDefinitionList.stream().filter(sd -> sd.getName().equals("SEAT_SALE")).findFirst().orElseGet(ServiceDefinition::new);

        final FareDetail fareDetail = offerItem.getFareDetail().get(0);

        final FareComponent fareComponent = fareDetail.getFareComponent().get(0);

        final String seatClassCode = fareComponent.getRbd().getRBDCode();
        final CabinType cabinType = fareComponent.getCabinType();
        final Price price = fareComponent.getPrice();
        final Discount discount = price.getDiscount();
        final BaseAmount baseAmount = price.getBaseAmount();

        ticketData.setSeatClassCode(seatClassCode);
        ticketData.setFlightId(flightData.getFlightId());
        ticketData.setMainClassCode(cabinType.getCabinTypeCode());
        ticketData.setMainClassName(cabinType.getCabinTypeName());
        ticketData.setIsWebSite("1");

        ticketData.setSeatClassName(ticketData.getMainClassName());
        ticketData.setProductType(BusinessEnum.ProductType.WEBSITE.getCode());

        ServiceDefinitionAssociation serviceDefinitionAssociation = seatSaleSd.getServiceDefinitionAssociation();
        ServiceBundle serviceBundle;
        if(serviceDefinitionAssociation != null && (serviceBundle = serviceDefinitionAssociation.getServiceBundle()) != null){
            ticketData.setQuantity(serviceBundle.getMaximumServiceQty());
        }
        ticketData.setTicketPrice(new BigDecimal(baseAmount.getValue()));
        ticketData.setPrice(ticketData.getTicketPrice());
        ticketData.setPurchasePrice(ticketData.getTicketPrice());
        ticketData.setTicketId(FlightKeyUtils.getTicketId(flightData.getFlightId(), ticketData.getProductType(), ticketData.getSeatClassCode(), offerItemID));
        if (discount != null) {
            ticketData.setFdPrice(discount.getDiscountAmount().getValue());
        }else {
            ticketData.setFdPrice(ticketData.getTicketPrice());
        }
        ticketData.setDiscount(ticketData.getTicketPrice().divide(ticketData.getFdPrice(),2, RoundingMode.HALF_UP));
        ticketData.setOfferItemId(offerItemID);

        FlightBaggageInfoData baggageInfoData = new FlightBaggageInfoData();
        ticketData.setServiceDefinitionList(getServiceDefinitionList(serviceDefinitionList, baggageAllowanceMap, baggageInfoData));

        List<FareRule> fareRule = fareComponent.getFareRule();
        CorpApiTicketPolicy corpApiTicketPolicy = parseTicketPolicy(fareRule, flightData.getFlightDate()+" "+flightData.getDepartureTime(), ticketData.getTicketPrice());
        corpApiTicketPolicy.setFlightBaggageInfoData(baggageInfoData);
        corpApiTicketPolicy.setBaggageInfo(parseBaggageInfoStr(baggageInfoData));
        ticketData.setPolicy(corpApiTicketPolicy);

        return ticketData;
    }

    public static String parseBaggageInfoStr(FlightBaggageInfoData baggageInfoData) {
        if (StringUtils.isEmpty(baggageInfoData.getFreeBaggageWeight()) || baggageInfoData.getFreeBaggageWeight().equals("0")) {
            return MessageFormat.format("无免费行李额；随身行李额：{0},限带{1}件", baggageInfoData.getCarryOnBaggageWeight(), baggageInfoData.getCarryOnBaggageAmount());
        }
        return MessageFormat.format("免费行李额：{0}；随身行李额：{1},限带{2}件", baggageInfoData.getFreeBaggageWeight(), baggageInfoData.getCarryOnBaggageWeight(), baggageInfoData.getCarryOnBaggageAmount());
    }

    public List<TicketServiceDefinition> getServiceDefinitionList(List<ServiceDefinition> serviceDefinitionList, Map<String, BaggageAllowance> baggageAllowanceMap, FlightBaggageInfoData baggageInfoData) {
        return Optional.ofNullable(serviceDefinitionList).orElseGet(Arrays::asList).stream().map(serviceDefinition -> {
            final TicketServiceDefinition definition = new TicketServiceDefinition();

            definition.setServiceDefinitionID(serviceDefinition.getServiceDefinitionID());
            definition.setServiceCode(serviceDefinition.getServiceCode());
            definition.setDescText(Optional.ofNullable(serviceDefinition.getDesc()).orElseGet(Desc::new).getDescText());
            definition.setName(serviceDefinition.getName());
            final ServiceDefinitionAssociation serviceDefinitionAssociation = serviceDefinition.getServiceDefinitionAssociation();

            BaggageAllowance baggageAllowance;
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
                    baggageInfoData.setCarryOnBaggageWeight(maximumWeightMeasure.getValue() + unitStr);
                    baggageInfoData.setCarryOnBaggageAmount(pieceAllowance.getTotalQty());
                    baggageInfoData.setCarryOnBaggageVolume(volume);
                }else if (BusinessEnum.BaggageType.CHECKED.getCode().equals(typeCode)){

                    baggageInfoData.setFreeBaggageWeight(maximumWeightMeasure.getValue() + unitStr);
                    baggageInfoData.setFreeBaggageAmount(pieceAllowance.getTotalQty());
                    baggageInfoData.setFreeBaggageVolume(volume);
                }
            }

            return definition;
        }).collect(Collectors.toList());
    }

    public static final String refundPolicyBeginStr = "航班规定离站时间%s%s（%s）之前，收取%s退票费 ；";
    public static final String refundPolicyStr = "航班规定离站时间前%s%s（%s）至航班规定离站时间前%s%s（%s），收取%s退票费；";
    public static final String refundPolicyEndStr = "航班规定离站时间前%s%s（%s）至航班起飞后，收取%s退票费；";

    public static final String changePolicyBeginStr = "航班规定离站时间%s%s（%s）之前，收取%s的改期费；";
    public static final String changePolicyStr = "航班规定离站时间前%s%s（%s）至航班规定离站时间前%s%s（%s），收取%s的改期费；";
    public static final String changePolicyEndStr = "航班规定离站时间前%s%s（%s）至航班起飞后，收取%s的改期费；";

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DateTimeUtils.PATTEN_YYYY_MM_DD_HHMM);
    private static final DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern(DateTimeUtils.PATTEN_YYYY_年_MM_月_DD_日_HH_MM);

    private static CorpApiTicketPolicy parseTicketPolicy(List<FareRule> fareRule, String flightDate, BigDecimal ticketPrice) {
        CorpApiTicketPolicy policy = new CorpApiTicketPolicy();
        List<TgqPointChargeInfo> tgqPointChargeInfoList = new ArrayList<>();

        StringBuilder refundPolicy = new StringBuilder();
        StringBuilder changePolicy = new StringBuilder();

        LocalDateTime flyDate = LocalDateTime.parse(flightDate, dateTimeFormatter);

        for (int i = fareRule.size()-1; i >=0; i--){

            FareRule rule = fareRule.get(i);
            Remark remark = rule.getRemark().get(0);

            RemarkText remarkText = JSONObject.parseObject(remark.getRemarkText(), RemarkText.class);
            final String useFlag = remarkText.getUseFlag(); // 0使用前 1使用后
            if ("1".equals(useFlag)){
                continue;
            }

//            String timeFlag = remarkText.getTimeFlag(); //0 航前 1 航后
            Long maxTime = remarkText.getMaxTime(), minTime = remarkText.getMinTime(); //时间
            String maxTimeUnit = remarkText.getMaxTimeUnit(), minTimeUnit = remarkText.getMinTimeUnit(); //单位，D(天),N(分钟),H(小时),M(月)

            TgqPointChargeInfo chargeInfo = new TgqPointChargeInfo();
            for (Penalty penalty : rule.getPenalty()) {

                String penaltyPercent = penalty.getPenaltyPercent();
                // 起飞前
                if (penaltyPercent == null || !penalty.getAppCode().equals("PDE")) {
                    continue;
                }

                BigDecimal penaltyAmount = new BigDecimal(penaltyPercent).divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP).multiply(ticketPrice).setScale(0, BigDecimal.ROUND_HALF_UP);
                // 退票
                if ("Cancellation".equals(penalty.getTypeCode())) {
                    chargeInfo.setReturnFee(penaltyAmount);
                    parseDate(flyDate, minTime, minTimeUnit, maxTime, maxTimeUnit, chargeInfo);

                    refundPolicyParse(refundPolicy, remarkText, penaltyPercent);
                }

                // 改签
                if ("Upgrade".equals(penalty.getTypeCode())){
                    chargeInfo.setChangeFee(penaltyAmount);

                    parseDate(flyDate, minTime, minTimeUnit, maxTime, maxTimeUnit, chargeInfo);

                    changePolicyParse(changePolicy, remarkText, penaltyPercent);
                }
            }
            if (StringUtils.isNoneBlank(chargeInfo.getTimeText())) {
                tgqPointChargeInfoList.add(chargeInfo);
            }
        }

        policy.setChangePolicy(changePolicy.toString());
        policy.setRefundPolicy(refundPolicy.toString());
        policy.setTgqPointChargeInfoList(tgqPointChargeInfoList.stream().sorted(Comparator.comparing(TgqPointChargeInfo::getTimeText)).collect(Collectors.toList()));

        return policy;
    }

    private static void refundPolicyParse(StringBuilder refundPolicy, RemarkText remarkText, String penaltyPercent){
        policyParse(refundPolicy, remarkText, penaltyPercent, refundPolicyEndStr, refundPolicyBeginStr, refundPolicyStr);
    }

    private static void changePolicyParse(StringBuilder changePolicy, RemarkText remarkText, String penaltyPercent){

        policyParse(changePolicy, remarkText, penaltyPercent, changePolicyEndStr, changePolicyBeginStr, changePolicyStr);
    }

    private static void policyParse(StringBuilder refundPolicy, RemarkText remarkText, String penaltyPercent, String refundPolicyEndStr, String refundPolicyBeginStr, String refundPolicyStr) {
        Long maxTime = remarkText.getMaxTime(), minTime = remarkText.getMinTime();
        String maxTimeUnit = remarkText.getMaxTimeUnit(), minTimeUnit = remarkText.getMinTimeUnit();
        String maxTimeFlag = remarkText.getMaxTimeFlag(), minTimeFlag = remarkText.getMinTimeFlag(); //是否包含,0不包含1包含

        if (maxTime == 0) {
            refundPolicy.append(String.format(refundPolicyEndStr, minTime, BusinessEnum.DateType.getDateMsgByCode(minTimeUnit), BusinessEnum.MaxTimeFlag.getMaxTimeFlagByCode(minTimeFlag), penaltyPercent+"%"));
        }else if (minTime == 12) {
            refundPolicy.append(String.format(refundPolicyBeginStr, maxTime, BusinessEnum.DateType.getDateMsgByCode(maxTimeUnit), BusinessEnum.MaxTimeFlag.getMaxTimeFlagByCode(maxTimeFlag), penaltyPercent+"%"));
        }else {
            refundPolicy.append(String.format(refundPolicyStr, minTime, BusinessEnum.DateType.getDateMsgByCode(minTimeUnit), BusinessEnum.MaxTimeFlag.getMaxTimeFlagByCode(minTimeFlag)
                    , maxTime, BusinessEnum.DateType.getDateMsgByCode(maxTimeUnit), BusinessEnum.MaxTimeFlag.getMaxTimeFlagByCode(maxTimeFlag)
                    , penaltyPercent+"%"));
        }
    }

    private static void parseDate(LocalDateTime flyDate, Long minTime, String minTimeUnit, Long maxTime, String maxTimeUnit, TgqPointChargeInfo chargeInfo) {
        if (maxTime == 0) {
            switch (minTimeUnit) {
                case "D":
                    chargeInfo.setTimeText(flyDate.minusDays(minTime).format(NdcFlightSearchHandler.dateTimeFormatter1) + "后");
                    break;
                case "N":
                    chargeInfo.setTimeText(flyDate.minusMinutes(minTime).format(NdcFlightSearchHandler.dateTimeFormatter1) + "后");
                    break;
                case "H":
                    chargeInfo.setTimeText(flyDate.minusHours(minTime).format(NdcFlightSearchHandler.dateTimeFormatter1) + "后");
                    break;
                case "M":
                    chargeInfo.setTimeText(flyDate.minusMonths(minTime).format(NdcFlightSearchHandler.dateTimeFormatter1) + "后");
                    break;
            }
        }else {
            switch (maxTimeUnit) {
                case "D":
                    chargeInfo.setTimeText(flyDate.minusDays(maxTime).format(NdcFlightSearchHandler.dateTimeFormatter1) + "前");
                    break;
                case "N":
                    chargeInfo.setTimeText(flyDate.minusMinutes(maxTime).format(NdcFlightSearchHandler.dateTimeFormatter1) + "前");
                    break;
                case "H":
                    chargeInfo.setTimeText(flyDate.minusHours(maxTime).format(NdcFlightSearchHandler.dateTimeFormatter1) + "前");
                    break;
                case "M":
                    chargeInfo.setTimeText(flyDate.minusMonths(maxTime).format(NdcFlightSearchHandler.dateTimeFormatter1) + "前");
                    break;
            }
        }
    }

    private static List<FlightStopOver> parseFlightStopOver(List<DatedOperatingLeg> stops) {
        AtomicInteger seq = new AtomicInteger();
        return stops.stream().map(leg -> {
            FlightStopOver flightStopOver = new FlightStopOver();

            flightStopOver.setStep(String.valueOf(seq.getAndIncrement()));
            flightStopOver.setStopCityCode(leg.getArrival().getIATALocationCode());
            flightStopOver.setStopTime(leg.getOnGroundDuration());

            return flightStopOver;
        }).collect(Collectors.toList());
    }

    public static IATAAirShoppingRQ getFlightSearchParams(String flightDate, String depCityCode, String destCityCode) {
        IATAAirShoppingRQ iataAirShoppingRQ = new IATAAirShoppingRQ();

        // Request
        Request request = new Request();
        FlightCriteria flightCriteria = new FlightCriteria();
        AffinityShoppingCriteria affinityShoppingCriteria = new AffinityShoppingCriteria();

        AffinityOriginDest affinityOriginDest = getAffinityOriginDest(flightDate, depCityCode, destCityCode);

        affinityShoppingCriteria.setAffinityOriginDest(Collections.singletonList(affinityOriginDest));

        flightCriteria.setAffinityShoppingCriteria(affinityShoppingCriteria);

        request.setFlightCriteria(flightCriteria);

        iataAirShoppingRQ.setRequest(request);
        return iataAirShoppingRQ;
    }

    private static AffinityOriginDest getAffinityOriginDest(String flightDate, String depCityCode, String destCityCode) {
        AffinityOriginDest affinityOriginDest = new AffinityOriginDest();

        AffinityDepRequest affinityDepRequest = new AffinityDepRequest();

        CountrySubDivision countrySubDivisionDep = new CountrySubDivision();
        countrySubDivisionDep.setCountrySubDivisionCode(depCityCode);
        Station stationDep = new Station();
        stationDep.setIATALocationCode("");
        affinityDepRequest.setStation(stationDep);
        affinityDepRequest.setCountrySubDivision(countrySubDivisionDep);
        affinityDepRequest.setDate(flightDate);
        affinityOriginDest.setAffinityDepRequest(affinityDepRequest);

        AffinityArrivalRequest affinityArrivalRequest = new AffinityArrivalRequest();
        CountrySubDivision countrySubDivision = new CountrySubDivision();
        countrySubDivision.setCountrySubDivisionCode(destCityCode);
        Station station = new Station();
        station.setIATALocationCode("");
        affinityArrivalRequest.setCountrySubDivision(countrySubDivision);
        affinityArrivalRequest.setStation(station);
        affinityOriginDest.setAffinityArrivalRequest(affinityArrivalRequest);

        affinityOriginDest.setConnectionPRefRefID("1");

        return affinityOriginDest;
    }

    public static String getFlyTime(String depTime, String arrTime, String dateFormat) {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormat);

        LocalDateTime depDate = LocalDateTime.parse(depTime, dateTimeFormatter);
        LocalDateTime arrDate = LocalDateTime.parse(arrTime, dateTimeFormatter);

        Duration duration = Duration.between(depDate, arrDate);

        long seconds = duration.getSeconds();

        long hour = seconds / 3600;
        long minutes = ((seconds % 3600) / 60);

        return (hour == 0 ? "00" : (hour < 10 ? "0"+hour : hour)) + ":" +(minutes == 0 ? "00" : (minutes < 10 ? "0"+minutes : minutes));
    }
}
