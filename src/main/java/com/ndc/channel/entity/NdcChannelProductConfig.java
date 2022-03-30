package com.ndc.channel.entity;

import java.io.Serializable;
import java.util.Date;

public class NdcChannelProductConfig implements Serializable {
    private Long productConfigId;

    private String ndcAccountCode;

    private String productCode;

    private String productName;

    private String productConstraint;

    private String productRights;

    private Byte enableStatus;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Long getProductConfigId() {
        return productConfigId;
    }

    public void setProductConfigId(Long productConfigId) {
        this.productConfigId = productConfigId;
    }

    public String getNdcAccountCode() {
        return ndcAccountCode;
    }

    public void setNdcAccountCode(String ndcAccountCode) {
        this.ndcAccountCode = ndcAccountCode;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public Byte getEnableStatus() {
        return enableStatus;
    }

    public void setEnableStatus(Byte enableStatus) {
        this.enableStatus = enableStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", productConfigId=").append(productConfigId);
        sb.append(", ndcAccountCode=").append(ndcAccountCode);
        sb.append(", productCode=").append(productCode);
        sb.append(", productName=").append(productName);
        sb.append(", productConstraint=").append(productConstraint);
        sb.append(", productRights=").append(productRights);
        sb.append(", enableStatus=").append(enableStatus);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}