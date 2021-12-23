package com.ndc.channel.flight.handler;

import com.ndc.channel.flight.dto.CorpApiFlightListDataV2;
import com.ndc.channel.flight.dto.CorpApiTicketData;
import com.ndc.channel.flight.dto.FlightStopOver;
import com.ndc.channel.flight.tools.DateTimeUtils;
import com.ndc.channel.flight.tools.NdcApiTools;
import com.ndc.channel.flight.xmlBean.flightSearch.request.bean.*;
import com.ndc.channel.flight.xmlBean.flightSearch.response.bean.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class NdcFlightSearchHandler {

    @Resource
    private NdcApiTools apiTools = new NdcApiTools();

    public List<CorpApiFlightListDataV2> flightSearch(String flightDate, String depCityCode, String destCityCode) {
        IATAAirShoppingRQ flightSearchParams = getFlightSearchParams(flightDate, depCityCode, destCityCode);

        IATAAirShoppingRS iataAirShoppingRS = apiTools.flightSearch(flightSearchParams);

        return convertFlightData(iataAirShoppingRS, flightDate, depCityCode, destCityCode);
    }

    private List<CorpApiFlightListDataV2> convertFlightData(IATAAirShoppingRS ndcResp, String flightDate, String depCityCode, String destCityCode) {

        Response response = ndcResp.getResponse();

        DataLists dataLists = response.getDataLists();
        List<String> paxJourneyRefID = dataLists.getOriginDestList().getOriginDest().get(0).getPaxJourneyRefID();

        List<PaxJourney> paxJourneyList = dataLists.getPaxJourneyList().getPaxJourney();
        List<PaxSegment> paxSegmentList = dataLists.getPaxSegmentList().getPaxSegment();

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
            return of.getPtcOfferParameters().get(0).getPTCPricedCode().equals("ADT");
        }).findFirst().orElse(null);

        List<OfferItem> offerItem = offer.getOfferItem();

        for (OfferItem item : offerItem) {
            CorpApiTicketData corpApiTicketData = convertFromCarrierOffer(item);
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

    private CorpApiTicketData convertFromCarrierOffer(OfferItem offerItem) {
        final CorpApiTicketData ticketData = new CorpApiTicketData();

        final String offerItemID = offerItem.getOfferItemID();
        offerItem.getFareDetail();

        return ticketData;
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
        AffinityOriginDest affinityOriginDest1 = getAffinityOriginDest("2022-03-15", destCityCode, depCityCode);
        affinityOriginDest1.setConnectionPRefRefID("-1");

        affinityShoppingCriteria.setAffinityOriginDest(Arrays.asList(affinityOriginDest, affinityOriginDest1));

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
