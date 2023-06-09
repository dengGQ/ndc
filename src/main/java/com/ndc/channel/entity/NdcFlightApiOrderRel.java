package com.ndc.channel.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class NdcFlightApiOrderRel implements Serializable {
    private Long relId;

    private String orderId;

    private String orderItemId;

    private String ownerCode;

    private String ownerTypeCode;

    private String externalOrderNumber;

    private String afterTicketUrl;

    private BigDecimal totalAmount;

    private BigDecimal ticketPrice;

    private String requestId;

    private String productConstraint;

    private String productRights;

    private Date createTime;

    private static final long serialVersionUID = 1L;

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

    public String getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(String orderItemId) {
        this.orderItemId = orderItemId;
    }

    public String getOwnerCode() {
        return ownerCode;
    }

    public void setOwnerCode(String ownerCode) {
        this.ownerCode = ownerCode;
    }

    public String getOwnerTypeCode() {
        return ownerTypeCode;
    }

    public void setOwnerTypeCode(String ownerTypeCode) {
        this.ownerTypeCode = ownerTypeCode;
    }

    public String getExternalOrderNumber() {
        return externalOrderNumber;
    }

    public void setExternalOrderNumber(String externalOrderNumber) {
        this.externalOrderNumber = externalOrderNumber;
    }

    public String getAfterTicketUrl() {
        return afterTicketUrl;
    }

    public void setAfterTicketUrl(String afterTicketUrl) {
        this.afterTicketUrl = afterTicketUrl;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getProductConstraint() {
        return productConstraint;
    }

    public void setProductConstraint(String productConstraint) {
        this.productConstraint = productConstraint;
    }

    public String getProductRights() {
        return productRights;
    }

    public void setProductRights(String productRights) {
        this.productRights = productRights;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", relId=").append(relId);
        sb.append(", orderId=").append(orderId);
        sb.append(", orderItemId=").append(orderItemId);
        sb.append(", ownerCode=").append(ownerCode);
        sb.append(", ownerTypeCode=").append(ownerTypeCode);
        sb.append(", externalOrderNumber=").append(externalOrderNumber);
        sb.append(", afterTicketUrl=").append(afterTicketUrl);
        sb.append(", totalAmount=").append(totalAmount);
        sb.append(", ticketPrice=").append(ticketPrice);
        sb.append(", requestId=").append(requestId);
        sb.append(", productConstraint=").append(productConstraint);
        sb.append(", productRights=").append(productRights);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}