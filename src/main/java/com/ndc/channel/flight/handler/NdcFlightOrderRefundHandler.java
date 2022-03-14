package com.ndc.channel.flight.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ndc.channel.entity.NdcFlightApiOrderRel;
import com.ndc.channel.entity.NdcFlightApiRefundOrderRel;
import com.ndc.channel.enumtype.BusinessEnum;
import com.ndc.channel.exception.BusinessException;
import com.ndc.channel.exception.BusinessExceptionCode;
import com.ndc.channel.executor.OrderDetailDelayQueryExecutor;
import com.ndc.channel.flight.dto.MsgBody;
import com.ndc.channel.flight.dto.orderDetail.NdcOrderDetailData;
import com.ndc.channel.flight.dto.orderDetail.OrderTicketInfo;
import com.ndc.channel.flight.dto.refund.RefundApplyParams;
import com.ndc.channel.flight.dto.refund.RefundApplyPassengerParams;
import com.ndc.channel.flight.dto.refund.RefundChangeMoneyQueryParams;
import com.ndc.channel.flight.dto.refund.RefundChangeMoneyQueryResp;
import com.ndc.channel.flight.tools.NdcApiTools;
import com.ndc.channel.flight.xmlBean.refundAmountSearch.response.bean.refund.CarrierFee;
import com.ndc.channel.flight.xmlBean.refundApply.request.bean.IATAOrderRetrieveRQ;
import com.ndc.channel.flight.xmlBean.refundApply.response.bean.*;
import com.ndc.channel.flight.xmlBean.refundApply.response.bean.Error;
import com.ndc.channel.flight.xmlBean.refundAmountSearch.request.bean.BookingRef;
import com.ndc.channel.flight.xmlBean.refundAmountSearch.request.bean.IATAOrderChangeRQ;
import com.ndc.channel.flight.xmlBean.refundAmountSearch.request.bean.OrderChangeParameters;
import com.ndc.channel.flight.xmlBean.refundAmountSearch.request.bean.Request;
import com.ndc.channel.flight.xmlBean.refundAmountSearch.response.bean.refund.OrderAmendment;
import com.ndc.channel.flight.xmlBean.refundAmountSearch.response.bean.refund.PaymentInfo;
import com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.Media;
import com.ndc.channel.flight.xmlBean.refundOrderDetail.request.bean.Order;
import com.ndc.channel.mapper.NdcFlightApiOrderRelMapper;
import com.ndc.channel.mapper.NdcFlightApiRefundOrderRelMapper;
import com.ndc.channel.redis.RedisUtils;
import com.ndc.channel.service.NdcFlightApiRefundOrderRelService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
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
public class NdcFlightOrderRefundHandler {

    @Resource
    private NdcApiTools ndcApiTools;

    @Resource
    private NdcFlightApiOrderRelMapper apiOrderRelMapper;

    @Resource
    private OrderDetailDelayQueryExecutor delayQueryExecutor;

    @Resource
    private NdcFlightApiRefundOrderRelService refundOrderRelService;

    @Resource
    private RedisUtils redisUtils;

