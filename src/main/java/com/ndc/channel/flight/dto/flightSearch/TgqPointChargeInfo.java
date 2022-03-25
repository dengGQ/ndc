package com.ndc.channel.flight.dto.flightSearch;

import java.math.BigDecimal;

/**
 * 退改签tgqPointCharges节点
 * @auther dingyajun
 * @date 2018/4/28
 */
public class TgqPointChargeInfo {

    private Integer time;

    private String timeText;

    private BigDecimal returnFee;

    private BigDecimal changeFee;

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getTimeText() {
        return timeText;
    }

    public void setTimeText(String timeText) {
        this.timeText = timeText;
    }

    public BigDecimal getReturnFee() {
        return returnFee;
    }

    public void setReturnFee(BigDecimal returnFee) {
        this.returnFee = returnFee;
    }

    public BigDecimal getChangeFee() {
        return changeFee;
    }

    public void setChangeFee(BigDecimal changeFee) {
        this.changeFee = changeFee;
    }
}
