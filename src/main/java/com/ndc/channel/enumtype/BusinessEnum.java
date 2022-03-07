package com.ndc.channel.enumtype;

import com.ndc.channel.exception.BusinessException;
import com.ndc.channel.exception.BusinessExceptionCode;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.util.Assert;

import java.util.*;

public class BusinessEnum {

	/**
	 * 客票状态
	 */
	public enum TicketStatus {

		_708("已换开"),B("客票已使用"),
		BD("旅客已登机"),CK("正在办理值机"),
		I("客票有效"),PE("客票已换开为纸票"),
		RF("客票已退"),S("客票已挂起"),V("作废");

		private String label;

		TicketStatus(String label) {
			this.label = label;
		}

		public String getLabel() {
			return label;
		}
	}

	/**
	 * 订单项状态：OUTINVOICE-已出票
	 */
	public enum OrderItemStatusCode {

		CANCELLEDBYCUSTOMER("订单项目预订已被客户取消或者因支付时间超时系统自动取消"),
		ENTITLED("等待接收"),NOTENTITLED("无权接收"),BOOKFAIL("预订失败"),WAITEPAY("等待支付"),WAITEOUTINVOICE("等待开票"),OUTINVOICE("已开票"),
		INVOICING("开票中"),FAILUREOUTINVOICE("开票失败"),REFUNDED("已退票"),REFUNDFAIL("退票失败"),ONREFUND("退票中"),
		REFUNDERROR("差错退款"),INVALID("无效订单"),BOOKSUCCESS("预订成功"),BOOKCANCEL("取消预订"),PAYING("支付中"),ABNORMAL("异常"),UNKNOWN("未知");
		private String label;

		OrderItemStatusCode(String label) {
			this.label = label;
		}

		public String getLabel() {
			return label;
		}

		public static String getLabelByName(String name) {
			return Arrays.stream(OrderItemStatusCode.values()).filter(s->s.name().equals(name)).findFirst().orElse(UNKNOWN).getLabel();
		}
	}

	public enum GenderCode{
		M("M", "男", "1"), F("F", "女", "2");

		private String code;
		private String msg;
		private String nativeCode;

		GenderCode(String code, String msg, String nativeCode) {
			this.code = code;
			this.msg = msg;
			this.nativeCode = nativeCode;
		}

		public String getCode() {
			return code;
		}

		public String getMsg() {
			return msg;
		}

		public String getNativeCode() {
			return nativeCode;
		}

		public static String getGenderCode(String nativeCode) {
			Assert.notNull(nativeCode, "性别必填！");
			return Arrays.stream(GenderCode.values())
					.filter(genderCode -> genderCode.getNativeCode().equals(nativeCode))
					.findFirst().orElseThrow(()->new BusinessException(BusinessExceptionCode.REQUEST_PARAM_ERROR, "性别类型转换错误:"+nativeCode))
					.getCode();
		}
	}

	public enum IdentityDocTypeCode{
		ID_CARD("709", "居民身份证", "1"), PT("PT", "护照", "2"), _9CT("9CT", "台胞证", "5"),
		_9CA("9CA","港澳通行证", "4"), _9CC("9CC","回乡证", "7"), TRP("TRP","台湾居民居住证", "7"),
		GRP("GRP","港澳居民居住证", "7"), _9CN("9CN","大陆居民往来中国台湾通行证", "7"), TID("TID","中国台湾身份证", "7"),
		_710("710","外国人永久居留证", "7"), MI("MI","军官证", "3"), GM("GM","军残证", "7"), POL("POL","警官证", "7"),
		JC("JC","警残证", "7"), HH("HH","户口簿", "7"), BR("BR","出生证明", "7"), F1("F1","其他", "6");

		private String code;
		private String msg;

		// 乘机人证件类型, 1 身份证； 2 护照； 3 军官证； 4 港澳通行证； 5 台胞证； 6 其他.
		private String nativeCode;

		private IdentityDocTypeCode(String code, String msg, String nativeCode) {
			this.code = code;
			this.msg = msg;
			this.nativeCode = nativeCode;
		}

		public String getCode() {
			return code;
		}

		public String getMsg() {
			return msg;
		}

		public String getNativeCode() {
			return nativeCode;
		}

		/**
		 * 乘机人证件类型, 1 身份证； 2 护照； 3 军官证； 4 港澳通行证； 5 台胞证； 6 其他.
		 * @param nativeCode
		 * @return
		 */
		public static String getIdentityDocTypeCode(String nativeCode) {
			Assert.notNull(nativeCode, "证件类型必填！");
			return Arrays.stream(IdentityDocTypeCode.values())
					.filter(identityDocTypeCode -> identityDocTypeCode.getNativeCode().equals(nativeCode)).findFirst()
					.orElseThrow(()->new BusinessException(BusinessExceptionCode.REQUEST_PARAM_ERROR, "证件类型转换错误！"))
					.getCode();
		}

	}

