package com.ndc.channel.flight.dto;

import java.io.Serializable;

/**
 * 公共返回封装对象
 * @param <T>
 */
public class ResponseData<T>  implements Serializable{

	private static final long serialVersionUID = 4121772914571121899L;

	private String code;
	private String message;
	private T data;
	private String sequence;
	private String sign;

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}

	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public boolean isSuccessful() {
		if(code != null && (code.equals("0000") || code.equals("0001"))) {
			return true;
		}else {
			return false;
		}
	}
}
