package com.bsoft.tools.c3p0;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.jfree.util.Log;

public class DBConnection {
	public static Connection con = null;
	public static Statement stmt = null;
	public PreparedStatement ps = null;
	public static ResultSet rs = null;
	public static String DbError = "";
	String sql = null;
	public String ArrayStr[];

	private static Logger logger = Logger.getLogger(DBConnection.class);

	public DBConnection() {
		super();
		con = getConnection("c3p0");
		// con = getConnection("jdbc");
	}

	/**
	 * 获取数据库连接
	 * 
	 * @return Connection
	 */
	public Connection getConnection(String type) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection con = null;
		try {
			if (type.equalsIgnoreCase("jdbc")) {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con = DriverManager.getConnection("jdbc:oracle:thin:@32.33.1.78:1521:orcl", "test", "test");
			} else {
				con = pool.getConnection();
				logger.info("获取数据库连接池连接成功!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取数据库连接池连接失败!" + e.getMessage());
			this.closeConn();
		}
		return con;
	}

	// 查询数据,返回记录集
	public String executePrepareCall(String ProceduresName, String request) {
		logger.info("调用存储过程:" + ProceduresName);
		String response = "ERR999";
		CallableStatement cst = null;
		try {
			request = new String(request.getBytes("GBK"), "ISO-8859-1");
			cst = con.prepareCall("{call " + ProceduresName + "(?,?,?)}");
			// as_input in varchar,as_output out varchar,aclb_output out Blob
			cst.setString(1, request);
			// 注册存储过程的第二个参数
			cst.registerOutParameter(2, java.sql.Types.VARCHAR);
			cst.registerOutParameter(3, java.sql.Types.BLOB);
			// 执行存储过程
			long startTime = System.currentTimeMillis();
			cst.execute();
			long endTime = System.currentTimeMillis();
			System.out.println("调用存储过程一共运行了:" + (endTime - startTime) + "毫秒.");
			// 得到存储过程的输出参数值
			String xml = cst.getString(2);
			Blob blob = cst.getBlob(3);
			if (blob == null) {
				if (xml == null) {
					logger.error("获取返回值失败,返回值全部为空!");
					return "ERR400";
				} else {
					return new String(xml.getBytes("ISO-8859-1"), "GBK");
				}
			} else {
				try {
					// String blobStr = new String(blob.getBytes((long) 1,
					// (int) blob.length()));
					// response = new String(blobStr.getBytes("ISO-8859-1"),
					// "GBK");//第一种方法返回失败
					response = new String(blob.getBytes((long) 1, (int) blob.length()), "GBK");// 第二套方法
																								// 目前成功
					// response =convertStr(blob,"GBK");
					return response;
				} catch (SQLException e) {
					e.printStackTrace();
					logger.error("获取blob字段失败:" + e.getMessage());
					return "ERR500";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("调用存储过程失败:" + e.getMessage());
			return response = "ERR600";
		} finally {
			if (cst != null) {
				try {
					cst.close();
				} catch (SQLException e) {
					logger.error("关闭连接失败:" + e.getMessage());
					e.printStackTrace();
				}
				cst = null;
			}
		}
	}

	// 查询数据,返回记录集2
	public String executePrepareCall2(String ProceduresName, String request) {
		logger.info("调用存储过程:" + ProceduresName);
		String response = "ERR999";
		CallableStatement cst = null;
		try {
			request = new String(request.getBytes("GBK"), "ISO-8859-1");
			cst = con.prepareCall("{call " + ProceduresName + "(?,?,?)}");
			// 注册存储过程的第二个参数
			cst.setString(1, request);
			cst.registerOutParameter(2, java.sql.Types.VARCHAR);
			cst.registerOutParameter(3, java.sql.Types.VARCHAR);
			// 执行存储过程
			long startTime = System.currentTimeMillis();
			cst.execute();
			long endTime = System.currentTimeMillis();
			Log.info("调用存储过程一共运行了:" + (endTime - startTime) + "毫秒");
			// 得到存储过程的输出参数值
			String asCode = cst.getString(2);
			String asMsg = cst.getString(3);
			
			if ("0".equals(asCode)) {//成功返回
				// as_Code := '0'; as_Msg := '交易成功';
				response = "{\"code\":\"200\",\"msg\":\"交易成功\"}";
				return response;
			}
			// 无论成功和失败直接返回
			response = new String(asMsg.getBytes("ISO-8859-1"), "GBK");
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("调用存储过程失败:" + e.getMessage());
			return response = "ERR600";

		} finally {
			if (cst != null) {
				try {
					cst.close();
				} catch (SQLException e) {
					logger.error("关闭连接失败:" + e.getMessage());
					e.printStackTrace();
				}
				cst = null;
			}
		}
	}

	// 查询数据,返回记录集3
	// 查询数据,返回记录集
	public String executePrepareCall3(String ProceduresName, String request) {
		logger.info("调用存储过程:" + ProceduresName);
		String response = "ERR999";
		CallableStatement cst = null;
		try {
			request = new String(request.getBytes("GBK"), "ISO-8859-1");
			cst = con.prepareCall("{call " + ProceduresName + "(?,?,?,?)}");
			// as_input in varchar2,as_Code out varchar2,as_Msg out
			// varchar2,as_output out varchar2
			cst.setString(1, request);
			// 注册存储过程的第二个参数
			cst.registerOutParameter(2, java.sql.Types.VARCHAR);
			cst.registerOutParameter(3, java.sql.Types.VARCHAR);
			cst.registerOutParameter(4, java.sql.Types.VARCHAR);
			// 执行存储过程
			long startTime = System.currentTimeMillis();
			cst.execute();
			long endTime = System.currentTimeMillis();
			System.out.println("调用存储过程executePrepareCall3一共运行了:" + (endTime - startTime) + "毫秒.");
			// 得到存储过程的输出参数值
			/*
			 * String returnCode = cst.getString(2); String returnMsg =
			 * cst.getString(3);
			 */
			String returnOutput = cst.getString(4);

			// success or fail return
			response = new String(returnOutput.getBytes("ISO-8859-1"), "GBK");
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("调用存储过程失败:" + e.getMessage());
			return response = "ERR600";
		} finally {
			if (cst != null) {
				try {
					cst.close();
				} catch (SQLException e) {
					logger.error("关闭连接失败:" + e.getMessage());
					e.printStackTrace();
				}
				cst = null;
			}
		}
	}

	

	public static String convertStr(Blob inBlob, String sourcecs) throws SQLException, IOException {
		Reader in = new InputStreamReader(inBlob.getBinaryStream(), sourcecs);
		StringBuffer outStr = new StringBuffer();
		int i, c;
		i = 0;
		while (((c = in.read()) != -1) & i < 1000) {
			outStr.append((char) c);
			i++;
		}
		in.close();
		return outStr.toString();
	}

	// 查询数据,返回记录集
	public ResultSet executeQuery(String sql) {
		System.out.println("sql==" + sql);
		DbError = "";
		try {
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(sql);
		} catch (Exception e) {
			e.printStackTrace();
			DbError = e.getMessage();
			rs = null;
		}
		return rs;
	}

	// 查询数据,返回记录集
	public ResultSet executeQueryPage(String sql, int start, int skip) {
		sql = "select * from (select temp.*,rownum num from ( " + sql + ") temp where rownum < " + (start + skip)
				+ ") where num >=" + start;
		System.out.println("sql==" + sql);
		DbError = "";
		try {

			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(sql);

		} catch (Exception e) {
			e.printStackTrace();
			DbError = e.getMessage();
			rs = null;
		}

		return rs;
	}

	// 查询数据,返回记录集
	public int executeQueryPageCount(String sql, int start, int skip) {
		sql = "select count(*) from (select temp.*,rownum num from ( " + sql + ") temp where rownum < " + (start + skip)
				+ ") where num >=" + start;
		int num = 0;
		System.out.println("sql==" + sql);
		DbError = "";
		try {

			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				num = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			DbError = e.getMessage();
			rs = null;
		}
		return num;
	}

	// 查询数据记录条数
	public long executeCount(String sql) {
		long ll_num = 0;
		DbError = "";
		try {

			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(sql);
			if (rs.next())
				ll_num = rs.getLong(1);

		} catch (Exception e) {
			e.printStackTrace();
			DbError = e.getMessage();
			rs = null;
		}

		return ll_num;
	}

	// 查询数据（使用过程）,返回多个记录集
	public ResultSet executeProcByPage(String sql, long al_page, long al_row) {
		CallableStatement cstmt = null;
		DbError = "";
		int i = 0;
		rs = null;
		try {
			cstmt = con.prepareCall("{call GetRecordSet(?,?,?)}");
			cstmt.setString(1, sql);
			cstmt.setLong(2, al_page);
			cstmt.setLong(3, al_row);
			cstmt.execute();

			boolean hasMoreResult = true;
			boolean lbfirst = false;
			while (hasMoreResult) {
				i++;
				rs = cstmt.getResultSet();

				if (i == 1) {
					if (rs.next()) {
						ArrayStr[0] = rs.getInt(1) + ""; // 得到页数
						i++;
						lbfirst = true;
					}
				}

				if (i == 2 && lbfirst == false) {
					rs.next();
					ArrayStr[0] = rs.getInt(1) + ""; // 得到页数
				}
				if (i == 3) {
					rs.next();
					ArrayStr[1] = rs.getInt(1) + ""; // 得到总行数
				}
				if (i == 4)
					return rs;
				hasMoreResult = cstmt.getMoreResults();
			}
		} catch (Exception e) {
			e.printStackTrace();
			DbError = e.getMessage();
		}
		return rs;
	}

	public long executeSql(String sql) {
		DbError = "";
		long num = 0;
		try {
			con.setAutoCommit(false);
			stmt = con.createStatement();
			num = stmt.executeUpdate(sql);
			con.commit();
			if (num > 1)
				num = 1;
		} catch (Exception e) {
			DbError = e.getMessage();
			if (DbError.indexOf("违反了 PRIMARY KEY 约束") >= 0) {
				DbError = "数据有重复，请检查重复的数据,详细原因：    [" + DbError + "]";
			}
			try {
				con.rollback();
			} catch (Exception f) {
				DbError += f.getMessage();
			}
			num = -1;
		}
		return num;
	}

	public long executeSql_UnCommit(String sql) {
		DbError = "";
		long num = 0;
		try {
			stmt = con.createStatement();
			num = stmt.executeUpdate(sql);
			num = 1;
		} catch (Exception e) {
			e.printStackTrace();
			DbError = e.getMessage();
			if (DbError.indexOf("违反了 PRIMARY KEY 约束") >= 0) {
				DbError = "数据有重复，请检查重复的数据，详细原因：  [" + DbError + "]";
			}
			num = -1;
		}
		return num;
	}

	public int Commit() {
		try {
			con.commit();
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}

	public void closeConn() {
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (stmt != null) {
				stmt.close();
				stmt = null;
			}
			if (ps != null) {
				ps.close();
				ps = null;
			}
			if (con != null) {
				con.close();
				con = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.equals("关闭连接失败:" + e.getMessage());
		} finally {
			con = null;
		}

	}

}