package com.ndc.channel.flight.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ndc.channel.entity.NdcFlightApiOrderRel;
import com.ndc.channel.entity.NdcFlightApiRefundOrderRel;
import com.ndc.channel.enumtype.BusinessEnum;
import com.ndc.channel.exception.BusinessException;
import com.ndc.channel.exception.BusinessExceptionCode;
import com.ndc.channel.flight.dto.refund.RefundChangeMoneyQueryParams;
import com.ndc.channel.flight.dto.refund.RefundChangeMoneyQueryResp;
import com.ndc.channel.flight.tools.NdcApiTools;
import com.ndc.channel.flight.xmlBean.refundAmountSearch.request.bean.BookingEntity;
import com.ndc.channel.flight.xmlBean.refundAmountSearch.request.bean.BookingRef;
import com.ndc.channel.flight.xmlBean.refundAmountSearch.request.bean.IATAOrderChangeRQ;
import com.ndc.channel.flight.xmlBean.refundAmountSearch.request.bean.OrderChangeParameters;
import com.ndc.channel.flight.xmlBean.refundAmountSearch.request.bean.Request;
import com.ndc.channel.flight.xmlBean.refundAmountSearch.response.bean.refund.*;
import com.ndc.channel.flight.xmlBean.refundApply.response.bean.ContactInfo;
import com.ndc.channel.flight.xmlBean.refundApply.response.bean.DataLists;
import com.ndc.channel.flight.xmlBean.refundApply.response.bean.Pax;
import com.ndc.channel.flight.xmlBean.refundApply.response.bean.Response;
import com.ndc.channel.mapper.NdcFlightApiOrderRelMapper;
import com.ndc.channel.mapper.NdcFlightApiRefundOrderRelMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 退票金额查询
 */

@Slf4j
@Component
public class NdcFlightRefundAmountSearchHandler {

    @Resource
    private NdcFlightApiOrderRelMapper apiOrderRelMapper;

    @Resource
    private NdcApiTools ndcApiTools;

    @Resource
    private NdcFlightOrderRefundHandler orderRefundHandler;

    @Resource
    private NdcFlightApiRefundOrderRelMapper refundOrderRelMapper;

    /**
     * 机票退票金额查询
     * @param params
     * @return
     */
    public RefundChangeMoneyQueryResp refundMoneyQuery(RefundChangeMoneyQueryParams params) {

        NdcFlightApiOrderRel ndcOrderRel = apiOrderRelMapper.selectByOrderId(params.getChannelOrderNumber());

        final Response refundApplyResp = orderRefundHandler.refundApply(params.getChannelOrderNumber(), params.getTicketNumList());

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
            if (params.getQueryType() == BusinessEnum.QueryType.CHANGE_MONEY_QUERY.getCode()) {
                resp.setChangeFee(BigDecimal.ZERO);
            }else {
                resp.setRefundFee(BigDecimal.ZERO);
            }
            return resp;
        }

        final com.ndc.channel.flight.xmlBean.refundAmountSearch.response.bean.refund.Response response = refundAmountQueryResult.getResponse();

        OrderAmendment orderAmendment = response.getOrderAmendment();
        final List<TicketDocInfo> ticketDocInfoList = orderAmendment.getTicketDocInfo();

        resp = getRefundChangeMoneyQueryResp(params.getTicketNumList(), ticketDocInfoList);

