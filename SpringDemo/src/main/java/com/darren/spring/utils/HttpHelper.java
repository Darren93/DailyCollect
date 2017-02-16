package com.darren.spring.utils;

//-------------------------------------------------------------------------------------
//CMB Confidential
//
//Copyright (C) 2010 China Merchants Bank Co., Ltd. All rights reserved.
//
//No part of this file may be reproduced or transmitted in any form or by any means,
//electronic, mechanical, photocopying, recording, or otherwise, without prior
//written permission of China Merchants Bank Co., Ltd.
//
//-------------------------------------------------------------------------------------

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

import jdk.nashorn.internal.runtime.GlobalConstants;

/**
* Http操作函数集合类 <br/>
*
* @author weidewang
*/
public class HttpHelper {

 private final static Log log = LogFactory.getLog(HttpHelper.class);
 
 private final static int HTTP_CONNECTION_TIMEOUT = 15 * 1000;
 private final static int HTTP_SOCKET_TIMEOUT = 30 * 1000;
 
 // SSL Configure
 public static final String KEY_STROE_PATH = "";
 public static final String KEY_STROE_TYPE = "";
 public static final String TRUSTED_STORE_PATH = "";
 public static final String TRUSTED_STORE_TYPE = "";
 public static final String KEY_STORE_PASSWORD = "";
 public static final String ALGORITHM = KeyManagerFactory.getDefaultAlgorithm();
 public static final String SSL_PROTOCOL = "SSL";
 public static final int HTTPS_PORT = 443;
 
/**
* http get请求
* @param uri
* @return get请求的字符串结果
* @throws IOException
*/
public String doGet(String uri) throws IOException {
 HttpClient httpClient = new DefaultHttpClient();
 String rs = null;
 try {
   setHttpClientParametersWithDefaultValue(httpClient);
   HttpGet httpGet = new HttpGet(uri);
   HttpResponse response;
   response = httpClient.execute(httpGet);
   if (response.getEntity() != null) {
     rs = EntityUtils.toString(response.getEntity());
   }
   
   log.info("Response Data " + rs);
 } catch (IOException ex) {
   throw ex;
 } finally {
   httpClient.getConnectionManager().shutdown();
 }
 return rs;
}


/**
* http post 请求
* 参数拼接在 url 后
* @param uri
* @return post请求结果
* @throws IOException
*/
public String doPost(String uri) throws IOException {
 HttpClient httpClient = new DefaultHttpClient();
 String rs = null;
 try {
   setHttpClientParametersWithDefaultValue(httpClient);
   HttpPost httpPost = new HttpPost(uri);
   HttpResponse response;
   response = httpClient.execute(httpPost);
   if (response.getEntity() != null) {
     rs = EntityUtils.toString(response.getEntity());
   }
 } catch (IOException ex) {
   throw ex;
 } finally {
   httpClient.getConnectionManager().shutdown();
 }
 return rs;
}

/**
* http get 请求 (对uri参数编码)
* @param uri
* @param encoding
* @return String
* @throws UnsupportedEncodingException
* @throws IOException 
*/
public String doGet(String uri,String encoding) throws UnsupportedEncodingException, IOException {
   uri = autoFixProtocolHeader(uri);
   
   log.info("Request Url " + uri);
   
   uri = encodingURIParams(uri, encoding);
   return doGet(uri);
}

/**
* http post 请求 (对uri参数编码)
* @param uri
* @param encoding
* @return String
* @throws UnsupportedEncodingException
* @throws IOException 
*/
public String doPost(String uri,String encoding) throws UnsupportedEncodingException, IOException {
   String requestHost = "";
   String requestParams = "";
   String respString = "";
   
   if (CommonHelper.isEmpty(uri)) return respString;
   
   uri = autoFixProtocolHeader(uri);
   if (CommonHelper.isEmpty(encoding)) encoding = "utf-8";
   
   // 对 url 进行 urlencode
   uri = encodingURIParams(uri, encoding);
   
   // 判断uri后面是否有接参数
   if (uri.indexOf("?") != -1 && uri.indexOf("?") != uri.length()-1) {
       requestHost = uri.split("\\?")[0];
       requestParams = uri.split("\\?")[1];
   } else {
       requestHost = uri;
   }
   
   log.info("Request URL "+uri);
   
   HttpClient httpClient = new DefaultHttpClient();
   try {
       setHttpClientParametersWithDefaultValue(httpClient);
       HttpPost httpPost = new HttpPost(requestHost);
       httpPost.setHeader("Connection", "close");
       StringEntity entity = new StringEntity(requestParams, encoding);
       entity.setContentType("application/x-www-form-urlencoded");
       httpPost.setEntity(entity);
       HttpResponse response;
       response = httpClient.execute(httpPost);
       if (response.getEntity() != null) {
           
           InputStream is = response.getEntity().getContent();
           ByteArrayOutputStream bos = new ByteArrayOutputStream();
           try {
               byte[] buffer = new byte[1024];
               int len ;

               while ((len = is.read(buffer)) > 0) {
                   bos.write(buffer, 0, len);
               }

               respString = bos.toString(encoding);
           } catch (IOException e) {
               throw e;
           } finally {
               is.close();
               bos.close();
           }
           
       }
       
       log.info("Response Data "+respString);
   } catch (IOException ex) {
       throw ex;
   } finally {
       httpClient.getConnectionManager().shutdown();
   }
   return respString;
}

/**
* get 和 post混合请求
* 
* @param uri
* @param paramsString
* @param encoding
* @return
* @throws UnsupportedEncodingException
* @throws IOException 
*/
public String doGetAndPost(String uri, String paramsString, String encoding) throws UnsupportedEncodingException, IOException {
   String requestHost = "";
   String requestParams = "";
   String respString = "";
   
   if (CommonHelper.isEmpty(uri)) return respString;
   
   uri = autoFixProtocolHeader(uri);
   if (CommonHelper.isEmpty(encoding)) encoding ="utf-8";
   
   // 对 url 进行 urlencode
   uri = encodingURIParams(uri, encoding);
   
   // 判断uri后面是否有接参数
    requestHost = uri;
    requestParams = paramsString;
   
   log.info("Request URL "+uri);
   
   HttpClient httpClient = new DefaultHttpClient();
   try {
       setHttpClientParametersWithDefaultValue(httpClient);
       HttpPost httpPost = new HttpPost(requestHost);
       httpPost.setHeader("Connection", "close");
       StringEntity entity = new StringEntity(requestParams, encoding);
       httpPost.setEntity(entity);
       HttpResponse response;
       response = httpClient.execute(httpPost);
       if (response.getEntity() != null) {
           
           InputStream is = response.getEntity().getContent();
           ByteArrayOutputStream bos = new ByteArrayOutputStream();
           try {
               byte[] buffer = new byte[1024];
               int len ;

               while ((len = is.read(buffer)) > 0) {
                   bos.write(buffer, 0, len);
               }

               respString = bos.toString(encoding);
           } catch (IOException e) {
               throw e;
           } finally {
               is.close();
               bos.close();
           }
           
       }
       
       log.info("Response Data "+respString);
   } catch (IOException ex) {
       throw ex;
   } finally {
       httpClient.getConnectionManager().shutdown();
   }
   return respString;
}

public String doPost(String uri, Map<String,String> paramsMap, String encoding) throws IOException {
   String respString = "";
   
   if (CommonHelper.isEmpty(uri)) return respString;
   if (CommonHelper.isEmpty(encoding)) respString = "utf-8";
   
   log.info("Request URL "+uri);
   
   HttpClient httpClient = new DefaultHttpClient();
   try {
       setHttpClientParametersWithDefaultValue(httpClient);
       HttpPost httpPost = new HttpPost(uri);
       httpPost.setHeader("Connection", "close");
       List<BasicNameValuePair> paramsList = new ArrayList<BasicNameValuePair>();
       
       for (Map.Entry<String,String> me : paramsMap.entrySet()) {
           paramsList.add(new BasicNameValuePair(me.getKey(), me.getValue()));
       }
       
       UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramsList, encoding);
       httpPost.setEntity(entity);
       HttpResponse response;
       response = httpClient.execute(httpPost);
       if (response.getEntity() != null) {
           InputStream is = response.getEntity().getContent();
           ByteArrayOutputStream bos = new ByteArrayOutputStream();
           try {
               byte[] buffer = new byte[1024];
               int len;

               while ((len = is.read(buffer)) > 0) {
                   bos.write(buffer, 0, len);
               }

               respString = bos.toString(encoding);
           } catch (IOException e) {
               throw e;
           } finally {
               is.close();
               bos.close();
           }
       }
       
       log.info("Response Data : "+ respString);
   } catch (IOException ex) {
       throw ex;
   } finally {
       httpClient.getConnectionManager().shutdown();
   }
   
