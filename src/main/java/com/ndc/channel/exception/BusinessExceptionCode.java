package com.ndc.channel.exception;

import java.util.HashMap;

/**
 * 异常编码定义
 *
 * @author chen
 */
public class BusinessExceptionCode {

	public static final String CODE = "code";
	public static final String MESSAGE = "message";
    // ---------------------  系统级别 -------------------
	public static final String SUCCESS_CODE = "0000";

	/**
	 * 数据重复处理
	 */
	public static final String REPETITIVE_DATA_PROCESSING = "0001";
	/**
	 * 系统错误
	 */
	public static final String SYSTEM_ERROR = "9999";

	/**
	 * 没有权限
	 */
	public static final String ROLE_CHANGED = "8007";
	
	/**
	 * 服务调用超时
	 */
	public static final String SYSTEM_SERVICE_TIMEOUT = "9998";

	/**
	 * 渠道配置错误
	 */
	public static final String CHANNEL_ACCOUNT_ERROR = "9997";

	/**
	 * 配置已存在
	 */
	public static final String CONFIG_IS_EXIST = "1036";

	/**
	 * 发送mq消息异常
	 * @author dingyj
	 * @Date 2019-01-03 10:44
	 */
	public static final String SEND_MQ_MSG_ERROR = "9100";

	public static final String MQ_MSG_NEED_RECONSUMER = "9101";

	//redis连接池耗尽
	public static final String REDIS_POOL_EXHAUST = "9102";
	/*************************************  不提示的code ,*********************************************************/
	public static final String NO_PROMPT_ERROR = "9997";

	/**
	 * 短信发送错误
	 */
	public static final String SEND_SMS_ERROR = "9995";


	/**********业务提示************************************************************************************/
	/**
	 * 企业用户登陆失败，企业号、账号或密码错误
	 */
	public static final String LOGIN_FAIL_CORP_WEB = "1001";
	
	/**
	 * 登陆失败，账号被锁定
	 */
	public static final String LOGIN_FAIL_ACCOUNT_LOCK = "1002";

	/**
	 * 账号未开通
	 */
	public static final String LOGIN_FAIL_ACCOUNT_NOT_OPEN = "1003";
	
	/**
	 * 账号已停用
	 */
	public static final String LOGIN_FAIL_ACCOUNT_DELETE = "1004";

	/**
	 * 密码不可修改为默认密码
	 */
	public static final String ACCOUNT_PASSWORD_INVALID = "1083";

	/**
	 * 验证码错误
	 */
	public static final String VERIFY_CODE_ERROR = "1005";
	
	/**
	 * tmc用户登录失败，tmc账号，员工账号或密码错误
	 */
	public static final String LOGIN_FAIL_TMC_WEB = "1006";
	
	/**
	 * 该人员信息已存在
	 */
	public static final String USER_PASSENGER_REL_EXISTS = "1007";
	/**
	 * 联系人已存在
	 */
	public static final String USER_CONTACT_EXISTS = "1008";

	/**
	 * 部门删除失败，该部门下员工不为空
	 */
	public static final String DEPARTMENT_DELETE_ERROR = "1009";

	public static final String REFUND_DELETE_ERROR = "1125";

	public static final String REFUND_PROCESSING_ERROR = "1126";

	/**
	 *成本中心删除失败，该成本中心下员工不为空
	 */
	public static final String COSTCENTER_DELETE_ERROR = "1010";
	
	/**
	 *差旅政策删除失败，该项差旅政策被使用
	 */
	public static final String POLICY_DELETE_ERROR = "1011";

	/**
	 * 没有找到用户差旅政策
	 */
	public static final String USER_POLICY_NOT_FOUND = "1012";
	
	/**
	 * 查询参数错误
	 */
	public static final String SELECT_PARAMS_ERROR = "1013";


	/**
	 * 订单重复情况
	 */
	public static final String ORDER_REPEAT = "1014";
	
	/**
	 * 机票票价有变动，请重新确认订单
	 */
	public static final String TICKET_PRICE_NOT_EQUAL = "1015";

	/**
	 * 保险价格不一致
	 */
	public static final String INSURANCE_PRICE_NOT_EQUAL = "1016";

	/**
	 * 创建PNR失败，机票已售完
	 */
	public static final String FLIGHT_TICKET_NOT_PNR = "1017";
	
	/**
	 * 代理人配置缺失
	 */
	public static final String TMC_OFFICEID_NOT_CONFIG = "1018";

	/**
	 * 退票或改签只能操作一次
	 */
	public static final String CHANGE_ONLY_ONE = "1021";

	/**
	 * 退票改签校验
	 */
	public static final String TICKET_IS_NOT_ISSUED = "1022";

	/**
	 * 查看订单列表为空
	 */
	public static final String ORDER_LIST_IS_EMPTY = "1023";

	/**
	 * 修改密码失败
	 */
	public static final String MODIFY_PASSWORD_ERROR = "1024";
	/**
	 * 修改密码时，原密码不正确
	 */
	public static final String MODIFY_PASSWORD_CHECK_FAIL = "1025";
	
	/**
	 * 手机号未注册
	 */
	public static final String PHONE_NOT_REGIST = "1026";

	/**
	 * 改签费有变动
	 */
	public static final String CHANGE_FEE_IS_ERROR = "1027";
	
	/**
	 * 查看订单详情失败
	 */
	public static final String ORDER_INFO_IS_ERROR = "1028";
	
	/**
	 * 重置密码失败
	 */
	public static final String RETRIEVE_PASSWORD_ERROR = "1029";
	
	/**
	 * 企业支付额度超出范围，请联系代理人
	 */
	public static final String CORP_PAY_LIMIT_OVER = "1030";
	
	/**
	 * 机票价格异常
	 */
	public static final String TICKET_PRICE_ERROR = "1031";

	/**
	 * 管理员账号不能删除
	 */
	public static final String REFUSE_DELETE_TMC_USER = "1032";

	/**
	 * 该人员为您公司的员工，请在员工信息中查询
	 */
	public static final String USER_PASSENGER_EMP_EXISTS = "1033";

	/**
	 * 公司名称已存在
	 */
	public static final String CORP_NAME_IS_EXIST = "1034";

	/**
	 * 公司编码已存在
	 */
	public static final String CORP_CODE_IS_EXIST = "1035";

	/**
	 * 只能取消待支付状态的订单
	 */
	public static final String FLIGHT_ORDER_CANNOT_CANCEL = "1036";

	/**
	 * 企业大客户编码已存在
	 */
	public static final String VIP_CODE_IS_EXIST = "1037";

	/**
	 * 员工手机号已存在
	 */
	public static final String USER_PHONE_IS_EXIST = "1038";

	/**
	 * 部门名称已存在
	 */
	public static final String CORP_DEPARTMENT_IS_EXIST = "1039";
	
	/**
	 * 成本中心已存在
	 */
	public static final String CORP_COST_CENTER_IS_EXIST = "1040";
	
	/**
	 * 差旅政策已存在
	 */
	public static final String CORP_POLICY_IS_EXIST = "1041";
	
	/**
	 * 成本中心只剩一个时不能删除
	 */
	public static final String COSTCENTER_IS_ONLY_ONE = "1042";

	/**
	 * 大客户编码被占用
	 */
	public static final String CORP_VIP_IS_OCCUPY = "1043";

	/**
	 * 管理员账号已存在
	 */
	public static final String CORP_ADMIN_IS_EXIST = "1044";

	/**
	 * 企业服务费已存在
	 */
	public static final String CORP_SERVICE_FEE_IS_EXISTS = "1045";

	/**
	 * 机票票号错误
	 */
	public static final String TICKET_NUMBER_IS_ERROR = "1046";

	/**
	 * 部门删除失败，包含下级部门
	 */
	public static final String DEPARTMENT_EXIST_CHILD = "1047";
	