	public enum BaggageType {

		CARRY_ON("CarryOn", "随身"), CHECKED("Checked", "托运");

		private String code;
		private String msg;

		private BaggageType(String code, String msg) {
			this.code = code;
			this.msg = msg;
		}

		public String getCode() {
			return code;
		}

		public String getMsg() {
			return msg;
		}
	}

	public enum WeightUnit{

		K("K", "KG"), L("L", "LB"), NO("", "");

		private String code;
		private String msg;

		private WeightUnit(String code, String msg) {
			this.code = code;
			this.msg = msg;
		}

		public String getCode() {
			return code;
		}

		public String getMsg() {
			return msg;
		}

		public static String getMsg(String code) {
			return Arrays.stream(WeightUnit.values()).filter(wu-> wu.code.equals(code)).findFirst().orElse(WeightUnit.NO).getMsg();
		}
	}

	public enum OperationalSuffixText{
		T("T", "共享航班"), F("F","非共享航班");
		private String code;
		private String msg;
		OperationalSuffixText(String code, String msg) {
			this.code = code;
			this.msg = msg;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}
	}

	public enum NdcApiInfo{

		FLIGHT_SEARCH("A0534", "/ndc-shopping-common/shopping/basicShopping"),
		OFFER_PRICE("A0535", "/ndc-flight-offer-price/price/flightOfferPrice"),
		CREATE_ORDER("A0536", "/ndc-flight-order-create/flight/flightOrderCreate"),
		ORDER_PAY("A0537", "/ndc-flight-order-payment/flight/flightOrderPayment"),
		ORDER_DETAIL("A0538", "/ndc-flight-order-retrieve/flight/flightOrderRetrieve"),
		REFUND_MONEY_QUERY("A0540", "/ndc-flight-ticket-refund/refund/flightRefundFee"),
		REFUND_APPLY("A0539", "/ndc-flight-ticket-refund/refund/flightRefundApply"),
		REFUND_CONFIRM("A0541", "//ndc-flight-ticket-refund/refund/flightRefundConfirm");


		private String apiCode;
		private String apiPath;

		public String getApiCode() {
			return apiCode;
		}

		public String getApiPath() {
			return apiPath;
		}

		NdcApiInfo(String apiCode, String apiPath) {
			this.apiCode = apiCode;
			this.apiPath = apiPath;
		}
	}

	/**
	 * 产品id
	 * 0:标准产品 1：官网产品 2：特惠产品 3：大客户协议产品 4：政采产品 5:未知产品类型
	 * @author Larissa
	 */
	public enum ProductType {
		/**
		 * 标准产品（包括自营标准产品和渠道标准产品）
		 */
		STANDARD("0"),
		/**
		 * 官网产品
		 */
		WEBSITE("1"),
		/**
		 * 特惠产品
		 */
		PREFERENTIAL_PRICE("2"),
		/**
		 * 大客户协议产品
		 */
		AGREEMENT("3"),
		/**
		 * 政采产品
		 */
		GOVERMENT("4"),
		/**
		 * 未知产品类型
		 */
		UNKOWN("5");

		private String code;

		ProductType(String code) {
			this.code = code;
		}

		public String getCode() {
			return code;
		}

		public static List<ProductType> getNonStandardProductType(){
			return Arrays.asList(AGREEMENT);
		}
	}

	public enum DateType {

		Day("D", "天"), Month("M", "月"), Hour("H","小时"), Minute("N","分钟");

		private String code;
		private String msg;

		DateType(String code, String msg) {
			this.code = code;
			this.msg = msg;
		}

		public String getCode() {
			return code;
		}

		public String getMsg() {
			return msg;
		}

		public static String getDateMsgByCode(String code) {
			final DateType dateType = Arrays.stream(DateType.values()).filter(dt -> {
				return dt.getCode().equals(code);
			}).findFirst().orElseThrow(BusinessException::new);

			return dateType.getMsg();
		}
	}

	public enum MaxTimeFlag {

		INE("0", "含"), EXI("1", "不含");

		private String code;
		private String msg;

		MaxTimeFlag(String code, String msg) {
			this.code = code;
			this.msg = msg;
		}

		public String getCode() {
			return code;
		}

		public String getMsg() {
			return msg;
		}

		public static String getMaxTimeFlagByCode(String code) {
			final MaxTimeFlag dateType = Arrays.stream(MaxTimeFlag.values()).filter(dt -> {
				return dt.getCode().equals(code);
			}).findFirst().orElseThrow(BusinessException::new);

			return dateType.getMsg();
		}
	}
}
