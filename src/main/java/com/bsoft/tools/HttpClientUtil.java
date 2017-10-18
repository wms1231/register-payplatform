/**
 * 
 */
package com.bsoft.tools;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;  
/* 
 * 利用HttpClient进行post请求的工具类 
 */  
public class HttpClientUtil {  
	
	/**
	 * url地址，传输map<String,String> 参数，charset编码
	 * @param url
	 * @param map
	 * @param charset
	 * @return
	 */
    public String doPost(String url,Map<String,String> map,String charset){
        HttpClient httpClient = null;  
        HttpPost httpPost = null;  
        String result = null;  
        try{  
            httpClient = new SSLClient();  
            httpPost = new HttpPost(url);  
            //设置参数  
//            List<NameValuePair> list = new ArrayList<NameValuePair>();  
//            Iterator iterator = map.entrySet().iterator();  
//            while(iterator.hasNext()){  
//                Entry<String,String> elem = (Entry<String, String>) iterator.next();  
//                list.add(new BasicNameValuePair(elem.getKey(),elem.getValue()));  
//            }  
//            if(list.size() > 0){  
//                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,charset);  
//                httpPost.setEntity(entity);  
//            }  
            HttpResponse response = httpClient.execute(httpPost);  
            if(response != null){  
                HttpEntity resEntity = response.getEntity();  
                if(resEntity != null){  
                    result = EntityUtils.toString(resEntity,charset);  
                }  
            }  
        }catch(Exception ex){  
            ex.printStackTrace();  
        }  
        return result;  
    }  
    
    /**
     * url地址，传输string 参数，charset编码
     * @param url
     * @param str
     * @param charset
     * @return
     */
    public String doPost(String url,String str,String charset){  
        HttpClient httpClient = null;  
        HttpPost httpPost = null;  
        String result = null;  
        try{  
            httpClient = new SSLClient();  
            httpPost = new HttpPost(url); 
            //设置参数  
            HttpEntity entity = new StringEntity(str,charset);
			httpPost.setEntity(entity);
            HttpResponse response = httpClient.execute(httpPost);  
            if(response != null){  
                HttpEntity resEntity = response.getEntity();  
                if(resEntity != null){  
                    result = EntityUtils.toString(resEntity,charset);  
                }  
            }  
        }catch(Exception ex){  
            ex.printStackTrace();  
        }
        
        return result;  
    }  
    
    
    /**
     * 下载支付宝对账单
     * @param strurl
     * @param filePath
     * @param charset
     * @return
     */
    public static InputStream ali_bill_download(String bill_download_url,String charset){  
    	boolean bool = false;
    	URL url = null;
		HttpURLConnection httpUrlConnection = null;
		InputStream fis = null;
//		FileOutputStream fos = null;
		try {
		    url = new URL(bill_download_url);
		    httpUrlConnection = (HttpURLConnection) url.openConnection();
		    httpUrlConnection.setConnectTimeout(5 * 1000);
		    httpUrlConnection.setDoInput(true);
		    httpUrlConnection.setDoOutput(true);
		    httpUrlConnection.setUseCaches(false);
		    httpUrlConnection.setRequestMethod("GET");
		    httpUrlConnection.setRequestProperty("Charsert", charset);
		    httpUrlConnection.connect();
			return httpUrlConnection.getInputStream();
//		    byte[] temp = new byte[1024];
//		    int b;
//		    fos = new FileOutputStream(new File(filePath));
//		    while ((b = fis.read(temp)) != -1) {
//		        fos.write(temp, 0, b);
//		        fos.flush();
//		    }
//		    bool = true;
		} catch (MalformedURLException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		} finally {
//		    try {
//		        if(fis!=null) fis.close();
//		        if(fos!=null) fos.close();
//		        if(httpUrlConnection!=null) httpUrlConnection.disconnect();
//		    } catch (IOException e) {
//		        e.printStackTrace();
//		    }
		}
		return null;
    }  
    
    /**
     * 解压zip,并从流中读取文件
     * @param file
     * @throws Exception
     */
    public static void readZipFile(String file) throws Exception {  
        ZipFile zf = new ZipFile(file);  
        InputStream in = new BufferedInputStream(new FileInputStream(file));  
        ZipInputStream zin = new ZipInputStream(in);  
        ZipEntry ze;  
        while ((ze = zin.getNextEntry()) != null) {  
            if (ze.isDirectory()) {
            } else {  
                System.err.println("file - " + ze.getName() + " : "  
                        + ze.getSize() + " bytes");  
                long size = ze.getSize();  
                if (size > 0) {  
                    BufferedReader br = new BufferedReader(  
                            new InputStreamReader(zf.getInputStream(ze)));  
                    String line;  
                    while ((line = br.readLine()) != null) {  
                        System.out.println(line);  
                    }  
                    br.close();  
                }  
                System.out.println();  
            }  
        }  
        zin.closeEntry();  
    }  
}  