        return resp;
    }

    private IATAOrderChangeRQ createRefundAmountQueryReqParams(RefundChangeMoneyQueryParams params, NdcFlightApiOrderRel ndcOrderRel, DataLists dataLists) {

        final List<Pax> paxList = dataLists.getPaxList();
        final List<String> contactInfoRefID = dataLists.getContactInfoList().stream().map(ContactInfo::getContactInfoID).collect(Collectors.toList());

        IATAOrderChangeRQ iataOrderChangeRQ = new IATAOrderChangeRQ(contactInfoRefID);
        Request request = new Request();

        request.setOrder(createOrder(params.getChannelOrderNumber(), ndcOrderRel));
        request.setDataLists(createDateLists(dataLists));
        request.setOrderChangeParameters(createOrderChangeParameters(params.getRefundWay()));

        List<BookingRef> bookingRef = null;
        /**
         * 非自愿转自愿金额查询参数改动
         */
        if (params.getQueryType() == BusinessEnum.QueryType.REFUND_MONEY_QUERY_.getCode()) {
            iataOrderChangeRQ.getMessageDoc().setName("NDC_FLIGHT_TICKET_REFUND_RECALCFEE");
            request.getDataLists().setPaxList(null);
            List<com.ndc.channel.flight.xmlBean.refundAmountSearch.request.bean.ContactInfo> contactInfoList = request.getDataLists().getContactInfoList()
                    .stream().filter(c -> !c.getContactTypeText().equals("PAX"))
                    .collect(Collectors.toList());
            request.getDataLists().setContactInfoList(contactInfoList);

            bookingRef = createBookingRef(params, ndcOrderRel);
        }else {
            bookingRef = createBookingRef(params, ndcOrderRel, paxList);
        }

        request.setBookingRef(bookingRef);
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

    /**
     * 非自愿转自愿金额查询创建BookingRef节点
     */
    private List<BookingRef> createBookingRef(RefundChangeMoneyQueryParams params, NdcFlightApiOrderRel ndcFlightApiOrderRel) {
        Long ndcRefundRelId = params.getNdcRefundRelId();
        if (ndcRefundRelId == null) {
            throw new BusinessException(BusinessExceptionCode.FLIGHT_CHANNEL_ERROR, "非自愿转自愿原退票单号必填！");
        }
        if (params.getRefundWay() != 1) {
            throw new BusinessException(BusinessExceptionCode.REQUEST_PARAM_ERROR, "非自愿转自愿退票方式错误！");
        }
        NdcFlightApiRefundOrderRel refundOrderRel = refundOrderRelMapper.selectByPrimaryKey(ndcRefundRelId);
        if (refundOrderRel == null) {
            throw new BusinessException(BusinessExceptionCode.REQUEST_PARAM_ERROR, "原退票单不存在！");
        }
        BookingRef bookingRef = new BookingRef();

        bookingRef.setBookingRefTypeCode(BusinessEnum.BookingRefTypeCode.REFUND_ORDER_ID.getCode());
        bookingRef.setBookingID(refundOrderRel.getRefundId());

        BookingEntity bookingEntity = new BookingEntity();
        com.ndc.channel.flight.xmlBean.refundAmountSearch.request.bean.Carrier carrier = new com.ndc.channel.flight.xmlBean.refundAmountSearch.request.bean.Carrier();
        carrier.setAirlineDesigCode(ndcFlightApiOrderRel.getOwnerCode());
        bookingEntity.setCarrier(carrier);
        bookingRef.setBookingEntity(bookingEntity);

        return Arrays.asList(bookingRef);
    }
    private List<BookingRef> createBookingRef(RefundChangeMoneyQueryParams params, NdcFlightApiOrderRel ndcFlightApiOrderRel, List<Pax> paxList) {
        return paxList.stream().map(pax -> {
            BookingRef bookingRef = new BookingRef();

            bookingRef.setBookingID(pax.getPaxID());
            bookingRef.setBookingRefTypeCode(BusinessEnum.BookingRefTypeCode.PAX_ID.getCode());
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

                Map<String, PenaltyAmount> penaltyAmountMap = ticketDocInfo.getPenalty().stream().collect(Collectors.toMap(com.ndc.channel.flight.xmlBean.refundAmountSearch.response.bean.refund.Penalty::getTypeCode, com.ndc.channel.flight.xmlBean.refundAmountSearch.response.bean.refund.Penalty::getPenaltyAmount));

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

}
