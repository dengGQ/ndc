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
import com.ndc.channel.flight.dto.refund.NdcRefundOrderSearchParams;
import com.ndc.channel.flight.tools.NdcApiTools;
import com.ndc.channel.flight.xmlBean.refundOrderDetail.request.bean.IATAOrderRetrieveRQ;
import com.ndc.channel.flight.xmlBean.refundOrderDetail.request.bean.Order;
import com.ndc.channel.flight.xmlBean.refundOrderDetail.response.bean.*;
import com.ndc.channel.flight.xmlBean.refundOrderDetail.response.bean.Error;
import com.ndc.channel.http.ChannelOKHttpService;
import com.ndc.channel.mapper.NdcFlightApiOrderRelMapper;
import com.ndc.channel.mapper.NdcFlightApiRefundOrderRelMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Component(value = "orderDetailHandler2")
public class NdcFlightRefundOrderDetailHandler implements NdcOrderDetailHandler<NdcRefundOrderSearchParams>{

    @Resource
    private NdcFlightApiRefundOrderRelMapper refundOrderRelMapper;

    @Resource
    private NdcFlightApiOrderRelMapper apiOrderRelMapper;

    @Resource
    private NdcApiTools ndcApiTools;

    @Resource
    private ChannelOKHttpService okHttpService;

    /**
     * 由于NDC退票单详情接口返回的是原订单下所有退票单信息（包括非同批次提交的退票）
     * 已经获取到渠道退票单号后，根据渠道退票单号检索退票单
     * @param orderRel
     * @param refundOrderRel
     * @return
     */
    public NdcOrderDetailData refundOrderDetail(NdcFlightApiOrderRel orderRel, NdcFlightApiRefundOrderRel refundOrderRel) {

        Response response = doSearchRefundOrderDetail(orderRel);

        List<TicketDocInfo> ticketDocInfoList = response.getOrderAmendment().stream().map(OrderAmendment::getTicketDocInfo).flatMap(Collection::stream).collect(Collectors.toList())
                .stream().filter(ticketDocInfo -> {

                    // 查询退票单
                    long count = ticketDocInfo.getBookingRef().stream()
                            .filter(bookingRef ->
                                    BusinessEnum.BookingRefTypeCode.REFUND_ORDER_ID.getCode().equals(bookingRef.getBookingRefTypeCode())
                                    && bookingRef.getBookingID().equals(refundOrderRel.getRefundId()))
                            .count();

                    return count > 0;
                }).collect(Collectors.toList());

        Map<String, Pax> paxMap = response.getDataLists().getPaxList().stream().collect(Collectors.toMap(Pax::getPaxID, Function.identity()));

        List<OrderTicketInfo> ticketInfoList = createOrderTicketInfo(ticketDocInfoList, paxMap);

        NdcOrderDetailData detailData = createNdcOrderDetailData(ticketInfoList, refundOrderRel.getExternalRefundNumber(), refundOrderRel.getRefundId());

        detailData.setRefundDetailResponse(response);
        return detailData;
    }

    /**
     * 由于NDC退票单详情接口返回的是原订单下所有退票单信息（包括非同批次提交的退票）
     * 所以在未获取到退票单号之前需要传入乘客ID（paxID）来查找本次退票申请所对应的退票单号
     * @param params
     * @return
     */
    @Override
    public NdcOrderDetailData orderDetail(NdcRefundOrderSearchParams params) {

        NdcFlightApiRefundOrderRel refundOrderRel = refundOrderRelMapper.selectByPrimaryKey(params.getRefundOrderPrimaryKey());

        NdcFlightApiOrderRel orderRel = apiOrderRelMapper.selectByOrderId(refundOrderRel.getOrderId());

        final Response response = doSearchRefundOrderDetail(orderRel);

        Map<String, Pax> paxMap = response.getDataLists().getPaxList().stream().collect(Collectors.toMap(Pax::getPaxID, Function.identity()));

        com.ndc.channel.flight.xmlBean.refundOrderDetail.response.bean.OrderAmendment orderAmendment = response.getOrderAmendment().stream()
                .filter(oad -> oad.getPaxRefID().contains(params.getPaxIdList().get(0)))
                .findFirst().get();

        List<TicketDocInfo> ticketDocInfoList = orderAmendment.getTicketDocInfo();
        String refundId = ticketDocInfoList.get(0).getBookingRef().stream()
                .filter(bookingRef -> BusinessEnum.BookingRefTypeCode.REFUND_ORDER_ID.getCode().equals(bookingRef.getBookingRefTypeCode()))
                .findFirst().get().getBookingID();

        List<OrderTicketInfo> ticketInfoList = createOrderTicketInfo(ticketDocInfoList, paxMap);


        NdcOrderDetailData detailData = createNdcOrderDetailData(ticketInfoList, refundOrderRel.getExternalRefundNumber(), refundId);
        detailData.setRefundDetailResponse(response);

        // 判断渠道订单号是否已经更新进关联订单中
        if (StringUtils.isEmpty(refundOrderRel.getRefundId())) {
            refundOrderRel.setRefundId(refundId);
            refundOrderRelMapper.updateByPrimaryKeySelective(refundOrderRel);
        }

        return detailData;
    }

