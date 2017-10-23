package com.bsoft.register.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bsoft.constant.CommonConstant;
import com.bsoft.exception.WebException;
import com.bsoft.register.service.OrderRecordService;
import com.bsoft.support.Pager;
import com.bsoft.support.service.ICommonService;
import com.bsoft.tools.ExpExcelUtil;
import com.ctc.wstx.util.StringUtil;

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
		 * * @param headStr 表格头
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
	public Pager orderRecordQuery(String register, String beginTime, String endTime, String orderStatus,
			Map<String, Object> param, Integer pageNo, Integer pageSize, String sqlKey, String[] orders) {
		valid(orders, beginTime, endTime, orderStatus);
		return commonService.selectListByPage(sqlKey, null, param, pageNo, pageSize, orders);
	}

	@Override
	public void valid(String[] orders, String beginTime, String endTime, String orderStatus) {
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
