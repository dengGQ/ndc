package com.ndc.channel.flight.dto.changeBooking;

public class ChangeBookingRespData {

    /**
     * 退票返回 1-成功；2-失败
     */
    private String result;

    /**
     * 详情 如失败原因等
     */
    private String resultInfo;

    /**
     * 改签订单号
     */
    private String channelRefundNumber;


    public ChangeBookingRespData() {
        this.result = "1";
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResultInfo() {
        return resultInfo;
    }

    public void setResultInfo(String resultInfo) {
        this.resultInfo = resultInfo;
    }

    public String getChannelRefundNumber() {
        return channelRefundNumber;
    }

    public void setChannelRefundNumber(String channelRefundNumber) {
        this.channelRefundNumber = channelRefundNumber;
    }
}
