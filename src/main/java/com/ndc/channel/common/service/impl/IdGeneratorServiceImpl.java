package com.ndc.channel.common.service.impl;

import com.ndc.channel.common.TableName;
import com.ndc.channel.common.service.IIdGeneratorService;
import com.ndc.channel.exception.BusinessException;
import com.ndc.channel.exception.BusinessExceptionCode;
import com.ndc.channel.redis.RedisUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 主键生成器
 * @author chenlei
 *
 */
@Service
public class IdGeneratorServiceImpl implements IIdGeneratorService {
	
	private static Logger logger = LoggerFactory.getLogger(IdGeneratorServiceImpl.class);
	
	@Resource
	private RedisUtils redisClientTemplate;
	/**
	 * 表主键
	 */
	public static final String TABLE_PRIMARY_KEY = "TABLE_PRIMARY_KEY";

	/**
	 * 表主键缓存入redis，若表已存在，则跳过；不存在，则写入
	 */
	@Override
    public void initRedisPrimaryKey(){
		for(String tablename: TableName.TABLES){
			redisClientTemplate.hsetnx(TABLE_PRIMARY_KEY, tablename, "50");
		}
	}
	
	/**
	 * 获取主键
	 * @param tablename
	 * @return
	 */
	@Override
    public Long getPrimaryKey(String tablename){

		String value = redisClientTemplate.hGet(TABLE_PRIMARY_KEY, tablename);

		if(StringUtils.isEmpty(value)){
			logger.error("未找到表主键{}",tablename);
			throw new BusinessException(BusinessExceptionCode.SYSTEM_ERROR);
		}
		Long id = redisClientTemplate.hincr(TABLE_PRIMARY_KEY, tablename, 1l);

		return id;
	}

	/**
	 * 如果不能保证整个操作的原子性，不要用此方法
	 * 单元测试可用
	 * @param tableName
	 * @return
	 */
	@Override
	public Long rollbackPrimaryKey(String tableName) {
		return redisClientTemplate.hincr(TABLE_PRIMARY_KEY, tableName, -1l);
	}

}
