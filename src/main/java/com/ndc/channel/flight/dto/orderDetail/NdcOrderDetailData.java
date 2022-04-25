package com.ndc.channel.flight.dto.orderDetail;

import com.ndc.channel.flight.xmlBean.refundOrderDetail.response.bean.Response;

import java.math.BigDecimal;
import java.util.List;

public class NdcOrderDetailData {

    private String channelOrderNumber;

    private List<OrderTicketInfo> ticketInfoList;

    private String orderStatus;

    private BigDecimal refundMoney;

    /**
     * 退票单明细原始数据
     */
    private Response refundDetailResponse;

    private String refundId;

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

    public Response getRefundDetailResponse() {
        return refundDetailResponse;
    }

    public void setRefundDetailResponse(Response refundDetailResponse) {
        this.refundDetailResponse = refundDetailResponse;
    }

    public String getRefundId() {
        return refundId;
    }

    public void setRefundId(String refundId) {
        this.refundId = refundId;
    }
}
