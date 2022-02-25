package com.ndc.channel.flight.dto.createOrder;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CorpApiFlightOrderCreateData {

	@ApiModelProperty("订单号")
	private String orderNumber;

	@ApiModelProperty("PNR有效时间")
	private String tktl;

	@ApiModelProperty("pnr")
	private String pnrNo;

	public CorpApiFlightOrderCreateData() {
	}

	public CorpApiFlightOrderCreateData(String orderNumber, String tktl, String pnrNo) {
		this.orderNumber = orderNumber;
		this.tktl = tktl;
		this.pnrNo = pnrNo;
	}
}
