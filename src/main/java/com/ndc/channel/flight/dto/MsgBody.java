package com.ndc.channel.flight.dto;

public class MsgBody {

    /**
     * 业务数据主键
     */
    private String businessNumber;

    /**
     * 1-预定，2-退票，3-改签
     */
    private String msgType;


    public MsgBody() {
    }

    public MsgBody(String businessNumber, String msgType) {
        this.businessNumber = businessNumber;
        this.msgType = msgType;
    }

    public String getBusinessNumber() {
        return businessNumber;
    }

    public void setBusinessNumber(String businessNumber) {
        this.businessNumber = businessNumber;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }
}