   return respString;
}

public String doPostWithSSL(String uri, String data, String encoding) throws IOException {
   String respString = "";
   
   if (CommonHelper.isEmpty(uri)) return respString;
   if (CommonHelper.isEmpty(encoding)) respString = "utf-8";
   
   log.info("Request URL "+uri);
   
   HttpClient httpClient = new DefaultHttpClient();
   try {
       setHttpClientParametersWithDefaultValue(httpClient);
       
       SSLSocketFactory sslSocketFactory = getSSLSocketFactory(KEY_STROE_PATH, TRUSTED_STORE_PATH, KEY_STROE_TYPE, TRUSTED_STORE_TYPE, KEY_STORE_PASSWORD, ALGORITHM, SSL_PROTOCOL);
       Scheme https = new Scheme("https", HTTPS_PORT, sslSocketFactory);
       
       httpClient.getConnectionManager().getSchemeRegistry().register(https);
       
       HttpPost httpPost = new HttpPost(uri);
       httpPost.setHeader("Connection", "close");
       StringEntity stringEntity = new StringEntity(data, encoding);
       httpPost.setHeader("Content-Type", "text/xml");
       
       httpPost.setEntity(stringEntity);
       HttpResponse response;
       response = httpClient.execute(httpPost);
       if (response.getEntity() != null) {
           InputStream is = response.getEntity().getContent();
           ByteArrayOutputStream bos = new ByteArrayOutputStream();
           try {
               byte[] buffer = new byte[1024];
               int len;

               while ((len = is.read(buffer)) > 0) {
                   bos.write(buffer, 0, len);
               }

               respString = bos.toString(encoding);
           } catch (IOException e) {
               throw e;
           } finally {
               is.close();
               bos.close();
           }
       }
       
       log.info("Response Data : "+ respString);
   } catch (IOException ex) {
       throw ex;
   } catch (Exception ex) {
       throw new RuntimeException(ex);
   } finally {
       httpClient.getConnectionManager().shutdown();
   }
   
   return respString;
}

