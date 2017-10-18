package com.bsoft.domain;

import java.util.ArrayList;
import java.util.List;

import com.bsoft.domain.OrderSourceReturnBean;

/**
 * 某个医生一天的排版信息整合
 * 
 * @author admin
 *
 */
public class RegOrderDay {

	private String day = "";
	private List<OrderSourceReturnBean> am = new ArrayList<OrderSourceReturnBean>();
	private List<OrderSourceReturnBean> pm = new ArrayList<OrderSourceReturnBean>();
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public List<OrderSourceReturnBean> getAm() {
		return am;
	}
	public void setAm(List<OrderSourceReturnBean> am) {
		this.am = am;
	}
	public List<OrderSourceReturnBean> getPm() {
		return pm;
	}
	public void setPm(List<OrderSourceReturnBean> pm) {
		this.pm = pm;
	}
	public void addAm(OrderSourceReturnBean am) {
		this.am.add(am);
	}
	public void addPm(OrderSourceReturnBean pm) {
		this.pm.add(pm);
	}
	@Override
	public String toString() {
		return "RegOrderDay [day=" + day + ", am=" + am + ", pm=" + pm + "]";
	}



}