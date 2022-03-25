package com.ndc.channel.flight.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ndc.channel.entity.NdcFlightApiOrderRel;
import com.ndc.channel.enumtype.BusinessEnum;
import com.ndc.channel.exception.BusinessException;
import com.ndc.channel.exception.BusinessExceptionCode;
import com.ndc.channel.flight.dto.changeFlightSearch.ChangeFlightQueryReq;
import com.ndc.channel.flight.dto.flightSearch.*;
import com.ndc.channel.flight.tools.DateTimeUtils;
import com.ndc.channel.flight.tools.NdcApiTools;
import com.ndc.channel.flight.xmlBean.changeFlightSearch.request.bean.*;
import com.ndc.channel.flight.xmlBean.changeFlightSearch.response.bean.*;
import com.ndc.channel.flight.xmlBean.changeFlightSearch.response.bean.Error;
import com.ndc.channel.flight.xmlBean.flightSearch.response.RemarkText;
import com.ndc.channel.mapper.NdcFlightApiOrderRelMapper;
import com.ndc.channel.redis.RedisKeyConstants;
import com.ndc.channel.redis.RedisUtils;
import com.ndc.channel.util.FlightKeyUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 改签航班查询
 */
@Slf4j
@Component
public class NdcFlightChangeFlightSearchHandler {

    @Resource
    private NdcApiTools ndcApiTools;

    @Resource
    private RedisUtils redisUtils;

    @Resource
    private NdcFlightApiOrderRelMapper orderRelMapper;

    public List<CorpApiFlightListDataV2> changeFlightSearch(ChangeFlightQueryReq req) {


        final IATAOrderReshopRQ rq = createIATAOrderReshopRQ(req);
        IATAOrderReshopRS iataOrderReshopRS = ndcApiTools.changeFlightSearch(rq);

        final Error error = iataOrderReshopRS.getError();
        if (error!=null) {
            throw new BusinessException(BusinessExceptionCode.REQUEST_PARAM_ERROR, error.getDescText());
        }

        Response response = iataOrderReshopRS.getResponse();

        return responseConvert(response, req.getFlightDate(), req.getDepAirportCode(), req.getDepAirportCode());
    }

