package com.bsoft.register.controller;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bsoft.exception.RegisterException;
import com.bsoft.exception.WebException;
import com.bsoft.register.service.RegisterService;
import com.bsoft.tools.DateFormatUtils;
import com.bsoft.tools.JSONObjectUtils;
import com.bsoft.tools.UserUtils;

@Controller
@RequestMapping("/register")
public class RegisterController {

	@Autowired
	private RegisterService registerService;

	private static Logger logger = Logger.getLogger(RegisterController.class);

	@RequestMapping(value = "/createpatientinfo", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String createPatientInfo(@RequestParam(value = "patIDType", required = true) String patIDType,
			@RequestParam(value = "patID", required = true) String patID,
			@RequestParam(value = "patName", required = true) String patName,
			@RequestParam(value = "patGender", required = true) String patGender,
			@RequestParam(value = "patTel", required = true) String patTel,
			@RequestParam(value = "patAge", required = false) String patAge) {

		return JSONObjectUtils.getSuccessJsonWithList(0, "success", "data",
				registerService.createPatientInfo(patIDType, patID, patName, patGender, patTel, patAge));
	}

	/**
	 * 获取科室信息
	 * 
	 * @param parentdeptCode
	 * @param deptCode
	 * @return
	 */
	@RequestMapping(value = "/depinfo", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getDepInfoWeb(@RequestParam(value = "parentdeptCode", required = false) String parentdeptCode,
			@RequestParam(value = "deptCode", required = false) String deptCode) {
		return JSONObjectUtils.getSuccessJsonWithMap(0, "success", "data",
				registerService.getDepInfoWeb(parentdeptCode, deptCode));
	}

	/**
	 * 大科室信息查询
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getdepinfo", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getDepInfo(HttpServletRequest request, HttpServletResponse response) {
		return JSONObjectUtils.getSuccessJsonWithMap(0, "success", "data",
				registerService.getDepInfo(request, response));
	}

	/**
	 * 获取号源信息(挂号科室)
	 * 
	 * @param beginDate
	 *            开始时间
	 * @param endDate
	 *            结束时间
	 * @param deptCode
	 *            科室信息
	 * @param doctorCode
	 *            医生信息
	 * @return
	 */
	@RequestMapping(value = "/hisorderinfo", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getHisOrderInfo(@RequestParam(value = "deptCode", required = false) String deptCode,
			@RequestParam(value = "parentDeptCode", required = false) String parentDeptCode,
			@RequestParam(value = "doctorCode", required = false) String doctorCode) {

		return JSONObjectUtils.getSuccessJsonWithList(0, "success", "data",
				registerService.getHisOrderInfo(deptCode, parentDeptCode, doctorCode));
	}

	/**
	 * 获取号源信息(挂号科室) 膏方
	 * 
	 * @param beginDate
	 *            开始时间
	 * @param endDate
	 *            结束时间
	 * @param deptCode
	 *            科室信息
	 * @param doctorCode
	 *            医生信息
	 * @return
	 */
	@RequestMapping(value = "/scriptOrderinfo", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getScriptOrderInfo(@RequestParam(value = "deptCode", required = false) String deptCode,
			@RequestParam(value = "parentDeptCode", required = false) String parentDeptCode,
			@RequestParam(value = "doctorCode", required = false) String doctorCode) {

		return JSONObjectUtils.getSuccessJsonWithList(0, "success", "data",
				registerService.getScriptOrderInfo(deptCode, parentDeptCode, doctorCode));
	}

	/**
	 * 根据医生名称 或者字母缩写查询医生信息
	 * 
	 * @param doctorName
	 * @return
	 */
	@RequestMapping(value = "/getdocionfo", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getDocInfo(@RequestParam(value = "doctorName", required = false) String doctorName) {
		return JSONObjectUtils.getSuccessJsonWithList(0, "success", "data", registerService.getDocInfo(doctorName));
	}

	/**
	 * 根据医生名称 或者字母缩写查询医生信息(膏方)
	 * 
	 * @param doctorName
	 * @return
	 */
	@RequestMapping(value = "/getCreamDeptSearch", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getCreamDeptSearch(@RequestParam(value = "doctorName", required = false) String doctorName) {
		return JSONObjectUtils.getSuccessJsonWithList(0, "success", "data",
				registerService.getCreamDeptSearch(doctorName));
	}

	/**
	 * 预约挂号
	 * 
	 * @param hisOrdNum
	 * @param cardtype
	 * @param cardNo
	 * @param patName
	 * @param phonenum
	 * @return
	 */
	@RequestMapping(value = "/register", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String hisRegister(@RequestParam(value = "hisOrdNum", required = true) String hisOrdNum,
			@RequestParam(value = "phonenum", required = true) String phonenum,
			@RequestParam(value = "patIndex", required = true) String patIndex,
			@RequestParam(value = "regChannel", required = true) String regChannel) {

		return JSONObjectUtils.getSuccessJsonWithList(0, "success", "data",
				registerService.hisRegister(hisOrdNum, phonenum, patIndex, regChannel));
	}

	@RequestMapping(value = "/querypatientinfo", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String querypatientinfo(@RequestParam(value = "cardtype", required = true) String cardtype,
			@RequestParam(value = "cardNo", required = true) String cardNo) {
		return JSONObjectUtils.getSuccessJsonWitObj(0, "success", "data",
				registerService.querypatientinfo(cardtype, cardNo));
	}

	/**
	 * 取消预约
	 * 
	 * @param hisOrdNum号源识别序号
	 * @param patIndex病人主索引
	 * @param serialNum就诊序号
	 * @param cancelReason取消原因
	 * @return
	 */
	@RequestMapping(value = "/cancelregister", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String cancelhisRegister(@RequestParam(value = "hisOrdNum", required = true) String hisOrdNum,
			@RequestParam(value = "patIndex", required = true) String patIndex,
			@RequestParam(value = "serialNum", required = true) String serialNum,
			@RequestParam(value = "cancelReason", required = true) String cancelReason) {

		return JSONObjectUtils.getSuccessJsonWithList(0, "success", "data",
				registerService.cancelhisRegister(hisOrdNum, patIndex, serialNum, cancelReason));
	}

	/**
	 * 查询预约记录
	 * 
	 * @param scheduleDate
	 * @param parentdeptCode
	 * @param deptCode
	 * @param doctorCode
	 * @param patIndex
	 * @return
	 */
	@RequestMapping(value = "/orderrecord", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String orderRecord(@RequestParam(value = "scheduleDate", required = false) String scheduleDate,
			@RequestParam(value = "parentdeptCode", required = false) String parentdeptCode,
			@RequestParam(value = "deptCode", required = false) String deptCode,
			@RequestParam(value = "parentdeptCode", required = false) String doctorCode,
			@RequestParam(value = "patIndex", required = false) String patIndex) {

		return JSONObjectUtils.getSuccessJsonWithList(0, "success", "data",
				registerService.orderRecord(scheduleDate, parentdeptCode, deptCode, doctorCode, patIndex));
	}

	/**
	 * 支付结果通知
	 * 
	 * @param hisOrdNum
	 * @param payMode
	 * @param payAmt
	 * @return
	 */
	@RequestMapping(value = "/notification", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String notification(@RequestParam(value = "hisOrdNum", required = false) String hisOrdNum,
			@RequestParam(value = "payMode", required = false) String payMode,
			@RequestParam(value = "payAmt", required = false) String payAmt,
			@RequestParam(value = "payFlag", required = false) String payFlag) {
		return JSONObjectUtils.getSuccessJsonWithList(0, "success", "data",
				registerService.notification(hisOrdNum, payMode, payAmt, payFlag));
	}

	@RequestMapping(value = "/refundnotification", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String refundnotification(@RequestParam(value = "hisOrdNum", required = false) String hisOrdNum,
			@RequestParam(value = "payAmt", required = false) String payAmt, HttpServletRequest request) {

		String cancelTime = DateFormatUtils.format(new Date(), DateFormatUtils.DATE_TIME_PATTERN);
		return JSONObjectUtils.getSuccessJsonWithList(0, "success", "data",
				registerService.refundnotification(hisOrdNum, payAmt, UserUtils.getCurrentUser(request), cancelTime));
	}

	@RequestMapping(value = "/getservertime", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getServertime() {
		return registerService.getServertime();
	}

	@ExceptionHandler(value = { Exception.class })
	@ResponseBody
	public String exceptionHander(Exception ex, HttpServletRequest request) {
		if (ex instanceof RegisterException) {
			RegisterException e = (RegisterException) ex;
			logger.error("RegisterException注册相关异常," + e.getMessage());
			return JSONObjectUtils.getFailJson(-1, e.getMessage());
		}

		if (ex instanceof WebException) {
			WebException e = (WebException) ex;
			logger.error("WebException相关异常," + e.getMessage());
			return JSONObjectUtils.getFailJson(-1, e.getMessage());
		}
		logger.error("其它相关异常," + ex.getMessage());
		return JSONObjectUtils.getFailJson(-1, ex.getMessage());
	}
}
