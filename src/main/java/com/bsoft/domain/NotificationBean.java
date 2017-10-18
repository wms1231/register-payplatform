package com.bsoft.domain;

/**
 * 支付成功通知
 * 
 * @author admin
 *
 */
public class NotificationBean {
	private String hisOrdNum;
	private String psOrdNum;
	private String payMode;
	private String payAmt;
	private String agtOrdNum;
	private String payTime;
	private String payFlag;
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
	public String getPayMode() {
		return payMode;
	}
	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}
	public String getPayAmt() {
		return payAmt;
	}
	public void setPayAmt(String payAmt) {
		this.payAmt = payAmt;
	}
	public String getAgtOrdNum() {
		return agtOrdNum;
	}
	public void setAgtOrdNum(String agtOrdNum) {
		this.agtOrdNum = agtOrdNum;
	}
	public String getPayTime() {
		return payTime;
	}
	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}
	
	public String getPayFlag() {
		return payFlag;
	}
	public void setPayFlag(String payFlag) {
		this.payFlag = payFlag;
	}
	@Override
	public String toString() {
		return "NotificationBean [hisOrdNum=" + hisOrdNum + ", psOrdNum=" + psOrdNum + ", payMode=" + payMode
				+ ", payAmt=" + payAmt + ", agtOrdNum=" + agtOrdNum + ", payTime=" + payTime + "]";
	}
	
	
}
