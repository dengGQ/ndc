package com.ndc.channel.mapper;

import com.ndc.channel.entity.NdcFlightApiRefundOrderRel;

public interface NdcFlightApiRefundOrderRelMapper {
    int deleteByPrimaryKey(Integer relId);

    int insert(NdcFlightApiRefundOrderRel record);

    int insertSelective(NdcFlightApiRefundOrderRel record);

    NdcFlightApiRefundOrderRel selectByPrimaryKey(Integer relId);

    int updateByPrimaryKeySelective(NdcFlightApiRefundOrderRel record);

    int updateByPrimaryKey(NdcFlightApiRefundOrderRel record);
}