package com.ndc.channel.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.ndc.channel.entity.NdcChannelApiLog;
import com.ndc.channel.exception.BusinessException;
import com.ndc.channel.mapper.NdcChannelApiLogMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

@Slf4j
@Aspect
@Component
public class ChannelApiLogInterceptor {

    @Resource
    private NdcChannelApiLogMapper apiLogMapper;

    @Pointcut("execution(public * com.ndc.channel.http.ChannelOKHttpService.*(..))")
    public void ndcApiToolsAspect(){}

    @Around("ndcApiToolsAspect()")
    public Object aroundLogger(ProceedingJoinPoint jp) throws Throwable{

        Signature signature = jp.getSignature();

        final String methodName = signature.getName();

        final Object[] args = jp.getArgs();

        String url = (String) args[0];
        String params = (String) args[1];

        Object result = null;

        Date requestTime = new Date();
        long st = System.currentTimeMillis();
        Byte status = 1;
        try {
            result = jp.proceed();
        } catch (BusinessException e){
            result = "错误代码：" + e.getCode() + ",错误信息：" + e.getMessage();
            status = 2;
            throw e;
        } catch (Exception e){
            result = "错误信息：" + getStackTrace(e);
            status = 2;
            throw e;
        }finally {
            long et = System.currentTimeMillis();
            createChannelApiLog(params, methodName, url, result, status, requestTime, et-st);
        }
        return result;
    }

    private String getStackTrace(Throwable throwable){
        StringWriter stringWriter=new StringWriter();
        PrintWriter printWriter=new PrintWriter(stringWriter);

        try {
            throwable.printStackTrace(printWriter);
            return stringWriter.toString();
        }finally {
            printWriter.close();
        }
    }

    /**
     * 接口调用日志表数据
     */
    private void createChannelApiLog(String params, String methodName, String url, Object result, Byte status, Date requestTime, long duration){
        try {
            //参数
            NdcChannelApiLog apiLog = new NdcChannelApiLog();
            apiLog.setRequestTime(requestTime);
            apiLog.setDuration(duration);
            apiLog.setUrl(url);
            apiLog.setParams(params);
            apiLog.setStatus(status);
            apiLog.setMethodName(methodName);
            apiLog.setResult(result == null ? null : JSONObject.toJSONString(result));
            apiLog.setNdcAccountCode("mu_ndc");
            apiLog.setNdcAccountName("东航NDC");

            apiLogMapper.insertSelective(apiLog);
        }catch (Exception e) {
            log.error("NDCChannelApiLog存储失败", e);
        }
    }
}
