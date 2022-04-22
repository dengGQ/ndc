package com.ndc.channel.flight.dto.refund;

import io.swagger.annotations.ApiModelProperty;

public class RefundApplyPassengerParams {

    @ApiModelProperty(value = "乘机人编号", required = false, hidden = true)
    private Long orderPassengerId;

    @ApiModelProperty(value = "乘机人姓名", required = true)
    private String passengerName;

    @ApiModelProperty(value = "乘机人证件", required = true)
    private String idcardCode;

    @ApiModelProperty(value = "票号", required = false, hidden = true)
    private String ticketNumber;

    @ApiModelProperty(value = "乘机人证件类型", required = true)
    private String idcardType;

    @ApiModelProperty(value = "重购客票票号，refundWay为6必填", required = false)
    private String repeatTicketNumber;

    public Long getOrderPassengerId() {
        return orderPassengerId;
    }

    public void setOrderPassengerId(Long orderPassengerId) {
        this.orderPassengerId = orderPassengerId;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getIdcardCode() {
        return idcardCode;
    }

    public void setIdcardCode(String idcardCode) {
        this.idcardCode = idcardCode;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public String getIdcardType() {
        return idcardType;
    }

    public void setIdcardType(String idcardType) {
        this.idcardType = idcardType;
    }

    public String getRepeatTicketNumber() {
        return repeatTicketNumber;
    }

    public void setRepeatTicketNumber(String repeatTicketNumber) {
        this.repeatTicketNumber = repeatTicketNumber;
    }
}
