package com.ndc.channel.flight.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ndc.channel.entity.NdcFlightApiOrderRel;
import com.ndc.channel.entity.NdcFlightApiRefundOrderRel;
import com.ndc.channel.enumtype.BusinessEnum;
import com.ndc.channel.exception.BusinessException;
import com.ndc.channel.exception.BusinessExceptionCode;
import com.ndc.channel.flight.dto.createOrder.FlightOrderPassengerData;
import com.ndc.channel.flight.dto.orderDetail.NdcOrderDetailData;
import com.ndc.channel.flight.dto.orderDetail.OrderTicketInfo;
import com.ndc.channel.flight.dto.refund.FlightRefundOrderNoticeData;
import com.ndc.channel.flight.tools.NdcApiTools;
import com.ndc.channel.flight.xmlBean.refundOrderDetail.request.bean.IATAOrderRetrieveRQ;
import com.ndc.channel.flight.xmlBean.refundOrderDetail.request.bean.Order;
import com.ndc.channel.flight.xmlBean.refundOrderDetail.response.bean.*;
import com.ndc.channel.flight.xmlBean.refundOrderDetail.response.bean.Error;
import com.ndc.channel.http.ChannelOKHttpService;
import com.ndc.channel.mapper.NdcFlightApiOrderRelMapper;
import com.ndc.channel.mapper.NdcFlightApiRefundOrderRelMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Component(value = "orderDetailHandler2")
public class NdcFlightRefundOrderDetailHandler implements NdcOrderDetailHandler{

    @Resource
    private NdcFlightApiRefundOrderRelMapper refundOrderRelMapper;

    @Resource
    private NdcFlightApiOrderRelMapper apiOrderRelMapper;

    @Resource
    private NdcApiTools ndcApiTools;

    @Resource
    private ChannelOKHttpService okHttpService;

    @Override
    public NdcOrderDetailData orderDetail(String refundId) {

        NdcFlightApiRefundOrderRel apiRefundOrderRel = refundOrderRelMapper.selectByRefundId(refundId);

        NdcFlightApiOrderRel orderRel = apiOrderRelMapper.selectByOrderId(apiRefundOrderRel.getOrderId());

        Order order = new Order();
        order.setOrderID(orderRel.getOrderId());
        order.setOwnerCode(orderRel.getOwnerCode());
        order.setOwnerTypeCode(orderRel.getOwnerTypeCode());
        IATAOrderRetrieveRQ rq = new IATAOrderRetrieveRQ(order);

        IATAOrderViewRS refundOrderResp = ndcApiTools.refundOrderDetail(rq);

        if (refundOrderResp == null) {
            throw new BusinessException(BusinessExceptionCode.FLIGHT_CHANNEL_ERROR, "渠道接口异常");
        }
        Error error = refundOrderResp.getError();
        if (error != null) {
            log.error("退票单明细查询失败，orderId={}", orderRel.getOrderId());
            throw new BusinessException(BusinessExceptionCode.REQUEST_PARAM_ERROR, error.getDescText());
        }

        Response response = refundOrderResp.getResponse();

        Map<String, Pax> paxMap = response.getDataLists().getPaxList().stream().collect(Collectors.toMap(Pax::getPaxID, Function.identity()));

        OrderAmendment orderAmendment = response.getOrderAmendment().get(0);

        final List<TicketDocInfo> ticketDocInfoList = orderAmendment.getTicketDocInfo();

        BigDecimal refundMoney = new BigDecimal("0");

        final List<OrderTicketInfo> ticketInfoList = ticketDocInfoList.stream().map(ticketDocInfo -> {

            OrderTicketInfo ticketInfo = new OrderTicketInfo();

            final String paxRefID = ticketDocInfo.getPaxRefID();
            Pax pax = paxMap.get(paxRefID);

            Ticket ticket = ticketDocInfo.getTicket();
            Coupon coupon = ticket.getCoupon();

            BigDecimal refundFee = null;
            BigDecimal changeFee = null;
            if (ticketDocInfo.getPenalty() != null) {
                Map<String, PenaltyAmount> penaltyAmountMap = ticketDocInfo.getPenalty().stream().collect(Collectors.toMap(Penalty::getTypeCode, Penalty::getPenaltyAmount));

                PenaltyAmount penaltyAmountRefund = Optional.ofNullable(penaltyAmountMap.get(BusinessEnum.ChangeRefundTypeCode.CANCELLATION.getCode())).orElseGet(PenaltyAmount::new);
                PenaltyAmount penaltyAmountChange = Optional.ofNullable(penaltyAmountMap.get(BusinessEnum.ChangeRefundTypeCode.CHANGE.getCode())).orElseGet(PenaltyAmount::new);

                refundFee = new BigDecimal(Optional.ofNullable(penaltyAmountRefund.getValue()).orElse("0"));
                changeFee = new BigDecimal(Optional.ofNullable(penaltyAmountChange.getValue()).orElse("0"));
            }

            CarrierFee carrierFee = ticketDocInfo.getCarrierFee();
            BigDecimal refundMoneyPax = null;
            if (carrierFee != null) {

                Amount amount = carrierFee.getAmount();
                refundMoneyPax = new BigDecimal(Optional.ofNullable(amount.getValue()).orElse("0"));
            }

            JSONObject jsonObject = JSONObject.parseObject(ticket.getRemarkText());
            String auditingStatus = jsonObject.getString("auditingStatus");

            ticketInfo.setRefundAmount(refundMoneyPax);
            ticketInfo.setRefundFee(refundFee);
            ticketInfo.setRefundAuditingStatus(auditingStatus);
            ticketInfo.setTicketStatus(coupon.getCouponStatusCode());
            ticketInfo.setIdCardNo(pax.getIdentityDoc().getIdentityDocID());
            ticketInfo.setTicketNumber(ticket.getTicketNumber());
            ticketInfo.setPassengerName(pax.getIdentityDoc().getSurname());

            return ticketInfo;
        }).collect(Collectors.toList());

        NdcOrderDetailData detailData = new NdcOrderDetailData();

        detailData.setTicketInfoList(ticketInfoList);
        detailData.setOrderStatus(ticketInfoList.get(0).getTicketStatus());
        detailData.setRefundMoney(ticketInfoList.stream().map(ot->Optional.ofNullable(ot.getRefundAmount()).orElse(BigDecimal.ZERO)).reduce(BigDecimal::add).get());
        detailData.setChannelOrderNumber(apiRefundOrderRel.getRefundId());

        return detailData;

    }

