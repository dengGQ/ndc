package com.ndc.channel.flight.dto.verifyPrice;

import io.swagger.annotations.ApiModel;

import java.math.BigDecimal;

/**
 * @author dingyajun
 * @date 2018/5/7
 */
@ApiModel("国内机票验价返回参数")
public class CorpApiFlightVerifyPriceData {

	private boolean isSuccess;

	private String msg;

	private String flightNumber;
	
	private String seatClassCode;
	
	private BigDecimal purchasePrice;

	private BigDecimal ticketPrice;
	
	private BigDecimal buildFee;
	
	private BigDecimal oilFee;

	private String seatCount;

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getSeatClassCode() {
		return seatClassCode;
	}

	public void setSeatClassCode(String seatClassCode) {
		this.seatClassCode = seatClassCode;
	}

	public BigDecimal getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(BigDecimal purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public BigDecimal getBuildFee() {
		return buildFee;
	}

	public void setBuildFee(BigDecimal buildFee) {
		this.buildFee = buildFee;
	}

	public BigDecimal getOilFee() {
		return oilFee;
	}

	public void setOilFee(BigDecimal oilFee) {
		this.oilFee = oilFee;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean success) {
		isSuccess = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public BigDecimal getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(BigDecimal ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public String getSeatCount() {
		return seatCount;
	}

	public void setSeatCount(String seatCount) {
		this.seatCount = seatCount;
	}
}
