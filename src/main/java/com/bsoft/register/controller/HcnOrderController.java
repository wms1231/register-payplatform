package com.bsoft.register.controller;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bsoft.constant.AppCommonConst;
import com.bsoft.constant.CommonConst;
import com.bsoft.register.service.HcnOrderService;
import com.bsoft.tools.DateUtils;
import com.bsoft.tools.FastJsonUtil;
import com.bsoft.tools.RequestDataUtil;
import com.bsoft.tools.ResultMessageUtil;

/**
 * 为hcn提供预约相关服务接口
 * 
 * code: 200成功， 201非法请求（解密失败）， 203参数错误， 204 其它错误， 205 业务处理失败. msg: code
 * 不为200时出错信息 body: 返回数据的结构体，类型可以是对象或者数组 ，具体类型由接口定义中给出
 *
 */
@Controller
@RequestMapping("/app")
public class HcnOrderController {
	private static Logger logger = Logger.getLogger(HcnOrderController.class);
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
	@Value("${hcnPayTemplate}")
	private String hcnPayTemplate;

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
	@Value("${paypage}")
	private String paypage;
	@Value("${domain}")
	private String domain;
	@Value("${port}")
	private String port;

	@Autowired
	private HcnOrderService hcnOrderService;

	@RequestMapping(value = "/createPatientDocument3", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String createPatientDocument3(HttpServletRequest request) {
		// 记录接口调用开始时间
		Date beginTime = new Date();
		String methodName = "createPatientDocument3";
		// 安全验证
		Map<String, Object> requestMap = hcnOrderService.requestCheck(beginTime, methodName, request);

		String checkCode = (String) requestMap.get("code2");
		// 验证不通过直接返回
		if (!"200".equals(checkCode)) {
			logger.error("验证不通过,返回参数:" + FastJsonUtil.toJSONString(requestMap));
			return FastJsonUtil.toJSONString(requestMap);
		}
		// 移除自定义字段，防止意外异常
		requestMap.remove("code2");
		// 添加业务编码
		requestMap.put("proNum", AppCommonConst.SYSTEM_APP_USP_SET_BRDA);
		// 设置服务参数
		Map<String, Object> param = new HashMap<>();
		param.put("proName", AppCommonConst.SYSTEM_PROCEDURE_NAME);
		// 调用服务接口
		String result = hcnOrderService.invokeProc(CommonConst.SYSTEM_PROCEDURE_SQLKEY, null, requestMap, param);
		// 记录接口调用结束时间
		Date endTime = new Date();
		logger.info("调用接口结束:" + methodName + ":时间为:" + DateUtils.convertDateTime_YYYYMMDDHHMMSS_CN(endTime) + ",请求结果为:"
				+ 200 + ",时间标记为[" + endTime.getTime() + "],调用接口消费了" + (endTime.getTime() - beginTime.getTime()) + "毫秒");

		return result;
	}

	@RequestMapping(value = "/departmentDoctorState2", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String departmentDoctorState2(HttpServletRequest request) {
		// 记录接口调用开始时间
		Date beginTime = new Date();
		String methodName = "departmentDoctorState2";
		Map<String, Object> requestMap = hcnOrderService.requestCheck(beginTime, methodName, request);
		String checkCode = (String) requestMap.get("code2");
		if (!"200".equals(checkCode)) {// 验证不通过直接返回
			logger.error("验证不通过,返回参数:" + FastJsonUtil.toJSONString(requestMap));
			return FastJsonUtil.toJSONString(requestMap);
		}
		// 移除自定义字段，防止意外异常
		requestMap.remove("code2");
		// 添加编码
		requestMap.put("proNum", AppCommonConst.SYSTEM_APP_USP_CHK_YSPB);
		Map<String, Object> param = new HashMap<>();
		param.put("proName", AppCommonConst.SYSTEM_PROCEDURE_NAME);
		String result = hcnOrderService.invokeProc(CommonConst.SYSTEM_PROCEDURE_SQLKEY, null, requestMap, param);
		// 记录接口调用结束时间
		Date endTime = new Date();

		logger.info("调用接口结束" + methodName + ":时间为:" + DateUtils.convertDateTime_YYYYMMDDHHMMSS_CN(endTime) + ",请求结果为:"
				+ 200 + ",时间标记为[" + endTime.getTime() + "],调用接口消费了" + (endTime.getTime() - beginTime.getTime()) + "毫秒");

		return result;
	}

	@RequestMapping(value = "/scheduleDepartDoctormentOrder", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String scheduleDepartDoctormentOrder(HttpServletRequest request) {
		// 记录接口调用开始时间
		Date beginTime = new Date();
		String methodName = "scheduleDepartDoctormentOrder";

		Map<String, Object> requestMap = hcnOrderService.requestCheck(beginTime, methodName, request);
		String checkCode = (String) requestMap.get("code2");
		// 验证不通过直接返回
		if (!"200".equals(checkCode)) {
			logger.error("验证不通过,返回参数:" + FastJsonUtil.toJSONString(requestMap));
			return FastJsonUtil.toJSONString(requestMap);
		}
		// 移除自定义字段，防止意外异常
		requestMap.remove("code2");
		// 添加业务编码
		requestMap.put("proNum", AppCommonConst.SYSTEM_APP_USP_YYHB);

		Map<String, Object> param = new HashMap<>();
		param.put("proName", AppCommonConst.SYSTEM_PROCEDURE_NAME);
		String result = hcnOrderService.invokeProc(CommonConst.SYSTEM_PROCEDURE_SQLKEY, null, requestMap, param);
		// 记录接口调用结束时间
		Date endTime = new Date();
		logger.info("调用接口结束" + methodName + ":时间为:" + DateUtils.convertDateTime_YYYYMMDDHHMMSS_CN(endTime) + ",请求结果为:"
				+ 200 + ",时间标记为[" + endTime.getTime() + "],调用接口消费了" + (endTime.getTime() - beginTime.getTime()) + "毫秒");
		return result;
	}

	@RequestMapping(value = "/submitOrderRecord2", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String submitOrderRecord2(HttpServletRequest request) {
		// 记录接口调用开始时间
		Date beginTime = new Date();
		String methodName = "submitOrderRecord2";
		Map<String, Object> requestMap = hcnOrderService.requestCheck(beginTime, methodName, request);
		String checkCode = (String) requestMap.get("code2");
		// 验证不通过直接返回
		if (!"200".equals(checkCode)) {
			logger.error("验证不通过,返回参数:" + FastJsonUtil.toJSONString(requestMap));
			return FastJsonUtil.toJSONString(requestMap);
		}
		// 移除自定义字段，防止意外异常
		requestMap.remove("code2");
		// 添加页面编码
		requestMap.put("proNum", AppCommonConst.SYSTEM_APP_USP_YYGH);
		// 添加存储过程名
		Map<String, Object> param = new HashMap<>();
		param.put("proName", AppCommonConst.SYSTEM_PROCEDURE_NAME);
		// 调用存储过程返回名
		String result = hcnOrderService.invokeProc(CommonConst.SYSTEM_PROCEDURE_SQLKEY, null, requestMap, param);
		// 记录接口调用结束时间
		Date endTime = new Date();
		logger.info("调用接口结束" + methodName + ":时间为:" + DateUtils.convertDateTime_YYYYMMDDHHMMSS_CN(endTime) + ",请求结果为:"
				+ 200 + ",时间标记为[" + endTime.getTime() + "],调用接口消费了" + (endTime.getTime() - beginTime.getTime()) + "毫秒");

		return result;
	}

	/**
	 * 支付数据同步接口
	 * 
	 * 注意:以存储过程同步结果为依据，不依赖支付平台，由pb提供终极方案支持7日内退款
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/orderPay2", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String orderPay2(HttpServletRequest request) {
		// 记录接口调用开始时间
		Date beginTime = new Date();
		String methodName = "orderPay2";
		Map<String, Object> requestMap = hcnOrderService.requestCheck(beginTime, methodName, request);
		if (!"200".equals(RequestDataUtil.getValueForKey(requestMap, "code2"))) {// 验证不通过直接返回
			logger.debug("验证不通过,返回参数:" + FastJsonUtil.toJSONString(requestMap));
			return FastJsonUtil.toJSONString(requestMap);
		}
		requestMap.remove("code2");// 移除自定义字段，防止意外异常
		requestMap.put("proNum", AppCommonConst.SYSTEM_APP_USP_YYGH_PAY);// 同步数据
		Map<String, Object> param = new HashMap<>();
		param.put("proName", AppCommonConst.SYSTEM_PROCEDURE_NAME);
		// 返回jsonStr结果
		String result = hcnOrderService.invokeProc(CommonConst.SYSTEM_PROCEDURE_SQLKEY, null, requestMap, param);
		@SuppressWarnings("unchecked")
		Map<String, Object> returnPayMap = FastJsonUtil.toJSONObject(result, Map.class);// 返回结果转化为json

		if (returnPayMap == null) { // 检测返回json是否规范
			logger.error("业务处理失败=>:" + result);
			return ResultMessageUtil.getParamError("json请求参数不符合规范", null);
		}
		// 如同步his失败直接返回异常
		String returnPayCde = RequestDataUtil.getValueForKey(returnPayMap, "code");
		if (!"200".equals(returnPayCde)) {
			// 已支付,直接返回成功
			if ("202".equals(returnPayCde)) {
				return ResultMessageUtil.getSuccess(null);
			}

			logger.error("预约平台同步数据失败=>:" + result);
			return ResultMessageUtil.getSpecialServiceFail(null, RequestDataUtil.getValueForKey(returnPayMap, "msg"));
		}

		// 数据推送以及发送短信
		try {
			// 数据同步成功，与支付平台同步
			String paySource = RequestDataUtil.getValueForKey(requestMap, "source");

			if (StringUtils.isBlank(paySource)) {
				paySource = RequestDataUtil.getValueForKey(returnPayMap, "source");
			}

			if (CommonConst.HCN_SOURCE_iOS.equals(paySource) || CommonConst.HCN_SOURCE_ANDROID.equals(paySource)) {// app支付
				String paySync = hcnOrderService.paySync(returnPayMap, CommonConst.APP_ORGANIZATION_CODE,
						CommonConst.APP_COMPUTER_NAME, CommonConst.APP_IP);
				logger.info("app推送结果=>" + paySync);

			} else if (CommonConst.HCN_SOURCE_WX.equals(paySource)) {// 微信公众号
				String paySync = hcnOrderService.paySync(returnPayMap, CommonConst.WXPUB_ORGANIZATION_CODE,
						CommonConst.WXPUB_COMPUTER_NAME, CommonConst.WXPUB_IP);
				logger.info("微信公众号推送结果=>" + paySync);
			} else if (CommonConst.HCN_SOURCE_WEB.equals(paySource)) {// 微信官网
				String paySync = hcnOrderService.paySync(returnPayMap, CommonConst.APP_ORGANIZATION_CODE,
						CommonConst.WEB_COMPUTER_NAME, CommonConst.WEB_IP);
				logger.info("微信官网推送结果=>" + paySync);
			} else {// 默认处理
				String paySync = hcnOrderService.paySync(returnPayMap, CommonConst.APP_ORGANIZATION_CODE,
						CommonConst.APP_COMPUTER_NAME, CommonConst.APP_IP);
				logger.info("其他推送结果=>" + paySync);
			}
		} catch (Exception e) {
			logger.error("推送信息异常=>" + e.getMessage());
		}

		try {
			// 发送短信
			Map<String, Object> messageMap = new HashMap<>();
			// 短信模板
			messageMap.put("hcnPayTemplate", hcnPayTemplate);
			messageMap.put("firstVisitPaySuccessMsg", firstVisitPaySuccessMsg);
			messageMap.put("nextVisitSelfPaySuccessMsg", nextVisitSelfPaySuccessMsg);
			messageMap.put("nextVisitNoSelfPaySuccessMsg", nextVisitNoSelfPaySuccessMsg);
			// 添加膏方短信
			messageMap.put("creamPaySuccessMsg", creamPaySuccessMsg);
			// 消息类型
			messageMap.put("msgFmt", msgFmt);
			// 该短信对应应用ID
			messageMap.put("ApplicationID", ApplicationID);
			// 是否需要状态报告
			messageMap.put("reqDeliveryReport", reqDeliveryReport);
			messageMap.put("sendMethod", sendMethod);
			// 正常的是MS_YYGH的主键 之前是hb的识别序号
			String regRecordId = RequestDataUtil.getValueForKey(requestMap, "regRecordId");
			if (StringUtils.isNotBlank(regRecordId)) {
				messageMap.put("regRecordId", regRecordId);
			}

			String sendMessage = hcnOrderService.sendMessage(messageMap);// 预约成功发送短信
			logger.info("发送短信内容=>" + sendMessage);
		} catch (Exception e) {
			logger.error("发送短信异常=>" + e.getMessage());
		}
		Date endTime = new Date();
		logger.info("调用接口结束:" + methodName + ",时间为:" + DateUtils.convertDateTime_YYYYMMDDHHMMSS_CN(endTime) + ",请求结果为:"
				+ 200 + ",时间标记为[" + endTime.getTime() + "],调用接口消费了" + (endTime.getTime() - beginTime.getTime()) + "毫秒");

		return result;// 返回同步数据结果
	}

	/**
	 * 取消预约并退款
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/cancelOrderRecord2", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String cancelOrderRecord2(HttpServletRequest request, HttpServletResponse response) {
		Date beginTime = new Date();
		String methodName = "cancelOrderRecord2";
		Map<String, Object> requestMap = hcnOrderService.requestCheck(beginTime, methodName, request);
		String checkCode = RequestDataUtil.getValueForKey(requestMap, "code2");
		if (!"200".equals(checkCode)) {// 验证不通过直接返回
			logger.debug("验证不通过,返回参数:" + FastJsonUtil.toJSONString(requestMap));
			return FastJsonUtil.toJSONString(requestMap);
		}
		requestMap.remove("code2");
		// 判断是否已经取消成功,取消成功直接跳转到下一步
		String recordId = RequestDataUtil.getValueForKey(requestMap, "regRecordId");
		if (StringUtils.isBlank(recordId)) {
			logger.debug("号源识别号未取到,取到结果:" + recordId);
			return ResultMessageUtil.getParamError(null, "请输入号源识别号");
		}

		Map<String, Object> param = new HashMap<>();
		param.put("yyxh", recordId);

		Map<String, Object> hisNumMap = hcnOrderService.cancelPay("cancelPay.validateCancelPay2", null, param);

		Map<String, Object> resultMap = null;
		if (hisNumMap == null || hisNumMap.size() < 3) {
			// 第一次取消取消预约
			requestMap.put("proNum", AppCommonConst.SYSTEM_APP_USP_YYGH_CANCEL);
			param.put("proName", AppCommonConst.SYSTEM_PROCEDURE_NAME);
			String result = hcnOrderService.invokeProc(CommonConst.SYSTEM_PROCEDURE_SQLKEY, null, requestMap, param);
			resultMap = FastJsonUtil.toJSONObject(result, Map.class);
			if (resultMap == null) {
				resultMap = new HashMap<>();
			}

			if (!"200".equals(RequestDataUtil.getValueForKey(resultMap, "code"))) {
				logger.debug("业务处理失败,处理结果:" + result);
				return ResultMessageUtil.getSpecialServiceFail(null, result);
			}
		}

		String yyhospno = "";
		String thhospno = "";
		String payAmount = "";
		if (resultMap == null) {
			// 非第一次成功取消
			yyhospno = RequestDataUtil.getValueForKey(hisNumMap, "YYHOSPNO");
			thhospno = RequestDataUtil.getValueForKey(hisNumMap, "THHOSPNO");

			BigDecimal monery = (BigDecimal) hisNumMap.get("PAYAMOUNT");
			monery = monery.abs();// 取绝对值
			payAmount = monery + "";
		} else {// 第一次调用取消
			yyhospno = RequestDataUtil.getValueForKey(resultMap, "yyhospno");
			thhospno = RequestDataUtil.getValueForKey(resultMap, "thhospno");
			payAmount = RequestDataUtil.getValueForKey(resultMap, "payAmount");
		}

		request.setAttribute("hospNo", yyhospno);// 订单号
		request.setAttribute("refund_fee", payAmount);// 退款金额
		request.setAttribute("out_request_no", thhospno);// 退款订单号

		// 发出退款 如果退款失败是否要回执数据
		String paySource = RequestDataUtil.getValueForKey(requestMap, "source");

		String payResult = null;
		if (CommonConst.HCN_SOURCE_iOS.equals(paySource) || CommonConst.HCN_SOURCE_ANDROID.equals(paySource)) {// app支付
			payResult = hcnOrderService.refund(request, response, CommonConst.APP_ORGANIZATION_CODE,
					CommonConst.APP_COMPUTER_NAME, CommonConst.APP_IP);
		} else if (CommonConst.HCN_SOURCE_WX.equals(paySource)) {// 微信公众号
			payResult = hcnOrderService.refund(request, response, CommonConst.WXPUB_ORGANIZATION_CODE,
					CommonConst.WXPUB_COMPUTER_NAME, CommonConst.WXPUB_IP);

		} else if (CommonConst.HCN_SOURCE_WEB.equals(paySource)) {// 微信官网
			payResult = hcnOrderService.refund(request, response, CommonConst.WEB_ORGANIZATION_CODE,
					CommonConst.WEB_COMPUTER_NAME, CommonConst.WEB_IP);
		} else {
			payResult = hcnOrderService.refund(request, response, CommonConst.APP_ORGANIZATION_CODE,
					CommonConst.APP_COMPUTER_NAME, CommonConst.APP_IP);
		}

		Map<String, Object> payResultMap = FastJsonUtil.toJSONObject(payResult, Map.class);

		if (!"200".equals(RequestDataUtil.getValueForKey(payResultMap, "code"))) {// 说明退款业务处理失败
			logger.error("退款业务处理失败,处理结果:" + payResult);
			return ResultMessageUtil.getSpecialServiceFail(null, payResult);
		}

		// 退款成功，开始调用退款存储过程,同步数据
		Map<String, Object> refundRquestMap = new HashMap<>();
		refundRquestMap.put("thhospno", thhospno);
		refundRquestMap.put("proNum", AppCommonConst.SYSTEM_APP_USP_YYGH_REFUND);
		// 覆盖之前的存储过程名
		param.put("proName", AppCommonConst.SYSTEM_PROCEDURE_NAME);
		// 退款成功后业务数据同步
		String refundResult = hcnOrderService.invokeProc(CommonConst.SYSTEM_PROCEDURE_SQLKEY, null, refundRquestMap,
				param);
		Map<String, Object> refundResultMap = FastJsonUtil.toJSONObject(refundResult, Map.class);

		if (!"200".equals(RequestDataUtil.getValueForKey(refundResultMap, "code"))) {// 说明退款业务处理失败
			logger.error("退款成功后业务数据同步失败,处理结果:" + refundResult);
			return ResultMessageUtil.getSpecialServiceFail(null, refundResult);
		}
		// 退款数据同步成功
		Date endTime = new Date();
		logger.info("调用接口结束:" + methodName + ":时间为:" + DateUtils.convertDateTime_YYYYMMDDHHMMSS_CN(endTime)
				+ ",请求结果信息为:" + 200 + ",时间标记为[" + endTime.getTime() + "],调用接口消费了"
				+ (endTime.getTime() - beginTime.getTime()) + "毫秒");

		return refundResult;
	}

	@RequestMapping(value = "/tuik", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String tuik(HttpServletRequest request, HttpServletResponse response) {
		// 记录接口调用开始时间
		Date beginTime = new Date();
		String methodName = "tuik";

		Map<String, Object> requestMap = hcnOrderService.requestCheck(beginTime, methodName, request);
		String checkCode = (String) requestMap.get("code2");
		if (!"200".equals(checkCode)) {// 验证不通过直接返回
			logger.debug("验证不通过,返回参数:" + FastJsonUtil.toJSONString(requestMap));
			return FastJsonUtil.toJSONString(requestMap);
		}
		// 移除自定义字段，防止意外异常
		requestMap.remove("code2");

		// 对转换后的jsonStr转换为对象然后添加proNum服务码字段

		// 预约成功,开始退款
		String yyhospno = "YYGH700260247171031154104";
		String thhospno = "YYTHHCN700260247171031154104";
		String payAmount = "15";
		request.setAttribute("hospNo", yyhospno);// 订单号
		request.setAttribute("refund_fee", payAmount);// 退款金额
		request.setAttribute("out_request_no", thhospno);// 退款订单号
		// 发出退款
		String payResult = hcnOrderService.refund(request, response, "466002630", "BSAPP", "BSAPP");
		System.out.println(payResult);

		Date endTime = new Date();
		logger.info("开始调用接口:" + methodName + ",时间为:" + DateUtils.convertDateTime_YYYYMMDDHHMMSS_CN(endTime) + ",请求信息为:"
				+ request + ",时间标记为[" + endTime.getTime() + "],调用接口消费了" + (endTime.getTime() - beginTime.getTime())
				+ "毫秒");

		// 返回最终的退款业务
		// return payResult;
		// return sendMessage;
		return "ok";
	}

	@RequestMapping(value = "/preSettlement", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String preSettlement(HttpServletRequest request) {
		// 记录接口调用开始时间
		Date beginTime = new Date();
		String methodName = "preSettlement";

		Map<String, Object> requestMap = hcnOrderService.requestCheck(beginTime, methodName, request);
		String checkCode = (String) requestMap.get("code2");
		if (!"200".equals(checkCode)) {// 验证不通过直接返回
			logger.error("验证不通过,返回参数:" + FastJsonUtil.toJSONString(requestMap));
			return FastJsonUtil.toJSONString(requestMap);
		}
		requestMap.remove("code2");
		requestMap.put("proNum", AppCommonConst.SYSTEM_APP_USP_YYGH_AMOUNT);
		Map<String, Object> param = new HashMap<>();
		param.put("proName", AppCommonConst.SYSTEM_PROCEDURE_NAME);
		String result = hcnOrderService.invokeProc(CommonConst.SYSTEM_PROCEDURE_SQLKEY, null, requestMap, param);
		Date endTime = new Date();
		logger.info("调用接口结束" + methodName + ":时间为:" + DateUtils.convertDateTime_YYYYMMDDHHMMSS_CN(endTime) + ",请求结果为:"
				+ 200 + ",时间标记为[" + endTime.getTime() + "],调用接口消费了" + (endTime.getTime() - beginTime.getTime()) + "毫秒");
		return result;
	}

	@RequestMapping(value = "/preSettlementDetail", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String preSettlementDetail(HttpServletRequest request) {
		Date beginTime = new Date();
		String methodName = "preSettlementDetail";
		Map<String, Object> requestMap = hcnOrderService.requestCheck(beginTime, methodName, request);
		String checkCode = (String) requestMap.get("code2");
		if (!"200".equals(checkCode)) {// 验证不通过直接返回
			logger.error("验证不通过,返回参数:" + FastJsonUtil.toJSONString(requestMap));
			return FastJsonUtil.toJSONString(requestMap);
		}
		requestMap.remove("code2");
		requestMap.put("proNum", AppCommonConst.SYSTEM_APP_USP_YYGH_AMOUNT_DETAIL);
		Map<String, Object> param = new HashMap<>();
		param.put("proName", AppCommonConst.SYSTEM_PROCEDURE_NAME);
		String result = hcnOrderService.invokeProc(CommonConst.SYSTEM_PROCEDURE_SQLKEY, null, requestMap, param);
		Date endTime = new Date();
		logger.info("调用接口结束" + methodName + ":时间为:" + DateUtils.convertDateTime_YYYYMMDDHHMMSS_CN(endTime) + ",请求结果为:"
				+ 200 + ",时间标记为[" + endTime.getTime() + "],调用接口消费了" + (endTime.getTime() - beginTime.getTime()) + "毫秒");

		return result;
	}

	@RequestMapping(value = "/orderRecord", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String orderRecord(HttpServletRequest request) {
		Date beginTime = new Date();
		String methodName = "orderRecord";

		Map<String, Object> requestMap = hcnOrderService.requestCheck(beginTime, methodName, request);
		// 验证不通过直接返回
		if (!"200".equals(RequestDataUtil.getValueForKey(requestMap, "code2"))) {
			logger.error("安全验证不通过,返回参数:" + FastJsonUtil.toJSONString(requestMap));
			return FastJsonUtil.toJSONString(requestMap);
		}
		requestMap.remove("code2");

		requestMap.put("proNum", AppCommonConst.SYSTEM_APP_USP_YSDM_RQ);

		Map<String, Object> param = RequestDataUtil.getMapByInputParam(Arrays.asList("proName"),
				Arrays.asList(AppCommonConst.SYSTEM_PROCEDURE_NAME));

		String result = hcnOrderService.invokeProc(CommonConst.SYSTEM_PROCEDURE_SQLKEY, null, requestMap, param);
		Date endTime = new Date();
		logger.info("调用接口结束" + methodName + ":时间为:" + DateUtils.convertDateTime_YYYYMMDDHHMMSS_CN(endTime) + ",请求结果为:"
				+ 200 + ",时间标记为[" + endTime.getTime() + "],调用接口消费了" + (endTime.getTime() - beginTime.getTime()) + "毫秒");

		return result;
	}

	@ExceptionHandler(value = { Exception.class })
	@ResponseBody
	public String exceptionHander(Exception ex, HttpServletRequest request) {
		logger.error("其它异常=>" + ex.getMessage());
		return ResultMessageUtil.getSpecialServiceFail(null, "其它错误");
	}
}
