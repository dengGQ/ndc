package com.ndc.channel.flight.handler;

import com.ndc.channel.entity.NdcFlightApiOrderRel;
import com.ndc.channel.enumtype.BusinessEnum;
import com.ndc.channel.exception.BusinessException;
import com.ndc.channel.exception.BusinessExceptionCode;
import com.ndc.channel.flight.dto.changeBooking.ChangeBookingReqParams;
import com.ndc.channel.flight.dto.createOrder.CorpApiOrderPassengerParams;
import com.ndc.channel.flight.dto.createOrder.OrderContactParams;
import com.ndc.channel.flight.dto.flightSearch.CorpApiFlightListDataV2;
import com.ndc.channel.flight.dto.flightSearch.CorpApiTicketData;
import com.ndc.channel.flight.tools.NdcApiTools;
import com.ndc.channel.flight.xmlBean.changeBooking.request.bean.*;
import com.ndc.channel.flight.xmlBean.changeBooking.response.bean.Error;
import com.ndc.channel.flight.xmlBean.changeBooking.response.bean.IATAOrderViewRS;
import com.ndc.channel.flight.xmlBean.changeBooking.response.bean.Response;
import com.ndc.channel.mapper.NdcFlightApiOrderRelMapper;
import com.ndc.channel.redis.RedisKeyConstants;
import com.ndc.channel.redis.RedisUtils;
import com.ndc.channel.service.NdcFlightApiChangeOrderRelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Component
public class NdcFlightChangeBooingHandler {

    @Resource
    private NdcApiTools ndcApiTools;
    @Resource
    private RedisUtils redisUtils;
    @Resource
    private NdcFlightApiOrderRelMapper orderRelMapper;

    @Resource
    private NdcFlightApiChangeOrderRelService changeOrderRelService;

    public String changeBooking(ChangeBookingReqParams params) {

        final NdcFlightApiOrderRel orderRel = orderRelMapper.selectByOrderId(params.getOrderNumber());

        CorpApiFlightListDataV2 flightData = redisUtils.get(RedisKeyConstants.getRedisFlightChangeDataCacheKey(params.getFlightId()), CorpApiFlightListDataV2.class);

        CorpApiTicketData ticketData = redisUtils.hGet(RedisKeyConstants.getRedisTicketChangeDataCacheKey(params.getFlightId()), params.getTicketId(), CorpApiTicketData.class);

        final IATAOrderViewRS viewRS = ndcApiTools.changeBooking(createIATAOrderChangeRQ(orderRel, params, flightData, ticketData));

        final Error error = viewRS.getError();
        if (error != null) {
            throw new BusinessException(BusinessExceptionCode.REQUEST_PARAM_ERROR, error.getError().getDescText());
        }

        Response response = viewRS.getResponse();
        final com.ndc.channel.flight.xmlBean.changeBooking.response.bean.Order order = response.getOrder();
        final String changeId = order.getOrderID();

        changeOrderRelService.insertEntity(params, params.getOrderNumber(), order, ticketData.getUpgradeTotalAmount());

        return changeId;
    }

    private IATAOrderChangeRQ createIATAOrderChangeRQ(NdcFlightApiOrderRel orderRel, ChangeBookingReqParams params, CorpApiFlightListDataV2 flightData, CorpApiTicketData ticketData) {

        final Request request = new Request();

        request.setDataLists(createDataLists(params, flightData, ticketData));
        request.setChangeOrder(createChangeOrder(flightData, ticketData, request.getDataLists().getPaxList()));
        request.setOrder(createOrder(orderRel));

        List<String> contactIdList = request.getDataLists().getContactInfoList().stream().map(ContactInfo::getContactInfoID).collect(Collectors.toList());
        IATAOrderChangeRQ rs = new IATAOrderChangeRQ(contactIdList);
        rs.setRequest(request);
        return rs;
    }

