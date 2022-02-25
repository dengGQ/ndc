package com.ndc.channel.flight.dto;

import com.google.common.collect.Lists;
import com.ndc.channel.exception.BusinessException;
import com.ndc.channel.exception.BusinessExceptionCode;
import org.springframework.beans.BeanUtils;

import java.util.List;

public class BusinessResponseFactory {
    
	/**
	 * system error
	 * @return
	 */
    public static ResponseData<Object> createSystemError() {
    	ResponseData<Object> response = new ResponseData<>();
    	response.setCode(BusinessExceptionCode.SYSTEM_ERROR);
    	response.setMessage(BusinessExceptionCode.getMessage(BusinessExceptionCode.SYSTEM_ERROR));
    	response.setData(null);
        return response;
    }

    /**
     * business error
     * @param errorCode
     * @param message
     * @return
     */
    public static ResponseData createBusinessError(String errorCode, String message){
    	ResponseData<Object> response = new ResponseData<>();
    	response.setCode(errorCode);
    	response.setMessage(message);
    	response.setData(null);
        return response;
    }


	public static ResponseData createBusinessError(BusinessException exce){
		ResponseData<Object> response = new ResponseData<>();
		response.setCode(exce.getCode());
		response.setMessage(exce.getMessage());
		response.setData(null);
		return response;
	}
    
    /**
     * business error
     * @param errorCode
     * @return
     */
    public static ResponseData createBusinessError(String errorCode){
    	ResponseData<Object> response = new ResponseData<>();
    	response.setCode(errorCode);
    	response.setMessage(BusinessExceptionCode.getMessage(errorCode));
    	response.setData(null);
        return response;
    }

	/**
	 * success
	 * @return
	 */
	public static ResponseData createSuccess() {
		ResponseData response = new ResponseData<>();
		response.setCode(BusinessExceptionCode.SUCCESS_CODE);
		response.setMessage(BusinessExceptionCode.getMessage(BusinessExceptionCode.SUCCESS_CODE));
		response.setData(null);
		return response;
	}
    
    /**
     * success
     * @param businessData
     * @return
     */
    public static <T> ResponseData<T> createSuccess(T businessData){
    	ResponseData<T> response = new ResponseData<>();
    	response.setCode(BusinessExceptionCode.SUCCESS_CODE);
    	response.setMessage(BusinessExceptionCode.getMessage(BusinessExceptionCode.SUCCESS_CODE));
    	response.setData(businessData);
        return response;
    }
    
    /**
     * success
     * @param businessData
     * @return
     */
    public static <T> ResponseData<T> createSuccess(T businessData, String sequence){
    	ResponseData<T> response = new ResponseData<>();
    	response.setCode(BusinessExceptionCode.SUCCESS_CODE);
    	response.setMessage(BusinessExceptionCode.getMessage(BusinessExceptionCode.SUCCESS_CODE));
    	response.setData(businessData);
    	response.setSequence(sequence);
        return response;
    }

	public static <R, T> ResponseData<Object> createSuccess(T businessData,Class<R> rClass) {
		ResponseData<Object> response = new ResponseData<>();
		response.setCode(BusinessExceptionCode.SUCCESS_CODE);
		response.setMessage(BusinessExceptionCode.getMessage(BusinessExceptionCode.SUCCESS_CODE));
		if (businessData instanceof List) {
			List<R> rList = Lists.newArrayList();
			((List) businessData).forEach(t -> {
				R r = null;
				try {
					r = rClass.newInstance();
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}
				BeanUtils.copyProperties(t, r);
				rList.add(r);

			});
			response.setData(rList);
		}else {
			try {
				R r = rClass.newInstance();
				BeanUtils.copyProperties(businessData,r);
				response.setData(r);
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}

		return response;
	}

	public static <T> ResponseData createBusinessWithCode(String code, T businessData) {
		ResponseData<Object> response = new ResponseData<>();
		response.setCode(code);
		response.setMessage(BusinessExceptionCode.getMessage(code));
		response.setData(businessData);
		return response;
	}
}
