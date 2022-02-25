package com.ndc.channel.flight.dto.createOrder;

import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;

public class CorpApiOrderFlightTicketParams implements Serializable {

	@ApiModelProperty(value = "航班编号", required = true)
	private String flightId;

	@ApiModelProperty(value = "机票价格", required = true)
	private BigDecimal price;

	@ApiModelProperty(value = "舱位代码", required = true)
	private String seatClassCode;

	@ApiModelProperty(value = "舱位id", required = true)
	private String ticketId;

	@ApiModelProperty(value = "航程类型", required = false, hidden = true)
	private String flightType;

	@ApiModelProperty(value = "渠道返佣缓存id", required = false, hidden = true)
	private String channelInfoId;

	public String getFlightId() {
		return flightId;
	}

	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getSeatClassCode() {
		return seatClassCode;
	}

	public void setSeatClassCode(String seatClassCode) {
		this.seatClassCode = seatClassCode;
	}

	public String getTicketId() {
		return ticketId;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}

	public String getFlightType() {
		return StringUtils.isEmpty(flightType) ? "1" : flightType;
	}

	public void setFlightType(String flightType) {
		this.flightType = flightType;
	}

	public String getChannelInfoId() {
		return channelInfoId;
	}

	public void setChannelInfoId(String channelInfoId) {
		this.channelInfoId = channelInfoId;
	}
}
