package com.ndc.channel.flight.dto.refund;

import com.ndc.channel.flight.dto.createOrder.FlightOrderPassengerData;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

public class FlightRefundOrderNoticeData {
    @ApiModelProperty("退票单号")
    private String refundNumber;

    @ApiModelProperty("外部退票单号")
    private String externalRefundNumber;

    @ApiModelProperty("是否退票成功")
    private Boolean isSuccess;

    @ApiModelProperty("乘客信息，成功有值")
    private List<FlightOrderPassengerData> passengerDatas;

    @ApiModelProperty("退款总金额")
    private BigDecimal refundMoney;

    public String getRefundNumber() {
        return refundNumber;
    }

    public void setRefundNumber(String refundNumber) {
        this.refundNumber = refundNumber;
    }

    public String getExternalRefundNumber() {
        return externalRefundNumber;
    }

    public void setExternalRefundNumber(String externalRefundNumber) {
        this.externalRefundNumber = externalRefundNumber;
    }

    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    public List<FlightOrderPassengerData> getPassengerDatas() {
        return passengerDatas;
    }

    public void setPassengerDatas(List<FlightOrderPassengerData> passengerDatas) {
        this.passengerDatas = passengerDatas;
    }

    public BigDecimal getRefundMoney() {
        return refundMoney;
    }

    public void setRefundMoney(BigDecimal refundMoney) {
        this.refundMoney = refundMoney;
    }
}
