package com.ndc.channel.mongodb.dto;


import java.io.Serializable;

/**
 * 多条件mongo查询
 * @author hejiang
 *
 */
public class QueryMongoParams implements Serializable {

	private String key;
	
	private Object obj;
	
	private String operateType;

	public QueryMongoParams() {
		super();
	}

	public QueryMongoParams(String key, Object obj, String operateType) {
		super();
		this.key = key;
		this.obj = obj;
		this.operateType = operateType;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

}
