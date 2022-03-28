package com.ndc.channel.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Document(collection = "ndc_channel_api_log")
public class NdcChannelApiLog implements Serializable {

    private String requestId;

    private String ndcAccountCode;

    private String ndcAccountName;

    private Byte type;

    private String url;

    private Date requestTime;

    private Byte status;

    private String methodName;

    private Long duration;

    private String params;

    private String result;

    private static final long serialVersionUID = 1L;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getNdcAccountCode() {
        return ndcAccountCode;
    }

    public void setNdcAccountCode(String ndcAccountCode) {
        this.ndcAccountCode = ndcAccountCode;
    }

    public String getNdcAccountName() {
        return ndcAccountName;
    }

    public void setNdcAccountName(String ndcAccountName) {
        this.ndcAccountName = ndcAccountName;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", ndcAccountCode=").append(ndcAccountCode);
        sb.append(", ndcAccountName=").append(ndcAccountName);
        sb.append(", type=").append(type);
        sb.append(", url=").append(url);
        sb.append(", requestTime=").append(requestTime);
        sb.append(", status=").append(status);
        sb.append(", methodName=").append(methodName);
        sb.append(", duration=").append(duration);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}