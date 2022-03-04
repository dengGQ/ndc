package com.ndc.channel.redis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Component
public class RedisUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(RedisUtils.class);

    @Resource
    private RedisTemplate redisTemplate;

    //默认存储时间 1天
    private static final Long DEFAULT_SAVE_TIME = 1l;


    public void put(String k, Object value) {
        String v = JSONObject.toJSONString(value);
        redisTemplate.opsForValue().set(k, v, DEFAULT_SAVE_TIME, TimeUnit.DAYS);
    }

    public Long ttl(String k){
        Long    result= redisTemplate.opsForValue().getOperations().getExpire(k);
        return result;
    }
    public void put(String k, String v) {
        redisTemplate.opsForValue().set(k, v, DEFAULT_SAVE_TIME, TimeUnit.DAYS);
    }

    public void putList(String k, Object value) {
        String v = JSONArray.toJSONString(value);
        redisTemplate.opsForValue().set(k, v, DEFAULT_SAVE_TIME, TimeUnit.DAYS);
    }

    public void putforList(String k, Object value) {
        String v = JSONArray.toJSONString(value);
        redisTemplate.opsForList().leftPush(k,v);
    }

    public void putList(String k, Object value, long time, TimeUnit type) {
        String v = JSONArray.toJSONString(value);
        redisTemplate.opsForValue().set(k, v, time, type);
    }

    public Long getExpire(String k, TimeUnit unit){
        return redisTemplate.getExpire(k, unit);
    }

    public void setExpire(String k, TimeUnit unit, Long time){
        redisTemplate.expire(k, time, unit);
    }

    public void put(String k, Object value, long time, TimeUnit type) {
        String v = JSONObject.toJSONString(value);
        redisTemplate.opsForValue().set(k, v, time, type);
    }

    public void setStrExpire(String k, String value, long time, TimeUnit type) {
        redisTemplate.opsForValue().set(k, value, time, type);
    }

    /**
     * 在列表的最左边塞入一个value
     *
     * @param key
     * @param value
     */
    public void lpush(String key, String value) {
        redisTemplate.opsForList().leftPush(key, value);
    }

    /**
     * 在列表的最左边塞入一个value
     *
     * @param key
     * @param value
     */
    public void lpush(String key, Object[] value) {
        redisTemplate.opsForList().leftPushAll(key, value);
    }

    /**
     * 在列表的最右边塞入一个value
     * @param key
     * @param value
     */
    public void rpush(String key, String value) {
        redisTemplate.opsForList().rightPush(key, value);
    }

    /**
     * 返回存储在 key 的列表里指定范围内的元素。 start 和 end 偏移量都是基于0的下标，即list的第一个元素下标是0（list的表头），第二个元素下标是1，以此类推。
     * 偏移量也可以是负数，表示偏移量是从list尾部开始计数。 例如， -1 表示列表的最后一个元素，-2 是倒数第二个，以此类推。
     * @param key
     * @param start
     * @param stop
     * @return
     */
    public List<String> lrange(String key, long start, long stop) {
        return redisTemplate.opsForList().range(key, start, stop);
    }

    public String rPop(String key) {
        return (String) redisTemplate.opsForList().rightPop(key);
    }

    /**
     * count > 0: 从左往右移除值为 value 的元素。
     * count < 0: 从右往左移除值为 value 的元素。
     * count = 0: 移除所有值为 value 的元素。
     * @param key
     * @param count
     * @param value
     */
    public void lRem(String key, long count, String value) {
        redisTemplate.opsForList().remove(key, count, value);
    }

    public Long lSize(String key) {
        return redisTemplate.opsForList().size(key);
    }

    public void put(Map<String, String> map) {
        if (map == null || map.size() == 0){
            return;
        }
        multiSet(map);
    }

    public String getString (String k) {
        Object o = redisTemplate.opsForValue().get(k);
        if ( o != null ){
            return (String) o;
        }
        return null;
    }

    /**
     *set同时新增多个value
     * @param key
     * @param member
     */
    public void setAdds(String key, String... member) {
        redisTemplate.opsForSet().add(key, member);
    }

    /**
     * zset 插入分数
     * @param key
     * @param member
     * @param score
     */
    public void zsetAddWithScore(String key, String member, double score) {

        redisTemplate.opsForZSet().add(key, member, score);
    }

    public Set<String> zsetRange(String key, long start, long end) {
        return redisTemplate.opsForZSet().range(key, start, end);
    }
    public Set<String> zsetRangeWithScores(String key, long start, long end) {
        return redisTemplate.opsForZSet().rangeWithScores(key, start, end);
    }

    public Set<String> zsetRangeByScore(String key, long start, long end) {
        return redisTemplate.opsForZSet().rangeByScore(key, start, end);
    }

    public Set<ZSetOperations.TypedTuple> zsetRangeByScoreWithScores(String key, long start, long end) {
        return redisTemplate.opsForZSet().rangeByScoreWithScores(key, start, end);
    }

    public Set<ZSetOperations.TypedTuple> rangeWithScores(String key, long start, long end) {
        return redisTemplate.opsForZSet().rangeWithScores(key, start, end);
    }


    public long zsetRem(String key, String... members) {
        return redisTemplate.opsForZSet().remove(key, members);
    }

    public Long zscard(String key) {
        return redisTemplate.opsForZSet().size(key);
    }

    /**
     *set同时新增单个value
     * @param key
     * @param member
     */
    public void setAdd(String key, String member) {
        redisTemplate.opsForSet().add(key, member);
    }

    /**
     * set判断是否存在此成员
     * @param key
     * @param member
     * @return
     */
    public Boolean sisMember(String key, String member) {
        return redisTemplate.opsForSet().isMember(key, member);
    }

    /**
     * set随机获取key个成员
     * @param key
     * @param count
     * @return
     */
    public List<String> srandMember (String key, Long count) {
        return redisTemplate.opsForSet().randomMembers(key, count);
    }

    public Set<String> smembers(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * set获取key的成员数量
     * @param key
     * @return
     */
    public Long scard(String key) {
        return redisTemplate.opsForSet().size(key);
    }

    /**
     * set删除成员
     * @param key
     * @param member
     * @return
     */
    public Long srem(String key, String... member) {
        return redisTemplate.opsForSet().remove(key, member);
    }


    /**
     * 获取redis里的json对象，转换为对象
     *
     * @param key
     *
     * @return Object
     *
     * @since p2p_cloud_v1.0
     */
    public <T> T getObject(String key, Class<T> clazz) {
        String jsonObject = getString(key);
        if (StringUtils.isEmpty(jsonObject)) {
            return null;
        }
        return JSON.parseObject(jsonObject, clazz);
    }

    public <T> T get (String k, Class<T> clazz) {
        Object o = redisTemplate.opsForValue().get(k);
        if ( o != null ){
            String value = (String) o;
            return JSONObject.parseObject(value, clazz);
        }
        return null;
    }

    public <T> List<T> getList (String k, Class<T> clazz) {
        Object o = redisTemplate.opsForValue().get(k);
        if ( o != null ){
           String value = (String) o;
           return JSONArray.parseArray(value, clazz);
       }
       return null;
    }

    public <T> List<T> getRealList (String k) {
        List range = redisTemplate.opsForList().range(k, 0, -1);
        if ( range != null ){
            List<T> value = (List<T>) range;
            return value;
        }
        return null;
    }

    public void removeKey(String k) {
        if (k.contains("*")){
            Set keys = redisTemplate.keys(k);
            redisTemplate.delete(keys);
        } else {
            redisTemplate.delete(k);
        }
    }
    public void removeOneKey(String k) {
            redisTemplate.delete(k);
    }
    public Object get (String k) {
        return redisTemplate.opsForValue().get(k);
    }

    public Set<String> getKeys(String k){
        return redisTemplate.keys(k);
    }

    public boolean hasKey (String k) {
        return redisTemplate.hasKey(k);
    }

    public boolean hHasKey (String h, String k) {
        return redisTemplate.opsForHash().hasKey(h, k);
    }

    public void hPut(String h, String k, String v) {
        redisTemplate.opsForHash().put(h, k, v);
    }

    public void hPut(String h, Object k, Object v) {
        redisTemplate.opsForHash().put(h, k, v);
    }

    public String hGet(String h, String k) {
        return (String) redisTemplate.opsForHash().get(h, k);
    }

    public <R> R hGet(String h, String k,Class<R> r) {
        return  JSON.parseObject(String.valueOf(redisTemplate.opsForHash().get(h, k)),r);
    }

    public Long hGet(String h, Object k) {
    	Object value = redisTemplate.opsForHash().get(h, k);
        return value == null ? null : Long.valueOf(value.toString());
    }

    public void hPut(String h, Map<String, String> map) {
        redisTemplate.opsForHash().putAll(h, map);
    }

    public void hPutAndExpire(String h, Map<String, String> map, TimeUnit timeUnit, long timeOut) {
        redisTemplate.opsForHash().putAll(h, map);
        redisTemplate.expire(h, timeOut, timeUnit);
    }

    public <T> void hPutAll(String h, Map<String, List<T>> map) {
        redisTemplate.executePipelined(new SessionCallback() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {

                for (Map.Entry<String, List<T>> entry : map.entrySet()) {
                    final String hk = entry.getKey();

                    final String hv = JSONArray.toJSONString(entry.getValue());

                    operations.opsForHash().put(h, hk, hv);
                }
                return null;
            }
        });
    }

    public void hPut(Map<String, Map<String, String>> map) {

        if ( MapUtils.isNotEmpty(map) ){

            redisTemplate.executePipelined(new SessionCallback() {
                @Override
                public Object execute(RedisOperations operations) throws DataAccessException {

                    for (Map.Entry<String, Map<String, String>> entry : map.entrySet()) {
                        operations.opsForHash().putAll(entry.getKey(), entry.getValue());
                    }
                    return null;
                }
            });

        }
    }

    public void hPutAndExpireAt(Map<String, Map<String, String>> map, Date expireDate) {

        if ( MapUtils.isNotEmpty(map) ){

            redisTemplate.executePipelined(new SessionCallback() {
                @Override
                public Object execute(RedisOperations operations) throws DataAccessException {

                    for (Map.Entry<String, Map<String, String>> entry : map.entrySet()) {
                        operations.opsForHash().putAll(entry.getKey(), entry.getValue());
                        operations.expireAt(entry.getKey(), expireDate);
                    }
                    return null;
                }
            });

        }
    }

    public void hPutAndExpireAt(Map<String, Map<String, String>> map, TimeUnit timeUnit, long timeout) {

        if ( MapUtils.isNotEmpty(map) ){

            redisTemplate.executePipelined(new SessionCallback() {
                @Override
                public Object execute(RedisOperations operations) throws DataAccessException {

                    for (Map.Entry<String, Map<String, String>> entry : map.entrySet()) {
                        operations.opsForHash().putAll(entry.getKey(), entry.getValue());
                        operations.expire(entry.getKey(), timeout, timeUnit);
                    }
                    return null;
                }
            });

        }
    }

    public Long incr(String key){
        return redisTemplate.opsForValue().increment(key);
    }

    public Long hincr(String h, String k, Long value){
        return redisTemplate.opsForHash().increment(h,k,value);
    }

    public Long hincr(String h, Long k, Long value){
        return redisTemplate.opsForHash().increment(h,k,value);
    }

    public Map<String, String> hGetAll(String h) {
        return redisTemplate.boundHashOps(h).entries();
    }

    public Map<Object, Object> hGetAll(Object h) {
        return redisTemplate.boundHashOps(h).entries();
    }

    public <T> Map<String, List<T>> hGetAll(String h, Class<T> clazz) {
        Map<String, List<T>> res = new HashedMap();
        Map<String, String> map = hGetAll(h);
        if (MapUtils.isEmpty(map))
            return res;
        res.putAll(map.entrySet().stream().collect(Collectors.toMap(t -> t.getKey(), t -> JSON.parseArray(t.getValue(), clazz))));
        return res;
    }

    public Map<String, Map<String, String>> phgetAllListExistKey(List<String> keys) {
	   	Map<String, Map<String, String>> result = null;
        if(keys == null || keys.size() == 0){
       	 	return result;
        }
        Map<String,Map<String,String>> responses = new HashMap<String,Map<String,String>>(keys.size());
        try {
        	HashOperations<String, String, String> operations = redisTemplate.opsForHash();
        	 for(String key : keys){
        		 responses.put(key, operations.entries(key));
        	 }
        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
        }
        return result;
	 }

    public void hsetnx(String key, String field, String value) {
    	redisTemplate.opsForHash().putIfAbsent(key, field, value);
    }

    public void setnx(String key, String value) {
    	redisTemplate.opsForValue().setIfAbsent(key, value);
    }

    public void setnx(String key, String value,long timeout) {
        redisTemplate.opsForValue().setIfAbsent(key, value,timeout,TimeUnit.SECONDS);
    }

    public void setObject(String key, Object obj) {
        String jsonObject = JSON.toJSONString(obj);
        set(key, jsonObject);
    }
    
    /**
     * 设置单个值
     *
     * @param key
     * @param value
     *
     * @return
     */
    public void set(String key, String value) {
    	redisTemplate.opsForValue().set(key, value);
    }

    public void multiSet(Map<String, String> map) {
        redisTemplate.opsForValue().multiSet(map);
    }

    public void multiSetAndExpire(Map<String, String> map, long seconds) {

        multiSetAndExpire(map, TimeUnit.SECONDS, seconds);
    }

    public void multiSetAndExpire(Map<String, String> map, TimeUnit timeUnit, long expirationTime) {

        final RedisSerializer stringSerializer = redisTemplate.getStringSerializer();

        redisTemplate.executePipelined(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {

                for (Map.Entry<String, String> entry : map.entrySet()) {

                    redisConnection.set(stringSerializer.serialize(entry.getKey()), stringSerializer.serialize(entry.getValue()), Expiration.from(expirationTime, timeUnit), RedisStringCommands.SetOption.UPSERT);
                }
                return null;
            };
        });
    }

    public void multiSetAndExpireAt(Map<String, String> map, Date expireDate) {

        redisTemplate.executePipelined(new SessionCallback(){
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {

                for (Map.Entry<String, String> entry : map.entrySet()) {
                    operations.opsForValue().set(entry.getKey(), entry.getValue());
                    operations.expireAt(entry.getKey(), expireDate);
                }
                return null;
            }
        });
    }

    public Boolean hexists(String key, String field) {
    	return redisTemplate.opsForHash().hasKey(key, field);
    }

    public void hset(String key, String field, String value) {
       redisTemplate.opsForHash().put(key, field, value);
    }

    public Long hincrBy(String key, String field, long value) {
        return redisTemplate.opsForHash().increment(key, field, value);
    }
    
    /**
     * 获得rediskey的剩余的过期时间
     * @param key
     * @return -2 表示没有这个key, -1表示可以没有设置过期时间； > 0 改key的剩余有效时间
     */
    public Long pttl(String key) {
    	return redisTemplate.getExpire(key);
    }

    public void del(String key) {
        redisTemplate.delete(key);
    }
    
    /**
     * 批量获取(get)值 (带key)
     * @param keys
     * @return
     */
    public Map<String,String> pgetAllExistKey(List<String> keys) {
    	Map<String,String> result = null;
        if(keys == null || keys.size() == 0){
        	return result;
        }
        
        result = new HashMap<>();
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        for (String key : keys) {
        	result.put(key, operations.get(key));
		}
        return result;
    }

    public Long hashKeySize(Object key) {
        return redisTemplate.opsForHash().size(key);
    }

    /**
     *
     * @param channel
     * @param message
     */
    public void publish(String channel,Object message){
        redisTemplate.convertAndSend(channel,message);
    }

    public void sadd(String key,String... values){
        redisTemplate.opsForSet().add(key,values);
    }

    public String pop(String key){
        return  (String) redisTemplate.opsForSet().pop(key);
    }

    
    public void unsubscribe(List<String> keys) {
    	redisTemplate.unlink(keys);
    }
    
    public void subscribe(MessageListener listener, List<String> keys) {
    	byte[][] b = keys.stream().map(String::getBytes).toArray(byte[][]::new);
    	RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
    	try {
    		connection.subscribe(listener, b);
		} finally {
			connection.close();
		}
    }

    /**
     * 序列
     * @author tiancongcong
     *
     * @param key
     * @param seconds
     * @param prefix
     * @return
     */
    public String getRedisSequence(String key, long seconds, int prefix) {
        if (StringUtils.isEmpty(key)) {
            return "";
        }
        Long _seq = incr(key);
        String seq = _seq == null ? null : String.valueOf(_seq);
        if (StringUtils.isEmpty(seq)) {
            seq = "1";
            set(key, seq);
            if (seconds > 0) {
                setExpire(key, TimeUnit.SECONDS, seconds);
            }
        }
        return completion(seq, prefix);
    }

    /**
     * 设置固定时间点失效
     * @param k
     * @param time timeMillis
     */
    public void expireAt(String k, Long time){
        redisTemplate.expireAt(k, new Date(time));
    }

    public void expireAt(String k, Date expireDate){
        redisTemplate.expireAt(k, expireDate);
    }

    private String completion(String seq, int prefix) {
        if (prefix > seq.length()) {
            StringBuffer str = new StringBuffer();
            int diff = prefix - seq.length();
            while (diff > 0) {
                --diff;
                str.append("0");
            }
            str.append(seq);
            return str.toString();
        }
        return seq;
    }

    public Boolean setIfAbsent(String k, String value, long time, TimeUnit type) {
        return redisTemplate.opsForValue().setIfAbsent(k,value,time,type);
    }
}
