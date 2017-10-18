package com.bsoft.domain;

/**
 * 挂号请求bean 
 * @author admin
 *
 */
public class RegOrderBean {
	private String hisOrdNum;
	private String psOrdNum;
	private String patIndex;
	private String phonenum;
	//预约来源
	private String regChannel;
	
	public String getRegChannel() {
		return regChannel;
	}

	public void setRegChannel(String regChannel) {
		this.regChannel = regChannel;
	}

	public String getHisOrdNum() {
		return hisOrdNum;
	}

	public void setHisOrdNum(String hisOrdNum) {
		this.hisOrdNum = hisOrdNum;
	}

	public String getPsOrdNum() {
		return psOrdNum;
	}

	public void setPsOrdNum(String psOrdNum) {
		this.psOrdNum = psOrdNum;
	}

	public String getPatIndex() {
		return patIndex;
	}

	public void setPatIndex(String patIndex) {
		this.patIndex = patIndex;
	}

	public String getPhonenum() {
		return phonenum;
	}

	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}

}
