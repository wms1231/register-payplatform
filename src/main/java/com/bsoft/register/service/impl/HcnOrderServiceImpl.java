package com.bsoft.register.service.impl;

import java.net.URLEncoder;
import java.sql.Blob;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.bsoft.constant.AppCommonConstant;
import com.bsoft.constant.CommonConstant;
import com.bsoft.register.service.HcnOrderService;
import com.bsoft.support.service.ICommonService;
import com.bsoft.tools.CharacterEncodeUtil;
import com.bsoft.tools.DateUtils;
import com.bsoft.tools.DecryptUtil;
import com.bsoft.tools.FastJsonUtil;
import com.bsoft.tools.HttpUtil;
import com.bsoft.tools.MessageUtils;
import com.bsoft.tools.RequestDataUtil;
import com.bsoft.tools.ResultMessageUtil;
import com.bsoft.tools.http.HttpRequestProxy;

@Service
public class HcnOrderServiceImpl implements HcnOrderService {
	private static Logger logger = Logger.getLogger(HcnOrderServiceImpl.class);

	@Autowired
	private ICommonService commonService;

	public Map<String, Object> callOrderPro(String sqlKey, String dataSource, Map<String, Object> param) {
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
		// 存储过程请求参数
		param.put("input", CharacterEncodeUtil.requestEncode(requestStr));
		param.put("msg", "");
		param.put("output", new Object());

		callOrderPro(sqlKey, dataSource, param);

		String msg = (String) param.get("msg");
		Blob output = (Blob) param.get("output");
		String result = "";
		// 如果msg为空说明程序正常
		if (StringUtils.isBlank(msg)) {
			result = CharacterEncodeUtil.returnEncode(output);// 转码
		} else {
			result = CharacterEncodeUtil.returnEncode(msg);
		}
		return result;
	}