	/**
	 * 部门只剩一个，不能删除
	 */
	public static final String DEPARTMENT_IS_ONLY_ONE = "1048";

	/**
	 * 差旅政策剩一个时不能删除
	 */
	public static final String POLICY_IS_ONLY_ONE = "1049";

	/**
	 * 大客户编码下有折扣信息时不能删除
	 */
	public static final String VIP_DISCOUNT_IS_NOT_EMPTY = "1050";

	/**
	 * 大客户折扣信息已存在
	 */
	public static final String VIP_DISCOUNT_IS_EXIST = "1051";
	
	/**
	 * 连接超时
	 */
	public static final String TIMEOUT_WAITING_FOR_CONNECTION = "1052";

	/**
	 * 该账号不是管理员
	 */
	public static final String ACCOUNT_IS_NOT_MANAGER = "1053";
	
	/**
	 * 登陆失败，账号或密码错误
	 */
	public static final String LOGIN_FAIL_EMP_WEB = "1054";

	/**
	 * 机票特价不存在
	 */
	public static final String FLIGHT_TICKET_NOT_NET_PRICE = "1055";
	
	/**
	 * 创建pnr失败
	 */
	public static final String CREATE_PNR_ERROR = "1056";
	
	/**
	 * 未找到日期内的机票信息
	 */
	public static final String OPEN_TICKET_NOT_FOUND = "1057";
	
	/**
	 * 创建订单失败
	 */
	public static final String CREATE_ORDER_FAIL = "1058";
	
	/**
	 * 机票查询失败
	 */
	public static final String FLIGHT_TICKET_SEARCH_FAIL = "1059";

	/**
	 * 航空乘机人姓名校验失败
	 */
	public static final String FLIGHT_PASSENGER_NAME_CHECK_FAIL = "1060";
	
	/**
	 * 获取经纬度失败
	 */
	public static final String ADDRESS_TO_LATANDLNG_ERROR = "1061";
	
	/**
	 * tmc资金账户不存在
	 */
	public static final String TMC_CATIAL_ACCOUNT_NO_EXIST = "1062";
	
	/**
	 * tmc账户余额不足
	 */
	public static final String TMC_MONEY_BALANCE_LESS = "1063";
	
	/**
	 * 订单不存在
	 */
	public static final String ORDER_NOT_EXIST = "1064";
	
	/**
	 * 订单状态不正确
	 */
	public static final String ORDER_STATUS_ERROR = "1065";
	
	/**
	 * 出票重复点击
	 */
	public static final String TICKET_REPEAT = "1066";
	
	/**
	 * 读取pnr失败
	 */
	public static final String READ_PNR_ERROR = "1067";
	
	/**
	 * 封口失败
	 */
	public static final String ORDER_PAT_ERROR = "1068";
	
	/**
	 * pnr校验失败
	 */
	public static final String PNR_CHECK_ERROR = "1069";
	public static final String PNR_CHECK_ERROR_NO = "10691";
	public static final String PNR_CHECK_ERROR_RR = "10692";

	/**
	 * 查询乘机人失败
	 */
	public static final String SEARCH_PASSENGE_ERROR = "1070";
	
	/**
	 * 更新外采订单失败
	 */
	public static final String UPDATE_CHANNEL_ORDER_ERROR = "1071";
	
	/**
	 * 取消外采订单失败
	 */
	public static final String ORDER_CANCEL_ERROR = "1072";

	/**
	 * 航班缓存添加失败
	 */
	public static final String FLIGHT_CACHE_ADD_ERROR = "1073";


	//查询订单保险信息失败
	public static final String SEARCH_ORDER_INSURANCE_ERROR = "1074";

	//校验订单保险信息失败
	public static final String CHECK_ORDER_INSURANCE_STATUS_FAIL = "1075";
	//校验订单保险金额失败
	public static final String CHECK_ORDER_INSURANCE_MONEY_FAIL = "1076";
	//查询服务费失败
	public static final String SEARCH_ORDER_SERVICE_FEE_ERROR = "1077";
	//校验服务费退费金额失败
	public static final String CHECK_ORDER_SERVICE_FEE_MONEY_ERROR = "1078";
	//校验服务费是否可退失败
	public static final String CHECK_ORDER_SERVICE_FEE_STATIS_ERROR = "1079";
	// 包机产品导入失败
	public static final String IMPORT_CHARTER_ORDER_ERRPOR = "1080";
	//删除包机对账结算失败
	public static final String DELETE_CHARTER_ORDER_FAIL = "1081";
	//查询包机对账结算失败
	public static final String SEARCH_CHARTER_ORDER_FAIL = "1082";
	//数据格式不正确
	public static final String DATA_FORMAT_ERROR = "1084";
	//不存在默认的office
	public static final String DEFAULT_OFFICE_NOTEXIST_ERROR = "1085";
	//当前配置号已存在所有者
	public static final String ETERM_EXIST_OWNER = "1086";
	//当前配置号不存在所有者
	public static final String ETERM_NOTEXIST_OWNER = "1087";
	

	//未找到充值单
	public static final String RECHARGE_NOT_FOUND = "1088";

	public static final String FLIGHT_TICKET_CHANGE_ERROR = "1089";

	public static final String FLIGHT_DATE_CHECK_FAIL = "1096";

	public static final String FLIGHT_FILL_SEAT_ERROR = "1097";

	public static final String FLIGHT_FILL_SEAT_FAILURE = "1098";

	//删除占位状态机票订单乘客
	public static final String FLIGHT_DEL_PASSENGER = "1099";

	/**
	 * 国际机票票价修改失败
	 */
	public static final String ORDER_PRICE_IS_ERROR = "1090";

    public static final String USER_EMAIL_IS_ERROR="1091";

	public static final String USER_EMAIL_IS_EXIST="1092";

	/**
	 *  审批人员不能删除
	 */
	public static final String USER_DELETE_ERRO="1093";

    /**
     * 审批人员不能没有审批权限
     */
    public static final String AUDITING_UPDATE_ERRO="1094";

	/**
	 * 该部门不存在审批人员
	 */
	public static final String DEPARTMENT_IS_NOT_AUDITED="1095";

	/**
	 * 改签航班变更
	 */
	public static final String FLIGHT_CHANGE="1116";

	/**
	 * 对账结算审核失败
	 */
	public static final String AUDITING_SETTLEMENT_ERROR = "1100";

	/**
	 * 写入个性化指令失败
	 */
	public static final String INPUT_INDIVIDUAL_INSTRUCTION_ERROR = "1101";

	/**
	 * 获取航段费失败
	 */
	public static final String SELECT_FLIGHT_SEGMENT_ERROR = "1102";

	/**
	 * 处理优惠政策失败
	 */
	public static final String TMC_FLIGHT_DISCOUNT_ERROR = "1103";

	/**
	 * 更新财务付款方式错误
	 */
	public static final String UPDATE_ORDER_FINANCIAL_ERROR = "1104";

	/**
	 * 保险渠道错误
	 */
	public static final String CHANNEL_INSURANCE_ERROR = "1105";

	/**
	 * cbe查询航班失败,提示给前端,重新触发查询
	 */
	public static final String SEARCH_CBE_FLIGHT_TICKET_ERROR = "1106";

	/**
	 * 政采票pat价格报错
	 */
	public static final String PAT_GP_PNR_FAIL = "1107";

	/**
	 * 订单日志记录错误
	 */
	public static final String ORDER_LOG_ERROR = "1110";

	/**
	 * 新增密码失败
	 */
	public static final String ADD_PASSWORD_ERROR = "1111";

	/**
	 * 订单更新轨迹错误
	 */
	public static final String ORDER_UPDATE_TRACK_ERROR = "1112";
	
	/**
	 * tmc渠道配额校验失败
	 */
	public static final String CHECK_TMC_CHANNEL_QUOTA_ERROR = "1120";

	/**
	 * 订单删除错误
	 */
	public static final String ORDER_DELETE_ERROR = "1113";