    public List<CorpApiFlightListDataV2> responseConvert(Response response, String flightDate, String depCityCode, String destCityCode) {

        final ShoppingResponse shoppingResponse = response.getShoppingResponse();

        final ReshopResults reshopResults = response.getReshopResults();
        final DataLists dataLists = response.getDataLists();
        String everyFlightDateKey=flightDate+depCityCode+destCityCode;
        // 成人票检查
        Pax pax = dataLists.getPaxList().stream()
                .filter(p -> p.getPTC().equals("ADT")).findFirst()
                .orElseGet(()->{
                    log.error("NDC航班未查到成人航班, everyFlightDateKey=", everyFlightDateKey);
                    throw new BusinessException(BusinessExceptionCode.REQUEST_PARAM_ERROR, "NDC航班未查到成人航班！");
                });

        // 出发和到达数据
        final OriginDest originDest = dataLists.getOriginDestList().get(0);
        final String paxJourneyRefID = originDest.getPaxJourneyRefID(); // 乘客行程ID

        // 乘客行程
        final List<PaxJourney> paxJourneyList = dataLists.getPaxJourneyList().stream()
                .filter(paxJourney -> paxJourneyRefID.equals(paxJourney.getPaxJourneyID()))
                .collect(Collectors.toList());

        // 乘客航段
        final List<PaxSegment> paxSegmentList = dataLists.getPaxSegmentList();

        // 服务定义列表
        final List<ServiceDefinition> serviceDefinitionList = dataLists.getServiceDefinitionList();

        // 行李额信息
        List<BaggageAllowance> baggageAllowanceList = dataLists.getBaggageAllowanceList();
        final Map<String, BaggageAllowance> baggageAllowanceMap = baggageAllowanceList.stream().collect(Collectors.toMap(BaggageAllowance::getBaggageAllowanceID, Function.identity()));

        // 价格类型列表
        final List<PriceClass> priceClassList = dataLists.getPriceClassList();
        final Map<String, PriceClass> priceClassMap = priceClassList.stream().collect(Collectors.toMap(PriceClass::getPriceClassID, Function.identity()));

        // 转换后的航班列表
        List<CorpApiFlightListDataV2> flightDataList = new ArrayList<>();

        // 遍历乘客航段和乘客行程，查找与乘客行程相匹配的航段
        for (PaxSegment paxSegment : paxSegmentList) {
            for (PaxJourney paxJourney : paxJourneyList) {

                if (paxJourney.getPaxSegmentRefID().contains(paxSegment.getPaxSegmentID())) {
                    CorpApiFlightListDataV2 corpApiFlightListDataV2 = flightDataConvertFromPaxSegment(originDest, paxJourney, paxSegment, flightDate, depCityCode, destCityCode);

                    corpApiFlightListDataV2.setShoppingResponseID(shoppingResponse.getShoppingResponseID());
                    flightDataList.add(corpApiFlightListDataV2);
                }
            }
        }

        // 报价信息
        final Offer offer = reshopResults.getReshopOffers().stream()
                .filter(of -> of.getPTCOfferParameters().getPTCPricedCode().equals(pax.getPTC()))
                .findFirst().orElseGet(()->{
                    throw new BusinessException(BusinessExceptionCode.REQUEST_PARAM_ERROR, "NDC航班查询未查到成人舱位！");
                });

        final String ownerTypeCode = offer.getOwnerTypeCode();
        final String ownerCode = offer.getOwnerCode();
        final Map<String, String> persistenceFlightDataMap= new HashMap<>(32);
        final Map<String, Map<String, String>> persistenceTicketDataMap = new HashMap<>(32);
        for (CorpApiFlightListDataV2 flightData : flightDataList) {

            persistenceFlightDataMap.put(RedisKeyConstants.getRedisFlightChangeDataCacheKey(flightData.getFlightId()), JSON.toJSONString(flightData));

            final Map<String, CorpApiTicketData> corpApiTicketMap = new HashMap<>();

            // 遍历报价项目
            for (AddOfferItem offerItem : offer.getAddOfferItem()) {

                // 报价详细信息
                FareDetail fareDetail = offerItem.getFareDetail();

                // 票价计算组件
                FareComponent fareComponent = fareDetail.getFareComponent();
                // 票价组关联航段ID
                List<String> paxSegmentRefID = fareComponent.getPaxSegmentRefID();
                // 票价组关联价格类型ID
                final String priceClassRefID = fareComponent.getPriceClassRefID();

//                final String fareTypeCode = fareComponent.getFareTypeCode();

                final PriceClass priceClass = priceClassMap.get(priceClassRefID);

                // 价格来源
                final String pricingSystemCodeText = fareDetail.getPricingSystemCodeText();

                if (paxSegmentRefID.contains(flightData.getPaxSegmentID())) {

                    // 乘客与服务之间的映射
                    Map<String, List<String>> serviceDefinitionRefIDMapping = Optional.ofNullable(offerItem.getService()).orElseGet(Arrays::asList).stream()
                            .collect(Collectors.groupingBy(
                                    ser -> ser.getPaxRefID() + ser.getServiceAssociations().getServiceDefinitionRef().getPaxSegmentRefID(),
                                    Collectors.mapping(ser -> ser.getServiceAssociations().getServiceDefinitionRef().getServiceDefinitionRefID(),
                                            Collectors.toList())));

                    final List<String> serviceDefinitionIdList = serviceDefinitionRefIDMapping.get(pax.getPaxID() + flightData.getPaxSegmentID());
                    if (CollectionUtils.isEmpty(serviceDefinitionIdList)) {
                        continue;
                    }
                    List<ServiceDefinition> paxServiceDefinitionList = serviceDefinitionList.stream().filter(sd -> serviceDefinitionIdList.contains(sd.getServiceDefinitionID())).collect(Collectors.toList());

                    CorpApiTicketData ticketData = ticketDataConvertFromCarrierOffer(flightData.getFlightId(), offerItem, paxServiceDefinitionList, baggageAllowanceMap, priceClass);
                    ticketData.setPaxId(pax.getPaxID());
                    ticketData.setPricingSystemCodeText(pricingSystemCodeText);
                    ticketData.setPriceClassID(priceClass.getPriceClassID());
                    ticketData.setPriceClassCode(priceClass.getCode());
                    ticketData.setPriceClassName(priceClass.getName());
//                    ticketData.setPriceClassDesc(priceClass.getDesc().getDescText());
//                    ticketData.setFareTypeCode(fareTypeCode);

                    TaxSummary taxSummary = offerItem.getPrice().getTaxSummary();

                    corpApiTicketMap.put(ticketData.getTicketId(), ticketData);
                    flightData.setBuildFee(new BigDecimal(taxSummary.getTotalTaxAmount().getValue()));
                }
            }

            flightData.setOfferId(offer.getOfferID());
            flightData.setOwnerCode(ownerCode);
            flightData.setOwnerTypeCode(ownerTypeCode);
            flightData.setOilFee(BigDecimal.ZERO);
            List<CorpApiTicketData> ticketDataList = corpApiTicketMap.values().stream().collect(Collectors.toList());

            final boolean isPresent = ticketDataList.get(0).getServiceDefinitionList().stream().filter(serviceDef -> BusinessEnum.ServiceName.MEAL.name().equals(serviceDef.getName())).findFirst().isPresent();
            flightData.setMealType(isPresent ? "1" : "0");
            flightData.setTickets(ticketDataList);

            final Map<String, String> ticketStrMap = corpApiTicketMap.values().stream().collect(Collectors.toMap(CorpApiTicketData::getTicketId, v -> JSON.toJSONString(v)));
            persistenceTicketDataMap.put(RedisKeyConstants.getRedisTicketChangeDataCacheKey(flightData.getFlightId()), ticketStrMap);
        }

        redisUtils.multiSetAndExpire(persistenceFlightDataMap, TimeUnit.DAYS, 1);
        redisUtils.hPutAndExpireAt(persistenceTicketDataMap, TimeUnit.DAYS, 1);

        return flightDataList;
    }

