package com.ndc.channel.flight.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ndc.channel.entity.NdcFlightApiOrderRel;
import com.ndc.channel.enumtype.BusinessEnum;
import com.ndc.channel.exception.BusinessException;
import com.ndc.channel.exception.BusinessExceptionCode;
import com.ndc.channel.flight.dto.refund.RefundApplyParams;
import com.ndc.channel.flight.dto.refund.RefundChangeMoneyQueryParams;
import com.ndc.channel.flight.dto.refund.RefundChangeMoneyQueryResp;
import com.ndc.channel.flight.tools.NdcApiTools;
import com.ndc.channel.flight.xmlBean.orderRefund.request.bean.IATAOrderRetrieveRQ;
import com.ndc.channel.flight.xmlBean.orderRefund.response.bean.*;
import com.ndc.channel.flight.xmlBean.orderRefund.response.bean.Error;
import com.ndc.channel.flight.xmlBean.refundAmountSearch.request.bean.BookingRef;
import com.ndc.channel.flight.xmlBean.refundAmountSearch.request.bean.IATAOrderChangeRQ;
import com.ndc.channel.flight.xmlBean.refundAmountSearch.request.bean.OrderChangeParameters;
import com.ndc.channel.flight.xmlBean.refundAmountSearch.request.bean.Request;
import com.ndc.channel.flight.xmlBean.refundAmountSearch.response.bean.refund.OrderAmendment;
import com.ndc.channel.flight.xmlBean.refundAmountSearch.response.bean.refund.PaymentInfo;
import com.ndc.channel.mapper.NdcFlightApiOrderRelMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
public class NdcFlightOrderRefundHandler {

    @Resource
    private NdcApiTools ndcApiTools;

    @Resource
    private NdcFlightApiOrderRelMapper apiOrderRelMapper;

    public String refundConfire(RefundApplyParams params) {
        return null;
    }

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

        return resp;
    }

    private Response refundApply(String orderId) {

        NdcFlightApiOrderRel ndcFlightApiOrderRel = apiOrderRelMapper.selectByOrderId(orderId);

        IATAOrderRetrieveRQ iataOrderRetrieveRQ = new IATAOrderRetrieveRQ(orderId, ndcFlightApiOrderRel);

        IATAOrderViewRS refundApplyResult = ndcApiTools.refundApply(iataOrderRetrieveRQ);

        final Error error = refundApplyResult.getError();
        if (error != null) {
            throw new BusinessException(BusinessExceptionCode.FLIGHT_CHANNEL_ERROR, error.getDescText());
        }

        Response response = refundApplyResult.getResponse();

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

        final List<com.ndc.channel.flight.xmlBean.refundAmountSearch.request.bean.ContactInfo> contactInfoList = JSONObject.parseObject(JSON.toJSONString(dataLists.getContactInfoList()), new TypeReference<List<com.ndc.channel.flight.xmlBean.refundAmountSearch.request.bean.ContactInfo>>() {
        });

        dataList.setPaxList(paxList);
        dataList.setContactInfoList(contactInfoList);

        return dataList;
    }

    private OrderChangeParameters createOrderChangeParameters(byte refundWay) {
        OrderChangeParameters parameters = new OrderChangeParameters();

        parameters.setReasonCode(BusinessEnum.RefundWay.getReasonCode(refundWay));

        return parameters;
    }

    private RefundChangeMoneyQueryResp getRefundChangeMoneyQueryResp(List<String> ticketNumList, List<com.ndc.channel.flight.xmlBean.refundAmountSearch.response.bean.refund.TicketDocInfo> ticketDocInfoList) {

        RefundChangeMoneyQueryResp resp = new RefundChangeMoneyQueryResp();

        BigDecimal refundMoney = new BigDecimal("0");

        BigDecimal changeMoney = new BigDecimal("0");

        for (com.ndc.channel.flight.xmlBean.refundAmountSearch.response.bean.refund.TicketDocInfo ticketDocInfo : ticketDocInfoList) {
            String ticketNumber = ticketDocInfo.getTicket().getTicketNumber();
            if (ticketNumList.contains(ticketNumber)) {

                Map<String, com.ndc.channel.flight.xmlBean.refundAmountSearch.response.bean.refund.PenaltyAmount> penaltyAmountMap = ticketDocInfo.getPenalty().stream().collect(Collectors.toMap(com.ndc.channel.flight.xmlBean.refundAmountSearch.response.bean.refund.Penalty::getTypeCode, com.ndc.channel.flight.xmlBean.refundAmountSearch.response.bean.refund.Penalty::getPenaltyAmount));

                com.ndc.channel.flight.xmlBean.refundAmountSearch.response.bean.refund.PenaltyAmount penaltyAmountRefund = penaltyAmountMap.get(BusinessEnum.ChangeRefundTypeCode.CANCELLATION.getCode());
                com.ndc.channel.flight.xmlBean.refundAmountSearch.response.bean.refund.PenaltyAmount penaltyAmountChange = penaltyAmountMap.get(BusinessEnum.ChangeRefundTypeCode.CHANGE.getCode());

                refundMoney = refundMoney.add(new BigDecimal(Optional.ofNullable(penaltyAmountRefund.getValue()).orElse("0")));
                changeMoney = refundMoney.add(new BigDecimal(Optional.ofNullable(penaltyAmountChange.getValue()).orElse("0")));
            }
        }

        resp.setChangeFee(changeMoney);
        resp.setRefundFee(refundMoney);

        return resp;
    }
}