/**
* urlencode编码链接参数值 （这个不编码= ? & 等符号，相当于浏览器的url编码）
* @param uri
* @param encoding
* @return urlencode编码后的uri
* @throws UnsupportedEncodingException 
*/
public static String encodingURIParams(String uri,String encoding) throws UnsupportedEncodingException {
   
   if (uri == null || "".equals(uri)) return uri;
   
   if (encoding == null || "".equals(encoding))  encoding = "utf-8";
   
   if (uri.indexOf("?") > -1) {
       String url = uri.substring(0,uri.indexOf("?"));
       String paramsString = uri.substring(uri.indexOf("?")+1);
       
       String[] paramsGroup = paramsString.split("&"); 
       String paramName;
       String paramValue;
       String[] paramKeyAndValue;
       
       paramsString = "";
       
       for (String param : paramsGroup) {
           
           if (param.indexOf("=") == -1 || param.indexOf("=") == param.length() - 1) {
               paramsString += param;
           } else {
               paramName = param.split("=")[0];
               paramValue = param.split("=")[1];
               paramValue = java.net.URLEncoder.encode(paramValue, encoding);
               paramsString += paramName + "=" + paramValue;
           }
           
           paramsString += "&";
       }
       
       if (!CommonHelper.isEmpty(paramsString) && paramsString.lastIndexOf("&")+1 == paramsString.length()) 
           paramsString = paramsString.substring(0, paramsString.lastIndexOf("&"));
       
       uri = url + "?" + paramsString;
   } 
   
   return uri;
}