    /**
     * 退票单提交
     * @param params
     * @return
     */
    public String refundConfirm(RefundApplyParams params) {

        final NdcFlightApiOrderRel orderRel = apiOrderRelMapper.selectByOrderId(params.getOrderNumber());

        final List<String> ticketNumberList = params.getRefundPassengerList().stream().map(RefundApplyPassengerParams::getTicketNumber).collect(Collectors.toList());

        final RefundChangeMoneyQueryResp refundChangeMoneyQueryResp = JSONObject.parseObject(redisUtils.getString(getKey(orderRel.getOrderId(), ticketNumberList)), RefundChangeMoneyQueryResp.class);

        if (refundChangeMoneyQueryResp == null) {
            throw new BusinessException(BusinessExceptionCode.REQUEST_PARAM_ERROR, "您的操作耗时太久了，请刷新页面重试！");
        }

        final Response refundApplyResponse = refundApply(orderRel.getOrderId());

        com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.Request request = getRefundConfirmReqData(params, refundApplyResponse, orderRel);

        List<String> contactInfoId = request.getDataLists().getContactInfoList().stream().map(com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.ContactInfo::getContactInfoID).collect(Collectors.toList());
        final com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.IATAOrderChangeRQ rq = new com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.IATAOrderChangeRQ(contactInfoId);
        rq.setRequest(request);

        com.ndc.channel.flight.xmlBean.refundConfirm.response.bean.IATAOrderViewRS iataOrderViewRS = ndcApiTools.refundConfirm(rq);

        final com.ndc.channel.flight.xmlBean.refundConfirm.response.bean.Error error = iataOrderViewRS.getError();
        if (error != null) {
            throw new BusinessException(BusinessExceptionCode.FLIGHT_CHANNEL_ERROR, error.getDescText());
        }

        final com.ndc.channel.flight.xmlBean.refundConfirm.response.bean.Response refundConfirmResponse = iataOrderViewRS.getResponse();

        final com.ndc.channel.flight.xmlBean.refundConfirm.response.bean.Order order = refundConfirmResponse.getOrder();
        final String refundId = order.getOrderID();

        refundOrderRelService.insertEntity(params, orderRel.getOrderId(), refundId, refundChangeMoneyQueryResp);

        delayQueryExecutor.submitTask(JSON.toJSONString(new MsgBody(orderRel.getOrderId(), "2")), 60L);

        return refundId;
    }

    /**
     * 机票退票金额查询
     * @param params
     * @return
     */
    public RefundChangeMoneyQueryResp refundMoneyQuery(RefundChangeMoneyQueryParams params) {

        NdcFlightApiOrderRel ndcOrderRel = apiOrderRelMapper.selectByOrderId(params.getChannelOrderNumber());

        final Response refundApplyResp = refundApply(params.getChannelOrderNumber());

        final DataLists dataLists = refundApplyResp.getDataLists();

        final IATAOrderChangeRQ refundAmountQueryReqParams = createRefundAmountQueryReqParams(params, ndcOrderRel, dataLists);
        com.ndc.channel.flight.xmlBean.refundAmountSearch.response.bean.refund.IATAOrderViewRS refundAmountQueryResult = ndcApiTools.refundAmountQuery(refundAmountQueryReqParams);

        com.ndc.channel.flight.xmlBean.refundAmountSearch.response.bean.refund.Error error = refundAmountQueryResult.getError();
        if (error != null) {
            throw new BusinessException(BusinessExceptionCode.FLIGHT_CHANNEL_ERROR, error.getDescText());
        }

        RefundChangeMoneyQueryResp resp = new RefundChangeMoneyQueryResp();

        PaymentInfo paymentInfo = refundAmountQueryResult.getPaymentInfo();
        if (paymentInfo == null) {
            if (params.getQueryType() == 1) {
                resp.setChangeFee(BigDecimal.ZERO);
            }else {
                resp.setRefundFee(BigDecimal.ZERO);
            }
            return resp;
        }

        final com.ndc.channel.flight.xmlBean.refundAmountSearch.response.bean.refund.Response response = refundAmountQueryResult.getResponse();

        OrderAmendment orderAmendment = response.getOrderAmendment();
        final List<com.ndc.channel.flight.xmlBean.refundAmountSearch.response.bean.refund.TicketDocInfo> ticketDocInfoList = orderAmendment.getTicketDocInfo();

        resp = getRefundChangeMoneyQueryResp(params.getTicketNumList(), ticketDocInfoList);


        redisUtils.setStrExpire(getKey(ndcOrderRel.getOrderId(), params.getTicketNumList()), JSON.toJSONString(resp), 5, TimeUnit.MINUTES);

        return resp;
    }

    private static String getKey(String orderId, List<String> ticketNumber) {
        return new StringBuilder(orderId).append("-").append(ticketNumber.stream().sorted().collect(Collectors.joining(","))).toString();
    }