    private CorpApiFlightListDataV2 flightDataConvertFromPaxSegment(OriginDest originDest, PaxJourney paxJourney, PaxSegment paxSegment, String flightDate, String depCityCode, String destCityCode) {

        CorpApiFlightListDataV2 corpApiFlight = new CorpApiFlightListDataV2();

        // 渠道信息
        corpApiFlight.setOriginDestID(originDest.getOriginDestID());
        corpApiFlight.setPaxJourneyRefID(Arrays.asList(originDest.getPaxJourneyRefID()));
        corpApiFlight.setPaxJourneyID(paxJourney.getPaxJourneyID());
        corpApiFlight.setPaxSegmentRefID(Arrays.asList(paxJourney.getPaxSegmentRefID()));
        corpApiFlight.setPaxSegmentID(paxSegment.getPaxSegmentID());

        // 航班信息
        MarketingCarrierInfo marketingCarrierInfo = paxSegment.getMarketingCarrierInfo();
        OperatingCarrierInfo operatingCarrierInfo = paxSegment.getOperatingCarrierInfo();
        Dep dep = paxSegment.getDep();
        Arrival arrival = paxSegment.getArrival();
//        String duration = paxSegment.getDuration();

//        Map<String, List<DatedOperatingLeg>> legMapping = paxSegment.getDatedOperatingLeg().stream().collect(Collectors.groupingBy(leg -> StringUtils.isEmpty(leg.getOnGroundDuration()) ? "0" : "1"));
        CarrierAircraftType carrierAircraftType = paxSegment.getDatedOperatingLeg().get(0).getCarrierAircraftType();

        String flightNumber = marketingCarrierInfo.getCarrierDesigCode() + marketingCarrierInfo.getMarketingCarrierFlightNumberText();
        // T: 共享航班 F：非共享航班
        final String operationalSuffixText = marketingCarrierInfo.getOperationalSuffixText();

        String operationCarrierFlightNumber = operatingCarrierInfo.getCarrierDesigCode() + operatingCarrierInfo.getOperatingCarrierFlightNumberText();

        corpApiFlight.setFlightDate(flightDate);
        corpApiFlight.setFlightNumber(flightNumber);
        corpApiFlight.setAirlineCode(marketingCarrierInfo.getCarrierDesigCode());
        corpApiFlight.setAirlineShortName("东方航空");


        corpApiFlight.setTpm(null);
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
        /*List<DatedOperatingLeg> datedOperatingLegs = legMapping.get("1");
        if (CollectionUtils.isNotEmpty(datedOperatingLegs)) {
            corpApiFlight.setIsStopover("1");
            corpApiFlight.setStopoverInfo(parseFlightStopOver(datedOperatingLegs));
        }*/

        if (BusinessEnum.OperationalSuffixText.F.getCode().equals(operationalSuffixText)) {

            corpApiFlight.setIsShare("0");
        } else {
            //共享航班
            corpApiFlight.setIsShare("1");
        }

        corpApiFlight.setMainAirlineCode(operatingCarrierInfo.getCarrierDesigCode());

        corpApiFlight.setMainFlightNumber(operationCarrierFlightNumber);
        corpApiFlight.setMainAirlineShortName("东方航空");

        corpApiFlight.setFlightId(FlightKeyUtils.getFlightId(flightDate, departureTime, arriveTime, flightNumber, corpApiFlight.getDepartureAirportCode(), corpApiFlight.getDestinationAirportCode()));
        return corpApiFlight;
    }


