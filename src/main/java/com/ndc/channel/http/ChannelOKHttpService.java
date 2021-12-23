package com.ndc.channel.http;


import com.ndc.channel.exception.BusinessException;
import com.ndc.channel.exception.BusinessExceptionCode;
import okhttp3.*;
import okhttp3.FormBody.Builder;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.net.ssl.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

@Component
public class ChannelOKHttpService {

	private static OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();

	private static Map<String,OkHttpClient> clientMap = new HashMap<>();

	//默认编码集
	private static final String CHARSET = "utf-8";

	private static final String APPLICATION_JSON = "application/json";

	public static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");

	public static final MediaType MEDIA_TYPE_FROM = MediaType.parse("application/x-www-form-urlencoded");

	public static final MediaType MEDIA_TYPE_MULTIPART = MediaType.parse("multipart/form-data");

	public static final MediaType MEDIA_TYPE_XML = MediaType.parse("application/xml");

	/**
	 *
	 * @param channelId 渠道id https请求传入渠道id和证书路径名，http请求默认为null
	 * @param cerificationPath 证书路径
	 * @return
	 */
	private OkHttpClient getClient(String channelId,String cerificationPath) {

		if(channelId == null){
			channelId = "http";
		}
		if (clientMap.get(channelId) == null) {
			OkHttpClient client;
			if ("http".equals(channelId)) {
				client = httpBuilder.readTimeout(2, TimeUnit.MINUTES)
						.connectTimeout(2, TimeUnit.MINUTES).writeTimeout(2, TimeUnit.MINUTES) //设置超时
						.build();

			}else {
				client = httpBuilder.readTimeout(2, TimeUnit.MINUTES)
						.connectTimeout(2, TimeUnit.MINUTES).writeTimeout(2, TimeUnit.MINUTES) //设置超时
						.sslSocketFactory(getSSlSocketFactory(getInputStream(cerificationPath)))
						.hostnameVerifier(getHostnameVerifier())
						.build();
			}
			clientMap.put(channelId,client);
			return client;
		}else{
			return clientMap.get(channelId);
		}
	}

	private Logger logger = LoggerFactory.getLogger(ChannelOKHttpService.class);

	public static HostnameVerifier getHostnameVerifier(){

 		HostnameVerifier hostnameVerifier = new HostnameVerifier(){
			@Override
			 public boolean verify(String s, SSLSession sslSession){
			 	return true;
			}
		};
		return hostnameVerifier;
	}

	private SSLSocketFactory getSSlSocketFactory(InputStream certificates){
		 SSLContext sslContext = null;
		 try {
			CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
			 Certificate ca;
			 try {
			 	ca = certificateFactory.generateCertificate(certificates);
			} finally {
				certificates.close();
			}

			// Create a KeyStore containing our trusted CAs
			 String keyStoreType = KeyStore.getDefaultType();
			 KeyStore keyStore = KeyStore.getInstance(keyStoreType);
			 keyStore.load(null, null);
			 keyStore.setCertificateEntry("ca", ca);

			// Create a TrustManager that trusts the CAs in our KeyStore
			String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
			TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
			tmf.init(keyStore);

			// Create an SSLContext that uses our TrustManager
			 sslContext = SSLContext.getInstance("SSL");
			 sslContext.init(null, tmf.getTrustManagers(), null);
		} catch (Exception e){

		 	e.printStackTrace();

		}
		 return sslContext != null ? sslContext.getSocketFactory():null;

	}

	//读取证书文件
	private InputStream getInputStream(String certificationPath){
		InputStream inputStream = null;
		try {
			inputStream = this.getClass().getResourceAsStream(certificationPath);
		} catch (Exception e){
		 	e.printStackTrace();
		}
		 return inputStream;
	}


	public String doPostXml(String url, String paramXml) throws Exception {
		return doPostXml(url, paramXml);
	}
	public String doPostXml(String url, String paramXml, Map<String,String> headers) throws Exception {
		try {
			RequestBody body = RequestBody.create(MEDIA_TYPE_XML, paramXml);
			Request.Builder builder = new Request.Builder().url(url);
			if(headers != null) {
				Iterator iterator = headers.entrySet().iterator();
				while(iterator.hasNext()) {
					Entry entry = (Entry)iterator.next();
					builder.addHeader(entry.getKey().toString(), entry.getValue().toString());
				}
			}
			Request request = builder.post(body).build();
			Response response = getClient(null,null).newCall(request).execute();
			if (response.isSuccessful()) {
				String result = response.body().string();
//				logger.info(result);
//				Document document = DocumentHelper.parseText(result);
//				result = document.getRootElement().getText();
				return result;
			} else {
				return null;
			}
		} catch (IOException e) {
			//服务调用失败
			throw new BusinessException(BusinessExceptionCode.SYSTEM_ERROR);
		}
	}
}
