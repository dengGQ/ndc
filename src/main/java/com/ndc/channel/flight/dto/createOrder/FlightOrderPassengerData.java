package com.ndc.channel.flight.dto.createOrder;

import io.swagger.annotations.ApiModelProperty;

public class FlightOrderPassengerData {

    @ApiModelProperty(value = "乘机人姓名", required = true)
    private String name;

    @ApiModelProperty(value = "乘机人证件号码", required = true)
    private String idcardCode;

    @ApiModelProperty(value = "票号", required = true)
    private String ticketNumber;

    @ApiModelProperty(value = "pnr", required = true)
    private String pnr;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getPnr() {
        return pnr;
    }

    public void setPnr(String pnr) {
        this.pnr = pnr;
    }
}
