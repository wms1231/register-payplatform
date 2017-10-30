
package com.bsoft.register.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bsoft.constant.AppCommonConst;
import com.bsoft.register.service.ReportService;
import com.bsoft.tools.DateUtils;
import com.bsoft.tools.DecryptUtil;
import com.bsoft.tools.FastJsonUtil;
import com.bsoft.tools.ResultMessageUtil;
import com.bsoft.tools.http.HttpRequestProxy;

/**
 * 接入东华webservice服务接口
 * 
 * @author asus
 *
 */
@Controller
@RequestMapping("/report")
public class CheckReportController {
	private static Logger logger = Logger.getLogger(CheckReportController.class);
	@Autowired
	private ReportService checkReportService;

	@RequestMapping(value = "/checkReport", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String checkReport(HttpServletRequest request) {

		// 记录接口调用开始时间
		Date beginTime = new Date();
		// 打印请求信息
		logger.info("开始调用接口checkReport:" + "时间为:" + DateUtils.convertDateTime_YYYYMMDDHHMMSS_CN(beginTime) + ",请求信息为:"
				+ request + ",时间标记为[" + beginTime.getTime() + "]");
		// 获得请求时间
		String timestamp = request.getParameter("timestamp");
		// 获得app发送来的请求acl
		String acl = request.getParameter("acl");
		// 参数为空直接返回参数为空的异常信息
		if (StringUtils.isBlank(timestamp) || StringUtils.isBlank(acl)) {
			logger.debug("timestampacl或acl参数为空" + "timestamp为:" + timestamp + " acl为:" + acl);
			Map<String, Object> paramErrorMap = ResultMessageUtil.getParamErrorMap(new ArrayList<Object>());
			String paramErrorMapMsg = FastJsonUtil.toJSONString(paramErrorMap);
			return paramErrorMapMsg;
		}

		String aclContent = null;
		// 读acl流数据
		try {
			aclContent = IOUtils.toString(request.getInputStream(), "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("读取acl流数据发生异常,异常信息为:" + e.getMessage());
			Map<String, Object> specialOtherErrorMap = ResultMessageUtil
					.getSpecialOtherErrorMap(new ArrayList<Object>(), "读取acl流数据发生异常");
			String OtherErrorMapMsg = FastJsonUtil.toJSONString(specialOtherErrorMap);
			return OtherErrorMapMsg;

		}

		// 解密
		String jsonStr = null;
		try {
			// 秘钥
			String key = AppCommonConst.SYSTEM_APP_SECRET_KEY + timestamp;
			// 解密
			jsonStr = DecryptUtil.decryptToString(key, aclContent);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("解密数据发生异常,异常信息为:" + e.getMessage());
			Map<String, Object> decryptFailMap = ResultMessageUtil.getDecryptFailMap(new ArrayList<Object>());
			String decryptFailMaptMsg = FastJsonUtil.toJSONString(decryptFailMap);
			return decryptFailMaptMsg;
		}

		// 安全性检测
		try {
			String checkAcl = HttpRequestProxy.getACL(AppCommonConst.SYSTEM_APP_OAUTH_NAME, timestamp, jsonStr);
			// 进行url加密，比如 / 转码为 %2F
			String encodeAcl = URLEncoder.encode(acl, "utf-8");
			if (StringUtils.isNotBlank(checkAcl) || StringUtils.isNotBlank(encodeAcl)) {
				if (!checkAcl.equals(encodeAcl)) {
					logger.debug("检测不通过,检测结果" + checkAcl.equals(encodeAcl) + "checkAcl为:" + checkAcl + " encodeAcl为:"
							+ encodeAcl);
					Map<String, Object> specialOtherErrorMap = ResultMessageUtil
							.getSpecialOtherErrorMap(new ArrayList<Object>(), "安全性检测不发通过");
					String OtherErrorMapMsg = FastJsonUtil.toJSONString(specialOtherErrorMap);
					return OtherErrorMapMsg;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("url加密异常，加密url:" + acl);
			Map<String, Object> otherErrorMap = ResultMessageUtil.getOtherErrorMap(new ArrayList<Object>());
			String otherErrorMapMsg = FastJsonUtil.toJSONString(otherErrorMap);
			return otherErrorMapMsg;
		}

		// 进行业务调用
		Long timestampValue = -1L;
		try {
			timestampValue = Long.valueOf(timestamp);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			timestampValue = -1L;
		}
		// 调用业务方法，设置返回结果
		String result = checkReportService.checkReportQuery(jsonStr, timestampValue);

		if (result.contains("ERR")) {// 说明业务处理失败
			logger.debug("业务处理失败,处理结果:" + result);
			Map<String, Object> specialServiceFailMap = ResultMessageUtil
					.getSpecialServiceFailMap(new ArrayList<Object>(), result);
			String specialServiceFailMapMsg = FastJsonUtil.toJSONString(specialServiceFailMap);
			return specialServiceFailMapMsg;
		}

		// 记录接口调用结束时间
		Date endTime = new Date();
		logger.info("开始调用接口checkReport:" + "时间为:" + DateUtils.convertDateTime_YYYYMMDDHHMMSS_CN(endTime) + ",请求信息为:"
				+ request + ",时间标记为[" + endTime.getTime() + "],调用接口消费了" + (endTime.getTime() - beginTime.getTime())
				+ "毫秒");

		return result;
	}

	@RequestMapping(value = "/sendLisResult", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String sendLisResult(HttpServletRequest request) {
		String patientName = request.getParameter("name");
		String orgId = request.getParameter("orgId");

		BufferedReader br = null;
		try {
			InputStream inputStream = request.getInputStream();
			int read = inputStream.read();

			br = new BufferedReader(new InputStreamReader(inputStream));
			String line = null;
			StringBuffer sb = new StringBuffer();
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			String returnMsg = sb.toString();
			if (StringUtils.isNotBlank(returnMsg)) {
				//String result = HttpRequestProxy.doPost(service, method, param);
				
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return "bsoft ok";
	}

}
