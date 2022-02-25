package com.ndc.channel.flight.dto.createOrder;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel("创建订单请求参数")
public class FlightOrderCreateReq {

	@ApiModelProperty(value = "出票回调地址", required = true)
	private String afterTicketUrl;

	@ApiModelProperty(value = "外部订单号", required = true)
	private String externalOrderNumber;

	@ApiModelProperty(value = "航程类型  1-单程，2-往返程 ", required = true)
	private Byte flightType;

	@ApiModelProperty(value = "联系人信息", required = true)
	private List<OrderContactParams> contacts;

	@ApiModelProperty(value = "乘客信息", required = true)
	private List<CorpApiOrderPassengerParams> passengers;

	@ApiModelProperty(value = "航班信息", required = true)
	private List<CorpApiOrderFlightTicketParams> tickets;

	public String getAfterTicketUrl() {
		return afterTicketUrl;
	}

	public void setAfterTicketUrl(String afterTicketUrl) {
		this.afterTicketUrl = afterTicketUrl;
	}

	public String getExternalOrderNumber() {
		return externalOrderNumber;
	}

	public void setExternalOrderNumber(String externalOrderNumber) {
		this.externalOrderNumber = externalOrderNumber;
	}

	public Byte getFlightType() {
		return flightType;
	}

	public void setFlightType(Byte flightType) {
		this.flightType = flightType;
	}

	public List<OrderContactParams> getContacts() {
		return contacts;
	}

	public void setContacts(List<OrderContactParams> contacts) {
		this.contacts = contacts;
	}

	public List<CorpApiOrderPassengerParams> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<CorpApiOrderPassengerParams> passengers) {
		this.passengers = passengers;
	}

	public List<CorpApiOrderFlightTicketParams> getTickets() {
		return tickets;
	}

	public void setTickets(List<CorpApiOrderFlightTicketParams> tickets) {
		this.tickets = tickets;
	}

}
