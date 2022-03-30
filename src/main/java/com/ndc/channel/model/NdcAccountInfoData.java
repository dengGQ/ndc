package com.ndc.channel.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ndc.channel.entity.NdcAccountInfo;
import com.ndc.channel.enumtype.BusinessEnum;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class NdcAccountInfoData {

    private String accountCode;

    private String accountName;

    private String merCode;
    private String chnlCode;
    private String authorization;

    private String apiUrl;

    private String params;

    private String bankAccountID;

    private JSONObject jsonParams;

    private BusinessEnum.NdcApiInfo ndcApiInfo;

    private String requestId;

    public String getApiUrl() {
        return apiUrl;
    }

    public BusinessEnum.NdcApiInfo getNdcApiInfo() {
        return ndcApiInfo;
    }

    public void setNdcApiInfo(BusinessEnum.NdcApiInfo ndcApiInfo) {
        this.ndcApiInfo = ndcApiInfo;
    }

    public String getMerCode() {
        if (jsonParams == null && params != null) {
            jsonParams = JSONObject.parseObject(params);
        }
        return jsonParams.getString("merCode");
    }


    public String getChnlCode() {
        if (jsonParams == null && params != null) {
            jsonParams = JSONObject.parseObject(params);
        }
        return jsonParams.getString("chnlCode");
    }


    public String getAuthorization() {
        if (jsonParams == null && params != null) {
            jsonParams = JSONObject.parseObject(params);
        }
        return jsonParams.getString("Authorization");
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getBankAccountID() {
        return bankAccountID;
    }

    public void setBankAccountID(String bankAccountID) {
        this.bankAccountID = bankAccountID;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
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
}
