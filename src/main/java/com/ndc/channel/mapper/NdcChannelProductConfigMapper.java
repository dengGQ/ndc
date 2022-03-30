package com.ndc.channel.mapper;

import com.ndc.channel.entity.NdcChannelProductConfig;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface NdcChannelProductConfigMapper {
    int deleteByPrimaryKey(Long productConfigId);

    int insert(NdcChannelProductConfig record);

    int insertSelective(NdcChannelProductConfig record);

    NdcChannelProductConfig selectByPrimaryKey(Long productConfigId);

    int updateByPrimaryKeySelective(NdcChannelProductConfig record);

    int updateByPrimaryKey(NdcChannelProductConfig record);

    @Select("select * from ndc_channel_product_config where enable_status = 1 and ndc_account_code = #{ndcAccountCode}")
    List<NdcChannelProductConfig> queryEnableProductByNdcAccountCode(@Param("ndcAccountCode") String ndcAccountCode);
}