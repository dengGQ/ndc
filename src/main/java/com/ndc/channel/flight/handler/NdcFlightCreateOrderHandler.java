package com.ndc.channel.flight.handler;

import com.ndc.channel.enumtype.BusinessEnum;
import com.ndc.channel.exception.BusinessException;
import com.ndc.channel.exception.BusinessExceptionCode;
import com.ndc.channel.flight.dto.createOrder.*;
import com.ndc.channel.flight.dto.flightSearch.CorpApiFlightListDataV2;
import com.ndc.channel.flight.dto.flightSearch.CorpApiTicketData;
import com.ndc.channel.flight.tools.NdcApiTools;
import com.ndc.channel.flight.xmlBean.createOrder.request.bean.*;
import com.ndc.channel.flight.xmlBean.createOrder.request.bean.ContactInfo;
import com.ndc.channel.flight.xmlBean.createOrder.request.bean.DataLists;
import com.ndc.channel.flight.xmlBean.createOrder.request.bean.IdentityDoc;
import com.ndc.channel.flight.xmlBean.createOrder.request.bean.Individual;
import com.ndc.channel.flight.xmlBean.createOrder.request.bean.Pax;
import com.ndc.channel.flight.xmlBean.createOrder.request.bean.Phone;
import com.ndc.channel.flight.xmlBean.createOrder.response.bean.*;
import com.ndc.channel.mapper.NdcFlightApiOrderRelMapper;
import com.ndc.channel.redis.RedisKeyConstants;
import com.ndc.channel.redis.RedisUtils;
import com.ndc.channel.service.NdcFlightApiOrderRelService;
import com.ndc.channel.util.FlightKeyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Component
public class NdcFlightCreateOrderHandler {

    @Resource
    private NdcApiTools ndcApiTools;
    @Resource
    private RedisUtils redisUtils;
    @Resource
    private NdcFlightApiOrderRelService orderRelService;

    @Resource
    private NdcFlightApiOrderRelMapper orderRelMapper;

    public CorpApiFlightOrderCreateData createOrder(FlightOrderCreateReq orderCreateReq) {
        if (orderCreateReq.getTickets().size() > 1) {
            throw new BusinessException(BusinessExceptionCode.REQUEST_PARAM_ERROR, "仅支持单程");
        }
        if (orderCreateReq.getTickets().size() == 0) {
            throw new BusinessException(BusinessExceptionCode.REQUEST_PARAM_ERROR, "下单至少需要选择一个航班");
        }

        CorpApiFlightOrderCreateData orderCreateData = new CorpApiFlightOrderCreateData();

        final CorpApiOrderFlightTicketParams ticketParams = orderCreateReq.getTickets().get(0);
        final String ticketId = ticketParams.getTicketId();
        final String flightId = FlightKeyUtils.getFlightIdByTicketId(ticketId);

        CorpApiFlightListDataV2 flightData = redisUtils.get(RedisKeyConstants.getRedisFlightDataCacheKey(flightId), CorpApiFlightListDataV2.class);
        CorpApiTicketData ticketData = redisUtils.hGet(RedisKeyConstants.getRedisTicketDataCacheKey(flightId), ticketId, CorpApiTicketData.class);

        final Request request = getCreateOrderRequest(orderCreateReq, flightData, ticketData);

        final List<String> contactIdList = request.getDataLists().getContactInfoList().stream().filter(contactInfo -> !contactInfo.getContactTypeText().equals("PAX")).map(ContactInfo::getContactInfoID).collect(Collectors.toList());

        IATAOrderCreateRQ createRQ = new IATAOrderCreateRQ(contactIdList);
        createRQ.setRequest(request);

        final IATAOrderViewRS orderViewRS = ndcApiTools.createOrder(createRQ);

        com.ndc.channel.flight.xmlBean.createOrder.response.bean.Error error = orderViewRS.getError();
        if (error != null) {
            log.error("ndc创建订单失败，failReason={}", error.getError().getDescText());
            throw new BusinessException(BusinessExceptionCode.REQUEST_PARAM_ERROR, "NDC创建订单失败！");
        }

        final Response response = orderViewRS.getResponse();
        final Order order = response.getOrder();
        final TicketDocInfo ticketDocInfo = response.getTicketDocInfo();

        final OrderItem orderItem = order.getOrderItem();

        // 交易订单号
        String orderID = order.getOrderID();
        // 机票订单号
        final String orderItemID = orderItem.getOrderItemID();

//        String statusCode = orderItem.getStatusCode();
        String paymentTimeLimitDateTime = orderItem.getPaymentTimeLimitDateTime();
        TotalPrice totalPrice = order.getTotalPrice();
        TotalAmount totalAmount = totalPrice.getTotalAmount();

        final BaseAmount baseAmount = totalPrice.getBaseAmount();

        orderCreateData.setOrderNumber(orderID);
        orderCreateData.setOrderItemId(orderItemID);
        orderCreateData.setSettlementMoney(new BigDecimal(totalAmount.getValue()));
        orderCreateData.setTicketPrice(new BigDecimal(baseAmount.getValue()));
        orderCreateData.setTktl(paymentTimeLimitDateTime);
        orderCreateData.setOwnerCode(order.getOwnerCode());
        orderCreateData.setOwnerTypeCode(order.getOwnerTypeCode());

        String pnrCode = ticketDocInfo.getBookingRef().stream().filter(bf->bf.getBookingRefTypeCode().equals("6")).map(BookingRef::getBookingID).findFirst().orElse(null);
        orderCreateData.setPnrNo(pnrCode);
        orderCreateData.setRequestId(createRQ.getPayloadAttributes().getEchoTokenText());

        // NDC订单关键信息保存
        orderRelService.insertEntity(orderCreateReq, orderCreateData, ticketData);

        return orderCreateData;
    }