	/**
	 * 请求验证
	 * 
	 * @param beginTime
	 * @param methodName
	 * @param request
	 * @return
	 */
	public Map<String, Object> requestCheck(Date beginTime, String methodName, HttpServletRequest request) {
		String timestamp = request.getParameter("timestamp");// 获得请求时间
		String acl = request.getParameter("acl");// 获得app发送来的请求acl
		if (StringUtils.isBlank(timestamp) || StringUtils.isBlank(acl)) {// 参数为空直接返回参数为空的异常信息
			logger.debug("timestampacl或acl参数为空" + "timestamp为:" + timestamp + " acl为:" + acl);
			return ResultMessageUtil.getParamErrorMap(null);
		}
		String aclContent = "";
		try {
			aclContent = IOUtils.toString(request.getInputStream(), "UTF-8");// 读acl流数据
		} catch (Exception e) {
			e.printStackTrace();
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
		// 打印请求信息
		logger.info("开始调用接口" + methodName + ":时间为:" + DateUtils.convertDateTime_YYYYMMDDHHMMSS_CN(beginTime) + ",请求参数为:"
				+ jsonStr + ",时间标记为[" + beginTime.getTime() + "]");
		// 安全性检测
		try {
			String checkAcl = HttpRequestProxy.getACL(AppCommonConstant.SYSTEM_APP_OAUTH_NAME, timestamp, jsonStr);
			// 进行url加密，比如 / 转码为 %2F
			String encodeAcl = URLEncoder.encode(acl, "utf-8");
			if (StringUtils.isNotBlank(checkAcl) || StringUtils.isNotBlank(encodeAcl)) {
				if (!checkAcl.equals(encodeAcl)) {
					logger.debug("检测不通过,检测结果" + checkAcl.equals(encodeAcl) + "checkAcl为:" + checkAcl + " encodeAcl为:"
							+ encodeAcl);
					return ResultMessageUtil.getSpecialOtherErrorMap(null, "安全性检测不发通过");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("url加密异常，加密url:" + acl);
			return ResultMessageUtil.getOtherErrorMap(null);
		}
		// 对转换后的jsonStr转换为对象然后添加proNum服务码字段
		@SuppressWarnings("unchecked")
		Map<String, Object> requestMap = FastJsonUtil.toJSONObject(jsonStr, Map.class);

		if (requestMap == null) {// json传参失败
			return ResultMessageUtil.getParamErrorMap("json请求参数不符合规范", null);
		}
		requestMap.put("code2", "200");

		return requestMap;
	}

	/**
	 * 取消支付
	 * 
	 * @param sqlKey
	 * @param dataSource
	 * @param param
	 * @return
	 */
	public Map<String, Object> cancelPay(String sqlKey, String dataSource, Map<String, Object> param) {
		return commonService.selectOne(sqlKey, dataSource, param);
	}

	/**
	 * App退款服务
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public String refund(HttpServletRequest request, HttpServletResponse response, String organizationCode,
			String computerName, String ip) {
		String hospNo = RequestDataUtil.getRequestParmByAttribute(request, "hospNo");// 医院订单号
		String refund_fee = RequestDataUtil.getRequestParmByAttribute(request, "refund_fee");
		String out_request_no = RequestDataUtil.getRequestParmByAttribute(request, "out_request_no");
		Map<String, String> map = new HashMap<String, String>();
		map.put("organizationCode", organizationCode);
		map.put("computerName", computerName);
		map.put("ip", ip);
		map.put("hospNo", hospNo);
		map.put("refund_fee", refund_fee);
		map.put("out_request_no", out_request_no);
		return HttpUtil.postData(CommonConstant.REFUND_PAY_URL, RequestDataUtil.generatorRequestXml(map));// 发出退款请求
	}

	/**
	 * 推送数据到支付平台，包含app、官网、微信公众号
	 * 
	 * @param requestMap
	 * @param timestampValue
	 */
	public String paySync(Map<String, Object> returnPayMap, String organizationCode, String computerName, String ip) {
		Map<String, String> map = new HashMap<String, String>();
		// 业务类型 1挂号、2门诊、3住院预交金、4住院结算 5、病历本
		map.put("payService", "1");
		map.put("ip", ip);// ip地址 eg：127.0.0.1
		// 机构编码
		map.put("organizationCode", organizationCode);
		map.put("computerName", computerName);// 计算机名 收费窗口计算机名
		// 发起订单号 一个商户下唯一
		map.put("hospNo", RequestDataUtil.getValueForKey(returnPayMap, "hospNo"));

		String payMoney = RequestDataUtil.getValueForKey(returnPayMap, "payMoney");
		if (StringUtils.isBlank(payMoney)) {
			payMoney = "0";
		}
		// 发起支付金额
		map.put("paymoney", payMoney);
		// 发票号码 多个发票号码用,分隔
		map.put("voucherNO", RequestDataUtil.getValueForKey(returnPayMap, "voucherNO"));
		// 病人性质
		map.put("patientType", RequestDataUtil.getValueForKey(returnPayMap, "patientType"));
		// 病人Id
		map.put("patientId", RequestDataUtil.getValueForKey(returnPayMap, "patientId"));
		// 病人姓名
		map.put("name", RequestDataUtil.getValueForKey(returnPayMap, "name"));
		// 病人性别
		map.put("sex", RequestDataUtil.getValueForKey(returnPayMap, "sex"));
		// 病人身份证号
		map.put("idcard", RequestDataUtil.getValueForKey(returnPayMap, "idcard"));
		// 出生日期
		map.put("birthday", RequestDataUtil.getValueForKey(returnPayMap, "birthday"));
		// 卡类型
		map.put("cardType", RequestDataUtil.getValueForKey(returnPayMap, "cardType"));
		// 卡号
		map.put("cardNo", RequestDataUtil.getValueForKey(returnPayMap, "cardNo"));
		// 支付来源 1、窗口 2、自助机 3、app
		map.put("paySource", "3");
		// 支付方式 1、支付宝 2微信 3 银联
		map.put("payType", RequestDataUtil.getValueForKey(returnPayMap, "payType"));
		// 操作员工号
		map.put("collectFeesCode", RequestDataUtil.getValueForKey(returnPayMap, "collectFeesCode"));
		// 操作员姓名
		map.put("collectFeesName", RequestDataUtil.getValueForKey(returnPayMap, "collectFeesName"));
		// 发送同步请求
		return HttpUtil.postData(CommonConstant.OTHER_PAY_URL, RequestDataUtil.generatorRequestXml(map));
	}

	/**
	 * 发送短信
	 * 
	 * @param returnPayMap
	 */
	public String sendMessage(Map<String, Object> messageMap) {
		JSONObject jObj = new JSONObject();
		jObj.put("code", 0);
		jObj.put("msg", "success");
		Map<String, Object> param = new HashMap<>();

		// 从数据中获得电话号码
		String yyxh = RequestDataUtil.getValueForKey(messageMap, "regRecordId");
		param.put("yyxh", yyxh);
		Map<String, Object> phoneMap = commonService.selectOne("cancelPay.queryPhone", null, param);// 查询电话号码
		String phoneNum = RequestDataUtil.getValueForKey(phoneMap, "PHONE");
		Byte msgFmt = (Byte) messageMap.get("msgFmt");

		// 消息类型
		String ApplicationID = (String) messageMap.get("ApplicationID");
		int reqDeliveryReport = (int) messageMap.get("reqDeliveryReport");
		int sendMethod = (int) messageMap.get("sendMethod");
		Map<String, Object> payMessageMap = commonService.selectOne("cancelPay.queryPayMessage", null, param);// 查询发送短信模板参数信息

		// 区分云诊室 和 膏方
		String deptType = RequestDataUtil.getValueForKey(payMessageMap, "DEPTTYPE");
		String messageContent = "";

		//膏方预约
		if ("5".equals(deptType)) {
			messageContent = MessageUtils.generateCreamMessage(payMessageMap,
					RequestDataUtil.getValueForKey(messageMap, "creamPaySuccessMsg"));
		} else {
			Map<String, Object> patNatureMap = commonService.selectOne("message.queryHcnPatNature", null, param);// 选择模板
			String visitFlag = RequestDataUtil.getValueForKey(patNatureMap, "VISITFLAG");
			String chargeFlag = RequestDataUtil.getValueForKey(patNatureMap, "CHARGEFLAG");

			if ("2".equals(visitFlag)) {
				// 初诊病人
				messageContent = MessageUtils.generateMutiMessage(payMessageMap,
						RequestDataUtil.getValueForKey(messageMap, "firstVisitPaySuccessMsg"));
			} else {
				if ("1000".equals(chargeFlag)) {
					// 自费病人
					messageContent = MessageUtils.generateMutiMessage(payMessageMap,
							RequestDataUtil.getValueForKey(messageMap, "nextVisitSelfPaySuccessMsg"));
				} else {
					// 非自费病人
					messageContent = MessageUtils.generateMutiMessage(payMessageMap,
							RequestDataUtil.getValueForKey(messageMap, "nextVisitNoSelfPaySuccessMsg"));
				}
			}
		}

		// 查不到信息不发送短信
		if (StringUtils.isNotBlank(messageContent)) {
			if (StringUtils.isBlank(phoneNum)) {// 如果为空重新电话号码值
				phoneNum = RequestDataUtil.getValueForKey(messageMap, "PHONE");
			}

			// 如果内容以及电话号码都不为空发送短信
			if (StringUtils.isNotBlank(phoneNum)) {
				try {
					// 发送短息
					MessageUtils.sendMessage("jdbc.properties", phoneNum, messageContent, msgFmt, "",
							new Timestamp(System.currentTimeMillis()), ApplicationID, phoneNum + System.nanoTime(),
							reqDeliveryReport, sendMethod, this.getClass());
				} catch (Exception e) {
					jObj.put("code", -1);
					jObj.put("msg", "短信发送异常");
					return jObj.toJSONString();
				}

			} else {
				jObj.put("code", -1);
				jObj.put("msg", "接受短信电话号码为空");
				return jObj.toJSONString();
			}

			// 返回成功参数
			return jObj.toJSONString();
		}

		jObj.put("code", -1);
		jObj.put("msg", "短信发送信息查找不全");
		return jObj.toJSONString();
	}

}
