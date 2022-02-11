package com.ndc.channel.flight.handler;

import com.alibaba.fastjson.JSONObject;
import com.ndc.channel.enumtype.BusinessEnum;
import com.ndc.channel.exception.BusinessException;
import com.ndc.channel.exception.BusinessExceptionCode;
import com.ndc.channel.flight.dto.CorpApiFlightListDataV2;
import com.ndc.channel.flight.dto.CorpApiTicketData;
import com.ndc.channel.flight.dto.CorpApiTicketPolicy;
import com.ndc.channel.flight.dto.FlightStopOver;
import com.ndc.channel.flight.tools.DateTimeUtils;
import com.ndc.channel.flight.tools.NdcApiTools;
import com.ndc.channel.flight.xmlBean.flightSearch.common.Error;
import com.ndc.channel.flight.xmlBean.flightSearch.request.bean.*;
import com.ndc.channel.flight.xmlBean.flightSearch.response.RemarkText;
import com.ndc.channel.flight.xmlBean.flightSearch.response.bean.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Component
public class NdcFlightSearchHandler {

    @Resource
    private NdcApiTools apiTools = new NdcApiTools();

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

        String everyFlightDateKey=flightDate+depCityCode+destCityCode;

        DataLists dataLists = response.getDataLists();
        List<String> paxJourneyRefID = dataLists.getOriginDestList().getOriginDest().get(0).getPaxJourneyRefID();

        List<PaxJourney> paxJourneyList = dataLists.getPaxJourneyList().getPaxJourney();
        List<PaxSegment> paxSegmentList = dataLists.getPaxSegmentList().getPaxSegment();
        Pax pax = dataLists.getPaxList().getPax().stream().filter(p -> p.getPTC().equals("ADT")).findFirst().orElse(null);
        if (pax == null) {
            log.error("NDC航班未查到成人航班, everyFlightDateKey=", everyFlightDateKey);
            throw new BusinessException(BusinessExceptionCode.REQUEST_PARAM_ERROR, "NDC航班未查到成人航班");
        }

        List<ServiceDefinition> serviceDefinition = dataLists.getServiceDefinitionList().getServiceDefinition();

        List<String> paxSegmentIdList = paxJourneyList.stream().filter(paxJourney -> {
            return paxJourneyRefID.contains(paxJourney.getPaxJourneyID());
        }).flatMap(paxJourney -> paxJourney.getPaxSegmentRefID().stream()).collect(Collectors.toList());

        List<CorpApiFlightListDataV2> flightDataList = new ArrayList<>();
        for (PaxSegment paxSegment : paxSegmentList) {
            if (paxSegmentIdList.contains(paxSegment.getPaxSegmentID())) {
                CorpApiFlightListDataV2 corpApiFlightListDataV2 = convertFromPaxSegment(paxSegment, flightDate, depCityCode, destCityCode);
                flightDataList.add(corpApiFlightListDataV2);
            }
        }

        List<CarrierOffers> carrierOffers = response.getOffersGroup().getCarrierOffers();
        Offer offer = carrierOffers.get(0).getOffer().stream().filter(of -> {
            return of.getPtcOfferParameters().get(0).getPTCPricedCode().equals(pax.getPTC());
        }).findFirst().orElse(null);

        Map<String, List<CorpApiTicketData>> offerItemMapping = new HashMap<>();
        for (OfferItem item : offer.getOfferItem()) {

            FareDetail fareDetail = item.getFareDetail().get(0);

            FareComponent fareComponent = fareDetail.getFareComponent().get(0);
            List<String> paxSegmentRefID = fareComponent.getPaxSegmentRefID();

            List<Service> service = item.getService();

            Map<String, List<String>> serviceDefinitionRefIDMapping = Optional.ofNullable(service).orElseGet(Arrays::asList).stream().collect(Collectors.groupingBy(ser -> {
                return ser.getPaxRefID() + ser.getServiceAssociations().getServiceDefinitionRef().getPaxSegmentRefID();
            }, Collectors.mapping(ser -> ser.getServiceAssociations().getServiceDefinitionRef().getServiceDefinitionRefID(), Collectors.toList())));

            for (String paxSegmentId : paxSegmentRefID) {
                List<CorpApiTicketData> offerItems = offerItemMapping.get(paxSegmentId);

                if (CollectionUtils.isEmpty(offerItems)) {
                    offerItems = new ArrayList<>();
                    offerItemMapping.put(paxSegmentId, offerItems);
                }

                final List<String> serviceDefinitionIdList = serviceDefinitionRefIDMapping.get(pax.getPaxID() + paxSegmentId);

                List<ServiceDefinition> serviceDefinitionList = serviceDefinition.stream().filter(sd -> serviceDefinitionIdList.contains(sd.getServiceDefinitionID())).collect(Collectors.toList());

                offerItems.add(convertFromCarrierOffer(paxSegmentId, item, serviceDefinitionList));
            }
        }

        Iterator<CorpApiFlightListDataV2> iterator = flightDataList.iterator();

