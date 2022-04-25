package com.ndc.channel.flight.dto.orderDetail;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

public class OrderTicketInfo {

    private String passengerName;

    private String idCardNo;

    private String pnrCode;

    private String ticketNumber;

    private String ticketStatus;

    private String refundAuditingStatus;

    private BigDecimal refundAmount;

    private BigDecimal refundFee;

    private String paxId;

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public String getPnrCode() {
        return pnrCode;
    }

    public void setPnrCode(String pnrCode) {
        this.pnrCode = pnrCode;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public String getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(String ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public String getRefundAuditingStatus() {
        return refundAuditingStatus;
    }

    public void setRefundAuditingStatus(String refundAuditingStatus) {
        this.refundAuditingStatus = refundAuditingStatus;
    }

    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }

    public BigDecimal getRefundFee() {
        return refundFee;
    }

    public void setRefundFee(BigDecimal refundFee) {
        this.refundFee = refundFee;
    }

    public String getPaxId() {
        return paxId;
    }

    public void setPaxId(String paxId) {
        this.paxId = paxId;
    }
}
