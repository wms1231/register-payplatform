/**
 * 
 */
package com.bsoft.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.net.ssl.HttpsURLConnection;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * http请求工具类
 * 
 * @author wms1231
 *
 */
public class HttpUtil {
	
    private static final Logger mLog = LogManager.getLogger(HttpUtil.class.getName());
    private final static int CONNECT_TIMEOUT = 5000; // in milliseconds  
    private final static String DEFAULT_ENCODING = "UTF-8";  
      
    public static String postData(String urlStr, String data){  
    	return new HttpClientUtil().doPost(urlStr, data, "UTF-8");
    }  
      
    public static String postData(String urlStr, String data, String contentType){  
        BufferedReader reader = null;  
        try {  
            URL url = new URL(urlStr);  
            URLConnection conn = url.openConnection();  
            conn.setDoOutput(true);  
            conn.setConnectTimeout(CONNECT_TIMEOUT);  
            conn.setReadTimeout(CONNECT_TIMEOUT);  
            if(contentType != null)  
                conn.setRequestProperty("content-type", contentType);  
            mLog.info("conn.getOutputStream()="+conn.getOutputStream());
            OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream(), DEFAULT_ENCODING);  
            if(data == null)  
                data = "";  
            writer.write(data);   
            writer.flush();  
            writer.close();    
  
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), DEFAULT_ENCODING));  
            StringBuilder sb = new StringBuilder();  
            String line = null;  
            while ((line = reader.readLine()) != null) {  
                sb.append(line);  
                sb.append("\r\n");  
            }  
            return sb.toString();  
        } catch (IOException e) {  
        	mLog.error("Error connecting to " + urlStr + ": " + e.getMessage());  
        } finally {  
            try {  
                if (reader != null)  
                    reader.close();  
            } catch (IOException e) {  
            }  
        }  
        return null;  
    }  
    
    
//    public static JSONObject httpsRequestToJsonObject(String requestUrl,
//            String requestMethod, String outputStr) {
//        JSONObject jsonObject = null;
//        try {
//            StringBuffer buffer = httpsRequest(requestUrl, requestMethod,
//                    outputStr);
//            jsonObject = JSONObject.fromObject(buffer.toString());
//        } catch (ConnectException ce) {
//            log.error("连接超时：" + ce.getMessage());
//        } catch (Exception e) {
//            log.error("https请求异常：" + e.getMessage());
//        }
//        return jsonObject;
//    }
    
    public static StringBuffer httpsRequest(String requestUrl,
            String requestMethod, String output)
            throws NoSuchAlgorithmException, NoSuchProviderException,
            KeyManagementException, MalformedURLException, IOException,
            ProtocolException, UnsupportedEncodingException {
        URL url = new URL(requestUrl);
        HttpsURLConnection connection = (HttpsURLConnection) url
                .openConnection();
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setUseCaches(false);
        connection.setRequestMethod(requestMethod);
        if (null != output) {
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(output.getBytes("UTF-8"));
            outputStream.close();
        }
        // 从输入流读取返回内容
        InputStream inputStream = connection.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(
                inputStream, "utf-8");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String str = null;
        StringBuffer buffer = new StringBuffer();
        while ((str = bufferedReader.readLine()) != null) {
            buffer.append(str);
        }
        bufferedReader.close();
        inputStreamReader.close();
        inputStream.close();
        inputStream = null;
        connection.disconnect();
        return buffer;
    }
    
}
