package com.ndc.channel.flight.dto.createOrder;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class FlightOrderNoticeData {

	@ApiModelProperty("订单号")
	private String orderNumber;

	@ApiModelProperty("外部订单号")
	private String externalOrderNumber;

	@ApiModelProperty("是否出票成功")
	private Boolean isSuccess;

	@ApiModelProperty("pnr，成功有值")
	private String pnr;

	@ApiModelProperty("乘客信息，成功有值")
	private List<FlightOrderPassengerData> passengerDatas;

	@ApiModelProperty("失败原因")
	private String message;

	public FlightOrderNoticeData() {
	}

	public FlightOrderNoticeData(String orderNumber, String externalOrderNumber, Boolean isSuccess, String pnr, List<FlightOrderPassengerData> passengerDatas) {
		this.orderNumber = orderNumber;
		this.externalOrderNumber = externalOrderNumber;
		this.isSuccess = isSuccess;
		this.pnr = pnr;
		this.passengerDatas = passengerDatas;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getExternalOrderNumber() {
		return externalOrderNumber;
	}

	public void setExternalOrderNumber(String externalOrderNumber) {
		this.externalOrderNumber = externalOrderNumber;
	}

	public Boolean getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(Boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getPnr() {
		return pnr;
	}

	public void setPnr(String pnr) {
		this.pnr = pnr;
	}

	public List<FlightOrderPassengerData> getPassengerDatas() {
		return passengerDatas;
	}

	public void setPassengerDatas(List<FlightOrderPassengerData> passengerDatas) {
		this.passengerDatas = passengerDatas;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
