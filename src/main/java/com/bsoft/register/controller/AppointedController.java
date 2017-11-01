package com.bsoft.register.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bsoft.domain.DeptReturnBean;
import com.bsoft.domain.DoctorReturnBean;
import com.bsoft.domain.OrderSourceReturnBean;
import com.bsoft.exception.RegisterException;
import com.bsoft.exception.WebException;
import com.bsoft.factory.BeanService;
import com.bsoft.register.service.AppointedService;
import com.bsoft.support.JsonResult;
import com.bsoft.tools.FastJsonUtil;
import com.bsoft.tools.JSONObjectUtils;

@Controller
@RequestMapping("/appointed")
public class AppointedController {
	private static Logger logger = Logger.getLogger(AppointedController.class);
	@Autowired
	private AppointedService appointedService;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getdepinfo", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getDepInfo(@RequestParam(value = "parentdeptCode", required = false) String parentdeptCode,
			@RequestParam(value = "deptCode", required = false) String deptCode) {

		BeanService bshisservice = appointedService.queryDeptInfo(deptCode, parentdeptCode);
		List<DeptReturnBean> list = (List<DeptReturnBean>) bshisservice.getData();
		return JSONObjectUtils.getSuccessJsonWithList(0, "success", "data", list);
	}

	/**
	 * 获取医生信息
	 * 
	 * @param parentdeptCode
	 * @param deptCode
	 * @param doctorCode
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getdocionfo", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getDocInfo(@RequestParam(value = "parentdeptCode", required = false) String parentdeptCode,
			@RequestParam(value = "deptCode", required = false) String deptCode,
			@RequestParam(value = "doctorCode", required = false) String doctorCode) {

		BeanService bshisservice = appointedService.queryDoctorInfo(parentdeptCode, deptCode, doctorCode);
		List<DoctorReturnBean> list = (List<DoctorReturnBean>) bshisservice.getData();
		return JSONObjectUtils.getSuccessJsonWithList(0, "success", "data", list);
	}

	/**
	 * 获取号源信息
	 * 
	 * @param beginDate
	 * @param endDate
	 * @param deptCode
	 * @param doctorCode
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/gethisorderinfo", method = RequestMethod.GET)
	@ResponseBody
	public String getHisOrderInfo(@RequestParam("beginDate") String beginDate, @RequestParam("endDate") String endDate,
			@RequestParam("deptCode") String deptCode, @RequestParam("doctorCode") String doctorCode) {
		JsonResult jsonResult = new JsonResult();
		BeanService bshisservice = appointedService.appointmentRegOrderSourceQuery(beginDate, endDate, deptCode, null,
				doctorCode);

		List<OrderSourceReturnBean> list = (List<OrderSourceReturnBean>) bshisservice.getData();
		Map<String, Object> data = new HashMap<>();
		data.put("data", list);
		data.put("rows", list.size());
		jsonResult.setCode("200");
		jsonResult.setMsg("success");
		jsonResult.setContent(data);

		return FastJsonUtil.toJSONString(jsonResult);
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
