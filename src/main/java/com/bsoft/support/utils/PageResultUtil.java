package com.bsoft.support.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.bsoft.constant.CommonConstant;
import com.bsoft.support.PageResult;

public class PageResultUtil {

	public static PageResult getSuccessPageResult(Long rowCount, Integer pageSize, Integer pageNo,
			List<Map<String, Object>> list, String message) {
		PageResult pageResult = new PageResult();
		pageResult.setRowCount(rowCount);
		pageResult.setPageSize(pageSize);
		pageResult.setList(list);
		pageResult.setPageNo(pageNo);
		pageResult.setCode(CommonConstant.DEFAULT_SUCCESS_CODE);
		pageResult.setMessage(message);
		return pageResult;
	}

	public static PageResult getFailPageResult(Long rowCount, Integer pageSize, Integer pageNo,
			List<Map<String, Object>> list, String message) {
		PageResult pageResult = new PageResult();
		pageResult.setRowCount(rowCount);
		pageResult.setPageSize(pageSize);
		pageResult.setList(new ArrayList<Map<String, Object>>());
		pageResult.setPageNo(pageNo);
		pageResult.setCode(CommonConstant.DEFAULT_FAIL_CODE);
		pageResult.setMessage(message);
		return pageResult;
	}

}
