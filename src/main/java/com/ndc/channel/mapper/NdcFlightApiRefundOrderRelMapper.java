package com.ndc.channel.mapper;

import com.ndc.channel.entity.NdcFlightApiOrderRel;
import com.ndc.channel.entity.NdcFlightApiRefundOrderRel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface NdcFlightApiRefundOrderRelMapper {
    int deleteByPrimaryKey(Integer relId);

    int insert(NdcFlightApiRefundOrderRel record);

    int insertSelective(NdcFlightApiRefundOrderRel record);

    NdcFlightApiRefundOrderRel selectByPrimaryKey(Integer relId);

    int updateByPrimaryKeySelective(NdcFlightApiRefundOrderRel record);

    int updateByPrimaryKey(NdcFlightApiRefundOrderRel record);

    @Select("select * from ndc_flight_api_refund_order_rel where refund_id = #{refundId}")
    NdcFlightApiRefundOrderRel selectByRefundId(@Param("refundId") String refundId);
}