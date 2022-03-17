package com.ndc.channel.mapper;

import com.ndc.channel.entity.NdcFlightApiChangeOrderRel;

public interface NdcFlightApiChangeOrderRelMapper {
    int deleteByPrimaryKey(Long relId);

    int insert(NdcFlightApiChangeOrderRel record);

    int insertSelective(NdcFlightApiChangeOrderRel record);

    NdcFlightApiChangeOrderRel selectByPrimaryKey(Long relId);

    int updateByPrimaryKeySelective(NdcFlightApiChangeOrderRel record);

    int updateByPrimaryKey(NdcFlightApiChangeOrderRel record);
}