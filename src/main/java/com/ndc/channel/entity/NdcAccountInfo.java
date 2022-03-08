package com.ndc.channel.entity;

import com.ndc.channel.enumtype.BusinessEnum;
import org.springframework.data.annotation.Transient;

import java.io.Serializable;
import java.util.Date;

public class NdcAccountInfo implements Serializable {
    private Long accountId;

    private String accountCode;

    private String accountName;

    private String apiUrl;

    private String bankAccountId;

    private String params;

    private Date createTime;

    private BusinessEnum.NdcApiInfo ndcApiInfo;

    private static final long serialVersionUID = 1L;

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(String bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    public BusinessEnum.NdcApiInfo getNdcApiInfo() {
        return ndcApiInfo;
    }

    public void setNdcApiInfo(BusinessEnum.NdcApiInfo ndcApiInfo) {
        this.ndcApiInfo = ndcApiInfo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", accountId=").append(accountId);
        sb.append(", accountCode=").append(accountCode);
        sb.append(", accountName=").append(accountName);
        sb.append(", apiUrl=").append(apiUrl);
        sb.append(", params=").append(params);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}