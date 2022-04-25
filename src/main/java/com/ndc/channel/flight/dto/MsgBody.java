package com.ndc.channel.flight.dto;

public class MsgBody {

    /**
     * 业务数据主键
     */
    private Object businessData;

    /**
     * 1-预定，2-退票，3-改签
     */
    private String msgType;


    public MsgBody() {
    }

    public MsgBody(Object businessData, String msgType) {
        this.businessData = businessData;
        this.msgType = msgType;
    }

    public Object getBusinessData() {
        return businessData;
    }

    public void setBusinessData(Object businessData) {
        this.businessData = businessData;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }
}
