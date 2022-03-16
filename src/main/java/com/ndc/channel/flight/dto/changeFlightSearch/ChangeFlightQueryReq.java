package com.ndc.channel.flight.dto.changeFlightSearch;

import java.util.List;

public final class ChangeFlightQueryReq {

    private String orderNumber;

    private List<String> ticketNumberList;

    private List<String> idcardCodes;

    private String flightDate;
    /**
     * 	改期航班出发地机场三字码
     */
    private String depAirportCode;
    /**
     * 改期航班到达地机场三字码
     */
    private String arrAirportCode;

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public List<String> getTicketNumberList() {
        return ticketNumberList;
    }

    public void setTicketNumberList(List<String> ticketNumberList) {
        this.ticketNumberList = ticketNumberList;
    }

    public List<String> getIdcardCodes() {
        return idcardCodes;
    }

    public void setIdcardCodes(List<String> idcardCodes) {
        this.idcardCodes = idcardCodes;
    }

    public String getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(String flightDate) {
        this.flightDate = flightDate;
    }

    public String getDepAirportCode() {
        return depAirportCode;
    }

    public void setDepAirportCode(String depAirportCode) {
        this.depAirportCode = depAirportCode;
    }

    public String getArrAirportCode() {
        return arrAirportCode;
    }

    public void setArrAirportCode(String arrAirportCode) {
        this.arrAirportCode = arrAirportCode;
    }
}
