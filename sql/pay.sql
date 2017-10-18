USE [pay]
GO

/****** Object:  Table [dbo].[payrecord]    Script Date: 05/16/2017 15:18:11 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO
--交易记录表
CREATE TABLE [dbo].[payrecord](
	[id] [numeric](18, 0) IDENTITY(1,1) NOT NULL,
	[payService] [varchar](2) NULL,
	[voucherNO] [varchar](50) NULL,
	[paymoney] [numeric](18, 2) NULL,
	[payTime] [datetime] NULL,
	[hospNo] [varchar](50) NULL,
	[verifyNo] [varchar](50) NULL,
	[bankType] [numeric](4, 0) NULL,
	[bankCode] [numeric](4, 0) NULL,
	[bankNo] [varchar](50) NULL,
	[payType] [numeric](4, 0) NULL,
	[paySource] [varchar](1) NULL,
	[terminalNo] [varchar](50) NULL,
	[payNo] [varchar](50) NULL,
	[collectFeesCode] [varchar](50) NULL,
	[collectFeesName] [varchar](50) NULL,
	[patienType] [varchar](4) NULL,
	[patientId] [varchar](50) NULL,
	[Name] [varchar](50) NULL,
	[Sex] [varchar](1) NULL,
	[Idcard] [varchar](18) NULL,
	[birthday] [datetime] NULL,
	[CardType] [varchar](10) NULL,
	[cardNo] [varchar](50) NULL,
	[terminalIP] [varchar](50) NULL,
	[status] [numeric](2, 0) NULL,
 CONSTRAINT [PK__payrecor__3213E83F07020F21] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'业务类型：1挂号 2收费 3住院预交金 4住院结算' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'payrecord', @level2type=N'COLUMN',@level2name=N'payService'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'业务凭证号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'payrecord', @level2type=N'COLUMN',@level2name=N'voucherNO'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'交易时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'payrecord', @level2type=N'COLUMN',@level2name=N'payTime'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'医院流水号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'payrecord', @level2type=N'COLUMN',@level2name=N'hospNo'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'对账流水号（支付宝、微信等交易号）' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'payrecord', @level2type=N'COLUMN',@level2name=N'verifyNo'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'银行卡类型：1借记卡 2信用卡' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'payrecord', @level2type=N'COLUMN',@level2name=N'bankType'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'银行代码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'payrecord', @level2type=N'COLUMN',@level2name=N'bankCode'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'银行卡号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'payrecord', @level2type=N'COLUMN',@level2name=N'bankNo'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'支付类型：1支付宝 2微信 3银联卡 ' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'payrecord', @level2type=N'COLUMN',@level2name=N'payType'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'支付来源：1窗口 2 自助机 3App' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'payrecord', @level2type=N'COLUMN',@level2name=N'paySource'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'支付终端号 如POS01、1号窗' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'payrecord', @level2type=N'COLUMN',@level2name=N'terminalNo'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'支付账号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'payrecord', @level2type=N'COLUMN',@level2name=N'payNo'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'操作员代码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'payrecord', @level2type=N'COLUMN',@level2name=N'collectFeesCode'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'操作员姓名' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'payrecord', @level2type=N'COLUMN',@level2name=N'collectFeesName'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'病人性质' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'payrecord', @level2type=N'COLUMN',@level2name=N'patienType'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'病人ID' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'payrecord', @level2type=N'COLUMN',@level2name=N'patientId'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'姓名' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'payrecord', @level2type=N'COLUMN',@level2name=N'Name'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'性别' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'payrecord', @level2type=N'COLUMN',@level2name=N'Sex'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'身份证号码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'payrecord', @level2type=N'COLUMN',@level2name=N'Idcard'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'出生年月' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'payrecord', @level2type=N'COLUMN',@level2name=N'birthday'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'支付终端IP' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'payrecord', @level2type=N'COLUMN',@level2name=N'terminalIP'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'状态码，0初始订单、1订单完成、2、订单关闭 3、订单失败,4等待输入密码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'payrecord', @level2type=N'COLUMN',@level2name=N'status'
GO


USE [pay]
GO

/****** Object:  Table [dbo].[payTrack]    Script Date: 05/16/2017 15:18:45 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO
--交易跟踪表
CREATE TABLE [dbo].[payTrack](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[payRecordId] [numeric](18, 0) NULL,
	[step] [numeric](4, 0) NULL,
	[systemName] [varchar](50) NULL,
	[remark] [varchar](100) NULL,
	[returnMsg] [varchar](4000) NULL,
	[trackTime] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'交易记录的Id号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'payTrack', @level2type=N'COLUMN',@level2name=N'payRecordId'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'步骤1、2、3、4、5、6、7' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'payTrack', @level2type=N'COLUMN',@level2name=N'step'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'系统名称 his系统、支付宝、微信、银联等' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'payTrack', @level2type=N'COLUMN',@level2name=N'systemName'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'调用说明' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'payTrack', @level2type=N'COLUMN',@level2name=N'remark'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'返回结果' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'payTrack', @level2type=N'COLUMN',@level2name=N'returnMsg'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'交易时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'payTrack', @level2type=N'COLUMN',@level2name=N'trackTime'
GO

--支付宝账号管理表
create table aliAccount(
id numeric(18,0) primary key identity(1,1),
pid varchar(100),--商户号
appId varchar(100),--appid
private_key varchar(4000),--私钥
public_key varchar(4000),--公钥
ALIPAY_PUBLIC_KEY varchar(4000),--支付宝公钥
open_api_domain varchar(200),--支付宝请求
redirect_uri varchar(500),--授权地址
sign_type varchar(100),--加密方式
max_query_retry numeric(4,0),--当面付最大查询次数
query_duration numeric(10,0),--当面付最大查询间隔（毫秒）
max_cancel_retry numeric(4,0),--当面付最大撤销次数
cancel_duration numeric(10,0),--当面付最大撤销间隔（毫秒）
heartbeat_delay numeric(4,0),--交易保障线程第一次调度延迟
heartbeat_duration numeric(10,0),--调度延迟（秒）
CHARSET varchar(50) default('UTF-8'), --编码
FORMAT  varchar(50) default('json'),--交互格式
MappingBank varchar(50)--归属银行
)

--微信的账号信息
create table WeiXinAccount(
 id numeric(18,0) primary key identity(1,1),
 APP_ID varchar(50),--微信appid
 APP_SECRET varchar(50),--
 TOKEN varchar(50),--微信token
 MCH_ID varchar(50),--商户号
 API_KEY varchar(50),--key
 redirect_uri varchar(200),--授权uri
 MappingBank varchar(50)--对应银行
 )
 --添加微信支付回调地址
 alter table weixinAccount
add NOTIFY_URL varchar(100);
 
 --支付终端管理
create table payTerminal(
 	id numeric(18,0) primary key identity(1,1),
	[TerminalType] [varchar](1) NULL,--终端类型 1窗口 2自助机
	[TerminalNo] [varchar](100) NULL,--终端号码
	[TerminalName] [varchar](100) NULL,--终端名称
	[EnableStatus] [varchar](1) NULL,--终端启用标志 1启用 2未启用
	[aliStatus] [varchar](1) NULL,--是否支持支付宝  1支持 2不支持
	[weixinStatus] [varchar](1) NULL,--是否支持微信 1支持 2不支持
	[posStatus] [varchar](1) NULL,--是否支持pos 1支持 2不支持
	[createTime] [datetime] NULL,--新增时间 
	[lastTime] [datetime] NULL --最后一次更改时间
 )
 
create table weiXinDownloadDill
(id int primary key identity(1,1),
 PAYTIME datetime,--交易时间
 APP_ID varchar(50),--公众账号Id
 MCH_ID varchar(50),--商户号
 CHILD_MCH_ID varchar(50),--子商户号
 DEVICE_INFO varchar(50),--设备号
 TRANSACTION_ID varchar(50), --微信订单号
 OUT_TRADE_NO varchar(100),--商户订单号
 OPENID varchar(50),--用户标示
 PAY_TYPE varchar(50),--交易类型
 PAY_STATUS varchar(5),--交易状态
 PAYBANK varchar(50),--付款银行
 FEE_TYPE varchar(16),--货币类型
 TOTAL_FEE numeric(18,2),--金额
 COUPON_FEE varchar(50),--代金券或立减优惠金额
 REFUND_NO varchar(17),--微信退款单号
 OUT_REFUND_NO varchar(50),--商户退款单号
 TRADE_FEE numeric(18,2),--退款金额
 COUPON_TRADE_FEE numeric(18,2),--代金券或立减优惠退款金额
 REFUND_TYPE varchar(5),--退款类型
 REFUND_STATUS varchar(5),--退款状态
 BODYNAME varchar(100),--商品名称
 BODYDATA varchar(4000),--商品数据包
 COUNTER_FEE numeric(18,2),--手续费
 RATE varchar(50)--费率
)

alter table payTerminal
add computerName varchar(100);--计算机名

alter table payTerminal
add IP varchar(50);--计算机IP


alter table payTrack
add computerName varchar(100);--计算机名

alter table payTrack
add IP varchar(50);--计算机IP

 create table AccessToServiceLog
(
	id numeric(18,0) primary key identity(1,1),
	hospNo varchar(100),--医院订单号
	computerName varchar(100),--访问的计算机名
	IP varchar(50),--访问的IP
	ServiceName varchar(100),--访问的服务名
	collectFeesCode varchar(50),--操作员工号
	collectFeesName varchar(50),--操作员姓名
	createTime datetime,--记录日期
	msg varchar(4000)--请求参数
)
alter table AccessToServiceLog
add method varchar(10);--提交方法

alter table AccessToServiceLog
add serviceUrl varchar(50) --请求的服务名

alter table AccessToServiceLog
add organizationCode varchar(50) --请求的机构编码


alter table WeiXinAccount
add bankCode varchar(5) --银行类型 01苏州银行 02浦发银行，依次类推

alter table aliAccount
add bankCode varchar(5) --银行类型

create table ServiceManage
(id int primary key identity(1,1),
 ServiceName varchar(100),--服务名
 ServiceURL varchar(200),--访问路径
 parameterExamples varchar(4000),--示例参数
 method varchar(200)--方法 post get
 )
 
 alter table ServiceManage
 add [flag] [int]
 
alter table WeiXinAccount
add certpath varchar(100)

alter table payrecord
add refund_time datetime 

alter table payrecord
add close_time datetime 

alter table payrecord
add updatetime datetime 


create table refundRecord(
	id int primary key identity(1,1),
	refundNo varchar(100),--退款订单号
	hospNo varchar(100),--医院订单号
	refundTime datetime,--退款时间
	refund_fee numeric(18,2),--退款金额
	collectFeesName varchar(50),--操作人员
	collectfeesCode varchar(50),--操作工号
	organizationCode varchar(50),--机构代码
	computerName varchar(50),--计算机名
	ip varchar(50),--ip地址,
	out_request_no varchar(100),--退款申请号
	verifyNo varchar(100),--交易流水号
	voucherNO varchar(100),--发票号码
	payType numeric(4,0)--交易类型
)

alter table payrecord
add refundFlag int default(0) --退款标记 默认0，已发生退款更改成1

--支付宝的账单表
CREATE TABLE [dbo].[aliDownloadDill](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[trade_no] [varchar](50) NULL,
	[out_trade_no] [varchar](50) NULL,
	[ServiceType] [varchar](50) NULL,
	[subject] [varchar](100) NULL,
	[createTime] [datetime] NULL,
	[finishTime] [datetime] NULL,
	[store_id] [varchar](50) NULL,
	[store_Name] [varchar](50) NULL,
	[collectFeesName] [varchar](50) NULL,
	[terminalNo] [varchar](50) NULL,
	[buyer_logon_id] [varchar](50) NULL,
	[total_amount] [numeric](18, 2) NULL,
	[receipt_amount] [numeric](18, 2) NULL,
	[red_packet] [numeric](18, 2) NULL,
	[out_request_no] [varchar](50) NULL,
	[remark] [varchar](4000) NULL,
	[APPID] [varchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]

GO