	/**
	 * 处理大客户号出错
	 */
	public static final String DEAL_WITH_VIPCODE_ERROR = "1114";

	/**
	 * 渠道错误
	 */
	public static final String FLIGHT_CHANNEL_ERROR = "1121";

	/**
	 * 机票异常单据处理失败
	 */
	public static final String EXCEPTION_HANDLE_FAIL = "1119";


	/**
	 *  成本中心编号相等无法修改
	 */
	public static final String CORP_COST_CENTER_NUM_IS_EXIST = "1122";
	
	/**
	 *  成本中心编号相等无法修改
	 */
	public static final String TRAVEL_APPROVAL_AUDITING_NOT_CONFIG = "1123";


	/**
	 *  成本中心编号相等无法修改
	 */
	public static final String TRAVEL_APPROVAL_AUDITING_NOT_BUSINESS_TYPE = "1124";

	/**
	 * 这个配置已经存在
	 */
	public static final String CONFIG_IS_EXISTS = "1125";

	/*************************火车票***************************************************************************/
	
	/**
	 * 19e返回错误码
	 */
	public static final String TRAIN_19E_ERROR = "2001";
	
	/**
	 * 火车票剩余数量不足
	 */
	public static final String TRAIN_TICKET_ERROR = "2002";
	
	/**
	 * 火车票订单状态不允许取消
	 */
	public static final String TRAIN_ORDER_CANCEL_ERROR = "2003";
	
	/**
	 * 火车票订单状态不允许退票
	 */
	public static final String TRAIN_TICKET_REFUND_ERROR = "2004";
	
	/**
	 * 登陆cookie失效
	 */
	public static final String LOGIN_COOKIE_INVALID = "2005";
	
	public static final String TRAIN_WORK_TIME_ERROR = "2006";

	/**
	 * 测试模式不能下订单
	 */
	public static final String TRAIN_19E_TEST = "2007";
	public static final String TRAIN_KONGTIE51_TEST = "2012";

	/**
	 * 火车票订单状态不允许改签
	 */
	public static final String TRAIN_TICKET_CHANGE_ERROR = "2008";
	
	public static final String TRAIN_TICKET_CREATE_ERROR = "2009";
	
	public static final String TRAIN_FUNCTION_SWITCH = "2010";
	
	/**
	 * 火车功能未开通
	 */
	public static final String TRAIN_NOT_OPEN = "2011";

	public final static String KONGTIE51_REFUND_FAIL  = "2013";

	/**
	 * 加价管理
	 */
	public static final String HOTEL_PREMIUM_TYPE_ERR = "2014";

	public static final String HOTEL_PREMIUM_TYPE_MUCH = "2015";

	public static final String HOTEL_PREMIUM_PRICE_ERR = "2016";

	public static final String HOTEL_PREMIUM_CITY_ERR = "2017";

	public static final String HOTEL_PREMIUM_CITY_EMPTY = "2018";

	/**
	 * 差旅政策
	 */
	public static final String HOTEL_POLICY_CITY_EMPTY = "2019";

	/**
	 * 火车再次出票
	 */
	public static final String TRAIN_TICKET_REISSUE = "2020";
	/*************************酒店***************************************************************************/
	
	/**
	 * 酒店配置缺失
	 */
	public static final String HOTEL_CONFIG_ERROR = "3001";
	
	/**
	 * 酒店验价
	 */
	public static final String HOTEL_CHECK_PRICE = "3002";
	
	/**
	 * 酒店房间库存不足
	 */
	public static final String HOTEL_INVENTORY_ERROR = "3003";
	
	/**
	 * 酒店价格变动
	 */
	public static final String HOTEL_PRICE_ERROR = "3004";
	
	/**
	 * 酒店创建失败
	 */
	public static final String HOTEL_CREATE_ERROR = "3005";
	
	/**
	 * 酒店取消失败
	 */
	public static final String HOTEL_REFUND_ERROR = "3006";
	
	public static final String HOTEL_GUARANTEE_ERROR = "3007";

	/**
	 * 酒店查询列表缓存失效
	 */
	public static final String HOTEL_SELECT_ERROR = "3008";

	/**
	 * 未开放该酒店渠道
	 */
	public static final String HOTEL_CHANNEL_ERROR = "3009";

	/**
	 * 该酒店渠道已下架
	 */
	public static final String HOTEL_CHANNEL_CLOSE = "3010";

	/**
	 * 酒店入住人姓名校验失败
	 */
	public static final String HOTEL_CUSTOMER_NAME_CHECK_FAIL = "3011";

	/**
	 * 酒店取消政策校验失败
	 */
	public static final String HOTEL_CHECK_CANCEL_PENALTY = "3012";

	/**********出票异常信息******************************************/

	/**
	 * 出票失败
	 */
	public static final String TICKET_ETDZ_ERROR = "4001";

	/**
	 * 机票价格变动，但是用户已支付，需要人工处理，多退少补
	 */
	public static final String TICKET_ETDZ_ERROR_PRICE_CHANGE = "4002";

	/**
	 * 出票时做RR失败
	 */
	public static final String TICKET_RR_ERROR = "4003";

	/**
	 * 运价失败
	 */
	public static final String TICKET_PAT_ERROR = "4004";

	/**
	 * PNR错误
	 */
	public static final String PNR_RT_ERROR = "4005";

	/**
	 * PNR解析失败
	 */
	public static final String TICKET_ETERM_PNR_ERROR = "4006";
	/**
	 * 默认配置号组已存在
	 */
	public static final String ETERM_GROUP_DEFAULT_EXIST_ERROR = "4007"; 


	/**-------------------------------------------------------阿里云 ons服务相关异常代码 -------------------------------------------------------*/
	public static final String ALIYUN_MQ_CONSUMER_ERROR = "5001";//消费消息异常
	public static final String ALIYUN_MQ_PRODUCER_ERROR = "5002";//发送消息异常


	/**--------------------------------------------------其他相关-------------------------------------------------------------------*/
	public static final String INIT_CHANNEL_CACHE_ERROR = "6001";//初始化外部渠道信息失败
	public static final String INIT_CHANNEL_ACCOUNT_CACHE_ERROR = "6001";//初始化外部渠道账号信息失败

	/*********调用参数错误****************************************************************************/

	/**
	 * 请求参数错误
	 */
	public static final String REQUEST_PARAM_ERROR = "7000";

	/**
	 * 优惠活动已结束
	 */
	public static final String EVENT_REDUCE_NOT_OPEN = "7001";
	/**
	 * 优惠价格变动
	 */
	public static final String EVENT_REDUCE_VALUE_NOT_EQUALS = "7002";

	/**
	 * 合作商不存在
	 */
	public static final String PARTNER_NOT_FOUND = "7003";

	/**
	 * 合作商未开通
	 */
	public static final String PARTNER_NOT_OPEN = "7004";

	/**
	 * 提交易图8订单失败
	 */
	public static final String SUBMIT_YITU8_ORDER_FAIL = "7005";
	
	/**
	 * 保险订单创建失败
	 */
	public static final String CREATE_INSURANCE_ORDER_FAIL = "7006";
	
	/**
	 * 保险订单创建失败
	 */
	public static final String CANCEL_INSURANCE_ORDER_FAIL = "7007";
	
	/**
	 * 保险产品获取失败
	 */
	public static final String SELECT_INSURANCE_FAIL = "7008";

	/**
	 * 校验保险投保人失败
	 */
	public static final String CHECK_INSURANCE_PERSON_ERROR = "7009";

	/**
	 * 保险改签错误
	 */
	public static final String INSURANCE_CHANGE_ERROR = "7010";

	/**
	 * 慧择保险错误
	 */
	public static final String HUIZE_INSURANCE_ERROR = "7011";

	/**
	 * 使用保险密码时发生错误
	 */
	public static final String INSURANCE_PASSWORD_ERROR = "7012";

