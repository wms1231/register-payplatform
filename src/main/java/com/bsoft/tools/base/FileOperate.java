package com.bsoft.tools.base;

public class FileOperate {

	public static String getWebinfLoca()  
    {  
        String path=Thread.currentThread().getContextClassLoader().getResource("").toString();  
        path=path.replace('/', '\\'); // 将/换成\  
        path=path.replace("file:", ""); //去掉file:  
        path=path.replace("classes\\", ""); //去掉class\  
        path=path.substring(1); //去掉第一个\,如 \D:\JavaWeb...  
        return path;  
    }  
}
