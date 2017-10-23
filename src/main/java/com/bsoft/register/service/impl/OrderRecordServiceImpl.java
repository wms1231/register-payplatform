package com.bsoft.register.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bsoft.constant.CommonConstant;
import com.bsoft.exception.WebException;
import com.bsoft.register.service.OrderRecordService;
import com.bsoft.support.Pager;
import com.bsoft.support.service.ICommonService;
import com.bsoft.tools.CharacterEncodeUtil;
import com.bsoft.tools.ExpExcelUtil;

/**
 * 查询12580预约记录
 * 
 * @author wms1231
 *
 */
@Service
public class OrderRecordServiceImpl implements OrderRecordService {
	@Autowired
	private ICommonService commonService;

	@Override
	public void download(HttpServletRequest request, HttpServletResponse response) {
		List<String> titleList = new ArrayList<>();
		titleList.add("name");
		titleList.add("age");

		List<List<String>> rowList = new ArrayList<List<String>>();

		for (int i = 0; i < 2000; i++) {
			List<String> itemRowList = new ArrayList<>();
			itemRowList.add("小莫" + i);
			itemRowList.add(i + "");
			rowList.add(itemRowList);
		}

		/*
		 * @param headStr 表格头
		 * 
		 * @param sheetStr 标题
		 * 
		 * @param titleList 标题集合
		 * 
		 * @param rowList 内容集合
		 */
		HSSFWorkbook workbook = ExpExcelUtil.getWorkbook("wms", "wms", titleList, rowList);
		ExpExcelUtil.exportExcel(response, "hello.xls", workbook);
	}

	@Override
	public void download(HttpServletResponse response, String register, String beginTime, String endTime,
			String orderStatus, Map<String, Object> param, String sqlKey, String[] orders) {
		
		List<String> headList = Arrays.asList("订单号", "手机号", "患者姓名", "身份证号", "预约来源", "预约状态", "专家姓名", "预约科室", "下单日期",
				"预约日期", "操作员工号", "操作员姓名");

		List<Map<String, Object>> orderRecordQuery = orderRecordQuery(register, beginTime, endTime, orderStatus, param,
				sqlKey, orders);
		
		orderExport(orderRecordQuery, headList, response);
	}

	@Override
	public void orderExport(List<Map<String, Object>> orderRecordQueryList,List<String> headList,HttpServletResponse response) {
		List<List<String>> bodyList = new ArrayList<List<String>>();
		for (Map<String, Object> map : orderRecordQueryList) {
			List<String> itemRowList = new ArrayList<>();
			itemRowList.add(MapUtils.getString(map, "HOSPNO", ""));
			itemRowList.add(MapUtils.getString(map, "LXDH", ""));
			itemRowList.add(CharacterEncodeUtil.returnEncode(MapUtils.getString(map, "BRMC", "")));
			itemRowList.add(MapUtils.getString(map, "SFZH", ""));
			itemRowList.add(MapUtils.getString(map, "YYQD", ""));
			itemRowList.add(MapUtils.getString(map, "YYZT", ""));
			itemRowList.add(CharacterEncodeUtil.returnEncode(MapUtils.getString(map, "YSMC", "")));
			itemRowList.add(CharacterEncodeUtil.returnEncode(MapUtils.getString(map, "KSMC", "")));
			itemRowList.add(MapUtils.getString(map, "GHRQ", ""));
			itemRowList.add(MapUtils.getString(map, "YYRQ", ""));
			itemRowList.add(MapUtils.getString(map, "CZGH", ""));
			itemRowList.add(MapUtils.getString(map, "CZMC", ""));
			bodyList.add(itemRowList);
		}
		// 创建工作簿
		HSSFWorkbook workbook = ExpExcelUtil.getWorkbook(null, "report", headList, bodyList);
		ExpExcelUtil.exportExcel(response, "yy_" + new Date().getTime() + "_report.xls", workbook);
	}

	@Override
	public List<Map<String, Object>> orderRecordQuery(String register, String beginTime, String endTime,
			String orderStatus, Map<String, Object> param, String sqlKey, String[] orders) {
		valid(register, orders, beginTime, endTime, orderStatus);
		return commonService.selectList(sqlKey, null, param, orders);
	}

	@Override
	public Pager orderRecordQueryWithPage(String register, String beginTime, String endTime, String orderStatus,
			Map<String, Object> param, Integer pageNo, Integer pageSize, String sqlKey, String[] orders) {
		valid(register, orders, beginTime, endTime, orderStatus);
		return commonService.selectListByPage(sqlKey, null, param, pageNo, pageSize, orders);
	}

	@Override
	public void valid(String register, String[] orders, String beginTime, String endTime, String orderStatus) {
		// register 校验用户是否存在 暂时不加

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

		if (StringUtils.isNotBlank(orderStatus)) {
			List<String> asList = Arrays.asList("1", "2", "3", "4", "5");
			if (!asList.contains(orderStatus)) {
				throw new WebException(CommonConstant.DEFAULT_FAIL_CODE, "orderStatus状态只能为1,2,3,4,5");
			}
		}

	}

}
