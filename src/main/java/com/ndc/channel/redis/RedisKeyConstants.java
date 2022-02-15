package com.ndc.channel.redis;

public class RedisKeyConstants {

	public static String NDC_FLIGHT_ID_ = "NDC_FLIGHT_ID_";

	public static String NDC_FLIGHT_TICKET_ID_ = "NDC_FLIGHT_TICKET_ID_";

	public static String getRedisFlightDataCacheKey(String flightId) {
		return new StringBuilder(RedisKeyConstants.NDC_FLIGHT_ID_).append(flightId).toString();
	}

	public static String getRedisTicketDataCacheKey(String flightId) {
		return new StringBuilder(RedisKeyConstants.NDC_FLIGHT_TICKET_ID_).append(flightId).toString();
	}
}
