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
}
