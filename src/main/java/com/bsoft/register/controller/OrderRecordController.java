package com.bsoft.register.controller;

import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bsoft.constant.CommonConstant;
import com.bsoft.exception.WebException;
import com.bsoft.register.service.OrderRecordService;
import com.bsoft.support.PageResult;
import com.bsoft.support.Pager;
import com.bsoft.support.utils.PageResultUtil;
import com.bsoft.tools.CharacterEncodeUtil;
import com.bsoft.tools.DateUtils;
import com.bsoft.tools.FastJsonUtil;
import com.bsoft.tools.RequestDataUtil;

/**
 * 12580预约预约下单报表
 * 
 * @author wms1231
 *
 */
@Controller
@RequestMapping("/record")
public class OrderRecordController {
	@Autowired
	private OrderRecordService orderRecordService;
	private static Logger logger = Logger.getLogger(OrderRecordController.class);
	private final static String NULL_VALUE = "";

	@RequestMapping(value = "/download", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public void download(HttpServletRequest request, HttpServletResponse response) {
		orderRecordService.download(request, response);
	}

	@RequestMapping(value = "/reportDownload", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public void reportDownload(@RequestParam(value = "register", required = false) String register,
			@RequestParam(value = "beginTime", required = true) String beginTime,
			@RequestParam(value = "endTime", required = true) String endTime,
			@RequestParam(value = "orderStatus", required = false) String orderStatus,
			@RequestParam(value = "orders", required = false) String[] orders, HttpServletResponse response) {

		// 参数设置
		String sqlKey = "record.findOrderRecord";
		Map<String, Object> param = RequestDataUtil.getMapByInputParam(
				Arrays.asList("register", "beginTime", "endTime", "orderStatus"),
				Arrays.asList(register, DateUtils.getDefaultQueryTime(beginTime, true),
						DateUtils.getDefaultQueryTime(endTime, false), orderStatus));

		orderRecordService.download(response, register, beginTime, endTime, orderStatus, param, sqlKey, orders);
	}

	@RequestMapping(value = "/orderRecordQuery", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String orderRecordQuery(@RequestParam(value = "register", required = false) String register,
			@RequestParam(value = "beginTime", required = true) String beginTime,
			@RequestParam(value = "endTime", required = true) String endTime,
			@RequestParam(value = "orderStatus", required = false) String orderStatus,
			@RequestParam(value = "pageNo", required = true) Integer pageNo,
			@RequestParam(value = "pageSize", required = false, defaultValue = "1") Integer pageSize,
			@RequestParam(value = "orders", required = false) String[] orders) {

		String returnMsg = NULL_VALUE;
		String sqlKey = "record.findOrderRecord";

		Map<String, Object> param = RequestDataUtil.getMapByInputParam(
				Arrays.asList("register", "beginTime", "endTime", "orderStatus"),
				Arrays.asList(register, DateUtils.getDefaultQueryTime(beginTime, true),
						DateUtils.getDefaultQueryTime(endTime, false), orderStatus));

		Pager page = null;
		try {
			page = orderRecordService.orderRecordQueryWithPage(register, beginTime, endTime, orderStatus, param, pageNo,
					pageSize, sqlKey, orders);
		} catch (Exception e) {
			throw new WebException(CommonConstant.DEFAULT_FAIL_CODE, e.getMessage());
		}

		PageResult pageResult = PageResultUtil.getSuccessPageResult(page.getRowCount(), page.getPageSize(), pageNo,
				page.getResult(), "success");

		returnMsg = CharacterEncodeUtil.returnEncode(FastJsonUtil.toJSONString(pageResult));
		return returnMsg;
	}

	@ExceptionHandler(value = { Exception.class })
	@ResponseBody
	public String exceptionHander(Exception ex, HttpServletRequest request) {
		logger.error("请求时间=>" + DateUtils.getCurrentDate_YYYYMMDDHHMMSS_CN() + " 调用发生异常,请求path=>"
				+ request.getPathInfo() + "请求参数=>" + FastJsonUtil.toJSONString(request.getParameterMap()) + " 错误信息=>"
				+ ex.getMessage());
		if (ex instanceof WebException) {
			WebException e = (WebException) ex;
			return FastJsonUtil
					.toJSONString(PageResultUtil.getFailPageResult(e.getErrorCode(), 0L, 0, 0, null, e.getMessage()));
		}
		return FastJsonUtil.toJSONString(PageResultUtil.getFailPageResult(0L, 0, 0, null, ex.getMessage()));
	}

}