    private CorpApiTicketData ticketDataConvertFromCarrierOffer(String flightId, AddOfferItem offerItem, List<ServiceDefinition> serviceDefinitionList, Map<String, BaggageAllowance> baggageAllowanceMap, PriceClass priceClass) {
        CorpApiTicketData ticketData = new CorpApiTicketData();

        final String offerItemID = offerItem.getOfferItemID();

        final Price diffPrice = offerItem.getPrice();
        final String upgradeTotalAmount = diffPrice.getTotalAmount().getValue();

        final ServiceDefinition seatSaleSd = serviceDefinitionList.stream().filter(sd -> sd.getName().equals("SEAT_SALE")).findFirst().orElse(null);

        final FareDetail fareDetail = offerItem.getFareDetail();
        final FarePriceType farePriceType = fareDetail.getFarePriceType();
        final String farePriceTypeCode = farePriceType.getFarePriceTypeCode();
        final Price price = farePriceType.getPrice();

        final FareComponent fareComponent = fareDetail.getFareComponent();

        final String seatClassCode = fareComponent.getRbd().getRBDCode();
        final CabinType cabinType = fareComponent.getCabinType();
        final BaseAmount baseAmount = price.getBaseAmount();

        ticketData.setSeatClassCode(seatClassCode);
        ticketData.setFlightId(flightId);
        ticketData.setMainClassCode(cabinType.getCabinTypeCode());
        ticketData.setMainClassName(priceClass.getName());
        ticketData.setIsWebSite(BusinessEnum.ProductType.WEBSITE.getCode());
        ticketData.setSeatClassName(ticketData.getMainClassName());
        ticketData.setProductType(BusinessEnum.ProductType.WEBSITE.getCode());
        ticketData.setQuantity(seatSaleSd.getServiceDefinitionAssociation().getServiceBundle().getMaximumServiceQty());
        ticketData.setTicketPrice(new BigDecimal(baseAmount.getValue()));
        ticketData.setPrice(ticketData.getTicketPrice());
        ticketData.setPurchasePrice(ticketData.getTicketPrice());
        ticketData.setTicketId(FlightKeyUtils.getTicketId(flightId, ticketData.getProductType(), ticketData.getSeatClassCode(), offerItemID));

        ticketData.setOfferItemId(offerItemID);

        FlightBaggageInfoData baggageInfoData = new FlightBaggageInfoData();
        ticketData.setServiceDefinitionList(getServiceDefinitionList(serviceDefinitionList, baggageAllowanceMap, baggageInfoData));

        List<FareRule> fareRule = fareComponent.getFareRule();
        CorpApiTicketPolicy corpApiTicketPolicy = parseTicketPolicy(fareRule);
        corpApiTicketPolicy.setFlightBaggageInfoData(baggageInfoData);
        ticketData.setPolicy(corpApiTicketPolicy);
        ticketData.setUpgradeTotalAmount(new BigDecimal(upgradeTotalAmount));

        return ticketData;
    }

