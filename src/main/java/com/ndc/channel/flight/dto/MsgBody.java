package com.ndc.channel.flight.dto;

public class MsgBody {

    /**
     * 业务数据主键
     */
    private String primaryKey;

    /**
     * 1-预定，2-退票，3-改签
     */
    private String msgType;

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }
}