	/**
	 * 黑屏保险错误
	 */
	public static final String BLACK_INSURANCE_ERROR = "7013";

	/**
	 * 永安保险错误
	 */
	public static final String YONGAN_INSURANCE_ERROR = "7014";

	/**
	 * 合作商参数配置错误
	 */
	public static final String PARTNER_PARAM_ERROR = "7015";

	//同一个订票员取消次数限制
	public static final String FLIGHT_CANCEL_TIMES = "FLIGHT_CANCEL_TIMES";

	/*********权限错误****************************************************************************/
	/**
	 * 鉴权失败
	 */
	public static final String AUTH_VALIDATE_ERROR = "8000";

	/**
	 * 验签失败，非法请求
	 */
	public static final String AUTH_VALIDATE_SIGN_ERROR = "8001";

	/**
	 * token校验异常
	 */
	public static final String TOKEN_VERIFY_EXCEPTION = "8003";

	/**
	 * token失效
	 */
	public static final String TOKEN_VERIFY_OVERDAY = "8004";

	public static final String REFERER_VERIFY_EXCEPTION = "80041";

	/**
	 * 没有权限
	 */
	public static final String AUTH_NO_RIGHT = "8005";

	public static final String SESSION_REFRESH_ERROR = "8006";

	/**
	 * 微信openid异常
	 */
	public static final String WECHAT_OPENID_ERROR = "8008";
	
	/**
	 * 微信openid异常
	 */
	public static final String WECHAT_AUTH_ERROR = "8009";

	/**------------------------------------------------------- 国际票-------------------------------------------------------*/
	public static final String INTL_FLIGHT_EXPIRE = "9001";//搜索结果已过期
	public static final String SEARCH_INTL_TICKET_PRICE_FAIL = "9002";//票价获取失败
	public static final String INTL_FLIGHT_PRICE_VERIFY_FAIL = "9003";//下单校验价格失败
	public static final String INTL_FLIGHT_ORDER_REPEAT = "9004";//下单校验乘客是否重复
	public static final String INTL_FLIGHT_E515 = "515";

	/**空铁无忧*/
	public final static String KONGTIE51_QUERY_ERROR  = "9010";
	public final static String KONGTIE51_TICKET_ERROR  = "9011";

	public final static String KONGTIE51_CARDTYPE_ERROR = "9009";
	/**微信支付返回错误*/
	public final static String WEIXINPAY_ERR  = "9012";
	/**
	 *
	 */
	public final static String TMC_VIRTUAL_REGIST_NOTEXIST  = "9013";

	public final static String TMC_REGIST_SWITCH_NOTEXIST  = "9014";

	public final static String CORP_IS_NOT_SELECT="9015";

	public final static String VERIFY_CODE_NULL="9016";

	public final static String CORP_CAPITAL_ACCOUNT_NO_EXIST = "9017";

	public final static String CORP_MONEY_BALANCE_LESS = "9018";

	public final static String VERIFY_TIME_LIMIT = "9019";

	public final static String VERIFY_DAT_LIMIT = "9010";

	/**
	 * 业务不支持该证件类型
	 */
	public static final String IDCARD_TYPE_ERROR = "9021";


	/**
	 * 客户维护
	 */
	// 客户维护规则管理错误
	public final static String CUSTOMER_ATTENTION_RULE_ERROR = "10001";

	// 客户维护工单管理错误
	public final static String CUSTOMER_ATTENTION_ORDER_ERROR = "10002";

	/**
	 * 出保结果解析失败
	 */
	public final static String BLACK_INS_SUBMIT_RESULT_ERROR = "10003";


	public final static String CAN_NOT_OPEN_HIGH_CABIN_CHECK = "10004";

	public final static String TRAIN_ORDER_PASSENGER_DATA_ERROR = "10005";

	public final static String OPERATION_LOCK = "10006";

	/**
	 * 保险产品已下架
	 */
	public final static String INSURANCE_IS_UNUSERFUL_ERROR = "10007";

    /**
     * 黑屏保险操作失败
     */
    public final static String BLACK_INS_ERROR = "10008";

	/**
	 * 业务结转失败
	 */
	public final static String CARRY_FORWARD_ERROR = "10009";

	/**
	 * 导出excel失败
	 */
	public final static String EXPORT_EXCEL_ERROR = "10010";

    /**
     * 订单垫付 预存确认支付重复
     */
    public final static String CONFIRM_PAY_ERROR = "10040";

    public final  static String  APPROVAL_IS_AUDITING = "10041";

	public final static String PART_ORDER_PAY = "10041";

	private static HashMap<String, String> EXCEPTION_MESSAGE;

	public final static String UPTICK_RULE_HOTEL_COUNT_ERROR = "10050";

	public final static String UPTICK_RULE_SAME_NAME_ERROR = "10051";

	public final static String UPTICK_RULE_FEE_ERROR = "10052";


	/**
	 * 基础数据
	 */
	public final static String CITY_NAME_EXISTED = "10113";
	public final static String CITY_NAME_NOT_EXISTED = "10111";
	public final static String CITY_NOT_EXISTED = "10101";
	public final static String CITY_CODE_NOT_EXISTED = "10112";
	public final static String CITY_EXISTED = "10104";
	public final static String CITY_CODE_EXISTED = "10102";
	public final static String AIRPORT_NOT_EXISTED = "10103";
	public final static String AIRPORT_NAME_EXISTED = "10105";
	public final static String AIRLINE_EXISTED = "10106";
	public final static String AIRLINE_NOT_EXISTED = "10107";
	public final static String TG_POLICY_NOT_EXISTED = "10108";
	public final static String TG_POLICY_EXISTED = "10109";
	public final static String AIRPORT_CODE_EXISTED = "10110";

	public final static String REGION_OPERATION_RECORD_NOT_EXIST = "10060";

	public final static String REGION_OPERATION_RECORD_EXIST_NO_PEND = "10061";

	public final static String FATHER_REGION_NOT_EXIST = "10062";
	public final static String ROOM_NUMBER_NOT_FOUND = "10053";
	public static final String TICKET_NOT_OPEN = "10054";

	public final static String XIECHENG_ERROR = "0";
	/**
	 * 酒店异常编码 区间 40000 -49999
	 */
	public final static String HOTEL_PRICE_CHANGE = "40001"; //酒店产品价格发生变动
	public final static String HOTEL_ROOM_DOWN = "40002"; //酒店产品房量售空
	public final static String HOTEL_VAILCODE_UNKNOW = "40003"; //未知异常
	public final static String HOTEL_ROOM_NUM_LOST = "40004"; //酒店产品房量不足
	public final static String HOTEL_ROOM_NO_CONFORM  = "40005"; //不满足预定规则


	public final static String FINANCE_SERVICE_FEE_ERROR = "20000";
	public final static String USER_INFO_GET_FAILURE = "10070";

	public final static String USER_INFO_CHECK_FAILURE = "10071";

	public final static String SAME_CHANNEL_NOT_ALLOWED = "10072";
	public final static String REGION_NOT_EXIST = "10063";

	public final static String BUSINESS_NOT_SELECT = "10100";

	public final static String AIRLINE_NOT_EXIST = "10101";//航司不存在
	public final static String AIRLINE_IS_NULL = "10102";//航司不能为空
	public final static String SEATCLASS_IS_NULL = "10103";//舱位不能为空

	public final static String UATP_PAY_FAIL = "20012";
	public final static String USER_NAME_FORMAT_ERROR = "10115";

	//航司编码已经存在
//	public final static String AIRLINE_EXISTED = "10114";