    public List<TicketServiceDefinition> getServiceDefinitionList(List<ServiceDefinition> serviceDefinitionList, Map<String, BaggageAllowance> baggageAllowanceMap, FlightBaggageInfoData baggageInfoData) {
        final List<TicketServiceDefinition> ticketServiceDefinitionList = Optional.ofNullable(serviceDefinitionList).orElseGet(Arrays::asList).stream().map(serviceDefinition -> {
            final TicketServiceDefinition definition = new TicketServiceDefinition();

            definition.setServiceDefinitionID(serviceDefinition.getServiceDefinitionID());
            definition.setServiceCode(serviceDefinition.getServiceCode());
            definition.setDescText(Optional.ofNullable(serviceDefinition.getDesc()).orElseGet(Desc::new).getDescText());
            definition.setName(serviceDefinition.getName());
            final ServiceDefinitionAssociation serviceDefinitionAssociation = serviceDefinition.getServiceDefinitionAssociation();

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

    public static final String refundPolicyBeginStr = "航班规定离站时间%s%s（%s）之前，收取%s退票费 ；";
    public static final String refundPolicyStr = "航班规定离站时间前%s%s（%s）至航班规定离站时间前%s%s（%s），收取%s退票费 ；";
    public static final String refundPolicyEndStr = "航班规定离站时间前%s%s（%s）至航班起飞后，收取%s退票费；";

    public static final String changePolicyBeginStr = "航班离站时间%s%s（%s）之前，收取%s的改期费；";
    public static final String changePolicyStr = "航班离站时间前%s%s（%s）至航班离站时间前%s%s（%s），收取%s的改期费；";
    public static final String changePolicyEndStr = "航班离站时间前%s%s（%s）至航班起飞后，收取%s的改期费；";

    private static CorpApiTicketPolicy parseTicketPolicy(List<FareRule> fareRule) {
        CorpApiTicketPolicy policy = new CorpApiTicketPolicy();

        StringBuilder refundPolicy = new StringBuilder();
        StringBuilder changePolicy = new StringBuilder();
        for (FareRule rule : fareRule) {

            Remark remark = rule.getRemark();

            RemarkText remarkText = JSONObject.parseObject(remark.getRemarkText(), RemarkText.class);
            final String useFlag = remarkText.getUseFlag(); // 0使用前 1使用后
            if (useFlag.equals("1")){
                continue;
            }

            final String timeFlag = remarkText.getTimeFlag(); //0 航前 1 航后

            final String maxTimeUnit = remarkText.getMaxTimeUnit(); //单位，D(天),N(分钟),H(小时),M(月)
            final String maxTimeFlag = remarkText.getMaxTimeFlag(); //是否包含,0不包含1包含
            final Long maxTime = remarkText.getMaxTime(); //时间

            final String minTimeUnit = remarkText.getMinTimeUnit();
            final String minTimeFlag = remarkText.getMinTimeFlag();
            final Long minTime = remarkText.getMinTime();


            for (Penalty penalty : rule.getPenalty()) {
                final String penaltyPercent = penalty.getPenaltyAmount().getValue();

                if (penaltyPercent == null) {
                    continue;
                }
                // 起飞后
                if (penalty.getAppCode().equals("ADE")) {
                }

                // 起飞前
                if (penalty.getAppCode().equals("PDE")) {
                    // 退票
                    if ("Cancellation".equals(penalty.getTypeCode())) {

                        if (maxTime.equals("0")) {
                            refundPolicy.append(String.format(refundPolicyEndStr, minTime, BusinessEnum.DateType.getDateMsgByCode(minTimeUnit), BusinessEnum.MaxTimeFlag.getMaxTimeFlagByCode(minTimeFlag), penaltyPercent+"%"));
                        }else if (minTime.equals("0")) {
                            refundPolicy.append(String.format(refundPolicyBeginStr, maxTime, BusinessEnum.DateType.getDateMsgByCode(maxTimeUnit), BusinessEnum.MaxTimeFlag.getMaxTimeFlagByCode(maxTimeFlag), penaltyPercent+"%"));
                        }else {
                            refundPolicy.append(String.format(refundPolicyStr, minTime, BusinessEnum.DateType.getDateMsgByCode(minTimeUnit), BusinessEnum.MaxTimeFlag.getMaxTimeFlagByCode(minTimeFlag)
                                    , maxTime, BusinessEnum.DateType.getDateMsgByCode(maxTimeUnit), BusinessEnum.MaxTimeFlag.getMaxTimeFlagByCode(maxTimeFlag)
                                    , penaltyPercent+"%"));
                        }
                    }

                    // 改签
                    if ("Upgrade".equals(penalty.getTypeCode())){

                        if (maxTime.equals("0")) {
                            changePolicy.append(String.format(changePolicyEndStr, minTime, BusinessEnum.DateType.getDateMsgByCode(minTimeUnit), BusinessEnum.MaxTimeFlag.getMaxTimeFlagByCode(minTimeFlag), penaltyPercent+"%"));
                        }else if (minTime.equals("0")) {
                            changePolicy.append(String.format(changePolicyBeginStr, maxTime, BusinessEnum.DateType.getDateMsgByCode(maxTimeUnit), BusinessEnum.MaxTimeFlag.getMaxTimeFlagByCode(maxTimeFlag), penaltyPercent+"%"));
                        }else {
                            changePolicy.append(String.format(changePolicyStr, minTime, BusinessEnum.DateType.getDateMsgByCode(minTimeUnit), BusinessEnum.MaxTimeFlag.getMaxTimeFlagByCode(minTimeFlag)
                                    , maxTime, BusinessEnum.DateType.getDateMsgByCode(maxTimeUnit), BusinessEnum.MaxTimeFlag.getMaxTimeFlagByCode(maxTimeFlag)
                                    , penaltyPercent+"%"));
                        }
                    }
                }
            }
        }

        policy.setChangePolicy(changePolicy.toString());
        policy.setRefundPolicy(refundPolicy.toString());
        return policy;
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
    private static List<FlightStopOver> parseFlightStopOver(List<DatedOperatingLeg> stops) {
        return stops.stream().map(leg -> {
            FlightStopOver flightStopOver = new FlightStopOver();
            flightStopOver.setStopCityCode(leg.getArrival().getIATALocationCode());
            return flightStopOver;
        }).collect(Collectors.toList());
    }
    
    public IATAOrderReshopRQ createIATAOrderReshopRQ(ChangeFlightQueryReq req) {
        final NdcFlightApiOrderRel orderRel = orderRelMapper.selectByOrderId(req.getOrderNumber());

        IATAOrderReshopRQ rq = new IATAOrderReshopRQ();
        rq.setRequest(createRequest(req, orderRel));
        return rq;
    }
    private Request createRequest(ChangeFlightQueryReq req, NdcFlightApiOrderRel orderRel) {
        final Request request = new Request();
        final BookingEntity bookingEntity = new BookingEntity();
        final Carrier carrier = new Carrier();
        carrier.setAirlineDesigCode("MU");
        bookingEntity.setCarrier(carrier);

        final List<BookingRef> bookingRefList = req.getTicketNumberList().stream().map(ticketNum -> {
            final BookingRef bookingRef = new BookingRef();

            bookingRef.setBookingID(ticketNum);
            bookingRef.setBookingRefTypeCode("5");
            bookingRef.setBookingEntity(bookingEntity);

            return bookingRef;
        }).collect(Collectors.toList());

        request.setBookingRef(bookingRefList);

        request.setOrderRefID(orderRel.getOrderItemId());

        request.setOrderActionContextText("8");

        request.setUpdateOrder(createUpdateOrder(req));

        return request;
    }
    private UpdateOrder createUpdateOrder(ChangeFlightQueryReq rq) {
        final UpdateOrder updateOrder = new UpdateOrder();

        final ReshopOrder reshopOrder = new ReshopOrder();
        final ServiceOrder serviceOrder = new ServiceOrder();
        final AddOfferItems addOfferItems = new AddOfferItems();
        final FlightCriteria flightCriteria = new FlightCriteria();
        final AffinityShoppingCriteria shoppingCriteria = new AffinityShoppingCriteria();

        AffinityOriginDest affinityOriginDest = new AffinityOriginDest();

        AffinityDepRequest depRequest = new AffinityDepRequest();
        depRequest.setDate(rq.getFlightDate());

        final Station dep = new Station();
        dep.setIATALocationCode(rq.getDepAirportCode());
        depRequest.setStation(dep);
        affinityOriginDest.setAffinityDepRequest(depRequest);

        AffinityArrivalRequest arrivalRequest = new AffinityArrivalRequest();
        Station dest = new Station();
        dest.setIATALocationCode(rq.getArrAirportCode());
        arrivalRequest.setStation(dest);
        affinityOriginDest.setAffinityArrivalRequest(arrivalRequest);

        affinityOriginDest.setConnectionPRefRefID("1");

        shoppingCriteria.setAffinityOriginDest(affinityOriginDest);

        flightCriteria.setAffinityShoppingCriteria(shoppingCriteria);

        addOfferItems.setFlightCriteria(flightCriteria);

        serviceOrder.setAddOfferItems(addOfferItems);

        reshopOrder.setServiceOrder(serviceOrder);

        updateOrder.setReshopOrder(reshopOrder);

        return updateOrder;
    }
}
