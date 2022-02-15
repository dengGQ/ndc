package com.ndc.channel.flight.dto.flightSearch;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 退改签政策
 * 
 * @author chenlei
 *
 */
@Data
public class CorpApiTicketPolicy {

	@ApiModelProperty(value = "行李额", required = false)
	private String baggageInfo;

	@ApiModelProperty(value = "改签政策", required = false)
	private String changePolicy;

	@ApiModelProperty(value = "改签时间点", required = false)
	private String changeTimePoint;

	@ApiModelProperty(value = "退票政策", required = false)
	private String refundPolicy;

	@ApiModelProperty(value = "退票时间点", required = false)
	private String refundTimePoint;

	@ApiModelProperty(value = "行李额信息")
	private FlightBaggageInfoData flightBaggageInfoData;
}