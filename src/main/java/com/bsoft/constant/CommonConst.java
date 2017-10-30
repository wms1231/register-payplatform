package com.bsoft.constant;

/**
 * 存储过程 常量定义
 * 
 * @author wms1231
 *
 */
public class CommonConst {
	// 存储过程的调用
	public static final String SYSTEM_PROCEDURE_PARAMETER = "SPK_USP.pr_TranService"; // 根节点

	public static final String SERVICE_CODE_PATIENTINDEX = "1002";
	public static final String SERVICE_CODE_PATIENTINDEX_DESC = "病人主索引查询--身份证";

	public static final String SERVICE_CODE_DEPTINFO = "0001";
	public static final String SERVICE_CODE_DEPTINFO_DESC = "查询挂号科室";

	public static final String SERVICE_CODE_DEPT = "0003";
	public static final String SERVICE_CODE_DEPT_DESC = "查询大科室信息";

	public static final String SERVICE_CODE_DOCTOR = "0004";
	public static final String SERVICE_CODE_DOCTOR_DESC = "模糊查询医生信息";

	public static final String SERVICE_CODE_DOCTORINFO = "0002";
	public static final String SERVICE_CODE_DOCTORINFO_DESC = "医生信息查询";

	public static final String SERVICE_CODE_ORDERSOURCE = "0011";
	public static final String SERVICE_CODE_ORDERSOURCE_DESC = "号源信息查询 ";

	public static final String SERVICE_CODE_ORDERSOURCE_NEW = "0018";
	public static final String SERVICE_CODE_ORDERSOURCE_NEW_DESC = "大科室号源信息查询 ";

	public static final String SERVICE_CODE_REGRECORD = "0014";
	public static final String SERVICE_CODE_REGRECORD_DESC = "预约查询";

	public static final String SERVICE_CODE_REGORDER = "0012";
	public static final String SERVICE_CODE_REGORDER_DESC = "挂号预约";

	public static final String SERVICE_CODE_REGORDER_CANCEL = "0013";
	public static final String SERVICE_CODE_REGORDER_CANCEL_DESC = "取消预约";

	public static final String SERVICE_CODE_GET_REGORDER = "0015";
	public static final String SERVICE_CODE_GET_REGORDERL_DESC = "预约记录";
	// 創建主索引
	public static final String SERVICE_CODE_CREATE_PATIENT = "1001";
	public static final String SERVICE_CODE_CREATE_PATIENT_DESC = "创建主索引";

	public static final String SERVICE_CODE_NOTIFICATION = "0016";
	public static final String SERVICE_CODE_NOTIFICATION_DESC = "支付通知";

	public static final String SERVICE_CODE_REFUNDNOTIFICATION = "0017";
	public static final String SERVICE_CODE_REFUNDNOTIFICATION_DESC = "退款通知";
	// clientType
	public static final String SERVICE_PARTNER_ID = "YD12580";
	public static final String SERVICE_APP_PARTNER_ID = "1";

	public static final String SERVICE_USER_ID = "YD12580";
	public static final String SERVICE_APP_USER_ID = "1";

	public static final String SERVICE_SERVICE_PASSWORD = "xyz";

	// 登录相关常量
	public static final int LOGIN_SUCCESS_RETURN_CODE = 200;// 登录成功返回code
	public static final String LOGIN_SUCCESS_RETURN_MESSAGE = "success";// 登录成功返回信息

	public static final int LOGIN_FAIL_RETURN_CODE = 201;// 登录失败返回code
	public static final String LOGIN_FAIL_RETURN_MESSAGE = "error";// 登录失败返回信息

	// 预约挂号app接口相关参数
	public static final int APP_REQUEST_SUCCESS_CODE = 200;// 200成功
	public static final String APP_REQUEST_SUCCESS_CODE_DESC = "成功";// 200成功

	public static final int APP_REQUEST_DECRYPT_FAIL_CODE = 201;// 201非法请求（解密失败）
	public static final String APP_REQUEST_DECRYPT_FAIL_CODE_DESC = "非法请求（解密失败）";// 201

	public static final int APP_REQUEST_PARAM_ERROR_CODE = 203;// 203参数错误
	public static final String APP_REQUEST_PARAM_ERROR_CODE_DESC = "参数错误";// 203参数错误

	public static final int APP_REQUEST_ERROR_CODE = 204;// 其它错误
	public static final String APP_REQUEST_ERROR_CODE_DESC = "其它错误";// 其它错误

	public static final int APP_SERVICE_ERROR_CODE = 205;
	public static final String APP_SERVICE_ERROR_CODE_DESC = "业务处理失败";// 其它错误

	// ====================================12580支付账号===========================================

	public final static String PAY_COMPUTER_NAME = "USER-PC";
	public final static String PAY_ORGANIZATION_CODE = "466002630";
	public final static String PAY_IP = "172.20.40.131";

	// ==================================app
	// 支付相关配置参数=========================================

	public final static String APP_ORGANIZATION_CODE = "466002630";
	public final static String APP_COMPUTER_NAME = "BSAPP";
	public final static String APP_IP = "BSAPP";

	// ==================================专门针对微信公众号============================================

	public final static String WXPUB_ORGANIZATION_CODE = "466002630";
	public final static String WXPUB_COMPUTER_NAME = "WXPUB";
	public final static String WXPUB_IP = "WXPUB";

	// ==================================专门针对官方的微信账号=========================================
	public final static String WEB_ORGANIZATION_CODE = "466002630";
	public final static String WEB_COMPUTER_NAME = "BSWEB";
	public final static String WEB_IP = "BSWEB";

	// =========================================source常量=====================================================
	public final static String HCN_SOURCE_iOS = "HCN_patient_iOS";
	public final static String HCN_SOURCE_ANDROID = "HCN_patient_android";
	public final static String HCN_SOURCE_WEB = "HCN_patient_WEB";
	public final static String HCN_SOURCE_WX = "HCN_patient_WX";

	// 退款url
	public final static String REFUND_PAY_URL = "http://172.20.16.250:80/rest/pay/refund";
	// 其它渠道支付数据同步接口
	public final static String OTHER_PAY_URL = "http://172.20.16.250:80/rest/pay/otherPay";
	// 全局调用存储过程sqlKey
	public static final String SYSTEM_PROCEDURE_SQLKEY = "callpro.order";
	// =======================通知次数====================================
	public static final int NOTIFICATION_12580_TIMES = 5;

	// 默认成功和失败
	public static final String DEFAULT_SUCCESS_CODE = "0";
	
	public static final String DEFAULT_FAIL_CODE = "-1";

}
