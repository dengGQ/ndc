package com.ndc.channel.flight.dto.refund;

import java.math.BigDecimal;

public class RefundChangeMoneyQueryResp {

    /**
     * 渠道收取改签费
     */
    private BigDecimal changeFee;
    /**
     * 渠道收取退票费
     */
    private BigDecimal refundFee;

    /**
     * 退款总金额
     */
    private BigDecimal refundMoney;

    public BigDecimal getChangeFee() {
        return changeFee;
    }

    public void setChangeFee(BigDecimal changeFee) {
        this.changeFee = changeFee;
    }

    public BigDecimal getRefundFee() {
        return refundFee;
    }

    public void setRefundFee(BigDecimal refundFee) {
        this.refundFee = refundFee;
    }

    public BigDecimal getRefundMoney() {
        return refundMoney;
    }

    public void setRefundMoney(BigDecimal refundMoney) {
        this.refundMoney = refundMoney;
    }
}
