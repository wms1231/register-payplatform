package com.bsoft.register.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bsoft.domain.DepartmentReturnBean;
import com.bsoft.domain.DeptReturnBean;
import com.bsoft.domain.DoctorReturnBean;
import com.bsoft.domain.OrderSourceReturnBean;
import com.bsoft.domain.RegOrderDay;
import com.bsoft.domain.RegSourceResponse;
import com.bsoft.domain.Regsourcefordep;
import com.bsoft.factory.BeanService;
import com.bsoft.register.service.AppointedService;
import com.bsoft.register.service.ReturnService;
import com.bsoft.tools.DateUtils;
import com.github.pagehelper.util.StringUtil;

@Service
public class ReturnServiceImpl implements ReturnService{
	@Autowired
	private AppointedService appointedService;

	/**
	 * 重新排列组合科室信息 。 服务层返回的数据。处理后返回给前台
	 * 
	 * @param list
	 * @return
	 */
	public Map<String, List<DeptReturnBean>> getDeptInfoForWeb(List<DeptReturnBean> list) {
		Map<String, List<DeptReturnBean>> parentcodelist = new HashMap<String, List<DeptReturnBean>>();
		for (int i = 0; i < list.size(); i++) {
			DeptReturnBean deptReturnBean = list.get(i);
			// 如果父科室不为零。表示子科室
			if (!deptReturnBean.getParentDeptCode().equals("0")) {
				if (parentcodelist.keySet().contains(deptReturnBean.getParentDeptCode())) {
					parentcodelist.get(deptReturnBean.getParentDeptCode()).add(deptReturnBean);

				} else {
					List<DeptReturnBean> DeptReturnBeanlist = new ArrayList<DeptReturnBean>();
					parentcodelist.put(deptReturnBean.getParentDeptCode(), DeptReturnBeanlist);
					DeptReturnBeanlist.add(deptReturnBean);
				}

			}
			// 如果子科室不为零，表示父科室
			if (!deptReturnBean.getHasChild().equals("0")) {
				if (!parentcodelist.keySet().contains(deptReturnBean.getParentDeptCode())) {
					List<DeptReturnBean> DeptReturnBeanlist = new ArrayList<DeptReturnBean>();
					parentcodelist.put(deptReturnBean.getParentDeptCode(), DeptReturnBeanlist);
				}
			}
		}

		return parentcodelist;
	}

	/**
	 * 重组大科室信息
	 * 
	 * @param list
	 * @return
	 */
	public Map<String, List<DepartmentReturnBean>> getDeptInfo(List<DepartmentReturnBean> list) {
		Map<String, List<DepartmentReturnBean>> parentcodelist = new HashMap<String, List<DepartmentReturnBean>>();

		for (int i = 0; i < list.size(); i++) {
			DepartmentReturnBean deptReturnBean = list.get(i);

			if (parentcodelist.keySet().contains(deptReturnBean.getDeptType())) {
				parentcodelist.get(deptReturnBean.getDeptType()).add(deptReturnBean);

			} else {
				List<DepartmentReturnBean> DeptReturnBeanlist = new ArrayList<DepartmentReturnBean>();
				parentcodelist.put(deptReturnBean.getDeptType(), DeptReturnBeanlist);
				DeptReturnBeanlist.add(deptReturnBean);
			}
		}

		return parentcodelist;
	}

