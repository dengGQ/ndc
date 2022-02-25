package com.ndc.channel.flight.handler;

import com.ndc.channel.flight.dto.createOrder.CorpApiFlightOrderCreateData;
import com.ndc.channel.flight.dto.createOrder.CorpApiOrderFlightTicketParams;
import com.ndc.channel.flight.dto.createOrder.FlightOrderCreateReq;
import com.ndc.channel.flight.dto.createOrder.OrderContactParams;
import com.ndc.channel.flight.dto.flightSearch.CorpApiFlightListDataV2;
import com.ndc.channel.flight.dto.flightSearch.CorpApiTicketData;
import com.ndc.channel.flight.tools.NdcApiTools;
import com.ndc.channel.flight.xmlBean.createOrder.request.bean.*;
import com.ndc.channel.flight.xmlBean.createOrder.response.bean.IATAOrderViewRS;
import com.ndc.channel.redis.RedisKeyConstants;
import com.ndc.channel.redis.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Component
public class NdcFlightCreateOrderHandler {

    @Resource
    private NdcApiTools ndcApiTools;

    @Resource
    private RedisUtils redisUtils;

    public CorpApiFlightOrderCreateData createOrder(FlightOrderCreateReq orderCreateReq) {
        final CorpApiFlightOrderCreateData orderCreateData = new CorpApiFlightOrderCreateData();

        final IATAOrderCreateRQ createRQ = new IATAOrderCreateRQ();

        createRQ.setRequest(getCreateOrderRequest(orderCreateReq));

        final IATAOrderViewRS orderViewRS = ndcApiTools.createOrder(createRQ);

        return orderCreateData;
    }

    private Request getCreateOrderRequest(FlightOrderCreateReq req) {
        final Request request = new Request();
        final CreateOrder createOrder = new CreateOrder();

        final List<SelectedOffer> selectedOfferList = req.getTickets().stream().map(ticketParams -> {
            final String flightId = ticketParams.getFlightId();
            final String ticketId = ticketParams.getTicketId();

            CorpApiFlightListDataV2 flightData = redisUtils.get(RedisKeyConstants.getRedisFlightDataCacheKey(flightId), CorpApiFlightListDataV2.class);
            CorpApiTicketData ticketData = redisUtils.hGet(RedisKeyConstants.getRedisTicketDataCacheKey(flightId), ticketId, CorpApiTicketData.class);

            final SelectedOffer selectedOffer = getSelectedOffer(flightData, ticketData);

            return selectedOffer;
        }).collect(Collectors.toList());

        createOrder.setSelectedOffer(selectedOfferList);

        request.setCreateOrder(createOrder);

        final DataLists dataLists = new DataLists();

        dataLists.setContactInfoList(getContactInfoList(req));
        dataLists.setPaxList(getPaxList(req));

        request.setDataLists(dataLists);

        return request;
    }

    private List<ContactInfo> getContactInfoList(FlightOrderCreateReq req) {

        final List<ContactInfo> contactInfoList = req.getContacts().stream().map(contactParams -> {
            final ContactInfo contactInfo = new ContactInfo();
            contactInfo.setContactInfoID(null);

            final Individual individual = new Individual();

            individual.setSurname(contactParams.getName());
            individual.setGenderCode(null);
            individual.setIndividualID(null);
            contactInfo.setIndividual(individual);

            contactInfo.setEmailAddress(null);
            contactInfo.setContactTypeText(null);

            final Phone phone = new Phone();
            phone.setPhoneNumber(contactParams.getPhone());
            phone.setCountryDialingCode("86");
            contactInfo.setPhone(phone);

            return contactInfo;
        }).collect(Collectors.toList());

        return contactInfoList;
    }

    private List<Pax> getPaxList(FlightOrderCreateReq req) {
        final List<Pax> paxList = req.getPassengers().stream().map(passenger -> {
            final Pax pax = new Pax();

            pax.setPaxID(passenger.getIdcardCode());
            pax.setCitizenshipCountryCode("CN");

            final Individual individual = new Individual();
            individual.setIndividualID(UUID.randomUUID().toString());
            individual.setGenderCode(passenger.getSex());
            individual.setSurname(passenger.getFlightPassengerName());
            pax.setIndividual(individual);

            final IdentityDoc identityDoc = new IdentityDoc();
            identityDoc.setIdentityDocID(passenger.getIdcardCode());
            identityDoc.setIdentityDocTypeCode(passenger.getIdcardType());
            identityDoc.setBirthdate(passenger.getBirthday());
            pax.setIdentityDoc(identityDoc);

            pax.setContactInfoRefID(null);
            pax.setPTC(null);
            return pax;
        }).collect(Collectors.toList());

        return paxList;
    }

    private SelectedOffer getSelectedOffer(CorpApiFlightListDataV2 flightData, CorpApiTicketData ticketData) {

        final SelectedOffer selectedOffer = new SelectedOffer();

        final SelectedOfferItem selectedOfferItem = new SelectedOfferItem();
        selectedOfferItem.setOfferItemRefID(ticketData.getOfferItemId());
        selectedOfferItem.setPaxRefID(ticketData.getPaxId());

        final SelectedALaCarteOfferItem selectedALaCarteOfferItem = new SelectedALaCarteOfferItem();
        selectedALaCarteOfferItem.setQty(null);
        selectedOfferItem.setSelectedALaCarteOfferItem(selectedALaCarteOfferItem);

        selectedOffer.setSelectedOfferItem(selectedOfferItem);
        selectedOffer.setOfferRefID(flightData.getOfferId());
        selectedOffer.setOwnerCode(flightData.getAirlineCode());
        selectedOffer.setShoppingResponseRefID(flightData.getShoppingResponseID());

        return selectedOffer;
    }
}
