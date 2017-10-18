package com.bsoft.tools.c3p0;

import java.sql.Connection;  
import java.sql.PreparedStatement;  
import java.sql.ResultSet;  
import java.sql.SQLException;  
  
public class PoolThread extends Thread {  
    @Override  
    public void run(){  
        ConnectionPool pool = ConnectionPool.getInstance();  
        Connection con = null;  
        PreparedStatement stmt= null;  
        ResultSet rs = null;  
        try{  
            con = pool.getConnection();  
            stmt = con.prepareStatement("select sysdate as nowtime from dual");  
            rs = stmt.executeQuery();  
            while(rs.next()){  
                System.out.println(Thread.currentThread().getId()+"---------------开始"+rs.getString("nowtime"));  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }finally{  
            try {  
                rs.close();  
                stmt.close();  
                con.close();  
            } catch (SQLException e) {  
                e.printStackTrace();  
            }  
        }  
        System.out.println(Thread.currentThread().getId()+"--------结束");  
    }  
}  