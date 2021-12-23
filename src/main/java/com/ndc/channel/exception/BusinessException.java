package com.ndc.channel.exception;

/**
 * 业务异常
 * 
 * @author chen
 *
 */
public class BusinessException extends RuntimeException {
    
	private static final long serialVersionUID = -1043294015859450356L;
	/**
	 * 异常编码
	 */
	private String code;
    /**
     * 异常内容
     */
	private String message;
    
	public BusinessException(){}

    public BusinessException(String code){
        this.code = code;
        this.message = BusinessExceptionCode.getMessage(code);
    }

    public BusinessException(Throwable cause)
    {
        super(cause);
    }

    public BusinessException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public BusinessException(String code, String message)
    {
        this.code = code;
        this.message = message;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }
}
