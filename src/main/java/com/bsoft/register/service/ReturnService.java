package com.bsoft.register.service;

import java.util.List;
import java.util.Map;

import com.bsoft.domain.DepartmentReturnBean;
import com.bsoft.domain.DeptReturnBean;
import com.bsoft.domain.OrderSourceReturnBean;
import com.bsoft.domain.RegSourceResponse;
import com.bsoft.domain.Regsourcefordep;


public interface ReturnService {

	/**
	 * 重新排列组合科室信息 。 服务层返回的数据。处理后返回给前台
	 * 
	 * @param list
	 * @return
	 */
	public Map<String, List<DeptReturnBean>> getDeptInfoForWeb(List<DeptReturnBean> list);

	/**
	 * 重组大科室信息
	 * 
	 * @param list
	 * @return
	 */
	public Map<String, List<DepartmentReturnBean>> getDeptInfo(List<DepartmentReturnBean> list);

	/**
	 * 整合号源信息
	 * 
	 * @param list
	 * @return
	 */
	public List<RegSourceResponse> getRegSourceResponse(List<OrderSourceReturnBean> list);

	public List<Regsourcefordep> getRegInfoForWeb(List<OrderSourceReturnBean> list);
	
}
