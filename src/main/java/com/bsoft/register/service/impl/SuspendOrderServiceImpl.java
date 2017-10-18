package com.bsoft.register.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bsoft.register.service.SuspendOrderService;
import com.bsoft.support.Pager;
import com.bsoft.support.service.ICommonService;

@Service
public class SuspendOrderServiceImpl implements SuspendOrderService {
	@Autowired
	private ICommonService commonService;

	@Override
	public List<Map<String, Object>> departQuery(String sqlKey, String dataSource, Object param, String[] orders) {
		return commonService.selectList(sqlKey, dataSource, param, orders);
	}

	@Override
	public List<Map<String, Object>> doctorsQuery(String sqlKey, String dataSource, Object param, String[] orders) {
		return commonService.selectList(sqlKey, dataSource, param, orders);
	}

	@Override
	public Pager suspendOrderQuery(String sqlKey, String dataSource, Map<String, Object> param, int pageNo,
			int pageSize, String[] orders) {
		return commonService.selectListByPage(sqlKey, dataSource, param, pageNo, pageSize, orders);
	}

	@Override
	public List<Map<String, Object>> deptMutiQuery(String sqlKey, String dataSource, Object param, String[] orders) {
		return commonService.selectList(sqlKey, dataSource, param, orders);
	}

}
