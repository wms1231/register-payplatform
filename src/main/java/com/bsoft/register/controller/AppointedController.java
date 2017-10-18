package com.bsoft.register.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.bsoft.domain.DeptReturnBean;
import com.bsoft.domain.DoctorReturnBean;
import com.bsoft.domain.HeadBean;
import com.bsoft.domain.OrderSourceReturnBean;
import com.bsoft.factory.BeanService;
import com.bsoft.register.service.AppointedService;
import com.bsoft.support.JsonResult;
import com.bsoft.tools.FastJsonUtil;

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
		List<DeptReturnBean> list = new ArrayList<DeptReturnBean>();

		JSONObject json = new JSONObject();
		json.put("code", 0);
		json.put("msg", "success");

		BeanService bshisservice = null;
		try {
			bshisservice = appointedService.queryDeptInfo(deptCode, parentdeptCode);
		} catch (Exception e) {
			logger.error("其它异常=>", e);
			json.put("code", -1);
			if (bshisservice.getHead() != null) {
				json.put("msg", bshisservice.getHead().getResultMessage());
			}

			return json.toJSONString();
		}
		HeadBean bean = bshisservice.getHead();
		if (!bean.getResultCode().equals("0000")) {
			json.put("code", -1);
			json.put("msg", bean.getResultMessage());
		} else {
			list = (List<DeptReturnBean>) bshisservice.getData();
			json.put("data", list);
		}
		return json.toJSONString();

	}
	
	
	/**
	 * 获取医生信息
	 * 
	 * @param parentdeptCode
	 *            父科室编码
	 * @param deptCode
	 *            科室编码
	 * @param doctorCode
	 *            医生编码
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getdocionfo", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getDocInfo(@RequestParam(value = "parentdeptCode", required = false) String parentdeptCode,
			@RequestParam(value = "deptCode", required = false) String deptCode,
			@RequestParam(value = "doctorCode", required = false) String doctorCode) {
		List<DoctorReturnBean> list = new ArrayList<DoctorReturnBean>();

		JSONObject json = new JSONObject();
		json.put("code", 0);
		json.put("msg", "success");

		BeanService bshisservice = null;
		try {
			bshisservice = appointedService.queryDoctorInfo(parentdeptCode, deptCode, doctorCode);
		} catch (Exception e) {
			logger.error("其它异常=>", e);
			json.put("code", -1);
			if (bshisservice.getHead() != null) {
				json.put("msg", bshisservice.getHead().getResultMessage());
			}
		}
		HeadBean bean = bshisservice.getHead();
		if (!bean.getResultCode().equals("0000")) {
			json.put("code", -1);
			json.put("msg", bean.getResultMessage());
		}else {
			list = (List<DoctorReturnBean>) bshisservice.getData();
			json.put("data", list);
		}
		return json.toJSONString();
	}

	/**
	 * 获取号源信息
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
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/gethisorderinfo", method = RequestMethod.GET)
	@ResponseBody
	public String getHisOrderInfo(@RequestParam("beginDate") String beginDate, @RequestParam("endDate") String endDate,
			@RequestParam("deptCode") String deptCode, @RequestParam("doctorCode") String doctorCode) {
		JsonResult jsonResult = new JsonResult();
		List<OrderSourceReturnBean> list = new ArrayList<OrderSourceReturnBean>();

		BeanService bshisservice = null;
		try {
			bshisservice = appointedService.appointmentRegOrderSourceQuery(beginDate, endDate,
					deptCode,null, doctorCode);
		} catch (Exception e) {
			logger.error("其它异常=>", e);
			jsonResult.setCode("200");
			jsonResult.setMsg("success");
			jsonResult.setContent(null);
			return FastJsonUtil.toJSONString(jsonResult);
		}
		list = (List<OrderSourceReturnBean>) bshisservice.getData();

		Map<String, Object> data = new HashMap<>();
		data.put("data", list);
		data.put("rows", list.size());
		
		jsonResult.setCode("200");
		jsonResult.setMsg("success");
		jsonResult.setContent(data);

		return FastJsonUtil.toJSONString(jsonResult);
	}


}
