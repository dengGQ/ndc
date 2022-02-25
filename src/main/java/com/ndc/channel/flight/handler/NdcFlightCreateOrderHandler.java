package com.ndc.channel.flight.handler;

import com.ndc.channel.enumtype.BusinessEnum;
import com.ndc.channel.flight.dto.createOrder.CorpApiFlightOrderCreateData;
import com.ndc.channel.flight.dto.createOrder.CorpApiOrderFlightTicketParams;
import com.ndc.channel.flight.dto.createOrder.FlightOrderCreateReq;
import com.ndc.channel.flight.dto.createOrder.OrderContactParams;
import com.ndc.channel.flight.dto.flightSearch.CorpApiFlightListDataV2;
import com.ndc.channel.flight.dto.flightSearch.CorpApiTicketData;
import com.ndc.channel.flight.tools.NdcApiTools;
import com.ndc.channel.flight.xmlBean.createOrder.request.bean.*;
import com.ndc.channel.flight.xmlBean.createOrder.response.bean.IATAOrderViewRS;
import com.ndc.channel.flight.xmlBean.createOrder.response.bean.Order;
import com.ndc.channel.flight.xmlBean.createOrder.response.bean.OrderItem;
import com.ndc.channel.flight.xmlBean.createOrder.response.bean.TotalPrice;
import com.ndc.channel.redis.RedisKeyConstants;
import com.ndc.channel.redis.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Component
public class NdcFlightCreateOrderHandler {

    @Resource
    private NdcApiTools ndcApiTools;

    @Resource
    private RedisUtils redisUtils;

    public CorpApiFlightOrderCreateData createOrder(FlightOrderCreateReq orderCreateReq) {
        CorpApiFlightOrderCreateData orderCreateData = new CorpApiFlightOrderCreateData();

        IATAOrderCreateRQ createRQ = new IATAOrderCreateRQ();
        createRQ.setRequest(getCreateOrderRequest(orderCreateReq));

        final IATAOrderViewRS orderViewRS = ndcApiTools.createOrder(createRQ);

        final Order order = orderViewRS.getResponse().getOrder();
        final OrderItem orderItem = order.getOrderItem();

        // 交易订单号
        String orderID = order.getOrderID();
        // 机票订单号
        final String orderItemID = orderItem.getOrderItemID();

        String statusCode = orderItem.getStatusCode();
        String paymentTimeLimitDateTime = orderItem.getPaymentTimeLimitDateTime();
        TotalPrice totalPrice = order.getTotalPrice();

        orderCreateData.setGroupOdrerNumber(orderID);
        orderCreateData.setOrderNumber(orderItemID);

        return orderCreateData;
    }

    private Request getCreateOrderRequest(FlightOrderCreateReq req) {
        Request request = new Request();
        CreateOrder createOrder = new CreateOrder();

        List<SelectedOffer> selectedOfferList = req.getTickets().stream().map(ticketParams -> {
            String flightId = ticketParams.getFlightId();
            String ticketId = ticketParams.getTicketId();

            CorpApiFlightListDataV2 flightData = redisUtils.get(RedisKeyConstants.getRedisFlightDataCacheKey(flightId), CorpApiFlightListDataV2.class);
            CorpApiTicketData ticketData = redisUtils.hGet(RedisKeyConstants.getRedisTicketDataCacheKey(flightId), ticketId, CorpApiTicketData.class);

            SelectedOffer selectedOffer = getSelectedOffer(flightData, ticketData);

            return selectedOffer;
        }).collect(Collectors.toList());

        createOrder.setSelectedOffer(selectedOfferList);

        request.setCreateOrder(createOrder);

        DataLists dataLists = new DataLists();

        final List<ContactInfo> contactInfoList = getContactInfoList(req);
        dataLists.setContactInfoList(contactInfoList);
        dataLists.setPaxList(getPaxList(req, contactInfoList.get(0)));

        request.setDataLists(dataLists);

        return request;
    }

    private List<ContactInfo> getContactInfoList(FlightOrderCreateReq req) {

        final List<ContactInfo> contactInfoList = req.getContacts().stream().map(contactParams -> {
            final ContactInfo contactInfo = new ContactInfo();
            contactInfo.setContactInfoID(UUID.randomUUID().toString());

            final Individual individual = new Individual();

            individual.setSurname(contactParams.getName());
            individual.setGenderCode(null);
            individual.setIndividualID(null);
            contactInfo.setIndividual(individual);

            contactInfo.setEmailAddress(null);
            contactInfo.setContactTypeText("TRAVEL_AGENCY");

            final Phone phone = new Phone();
            phone.setPhoneNumber(contactParams.getPhone());
            phone.setCountryDialingCode("86");
            contactInfo.setPhone(phone);

            EmailAddress emailAddress = new EmailAddress();
            emailAddress.setEmailAddressText("565820745@qq.com");
            contactInfo.setEmailAddress(emailAddress);

            return contactInfo;
        }).collect(Collectors.toList());

        return contactInfoList;
    }

    private List<Pax> getPaxList(FlightOrderCreateReq req,  ContactInfo contactInfo) {
        List<Pax> paxList = req.getPassengers().stream().map(passenger -> {
            final Pax pax = new Pax();

            pax.setPaxID(passenger.getIdcardCode());
            pax.setCitizenshipCountryCode("CN");

            final Individual individual = new Individual();
            individual.setIndividualID(UUID.randomUUID().toString());
            individual.setGenderCode(BusinessEnum.GenderCode.getGenderCode(passenger.getSex()));
            individual.setSurname(passenger.getFlightPassengerName());
            pax.setIndividual(individual);

            final IdentityDoc identityDoc = new IdentityDoc();
            identityDoc.setIdentityDocID(passenger.getIdcardCode());
            identityDoc.setIdentityDocTypeCode(BusinessEnum.IdentityDocTypeCode.getIdentityDocTypeCode(passenger.getIdcardType()));
            identityDoc.setBirthdate(passenger.getBirthday());
            pax.setIdentityDoc(identityDoc);

            pax.setContactInfoRefID(contactInfo.getContactInfoID());
            pax.setPTC("ADT");
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
