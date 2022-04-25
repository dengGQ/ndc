package com.ndc.channel.flight.dto.orderDetail;

public class NdcOrderSearchParams {

    private String orderId;

    public NdcOrderSearchParams(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
