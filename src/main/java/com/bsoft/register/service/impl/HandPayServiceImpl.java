package com.bsoft.register.service.impl;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.sql.Blob;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bsoft.client.mscf.HISWebServiceSoap;
import com.bsoft.constant.AppCommonConstant;
import com.bsoft.constant.CommonConstant;
import com.bsoft.exception.HandPayException;
import com.bsoft.register.service.HandPayService;
import com.bsoft.support.service.ICommonService;
import com.bsoft.tools.CharacterEncodeUtil;
import com.bsoft.tools.DateUtils;
import com.bsoft.tools.DecryptUtil;
import com.bsoft.tools.FastJsonUtil;
import com.bsoft.tools.HttpUtil;
import com.bsoft.tools.RequestDataUtil;
import com.bsoft.tools.ResultMessageUtil;
import com.bsoft.tools.http.HttpRequestProxy;

@Service
public class HandPayServiceImpl implements HandPayService {
	private static Logger logger = Logger.getLogger(HandPayServiceImpl.class);

	@Autowired
	private ICommonService commonService;

	@Resource(name = "hisWebService")
	private HISWebServiceSoap hisWebServiceSoap;

	/**
	 * 调用诊间支付存储过程[过时],以及切换至SPK_CHARGE_JSON包
	 * 
	 * @param sqlKey
	 * @param dataSource
	 * @param param
	 * @return
	 */
	@Deprecated
	public Map<String, Object> callHandPayPro_(String sqlKey, String dataSource, Map<String, Object> param) {
		// 加入切换条件
		String paramV = RequestDataUtil.getValueForKey(
				commonService.selectOne("callpro.selectPro", null, RequestDataUtil.getMapByInputParam(null, null)),
				"PARAMV");
		if (StringUtils.isBlank(paramV)) {
			paramV = "1";
		}

		if (!"1".equals(paramV)) {
			param.put("proName",
					RequestDataUtil.getValueForKey(param, "proName").replaceAll(".+\\.", AppCommonConstant.pack2));
		}

		commonService.selectOne(sqlKey, dataSource, param);
		return param;
	}

	/**
	 * 调用诊间支付存储过程
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
	 * 插入处方
	 * 
	 * @param sqlKey
	 * @param dataSource
	 * @param requestMap
	 * @param param
	 * @return
	 */
	public String insertRecipe(String sqlKey, String dataSource, String requestStr, Map<String, Object> param) {
		// 存储过程请求参数
		param.put("input", CharacterEncodeUtil.requestEncode(requestStr));
		param.put("code", "");
		param.put("msg", "");

		callHandPayPro(sqlKey, dataSource, param);

		String code = (String) param.get("code");
		String msg = (String) param.get("msg");

		if ("0".equals(code)) {// 成功返回
			return "{\"code\":\"200\",\"msg\":\"交易成功\"}";
		}

		return CharacterEncodeUtil.returnEncode(msg);
	}

	public String saveBill(String sqlKey, String dataSource, String requestStr, Map<String, Object> param) {
		// 存储过程请求参数
		param.put("input", CharacterEncodeUtil.requestEncode(requestStr));
		param.put("code", "");
		param.put("msg", "");
		param.put("output", "");

		callHandPayPro(sqlKey, dataSource, param);
		return CharacterEncodeUtil.returnEncode(RequestDataUtil.getValueForKey(param, "output"));
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

			String key = AppCommonConstant.SYSTEM_APP_SECRET_KEY + timestamp;// 秘钥
			jsonStr = DecryptUtil.decryptToString(key, aclContent);// 解密
		} catch (Exception e) {
			logger.error("解密数据发生异常,异常信息为:" + e.getMessage());
			return ResultMessageUtil.getDecryptFailMap(null);
		}

		try {
			// 安全性检测
			String checkAcl = HttpRequestProxy.getACL(AppCommonConstant.SYSTEM_APP_OAUTH_NAME, timestamp, jsonStr);
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
			if (sqlKey.contains("saveBill")) {
				// 处理结算
				result = saveBill("callpro.saveBill", null, FastJsonUtil.toJSONString(requestMap),
						RequestDataUtil.getMapByInputParam(Arrays.asList("proName"), Arrays.asList(proName)));
			} else {
				// 处理公共的服务
				result = invokeProc(sqlKey, null, requestMap,
						RequestDataUtil.getMapByInputParam(Arrays.asList("proName"), Arrays.asList(proName)));
			}
		} catch (Exception e) {
			throw new HandPayException(e.getMessage());
		}

