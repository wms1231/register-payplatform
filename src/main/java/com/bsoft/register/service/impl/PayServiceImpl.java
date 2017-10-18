package com.bsoft.register.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import com.bsoft.constant.CommonConstant;
import com.bsoft.domain.HeadBean;
import com.bsoft.factory.BeanService;
import com.bsoft.register.service.AppointedService;
import com.bsoft.register.service.PayService;
import com.bsoft.support.service.ICommonService;
import com.bsoft.tools.CharacterEncodeUtil;
import com.bsoft.tools.DateUtils;
import com.bsoft.tools.HttpUtil;
import com.bsoft.tools.MessageGenerator;
import com.bsoft.tools.MessageUtils;
import com.bsoft.tools.RequestDataUtil;
import com.github.pagehelper.util.StringUtil;

@Service
public class PayServiceImpl implements PayService {
	private static Logger logger = Logger.getLogger(PayServiceImpl.class);
	@Value("${pay_url}")
	private String pay_url;
	@Value("${orderquery}")
	private String orderquery;
	@Value("${signUrlCode}")
	private String signUrlCode;
	@Value("${ApplicationID}")
	private String ApplicationID;
	@Value("${msgFmt}")
	private Byte msgFmt;
	@Value("${reqDeliveryReport}")
	private int reqDeliveryReport;
	@Value("${sendMethod}")
	private int sendMethod;
	@Value("${refund}")
	private String refund;
	@Value("${msg}")
	private String msg;
	@Value("${msg1}")
	private String msg1;
	@Value("${msg2}")
	private String msg2;
	@Value("${firstVisitPaySuccessMsg}")
	private String firstVisitPaySuccessMsg;
	@Value("${nextVisitSelfPaySuccessMsg}")
	private String nextVisitSelfPaySuccessMsg;
	@Value("${nextVisitNoSelfPaySuccessMsg}")
	private String nextVisitNoSelfPaySuccessMsg;

	@Value("${creamPaySuccessMsg}")
	private String creamPaySuccessMsg;

	@Value("${termOfValidity}")
	private String termOfValidity;
	@Value("${CallBack}")
	private String CallBack;
	@Value("${callCreamBack}")
	private String callCreamBack;
	@Value("${paypage}")
	private String paypage;
	@Value("${domain}")
	private String domain;
	@Value("${port}")
	private String port;

	@Autowired
	private AppointedService appointedService;
	@Autowired
	private ICommonService commonService;

