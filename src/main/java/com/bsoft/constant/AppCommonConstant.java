package com.bsoft.constant;

/**
 * HCN系统参数配置
 * 
 * @author wms1231
 *
 */
public class AppCommonConstant {
	// 定义了数据字面值
	public final static String PARAM_VALUE = "SET_YYGH_JSON";// select csz from gy_xtcs where csmc='SET_YYGH_JSON';
	// 定义包名
	public final static String pack1 = "SPK_YYGH_JSON_1.";
	public final static String pack2 = "SPK_YYGH_JSON_2.";

	// 定义掌上支付包名
	public final static String hand_pack = "SPK_CHARGE_JSON.";

	// 默认存储过程的调用
	public static final String SYSTEM_PROCEDURE_NAME = pack1 + "BSHIS_usp_interface"; // 根节点

	// 定义存储过程code编码
	public static final String SYSTEM_APP_OAUTH_NAME = "jsszyy"; // app名称
	public static final String SYSTEM_APP_SECRET_KEY = "sddssfdxxccxdsdsbfdf43345ttrh" + "&"; // app秘钥

	public static final String SYSTEM_APP_USP_SET_BRDA = "1001";// 患者建档
	public static final String SYSTEM_APP_USP_CHK_YSPB = "2002";// 获取科室医生状态
	public static final String SYSTEM_APP_USP_YYHB = "2001";// 获取科室/医生排班号源列表

	public static final String SYSTEM_APP_USP_YSDM_RQ = "2003";// 预约记录查询

	public static final String SYSTEM_APP_USP_YYGH = "3001";// 提交预约记录
	public static final String SYSTEM_APP_USP_YYGH_PAY = "3002";// 预约支付
	public static final String SYSTEM_APP_USP_YYGH_CANCEL = "3003";// 取消预约
	public static final String SYSTEM_APP_USP_YYGH_REFUND = "3004";// 退款存储过程
	public static final String SYSTEM_APP_USP_YYGH_AMOUNT = "3005";// 退款存储过程
	public static final String SYSTEM_APP_USP_YYGH_AMOUNT_DETAIL = "3006";// 退款存储过程

	//掌上支付
	public static final String SYSTEM_APP_USP_GETPAYLIST = "4002";// 获取待支付列表
	public static final String SYSTEM_APP_USP_GETPAIDLIST = "4003";// 获取已支付列表
	public static final String SYSTEM_APP_USP_GETFEELIST = "4004";// 获取收费单据详细列表
	public static final String SYSTEM_APP_USP_BEFORE = "4000";// 取明细前先取GHMX的SBXH

	
	// =============================================掌上支付存储过程=====================================================

	// 掌上支付 [备用]
	public static final String SYSTEM_HAND_PROCEDURE_NAME = hand_pack + "BSHIS_usp_interface";
	
	public static final String SYSTEM_HAND_PROCEDURE_NAME_INSERT_CFXX = hand_pack + "BSHIS_usp_app_insert_cfxx";// 保存处方医技信息（临时）
	public static final String SYSTEM_HAND_PROCEDURE_NAME_SAVE_BILLCHARGE_PRE = hand_pack+"bshis_app_saveBillCharge_pre";//获取费用预结算金额
	public static final String SYSTEM_HAND_PROCEDURE_NAME_SAVE_BILLCHARGE = hand_pack + "BSHIS_app_saveBillCharge";//确认费用结算处理

	// 掌上支付特别存储过程[过时]
	/*
       public static final String SYSTEM_PROCEDURE_NAME_INSERT_CFXX = pack1 + "BSHIS_usp_app_insert_cfxx";// 保存处方医技信息（临时）
	   public static final String SYSTEM_PROCEDURE_NAME_SAVE_BILLCHARGE_PRE = pack1 + "bshis_app_saveBillCharge_pre";// 保存处方医技信息（临时）
	   public static final String SYSTEM_PROCEDURE_NAME_SAVE_BILLCHARGE = pack1 + "BSHIS_app_saveBillCharge";// 保存处方医技信息（临时）
    */
}