	/**
	 * 整合号源信息
	 * 
	 * @param list
	 * @return
	 */
	public List<RegSourceResponse> getRegSourceResponse(List<OrderSourceReturnBean> list) {
		List<RegSourceResponse> regOrderResponslist = new ArrayList<RegSourceResponse>();
		Map<String, List<OrderSourceReturnBean>> depsource = new HashMap<String, List<OrderSourceReturnBean>>();

		//搜集科室代码
		for (OrderSourceReturnBean bean : list) {
			if (depsource.keySet().contains(bean.getDeptCode())) {
				depsource.get(bean.getDeptCode()).add(bean);
			} else {
				List<OrderSourceReturnBean> orderSourceReturnBean = new ArrayList<OrderSourceReturnBean>();
				orderSourceReturnBean.add(bean);
				depsource.put(bean.getDeptCode(), orderSourceReturnBean);
			}
		}

		if (depsource.size() != 0) {
			for (Entry<String, List<OrderSourceReturnBean>> entry : depsource.entrySet()) {
				RegSourceResponse regSourceResponse = new RegSourceResponse();
				regSourceResponse.setDeptCode(entry.getValue().get(0).getDeptCode());
				regSourceResponse.setDeptName(entry.getValue().get(0).getDeptName());
				//取第一个deptType
				regSourceResponse.setDeptType(entry.getValue().get(0).getDeptType());
				regSourceResponse.setRegsourcefordep(getRegInfoForWeb(entry.getValue()));
				regOrderResponslist.add(regSourceResponse);
			}
		}
		return regOrderResponslist;

	}

	public List<Regsourcefordep> getRegInfoForWeb(List<OrderSourceReturnBean> list) {
		List<Regsourcefordep> regOrderResponslist = new ArrayList<Regsourcefordep>();
		// 将医生名下的所有号源整合
		Map<String, List<OrderSourceReturnBean>> docordersource = new HashMap<String, List<OrderSourceReturnBean>>();
		for (OrderSourceReturnBean bean : list) {
			if (docordersource.keySet().contains(bean.getDoctorCode())) {
				docordersource.get(bean.getDoctorCode()).add(bean);
			} else {
				List<OrderSourceReturnBean> orderSourceReturnBean = new ArrayList<OrderSourceReturnBean>();
				orderSourceReturnBean.add(bean);
				docordersource.put(bean.getDoctorCode(), orderSourceReturnBean);
			}
		}

		if (docordersource.size() != 0) {
			for (Entry<String, List<OrderSourceReturnBean>> entry : docordersource.entrySet()) {
				Regsourcefordep regOrderResponse = new Regsourcefordep();
				// 获取医生信息
				String docid = entry.getKey();
				if (StringUtil.isEmpty(docid)) {
					regOrderResponse.setDoctorInfo(new DoctorReturnBean());
				} else {
					BeanService service = null;
					try {
						service = appointedService.queryDoctorInfo(null, null, docid);
					} catch (Exception e) {
						e.printStackTrace();
						return regOrderResponslist;
					}
					if (service.getData().size() != 0) {
						DoctorReturnBean DoctorReturnBean = (com.bsoft.domain.DoctorReturnBean) service.getData().get(0);
						regOrderResponse.setDoctorInfo(DoctorReturnBean);
					}
				}
				Map<String, RegOrderDay> pbiNfo = new HashMap<String, RegOrderDay>();
				// 整合同一天的信息 ，并整合上午 下午信息
				for (OrderSourceReturnBean bean : entry.getValue()) {
					if (pbiNfo.keySet().contains(bean.getScheduleDate())) {
						pbiNfo.get(bean.getScheduleDate()).setDay(bean.getScheduleDate());

						// 上午
						if (bean.getTimeFlag().equals("1")) {
							pbiNfo.get(bean.getScheduleDate()).addAm(bean);
						} else {
							pbiNfo.get(bean.getScheduleDate()).addPm(bean);
						}
					} else {
						RegOrderDay regOrderDay = new RegOrderDay();
						// 上午
						pbiNfo.put(bean.getScheduleDate(), regOrderDay);
						if (bean.getTimeFlag().equals("1")) {
							regOrderDay.addAm(bean);
						} else {
							regOrderDay.addPm(bean);
						}
						regOrderDay.setDay(bean.getScheduleDate());

					}

				}

				// 获取一周日期
				List<String> week = DateUtils.getweek(new Date());
				for (String today : week) {
					if (pbiNfo.keySet().contains(today)) {
						regOrderResponse.addPbiNfo(pbiNfo.get(today));
					} else {
						RegOrderDay regOrderDay = new RegOrderDay();
						regOrderDay.setDay(today);
						regOrderResponse.addPbiNfo(regOrderDay);
					}
				}

				regOrderResponslist.add(regOrderResponse);

			}
		}

		return regOrderResponslist;

	}

}