        while (iterator.hasNext()) {
            CorpApiFlightListDataV2 v2 = iterator.next();
            List<CorpApiTicketData> corpApiTicketData = offerItemMapping.get(v2.getFlightId());

            if (CollectionUtils.isEmpty(corpApiTicketData)) {
                iterator.remove();
                continue;
            }
            v2.setTickets(corpApiTicketData);
        }

        return flightDataList;
    }

    private CorpApiFlightListDataV2 convertFromPaxSegment(PaxSegment paxSegment, String flightDate, String depCityCode, String destCityCode) {

        CorpApiFlightListDataV2 corpApiFlight = new CorpApiFlightListDataV2();

        MarketingCarrierInfo marketingCarrierInfo = paxSegment.getMarketingCarrierInfo();
        OperatingCarrierInfo operatingCarrierInfo = paxSegment.getOperatingCarrierInfo();
        Dep dep = paxSegment.getDep();
        Arrival arrival = paxSegment.getArrival();
        String duration = paxSegment.getDuration();

        Map<String, List<DatedOperatingLeg>> legMapping = paxSegment.getDatedOperatingLeg().stream().collect(Collectors.groupingBy(leg -> StringUtils.isEmpty(leg.getOnGroundDuration()) ? "0" : "1"));
        CarrierAircraftType carrierAircraftType = legMapping.get("0").get(0).getCarrierAircraftType();

        String flightNumber = marketingCarrierInfo.getCarrierDesigCode() + marketingCarrierInfo.getMarketingCarrierFlightNumberText();

        String operationCarrierFlightNumber = operatingCarrierInfo.getCarrierDesigCode() + operatingCarrierInfo.getOperatingCarrierFlightNumberText();

        corpApiFlight.setFlightId(paxSegment.getPaxSegmentID());
        corpApiFlight.setFlightDate(flightDate);
        corpApiFlight.setFlightNumber(flightNumber);
        corpApiFlight.setAirlineCode(marketingCarrierInfo.getCarrierDesigCode());
        corpApiFlight.setAirlineShortName("东方航空");

        corpApiFlight.setFlyingTime(duration);

        corpApiFlight.setTpm(null);
        corpApiFlight.setPlaneType(carrierAircraftType.getCarrierAircraftTypeName());
        corpApiFlight.setMealType(null);

        String departureTime = DateTimeUtils.parseStringToString(dep.getAircraftScheduledDateTime(), DateTimeUtils.PATTEN_YYYY_MM_DDTHH_MM_SS_SSS_XXX, DateTimeUtils.PATTEN_HH_MM);
        corpApiFlight.setDepartureTime(departureTime);
        corpApiFlight.setDepartureCityCode(depCityCode);
        corpApiFlight.setDepartureAirportCode(dep.getIATALocationCode());
        corpApiFlight.setDepartureAirport(dep.getStationName());
        corpApiFlight.setDepartureTerminal(dep.getTerminalName());

        String arriveTime = DateTimeUtils.parseStringToString(arrival.getAircraftScheduledDateTime(), DateTimeUtils.PATTEN_YYYY_MM_DDTHH_MM_SS_SSS_XXX, DateTimeUtils.PATTEN_HH_MM);
        corpApiFlight.setDestinationTime(arriveTime);
        corpApiFlight.setDestinationCityCode(destCityCode);
        corpApiFlight.setDestinationAirportCode(arrival.getIATALocationCode());
        corpApiFlight.setDestinationAirport(arrival.getStationName());
        corpApiFlight.setDestinationTerminal(arrival.getTerminalName());

        corpApiFlight.setIsStopover("0");
        List<DatedOperatingLeg> datedOperatingLegs = legMapping.get("1");
        if (CollectionUtils.isNotEmpty(datedOperatingLegs)) {
            corpApiFlight.setIsStopover("1");
            corpApiFlight.setStopoverInfo(parseFlightStopOver(datedOperatingLegs));
        }

        if (flightNumber.equals(operationCarrierFlightNumber)) {

            corpApiFlight.setIsShare("0");
        } else {
            //共享航班
            corpApiFlight.setIsShare("1");
            corpApiFlight.setMainAirlineCode(operatingCarrierInfo.getCarrierDesigCode());

            corpApiFlight.setMainFlightNumber(operationCarrierFlightNumber);
            corpApiFlight.setMainAirlineShortName(null);
        }

        return corpApiFlight;
    }

    private CorpApiTicketData convertFromCarrierOffer(String paxSegmentId, OfferItem offerItem, List<ServiceDefinition> serviceDefinitionList) {
        CorpApiTicketData ticketData = new CorpApiTicketData();

        final String offerItemID = offerItem.getOfferItemID();

        final ServiceDefinition seatSaleSd = serviceDefinitionList.stream().filter(sd -> sd.getName().equals("SEAT_SALE")).findFirst().orElse(null);

        final FareDetail fareDetail = offerItem.getFareDetail().get(0);
        final FareComponent fareComponent = fareDetail.getFareComponent().get(0);


        final String seatClassCode = fareComponent.getRbd().getRBDCode();
        final CabinType cabinType = fareComponent.getCabinType();
        final Price price = fareComponent.getPrice();
        final Discount discount = price.getDiscount();
        final BaseAmount baseAmount = price.getBaseAmount();
        final List<TaxSummary> taxSummary = price.getTaxSummary();

        ticketData.setSeatClassCode(seatClassCode);
        ticketData.setFlightId(paxSegmentId);
        ticketData.setTicketId(offerItemID);
        ticketData.setMainClassCode(cabinType.getCabinTypeCode());
        ticketData.setMainClassName(cabinType.getCabinTypeName());
        ticketData.setIsWebSite("1");
        ticketData.setSeatClassName(ticketData.getMainClassName());
        ticketData.setProductType(BusinessEnum.ProductType.WEBSITE.getCode());
        ticketData.setQuantity(seatSaleSd.getServiceDefinitionAssociation().getServiceBundle().getMaximumServiceQty());
        ticketData.setTicketPrice(new BigDecimal(baseAmount.getValue()));
        ticketData.setPrice(ticketData.getTicketPrice());
        ticketData.setPurchasePrice(ticketData.getTicketPrice());
//        ticketData.setDiscount(ticketData.getTicketPrice().divide(ticketData.getFdPrice(),2, RoundingMode.HALF_UP));

        List<FareRule> fareRule = fareComponent.getFareRule();
        ticketData.setPolicy(parseTicketPolicy(fareRule));

        return ticketData;
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

            Remark remark = rule.getRemark().get(0);

            RemarkText remarkText = JSONObject.parseObject(remark.getRemarkText(), RemarkText.class);
            final String useFlag = remarkText.getUseFlag(); // 0使用前 1使用后
            if (useFlag.equals("1")){
                continue;
            }

            final String timeFlag = remarkText.getTimeFlag(); //0 航前 1 航后

            final String maxTimeUnit = remarkText.getMaxTimeUnit(); //单位，D(天),N(分钟),H(小时),M(月)
            final String maxTimeFlag = remarkText.getMaxTimeFlag(); //是否包含,0不包含1包含
            final String maxTime = remarkText.getMaxTime(); //时间

            final String minTimeUnit = remarkText.getMinTimeUnit();
            final String minTimeFlag = remarkText.getMinTimeFlag();
            final String minTime = remarkText.getMinTime();


            for (Penalty penalty : rule.getPenalty()) {
                final String penaltyPercent = penalty.getPenaltyPercent();

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

    private static List<FlightStopOver> parseFlightStopOver(List<DatedOperatingLeg> stops) {
        return stops.stream().map(leg -> {
            FlightStopOver flightStopOver = new FlightStopOver();
            flightStopOver.setStopCityCode(leg.getArrival().getIATALocationCode());
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
//        AffinityOriginDest affinityOriginDest1 = getAffinityOriginDest("2022-03-15", destCityCode, depCityCode);
//        affinityOriginDest1.setConnectionPRefRefID("-1");

        affinityShoppingCriteria.setAffinityOriginDest(Arrays.asList(affinityOriginDest));

        flightCriteria.setAffinityShoppingCriteria(affinityShoppingCriteria);

        request.setFlightCriteria(flightCriteria);

        iataAirShoppingRQ.setRequest(request);
        return iataAirShoppingRQ;
    }

    private static AffinityOriginDest getAffinityOriginDest(String flightDate, String depCityCode, String destCityCode) {
        AffinityOriginDest affinityOriginDest = new AffinityOriginDest();

        AffinityDepRequest affinityDepRequest = new AffinityDepRequest();

        CountrySubDivision countrySubDivisionDep = new CountrySubDivision();
        countrySubDivisionDep.setCountrySubDivisionCode("");
        Station stationDep = new Station();
        stationDep.setIATALocationCode(depCityCode);
        affinityDepRequest.setStation(stationDep);
        affinityDepRequest.setCountrySubDivision(countrySubDivisionDep);
        affinityDepRequest.setDate(flightDate);
        affinityOriginDest.setAffinityDepRequest(affinityDepRequest);

        AffinityArrivalRequest affinityArrivalRequest = new AffinityArrivalRequest();
        CountrySubDivision countrySubDivision = new CountrySubDivision();
        countrySubDivision.setCountrySubDivisionCode("");
        Station station = new Station();
        station.setIATALocationCode(destCityCode);
        affinityArrivalRequest.setCountrySubDivision(countrySubDivision);
        affinityArrivalRequest.setStation(station);
        affinityOriginDest.setAffinityArrivalRequest(affinityArrivalRequest);

        affinityOriginDest.setConnectionPRefRefID("1");

        return affinityOriginDest;
    }

    public static void saveAsFileWriter(String content, String filePath) {
        FileWriter fwriter = null;
        try {
            // true表示不覆盖原来的内容，而是加到文件的后面。若要覆盖原来的内容，直接省略这个参数就好
            fwriter = new FileWriter(filePath, true);
            fwriter.write(content);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                fwriter.flush();
                fwriter.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
