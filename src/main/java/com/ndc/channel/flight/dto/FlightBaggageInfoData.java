package com.ndc.channel.flight.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "行李额信息")
public class FlightBaggageInfoData {

    @ApiModelProperty(value = "航司代码")
    private String airlineCode;

    @ApiModelProperty(value = "仓位代码")
    private String classCode;

    @ApiModelProperty(value = "行李额信息")
    private String baggageInfo;

    @ApiModelProperty(value = "免费行李额重量，单位kg")
    private String freeBaggageWeight;

    @ApiModelProperty(value = "免费行李额数量，单位件")
    private String freeBaggageAmount;

    @ApiModelProperty(value = "免费行李额体积，单位cm")
    private String freeBaggageVolume;

    @ApiModelProperty(value = "随身行李额重量，单位kg")
    private String carryOnBaggageWeight;

    @ApiModelProperty(value = "随身行李数量，单位件")
    private String carryOnBaggageAmount;

    @ApiModelProperty(value = "随身行李额体积，单位cm")
    private String carryOnBaggageVolume;

    public String getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getBaggageInfo() {
        return baggageInfo;
    }

    public void setBaggageInfo(String baggageInfo) {
        this.baggageInfo = baggageInfo;
    }

    public String getFreeBaggageWeight() {
        return freeBaggageWeight;
    }

    public void setFreeBaggageWeight(String freeBaggageWeight) {
        this.freeBaggageWeight = freeBaggageWeight;
    }

    public String getFreeBaggageAmount() {
        return freeBaggageAmount;
    }

    public void setFreeBaggageAmount(String freeBaggageAmount) {
        this.freeBaggageAmount = freeBaggageAmount;
    }

    public String getFreeBaggageVolume() {
        return freeBaggageVolume;
    }

    public void setFreeBaggageVolume(String freeBaggageVolume) {
        this.freeBaggageVolume = freeBaggageVolume;
    }

    public String getCarryOnBaggageWeight() {
        return carryOnBaggageWeight;
    }

    public void setCarryOnBaggageWeight(String carryOnBaggageWeight) {
        this.carryOnBaggageWeight = carryOnBaggageWeight;
    }

    public String getCarryOnBaggageAmount() {
        return carryOnBaggageAmount;
    }

    public void setCarryOnBaggageAmount(String carryOnBaggageAmount) {
        this.carryOnBaggageAmount = carryOnBaggageAmount;
    }

    public String getCarryOnBaggageVolume() {
        return carryOnBaggageVolume;
    }

    public void setCarryOnBaggageVolume(String carryOnBaggageVolume) {
        this.carryOnBaggageVolume = carryOnBaggageVolume;
    }

}