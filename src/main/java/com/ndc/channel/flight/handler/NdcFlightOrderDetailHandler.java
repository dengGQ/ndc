package com.ndc.channel.flight.handler;

import com.alibaba.fastjson.JSON;
import com.ndc.channel.entity.NdcFlightApiOrderRel;
import com.ndc.channel.enumtype.BusinessEnum;
import com.ndc.channel.exception.BusinessException;
import com.ndc.channel.exception.BusinessExceptionCode;
import com.ndc.channel.executor.OrderDetailDelayQueryExecutor;
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
import com.ndc.channel.notice.NdcFlightOrderNotice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Component
public class NdcFlightOrderDetailHandler {

    @Resource
    private NdcApiTools ndcApiTools;

    @Resource
    private OrderDetailDelayQueryExecutor detailDelayQueryExecutor;

    @Resource
    private NdcFlightOrderNotice flightOrderNotice;

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

            final BookingRef bookingRef = tf.getBookingRef();
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
        detailData.setChannelGroupOrderNumber(orderId);

        return detailData;
    }

    public void orderStatusProcess(String orderId) {
        NdcOrderDetailData ndcOrderDetailData = this.orderDetail(orderId);

        log.info("NDC订单明细查询结果={}", JSON.toJSONString(ndcOrderDetailData));

        if (BusinessEnum.OrderItemStatusCode.INVOICING.name().equals(ndcOrderDetailData.getOrderStatus())
                || BusinessEnum.OrderItemStatusCode.PAYING.name().equals(ndcOrderDetailData.getOrderStatus())) {

            detailDelayQueryExecutor.submitTask(orderId, 60*10);
        }else {
            flightOrderNotice.bookingResultNotice(ndcOrderDetailData);
        }
    }
}
