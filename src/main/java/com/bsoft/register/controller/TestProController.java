package com.bsoft.register.controller;

import java.net.URLEncoder;
import java.sql.Blob;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bsoft.constant.AppCommonConst;
import com.bsoft.support.Pager;
import com.bsoft.support.service.ICommonService;
import com.bsoft.tools.CharacterEncodeUtil;
import com.bsoft.tools.DateUtils;
import com.bsoft.tools.DecryptUtil;
import com.bsoft.tools.FastJsonUtil;
import com.bsoft.tools.ResultMessageUtil;
import com.bsoft.tools.http.HttpRequestProxy;

/**
 * 测试使用
 * 
 * @author wms1231
 *
 */
@Controller
@RequestMapping("/test")
public class TestProController {
	private static Logger logger = Logger.getLogger(TestProController.class);
	@Autowired
	private ICommonService commonService;

	@RequestMapping(value = "/testPro", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String testPro(HttpServletRequest request) {
		Map<String, Object> param = new HashMap<>();
		param.put("input", CharacterEncodeUtil.requestEncode("test"));
		param.put("code", "");
		param.put("msg", "");

		commonService.selectOne("callpro.getTestPro", null, param);
		param.put("msg", CharacterEncodeUtil.returnEncode((String) param.get("msg")));
		System.out.println(param);
		return FastJsonUtil.toJSONString(param);

	}

	@RequestMapping(value = "/find", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String testPro(HttpServletRequest request, @RequestParam(value = "sqlKey", required = false) String sqlKey,
			@RequestParam(value = "dataSource", required = false) String dataSource) {

		Map<String, Object> param = new HashMap<>();
		// Map<String, Object> map =
		// commonService.selectOne("cancelPay.queryBrda", null, param);
		// Map<String, Object> map = commonService.selectOne(sqlKey, null,
		// param);
		Pager selectListByPage = commonService.selectListByPage(sqlKey, dataSource, param, 1, 3, null);
		return FastJsonUtil.toJSONString(selectListByPage.getResult());

	}

	@RequestMapping(value = "/find2", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String find2(HttpServletRequest request, @RequestParam(value = "sqlKey", required = false) String sqlKey,
			@RequestParam(value = "dataSource", required = false) String dataSource) {

		Map<String, Object> param = new HashMap<>();
		List<Map<String, Object>> selectList = commonService.selectList("cancelPay.querykspb", null, param, null);

		/*
		 * for (Map<String, Object> map : selectList) { Object obj =
		 * map.get("DEPTNAME"); String deptName = ""; if(obj instanceof CLOB){
		 * deptName = CharacterEncodeUtil.returnEncode((CLOB)obj); }else if(obj
		 * instanceof BLOB){ deptName =
		 * CharacterEncodeUtil.returnEncode((BLOB)obj); }else if(obj instanceof
		 * String){ deptName = CharacterEncodeUtil.returnEncode((String)obj); }
		 * map.put("DEPTNAME", deptName);
		 * 
		 * Object obj2 = map.get("PARENTDEPTNAME"); String parentDeptName = "";
		 * if(obj2 instanceof CLOB){ parentDeptName =
		 * CharacterEncodeUtil.returnEncode((CLOB)obj2); }else if(obj2
		 * instanceof BLOB){ parentDeptName =
		 * CharacterEncodeUtil.returnEncode((BLOB)obj2); }else if(obj2
		 * instanceof String){ parentDeptName =
		 * CharacterEncodeUtil.returnEncode((String)obj2); }
		 * map.put("PARENTDEPTNAME", parentDeptName);
		 * 
		 * Object obj3 = map.get("DOCTORNAMES"); String doctnames = ""; if(obj3
		 * instanceof CLOB){ doctnames =
		 * CharacterEncodeUtil.returnEncode((CLOB)obj3); }else if(obj3
		 * instanceof BLOB){ doctnames =
		 * CharacterEncodeUtil.returnEncode((BLOB)obj3); }else if(obj3
		 * instanceof String){ doctnames =
		 * CharacterEncodeUtil.returnEncode((String)obj3); }
		 * map.put("DOCTORNAMES", doctnames); }
		 */
		String jsonString = FastJsonUtil.toJSONString(selectList);

		// 将字符编码统一的转化,这样就解决了乱码 哈哈哈哈
		jsonString = CharacterEncodeUtil.returnEncode(jsonString);

		return jsonString;

	}

	@RequestMapping(value = "/yspb", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String yspb(HttpServletRequest request) {

		Date beginTime = new Date();
		String methodName = "yspb";

		Map<String, Object> requestMap = requestCheck(beginTime, methodName, request);
		String checkCode = (String) requestMap.get("code2");
		if (!"200".equals(checkCode)) {// 验证不通过直接返回
			return FastJsonUtil.toJSONString(requestMap);
		}
		// 移除自定义字段，防止意外异常
		requestMap.remove("code2");

		String requestStr = FastJsonUtil.toJSONString(requestMap);
		Map<String, Object> param = new HashMap<>();
		param.put("input", CharacterEncodeUtil.requestEncode(requestStr));
		Object obj = new Object();
		param.put("msg", "");
		param.put("output", obj);

		commonService.selectOne("callpro.yspb", null, param);
		param.put("msg", CharacterEncodeUtil.returnEncode((String) param.get("msg")));
		param.put("output", CharacterEncodeUtil.returnEncode((Blob) param.get("output")));
		System.out.println(param);
		return FastJsonUtil.toJSONString(param);

	}

	@RequestMapping(value = "/yspb2", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String yspb2(HttpServletRequest request) {

		Date beginTime = new Date();
		String methodName = "yspb";

		Map<String, Object> requestMap = requestCheck(beginTime, methodName, request);
		String checkCode = (String) requestMap.get("code2");
		if (!"200".equals(checkCode)) {// 验证不通过直接返回
			return FastJsonUtil.toJSONString(requestMap);
		}
		// 移除自定义字段，防止意外异常
		requestMap.remove("code2");
		requestMap.put("proNum", AppCommonConst.SYSTEM_APP_USP_CHK_YSPB);

		String requestStr = FastJsonUtil.toJSONString(requestMap);
		Map<String, Object> param = new HashMap<>();
		param.put("input", CharacterEncodeUtil.requestEncode(requestStr));
		Object obj = new Object();
		param.put("msg", "");
		param.put("output", obj);
		param.put("proName", AppCommonConst.SYSTEM_PROCEDURE_NAME);

		commonService.selectOne("callpro.yspb2", null, param);
		param.put("msg", CharacterEncodeUtil.returnEncode((String) param.get("msg")));
		param.put("output", CharacterEncodeUtil.returnEncode((Blob) param.get("output")));
		System.out.println(param);
		return FastJsonUtil.toJSONString(param);

	}

	private Map<String, Object> requestCheck(Date beginTime, String methodName, HttpServletRequest request) {
		// 打印请求信息
		logger.info("开始调用接口" + methodName + ":时间为:" + DateUtils.convertDateTime_YYYYMMDDHHMMSS_CN(beginTime) + ",请求信息为:"
				+ request + ",时间标记为[" + beginTime.getTime() + "]");
		// 获得请求时间
		String timestamp = request.getParameter("timestamp");
		// 获得app发送来的请求acl
		String acl = request.getParameter("acl");
		// 参数为空直接返回参数为空的异常信息

		if (StringUtils.isBlank(timestamp) || StringUtils.isBlank(acl)) {
			logger.debug("timestampacl或acl参数为空" + "timestamp为:" + timestamp + " acl为:" + acl);
			Map<String, Object> paramErrorMap = ResultMessageUtil.getParamErrorMap(null);
			return paramErrorMap;
		}
		String aclContent = null;
		// 读acl流数据
		try {
			aclContent = IOUtils.toString(request.getInputStream(), "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("读取acl流数据发生异常,异常信息为:" + e.getMessage());
			Map<String, Object> specialOtherErrorMap = ResultMessageUtil.getSpecialOtherErrorMap(null, "读取acl流数据发生异常");

			return specialOtherErrorMap;
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
			Map<String, Object> decryptFailMap = ResultMessageUtil.getDecryptFailMap(null);

			return decryptFailMap;
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
					Map<String, Object> specialOtherErrorMap = ResultMessageUtil.getSpecialOtherErrorMap(null,
							"安全性检测不发通过");

					return specialOtherErrorMap;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("url加密异常，加密url:" + acl);
			Map<String, Object> otherErrorMap = ResultMessageUtil.getOtherErrorMap(null);

			return otherErrorMap;
		}

		// 对转换后的jsonStr转换为对象然后添加proNum服务码字段
		Map<String, Object> requestMap = FastJsonUtil.toJSONObject(jsonStr, Map.class);

		if (requestMap == null) {// json传参失败
			Map<String, Object> paramErrorMap = ResultMessageUtil.getParamErrorMap("json请求参数不符合规范", null);
			return paramErrorMap;
		}

		requestMap.put("code2", "200");
		return requestMap;
	}

}
