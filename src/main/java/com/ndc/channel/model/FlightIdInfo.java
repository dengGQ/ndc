package com.ndc.channel.model;

import io.swagger.annotations.ApiModelProperty;

public class FlightIdInfo {

    @ApiModelProperty(value = "航班编号")
    private String flightId;

    @ApiModelProperty(value = "航司二字码")
    private String airlineCode;

    @ApiModelProperty(value = "航班号")
    private String flightNumber;

    @ApiModelProperty(value = "航班日期, yyyy-MM-dd")
    private String flightDate;

    @ApiModelProperty(value = "出发时间, HHmm")
    private String departureTime;

    @ApiModelProperty(value = "到达时间, HHmm")
    private String destinationTime;

    @ApiModelProperty(value = "出发机场三字码")
    private String departureAirportCode;//出发机场三字码

    @ApiModelProperty(value = "到达机场三字码")
    private String destinationAirportCode;//到达机场三字码

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public String getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(String flightDate) {
        this.flightDate = flightDate;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getDestinationTime() {
        return destinationTime;
    }

    public void setDestinationTime(String destinationTime) {
        this.destinationTime = destinationTime;
    }

    public String getDepartureAirportCode() {
        return departureAirportCode;
    }

    public void setDepartureAirportCode(String departureAirportCode) {
        this.departureAirportCode = departureAirportCode;
    }

    public String getDestinationAirportCode() {
        return destinationAirportCode;
    }

    public void setDestinationAirportCode(String destinationAirportCode) {
        this.destinationAirportCode = destinationAirportCode;
    }
}