    private DataLists createDataLists(ChangeBookingReqParams params, CorpApiFlightListDataV2 flightData, CorpApiTicketData ticketData) {
        final DataLists dataLists = new DataLists();

        dataLists.setContactInfoList(createContactInfoList(params.getPassengers()));

        final ContactInfo paxContactInfo = dataLists.getContactInfoList().stream().filter(cf -> cf.getContactTypeText().equals("PAX")).findFirst().get();

        dataLists.setPaxList(createPaxList(params.getPassengers(), paxContactInfo));
        dataLists.setPaxSegmentList(createPaxSegmentList(flightData));
        dataLists.setServiceDefinitionList(createServiceDefinitionList(ticketData));

        return dataLists;
    }

    private List<Pax> createPaxList(List<CorpApiOrderPassengerParams> passengerParamsList, ContactInfo paxContactInfo) {

        List<Pax> paxList = passengerParamsList.stream().map(passenger -> {
            final Pax pax = new Pax();

            pax.setPaxID("DAT"+passenger.getIdcardCode());

            IdentityDoc identityDoc = new IdentityDoc();
            identityDoc.setIdentityDocID(passenger.getIdcardCode());
            identityDoc.setIdentityDocTypeCode(BusinessEnum.IdentityDocTypeCode.getIdentityDocTypeCode(passenger.getIdcardType()));
            pax.setIdentityDoc(identityDoc);

            pax.setContactInfoRefID(paxContactInfo.getContactInfoID());
            pax.setPTC("ADT");
            return pax;
        }).collect(Collectors.toList());

        return paxList;
    }

    private List<ContactInfo> createContactInfoList(List<CorpApiOrderPassengerParams> passengerParamsList){
        final String[] primaryContactStr = orderRelMapper.selectContact().split(",");

        CorpApiOrderPassengerParams passengerParams = passengerParamsList.get(0);

        final OrderContactParams primaryContact = new OrderContactParams();
        primaryContact.setName(primaryContactStr[0]);
        primaryContact.setPhone(primaryContactStr[1]);
        primaryContact.setContactType("PRIMARY");

        final OrderContactParams passengerContact = new OrderContactParams();
        passengerContact.setPhone(passengerParams.getPhone());
        passengerContact.setName(passengerParams.getFlightPassengerName());
        passengerContact.setContactType("PAX");

        final OrderContactParams travelAgentOrderContact= new OrderContactParams();
        travelAgentOrderContact.setName(primaryContactStr[0]);
        travelAgentOrderContact.setPhone(primaryContactStr[1]);
        travelAgentOrderContact.setContactType("TRAVEL_AGENCY");

        final List<ContactInfo> contactInfoList = Arrays.asList(primaryContact, passengerContact, travelAgentOrderContact).stream().map(contactParams -> {
            final ContactInfo contactInfo = new ContactInfo();
            contactInfo.setContactInfoID(UUID.randomUUID().toString());
            contactInfo.setContactTypeText(contactParams.getContactType());

            if (contactParams.getContactType().equals("PRIMARY")) {
                final Individual individual = new Individual();
                individual.setSurname(contactParams.getName());
                contactInfo.setIndividual(individual);
            }

            final Phone phone = new Phone();
            phone.setPhoneNumber(contactParams.getPhone());
            phone.setCountryDialingCode("86");
            contactInfo.setPhone(phone);

            return contactInfo;
        }).collect(Collectors.toList());

        return contactInfoList;
    }
    private List<ServiceDefinition> createServiceDefinitionList(CorpApiTicketData ticketData){

        final List<ServiceDefinition> serviceDefinitionList = ticketData.getServiceDefinitionList().stream().map(serviceDefinition -> {

            ServiceDefinition sd = new ServiceDefinition();
            sd.setServiceCode(serviceDefinition.getServiceCode());
            sd.setServiceDefinitionID(serviceDefinition.getServiceDefinitionID());
            sd.setName(serviceDefinition.getName());

            return sd;
        }).collect(Collectors.toList());

        return serviceDefinitionList;
    }
    private List<PaxSegment> createPaxSegmentList(CorpApiFlightListDataV2 flightData){

        PaxSegment paxSegment = new PaxSegment();
        paxSegment.setPaxSegmentID(flightData.getPaxSegmentID());

        final Dep dep = new Dep();
        dep.setAircraftScheduledDateTime(flightData.getFlightDate());
        dep.setIATALocationCode(flightData.getDepartureAirportCode());
        dep.setTerminalName(flightData.getDestinationTerminal());
        paxSegment.setDep(dep);

        final Arrival arrival = new Arrival();
        arrival.setAircraftScheduledDateTime(flightData.getFlightDate());
        arrival.setIATALocationCode(flightData.getDestinationAirportCode());
        arrival.setTerminalName(flightData.getDestinationTerminal());
        paxSegment.setArrival(arrival);


        final MarketingCarrierInfo marketingCarrierInfo = new MarketingCarrierInfo();
        marketingCarrierInfo.setCarrierDesigCode(flightData.getAirlineCode());
        marketingCarrierInfo.setMarketingCarrierFlightNumberText(flightData.getFlightNumber().substring(2));

        paxSegment.setMarketingCarrierInfo(marketingCarrierInfo);

        OperatingCarrierInfo operatingCarrierInfo = new OperatingCarrierInfo();
        operatingCarrierInfo.setCarrierDesigCode(flightData.getMainAirlineCode());
        operatingCarrierInfo.setOperatingCarrierFlightNumberText(flightData.getFlightNumber().substring(2));
        paxSegment.setOperatingCarrierInfo(operatingCarrierInfo);

        final DatedOperatingLeg datedOperatingLeg = new DatedOperatingLeg();
        datedOperatingLeg.setDatedOperatingLegID("1");
        datedOperatingLeg.setArrival(arrival);
        datedOperatingLeg.setDep(dep);
        paxSegment.setDatedOperatingLeg(datedOperatingLeg);


        return Arrays.asList(paxSegment);
    }

