package com.bsoft.domain;

/**
 * 退款成功后通知数据库
 * @author admin
 *
 */
public class NotificationRefundBean {
	
	private String  hisOrdNum;
	private String  payAmt;
	private String   payTime;
	public String getHisOrdNum() {
		return hisOrdNum;
	}
	public void setHisOrdNum(String hisOrdNum) {
		this.hisOrdNum = hisOrdNum;
	}
	public String getPayAmt() {
		return payAmt;
	}
	public void setPayAmt(String payAmt) {
		this.payAmt = payAmt;
	}
	public String getPayTime() {
		return payTime;
	}
	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}
	@Override
	public String toString() {
		return "NotificationRefundBean [hisOrdNum=" + hisOrdNum + ", payAmt=" + payAmt + ", payTime=" + payTime + "]";
	}
	
	

}
