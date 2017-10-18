package com.bsoft.domain;

/**
 *支付成功通知  存储过程的返回值
 * @author admin
 *
 */
public class NotificationReturnBean {
	private String serialNum;
	private String visitPosition;
	private String visitDesc;
	public String getSerialNum() {
		return serialNum;
	}
	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}
	public String getVisitPosition() {
		return visitPosition;
	}
	public void setVisitPosition(String visitPosition) {
		this.visitPosition = visitPosition;
	}
	public String getVisitDesc() {
		return visitDesc;
	}
	public void setVisitDesc(String visitDesc) {
		this.visitDesc = visitDesc;
	}
	@Override
	public String toString() {
		return "NotificationReturnBean [serialNum=" + serialNum + ", visitPosition=" + visitPosition + ", visitDesc="
				+ visitDesc + "]";
	}
	
	

}
