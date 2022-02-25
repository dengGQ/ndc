package com.ndc.channel.util;

import com.ndc.channel.model.FlightIdInfo;
import com.ndc.channel.model.TicketIdInfo;
import org.apache.commons.lang3.StringUtils;

public class FlightKeyUtils {

    // 2021-08-2708001015MU5102PEKSHA
    public static FlightIdInfo processFlightId(String flightId) {
        FlightIdInfo flightIdInfo = new FlightIdInfo();

        flightIdInfo.setFlightId(flightId);
        flightIdInfo.setFlightNumber(flightId.substring(18, 24));
        flightIdInfo.setAirlineCode(flightIdInfo.getFlightNumber().substring(0, 2));
        flightIdInfo.setFlightDate(flightId.substring(0, 10));
        flightIdInfo.setDepartureTime(flightId.substring(10, 14));
        flightIdInfo.setDestinationTime(flightId.substring(14, 18));
        flightIdInfo.setDepartureAirportCode(flightId.substring(24, 27));
        flightIdInfo.setDestinationAirportCode(flightId.substring(27));

        return flightIdInfo;
    }

    /**
     *
     * @param flightId {@link #getFlightId(String, String, String, String, String, String)}
     * @param ticketId {@link #getTicketId(Long, String, String, String)}
     * @return
     */
    public static TicketIdInfo processTicketId(String flightId, String ticketId) {
        TicketIdInfo ticketIdInfo = new TicketIdInfo();

        ticketIdInfo.setChannelId(Long.valueOf(ticketId.split("-")[0]));
        ticketIdInfo.setProductType(ticketId.substring(ticketId.length()-1));

        String str = ticketId.split(flightId)[1];
        ticketIdInfo.setSeatClassCode(str.substring(0, str.length()-1));

        return ticketIdInfo;
    }

    /**
     * 生成flightId
     *
     * @param flightDate   航班日期
     * @param depTime      出发时间
     * @param arrTime      到达时间
     * @param flightNumber 航班号
     * @param fromAirport  出发机场
     * @param toAirport    到达机场
     * @return
     */
    public static String getFlightId(String flightDate, String depTime, String arrTime, String flightNumber,
                                     String fromAirport, String toAirport) {
        return new StringBuffer().append(flightDate).append(depTime).append(arrTime).append(flightNumber)
                .append(fromAirport).append(toAirport).toString();
    }

    /**
     * 生成ticketId
     *
     * @param flightId
     * @param seatClassCode 舱位代码
     * @return
     */
    public static String getTicketId(String flightId, String productType, String seatClassCode, String offerItem) {
        return new StringBuffer().append(flightId).append(seatClassCode).append(productType).append("@").append(offerItem).toString();
    }
}
