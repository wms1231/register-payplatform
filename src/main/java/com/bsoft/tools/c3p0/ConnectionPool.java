package com.bsoft.tools.c3p0;

import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;
  
public class ConnectionPool {  
	public ComboPooledDataSource dataSource;
    private static ConnectionPool pool;  
    private ConnectionPool(){
  			 dataSource = new ComboPooledDataSource("bsoft2dh");//通过根目录的c3p0-config.xml获取
    		//dataSource = new ComboPooledDataSource("local");//通过根目录的c3p0-config.xml获取
			 dataSource.setMaxStatementsPerConnection(50);//连接池内单个连接所拥有的最大缓存Statement数
			 dataSource.setMaxStatements(0);//数据源内加载的PreparedStatement数量
			 dataSource.setNumHelperThreads(17);//C3P0是异步操作的，缓慢的JDBC操作通过帮助进程完成。扩展这些操作可以有效的提升性能，通过多线程实现多个操作同时被执行
			 dataSource.setAcquireIncrement(3);//当连接池中的连接用完时，C3P0一次性创建新连接的数目
			 dataSource.setAcquireRetryAttempts(3);//数据库获取新连接失败后重复尝试的次数
			 dataSource.setAcquireRetryDelay(1000);//两次连接中间隔时间,默认为1000毫秒
			 dataSource.setBreakAfterAcquireFailure(false);
    }  
    
    public static final ConnectionPool getInstance(){  
        if(pool==null){  
            try{  
                pool = new ConnectionPool();  
                System.out.println("创建新的pool......");
            }catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
        return pool;  
    }  
    public synchronized final Connection getConnection() {    
        try {  
            return dataSource.getConnection();  
        } catch (SQLException e) {       
            e.printStackTrace();  
        }  
        return null;  
    }  
      
}  