		Date endTime = new Date();
		logger.info("调用服务接口结束" + methodName + ":时间为:" + DateUtils.convertDateTime_YYYYMMDDHHMMSS_CN(endTime) + ",请求信息为:"
				+ result + ",时间标记为[" + endTime.getTime() + "],调用接口消费了" + (endTime.getTime() - beginTime.getTime())
				+ "毫秒");
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String queryPayList(Map<String, Object> requestMap, String methodName, Date beginTime) {
		// 取明细前先取GHMX的SBXH
		requestMap.put("proNum", AppCommonConstant.SYSTEM_APP_USP_BEFORE);

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("proName", AppCommonConstant.SYSTEM_HAND_PROCEDURE_NAME);

		String visitStr = invokeProc(CommonConstant.SYSTEM_PROCEDURE_SQLKEY, null, requestMap, param);

		// 获取就诊序号失败
		if (!visitStr.contains("xml")) {
			logger.error("获取GHMX的SBXH失败失败,处理结果:" + visitStr);
			return ResultMessageUtil.getSpecialServiceFail(null, visitStr);
		}

		// 调用东华接,获取MS_CF信息
		String mscfMsg = hisWebServiceSoap.hisWebService(visitStr);
		// 包含"ServiceCode无效"表示请求数据异常
		if (mscfMsg == null || mscfMsg.contains("ServiceCode无效")) {
			logger.error("获取东华MS_CF信息失败=>" + visitStr);
			return ResultMessageUtil.getSpecialServiceFail(null, "ms_cf请求参数格式异常");
		}

		if (mscfMsg.matches("<topic>\\s*</topic>")) {
			logger.error("获取东华MS_CF信息为空=>" + visitStr);
			return ResultMessageUtil.getSpecialServiceFail(null, "获取ms_cf信息为空");
		}

		// 保存处方\医技信息
		param.put("proName", AppCommonConstant.SYSTEM_HAND_PROCEDURE_NAME_INSERT_CFXX);
		String recipeInfo = insertRecipe("callpro.insertRecipe", null, mscfMsg, param);
		Map<String, Object> recipeInfoMap = FastJsonUtil.toJSONObject(recipeInfo, Map.class);

		if (recipeInfoMap == null) {
			logger.error("调用保存处方医技信息返回的格式不符合json格式,处理结果:" + recipeInfo);
			return ResultMessageUtil.getSpecialServiceFail(null, recipeInfo);
		}

		if (!"200".equals(RequestDataUtil.getValueForKey(recipeInfoMap, "code"))) {
			logger.debug("调用保存处方医技信息存储过程失败,处理结果:" + FastJsonUtil.toJSONString(recipeInfoMap));
			return ResultMessageUtil.getSpecialServiceFail(null, recipeInfo);
		}

		requestMap.put("proNum", AppCommonConstant.SYSTEM_APP_USP_GETPAYLIST);
		param.put("proName", AppCommonConstant.SYSTEM_HAND_PROCEDURE_NAME);
		String payListReturn = invokeProc(CommonConstant.SYSTEM_PROCEDURE_SQLKEY, null, requestMap, param);// 调用服务接口

		Date endTime = new Date();
		logger.info("结束接口调用" + methodName + ":时间为:" + DateUtils.convertDateTime_YYYYMMDDHHMMSS_CN(endTime) + ",请求信息为:"
				+ payListReturn + ",时间标记为[" + endTime.getTime() + "],调用接口消费了"
				+ (endTime.getTime() - beginTime.getTime()) + "毫秒");

		return payListReturn;
	}

	@Override
	public String outpatientFee(HttpServletRequest request) {
		/*
		 * 测试 ok
		 * 
		 * wsimport -keep -d d:\ -s d:\src -p com.bsoft.webs -verbose
		 * http://172.20.20.35/csp/i-his/DHC.HIS.BS.HISWebService.cls?wsdl
		 * VisitNumber 40252655 测试账号
		 */
		String ls_msg = "<PAYMENT>";
		ls_msg += "<HERD>";
		ls_msg += "<ServiceCode>MS_CF</ServiceCode>";
		ls_msg += "<ResultCode></ResultCode>";
		ls_msg += "<ResultMsg> </ResultMsg>";
		ls_msg += "</HERD>";
		ls_msg += "<BODY><INFO>";
		ls_msg += "<VisitNumber>" + 40252655 + "</VisitNumber>";
		ls_msg += "<LIST></LIST></INFO></BODY></PAYMENT>";

		String returnMsg = hisWebServiceSoap.hisWebService(ls_msg);

		// 判断请求参数是否不正常，包含"ServiceCode无效"表示请求异常
		if (returnMsg != null && returnMsg.contains("ServiceCode无效")) {
			Map<String, Object> paramErrorMap = ResultMessageUtil.getParamErrorMap("请求参数无效", null);
			String paramErrorMapMsg = FastJsonUtil.toJSONString(paramErrorMap);
			return paramErrorMapMsg;
		}
		return returnMsg;
	}

