package com.ndc.channel.mapper;

import com.ndc.channel.entity.NdcChannelApiLog;

public interface NdcChannelApiLogMapper {
    int deleteByPrimaryKey(Long logId);

    int insert(NdcChannelApiLog record);

    int insertSelective(NdcChannelApiLog record);

    NdcChannelApiLog selectByPrimaryKey(Long logId);

    int updateByPrimaryKeySelective(NdcChannelApiLog record);

    int updateByPrimaryKey(NdcChannelApiLog record);
}