    /**
     * 机票退票申请
     * @param orderId
     * @return
     */
    private Response refundApply(String orderId) {

        NdcFlightApiOrderRel ndcFlightApiOrderRel = apiOrderRelMapper.selectByOrderId(orderId);

        IATAOrderRetrieveRQ iataOrderRetrieveRQ = new IATAOrderRetrieveRQ(orderId, ndcFlightApiOrderRel);

        IATAOrderViewRS refundApplyResult = ndcApiTools.refundApply(iataOrderRetrieveRQ);

        final Error error = refundApplyResult.getError();
        if (error != null) {
            throw new BusinessException(BusinessExceptionCode.FLIGHT_CHANNEL_ERROR, error.getDescText());
        }

        Response response = refundApplyResult.getResponse();

        Ticket ticket = response.getOrderAmendment().getTicketDocInfo().getTicket();
        Coupon coupon = ticket.getCoupon();
        //客票状态
        String couponStatusCode = coupon.getCouponStatusCode();

        // 不可退标识，true不可退，false可退
        Boolean nonRefundableInd = coupon.getNonRefundableInd();

        if (nonRefundableInd) {
            throw new BusinessException(BusinessExceptionCode.REQUEST_PARAM_ERROR, "不可退，客票状态（"+BusinessEnum.TicketStatus.getLabelByCode(couponStatusCode)+")");
        }
        return response;

    }

    private IATAOrderChangeRQ createRefundAmountQueryReqParams(RefundChangeMoneyQueryParams params, NdcFlightApiOrderRel ndcOrderRel, DataLists dataLists) {

        final List<Pax> paxList = dataLists.getPaxList();
        final List<String> contactInfoRefID = dataLists.getContactInfoList().stream().map(ContactInfo::getContactInfoID).collect(Collectors.toList());

        IATAOrderChangeRQ iataOrderChangeRQ = new IATAOrderChangeRQ(contactInfoRefID);
        Request request = new Request();

        request.setOrder(createOrder(params.getChannelOrderNumber(), ndcOrderRel));
        request.setBookingRef(createBookingRef(params.getChannelOrderNumber(), ndcOrderRel, paxList));
        request.setDataLists(createDateLists(dataLists));
        request.setOrderChangeParameters(createOrderChangeParameters(params.getRefundWay()));

        iataOrderChangeRQ.setRequest(request);

        return iataOrderChangeRQ;
    }

    private com.ndc.channel.flight.xmlBean.refundAmountSearch.request.bean.Order createOrder(String channelOrderNumber, NdcFlightApiOrderRel orderRel) {
        com.ndc.channel.flight.xmlBean.refundAmountSearch.request.bean.Order order = new com.ndc.channel.flight.xmlBean.refundAmountSearch.request.bean.Order();

        order.setOrderID(channelOrderNumber);
        order.setOwnerCode(orderRel.getOwnerCode());
        order.setOwnerTypeCode(orderRel.getOwnerTypeCode());
        return order;
    }

    private List<BookingRef> createBookingRef(String orderId, NdcFlightApiOrderRel ndcFlightApiOrderRel, List<Pax> paxList) {

        return paxList.stream().map(pax -> {
            BookingRef bookingRef = new BookingRef();

            bookingRef.setBookingID(pax.getPaxID());
            bookingRef.setBookingRefTypeCode("1");
            return bookingRef;
        }).collect(Collectors.toList());
    }

