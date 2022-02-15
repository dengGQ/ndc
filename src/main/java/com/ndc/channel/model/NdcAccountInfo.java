package com.ndc.channel.model;

import com.ndc.channel.enumtype.BusinessEnum;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class NdcAccountInfo {

    private String path;
    private String timeStamp; //LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")));
    private String requestId;
    private String merCode; //BOP-2021122034800");
    private String chnlCode;//1462");
    private String authorization; //eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzY29wZSI6WyJub3JtYWwiXSwiYXV0aG9yaXRpZXMiOlsiMTQ2MiJdLCJqdGkiOiI4OTY1Njk5YS1hMTNmLTQ5Y2UtYmQ5OC1mZDA5MWY0ODMxNWUiLCJjbGllbnRfaWQiOiIxNDYyIn0.fi5LlLjc2SEdil_sfskJasD1Hwr82FT-vogYL2IuTOdl1T8TaE5yAimNZTJOgo1myk-R-5Ipy4cSJekTr_7BbnRQiM_J0Nzx3urQFmc7MSRJnvJLmzvBD5E4ZanzHqAiRfPrC_yyX-CjaXTXW5Cg6tnAEf5ShB-kovP0CCTwGO7WIJByvXRigfI7YOqWPuhcQxUAoBaAYtgit35AcG3daW7x0LPRnGNgBeQTfXQGSpoVU9GYN-2bRlm1ql2L3IxVjtGueOMwxiMkPou0ys9k8rEBEvy5QjwI0SnwYakLognNNqtIYp0ewR4tVjyE0dECwUQWnrpkMuxgLseeuSaCsQ");
    private String apiCode; //A0534");

    public NdcAccountInfo(BusinessEnum.NdcApiInfo ndcApiInfo) {
        this.path = ndcApiInfo.getPath();
        this.timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
        this.requestId = UUID.randomUUID().toString();
        this.apiCode = ndcApiInfo.getCode();
    }

    public NdcAccountInfo(String path, String merCode, String chnlCode, String authorization, String apiCode) {
        this.path = path;
        this.timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
        this.requestId = UUID.randomUUID().toString();
        this.merCode = merCode;
        this.chnlCode = chnlCode;
        this.authorization = authorization;
        this.apiCode = apiCode;
    }

    public NdcAccountInfo() {
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getMerCode() {
        return merCode;
    }

    public void setMerCode(String merCode) {
        this.merCode = merCode;
    }

    public String getChnlCode() {
        return chnlCode;
    }

    public void setChnlCode(String chnlCode) {
        this.chnlCode = chnlCode;
    }

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }

    public String getApiCode() {
        return apiCode;
    }

    public void setApiCode(String apiCode) {
        this.apiCode = apiCode;
    }
}
