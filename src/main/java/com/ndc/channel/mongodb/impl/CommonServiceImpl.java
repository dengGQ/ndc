package com.ndc.channel.mongodb.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.ndc.channel.mongodb.ICommonService;
import com.ndc.channel.mongodb.dto.MongoOperateConstants;
import com.ndc.channel.mongodb.dto.QueryMongoParams;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.index.IndexInfo;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class CommonServiceImpl implements ICommonService {
	
	private Logger log = LoggerFactory.getLogger(getClass());

	@Resource
    private MongoTemplate mongoTemplate;
	@Override
	public String insert(Object obj) {
		// TODO Auto-generated method stub
		mongoTemplate.insert(obj);
		return JSON.parseObject(JSON.toJSONString(obj)).getString("_id");
	}

	@Override
	public String insert(Object obj, String collectionName) {
		mongoTemplate.insert(obj,collectionName);
		return JSON.parseObject(JSON.toJSONString(obj)).getString("_id");
	}
	
	@Override
	public <T> T findById(String id, Class<T> clazz) {
		// TODO Auto-generated method stub
		return mongoTemplate.findById(id, clazz);
	}

	@Override
	public <T> List<T> findAll(Class<T> clazz) {
		// TODO Auto-generated method stub
		return mongoTemplate.findAll(clazz);
	}

	@Override
	public <T> List<T> findByRegex(String key, String regex, Class<T> clazz) {
		//创建正则表达式
		Pattern pattern = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);     
        Criteria criteria = new Criteria(key).regex(pattern.toString());     
		return mongoTemplate.find(new Query(criteria), clazz);
	}

	@Override
	public <T> void removeById(String id, Class<T> clazz) {
		 mongoTemplate.remove(this.findById(id, clazz));    
	}

	@Override
	public <T> void removeAll(Class<T> clazz) {
		List<T> list = this.findAll(clazz);
		if(list != null && list.size() > 0){
			list.forEach(l1 -> mongoTemplate.remove(l1));
		}
	}

	@Override
	public <T> void findAndModify(String id, Map<String,Object> map, Class<T> clazz) {
		Update update = new Update();
		for(Map.Entry<String,Object> entry : map.entrySet()){
			update.set(entry.getKey(), entry.getValue());
		}
		mongoTemplate.updateMulti(new Query(Criteria.where("_id").is(id)), update, clazz);  
	}

	@Override
	public <T> List<T> findAll(String collectionName, Class<T> clazz) {
		// TODO Auto-generated method stub
		return mongoTemplate.findAll(clazz, collectionName);
	}

	@Override
	public <T> List<T> findByRegex(String key, String regex,
			String collectionName, Class<T> clazz) {
		Pattern pattern = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);     
        Criteria criteria = new Criteria(key).regex(pattern.toString());     
		return mongoTemplate.find(new Query(criteria), clazz, collectionName);
	}

	@Override
	public <T> void removeAll(String collectionName, Class<T> clazz) {
		List<T> list = this.findAll(collectionName, clazz);
		if(list != null && list.size() > 0){
			list.forEach(l1 -> mongoTemplate.remove(l1, collectionName));
		}
	}
	@Override
	public <T> List<T> findAndParams(Class<T> clazz, QueryMongoParams... params) {
		// TODO Auto-generated method stub
		Criteria criteria = new Criteria();
		if(params != null){
			//组装and参数
			createMongoCriteria(criteria, params);
		}
		return mongoTemplate.find(new Query(criteria), clazz);
	}

	@Override
	public <T> List<T> findByCriteria(Criteria criteria, Class<T> clazz) {
		// TODO Auto-generated method stub
		return mongoTemplate.find(new Query(criteria), clazz);
	}

	private void createMongoCriteria(Criteria criteria,
			QueryMongoParams... params) {
		Criteria[] criterias = new Criteria[params.length];
		for(int i = 0 ; i < params.length ; i++){
			QueryMongoParams param = params[i];
			switch (param.getOperateType()) {
			
			case MongoOperateConstants.MONGO_EQU:
				criterias[i] = Criteria.where(param.getKey()).is(param.getObj());
				break;
			case MongoOperateConstants.MONGO_LT:
				criterias[i] = Criteria.where(param.getKey()).lt(param.getObj());
				break;
			case MongoOperateConstants.MONGO_LTE:
				criterias[i] = Criteria.where(param.getKey()).lte(param.getObj());
				break;
			case MongoOperateConstants.MONGO_GT:
				criterias[i] = Criteria.where(param.getKey()).gt(param.getObj());
				break;
			case MongoOperateConstants.MONGO_GTE:
				criterias[i] = Criteria.where(param.getKey()).gte(param.getObj());
				break;
			case MongoOperateConstants.MONGO_NE:
				criterias[i] = Criteria.where(param.getKey()).ne(param.getObj());
				break;
			case MongoOperateConstants.MONGO_IN:
				if(param.getObj().getClass().isArray()){
					Object[] objs = (Object[]) param.getObj();
					criterias[i] = Criteria.where(param.getKey()).in(objs);
				}else if(param.getObj() instanceof List){
					List list = (List) param.getObj();
					criterias[i] = Criteria.where(param.getKey()).in(list.toArray());
				}else{
					criterias[i] = Criteria.where(param.getKey()).in(param.getObj());
				}
				break;
			case MongoOperateConstants.MONGO_NIN:
				if(param.getObj().getClass().isArray()){
					Object[] objs = (Object[]) param.getObj();
					criterias[i] = Criteria.where(param.getKey()).in(objs);
				}else if(param.getObj() instanceof List){
					List list = (List) param.getObj();
					criterias[i] = Criteria.where(param.getKey()).in(list.toArray());
				}else{
					criterias[i] = Criteria.where(param.getKey()).in(param.getObj());
				}
				break;
				
			case MongoOperateConstants.MONGO_REGEX:
				criterias[i] = Criteria.where(param.getKey()).regex(param.getObj().toString());
				break;
			default:
				break;
			}
		}
		criteria.andOperator(criterias);
	}

	@Override
	public <T> List<T> findAndParams(Class<T> clazz, String collectionName,
			QueryMongoParams... params) {
		// TODO Auto-generated method stub
		Criteria criteria = new Criteria();
		if(params != null){
			//组装and参数
			createMongoCriteria(criteria, params);
		}
		return mongoTemplate.find(new Query(criteria), clazz, collectionName);
	}

	@Override
	public <T> List<T> findByCriteria(Criteria criteria, String collectionName,
			Class<T> clazz) {
		// TODO Auto-generated method stub
		return mongoTemplate.find(new Query(criteria), clazz, collectionName);
	}
	
	@Override
	public <T> void insertAll(List<T> list) {
		mongoTemplate.insertAll(list);
	}

	@Override
	public <T> T findOne(String collectionName, Class<T> clazz, QueryMongoParams... params) {
		Criteria criteria = new Criteria();
		if(params != null){
			//组装and参数
			createMongoCriteria(criteria, params);
		}
		return mongoTemplate.findOne(new Query(criteria), clazz, collectionName);
	}

	@Override
	public <T> String getCollectionName(Class<T> clazz) {
		String collectionName = "";
		Document document = clazz.getAnnotation(Document.class);
		if (document != null) {
			Method[] met = document.annotationType().getDeclaredMethods();
			for (Method me : met) {
				if (!me.isAccessible()) {
					me.setAccessible(true);
				}
				try {
					if (me.getName().equals("collection")) {
						collectionName = me.invoke(document).toString();
					}
				} catch (Exception e) {
					log.error("mogodb collection 获取失败");
				}
			}
		}else{
			collectionName = clazz.getSimpleName();
		}
		return collectionName;
	}

	@Override
	public <T> List<T> findByCriteriaWithOrder(Criteria criteria, String collectionName, Class<T> clazz, Sort sort) {
		// TODO Auto-generated method stub
		return mongoTemplate.find(new Query(criteria).with(sort), clazz, collectionName);
	}

	@Override
	public <T> void update(List<QueryMongoParams> params, Map<String, Object> map, Class<T> clazz) {
		Update update = new Update();
		for(Map.Entry<String,Object> entry : map.entrySet()){
			update.set(entry.getKey(), entry.getValue());
		}
		Criteria criteria = new Criteria();
		createMongoCriteria(criteria, params.toArray(new QueryMongoParams[params.size()]));
		mongoTemplate.updateMulti(new Query(criteria), update, getCollectionName(clazz));  
	}

	@Override
	public <T> void upsert(List<QueryMongoParams> params, Map<String, Object> map, Class<T> clazz) {
		Criteria criteria = new Criteria();
		createMongoCriteria(criteria, params.toArray(new QueryMongoParams[params.size()]));
		Update update = new Update();
		for(Map.Entry<String,Object> entry : map.entrySet()){
			update.set(entry.getKey(), entry.getValue());
		}
		mongoTemplate.upsert(new Query(criteria), update, getCollectionName(clazz));
	}

	@Override
	public <T> void deleteAll(Class<T> clazz) {
		mongoTemplate.dropCollection(getCollectionName(clazz));  
	}

	@Override
	public <T> void addIndex(Class<T> clazz, String field) {
		Index index =new Index();
	    index.on(field, Direction.ASC);
		mongoTemplate.indexOps(getCollectionName(clazz)).ensureIndex(index);
	}

	@Override
	public <T> void addIndex(Class<T> clazz, List<String> fields) {
		Index index = new Index();
		fields.forEach(t -> index.on(t, Direction.ASC));
		mongoTemplate.indexOps(getCollectionName(clazz)).ensureIndex(index);
	}

	@Override
	public <T> List<String> getIndexes(Class<T> clazz, String... fieldLike) {
		List<IndexInfo> indexInfoList = mongoTemplate.indexOps(getCollectionName(clazz)).getIndexInfo();
		if (CollectionUtils.isEmpty(indexInfoList)) {
			return Lists.newArrayList();
		}
		List<String> fieldLikeList = Arrays.asList(fieldLike);
		if (CollectionUtils.isNotEmpty(fieldLikeList)) {

			List<String> toBeReturn = Lists.newArrayList();
			indexInfoList.forEach(t -> t.getIndexFields().forEach(index -> {
				if (fieldLikeList.contains(index.getKey())) {
					//同一索引不会添加多次
					toBeReturn.add(t.getName());
				}
			}));

			if (CollectionUtils.isNotEmpty(toBeReturn)) {
				return toBeReturn;
			}
		}
		return indexInfoList.stream().map(IndexInfo::getName).collect(Collectors.toList());
	}

	@Override
	public <T> void dropIndex(Class<T> clazz, String indexName) {
		if (StringUtils.isNotEmpty(indexName)) {
			mongoTemplate.indexOps(getCollectionName(clazz)).dropIndex(indexName);
		}

	}

	@Override
	public <T> void upsert(Criteria criteria, String collectionName, Map<String, Object> map, Class<T> clazz) {
		Update update = new Update();
		for(Map.Entry<String,Object> entry : map.entrySet()){
			update.set(entry.getKey(), entry.getValue());
		}
		mongoTemplate.upsert(new Query(criteria), update, collectionName);
	}

	@Override
	public void save(Object object) {
		mongoTemplate.save(object);
	}

	@Override
	public <T> T findOne(Class<T> clazz, QueryMongoParams... params) {
		Criteria criteria = new Criteria();
		if(params != null){
			//组装and参数
			createMongoCriteria(criteria, params);
		}
		return mongoTemplate.findOne(new Query(criteria), clazz);
	}

	@Override
	public <T> T findOne(Class<T> clazz, List<QueryMongoParams> params) {
		return findOne(clazz, params.toArray(new QueryMongoParams[0]));
	}
}
