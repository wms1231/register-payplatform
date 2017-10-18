package com.bsoft.factory;

import java.util.ArrayList;
import java.util.List;

import com.bsoft.domain.HeadBean;

public class BeanService {
	// 用于生成产品用
	//存储head
	private HeadBean head = null;
	//存储data
	private List<?> data = null;

	private BeanService(BSHiServiceBuilder bsHiServiceBuilder) {
		super();
		head = new HeadBean();
		data = new ArrayList<Object>();
		head.setServiceCode(bsHiServiceBuilder.serviceCode);

		head.setServiceDesc(bsHiServiceBuilder.serviceDesc);
		head.setClientRunningNum(bsHiServiceBuilder.clientRunningNum);
		head.setPartnerID(bsHiServiceBuilder.partnerID);
		head.setClientType(bsHiServiceBuilder.clientType);

		head.setTerminalCode(bsHiServiceBuilder.terminalCode);
		head.setUserID(bsHiServiceBuilder.userID);
		head.setTimeStamp(bsHiServiceBuilder.timeStamp);
		head.setServicePassword(bsHiServiceBuilder.servicePassword);
		head.setServerRunningNum(bsHiServiceBuilder.serverRunningNum);

		head.setResultCode(bsHiServiceBuilder.resultCode);
		head.setResultMessage(bsHiServiceBuilder.resultMessage);

		// 为集合赋值
		data = bsHiServiceBuilder.pItems;

	}

	// 静态内部类
	public static class BSHiServiceBuilder {
		private List<Object> pItems = new ArrayList<Object>();

		// =======================公共部分=====================
		private String serviceCode;// 请求接口代码 Y String 4
		private String serviceDesc;// 请求接口描述 N String 200
		private String clientRunningNum;// 合作方交易唯一流水号 N string 200
		private String partnerID;// 合作方ID Y String 20
		private String clientType;// 合作方渠道代码 N String 20

		private String terminalCode;// 合作方终端代码 N String 20
		private String userID;// 合作方操作员 Y String 20
		private String timeStamp;// 请求时间戳 Y Datetime
		private String servicePassword;// 请求秘钥 Y String 40
		private String serverRunningNum;// HIS交易唯一流水号 N String 200

		private String resultCode;// 返回结果码(请求时为空) 0：成功 其他：失败 Y String 4
		private String resultMessage;// 返回信息(请求时为空) 成功消息或失败原因;// Y String 200

		/**
		 * 构造方法，传入head部分公共的参数进行赋值
		 * 
		 * @param serviceCode
		 * @param serviceDesc
		 * @param clientRunningNum
		 * @param partnerID
		 * @param clientType
		 * @param terminalCode
		 * @param userID
		 * @param timeStamp
		 * @param servicePassword
		 * @param serverRunningNum
		 * @param resultCode
		 * @param resultMessage
		 */
		public BSHiServiceBuilder(String serviceCode, String serviceDesc, String clientRunningNum, String partnerID,
				String clientType, String terminalCode, String userID, String timeStamp, String servicePassword,
				String serverRunningNum, String resultCode, String resultMessage) {
			super();
			this.serviceCode = serviceCode;
			this.serviceDesc = serviceDesc;
			this.clientRunningNum = clientRunningNum;
			this.partnerID = partnerID;
			this.clientType = clientType;
			this.terminalCode = terminalCode;
			this.userID = userID;
			this.timeStamp = timeStamp;
			this.servicePassword = servicePassword;
			this.serverRunningNum = serverRunningNum;
			this.resultCode = resultCode;
			this.resultMessage = resultMessage;
		}

		// 变化部分需要自己来设置
		public BSHiServiceBuilder setItem(Object obj) {
			this.pItems.add(obj);
			return this;
		}

		/**
		 * 构建真正的对象并返回
		 * 
		 * @return 构建的保险合同的对象
		 */
		public BeanService build() {
			// 可以进行一些其他步骤的处理
			return new BeanService(this);
		}

	}// BSHiServiceBuilder end

	public HeadBean getHead() {
		return head;
	}

	public void setHead(HeadBean head) {
		this.head = head;
	}

	public List<?> getData() {
		return data;
	}

	public void setData(List<Object> data) {
		this.data = data;
	}

}
