package com.ndc.channel.flight.dto.flightSearch;

import io.swagger.annotations.ApiModelProperty;

public class FlightStopOver {

	private static final long serialVersionUID = 7404645126138831416L;
	@ApiModelProperty(value = "序号", required = false)
	private String step;

	@ApiModelProperty(value = "经停城市代码", required = false)
	private String stopCityCode;

	@ApiModelProperty(value = "经停城市名称", required = false)
	private String stopCityName;

	@ApiModelProperty(value = "停留开始时间", required = false)
	private String stopStartTime;

	@ApiModelProperty(value = "停留结束时间", required = false)
	private String stopEndTime;

	@ApiModelProperty(value = "停留时长", required = false)
	private String stopTime;

	public String getStep() {
		return step;
	}

	public void setStep(String step) {
		this.step = step;
	}

	public String getStopCityCode() {
		return stopCityCode;
	}

	public void setStopCityCode(String stopCityCode) {
		this.stopCityCode = stopCityCode;
	}

	public String getStopCityName() {
		return stopCityName;
	}

	public void setStopCityName(String stopCityName) {
		this.stopCityName = stopCityName;
	}

	public String getStopStartTime() {
		return stopStartTime;
	}

	public void setStopStartTime(String stopStartTime) {
		this.stopStartTime = stopStartTime;
	}

	public String getStopEndTime() {
		return stopEndTime;
	}

	public void setStopEndTime(String stopEndTime) {
		this.stopEndTime = stopEndTime;
	}

	public String getStopTime() {
		return stopTime;
	}

	public void setStopTime(String stopTime) {
		this.stopTime = stopTime;
	}

}
