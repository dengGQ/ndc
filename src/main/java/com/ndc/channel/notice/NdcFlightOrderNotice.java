package com.ndc.channel.notice;

import com.alibaba.fastjson.JSON;
import com.ndc.channel.entity.NdcFlightApiOrderRel;
import com.ndc.channel.enumtype.BusinessEnum;
import com.ndc.channel.flight.dto.createOrder.FlightOrderNoticeData;
import com.ndc.channel.flight.dto.createOrder.FlightOrderPassengerData;
import com.ndc.channel.flight.dto.orderDetail.NdcOrderDetailData;
import com.ndc.channel.flight.dto.orderDetail.OrderTicketInfo;
import com.ndc.channel.http.ChannelOKHttpService;
import com.ndc.channel.mapper.NdcFlightApiOrderRelMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class NdcFlightOrderNotice {

    @Resource
    private NdcFlightApiOrderRelMapper orderRelMapper;

    @Resource
    private ChannelOKHttpService okHttpService;

    public void bookingResultNotice(NdcOrderDetailData ndcOrderDetailData) {

        String orderId = ndcOrderDetailData.getChannelGroupOrderNumber();
        NdcFlightApiOrderRel ndcFlightApiOrderRel = orderRelMapper.selectByOrderId(orderId);
        String afterTicketUrl = ndcFlightApiOrderRel.getAfterTicketUrl();
        FlightOrderNoticeData noticeData = new FlightOrderNoticeData();
        try{
            // 通知第三方订单状态
            noticeData.setExternalOrderNumber(ndcFlightApiOrderRel.getExternalOrderNumber());

            String orderStatus = ndcOrderDetailData.getOrderStatus();

            if (orderStatus.equals(BusinessEnum.OrderItemStatusCode.OUTINVOICE.name())) {
                List<OrderTicketInfo> ticketInfoList = ndcOrderDetailData.getTicketInfoList();
                List<FlightOrderPassengerData> passengerDataList = ticketInfoList.stream().map(ticketInfo -> {
                    FlightOrderPassengerData passengerData = new FlightOrderPassengerData();

                    passengerData.setName(ticketInfo.getPassengerName());
                    passengerData.setPnr(ticketInfo.getPnrCode());
                    passengerData.setIdcardCode(ticketInfo.getIdCardNo());
                    passengerData.setTicketNumber(ticketInfo.getTicketNumber());

                    return passengerData;
                }).collect(Collectors.toList());
                noticeData.setOrderNumber(orderId);
                noticeData.setPnr(ticketInfoList.get(0).getPnrCode());
                noticeData.setPassengerDatas(passengerDataList);
                noticeData.setIsSuccess(true);
            }else {
                noticeData.setIsSuccess(false);
                noticeData.setMessage(BusinessEnum.OrderItemStatusCode.getLabelByName(orderStatus));
            }
            okHttpService.doPost(afterTicketUrl, JSON.toJSONString(noticeData));
        }catch (Exception e) {
            log.error("Ndc订单状态推送失败, url="+afterTicketUrl+", params="+JSON.toJSONString(noticeData), e);
        }
    }

    public void refundResultNotice(NdcOrderDetailData ndcOrderDetailData) {}

    public void changeResultNotice(NdcOrderDetailData ndcOrderDetailData) {}
}