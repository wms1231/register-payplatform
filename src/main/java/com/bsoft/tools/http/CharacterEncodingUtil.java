package com.bsoft.tools.http;
import java.io.UnsupportedEncodingException;

public class CharacterEncodingUtil {
	
	public static String gbtoiso(String strgb) {  
	    String striso = null;  
	  
	    try {  
	        striso = new String(strgb.getBytes("GBK"), "iso-8859-1");  
	    } catch (UnsupportedEncodingException e) {  
	        // log.info(e.getMessage());  
	    } catch (Exception e) {  
	        // log.info(e.getMessage());  
	    }  
	    return striso;  
	}  
	
	public static String isotogb(String striso) {  
        String strgb = null;  
        try {  
            if (striso != null && !striso.equals("null"))  
                strgb = new String(striso.getBytes("iso-8859-1"), "GBK");  
            else  
                strgb = "";  
        } catch (UnsupportedEncodingException e) {  
//            log.info(e.getMessage());  
        }  
        return strgb;  
    }  

}