	/**
	 * 刷卡支付
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public String trade_pay(HttpServletRequest request, HttpServletResponse response) {
		String payService = "1";// 支付类型，1表示表示
		String paySource = "1";// 窗口支付
		String auth_code = RequestDataUtil.getRequestParmByParameter(request, "auth_code");// 支付二维码
		String hospNo = RequestDataUtil.getRequestParmByParameter(request, "hospNo");// 医院订单号
		String paymoney = RequestDataUtil.getRequestParmByParameter(request, "paymoney");// 支付金额
		Map<String, String> map = new HashMap<String, String>();
		map.put("organizationCode", CommonConstant.PAY_12580_ORGANIZATION_CODE);
		map.put("computerName", CommonConstant.PAY_12580_COMPUTER_NAME);
		map.put("ip", CommonConstant.PAY_12580_IP);
		map.put("payService", payService);
		map.put("auth_code", auth_code);
		map.put("hospNo", hospNo);
		map.put("paymoney", paymoney);
		map.put("paySource", paySource);
		String data = RequestDataUtil.generatorRequestXml(map);
		logger.info("刷卡支付url=>trade_pay,支付订单=>" + hospNo + " 交易结果=>" + data);
		return HttpUtil.postData(pay_url, data);
	}

	/**
	 * 查询订单状态
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public String orderquery(HttpServletRequest request, HttpServletResponse response) {
		String hospNo = RequestDataUtil.getRequestParmByParameter(request, "hospNo");// 医院订单号
		Map<String, String> map = new HashMap<String, String>();
		map.put("hospNo", hospNo);
		map.put("organizationCode", CommonConstant.PAY_12580_ORGANIZATION_CODE);
		map.put("computerName", CommonConstant.PAY_12580_COMPUTER_NAME);
		map.put("ip", CommonConstant.PAY_12580_IP);
		String data = RequestDataUtil.generatorRequestXml(map);
		logger.info("查询订单url=>orderquery,查询订单" + hospNo + " 查询结果" + data);
		return HttpUtil.postData(orderquery, data);
	}

	/**
	 * 退款接口
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public String refund(HttpServletRequest request, HttpServletResponse response) {
		String hospNo = RequestDataUtil.getRequestParmByParameter(request, "hospNo");// 医院订单号
		String refund_fee = RequestDataUtil.getRequestParmByParameter(request, "refund_fee");
		String out_request_no = RequestDataUtil.getRequestParmByParameter(request, "out_request_no");
		Map<String, String> map = new HashMap<String, String>();
		map.put("organizationCode", CommonConstant.PAY_12580_ORGANIZATION_CODE);
		map.put("computerName", CommonConstant.PAY_12580_COMPUTER_NAME);
		map.put("ip", CommonConstant.PAY_12580_IP);
		map.put("hospNo", hospNo);
		map.put("out_request_no", out_request_no);
		map.put("refund_fee", refund_fee);
		String data = RequestDataUtil.generatorRequestXml(map);
		logger.info("退款交易url=>refund,退款订单=>" + hospNo + " 退款请求参数=>" + data);
		return HttpUtil.postData(refund, data);
	}

	/**
	 * 退款成功后短信通知
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public String refundmessage(HttpServletRequest request, HttpServletResponse response) {
		JSONObject jObj = new JSONObject();
		jObj.put("code", 0);
		jObj.put("msg", "success");
		String phoneNum = RequestDataUtil.getRequestParmByParameter(request, "phonenum");
		String messageContent = MessageGenerator.generatorMessageContent(request, null, msg2);
		if (StringUtils.isNotBlank(phoneNum)) {
			try {
				MessageUtils.sendMessage("jdbc.properties", phoneNum, messageContent, msgFmt, "",
						new Timestamp(System.currentTimeMillis()), ApplicationID, phoneNum + System.nanoTime(),
						reqDeliveryReport, sendMethod, this.getClass());
			} catch (Exception e) {
				jObj.put("code", -1);
				jObj.put("msg", "短信发送异常");
				logger.error("退款通知短信发送异常", e);
				return jObj.toJSONString();
			}
		} else {
			jObj.put("code", -1);
			jObj.put("msg", "请传入电话号码");
			logger.error("退款通知短信电话为空=>" + phoneNum + " 通知内容=>" + messageContent);
			return jObj.toJSONString();
		}
		return jObj.toJSONString();
	}

	/**
	 * 12580 预约成功后 获取URL并发送短息通知,并支持重复发送短信 修改
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public String sendmessage(HttpServletRequest request, HttpServletResponse response, String callBackUrl) {
		JSONObject jObj = new JSONObject();// 设置默认值
		jObj.put("code", 0);
		jObj.put("msg", "success");
		String payService = "1";// 1.表示预约
		String paySource = "5";
		Map<String, Object> mesMap = appointedService.getMessinfo(request);// 默认数据库取数据
		
		if (mesMap != null) {// 非第一次发送短信
			String newPhone = RequestDataUtil.getRequestParmByParameter(request, "phonenum");// 如果电话号码变了,需要更新
			String oldPhone = (String) mesMap.get("PHONENUM");
			newPhone = newPhone == null ? "n" : newPhone;
			oldPhone = oldPhone == null ? "o" : oldPhone;
			if (!oldPhone.equals(newPhone)) {// 如果电话号码有变，更新电话
				if (appointedService.updateMessinfo(request) != 1) {// 更新短信信息
					jObj.put("code", -1);
					jObj.put("msg", "更新电话号码失败");
					return jObj.toJSONString();
				}
			}
			String newUrl = (String) mesMap.get("URL");// url特殊处理
			newUrl = RequestDataUtil.replaceDomainAndPort(domain, port, newUrl);
			String newMessageContent = MessageGenerator.generatorMessageContent(request, newUrl, msg);// 发送短息准备
			try {
				MessageUtils.sendMessage("jdbc.properties", newPhone, newMessageContent, msgFmt, "",
						new Timestamp(System.currentTimeMillis()), ApplicationID, newPhone + System.nanoTime(),
						reqDeliveryReport, sendMethod, this.getClass());
			} catch (Exception e) {
				jObj.put("code", -1);
				jObj.put("msg", "短信发送异常");
				logger.error("12580 发送短信异常", e);
				return jObj.toJSONString();
			}
			logger.info("12580 预约成功后补发短信通知url=>sendmessage,通知电话=>" + newPhone + " 通知内容=>" + newMessageContent);
		} else {
			// 第一次发送短信
			String hospNo = RequestDataUtil.getRequestParmByParameter(request, "hospNo");
			String paymoney = RequestDataUtil.getRequestParmByParameter(request, "paymoney");// 支付金额
			Map<String, String> map = new HashMap<String, String>();
			map.put("payService", payService);
			map.put("organizationCode", CommonConstant.PAY_12580_ORGANIZATION_CODE);
			map.put("computerName", CommonConstant.PAY_12580_COMPUTER_NAME);
			map.put("ip", CommonConstant.PAY_12580_IP);
			map.put("hospNo", hospNo);
			map.put("paymoney", paymoney);
			map.put("termOfValidity", termOfValidity);

			
			if ("1".equals(callBackUrl)) {
				map.put("callback", CallBack);
			} else if ("2".equals(callBackUrl)) {
				// 注入膏方回调
				map.put("callback", callCreamBack);
			}

			map.put("paySource", paySource);
			String data = RequestDataUtil.generatorRequestXml(map);// xml数据转换
			String ret = HttpUtil.postData(paypage, data);// 调用支付接口获取支付url地址
			JSONObject json = JSONObject.parseObject(ret);
			if (StringUtil.isEmpty(ret) || !json.get("code").equals("200")) {
				jObj.put("code", -1);
				jObj.put("msg", "获取URL失败");
				return jObj.toJSONString();
			}
			String URL = (String) json.get("urlCode");// 支付URL
			URL = RequestDataUtil.replaceDomainAndPort(domain, port, URL);
			String phoneNum = RequestDataUtil.getRequestParmByParameter(request, "phonenum");
			if (appointedService.saveMessinfo(request, URL) != 1) {// 保存信息到数据库,如果不为1失败
				jObj.put("code", -1);
				jObj.put("msg", "保存短信到失败");
				return jObj.toJSONString();
			}

			String messageContent = MessageGenerator.generatorMessageContent(request, URL, msg);
			try {
				MessageUtils.sendMessage("jdbc.properties", phoneNum, messageContent, msgFmt, "",
						new Timestamp(System.currentTimeMillis()), ApplicationID, phoneNum + System.nanoTime(),
						reqDeliveryReport, sendMethod, this.getClass());
			} catch (Exception e) {
				jObj.put("code", -1);
				jObj.put("msg", "短信发送异常");
				logger.error("12580 发送短信异常", e);
				return jObj.toJSONString();
			}
			logger.info("12580 预约成功后短信通知url=>sendmessage,通知电话=>" + phoneNum + " 通知内容=>" + messageContent);
		}
		return jObj.toJSONString();

	}

	/**
	 * 支付宝回调地址, 注意:支付平台会异步通知预约平台,回调是不需要反参的
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public String callback(HttpServletRequest request, HttpServletResponse response) {
		JSONObject map = null;
		JSONObject json = new JSONObject();// 这里设置返回值是为了程序扩展
		json.put("code", 0);
		json.put("msg", "success");
		String payFlag = "1";// 默认设置为1
		boolean isSuccess = true;// 默认表示同步数据成功
		try {
			map = RequestDataUtil.getRequestData(request);
		} catch (Exception e) {
			isSuccess = false;
			map = new JSONObject();// 防止程序崩溃
			logger.error("callback==》请求转换失败", e);
		}
		if (!"200".equals(map.getString("code"))) {// 反参是错误的也会更新到数据
			payFlag = "2";
		}
		String hospNo = RequestDataUtil.getStringByKey(map, "hospNo");// 医院订单号
		String payType = RequestDataUtil.getStringByKey(map, "payType");
		String paymoney = RequestDataUtil.getStringByKey(map, "paymoney");
		String payTime = RequestDataUtil.getStringByKey(map, "payTime");
		// String verifyNo = RequestDataUtil.getStringByKey(map, "payTime");
		String methodName = "callback";
		Date beginTime = new Date();
		logger.info("开始调用回调接口" + methodName + " 订单号为:" + hospNo + ":时间为:"
				+ DateUtils.convertDateTime_YYYYMMDDHHMMSS_CN(beginTime) + ",请求参数为:" + "订单号=>" + hospNo + " 支付金额=>"
				+ paymoney + " 支付类型=>" + payType + " 支付时间=>" + payTime + ",时间标记为[" + beginTime.getTime() + "]");
		if ("1".equals(payType)) {
			payType = "ZFBPay";
		} else if ("2".equals(payType)) {
			payType = "WXPay";
		} else {
			payType = "YFK";
		}
		if (StringUtils.isBlank(hospNo)) {// 订单号不能为空
			isSuccess = false;
			logger.error("订单号为空,订单号=>" + hospNo);
		}
		HeadBean bean = null;
		if (isSuccess) {
			BeanService bshisservice = null;
			try {
				bshisservice = appointedService.Notification(hospNo, null, payType, paymoney, hospNo, payTime, payFlag);
			} catch (Exception e) {
				logger.error("其它异常=>", e);
				json.put("code", -1);
				if (bshisservice.getHead() != null) {
					json.put("msg", bshisservice.getHead().getResultMessage());
				}
			} // 通知his同步数据
			bean = bshisservice.getHead();
			if (!"0000".equals(bean.getResultCode())) {// 获取状态码
				int counter = 1;
				while (counter < CommonConstant.NOTIFICATION_12580_TIMES) {
					try {
						bshisservice = appointedService.Notification(hospNo, null, payType, paymoney, hospNo, payTime,
								payFlag);
					} catch (Exception e) {
						logger.error("其它异常=>", e);
						json.put("code", -1);
						if (bshisservice.getHead() != null) {
							json.put("msg", bshisservice.getHead().getResultMessage());
						}
					}
					bean = bshisservice.getHead();
					if ("0000".equals(bean.getResultCode()) || "003D".equals(bean.getResultCode())) {
						break;
					}
					counter++;
				}
				if (counter >= CommonConstant.NOTIFICATION_12580_TIMES) {
					isSuccess = false;
				} else {
					isSuccess = true;
				}
			}
		}
		// 同步成功,才进一步发送短信
		if (isSuccess) {
			Map<String, Object> param = new HashMap<>();
			param.put("HOSPNO", hospNo);
			Map<String, Object> msgmap = commonService.selectOne("msginfo.findMsgInfo", null, param);// 查询短信发送内容
			Map<String, Object> addMap = commonService.selectOne("cancelPay.queryAddressByNo", null, param);// 查询科室地址
			// 获取诊疗地址信息
			String address = CharacterEncodeUtil.returnEncode(RequestDataUtil.getValueForKey(addMap, "ADDRESS"));
			String phoneNum = RequestDataUtil.getValueForKey(msgmap, "PHONENUM");
			if (StringUtils.isNotBlank(phoneNum)) {
				// 更加是否初诊和自费以及非自费选择不同的模板发送短信
				Map<String, Object> patNatureMap = commonService.selectOne("message.queryPatNature", null, param);

				String visitFlag = RequestDataUtil.getValueForKey(patNatureMap, "VISITFLAG");
				String chargeFlag = RequestDataUtil.getValueForKey(patNatureMap, "CHARGEFLAG");
				String messageContent = "";
				if ("2".equals(visitFlag)) { // 初诊病人
					messageContent = MessageUtils.generateMutiMessage(msgmap, firstVisitPaySuccessMsg);
				} else {
					if ("1000".equals(chargeFlag)) {// 自费病人
						messageContent = MessageUtils.generateMutiMessage(msgmap, nextVisitSelfPaySuccessMsg);
					} else {// 非自费病人
						messageContent = MessageUtils.generateMutiMessage(msgmap, nextVisitNoSelfPaySuccessMsg);
					}
				}
				try {
					// 不为空 且 包含江苏省中医院
					if (StringUtils.isNotBlank(messageContent) && messageContent.matches(".*江苏省中医院.*")) {
						messageContent = messageContent.replaceAll("江苏省中医院", address);
					}
					// 一切发送成功然后发送短息
					MessageUtils.sendMessage("jdbc.properties", phoneNum, messageContent, msgFmt, "",
							new Timestamp(System.currentTimeMillis()), ApplicationID, phoneNum + System.nanoTime(),
							reqDeliveryReport, sendMethod, this.getClass());
				} catch (Exception e) {
					json.put("code", -1);
					json.put("msg", "短信发送异常");
					logger.error("12580 发送短信异常", e);
					return json.toJSONString();
				}
				Date endTime = new Date();
				logger.info("调用接口" + methodName + "订单号为=>" + hospNo + "结束:时间为:"
						+ DateUtils.convertDateTime_YYYYMMDDHHMMSS_CN(endTime) + ",请求参数为:" + "电话号码为=>" + phoneNum
						+ " 发送内容为=>" + messageContent + ",时间标记为[" + beginTime.getTime() + "]");
			} else {
				logger.error(hospNo + "号订单回调通知失败,失败原因:电话号码为空,电话号码为phoneNum=>" + phoneNum);
			}
		}
		if (!isSuccess) {// 做一个简单的反参处理
			if (bean != null) {
				json.put("code", bean.getResultCode());
				json.put("msg", bean.getResultMessage());
				logger.error(hospNo + "号订单回调通知失败,失败原因:返回参数=>" + json.toJSONString());
			} else {
				logger.error("程序转换异常或者订单号为空!");
			}
		}
		logger.info(hospNo + "号订单回调同步数据成功,反参=>" + json.toJSONString());
		return json.toJSONString();
	}

	/**
	 * 支付宝回调地址, 注意:支付平台会异步通知预约平台,回调是不需要反参的(膏方)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public String callCreamBack(HttpServletRequest request, HttpServletResponse response) {
		JSONObject map = null;
		JSONObject json = new JSONObject();// 这里设置返回值是为了程序扩展
		json.put("code", 0);
		json.put("msg", "success");
		String payFlag = "1";// 默认设置为1
		boolean isSuccess = true;// 默认表示同步数据成功
		try {
			map = RequestDataUtil.getRequestData(request);
		} catch (Exception e) {
			isSuccess = false;
			map = new JSONObject();// 防止程序崩溃
			logger.error("callback==》请求转换失败", e);
		}
		if (!"200".equals(map.getString("code"))) {// 反参是错误的也会更新到数据
			payFlag = "2";
		}
		String hospNo = RequestDataUtil.getStringByKey(map, "hospNo");// 医院订单号
		String payType = RequestDataUtil.getStringByKey(map, "payType");
		String paymoney = RequestDataUtil.getStringByKey(map, "paymoney");
		String payTime = RequestDataUtil.getStringByKey(map, "payTime");
		// String verifyNo = RequestDataUtil.getStringByKey(map, "payTime");
		String methodName = "callCreamBack";
		Date beginTime = new Date();
		logger.info("开始调用回调接口" + methodName + " 订单号为:" + hospNo + ":时间为:"
				+ DateUtils.convertDateTime_YYYYMMDDHHMMSS_CN(beginTime) + ",请求参数为:" + "订单号=>" + hospNo + " 支付金额=>"
				+ paymoney + " 支付类型=>" + payType + " 支付时间=>" + payTime + ",时间标记为[" + beginTime.getTime() + "]");
		if ("1".equals(payType)) {
			payType = "ZFBPay";
		} else if ("2".equals(payType)) {
			payType = "WXPay";
		} else {
			payType = "YFK";
		}
		if (StringUtils.isBlank(hospNo)) {// 订单号不能为空
			isSuccess = false;
			logger.error("订单号为空,订单号=>" + hospNo);
		}
		HeadBean bean = null;
		if (isSuccess) {
			BeanService bshisservice = null;
			try {
				bshisservice = appointedService.Notification(hospNo, null, payType, paymoney, hospNo, payTime, payFlag);
			} catch (Exception e) {
				logger.error("其它异常=>", e);
				json.put("code", -1);
				if (bshisservice.getHead() != null) {
					json.put("msg", bshisservice.getHead().getResultMessage());
				}
			} // 通知his同步数据
			bean = bshisservice.getHead();
			if (!"0000".equals(bean.getResultCode())) {// 获取状态码
				int counter = 1;
				while (counter < CommonConstant.NOTIFICATION_12580_TIMES) {
					try {
						bshisservice = appointedService.Notification(hospNo, null, payType, paymoney, hospNo, payTime,
								payFlag);
					} catch (Exception e) {
						logger.error("其它异常=>", e);
						json.put("code", -1);
						if (bshisservice.getHead() != null) {
							json.put("msg", bshisservice.getHead().getResultMessage());
						}
					}
					bean = bshisservice.getHead();
					if ("0000".equals(bean.getResultCode()) || "003D".equals(bean.getResultCode())) {
						break;
					}
					counter++;
				}
				if (counter >= CommonConstant.NOTIFICATION_12580_TIMES) {
					isSuccess = false;
				} else {
					isSuccess = true;
				}
			}
		}

		if (isSuccess) {// 同步成功,才进一步发送短信
			Map<String, Object> param = new HashMap<>();
			param.put("HOSPNO", hospNo);
			Map<String, Object> msgmap = commonService.selectOne("msginfo.findMsgInfo", null, param);// 查询短信发送内容
			String phoneNum = RequestDataUtil.getValueForKey(msgmap, "PHONENUM");
			if (StringUtils.isNotBlank(phoneNum)) {
				String messageContent = MessageUtils.generateCreamMessage(msgmap, creamPaySuccessMsg);
				try {
					// 一切发送成功然后发送短息
					MessageUtils.sendMessage("jdbc.properties", phoneNum, messageContent, msgFmt, "",
							new Timestamp(System.currentTimeMillis()), ApplicationID, phoneNum + System.nanoTime(),
							reqDeliveryReport, sendMethod, this.getClass());
				} catch (Exception e) {
					json.put("code", -1);
					json.put("msg", "短信发送异常");
					logger.error("12580 发送短信异常", e);
					return json.toJSONString();
				}
				Date endTime = new Date();
				logger.info("调用接口" + methodName + "订单号为=>" + hospNo + "结束:时间为:"
						+ DateUtils.convertDateTime_YYYYMMDDHHMMSS_CN(endTime) + ",请求参数为:" + "电话号码为=>" + phoneNum
						+ " 发送内容为=>" + messageContent + ",时间标记为[" + beginTime.getTime() + "]");
			} else {
				logger.error(hospNo + "号订单回调通知失败,失败原因:电话号码为空,电话号码为phoneNum=>" + phoneNum);
			}
		}
		if (!isSuccess) {// 做一个简单的反参处理
			if (bean != null) {
				json.put("code", bean.getResultCode());
				json.put("msg", bean.getResultMessage());
				logger.error(hospNo + "号订单回调通知失败,失败原因:返回参数=>" + json.toJSONString());
			} else {
				logger.error("程序转换异常或者订单号为空!");
			}
		}
		logger.info(hospNo + "号订单回调同步数据成功,反参=>" + json.toJSONString());
		return json.toJSONString();
	}

}
