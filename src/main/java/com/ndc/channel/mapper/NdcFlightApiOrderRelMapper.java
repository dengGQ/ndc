package com.ndc.channel.mapper;


import com.ndc.channel.entity.NdcFlightApiOrderRel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface NdcFlightApiOrderRelMapper {
    int deleteByPrimaryKey(Long relId);

    int insert(NdcFlightApiOrderRel record);

    int insertSelective(NdcFlightApiOrderRel record);

    NdcFlightApiOrderRel selectByPrimaryKey(Long relId);

    int updateByPrimaryKeySelective(NdcFlightApiOrderRel record);

    int updateByPrimaryKey(NdcFlightApiOrderRel record);

    @Select("select * from ndc_flight_api_order_rel where order_id = #{orderId}")
    NdcFlightApiOrderRel selectByOrderId(@Param("orderId") String orderId);
}