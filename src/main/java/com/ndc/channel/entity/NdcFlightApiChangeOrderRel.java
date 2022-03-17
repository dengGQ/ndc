package com.ndc.channel.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class NdcFlightApiChangeOrderRel implements Serializable {
    private Long relId;

    private String orderId;

    private String orderItemId;

    private String changeId;

    private String ownerCode;

    private String ownerTypeCode;

    private String externalChangeNumber;

    private String afterEndorseUrl;

    private BigDecimal upgradeTotalAmount;

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

    public String getChangeId() {
        return changeId;
    }

    public void setChangeId(String changeId) {
        this.changeId = changeId;
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

    public String getExternalChangeNumber() {
        return externalChangeNumber;
    }

    public void setExternalChangeNumber(String externalChangeNumber) {
        this.externalChangeNumber = externalChangeNumber;
    }

    public String getAfterEndorseUrl() {
        return afterEndorseUrl;
    }

    public void setAfterEndorseUrl(String afterEndorseUrl) {
        this.afterEndorseUrl = afterEndorseUrl;
    }

    public BigDecimal getUpgradeTotalAmount() {
        return upgradeTotalAmount;
    }

    public void setUpgradeTotalAmount(BigDecimal upgradeTotalAmount) {
        this.upgradeTotalAmount = upgradeTotalAmount;
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
        sb.append(", changeId=").append(changeId);
        sb.append(", ownerCode=").append(ownerCode);
        sb.append(", ownerTypeCode=").append(ownerTypeCode);
        sb.append(", externalChangeNumber=").append(externalChangeNumber);
        sb.append(", afterEndorseUrl=").append(afterEndorseUrl);
        sb.append(", upgradeTotalAmount=").append(upgradeTotalAmount);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}