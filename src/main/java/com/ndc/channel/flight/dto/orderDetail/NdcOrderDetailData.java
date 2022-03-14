package com.ndc.channel.flight.dto.orderDetail;

import java.math.BigDecimal;
import java.util.List;

public class NdcOrderDetailData {

    private String channelOrderNumber;

    private List<OrderTicketInfo> ticketInfoList;

    private String orderStatus;

    private BigDecimal refundMoney;

    public String getChannelOrderNumber() {
        return channelOrderNumber;
    }

    public void setChannelOrderNumber(String channelOrderNumber) {
        this.channelOrderNumber = channelOrderNumber;
    }

    public List<OrderTicketInfo> getTicketInfoList() {
        return ticketInfoList;
    }

    public void setTicketInfoList(List<OrderTicketInfo> ticketInfoList) {
        this.ticketInfoList = ticketInfoList;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public BigDecimal getRefundMoney() {
        return refundMoney;
    }

    public void setRefundMoney(BigDecimal refundMoney) {
        this.refundMoney = refundMoney;
    }
}
