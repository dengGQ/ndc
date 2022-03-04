package com.ndc.channel.flight.dto.orderDetail;

import java.util.List;

public class NdcOrderDetailData {

    private String channelOrderNumber;

    private String channelGroupOrderNumber;

    private List<OrderTicketInfo> ticketInfoList;

    private String orderStatus;

    public String getChannelOrderNumber() {
        return channelOrderNumber;
    }

    public void setChannelOrderNumber(String channelOrderNumber) {
        this.channelOrderNumber = channelOrderNumber;
    }

    public String getChannelGroupOrderNumber() {
        return channelGroupOrderNumber;
    }

    public void setChannelGroupOrderNumber(String channelGroupOrderNumber) {
        this.channelGroupOrderNumber = channelGroupOrderNumber;
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
}
