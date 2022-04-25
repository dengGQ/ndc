package com.ndc.channel.common;

public class TableName {
    public static final String NDC_FLIGHT_API_REFUND_ORDER_REL = "ndc_flight_api_refund_order_rel";
    /**
     * 需要在REDIS中生成主键的表
     */
    public static final String[] TABLES = new String[]{
            NDC_FLIGHT_API_REFUND_ORDER_REL
    };
}