/**
* 添加http 头协议
* @param uri
* @return String
*/
 public static String autoFixProtocolHeader(String uri) {

     if (uri == null) {
         return uri;
     }

     if (!uri.startsWith("http://") && !uri.startsWith("https://")) {
         uri = "http://" + uri;
     }

     return uri;
 }

 /**
  * 设置HttpClient得链接属性
  * 
  * @param httpClient 
  */
 public void setHttpClientParametersWithDefaultValue(HttpClient httpClient) {
     httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, HTTP_CONNECTION_TIMEOUT);
     httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, HTTP_SOCKET_TIMEOUT);
 }

 public static String getRequestClientIp(HttpServletRequest request){
     String ip = "";
     
     if (request == null) return ip;
     
     ip = request.getHeader("x-forwarded-for");

     if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
         ip = request.getHeader("Proxy-Client-IP");

         if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
             ip = request.getHeader("WL-Proxy-Client-IP");

             if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                 ip = request.getRemoteAddr();
             }
         }
     }
     
     
     
     return ip;
 }
 
 
 /**
	 * 配置SSLSocketFactory
	 * 
	 * @param conn
	 * @param keyStorePath
	 * @param trustedCertPath
	 * @param password
	 * @param algorithm
	 * @param sslProtocol
	 * @throws NoSuchAlgorithmException
	 * @throws KeyStoreException
	 * @throws CertificateException
	 * @throws IOException
	 * @throws UnrecoverableKeyException
	 * @throws KeyManagementException
	 */
 private static SSLSocketFactory getSSLSocketFactory(String keyStorePath, String trustedStorePath, String keyStoreType, String trustedStoreType, String password, String algorithm, String sslProtocol) throws NoSuchAlgorithmException, KeyStoreException, CertificateException, IOException, UnrecoverableKeyException, KeyManagementException {
     KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(algorithm);
     TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(algorithm);

     KeyStore keyStore = getKeyStore(keyStorePath, password, keyStoreType);
     KeyStore trustedStore = getKeyStore(trustedStorePath, password, trustedStoreType);

     if (password == null) {
             keyManagerFactory.init(keyStore, null);
     } else {
             keyManagerFactory.init(keyStore, password.toCharArray());
     }

     trustManagerFactory.init(trustedStore);

     SSLContext sslContext = SSLContext.getInstance(sslProtocol);
     sslContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), new SecureRandom());

     
     SSLSocketFactory sslSocketFactory = new SSLSocketFactory(sslContext);
     return sslSocketFactory;
 }
	
 private static KeyStore getKeyStore(String keyStorePath, String password, String keyStoreType) throws KeyStoreException, NoSuchAlgorithmException, CertificateException {
     
     KeyStore keyStore = null;
     if (keyStoreType == null || "".equals(keyStoreType)) {
         keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
     } else {
         keyStore = KeyStore.getInstance(keyStoreType);
     }
     

     FileInputStream fis = null;
     try {
             fis = new FileInputStream(keyStorePath);

             if (password == null) {
                     keyStore.load(fis, null);
             } else {
                     keyStore.load(fis, password.toCharArray());
             }
     } catch (IOException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();

             if (fis != null) {
                     try {
                             fis.close();
                     } catch (IOException e1) {
                             // TODO Auto-generated catch block
                             e1.printStackTrace();
                     }
                     fis = null;
             }
     }


     return keyStore;
 }
}

