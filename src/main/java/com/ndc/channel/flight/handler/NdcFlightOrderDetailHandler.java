package com.ndc.channel.flight.handler;

import com.alibaba.fastjson.JSON;
import com.ndc.channel.entity.NdcFlightApiOrderRel;
import com.ndc.channel.enumtype.BusinessEnum;
import com.ndc.channel.exception.BusinessException;
import com.ndc.channel.exception.BusinessExceptionCode;
import com.ndc.channel.flight.dto.createOrder.FlightOrderNoticeData;
import com.ndc.channel.flight.dto.createOrder.FlightOrderPassengerData;
import com.ndc.channel.flight.dto.orderDetail.NdcOrderDetailData;
import com.ndc.channel.flight.dto.orderDetail.OrderTicketInfo;
import com.ndc.channel.flight.tools.NdcApiTools;
import com.ndc.channel.flight.xmlBean.orderDetail.request.bean.IATAOrderRetrieveRQ;
import com.ndc.channel.flight.xmlBean.orderDetail.request.bean.Order;
import com.ndc.channel.flight.xmlBean.orderDetail.response.bean.*;
import com.ndc.channel.flight.xmlBean.orderDetail.response.bean.Error;
import com.ndc.channel.http.ChannelOKHttpService;
import com.ndc.channel.mapper.NdcFlightApiOrderRelMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Component(value = "orderDetailHandler1")
public class NdcFlightOrderDetailHandler implements NdcOrderDetailHandler{

    @Resource
    private NdcApiTools ndcApiTools;

    @Resource
    private NdcFlightApiOrderRelMapper orderRelMapper;

    @Resource
    private ChannelOKHttpService okHttpService;

    @Override
    public NdcOrderDetailData orderDetail(String orderId) {

        final Order order = new Order();
        order.setOrderID(orderId);
        order.setOwnerCode("MU");
        order.setOwnerTypeCode(null);

        final IATAOrderRetrieveRQ retrieveRQ = new IATAOrderRetrieveRQ(order);

        IATAOrderViewRS iataOrderViewRS = ndcApiTools.orderDetail(retrieveRQ);

        Error error = iataOrderViewRS.getError();

        if (error != null) {
            log.error("NDC机票订单详情查询失败，echoTokenText={}, failReason={}", retrieveRQ.getPayloadAttributes().getEchoTokenText(), error.getDescText());
            throw new BusinessException(BusinessExceptionCode.REQUEST_PARAM_ERROR, error.getDescText());
        }

        final Response response = iataOrderViewRS.getResponse();

        final DataLists dataLists = response.getDataLists();

        final List<Pax> paxList = dataLists.getPaxList();
        final Map<String, Pax> paxMap = paxList.stream().collect(Collectors.toMap(Pax::getPaxID, Function.identity()));

        final List<TicketDocInfo> ticketDocInfo = response.getTicketDocInfo();

        final OrderItem orderItem = response.getOrder().getOrderItem();

        String statusCode = orderItem.getStatusCode();

        final List<OrderTicketInfo> ticketInfoList = ticketDocInfo.stream().map(tf -> {

            final OrderTicketInfo ticketInfo = new OrderTicketInfo();

            final String paxRefID = tf.getPaxRefID();
            final Pax pax = paxMap.get(paxRefID);
            final IdentityDoc identityDoc = pax.getIdentityDoc();

            final BookingRef bookingRef = tf.getBookingRef().stream().filter(bf->bf.getBookingRefTypeCode().equals("6")).findFirst().get();
            final String pnrCode = bookingRef.getBookingID();

            final Ticket ticket = tf.getTicket();
            final String ticketNumber = ticket.getTicketNumber();

            final Coupon coupon = ticket.getCoupon();
            final String ticketStatus = coupon.getCouponStatusCode();

            ticketInfo.setTicketNumber(ticketNumber);
            ticketInfo.setPnrCode(pnrCode);
            ticketInfo.setTicketStatus(ticketStatus);
            ticketInfo.setPassengerName(identityDoc.getSurname());
            ticketInfo.setIdCardNo(identityDoc.getIdentityDocID());

            return ticketInfo;
        }).collect(Collectors.toList());

        final NdcOrderDetailData detailData = new NdcOrderDetailData();

        detailData.setOrderStatus(statusCode);
        detailData.setTicketInfoList(ticketInfoList);
        detailData.setChannelOrderNumber(orderId);

        return detailData;
    }

    @Override
    public Boolean checkStatusComplete(NdcOrderDetailData detailData) {
        return BusinessEnum.OrderItemStatusCode.getCompleteStatusCode().contains(detailData.getOrderStatus());
    }

    @Override
    public void statusChangeNotice(NdcOrderDetailData ndcOrderDetailData) {
        String orderId = ndcOrderDetailData.getChannelOrderNumber();
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
}
