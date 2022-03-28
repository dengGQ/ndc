package com.ndc.channel.entity;

import java.math.BigDecimal;
import java.util.Date;

public class NdcFlightApiRefundOrderRel {

    private Long relId;

    /**
     * 原订单号
     */
    private String orderId;

    /**
     * 退票单号
     */
    private String refundId;

    /**
     * 外部退票单号
     */
    private String externalRefundNumber;

    /**
     * 退票回调通知URL
     */
    private String afterRefundTicketUrl;


    private String requestId;

    /**
     * 创建时间
     */
    private Date createTime;

    public Long getRelId() {
        return relId;
    }

    public void setRelId(Long relId) {
        this.relId = relId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getRefundId() {
        return refundId;
    }

    public void setRefundId(String refundId) {
        this.refundId = refundId;
    }

    public String getExternalRefundNumber() {
        return externalRefundNumber;
    }

    public void setExternalRefundNumber(String externalRefundNumber) {
        this.externalRefundNumber = externalRefundNumber;
    }

    public String getAfterRefundTicketUrl() {
        return afterRefundTicketUrl;
    }

    public void setAfterRefundTicketUrl(String afterRefundTicketUrl) {
        this.afterRefundTicketUrl = afterRefundTicketUrl;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}
