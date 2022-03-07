package com.ndc.channel.mapper;

import com.ndc.channel.entity.NdcAccountInfo;
import com.ndc.channel.model.NdcAccountInfoData;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface NdcAccountInfoMapper {
    int deleteByPrimaryKey(Long accountId);

    int insert(NdcAccountInfo record);

    int insertSelective(NdcAccountInfo record);

    NdcAccountInfo selectByPrimaryKey(Long accountId);

    int updateByPrimaryKeySelective(NdcAccountInfo record);

    int updateByPrimaryKey(NdcAccountInfo record);

    @Select("select * from ndc_account_info where account_code = #{ndcCode}")
    NdcAccountInfoData selectByNdcCode(@Param("ndcCode") String ndcCode);
}