    private com.ndc.channel.flight.xmlBean.refundAmountSearch.request.bean.DataLists createDateLists(DataLists dataLists) {
        final com.ndc.channel.flight.xmlBean.refundAmountSearch.request.bean.DataLists dataList = new com.ndc.channel.flight.xmlBean.refundAmountSearch.request.bean.DataLists();

        final List<com.ndc.channel.flight.xmlBean.refundAmountSearch.request.bean.Pax> paxList = JSONObject.parseObject(JSON.toJSONString(dataLists.getPaxList()), new TypeReference<List<com.ndc.channel.flight.xmlBean.refundAmountSearch.request.bean.Pax>>() {
        });

        final List<com.ndc.channel.flight.xmlBean.refundAmountSearch.request.bean.ContactInfo> contactInfoList = JSONObject.parseObject(JSON.toJSONString(dataLists.getContactInfoList()), new TypeReference<List<com.ndc.channel.flight.xmlBean.refundAmountSearch.request.bean.ContactInfo>>() {});
        final com.ndc.channel.flight.xmlBean.refundAmountSearch.request.bean.ContactInfo primaryContact = contactInfoList.stream().filter(cf -> cf.getContactTypeText().equals("PRIMARY")).findFirst().get();
        primaryContact.setContactTypeText("APPLICANT");

        dataList.setPaxList(paxList);
        dataList.setContactInfoList(contactInfoList.stream().filter(c->!c.getContactTypeText().equals("TRAVEL_AGENCY")).collect(Collectors.toList()));

        return dataList;
    }

    private OrderChangeParameters createOrderChangeParameters(byte refundWay) {
        OrderChangeParameters parameters = new OrderChangeParameters();

        parameters.setReasonCode(BusinessEnum.RefundWay.getReasonCode(refundWay));

        return parameters;
    }

    private RefundChangeMoneyQueryResp getRefundChangeMoneyQueryResp(List<String> ticketNumList, List<com.ndc.channel.flight.xmlBean.refundAmountSearch.response.bean.refund.TicketDocInfo> ticketDocInfoList) {

        RefundChangeMoneyQueryResp resp = new RefundChangeMoneyQueryResp();

        BigDecimal refundFee = new BigDecimal("0");

        BigDecimal changeFee = new BigDecimal("0");

        BigDecimal refundMoney = new BigDecimal("0");

        for (com.ndc.channel.flight.xmlBean.refundAmountSearch.response.bean.refund.TicketDocInfo ticketDocInfo : ticketDocInfoList) {

            String ticketNumber = ticketDocInfo.getTicket().getTicketNumber();

            CarrierFee carrierFee = ticketDocInfo.getCarrierFee();

            if (ticketNumList.contains(ticketNumber)) {

                Map<String, com.ndc.channel.flight.xmlBean.refundAmountSearch.response.bean.refund.PenaltyAmount> penaltyAmountMap = ticketDocInfo.getPenalty().stream().collect(Collectors.toMap(com.ndc.channel.flight.xmlBean.refundAmountSearch.response.bean.refund.Penalty::getTypeCode, com.ndc.channel.flight.xmlBean.refundAmountSearch.response.bean.refund.Penalty::getPenaltyAmount));

                com.ndc.channel.flight.xmlBean.refundAmountSearch.response.bean.refund.PenaltyAmount penaltyAmountRefund = penaltyAmountMap.get(BusinessEnum.ChangeRefundTypeCode.CANCELLATION.getCode());
                com.ndc.channel.flight.xmlBean.refundAmountSearch.response.bean.refund.PenaltyAmount penaltyAmountChange = penaltyAmountMap.get(BusinessEnum.ChangeRefundTypeCode.CHANGE.getCode());

                refundFee = refundFee.add(new BigDecimal(Optional.ofNullable(penaltyAmountRefund.getValue()).orElse("0")));
                changeFee = changeFee.add(new BigDecimal(Optional.ofNullable(penaltyAmountChange.getValue()).orElse("0")));

                refundMoney = refundMoney.add(new BigDecimal(Optional.ofNullable(carrierFee.getAmount().getValue()).orElse("0")));
            }
        }

        resp.setChangeFee(changeFee);
        resp.setRefundFee(refundFee);
        resp.setRefundMoney(refundMoney);

        return resp;
    }


