package com.bsoft.register.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.DocumentException;
import org.springframework.stereotype.Service;

import com.bsoft.register.service.CheckReportService;
import com.bsoft.tools.DateUtils;
import com.bsoft.tools.c3p0.DBConnection2;

/**
 * 提供检查报告查询
 * 
 * @author xy
 *
 */
@Service
public class CheckReportServiceImpl implements CheckReportService {
	private static Logger logger = Logger.getLogger(CheckReportServiceImpl.class);

	public String checkReportQuery(String responseXml, Long beginTime) {
		logger.info("请求参数为" + responseXml);
		// 存储过程的调用
		if (StringUtils.isNotBlank(responseXml)) {
			if (beginTime == null || beginTime == -1L) {
				beginTime = System.currentTimeMillis();
			}
			responseXml = invokeProcedure(beginTime, responseXml, "SPK_LIS_HCN1.BSLIS_USP_TMH");
		} else {
			logger.debug("responseXml参数请求异常,请求参数为:" + responseXml);
			responseXml = "ERR999";

		}
		logger.info("返回成功参数为" + responseXml);
		return responseXml;
	}

	/**
	 * 调用存储过程，返回xml数据
	 * 
	 * @param beginTime
	 * @param requestStr
	 * @return
	 * @throws DocumentException
	 */
	public String invokeProcedure(long beginTime, String requestStr, String proName) {
		// 打印请求信息
		logger.info("开始调用方法:" + "0000" + "时间为:" + DateUtils.getCurrentDate_YYYYMMDDHHMMSS() + ",请求信息为:" + requestStr
				+ ",时间标记为[" + beginTime + "]");
		// 获得连接
		DBConnection2 dbc = new DBConnection2();
		// 后期可以将常量抽取到一个常量类中
		String responseStr = null;
		try {
			// 调用存储过程
			responseStr = dbc.executePrepareCall(proName, requestStr);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 回收链接
			dbc.closeConn();
		}
		// 打印调试信息
		logger.info("调用方法:" + "0000" + "结束,时间为:" + DateUtils.getCurrentDate_YYYYMMDDHHMMSS() + ",返回信息为:" + responseStr
				+ ",时间标记为[" + beginTime + "]");
		return responseStr;
	}

}