    @Override
    public Boolean checkStatusComplete(NdcOrderDetailData detailData) {
        return BusinessEnum.RefundAuditingStatus.getEndStatus().contains(detailData.getTicketInfoList().get(0).getRefundAuditingStatus());
    }

    @Override
    public void statusChangeNotice(NdcOrderDetailData ndcOrderDetailData) {
        FlightRefundOrderNoticeData noticeData = new FlightRefundOrderNoticeData();
        String externalRefundNumber = ndcOrderDetailData.getChannelOrderNumber();

        NdcFlightApiRefundOrderRel refundOrderRel = refundOrderRelMapper.selectByExternalRefundNumber(externalRefundNumber);
        noticeData.setExternalRefundNumber(refundOrderRel.getExternalRefundNumber());
        String refundAuditingStatus = ndcOrderDetailData.getTicketInfoList().get(0).getRefundAuditingStatus();

        try{

            /*if (BusinessEnum.RefundAuditingStatus.getTicketRefundFail().contains(refundAuditingStatus)) {

                noticeData.setIsSuccess(false);
                noticeData.setMessage(BusinessEnum.RefundAuditingStatus.getLabelByName(refundAuditingStatus));
            }else */
            if(BusinessEnum.RefundAuditingStatus.getTicketRefundedStatus().contains(refundAuditingStatus)) {
                // 票已退 款未退
                noticeData.setIsSuccess(false);
                noticeData.setMessage("一审拒绝");
            }else if (BusinessEnum.RefundAuditingStatus.getCompleteStatus().contains(refundAuditingStatus)){
                // 退款成功
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
                noticeData.setRefundNumber(refundOrderRel.getRefundId());
                noticeData.setPassengerDatas(passengerDataList);
                noticeData.setIsSuccess(true);
            }

            okHttpService.doPost(refundOrderRel.getAfterRefundTicketUrl(), JSON.toJSONString(noticeData));
        }catch (Exception e) {
            log.error("Ndc退票单状态推送失败, url="+refundOrderRel.getAfterRefundTicketUrl()+", params="+JSON.toJSONString(noticeData), e);
        }
    }

    @Override
    public NdcRefundOrderSearchParams resolveParams(Object params) {
        return JSONObject.parseObject(JSON.toJSONString(params), NdcRefundOrderSearchParams.class);
    }

    private Response doSearchRefundOrderDetail(NdcFlightApiOrderRel orderRel) {
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

        return response;
    }

    private NdcOrderDetailData createNdcOrderDetailData(List<OrderTicketInfo> ticketInfoList, String externalRefundNumber, String refundId) {
        NdcOrderDetailData detailData = new NdcOrderDetailData();

        detailData.setTicketInfoList(ticketInfoList);
        detailData.setOrderStatus(ticketInfoList.get(0).getTicketStatus());
        detailData.setRefundMoney(ticketInfoList.stream().map(ot->Optional.ofNullable(ot.getRefundAmount()).orElse(BigDecimal.ZERO)).reduce(BigDecimal::add).get());
        detailData.setChannelOrderNumber(externalRefundNumber);
        detailData.setRefundId(refundId);
        return detailData;
    }

    /**
     * 退票结果通知票的相关信息
     * @param ticketDocInfoList
     * @param paxMap
     * @return
     */
    private List<OrderTicketInfo> createOrderTicketInfo(List<TicketDocInfo> ticketDocInfoList, Map<String, Pax> paxMap) {
        List<OrderTicketInfo> ticketInfoList = ticketDocInfoList.stream().map(ticketDocInfo -> {

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
            ticketInfo.setPaxId(paxRefID);

            return ticketInfo;
        }).collect(Collectors.toList());

        return ticketInfoList;
    }
}
