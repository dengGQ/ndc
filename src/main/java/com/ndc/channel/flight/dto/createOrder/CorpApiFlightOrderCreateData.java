package com.ndc.channel.flight.dto.createOrder;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

public class CorpApiFlightOrderCreateData {

	@ApiModelProperty("订单号")
	private String orderNumber;

	@ApiModelProperty("PNR有效时间")
	private String tktl;

	@ApiModelProperty("pnr")
	private String pnrNo;

	private String orderItemId;

	@ApiModelProperty("订单结算金额")
	private BigDecimal settlementMoney;

	@ApiModelProperty("票价")
	private BigDecimal ticketPrice;

	private String ownerCode;

	private String ownerTypeCode;

	private String requestId;

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getTktl() {
		return tktl;
	}

	public void setTktl(String tktl) {
		this.tktl = tktl;
	}

	public String getPnrNo() {
		return pnrNo;
	}

	public void setPnrNo(String pnrNo) {
		this.pnrNo = pnrNo;
	}

	public String getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(String orderItemId) {
		this.orderItemId = orderItemId;
	}

	public BigDecimal getSettlementMoney() {
		return settlementMoney;
	}

	public void setSettlementMoney(BigDecimal settlementMoney) {
		this.settlementMoney = settlementMoney;
	}

	public BigDecimal getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(BigDecimal ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public String getOwnerCode() {
		return ownerCode;
	}

	public void setOwnerCode(String ownerCode) {
		this.ownerCode = ownerCode;
	}

	public String getOwnerTypeCode() {
		return ownerTypeCode;
	}

	public void setOwnerTypeCode(String ownerTypeCode) {
		this.ownerTypeCode = ownerTypeCode;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
}
