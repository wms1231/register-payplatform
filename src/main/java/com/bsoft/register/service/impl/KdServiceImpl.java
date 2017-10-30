package com.bsoft.register.service.impl;

import java.net.URLEncoder;
import java.sql.Blob;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bsoft.constant.AppCommonConst;
import com.bsoft.exception.HandPayException;
import com.bsoft.register.service.KdService;
import com.bsoft.support.service.ICommonService;
import com.bsoft.tools.CharacterEncodeUtil;
import com.bsoft.tools.DateUtils;
import com.bsoft.tools.DecryptUtil;
import com.bsoft.tools.FastJsonUtil;
import com.bsoft.tools.RequestDataUtil;
import com.bsoft.tools.ResultMessageUtil;
import com.bsoft.tools.http.HttpRequestProxy;

@Service
public class KdServiceImpl implements KdService {

	private static Logger logger = Logger.getLogger(KdServiceImpl.class);

	@Autowired
	private ICommonService commonService;

	/**
	 * 调用快递存储过程
	 * 
	 * @param sqlKey
	 * @param dataSource
	 * @param param
	 * @return
	 */
	public Map<String, Object> callHandPayPro(String sqlKey, String dataSource, Map<String, Object> param) {
		commonService.selectOne(sqlKey, dataSource, param);
		return param;
	}

	/**
	 * 调用存储过程
	 * 
	 * @param sqlKey
	 * @param dataSource
	 * @param requestMap
	 * @param param
	 * @return
	 */
	public String invokeProc(String sqlKey, String dataSource, Map<String, Object> requestMap,
			Map<String, Object> param) {
		String requestStr = FastJsonUtil.toJSONString(requestMap);
		param.put("input", CharacterEncodeUtil.requestEncode(requestStr));
		param.put("msg", "");
		param.put("output", new Object());

		callHandPayPro(sqlKey, dataSource, param);

		String msg = (String) param.get("msg");
		Blob output = (Blob) param.get("output");
		String result = "";
		// msg为空表示正确调用
		if (StringUtils.isBlank(msg)) {
			result = CharacterEncodeUtil.returnEncode(output);
		} else {
			result = CharacterEncodeUtil.returnEncode(msg);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> requestCheck(Date beginTime, String methodName, HttpServletRequest request) {

		String timestamp = request.getParameter("timestamp");

		// 获得app发送来的请求acl
		String acl = request.getParameter("acl");

		// 参数为空直接返回参数为空的异常信息
		if (StringUtils.isBlank(timestamp) || StringUtils.isBlank(acl)) {
			logger.debug("timestampacl或acl参数为空" + "timestamp为:" + timestamp + " acl为:" + acl);
			return ResultMessageUtil.getParamErrorMap(null);
		}
		String aclContent = null;
		try {
			aclContent = IOUtils.toString(request.getInputStream(), "UTF-8"); // 读acl流数据
		} catch (Exception e) {
			logger.error("读取acl流数据发生异常,异常信息为:" + e.getMessage());
			return ResultMessageUtil.getSpecialOtherErrorMap(null, "读取acl流数据发生异常");
		}

		// 解密
		String jsonStr = "";
		try {

			String key = AppCommonConst.SYSTEM_APP_SECRET_KEY + timestamp;// 秘钥
			jsonStr = DecryptUtil.decryptToString(key, aclContent);// 解密
		} catch (Exception e) {
			logger.error("解密数据发生异常,异常信息为:" + e.getMessage());
			return ResultMessageUtil.getDecryptFailMap(null);
		}

		try {
			// 安全性检测
			String checkAcl = HttpRequestProxy.getACL(AppCommonConst.SYSTEM_APP_OAUTH_NAME, timestamp, jsonStr);
			// 进行url加密，比如 / 转码为 %2F
			String encodeAcl = URLEncoder.encode(acl, "utf-8");

			if (StringUtils.isNotBlank(checkAcl) || StringUtils.isNotBlank(encodeAcl)) {
				if (!checkAcl.equals(encodeAcl)) {
					logger.error("检测不通过,检测结果" + checkAcl.equals(encodeAcl) + "checkAcl为:" + checkAcl + " encodeAcl为:"
							+ encodeAcl);
					return ResultMessageUtil.getSpecialOtherErrorMap(null, "安全性检测不发通过");
				}
			}

		} catch (Exception e) {
			logger.error("url加密异常，加密url:" + acl, e);
			return ResultMessageUtil.getOtherErrorMap(null);
		}

		// 解密后记录开始调用时间
		logger.info("开始调用接口" + methodName + ":时间为:" + DateUtils.convertDateTime_YYYYMMDDHHMMSS_CN(beginTime) + ",请求信息为:"
				+ jsonStr + ",时间标记为[" + beginTime.getTime() + "]");

		Map<String, Object> requestMap = FastJsonUtil.toJSONObject(jsonStr, Map.class);
		if (requestMap == null) {
			return ResultMessageUtil.getParamErrorMap("json请求参数不符合规范", null);
		}
		requestMap.put("code2", "200");
		return requestMap;
	}

	@Override
	public String invokeCommonService(String methodName, String proNum, String proName, String sqlKey,
			Map<String, Object> requestMap, Date beginTime) {
		String result = null;
		try {
			if (StringUtils.isNotBlank(proNum)) {
				// 添加标识编码
				requestMap.put("proNum", proNum);
			}
			result = null;
			// 处理公共的服务
			result = invokeProc(sqlKey, null, requestMap,
					RequestDataUtil.getMapByInputParam(Arrays.asList("proName"), Arrays.asList(proName)));
		} catch (Exception e) {
			throw new HandPayException(e.getMessage());
		}

		Date endTime = new Date();
		logger.info("调用服务接口结束" + methodName + ":时间为:" + DateUtils.convertDateTime_YYYYMMDDHHMMSS_CN(endTime) + ",请求信息为:"
				+ result + ",时间标记为[" + endTime.getTime() + "],调用接口消费了" + (endTime.getTime() - beginTime.getTime())
				+ "毫秒");
		return result;
	}

}
