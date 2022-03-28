package com.ndc.channel.flight.dto.flightSearch;

/**
 * 出发日期不能为空
 */
public class FlightSearchReq {
	/**
	 * 出发日期
	 */
	private String flightDate;

	/**
	 *出发城市
	 */
	private String fromCity;
	/**
	 *到达城市
	 */
	private String toCity;

	private String requestId;

	public String getFlightDate() {
		return flightDate;
	}

	public void setFlightDate(String flightDate) {
		this.flightDate = flightDate;
	}

	public String getFromCity() {
		return fromCity;
	}

	public void setFromCity(String fromCity) {
		this.fromCity = fromCity;
	}

	public String getToCity() {
		return toCity;
	}

	public void setToCity(String toCity) {
		this.toCity = toCity;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
}