	static {

        EXCEPTION_MESSAGE = new HashMap<String, String>();
		EXCEPTION_MESSAGE.put(TMC_VIRTUAL_REGIST_NOTEXIST,"该TMC未开启散客注册服务！");
		EXCEPTION_MESSAGE.put(SEARCH_CHARTER_ORDER_FAIL, "查询包机对账结算失败！");
		EXCEPTION_MESSAGE.put(DELETE_CHARTER_ORDER_FAIL, "删除包机对账结算失败！");
		EXCEPTION_MESSAGE.put(IMPORT_CHARTER_ORDER_ERRPOR, "包机产品报表数据导入失败！");
		EXCEPTION_MESSAGE.put(SEARCH_INTL_TICKET_PRICE_FAIL, "国际票获取票价失败！");
		EXCEPTION_MESSAGE.put(INTL_FLIGHT_EXPIRE, "国际票查询已过期！");
		EXCEPTION_MESSAGE.put(INTL_FLIGHT_PRICE_VERIFY_FAIL,"国际机票验价失败!");
		EXCEPTION_MESSAGE.put(INTL_FLIGHT_ORDER_REPEAT,"国际机票下单校验，订单中乘客已有相关订单存在!");
		EXCEPTION_MESSAGE.put(SEARCH_ORDER_SERVICE_FEE_ERROR, "查询订单服务费失败！");
		EXCEPTION_MESSAGE.put(CHECK_ORDER_SERVICE_FEE_MONEY_ERROR, "订单服务费退费金额不能高于收取的服务费！");
		EXCEPTION_MESSAGE.put(CHECK_ORDER_SERVICE_FEE_STATIS_ERROR, "订单服务费已退，不可重复退费！");

		EXCEPTION_MESSAGE.put(CHECK_ORDER_INSURANCE_MONEY_FAIL, "订单保险退费金额不能高于保险原价！");
		EXCEPTION_MESSAGE.put(CHECK_ORDER_INSURANCE_STATUS_FAIL, "校验订单保险状态是否可退失败！");
		EXCEPTION_MESSAGE.put(SEARCH_ORDER_INSURANCE_ERROR, "查询订单保险信息失败！");
		EXCEPTION_MESSAGE.put(INIT_CHANNEL_ACCOUNT_CACHE_ERROR, "初始化外部渠道账号信息失败！");
		EXCEPTION_MESSAGE.put(INIT_CHANNEL_CACHE_ERROR, "初始化外部渠道信息失败！");
		EXCEPTION_MESSAGE.put(ALIYUN_MQ_PRODUCER_ERROR, "发送消息失败！");
		EXCEPTION_MESSAGE.put(ALIYUN_MQ_CONSUMER_ERROR, "消费消息失败！");
        EXCEPTION_MESSAGE.put(SYSTEM_ERROR,"网络繁忙，请稍后再试");
        EXCEPTION_MESSAGE.put(SYSTEM_SERVICE_TIMEOUT, "服务调用超时");
        
        EXCEPTION_MESSAGE.put(AUTH_VALIDATE_ERROR,"鉴权失败");
        EXCEPTION_MESSAGE.put(AUTH_VALIDATE_SIGN_ERROR,"非法请求");
        
        EXCEPTION_MESSAGE.put(TOKEN_VERIFY_OVERDAY,"登陆信息过期，请重新登陆");
        EXCEPTION_MESSAGE.put(REFERER_VERIFY_EXCEPTION,"不合规的请求");
        EXCEPTION_MESSAGE.put(TOKEN_VERIFY_EXCEPTION,"登陆信息校验失败，请重新登陆");
        
        EXCEPTION_MESSAGE.put(AUTH_NO_RIGHT,"没有权限");
        
        EXCEPTION_MESSAGE.put(REQUEST_PARAM_ERROR, "请求参数错误");
        
        EXCEPTION_MESSAGE.put(LOGIN_FAIL_CORP_WEB, "企业号、账号或密码错误");
        EXCEPTION_MESSAGE.put(LOGIN_FAIL_ACCOUNT_LOCK, "登陆失败，账号被锁定,请联系代理人解锁");
        EXCEPTION_MESSAGE.put(LOGIN_FAIL_ACCOUNT_NOT_OPEN, "账号未开通");
        EXCEPTION_MESSAGE.put(LOGIN_FAIL_ACCOUNT_DELETE, "账号已停用");
		EXCEPTION_MESSAGE.put(ACCOUNT_PASSWORD_INVALID, "不可修改为系统默认密码");
        EXCEPTION_MESSAGE.put(VERIFY_CODE_ERROR, "验证码错误");


        
        EXCEPTION_MESSAGE.put(USER_PASSENGER_REL_EXISTS, "该人员信息已存在");
        EXCEPTION_MESSAGE.put(USER_CONTACT_EXISTS, "联系人已存在");
        
        EXCEPTION_MESSAGE.put(FLIGHT_CACHE_ADD_ERROR, "航班缓存添加失败，航班信息：%t");
        
        EXCEPTION_MESSAGE.put(LOGIN_FAIL_TMC_WEB, "TMC编码、账号或密码错误");
        EXCEPTION_MESSAGE.put(DEPARTMENT_DELETE_ERROR, "部门删除失败，该部门下员工不为空");

		EXCEPTION_MESSAGE.put(REFUND_DELETE_ERROR, "退房回录失败");
		EXCEPTION_MESSAGE.put(REFUND_PROCESSING_ERROR, "退房回录失败,当前订单正在处理中，请稍后。");
        EXCEPTION_MESSAGE.put(COSTCENTER_DELETE_ERROR, "成本中心删除失败，该成本中心下员工不为空");
        EXCEPTION_MESSAGE.put(POLICY_DELETE_ERROR , "差旅政策删除失败，该项差旅政策被使用");
        
        EXCEPTION_MESSAGE.put(USER_POLICY_NOT_FOUND, "没有找到用户差旅政策");
        
        EXCEPTION_MESSAGE.put(SELECT_PARAMS_ERROR , "查询参数错误");
        EXCEPTION_MESSAGE.put(ORDER_REPEAT , "订单已存在");
        
        EXCEPTION_MESSAGE.put(TICKET_PRICE_NOT_EQUAL , "机票票价有变动，请重新确认订单");
        EXCEPTION_MESSAGE.put(INSURANCE_PRICE_NOT_EQUAL , "保险价格有变动，请重新确认订单");
        
        EXCEPTION_MESSAGE.put(FLIGHT_TICKET_NOT_PNR , "该舱位无剩余机票，请重新选择"); 
        EXCEPTION_MESSAGE.put(TMC_OFFICEID_NOT_CONFIG , "代理人配置缺失"); 
        
        EXCEPTION_MESSAGE.put(TICKET_ETDZ_ERROR , "出票失败"); 
        EXCEPTION_MESSAGE.put(TICKET_RR_ERROR , "出票时做RR失败"); 
        EXCEPTION_MESSAGE.put(TICKET_ETDZ_ERROR_PRICE_CHANGE , "机票价格变动，但是用户已支付，需要人工处理，多退少补"); 
        EXCEPTION_MESSAGE.put(CHANGE_ONLY_ONE , "该机票已退票或已改签");
        
        EXCEPTION_MESSAGE.put(TICKET_IS_NOT_ISSUED , "该机票未出票或已经退改签，不能进行改签或退票");
        EXCEPTION_MESSAGE.put(ORDER_LIST_IS_EMPTY , "获取订单列表数据为空");
        
        EXCEPTION_MESSAGE.put(MODIFY_PASSWORD_ERROR , "修改密码失败");
        EXCEPTION_MESSAGE.put(MODIFY_PASSWORD_CHECK_FAIL , "原密码不正确");
        
        EXCEPTION_MESSAGE.put(PHONE_NOT_REGIST , "手机号或邮箱未注册");
        EXCEPTION_MESSAGE.put(CHANGE_FEE_IS_ERROR , "改签费有变动，请重新提交改签申请");
        EXCEPTION_MESSAGE.put(ORDER_INFO_IS_ERROR , "查看订单详情失败");
        EXCEPTION_MESSAGE.put(RETRIEVE_PASSWORD_ERROR , "重置密码失败,公司编码或手机号输入错误");
        
        EXCEPTION_MESSAGE.put(CORP_PAY_LIMIT_OVER , "企业支付额度超出范围，请联系代理人");
        
        EXCEPTION_MESSAGE.put(TICKET_PRICE_ERROR , "机票价格异常，请联系代理人");
        EXCEPTION_MESSAGE.put(REFUSE_DELETE_TMC_USER , "员工账号为管理员，默认不能删除");
        
        EXCEPTION_MESSAGE.put(CORP_NAME_IS_EXIST, "公司名称被占用，请重新输入");
        EXCEPTION_MESSAGE.put(CORP_CODE_IS_EXIST, "公司编码被占用，请重新输入");
        
        EXCEPTION_MESSAGE.put(FLIGHT_ORDER_CANNOT_CANCEL, "只能取消待支付状态的订单"); 

        EXCEPTION_MESSAGE.put(VIP_CODE_IS_EXIST, "大客户编码已存在");
        EXCEPTION_MESSAGE.put(CORP_VIP_IS_OCCUPY, "该大客户编码被占用，请重新输入"); 
        EXCEPTION_MESSAGE.put(USER_PHONE_IS_EXIST, "该手机号已存在，请重新输入"); 
        EXCEPTION_MESSAGE.put(CORP_DEPARTMENT_IS_EXIST, "该部门已存在，请重新输入"); 
        EXCEPTION_MESSAGE.put(CORP_COST_CENTER_IS_EXIST, "该成本中心已存在，请重新输入");
		EXCEPTION_MESSAGE.put(CORP_COST_CENTER_NUM_IS_EXIST, "该成本中心编号已存在，请重新输入");
        EXCEPTION_MESSAGE.put(CORP_POLICY_IS_EXIST, "该差旅政策已存在，请重新输入"); 
        EXCEPTION_MESSAGE.put(CORP_ADMIN_IS_EXIST, "管理员账号已存在，请重新输入");
        EXCEPTION_MESSAGE.put(USER_PASSENGER_EMP_EXISTS, "该人员信息已存在");
        EXCEPTION_MESSAGE.put(COSTCENTER_IS_ONLY_ONE, "成本中心删除失败，该企业下属成本中心仅剩一个"); 
        EXCEPTION_MESSAGE.put(TICKET_NUMBER_IS_ERROR, "改签机票票号不正确，请重新输入"); 
        EXCEPTION_MESSAGE.put(DEPARTMENT_EXIST_CHILD, "部门删除失败，该部门包含下级部门"); 

        EXCEPTION_MESSAGE.put(DEPARTMENT_IS_ONLY_ONE, "部门删除失败，该公司部门只剩一个"); 
        EXCEPTION_MESSAGE.put(POLICY_IS_ONLY_ONE, "差旅政策删除失败，该公司差旅政策只剩一个");
        EXCEPTION_MESSAGE.put(VIP_DISCOUNT_IS_NOT_EMPTY, "大客户协议删除失败，该大客户协议下存在折扣信息");
        EXCEPTION_MESSAGE.put(VIP_DISCOUNT_IS_EXIST, "大客户折扣信息已存在");
        EXCEPTION_MESSAGE.put(TIMEOUT_WAITING_FOR_CONNECTION, "连接超时，请稍后重试");
        EXCEPTION_MESSAGE.put(ACCOUNT_IS_NOT_MANAGER, "您不是管理员，不能重置员工密码");
        EXCEPTION_MESSAGE.put(LOGIN_FAIL_EMP_WEB, "登陆失败，账号或密码错误");
        
        EXCEPTION_MESSAGE.put(FLIGHT_TICKET_NOT_NET_PRICE, "机票特价不存在");
        EXCEPTION_MESSAGE.put(CREATE_PNR_ERROR, "非常抱歉，创建pnr失败，该舱位已无余票，请重新查询!");
        EXCEPTION_MESSAGE.put(OPEN_TICKET_NOT_FOUND, "未找到该日期范围内的机票信息");
        EXCEPTION_MESSAGE.put(CREATE_ORDER_FAIL, "抱歉，创建订单失败，请稍后重试");
        EXCEPTION_MESSAGE.put(FLIGHT_TICKET_SEARCH_FAIL, "机票信息查询失败，请稍后重试");
        
        EXCEPTION_MESSAGE.put(FLIGHT_PASSENGER_NAME_CHECK_FAIL, "乘机人姓名输入有误，请按乘机姓名规则输入");
        
        EXCEPTION_MESSAGE.put(ORDER_NOT_EXIST, "订单不存在");
        EXCEPTION_MESSAGE.put(ORDER_STATUS_ERROR, "订单状态不正确");

		EXCEPTION_MESSAGE.put(RECHARGE_NOT_FOUND, "未找到充值单");

        
        EXCEPTION_MESSAGE.put(PARTNER_NOT_FOUND, "合作商不存在");
        EXCEPTION_MESSAGE.put(PARTNER_NOT_OPEN, "合作商未开通");
        EXCEPTION_MESSAGE.put(ADDRESS_TO_LATANDLNG_ERROR,"地址转换经纬度失败，请稍后重试");
        
        EXCEPTION_MESSAGE.put(TICKET_PAT_ERROR,"出票时运价失败");
		EXCEPTION_MESSAGE.put(INPUT_INDIVIDUAL_INSTRUCTION_ERROR,"写入个性化指令失败");
		EXCEPTION_MESSAGE.put(SELECT_FLIGHT_SEGMENT_ERROR,"航段费获取失败");

        EXCEPTION_MESSAGE.put(TMC_CATIAL_ACCOUNT_NO_EXIST, "tmc资金账户不存在");
        EXCEPTION_MESSAGE.put(CORP_SERVICE_FEE_IS_EXISTS,"企业服务费已存在");
        EXCEPTION_MESSAGE.put(SUBMIT_YITU8_ORDER_FAIL,"易图8订单失败");
        EXCEPTION_MESSAGE.put(TMC_MONEY_BALANCE_LESS, "账户余额不足");
        
        
    	EXCEPTION_MESSAGE.put(EVENT_REDUCE_NOT_OPEN, "活动已结束，请重新查询并预订！");
    	EXCEPTION_MESSAGE.put(EVENT_REDUCE_VALUE_NOT_EQUALS, "优惠价格有变动，请重新查询并预订！");
    	
    	EXCEPTION_MESSAGE.put(TRAIN_19E_ERROR, "19e接口返回出错");
    	EXCEPTION_MESSAGE.put(TRAIN_TICKET_ERROR, "火车票剩余数量不足");
    	EXCEPTION_MESSAGE.put(TRAIN_ORDER_CANCEL_ERROR, "火车票订单状态不允许取消");
    	EXCEPTION_MESSAGE.put(TRAIN_TICKET_REFUND_ERROR, "火车票订单状态不允许退票");
    	EXCEPTION_MESSAGE.put(LOGIN_COOKIE_INVALID, "12306 cookie已过期");
    	EXCEPTION_MESSAGE.put(TRAIN_WORK_TIME_ERROR, "超过火车票每日订票时间");
    	EXCEPTION_MESSAGE.put(TRAIN_TICKET_CHANGE_ERROR, "火车票订单状态不允许改签");
    	EXCEPTION_MESSAGE.put(KONGTIE51_TICKET_ERROR,"空铁下单接口异常");
    	
    	EXCEPTION_MESSAGE.put(TRAIN_19E_TEST, "测试模式火车票不能下订单");
    	
    	EXCEPTION_MESSAGE.put(TRAIN_TICKET_CREATE_ERROR, "火车票订单创建失败");
    	EXCEPTION_MESSAGE.put(TRAIN_FUNCTION_SWITCH, "火车相关功能升级中，请稍候");
    	
    	EXCEPTION_MESSAGE.put(TRAIN_NOT_OPEN, "火车功能未开通");
    	
    	EXCEPTION_MESSAGE.put(PNR_RT_ERROR, "无法读取PNR信息，请检查PNR编码");

    	EXCEPTION_MESSAGE.put(HOTEL_CONFIG_ERROR, "酒店配置缺失");
    	EXCEPTION_MESSAGE.put(HOTEL_CHECK_PRICE, "酒店验价失败");
    	EXCEPTION_MESSAGE.put(HOTEL_INVENTORY_ERROR, "酒店房间库存不足");
    	EXCEPTION_MESSAGE.put(HOTEL_PRICE_ERROR, "酒店价格变动请重新查价");
    	EXCEPTION_MESSAGE.put(HOTEL_SELECT_ERROR,"酒店信息已过期，请重新查询!");
    	EXCEPTION_MESSAGE.put(HOTEL_CHANNEL_CLOSE,"该酒店已被下架，请预定其他酒店");
    	EXCEPTION_MESSAGE.put(HOTEL_CUSTOMER_NAME_CHECK_FAIL,"中文姓名不允许出现英文、特殊字符、数字；英文姓名允许-.／空格特殊字符出现，不允许出现数字、特殊字符");
    	EXCEPTION_MESSAGE.put(HOTEL_CHANNEL_ERROR,"该酒店已被下架，请返回首页重新查询");
    	EXCEPTION_MESSAGE.put(HOTEL_CREATE_ERROR, "酒店订单创建失败");
    	EXCEPTION_MESSAGE.put(HOTEL_REFUND_ERROR, "酒店订单取消失败");
    	EXCEPTION_MESSAGE.put(HOTEL_CHECK_CANCEL_PENALTY,"取消规则有变动，请刷新房型列表获取新的取消规则");
    	EXCEPTION_MESSAGE.put(HOTEL_GUARANTEE_ERROR, "酒店订单需要担保");
    	EXCEPTION_MESSAGE.put(ORDER_PAT_ERROR, "机票订单运价封口失败");
    	EXCEPTION_MESSAGE.put(PNR_CHECK_ERROR, "pnr信息校验失败");
    	EXCEPTION_MESSAGE.put(DATA_FORMAT_ERROR, "数据格式不正确");
    	EXCEPTION_MESSAGE.put(DEFAULT_OFFICE_NOTEXIST_ERROR, "不存在默认office");
    	EXCEPTION_MESSAGE.put(ETERM_EXIST_OWNER, "当前配置号在飞巴存在所有者，如果这个配置号是您本人，则请联系飞巴客服");
    	EXCEPTION_MESSAGE.put(ETERM_NOTEXIST_OWNER, "当前配置号在飞巴系统不存在所有者，请设置配置号为自有配置");
		EXCEPTION_MESSAGE.put(TICKET_ETERM_PNR_ERROR, "pnr信息解析失败");
    	
    	EXCEPTION_MESSAGE.put(CREATE_INSURANCE_ORDER_FAIL, "保险订单创建失败");
    	EXCEPTION_MESSAGE.put(CANCEL_INSURANCE_ORDER_FAIL, "保单取消失败");
    	EXCEPTION_MESSAGE.put(SELECT_INSURANCE_FAIL, "未获取相关保险信息");

    	EXCEPTION_MESSAGE.put(FLIGHT_CANCEL_TIMES,"当前订票员当日取消订单次数过多,请第二天再预订");
		EXCEPTION_MESSAGE.put(ETERM_GROUP_DEFAULT_EXIST_ERROR, "默认配置号组已存在，不能重复设置");
		EXCEPTION_MESSAGE.put(USER_EMAIL_IS_EXIST,"邮箱已存在");
		EXCEPTION_MESSAGE.put(USER_DELETE_ERRO, "该人员为审批人员，无法删除");
        EXCEPTION_MESSAGE.put(AUDITING_UPDATE_ERRO, "该人员为审批人员，不能取消其审批权限");
		EXCEPTION_MESSAGE.put(TMC_REGIST_SWITCH_NOTEXIST,"该TMC不是未开通散客注册");
		
		EXCEPTION_MESSAGE.put(WECHAT_OPENID_ERROR,"微信账号尚未与业务账号关联");
		EXCEPTION_MESSAGE.put(WECHAT_AUTH_ERROR, "微信授权失败");

		EXCEPTION_MESSAGE.put(DEPARTMENT_IS_NOT_AUDITED,"该部门不存在审批人员");
		EXCEPTION_MESSAGE.put(CHANNEL_ACCOUNT_ERROR,"渠道商配置错误");
		EXCEPTION_MESSAGE.put(FLIGHT_DATE_CHECK_FAIL,"航班日期校验未通过");
		EXCEPTION_MESSAGE.put(FLIGHT_FILL_SEAT_ERROR,"未能获取到放大器补位结果");
		EXCEPTION_MESSAGE.put(FLIGHT_FILL_SEAT_FAILURE,"补位失败，可能原仓位已无坐席，请重新预定");
		EXCEPTION_MESSAGE.put(FLIGHT_DEL_PASSENGER,"删除乘客信息失败");
		EXCEPTION_MESSAGE.put(CORP_IS_NOT_SELECT,"请点选一个企业！");
		EXCEPTION_MESSAGE.put(INTL_FLIGHT_E515,"查询结果为空");
		EXCEPTION_MESSAGE.put(VERIFY_CODE_NULL,"请输入验证码");
		EXCEPTION_MESSAGE.put(HOTEL_PREMIUM_TYPE_ERR,"酒店同一渠道只可设置相同种类加价规则");
		EXCEPTION_MESSAGE.put(HOTEL_PREMIUM_TYPE_MUCH,"酒店同一渠道全范围加价只可设置一条");
		EXCEPTION_MESSAGE.put(HOTEL_PREMIUM_PRICE_ERR,"参数范围重叠");
		EXCEPTION_MESSAGE.put(HOTEL_PREMIUM_CITY_ERR,"城市设置重复");
		EXCEPTION_MESSAGE.put(HOTEL_PREMIUM_CITY_EMPTY,"城市设置不能为空");
		EXCEPTION_MESSAGE.put(HOTEL_POLICY_CITY_EMPTY,"差旅政策城市自定义不能为空");
		EXCEPTION_MESSAGE.put(CORP_CAPITAL_ACCOUNT_NO_EXIST,"企业资金账户不存在");
		EXCEPTION_MESSAGE.put(CORP_MONEY_BALANCE_LESS,"企业账户余额不足");
		EXCEPTION_MESSAGE.put(TMC_FLIGHT_DISCOUNT_ERROR,"处理优惠政策失败");
		EXCEPTION_MESSAGE.put(UPDATE_ORDER_FINANCIAL_ERROR,"更新财务付款方式错误");
		EXCEPTION_MESSAGE.put(AUDITING_SETTLEMENT_ERROR,"对账结算审核出错");
		EXCEPTION_MESSAGE.put(ORDER_LOG_ERROR,"订单日志记录错误");
		EXCEPTION_MESSAGE.put(CHANNEL_INSURANCE_ERROR,"保险渠道发生错误");
		EXCEPTION_MESSAGE.put(CHECK_INSURANCE_PERSON_ERROR,"校验保险投保人失败");
		EXCEPTION_MESSAGE.put(INSURANCE_CHANGE_ERROR,"保险改签错误");
		EXCEPTION_MESSAGE.put(KONGTIE51_CARDTYPE_ERROR,"此渠道不支持该证件类型");
		EXCEPTION_MESSAGE.put(SEND_SMS_ERROR,"发送短信失败");
		EXCEPTION_MESSAGE.put(SEARCH_CBE_FLIGHT_TICKET_ERROR,"配置号查询航班信息超时，部分舱位可能无法展示，请稍后重新查询！");
		EXCEPTION_MESSAGE.put(SEND_MQ_MSG_ERROR,"发送MQ消息异常");
		EXCEPTION_MESSAGE.put(HUIZE_INSURANCE_ERROR,"慧择保险错误");
		EXCEPTION_MESSAGE.put(MQ_MSG_NEED_RECONSUMER, "创建订单事物未提交,需要重新消费");
		EXCEPTION_MESSAGE.put(PAT_GP_PNR_FAIL, "政采票验价失败,没有符合条件的运价请手工处理");
		EXCEPTION_MESSAGE.put(CUSTOMER_ATTENTION_RULE_ERROR, "客户维护规则管理错误");
		EXCEPTION_MESSAGE.put(BLACK_INS_ERROR,"黑屏保险操作失败");
		EXCEPTION_MESSAGE.put(BLACK_INS_SUBMIT_RESULT_ERROR,"出保结果解析失败");
		EXCEPTION_MESSAGE.put(INSURANCE_PASSWORD_ERROR,"使用保险密码时发生错误");
		EXCEPTION_MESSAGE.put(BLACK_INSURANCE_ERROR,"黑屏保险错误");
		EXCEPTION_MESSAGE.put(ADD_PASSWORD_ERROR,"新增密码失败");
		EXCEPTION_MESSAGE.put(VERIFY_TIME_LIMIT,"发送频率超过限制！");
		EXCEPTION_MESSAGE.put(VERIFY_DAT_LIMIT,"找回密码验证超单日限额！");
		EXCEPTION_MESSAGE.put(ORDER_UPDATE_TRACK_ERROR,"订单更新轨迹错误");
		EXCEPTION_MESSAGE.put(CHECK_TMC_CHANNEL_QUOTA_ERROR,"今日政策配额已用完");
		EXCEPTION_MESSAGE.put(ORDER_DELETE_ERROR,"订单删除错误");
		EXCEPTION_MESSAGE.put(CAN_NOT_OPEN_HIGH_CABIN_CHECK,"无法修改高舱检测状态");
		EXCEPTION_MESSAGE.put(TRAIN_ORDER_PASSENGER_DATA_ERROR,"包含数据异常的乘客");
		EXCEPTION_MESSAGE.put(DEAL_WITH_VIPCODE_ERROR,"大客户号有误，请重新确认");
		EXCEPTION_MESSAGE.put(INSURANCE_IS_UNUSERFUL_ERROR, "保险产品已下架");
		EXCEPTION_MESSAGE.put(FLIGHT_CHANNEL_ERROR,"渠道错误");
		EXCEPTION_MESSAGE.put(YONGAN_INSURANCE_ERROR,"永安保险渠道发生错误");
		EXCEPTION_MESSAGE.put(TRAIN_TICKET_REISSUE,"火车再次出票错误");
		EXCEPTION_MESSAGE.put(PARTNER_PARAM_ERROR,"合作商参数配置错误");
		EXCEPTION_MESSAGE.put(IDCARD_TYPE_ERROR,"该业务不支持该证件类型");
		EXCEPTION_MESSAGE.put(REDIS_POOL_EXHAUST, "REDIS连接池耗尽");
		EXCEPTION_MESSAGE.put(CARRY_FORWARD_ERROR,"业务结转数据失败");
		EXCEPTION_MESSAGE.put(EXPORT_EXCEL_ERROR,"导出excel失败");
		EXCEPTION_MESSAGE.put(TRAVEL_APPROVAL_AUDITING_NOT_CONFIG,"该企业未开启出差申请审批设置");
		EXCEPTION_MESSAGE.put(PART_ORDER_PAY,"当前机票确认成功，请完成剩余机票确认");
		EXCEPTION_MESSAGE.put(UPTICK_RULE_HOTEL_COUNT_ERROR,"范围包含酒店的规则不可超过10条");
		EXCEPTION_MESSAGE.put(UPTICK_RULE_SAME_NAME_ERROR,"规则名称已存在");
		EXCEPTION_MESSAGE.put(UPTICK_RULE_FEE_ERROR,"加价规则费用参数异常");
		EXCEPTION_MESSAGE.put(XIECHENG_ERROR,"处理失败");
		EXCEPTION_MESSAGE.put(TICKET_NOT_OPEN,"客票已使用");
		EXCEPTION_MESSAGE.put(CITY_NAME_EXISTED,"城市名称已存在");
		EXCEPTION_MESSAGE.put(CITY_CODE_EXISTED,"城市编码已存在");
		EXCEPTION_MESSAGE.put(CITY_NOT_EXISTED,"城市不存在");
		EXCEPTION_MESSAGE.put(CITY_CODE_NOT_EXISTED,"城市编码不存在");
		EXCEPTION_MESSAGE.put(CITY_NAME_NOT_EXISTED,"城市名称不存在");
		EXCEPTION_MESSAGE.put(CITY_EXISTED,"城市已存在");
		EXCEPTION_MESSAGE.put(AIRPORT_NOT_EXISTED,"机场不存在");
		EXCEPTION_MESSAGE.put(AIRPORT_NAME_EXISTED,"机场名称已存在");
		EXCEPTION_MESSAGE.put(AIRPORT_CODE_EXISTED,"机场编码已存在");
		EXCEPTION_MESSAGE.put(AIRLINE_EXISTED,"航司信息已存在");
		EXCEPTION_MESSAGE.put(AIRLINE_NOT_EXISTED,"航司信息不存在");
		EXCEPTION_MESSAGE.put(REGION_OPERATION_RECORD_NOT_EXIST,"行政区审核数据不存在");
		EXCEPTION_MESSAGE.put(USER_INFO_GET_FAILURE,"根据session获取用户信息失败");
		EXCEPTION_MESSAGE.put(USER_INFO_CHECK_FAILURE,"获取企业用户信息失败");
		EXCEPTION_MESSAGE.put(ROOM_NUMBER_NOT_FOUND,"房间入住人信息未找到");
		EXCEPTION_MESSAGE.put(FINANCE_SERVICE_FEE_ERROR,"火车服务费计算异常");
		EXCEPTION_MESSAGE.put(HOTEL_PRICE_CHANGE,"酒店产品价格发生变动！");
		EXCEPTION_MESSAGE.put(HOTEL_ROOM_DOWN,"酒店产品房量售空！");
		EXCEPTION_MESSAGE.put(REGION_OPERATION_RECORD_EXIST_NO_PEND,"请按照顺序依次处理");
		EXCEPTION_MESSAGE.put(FATHER_REGION_NOT_EXIST,"父级行政区不存在");
		EXCEPTION_MESSAGE.put(HOTEL_ROOM_NO_CONFORM,"酒店预定规则不符！");
		EXCEPTION_MESSAGE.put(FLIGHT_CHANGE,"改签航班变更，需重新选择航班！");
		EXCEPTION_MESSAGE.put(REGION_NOT_EXIST,"行政区数据不存在");
		EXCEPTION_MESSAGE.put(BUSINESS_NOT_SELECT,"按订单号查询时需要选择对应的业务类型");
		EXCEPTION_MESSAGE.put(EXCEPTION_HANDLE_FAIL,"机票异常单据处理失败");
		EXCEPTION_MESSAGE.put(UATP_PAY_FAIL,"UATP支付出票失败");
		EXCEPTION_MESSAGE.put(SAME_CHANNEL_NOT_ALLOWED,"不允许合并相同渠道");
//		EXCEPTION_MESSAGE.put(AIRLINE_EXISTED,"航司编码或名称或简称或极简称已经存在");
		EXCEPTION_MESSAGE.put(AIRLINE_NOT_EXIST,"该航司不存在，请先维护航司基础数据");
		EXCEPTION_MESSAGE.put(AIRLINE_IS_NULL,"航司为空");
		EXCEPTION_MESSAGE.put(SEATCLASS_IS_NULL,"舱位为空");
		EXCEPTION_MESSAGE.put(USER_NAME_FORMAT_ERROR,"英文姓与名之间请用/分割");
    }

    public static String getMessage(String code) {
        return EXCEPTION_MESSAGE.get(code);
    }


}
