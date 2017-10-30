package com.bsoft.register.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import com.bsoft.constant.CommonConst;
import com.bsoft.constant.PayServiceConst;
import com.bsoft.constant.PaySourceConst;
import com.bsoft.domain.HeadBean;
import com.bsoft.exception.MessageException;
import com.bsoft.exception.PayException;
import com.bsoft.factory.BeanService;
import com.bsoft.register.service.AppointedService;
import com.bsoft.register.service.PayService;
import com.bsoft.register.service.SendMessageService;
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

	@Autowired
	private SendMessageService sendMessageService;

	/**
	 * 刷卡支付
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public String trade_pay(HttpServletRequest request, HttpServletResponse response) {
		String payService = PayServiceConst.GHYW;
		String paySource = PaySourceConst.CKZF;
		// 支付二维码
		String auth_code = RequestDataUtil.getRequestParmByParameter(request, "auth_code");
		String hospNo = RequestDataUtil.getRequestParmByParameter(request, "hospNo");
		String paymoney = RequestDataUtil.getRequestParmByParameter(request, "paymoney");

		Map<String, String> param = RequestDataUtil.getMapByStringParam(
				Arrays.asList("organizationCode", "computerName", "ip", "payService", "auth_code", "hospNo", "paymoney",
						"paySource"),
				Arrays.asList(CommonConst.PAY_ORGANIZATION_CODE, CommonConst.PAY_COMPUTER_NAME, CommonConst.PAY_IP,
						payService, auth_code, hospNo, paymoney, paySource));

		return HttpUtil.postData(pay_url, RequestDataUtil.generatorRequestXml(param));
	}

	/**
	 * 查询订单状态
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public String orderquery(HttpServletRequest request, HttpServletResponse response) {
		String hospNo = RequestDataUtil.getRequestParmByParameter(request, "hospNo");

		Map<String, String> param = RequestDataUtil.getMapByStringParam(
				Arrays.asList("hospNo", "organizationCode", "computerName", "ip"), Arrays.asList(hospNo,
						CommonConst.PAY_ORGANIZATION_CODE, CommonConst.PAY_COMPUTER_NAME, CommonConst.PAY_IP));

		return HttpUtil.postData(orderquery, RequestDataUtil.generatorRequestXml(param));
	}

	/**
	 * 退款接口
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public String refund(HttpServletRequest request, HttpServletResponse response) {
		String hospNo = RequestDataUtil.getRequestParmByParameter(request, "hospNo");
		String refund_fee = RequestDataUtil.getRequestParmByParameter(request, "refund_fee");
		String out_request_no = RequestDataUtil.getRequestParmByParameter(request, "out_request_no");

		Map<String, String> param = RequestDataUtil.getMapByStringParam(
				Arrays.asList("organizationCode", "computerName", "ip", "hospNo", "out_request_no", "refund_fee"),
				Arrays.asList(CommonConst.PAY_ORGANIZATION_CODE, CommonConst.PAY_COMPUTER_NAME, CommonConst.PAY_IP,
						hospNo, out_request_no, refund_fee));

		return HttpUtil.postData(refund, RequestDataUtil.generatorRequestXml(param));
	}

	/**
	 * 退款成功后短信通知
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public String refundmessage(HttpServletRequest request, HttpServletResponse response) {
		String phoneNum = RequestDataUtil.getRequestParmByParameter(request, "phonenum");
		String messageContent = MessageGenerator.generatorMessageContent(request, null, msg2);
		String requestIdentifier = "";
		if (StringUtils.isNotBlank(phoneNum)) {
			requestIdentifier = sendMessageService.sendLongMess(messageContent, phoneNum);
		} else {
			throw new MessageException("-1", "电话号码为空");

		}
		return requestIdentifier;
	}

	/**
	 * 12580 预约成功后 获取URL并发送短息通知,并支持重复发送短信 修改
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public String sendmessage(HttpServletRequest request, HttpServletResponse response, String callBackUrl) {
		JSONObject jObj = new JSONObject();
		jObj.put("code", 0);
		jObj.put("msg", "success");

		String payService = PayServiceConst.GHYW;
		String paySource = PaySourceConst.DXZF;
		Map<String, Object> mesMap = appointedService.getMessinfo(request);

		if (mesMap != null) {// 非第一次发送短信
			String newPhone = RequestDataUtil.getRequestParmByParameter(request, "phonenum");// 如果电话号码变了,需要更新
			String oldPhone = (String) mesMap.get("PHONENUM");
			newPhone = newPhone == null ? "n" : newPhone;
			oldPhone = oldPhone == null ? "o" : oldPhone;
			if (!oldPhone.equals(newPhone)) {// 如果电话号码有变，更新电话
				if (appointedService.updateMessinfo(request) != 1) {// 更新短信信息
					throw new PayException("-1", "更新电话号码失败");
				}
			}
			String newUrl = (String) mesMap.get("URL");// url特殊处理
			newUrl = RequestDataUtil.replaceDomainAndPort(domain, port, newUrl);
			String newMessageContent = MessageGenerator.generatorMessageContent(request, newUrl, msg);// 发送短息准备
			try {
				sendMessageService.sendLongMess(newMessageContent, newPhone);
				logger.info("12580 预约成功后补发短信通知url=>sendmessage,通知电话=>" + newPhone + " 通知内容=>" + newMessageContent);
			} catch (Exception e) {
				throw e;
			}

		} else {
			// 第一次发送短信
			String hospNo = RequestDataUtil.getRequestParmByParameter(request, "hospNo");
			String paymoney = RequestDataUtil.getRequestParmByParameter(request, "paymoney");

			Map<String, String> param = RequestDataUtil.getMapByStringParam(
					Arrays.asList("payService", "organizationCode", "computerName", "ip", "hospNo", "paymoney",
							"termOfValidity", "paySource"),
					Arrays.asList(payService, CommonConst.PAY_ORGANIZATION_CODE, CommonConst.PAY_COMPUTER_NAME,
							CommonConst.PAY_IP, hospNo, paymoney, termOfValidity, paySource));

			if ("1".equals(callBackUrl)) {
				param.put("callback", CallBack);
			} else if ("2".equals(callBackUrl)) {
				// 注入膏方回调
				param.put("callback", callCreamBack);
			}

			String ret = HttpUtil.postData(paypage, RequestDataUtil.generatorRequestXml(param));

			JSONObject json = JSONObject.parseObject(ret);
			if (StringUtil.isEmpty(ret) || !json.get("code").equals("200")) {
				throw new PayException("-1", ret);
			}
			String URL = (String) json.get("urlCode");// 支付URL
			URL = RequestDataUtil.replaceDomainAndPort(domain, port, URL);
			String phoneNum = RequestDataUtil.getRequestParmByParameter(request, "phonenum");
			if (appointedService.saveMessinfo(request, URL) != 1) {// 保存信息到数据库,如果不为1失败
				throw new PayException("-1", "保存短信到失败");
			}

			try {
				String messageContent = MessageGenerator.generatorMessageContent(request, URL, msg);
				sendMessageService.sendLongMess(messageContent, phoneNum);
				logger.info("12580 预约成功后短信通知url=>sendmessage,通知电话=>" + phoneNum + " 通知内容=>" + messageContent);
			} catch (Exception e) {
				throw e;
			}

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
	public void callback(HttpServletRequest request, HttpServletResponse response, int flag) {
		String payFlag = "1";
		boolean isSuccess = true;

		JSONObject map = RequestDataUtil.getRequestData(request);
		if (!"200".equals(map.getString("code"))) {
			payFlag = "2";// 失败状态也更新
		}
		String hospNo = RequestDataUtil.getStringByKey(map, "hospNo");
		String payType = RequestDataUtil.getStringByKey(map, "payType");
		String paymoney = RequestDataUtil.getStringByKey(map, "paymoney");
		String payTime = RequestDataUtil.getStringByKey(map, "payTime");

		if ("1".equals(payType)) {
			payType = "ZFBPay";
		} else if ("2".equals(payType)) {
			payType = "WXPay";
		} else {
			payType = "YFK";
		}

		if (StringUtils.isBlank(hospNo)) {
			isSuccess = false;
			logger.error("订单号为空,订单号=>" + hospNo);
		}

		if (isSuccess) {
			// 通知his同步数据
			BeanService bshisservice = null;
			try {
				bshisservice = appointedService.Notification(hospNo, null, payType, paymoney, hospNo, payTime, payFlag);
				HeadBean bean = bshisservice.getHead();
				// 不成功 多更新几次
				if (!"0000".equals(bean.getResultCode())) {
					int counter = 1;
					while (counter < CommonConst.NOTIFICATION_12580_TIMES) {
						bshisservice = appointedService.Notification(hospNo, null, payType, paymoney, hospNo, payTime,
								payFlag);

						bean = bshisservice.getHead();
						if ("0000".equals(bean.getResultCode()) || "003D".equals(bean.getResultCode())) {
							break;
						}
						counter++;
					}

					if (counter >= CommonConst.NOTIFICATION_12580_TIMES) {
						isSuccess = false;
					}
				}

			} catch (Exception e1) {
				throw new PayException("-1", bshisservice.getHead().getResultMessage());
			}

		}

		// 同步成功,才进一步发送短信
		String sendOrderMessage = "";
		if (isSuccess) {
			if (flag == 1) {
				sendOrderMessage = sendOrderMessage(hospNo);
			} else if (flag == 2) {
				sendOrderMessage = sendCreamOrderMessage(hospNo);
			}

			logger.info("订单号=>" + hospNo + "在" + DateUtils.convertDateTime_YYYYMMDDHHMMSS_CN(new Date())
					+ " 发送短信,短信内容=>" + sendOrderMessage);

		}

	}

	/**
	 * 支付宝回调地址, 注意:支付平台会异步通知预约平台,回调是不需要反参的(膏方)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public void callCreamBack(HttpServletRequest request, HttpServletResponse response) {
		callback(request, response, 2);
	}

	/**
	 * 发送刚放预约短信
	 * 
	 * @param hospNo
	 * @return
	 */
	private String sendCreamOrderMessage(String hospNo) {
		Map<String, Object> param = RequestDataUtil.getMapByInputParam(Arrays.asList("HOSPNO"), Arrays.asList(hospNo));
		// 查询短信发送内容
		Map<String, Object> msgmap = commonService.selectOne("msginfo.findMsgInfo", null, param);
		String phoneNum = RequestDataUtil.getValueForKey(msgmap, "PHONENUM");
		String messageContent = "";
		if (StringUtils.isNotBlank(phoneNum)) {
			messageContent = MessageUtils.generateCreamMessage(msgmap, creamPaySuccessMsg);
			sendMessageService.sendLongMess(messageContent, phoneNum);

		} else {
			logger.error(hospNo + "号订单回调通知失败,失败原因:电话号码为空,电话号码为phoneNum=>" + phoneNum);
			return messageContent;
		}

		return messageContent;
	}

	/**
	 * 发送12580预约成功短信
	 * 
	 * @param hospNo
	 * @param methodName
	 * @param beginTime
	 */
	private String sendOrderMessage(String hospNo) {
		String messageContent = "";
		Map<String, Object> param = RequestDataUtil.getMapByInputParam(Arrays.asList("HOSPNO"), Arrays.asList(hospNo));

		Map<String, Object> msgmap = commonService.selectOne("msginfo.findMsgInfo", null, param);// 查询短信发送内容
		Map<String, Object> addMap = commonService.selectOne("cancelPay.queryAddressByNo", null, param);// 查询科室地址
		// 获取诊疗地址信息
		String address = CharacterEncodeUtil.returnEncode(RequestDataUtil.getValueForKey(addMap, "ADDRESS"));
		String phoneNum = RequestDataUtil.getValueForKey(msgmap, "PHONENUM");
		if (StringUtils.isNotBlank(phoneNum)) {
			// 更加是否初诊和自费以及非自费选择不同的模板发送短信
			Map<String, Object> patNatureMap = commonService.selectOne("message.queryPatNature", null, param);
			// 初诊病人
			if ("2".equals(RequestDataUtil.getValueForKey(patNatureMap, "VISITFLAG"))) {
				messageContent = MessageUtils.generateMutiMessage(msgmap, firstVisitPaySuccessMsg);
			} else {
				// 自费病人
				if ("1000".equals(RequestDataUtil.getValueForKey(patNatureMap, "CHARGEFLAG"))) {
					messageContent = MessageUtils.generateMutiMessage(msgmap, nextVisitSelfPaySuccessMsg);
				} else {
					// 非自费病人
					messageContent = MessageUtils.generateMutiMessage(msgmap, nextVisitNoSelfPaySuccessMsg);
				}
			}

			if (StringUtils.isNotBlank(messageContent) && messageContent.matches(".*江苏省中医院.*")) {
				messageContent = messageContent.replaceAll("江苏省中医院", address);
			}
			if(StringUtils.isNotBlank(messageContent)){
				sendMessageService.sendLongMess(messageContent, phoneNum);
			}else{
				logger.error("发送短信内容为null,拒绝发送");
				return messageContent;
			}

		} else {
			logger.error(hospNo + "号订单回调通知失败,失败原因:电话号码为空,电话号码为phoneNum=>" + phoneNum);
			return messageContent;
		}

		return messageContent;
	}

}
