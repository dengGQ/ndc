package com.ndc.channel.entity;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class NdcFlightApiRefundOrderRel {

    private Long relId;

    @ApiModelProperty(value = "原订单号")
    private String orderId;

    @ApiModelProperty(value = "退票单号")
    private String refundId;

    @ApiModelProperty(value = "外部退票单号", required = true)
    private String externalRefundNumber;

    @ApiModelProperty(value = "退票回调通知URL", required = true)
    private String afterRefundTicketUrl;

    @ApiModelProperty(value = "创建时间", required = true)
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
}