    @Override
    public Boolean checkStatusComplete(NdcOrderDetailData detailData) {
        return BusinessEnum.RefundAuditingStatus.allCompleteStatus().contains(detailData.getTicketInfoList().get(0).getRefundAuditingStatus());
    }

    @Override
    public void statusChangeNotice(NdcOrderDetailData ndcOrderDetailData) {
        FlightRefundOrderNoticeData noticeData = new FlightRefundOrderNoticeData();
        String refundId = ndcOrderDetailData.getChannelOrderNumber();

        NdcFlightApiRefundOrderRel refundOrderRel = refundOrderRelMapper.selectByRefundId(refundId);
        noticeData.setExternalRefundNumber(refundOrderRel.getExternalRefundNumber());
        String refundAuditingStatus = ndcOrderDetailData.getTicketInfoList().get(0).getRefundAuditingStatus();

        try{

            if (BusinessEnum.RefundAuditingStatus.REFUND_SUCCESS.getCode().equals(refundAuditingStatus)
                    || BusinessEnum.RefundAuditingStatus.REFUND_COMPLETE.getCode().equals(refundAuditingStatus)) {

                List<FlightOrderPassengerData> passengerDataList = ndcOrderDetailData.getTicketInfoList().stream().map(ticketInfo -> {
                    FlightOrderPassengerData passengerData = new FlightOrderPassengerData();

                    passengerData.setName(ticketInfo.getPassengerName());
                    passengerData.setIdcardCode(ticketInfo.getIdCardNo());
                    passengerData.setTicketNumber(ticketInfo.getTicketNumber());
                    passengerData.setRefundFee(ticketInfo.getRefundFee());
                    passengerData.setRefundAmount(ticketInfo.getRefundAmount());

                    return passengerData;
                }).collect(Collectors.toList());

                noticeData.setRefundMoney(passengerDataList.stream().map(FlightOrderPassengerData::getRefundAmount).reduce(BigDecimal::add).get());
                noticeData.setRefundNumber(refundId);
                noticeData.setPassengerDatas(passengerDataList);
                noticeData.setIsSuccess(true);
            }else {
                noticeData.setIsSuccess(false);
                noticeData.setMessage(BusinessEnum.RefundAuditingStatus.getLabelByName(refundAuditingStatus));
            }

            okHttpService.doPost(refundOrderRel.getAfterRefundTicketUrl(), JSON.toJSONString(noticeData));
        }catch (Exception e) {
            log.error("Ndc退票单状态推送失败, url="+refundOrderRel.getAfterRefundTicketUrl()+", params="+JSON.toJSONString(noticeData), e);
        }
    }
}