    private com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.Request getRefundConfirmReqData(RefundApplyParams params, com.ndc.channel.flight.xmlBean.refundApply.response.bean.Response refundApplyResponse, NdcFlightApiOrderRel orderRel) {
        com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.Request request = new com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.Request();

        final List<com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.BookingRef> bookingRefList = createRefundConfirmBookingRef(refundApplyResponse);
        request.setBookingRef(bookingRefList);

        final com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.DataLists refundConfirmDataLists = createRefundConfirmDataLists(params, refundApplyResponse);
        request.setDataLists(refundConfirmDataLists);

        final com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.Order refundConfirmOrder = createRefundConfirmOrder(orderRel);
        request.setOrder(refundConfirmOrder);


        final com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.OrderChangeParameters parameters = new com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.OrderChangeParameters();
        parameters.setReasonCode(BusinessEnum.RefundWay.getReasonCode(params.getRefundWay()));

        final com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.Remark remark = new com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.Remark();
        remark.setRemarkText(params.getMemo());
        parameters.setRemark(remark);
        request.setOrderChangeParameters(parameters);

        return request;
    }

    private com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.DataLists createRefundConfirmDataLists(RefundApplyParams params, Response refundApplyResponse) {

        final List<String> refundAttachmentUrl = params.getRefundAttachmentUrl();

        List<String> idCardListRefundPassenger = params.getRefundPassengerList().stream().map(RefundApplyPassengerParams::getIdcardCode).collect(Collectors.toList());

        final com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.DataLists refundConfirmDataLists = new com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.DataLists();

        final DataLists dataLists = refundApplyResponse.getDataLists();

        final List<com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.Pax> refundConfirmPaxList = createRefundConfirmPaxList(dataLists, idCardListRefundPassenger);
        refundConfirmDataLists.setPaxList(refundConfirmPaxList);

        final List<com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.ContactInfo> refundConfirmContactInfoList = createRefundConfirmContactInfo(dataLists);
        refundConfirmDataLists.setContactInfoList(refundConfirmContactInfoList);

        Media media = new Media();
        refundConfirmDataLists.setMediaList(Arrays.asList(media));

        return refundConfirmDataLists;
    }
    private List<com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.Pax> createRefundConfirmPaxList(DataLists dataLists, List<String> idCardListRefundPassenger) {
        final List<Pax> paxList = dataLists.getPaxList().stream().filter(pax -> {
            final IdentityDoc identityDoc = pax.getIdentityDoc();
            return idCardListRefundPassenger.contains(identityDoc.getIdentityDocID());
        }).collect(Collectors.toList());
        return JSONObject.parseObject(JSON.toJSONString(paxList), new TypeReference<List<com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.Pax>>() {});
    }
    private List<com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.BookingRef> createRefundConfirmBookingRef(Response refundApplyResponse) {

        final com.ndc.channel.flight.xmlBean.refundApply.response.bean.OrderAmendment orderAmendment = refundApplyResponse.getOrderAmendment();

        final TicketDocInfo ticketDocInfo = orderAmendment.getTicketDocInfo();

        final List<com.ndc.channel.flight.xmlBean.refundApply.response.bean.BookingRef> bookingRef = ticketDocInfo.getBookingRef();

        return JSONObject.parseObject(JSON.toJSONString(bookingRef), new TypeReference<List<com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.BookingRef>>(){});
    }
    private List<com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.ContactInfo> createRefundConfirmContactInfo(DataLists dataLists) {

        final List<ContactInfo> contactInfoList = dataLists.getContactInfoList();

        final List<com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.ContactInfo> contactInfos = JSONObject.parseObject(JSON.toJSONString(contactInfoList), new TypeReference<List<com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.ContactInfo>>() {});
        final com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.ContactInfo primaryContact = contactInfos.stream().filter(cf -> cf.getContactTypeText().equals("PRIMARY")).findFirst().get();
        primaryContact.setContactTypeText("APPLICANT");

        return contactInfos.stream().filter(c->!c.getContactTypeText().equals("TRAVEL_AGENCY")).collect(Collectors.toList());
    }
    private com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.Order createRefundConfirmOrder(NdcFlightApiOrderRel orderRel) {
        final com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.Order order = new com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.Order();
        order.setOrderID(orderRel.getOrderId());
        order.setOwnerCode(orderRel.getOwnerCode());
        order.setOwnerTypeCode(orderRel.getOwnerTypeCode());

        return order;
    }
}
