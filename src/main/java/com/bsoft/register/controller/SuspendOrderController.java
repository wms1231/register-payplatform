package com.bsoft.register.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bsoft.constant.CommonConstant;
import com.bsoft.exception.WebException;
import com.bsoft.register.service.SuspendOrderService;
import com.bsoft.support.PageResult;
import com.bsoft.support.Pager;
import com.bsoft.support.utils.PageResultUtil;
import com.bsoft.tools.CharacterEncodeUtil;
import com.bsoft.tools.DateUtils;
import com.bsoft.tools.FastJsonUtil;
import com.bsoft.tools.RequestDataUtil;

/**
 * 医生停诊订单查询
 * 
 * @author wms1231
 *
 */
@Controller
@RequestMapping("/order")
public class SuspendOrderController {
	private static Logger logger = Logger.getLogger(SuspendOrderController.class);
	private final static String NULL_VALUE = "";
	@Autowired
	private SuspendOrderService suspendOrderService;

	@RequestMapping(value = "/depts/{deptCode}", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String deptsQuery(@PathVariable("deptCode") String deptCode,
			@RequestParam(value = "sqlKey", required = false) String sqlKey,
			@RequestParam(value = "dataSource", required = false) String dataSource,
			@RequestParam(value = "orders", required = false) String[] orders) {

		String returnMsg = NULL_VALUE;
		try {
			Map<String, Object> param = new HashMap<String, Object>();

			if (StringUtils.isBlank(deptCode) || "all".equals(deptCode.toLowerCase())) {
				param.put("deptCode", "");
			} else {
				param.put("deptCode", deptCode);
			}
			
			if (StringUtils.isBlank(sqlKey)) {
				sqlKey = "dept.queryDept";
			}

			if (orders != null && orders.length > 0) {
				for (String order : orders) {
					order = order.split(":")[0] + ":" + order.split(":")[1].toLowerCase();
					if (!order.trim().matches(".+:desc|.+:asc")) {
						throw new WebException(CommonConstant.DEFAULT_FAIL_CODE, "order格式必须为 字段名:desc或 字段名:asc");
					}
				}
			}

			List<Map<String, Object>> mapList = suspendOrderService.departQuery(sqlKey, dataSource, param, orders);
			returnMsg = CharacterEncodeUtil.returnEncode(FastJsonUtil.toJSONString(mapList));
		} catch (Exception e) {
			throw new WebException(CommonConstant.DEFAULT_FAIL_CODE, e.getMessage());
		}
		return returnMsg;

	}

	@RequestMapping(value = "/doctors/{deptCode}", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String doctorsQuery(@PathVariable("deptCode") String deptCode,
			@RequestParam(value = "sqlKey", required = false) String sqlKey,
			@RequestParam(value = "dataSource", required = false) String dataSource,
			@RequestParam(value = "orders", required = false) String[] orders) {

		String returnMsg = NULL_VALUE;
		try {
			if (orders != null && orders.length > 0) {
				for (String order : orders) {
					order = order.split(":")[0] + ":" + order.split(":")[1].toLowerCase();
					if (!order.trim().matches(".+:desc|.+:asc")) {
						throw new WebException(CommonConstant.DEFAULT_FAIL_CODE, "order格式必须为 字段名:desc或 字段名:asc");
					}
				}
			}
			Map<String, Object> param = new HashMap<String, Object>();
			if ("all".equals(deptCode.toLowerCase())) {
				param.put("deptCode", "");
			} else {
				param.put("deptCode", deptCode);
			}

			if (StringUtils.isBlank(sqlKey)) {
				sqlKey = "dept.queryDoctor";
			}

			List<Map<String, Object>> mapList = suspendOrderService.doctorsQuery(sqlKey, dataSource, param, orders);
			returnMsg = CharacterEncodeUtil.returnEncode(FastJsonUtil.toJSONString(mapList));
		} catch (Exception e) {
			throw new WebException(CommonConstant.DEFAULT_FAIL_CODE, e.getMessage());
		}
		return returnMsg;

	}
	
	
	
	@RequestMapping(value = "/dept/{doctorCode}/{doctorName}", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String deptMutiQuery(@PathVariable("doctorCode") String doctorCode,
			@PathVariable("doctorName") String doctorName,
			@RequestParam(value = "sqlKey", required = false) String sqlKey,
			@RequestParam(value = "dataSource", required = false) String dataSource,
			@RequestParam(value = "orders", required = false) String[] orders) {

		String returnMsg = NULL_VALUE;
		try {
			if (orders != null && orders.length > 0) {
				for (String order : orders) {
					order = order.split(":")[0] + ":" + order.split(":")[1].toLowerCase();
					if (!order.trim().matches(".+:desc|.+:asc")) {
						throw new WebException(CommonConstant.DEFAULT_FAIL_CODE, "order格式必须为 字段名:desc或 字段名:asc");
					}
				}
			}
			Map<String, Object> param = new HashMap<String, Object>();
			if("all".equals(doctorCode.toLowerCase())){
				param.put("doctorCode", "");
			}else{
				param.put("doctorCode", doctorCode);
			}
			
			if("all".equals(doctorName.toLowerCase())){
				param.put("doctorName", "");
			}else{
				param.put("doctorName", CharacterEncodeUtil.requestEncode(doctorName));
			}

			if (StringUtils.isBlank(sqlKey)) {
				sqlKey = "dept.mutiQueryDept";
			}

			List<Map<String, Object>> mapList = suspendOrderService.deptMutiQuery(sqlKey, dataSource, param, orders);
			returnMsg = CharacterEncodeUtil.returnEncode(FastJsonUtil.toJSONString(mapList));
		} catch (Exception e) {
			throw new WebException(CommonConstant.DEFAULT_FAIL_CODE, e.getMessage());
		}
		return returnMsg;

	}

	
	
	

	@RequestMapping(value = "/doctor/orders/{deptCode}/{doctorCode}/{timeFlag}/{orderState}/{stopFlag}/{beginTime}/{endTime}", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String suspendOrderQuery(@PathVariable("deptCode") String deptCode,
			@PathVariable("doctorCode") String doctorCode, @PathVariable("timeFlag") String timeFlag,
			@PathVariable("orderState") String orderState, @PathVariable("stopFlag") String stopFlag,
			@PathVariable("beginTime") String beginTime, @PathVariable("endTime") String endTime,
			@RequestParam(value = "sqlKey", required = false) String sqlKey,
			@RequestParam(value = "dataSource", required = false) String dataSource,
			@RequestParam(value = "pageNo", required = true) Integer pageNo,
			@RequestParam(value = "pageSize", required = false) Integer pageSize,
			@RequestParam(value = "orders", required = false) String[] orders) {

		String returnMsg = NULL_VALUE;
		try {
			if (!timeFlag.matches("[12]{1}")) {
				throw new WebException(CommonConstant.DEFAULT_FAIL_CODE, "timeFlag字段必须为1或2");
			}

			if (orders != null && orders.length > 0) {
				for (String order : orders) {
					order = order.split(":")[0] + ":" + order.split(":")[1].toLowerCase();
					if (!order.trim().matches(".+:desc|.+:asc")) {
						throw new WebException(CommonConstant.DEFAULT_FAIL_CODE, "order格式必须为 字段名:desc或 字段名:asc");
					}
				}
			}

			if (!beginTime.matches("\\d{4}-\\d{1,2}-\\d{1,2}")) {
				throw new WebException(CommonConstant.DEFAULT_FAIL_CODE, "beginTime日期字符串不满足默认的yyyy-MM-dd格式");
			}

			if (!endTime.matches("\\d{4}-\\d{1,2}-\\d{1,2}")) {
				throw new WebException(CommonConstant.DEFAULT_FAIL_CODE, "date日期字符串不满足默认的yyyy-MM-dd格式");
			}

			if (StringUtils.isBlank(sqlKey)) {
				sqlKey = "dept.queryDoctorOrder";
			}
			if (pageSize == null || pageSize < 1) {
				pageSize = 1;
			}

			if (pageNo == null || pageNo < 1) {
				pageNo = 1;
			}
			Map<String, Object> param = RequestDataUtil.getMapByInputParam(
					Arrays.asList("deptCode", "doctorCode", "timeFlag", "orderState", "beginTime", "endTime",
							"stopFlag"),
					Arrays.asList(deptCode, doctorCode, timeFlag, orderState,
							DateUtils.getDefaultQueryTime(beginTime, true),
							DateUtils.getDefaultQueryTime(endTime, false), stopFlag));
			
			if("all".equals(deptCode.toLowerCase())){
				param.remove("deptCode");
			}
			if("all".equals(doctorCode.toLowerCase())){
				param.remove("doctorCode");
			}
			if("all".equals(timeFlag.toLowerCase())){
				param.remove("timeFlag");
			}
			if("all".equals(orderState.toLowerCase())){
				param.remove("orderState");
			}
			if("all".equals(stopFlag.toLowerCase())){
				param.remove("stopFlag");
			}
			
			Pager page = suspendOrderService.suspendOrderQuery(sqlKey, dataSource, param, pageNo, pageSize, orders);
			PageResult pageResult = PageResultUtil.getSuccessPageResult(page.getRowCount(), page.getPageSize(), pageNo,
					page.getResult(), "success");
			returnMsg = CharacterEncodeUtil.returnEncode(FastJsonUtil.toJSONString(pageResult));
		} catch (Exception e) {
			throw new WebException(CommonConstant.DEFAULT_FAIL_CODE, e.getMessage());
		}
		return returnMsg;
	}

	@ExceptionHandler(value = { Exception.class })
	@ResponseBody
	public String exceptionHander(Exception ex, HttpServletRequest request) {
		logger.error("请求时间=>" + DateUtils.getCurrentDate_YYYYMMDDHHMMSS_CN() + " 调用发生异常,请求path=>"
				+ request.getPathInfo() + "请求参数=>" + FastJsonUtil.toJSONString(request.getParameterMap()));
		return FastJsonUtil.toJSONString(PageResultUtil.getFailPageResult(0L, 0, 0, null, ex.getMessage()));
		
	}

}
