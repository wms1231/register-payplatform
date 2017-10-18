package com.bsoft.tools.http;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.*;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.CodingErrorAction;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;

/**
 * 网络请求
 */
public class HTTPClient {

	//默认超时 5s
	public final static int connectTimeout = 100000;

	private final static Logger logger = LoggerFactory.getLogger(HTTPClient.class);

	private static PoolingHttpClientConnectionManager connManager = null;

	private static CloseableHttpClient httpclient = null;

	static {
		try {
			SSLContext sslContext = SSLContexts.custom().useTLS().build();
			sslContext.init(null, new TrustManager[] { new X509TrustManager() {
				@Override
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}

				@Override
				public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
				}

				@Override
				public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
				}
			} }, null);
			Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create().register("http", PlainConnectionSocketFactory.INSTANCE).register("https", new SSLConnectionSocketFactory(sslContext)).build();

			connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
			httpclient = HttpClients.custom().setConnectionManager(connManager).build();
			// Create socket configuration
			SocketConfig socketConfig = SocketConfig.custom().setTcpNoDelay(true).build();
			connManager.setDefaultSocketConfig(socketConfig);
			// Create message constraints
			MessageConstraints messageConstraints = MessageConstraints.custom().setMaxHeaderCount(200).setMaxLineLength(2000).build();
			// Create connection configuration
			ConnectionConfig connectionConfig = ConnectionConfig.custom().setMalformedInputAction(CodingErrorAction.IGNORE).setUnmappableInputAction(CodingErrorAction.IGNORE).setCharset(Consts.UTF_8).setMessageConstraints(messageConstraints).build();
			connManager.setDefaultConnectionConfig(connectionConfig);
			connManager.setMaxTotal(200);
			connManager.setDefaultMaxPerRoute(20);
		} catch (KeyManagementException e) {
			logger.error("KeyManagementException", e);
		} catch (NoSuchAlgorithmException e) {
			logger.error("NoSuchAlgorithmException", e);
		}
	}

	public static String doPost(String url, Map<String, String> params) throws UnsupportedEncodingException {
		HttpPost post = new HttpPost(url);
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(connectTimeout).setConnectTimeout(connectTimeout).setConnectionRequestTimeout(connectTimeout).build();
		post.setConfig(requestConfig);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();

		Set<String> keySet = params.keySet();
		for (String key : keySet) {
			nvps.add(new BasicNameValuePair(key, params.get(key)));
		}

		try {
			post.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
			CloseableHttpResponse response = httpclient.execute(post);
			try {
				HttpEntity entity = response.getEntity();
				try {
					if (entity != null) {
						String str = EntityUtils.toString(entity, "UTF-8");
						logger.info("[HttpUtils Post]Debug response, url :" + url + " , response string :" + str);
						return str;
					}
				} finally {
					if (entity != null) {
						entity.getContent().close();
					}
				}
			} finally {
				if (response != null) {
					response.close();
				}
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			post.releaseConnection();
		}
		return "";
	}

	public static String doPost(String url, String json) {
		HttpPost post = new HttpPost(url);
		post.addHeader("Content-Type", "application/json;charset=utf-8");
		StringEntity s;
		try {
			s = new StringEntity(json,"UTF-8");
//			s.setContentEncoding("UTF-8");
//			s.setContentType("application/json");
			
			post.setEntity(s);

			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(connectTimeout).setConnectTimeout(connectTimeout).setConnectionRequestTimeout(connectTimeout).build();
			post.setConfig(requestConfig);

			CloseableHttpResponse response = httpclient.execute(post);
			try {
				HttpEntity entity = response.getEntity();
				try {
					if (entity != null) {
						String str = EntityUtils.toString(entity, "UTF-8");
						logger.info("[HttpUtils Post]Debug response, url :" + url + " , response string :" + str);
						return str;
					}
				} finally {
					if (entity != null) {
						entity.getContent().close();
					}
				}
			} finally {
				if (response != null) {
					response.close();
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			post.releaseConnection();
		}
		return "";
	}

    public static String doPostAndHeader(String url, String json,String token,String service,String method) {
        HttpPost post = new HttpPost(url);
        post.setHeader("X-Access-Token", token);
        post.setHeader("X-Service-Id", service);
        post.setHeader("X-Service-Method", method);
        post.setHeader("X-Security-Id", "tongxiang");
        post.addHeader("Content-Type", "application/json");
//        post.addHeader("Content-Type", "application/json;charset=utf-8");
        StringEntity s;
        try {
            s = new StringEntity(json,"UTF-8");
            post.setEntity(s);
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(connectTimeout).setConnectTimeout(connectTimeout).setConnectionRequestTimeout(connectTimeout).build();
            post.setConfig(requestConfig);
            CloseableHttpResponse response = httpclient.execute(post);
            try {
                HttpEntity entity = response.getEntity();
                try {
                    if (entity != null) {
                        String str = EntityUtils.toString(entity, "UTF-8");
                        logger.info("[HttpUtils Post]Debug response, url :" + url + " , response string :" + str);
                        return str;
                    }
                } finally {
                    if (entity != null) {
                        entity.getContent().close();
                    }
                }
            } finally {
                if (response != null) {
                    response.close();
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            post.releaseConnection();
        }
        return "";
    }

    public static String requestArray(String url, Object[] param, String token, String service, String method){
        String paramValue = "";
        if(param != null){
            paramValue = Arrays.toString(param);
        }
        logger.info("请求参数 :" + paramValue);
        long wsstart = System.currentTimeMillis();
        String result = doPostAndHeader(url+"*.jsonRequest", paramValue,token,service,method);
        long wsend = System.currentTimeMillis();
        logger.info("用时：" + (wsend - wsstart));
        logger.info("返回数据：" + result);
        return result;
    }

    public static String requestMap(String url, Map<String, Object> map, String token, String service, String method){
        StringBuilder param = new StringBuilder("[");
        String json = JSONObject.toJSONString(map);
        param.append(json).append("]");
        System.out.println("请求参数 :" + param.toString());
        long wsstart = System.currentTimeMillis();
        String result = doPostAndHeader(url+"*.jsonRequest", param.toString(),token,service,method);
        long wsend = System.currentTimeMillis();
        logger.info("用时：" + (wsend - wsstart));
        logger.info("返回数据：" + result);
        return result;
    }

    public static String requestList(String url,List<Map<String, Object>> list,String token,String service,String method){
        StringBuilder param = new StringBuilder("[");
        String json = JSONObject.toJSONString(list);
        param.append(json).append("]");
        System.out.println("请求参数 :" + param.toString());
        long wsstart = System.currentTimeMillis();
        String result = doPostAndHeader(url+"*.jsonRequest", param.toString(),token,service,method);
        long wsend = System.currentTimeMillis();
        logger.info("用时：" + (wsend - wsstart));
        logger.info("返回数据：" + result);
        return result;
    }

    public static String requestStrList(String url,List<String> list,String token,String service,String method){
        StringBuilder param = new StringBuilder("[");
        String json = JSONObject.toJSONString(list);
        param.append(json).append("]");
        System.out.println("请求参数 :" + param.toString());
        long wsstart = System.currentTimeMillis();
        String result = doPostAndHeader(url+"*.jsonRequest", param.toString(),token,service,method);
        long wsend = System.currentTimeMillis();
        logger.info("用时：" + (wsend - wsstart));
        logger.info("返回数据：" + result);
        return result;
    }

}