    private Request getCreateOrderRequest(FlightOrderCreateReq req, CorpApiFlightListDataV2 flightData, CorpApiTicketData ticketData) {
        Request request = new Request();

        DataLists dataLists = new DataLists();
        final List<ContactInfo> contactInfoList = getContactInfoList(req);
        dataLists.setContactInfoList(contactInfoList);

        String contactInfoID = contactInfoList.stream().filter(cf -> cf.getContactTypeText().equals("PAX")).map(ContactInfo::getContactInfoID).findFirst().orElse(null);
        final List<Pax> paxList = getPaxList(req, contactInfoID);
        dataLists.setPaxList(paxList);
        request.setDataLists(dataLists);


        CreateOrder createOrder = new CreateOrder();
        final List<String> paxIdList = paxList.stream().map(Pax::getPaxID).collect(Collectors.toList());

        SelectedOffer selectedOffer = getSelectedOffer(flightData, ticketData, paxIdList);

        createOrder.setSelectedOffer(Collections.singletonList(selectedOffer));
        request.setCreateOrder(createOrder);

        return request;
    }

    private List<ContactInfo> getContactInfoList(FlightOrderCreateReq req) {

        final String[] primaryContactStr = orderRelMapper.selectContact().split(",");

        CorpApiOrderPassengerParams passengerParams = req.getPassengers().get(0);

        return Stream.of("PRIMARY", "PAX", "TRAVEL_AGENCY").map(contactType->{
            final ContactInfo contactInfo = new ContactInfo();
            contactInfo.setContactInfoID(UUID.randomUUID().toString());
            contactInfo.setContactTypeText(contactType);
            final Phone phone = new Phone();
            phone.setPhoneNumber(passengerParams.getPhone());
            phone.setCountryDialingCode("86");
            contactInfo.setPhone(phone);

            if (contactType.equals("PRIMARY")) {
                final Individual individual = new Individual();
                individual.setSurname(passengerParams.getFlightPassengerName());
                contactInfo.setIndividual(individual);
            }

            if (contactType.equals("TRAVEL_AGENCY")) {
                contactInfo.getPhone().setPhoneNumber(primaryContactStr[1]);
            }
            return contactInfo;
        }).collect(Collectors.toList());
    }

    private List<Pax> getPaxList(FlightOrderCreateReq req,  String contactInfoID) {
        List<Pax> paxList = req.getPassengers().stream().map(passenger -> {
            final Pax pax = new Pax();

            pax.setPaxID("DAT"+passenger.getIdcardCode());
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

            pax.setContactInfoRefID(contactInfoID);
            pax.setPTC("ADT");
            return pax;
        }).collect(Collectors.toList());

        return paxList;
    }

    private SelectedOffer getSelectedOffer(CorpApiFlightListDataV2 flightData, CorpApiTicketData ticketData, List<String> paxIdList) {

        final SelectedOffer selectedOffer = new SelectedOffer();

        final SelectedOfferItem selectedOfferItem = new SelectedOfferItem();
        selectedOfferItem.setOfferItemRefID(ticketData.getOfferItemId());
        selectedOfferItem.setPaxRefID(paxIdList);

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