    private ChangeOrder createChangeOrder(CorpApiFlightListDataV2 flightData, CorpApiTicketData ticketData, List<Pax> paxList) {
        ChangeOrder changeOrder = new ChangeOrder();
        UpdateOrderItem updateOrderItem = new UpdateOrderItem();
        AcceptOffer acceptOffer = new AcceptOffer();
        SelectedOffer selectedOffer = new SelectedOffer();

        selectedOffer.setOwnerCode(flightData.getOwnerCode());
        selectedOffer.setShoppingResponseRefID(flightData.getShoppingResponseID());
        selectedOffer.setOfferRefID(flightData.getOfferId());
        selectedOffer.setTotalOfferPriceAmount(ticketData.getUpgradeTotalAmount().toPlainString());

        final SelectedOfferItem offerItem = new SelectedOfferItem();

        SelectedALaCarteOfferItem carteOfferItem = new SelectedALaCarteOfferItem();
        carteOfferItem.setQty(null);
        FlightAssociations fa = new FlightAssociations();
        fa.setPaxSegmentRefID(null);
        carteOfferItem.setFlightAssociations(fa);

        SelectedBundleServices bundleServices = new SelectedBundleServices();
        bundleServices.setSelectedServiceRefID(null);

        offerItem.setSelectedBundleServices(bundleServices);
        offerItem.setSelectedALaCarteOfferItem(carteOfferItem);
        offerItem.setOfferItemRefID(ticketData.getOfferItemId());
        offerItem.setPaxRefID(paxList.stream().map(Pax::getPaxID).collect(Collectors.toList()));

        selectedOffer.setSelectedOfferItem(offerItem);

        acceptOffer.setSelectedOffer(selectedOffer);

        updateOrderItem.setAcceptOffer(acceptOffer);
        changeOrder.setUpdateOrderItem(updateOrderItem);

        return changeOrder;
    }

    private Order createOrder(NdcFlightApiOrderRel orderRel) {
        Order order = new Order();

        order.setOrderID(orderRel.getOrderId());
        order.setOwnerCode(orderRel.getOwnerCode());
        order.setOwnerTypeCode(order.getOwnerTypeCode());

        return order;
    }
}
