package com.bsoft.domain;

public class OrderCancel {
	private String hisOrdNum;
	private String psOrdNum;
	private String patIndex;
	private String srialNum;
	private String cancelReason;

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

	public String getSrialNum() {
		return srialNum;
	}

	public void setSrialNum(String srialNum) {
		this.srialNum = srialNum;
	}

	public String getCancelReason() {
		return cancelReason;
	}

	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}

}