	@Override
	public String queryChannel(String sqlKey, String dataSource, String source) {
		Map<String, Object> param = RequestDataUtil.getMapByInputParam(Arrays.asList("channel"), Arrays.asList(source));
		Map<String, Object> channelMap = commonService.selectOne(sqlKey, dataSource, param);
		String channel = RequestDataUtil.getValueForKey(channelMap, "CHANNEL");
		String address = RequestDataUtil.getValueForKey(channelMap, "ADDRESS");
		return channel + "#" + address;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean propelPayInfo(String source, Map<String, Object> billResultMap, Map<String, Object> requestMap) {
		// 推送默认为成功
		boolean flag = true;

		if (StringUtils.isBlank(source)) {
			throw new HandPayException("输入source不能为空,您输入的是:" + source);
		}
		String channelInfo = queryChannel("channel.queryChannel", null, source);
		String[] channelInfoArr = channelInfo.split("#");
		if (channelInfoArr == null || channelInfoArr.length != 4) {
			throw new HandPayException("传入渠道有错,例如HCN-WEB,HCN-WXH,CN-APP");
		}

		Map<String, String> map = new HashMap<String, String>();
		// 业务类型 1挂号、2 门诊、3住院预交金、4住院结算 5、病历本
		map.put("payService", "2");
		// 机构编码 466002630#BSWEB#BSWEB#
		map.put("organizationCode", channelInfoArr[1]);
		map.put("computerName", channelInfoArr[2]);
		map.put("ip", channelInfoArr[3]);

		//传入钱
		Double payMoney = 0.0;
		try {
			payMoney = (Double) requestMap.get("selfPay");
		} catch (Exception e) {
			BigDecimal bigPayMoney = (BigDecimal) requestMap.get("selfPay");
			payMoney = bigPayMoney.doubleValue();
			logger.info("掌上支付金钱是BigDecimal类型的:" + payMoney);
		}
		map.put("paymoney", payMoney.toString());
		// 添加发票号码 暂时不添加
		map.put("voucherNO", "");
		// 发送订单号
		String hospNo = RequestDataUtil.getValueForKey(requestMap, "payServerTradeNo");
		if (StringUtils.isBlank(hospNo)) {
			logger.error("支付服务商订单号没有输入=>" + hospNo);
			throw new HandPayException("请输入支付服务商订单号");
		}
		map.put("hospNo", hospNo);

		String patId = RequestDataUtil.getValueForKey(requestMap, "patientId");

		Map<String, Object> param = RequestDataUtil.getMapByInputParam(Arrays.asList("patId"), Arrays.asList(patId));

		Map<String, Object> patMap = commonService.selectOne("channel.queryPatInfo", null, param);

		// 病人性质
		map.put("patientType", RequestDataUtil.getValueForKey(patMap, "PATIENTTYPE"));
		// 病人Id
		map.put("patientId", RequestDataUtil.getValueForKey(patMap, "PATIENTID"));
		// 病人姓名
		map.put("name", CharacterEncodeUtil.returnEncode(RequestDataUtil.getValueForKey(patMap, "PATNAME")));
		// 病人性别
		map.put("sex", RequestDataUtil.getValueForKey(patMap, "PATSEX"));
		// 病人身份证号
		map.put("idcard", RequestDataUtil.getValueForKey(patMap, "IDCARD"));
		// 出生日期
		map.put("birthday", RequestDataUtil.getValueForKey(patMap, "BIRTHDAY"));
		// 卡类型
		map.put("cardType", "");
		// 卡号
		map.put("cardNo", "");
		// 支付来源 1、窗口 2、自助机 3、app
		map.put("paySource", "3");
		String payType = RequestDataUtil.getValueForKey(requestMap, "payType");
		if ("03".equals(payType) || "05".equals(payType)) {
			payType = "2";
		} else if ("04".equals(payType)) {
			payType = "3";
		} else if ("02".equals(payType)) {
			payType = "1";
		} else {
			payType = "1";
		}
		// 支付方式 1、支付宝 2微信 3 银联
		map.put("payType", payType);
		// 渠道替换操作员
		map.put("collectFeesCode", channelInfoArr[0]);
		// 操作员姓名
		map.put("collectFeesName", RequestDataUtil.getValueForKey(requestMap, "payer"));

		// 发送请求
		String result = HttpUtil.postData(CommonConstant.OTHER_PAY_URL, RequestDataUtil.generatorRequestXml(map));
		Map<String, Object> resultMap = FastJsonUtil.toJSONObject(result, Map.class);

		logger.info("推送消息到支付反馈消息=>" + result);
		if (!"200".equals(RequestDataUtil.getValueForKey(resultMap, "code"))) {
			logger.error("推送消息到支付平台失败,失败原因=>" + result);
			flag = false;
		}

		return flag;
	}

}
