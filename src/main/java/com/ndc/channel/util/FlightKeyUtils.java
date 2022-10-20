package com.ndc.channel.util;

import com.ndc.channel.exception.BusinessException;
import com.ndc.channel.exception.BusinessExceptionCode;

public class FlightKeyUtils {

    public static final String SEP = "|";
    public static final String SEP_REG = "\\|";


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
    @Deprecated
    public static String getFlightId(String flightDate, String depTime, String arrTime, String flightNumber,
                                     String fromAirport, String toAirport) {
        return new StringBuffer().append(flightDate).append(depTime).append(arrTime).append(flightNumber)
                .append(fromAirport).append(toAirport).toString();
    }
    // 968d9364-bd83-4fc2-a532-a680a9971cfa@MF8123
    public static String getFlightId(String shopingResponseId, String flightNumber) {
        return new StringBuffer(shopingResponseId).append(SEP).append(flightNumber).toString();
    }
    public static String getFlightIdByTicketId(String ticketId) {
        try{

            return ticketId.split(SEP_REG)[0];
        }catch (Exception e) {

            throw new BusinessException(BusinessExceptionCode.REQUEST_PARAM_ERROR, "机票ID格式错误!");
        }
    }

    /**
     * 生成ticketId
     *
     * @param flightId
     * @param seatClassCode 舱位代码
     * @return
     */
    @Deprecated
    public static String getTicketId(String flightId, String productType, String seatClassCode, String offerItem) {
        return new StringBuffer().append(flightId).append(seatClassCode).append(productType).append("@").append(offerItem).toString();
    }

    // 968d9364-bd83-4fc2-a532-a680a9971cfa|MF8123|S
    public static String getTicketId(String flightId, String seatClassCode) {
        return new StringBuffer(flightId).append(SEP).append(seatClassCode).toString();
    }
}
