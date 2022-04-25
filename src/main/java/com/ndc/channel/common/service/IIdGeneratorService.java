package com.ndc.channel.common.service;

/**
 * 主键服务
 *
 * @author chenlei
 */
public interface IIdGeneratorService {

    /**
     * 表主键缓存入redis，若表已存在，则跳过；不存在，则写入
     */
    void initRedisPrimaryKey();

    /**
     * 获取主键
     *
     * @param tablename
     * @return
     */
    Long getPrimaryKey(String tablename);

    /**
     * 回退redis存储的主键值
     *
     * @param tableName
     * @return
     */
    Long rollbackPrimaryKey(String tableName);

}
