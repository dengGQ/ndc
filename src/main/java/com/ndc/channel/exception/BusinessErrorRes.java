package com.ndc.channel.exception;


/**
 * 当系统错误时，RESTFULE接口返回的对象
 * 
 * @author chen
 */
public class BusinessErrorRes {

    private String code;
    
    private String message;
    
    public static BusinessErrorRes create(String code, String message) {
        BusinessErrorRes exceptionResponse = new BusinessErrorRes();
        exceptionResponse.setCode(code);
        exceptionResponse.setMessage(message);
        return exceptionResponse;
    }

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

}
