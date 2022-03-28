package com.ndc.channel.mongodb;

import com.ndc.channel.mongodb.dto.QueryMongoParams;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.List;
import java.util.Map;

public interface ICommonService {

	/**
	 * 新增 注意如需返回mongo新增后的id,插入数据对象需要继承或添加一个字段'_id';
	 * @param obj
	 * @return
	 */
    String insert(Object obj);
    
    /**
     * 根据id查询 
     * @param id
     * @param clazz
     * @return
     */
    <T> T findById(String id, Class<T> clazz);

	/**
	 * 查询所有
	 * @param clazz
	 * @return
	 */
    <T> List<T> findAll(Class<T> clazz);
      
    /**
     * 正则查询
     * @param key 查询的key
     * @param regex 正则表达式
     * @param clazz 转换的实体对象
     * @return
     */
    <T> List<T> findByRegex(String key, String regex, Class<T> clazz);

	/**
	 * 根据id删除
	 * @param id mongoId
	 * @param clazz 实体类
	 */
    <T> void removeById(String id, Class<T> clazz);

    /**
     * 删除所有 
     * @param clazz
     */
    <T> void removeAll(Class<T> clazz);

	/**
	 * 通过ID找到并修改  
	 * @param id
	 * @param map 存储格式 key(属性名)：value(修改后的属性值)
	 * @param clazz
	 */
    <T> void findAndModify(String id, Map<String, Object> map, Class<T> clazz);
    
    /**
     * 注意如需返回mongo新增后的id,插入数据对象需要继承或添加一个字段'_id';
     * 新增 指定collectionName
     * @param obj
     * @param collectionName
     * @return
     */
    String insert(Object obj, String collectionName);
    
    /**
     * 查询所有
     * @param collectionName
     * @param clazz
     * @return
     */
    <T> List<T> findAll(String collectionName, Class<T> clazz);
      
    /**
     * 正则查询
     * @param key 查询的key
     * @param regex 正则表达式
     * @param clazz 转换的实体对象
     * @param collectionName
     * @return
     */
    <T> List<T> findByRegex(String key, String regex, String collectionName, Class<T> clazz);
    
    /**
     * 删除所有 
     * @param clazz
     * @param collectionName
     */
    <T> void removeAll(String collectionName, Class<T> clazz);
    
    /**
     * 多条件and连接查询
     * @param params
     * @return
     */
    <T> List<T> findAndParams(Class<T> clazz, QueryMongoParams... params);
    
    /**
     * 已组装好条件查询
     * @param criteria
     * @return
     */
    <T> List<T> findByCriteria(Criteria criteria, Class<T> clazz);
    
    /**
     * 多条件and连接查询
     * @param params
     * @param collectionName
     * @return
     */
    <T> List<T> findAndParams(Class<T> clazz, String collectionName, QueryMongoParams... params);
    
    /**
     * 已组装好条件查询
     * @param criteria
     * @param collectionName
     * @return
     */
    <T> List<T> findByCriteria(Criteria criteria, String collectionName, Class<T> clazz);
    
    <T> void insertAll(List<T> list);
    
    <T> T findOne(String collectionName, Class<T> clazz, QueryMongoParams... params);
    
    <T> String getCollectionName(Class<T> clazz);
    
    <T> List<T> findByCriteriaWithOrder(Criteria criteria, String collectionName, Class<T> clazz, Sort sort);
    
    /**
	 * 通过ID找到并修改  
	 * @param map 存储格式 key(属性名)：value(修改后的属性值)
	 * @param clazz
	 */
    <T> void update(List<QueryMongoParams> params, Map<String, Object> map, Class<T> clazz);
    
    <T> void upsert(List<QueryMongoParams> params, Map<String, Object> map, Class<T> clazz);
    
    <T> void deleteAll(Class<T> clazz);
    
    <T> void addIndex(Class<T> clazz, String field);

    /**
     * 创建复合所有
     * -1:降序
     * 1:正序
     * default:1
     * @author dingyj
     * @date 2019-04-24 15:00
     */
    <T> void addIndex(Class<T> clazz, List<String> fields);

    /**
     * 查看集合索引并返回,支持索引名称like
     * @author dingyj
     * @date 2019-04-24 15:14
     */
    <T> List<String> getIndexes(Class<T> clazz, String... fieldLike);
    /**
     * 删除索引
     * @author dingyj
     * @date 2019-04-24 15:12
     */
    <T> void dropIndex(Class<T> clazz, String indexName);
    /**
     * upset
     * @param criteria
     * @param collectionName
     * @param map
     * @param clazz
     * @param <T>
     */
    <T> void upsert(Criteria criteria, String collectionName, Map<String, Object> map, Class<T> clazz);


    /**
     * mong 批量更新
     * @param options
     * @param collectionName
     * @return
     */
//    int bathUpdate(List<BathUpdateOptions> options, String collectionName);

    /**
     * 批量更新
     * @return
     */
//    <T> int bathUpdate(List<BathUpdateOptions> options, Class<T> clazz);
    
    void save(Object object);
    
    <T> T findOne(Class<T> clazz, QueryMongoParams... params);
    <T> T findOne(Class<T> clazz, List<QueryMongoParams> params);
}
