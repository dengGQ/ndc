package com.ndc.channel.flight.dto.flightSearch;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

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

	/**
	 * 页面政策展示用
	 */
	@ApiModelProperty(value = "退改签政策，页面展示用")
	private List<TgqPointChargeInfo> tgqPointChargeInfoList;
}