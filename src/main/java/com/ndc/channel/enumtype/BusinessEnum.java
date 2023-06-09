package com.ndc.channel.enumtype;

import com.ndc.channel.exception.BusinessException;
import com.ndc.channel.exception.BusinessExceptionCode;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.util.Assert;

import java.util.*;

public class BusinessEnum {

	public enum PaymentStatusCode{
		UNKNOWN("支付状态未知"),//	1分钟后可以重新发起支付，直到获取到其他支付状态。
		CLOSED("支付完成"),//支付完成，等待出票
		FAILED("支付失败"),//支付失败，订单支付时间没有失效的情况下可以重新发起支付。
		PENDING("支付处理中"),//1分钟后可以重新发起支付。
		ACCEPTED("已接收支付请求"),//对接渠道将H5页面现实给旅客，旅客在该页面发起支付操作【BSP Cash支付】；对接渠道唤醒SDK支付【微信、支付宝、银联APP】，旅客通过对应的APP进行支付。
		PARTIAL_CLOSED("部分成功");//部分成功，订单支付时间没有失效的情况下可以重新发起支付。针对部分成功的扣款东航会差退处理。

		private String statusDesc;

		PaymentStatusCode(String statusDesc) {
			this.statusDesc = statusDesc;
		}

		public static List<String> getAllRetryRequiredStatus() {
			return Arrays.asList(UNKNOWN.name(), FAILED.name(), PENDING.name(), PARTIAL_CLOSED.name());
		}
	}

	public enum RefundAuditingStatus{
		REFUND_EXCEPTION("109", "退票异常"),
		REJECT_FIRST("2011", "一审拒绝"),
		EXCEPTION("309", "生成退票单号异常"),
		REFUND_SUCCESS("501", "退款成功"),
		REFUND_COMPLETE("901", "退票完成"),
		DEL("909", "已删除"),
		UN_KNOW("9999","未知");

		private String code;
		private String label;

		RefundAuditingStatus(String code, String label) {
			this.code = code;
			this.label = label;
		}

		public String getCode() {
			return code;
		}

		public String getLabel() {
			return label;
		}

		/**
		 * 中止状态
		 * @return
		 */
		public static List<String> getEndStatus(){
			return Arrays.asList(REFUND_SUCCESS.getCode(), REFUND_EXCEPTION.getCode(), REJECT_FIRST.getCode(), EXCEPTION.getCode());
		}

		/**
		 * 完成状态
		 * @return
		 */
		public static List<String> getCompleteStatus() {
			return Arrays.asList(REFUND_SUCCESS.getCode());
		}

		/**
		 * 票已退未退款: 一审拒
		 * @return
		 */
		public static List<String> getTicketRefundedStatus() {
			return Arrays.asList(REJECT_FIRST.getCode());
		}

		/**
		 * 异常
		 * @return
		 */
		public static List<String> getTicketRefundFail() {
			return Arrays.asList(REFUND_EXCEPTION.getCode(), EXCEPTION.getCode());
		}


		public static String getLabelByName(String code) {
			return Arrays.stream(RefundAuditingStatus.values()).filter(s->s.getCode().equals(code)).findFirst().orElse(UN_KNOW).getLabel();
		}
	}

	public enum ChangeRefundTypeCode {

		CANCELLATION("Cancellation", "取消（退票）"),
		CHANGE("Change", "变更【改签】"),
		NO_SHOW("NoShow", "误机"),
		OTHER("Other", "其他"),
		UPGRADE("Upgrade(", "改升");
		private String code;
		private String label;

		public String getCode() {
			return code;
		}

		public String getLabel() {
			return label;
		}

		ChangeRefundTypeCode(String code, String label) {
			this.code = code;
			this.label = label;
		}
	}

	/**
	 * 退票方式：1-自愿退票；2-非自愿退票,航变；3-非自愿，病退;4-非自愿,升舱或换开；5-其他；6-非自愿，重购客票退票
	 */
	public enum RefundWay{
		_10("10", "由于自身原因无法成行, 同意扣除退票手续费退票"),_11("11","易享退"), _21("21","由于航空公司原因, 无法成行, 申请全额退票"),
		_22("22","由于乘机人因病(身故), 无法成行, 申请全额退票"), _23("23","重购相同客票且已使用, 申请全额退票"), _24("24","其他（疫情、拒签、系统原因等）");

		private String code;
		private String label;

		RefundWay(String code, String label) {
			this.code = code;
			this.label = label;
		}

		public String getCode() {
			return code;
		}

		public static String getReasonCode(byte refundWay) {
			if (refundWay == 1) {
				return "10";
			}else if(refundWay == 2) {
				return "21";
			}else if(refundWay == 3) {
				return "22";
			}else if(refundWay == 6) {
				return "23";
			}else{
				return "24";
			}
		}
	}

	public enum ServiceName{

		WIFI("wifi"),
		BAGGAGE("行李额"),
		MEAL("餐食"),
		SEAT("座位");

		private String label;

		ServiceName(String label) {
			this.label = label;
		}
	}

	/**
	 * 客票状态
	 */
	public enum TicketStatus {

		_708("已换开"),

		B("客票已使用"),
		BD("旅客已登机"),
		CK("正在办理值机"),
		I("客票有效"),
		PE("客票已换开为纸票"),
		RF("客票已退"),
		S("客票已挂起"),
		V("作废"),
		UN_KNOW("未知");

		private String label;

		TicketStatus(String label) {
			this.label = label;
		}

		public String getLabel() {
			return label;
		}

		public static String getLabelByCode(String code) {
			return Arrays.stream(TicketStatus.values())
					.filter(ticketStatus -> ticketStatus.name().equals(code)).findFirst().orElse(UN_KNOW).getLabel();
		}
	}

	/**
	 * 订单项状态：OUTINVOICE-已出票
	 */
	public enum OrderItemStatusCode {

		CANCELLEDBYCUSTOMER("订单项目预订已被客户取消或者因支付时间超时系统自动取消"),
		ENTITLED("等待接收"),
		NOTENTITLED("无权接收"),

		BOOKSUCCESS("预订成功"),
		BOOKCANCEL("取消预订"),
		BOOKFAIL("预订失败"),

		WAITEPAY("等待支付"),
		PAYING("支付中"),

		WAITEOUTINVOICE("等待开票"),
		OUTINVOICE("已开票"),
		INVOICING("开票中"),
		FAILUREOUTINVOICE("开票失败"),

		REFUNDED("已退票"),
		REFUNDFAIL("退票失败"),
		ONREFUND("退票中"),
		REFUNDERROR("差错退款"),

		INVALID("无效订单"),

		ABNORMAL("异常"),
		UNKNOWN("未知");

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

		public static List<String> getCompleteStatusCode() {
			return Arrays.asList(NOTENTITLED.name(), BOOKCANCEL.name(), BOOKFAIL.name(), OUTINVOICE.name(), FAILUREOUTINVOICE.name(), REFUNDED.name(), REFUNDFAIL.name(), ABNORMAL.name());
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
		CREATE_ORDER("A0533", "/ndc-flight-order-create/flight/flightOrderCreate"),
		ORDER_PAY("A0536", "/ndc-flight-order-payment/flight/flightOrderPayment"),
		ORDER_DETAIL("A0537", "/ndc-flight-order-retrieve/flight/flightOrderRetrieve"),
		REFUND_MONEY_QUERY("A0539", "/ndc-flight-ticket-refund/refund/flightRefundFee"),
		REFUND_APPLY("A0538", "/ndc-flight-ticket-refund/refund/flightRefundApply"),
		REFUND_REAPPLY("A0543", "/ndc-flight-ticket-refund/refund/flightRefundReApply"),
		REFUND_CONFIRM("A0540", "/ndc-flight-ticket-refund/refund/flightRefundConfirm"),
		REFUND_ORDER_DETAIL("A0541", "/ndc-flight-ticket-refund/refund/flightRefundNote"),
		CHANGE_FLIGHT_SEARCH("A0549", "/ndc-flight-order-reshopping/voluntary/voluntaryReshopping"),
		REFUND_MONEY_QUERY_("A0544", "/ndc-flight-ticket-refund/refund/flightRefundRecalcfee"),//非自愿转自愿退票金额查询
		CHANGE_BOOKING("A0550", "/ndc-flight-order-rebooking/voluntary/voluntaryOrderRebooking");



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

	public enum BookingRefTypeCode{
		PAX_ID("1","旅客唯一标识"), REFUND_ORDER_ID("708", "退货单ID"), REPEAT_TICKET_NUMBER("709", "重购客票号");

		private String code;
		private String msg;
		BookingRefTypeCode(String code, String msg) {
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

	public enum QueryType {
		REFUND_MONEY_QUERY(0, "退票手续费查询"), CHANGE_MONEY_QUERY(1, "改签手续费查询"), REFUND_MONEY_QUERY_(2, "非自愿转自愿退票金额查询");

		private Integer code;
		private String label;
		QueryType(Integer code, String label) {
			this.code = code;
			this.label = label;
		}

		public Integer getCode() {
			return code;
		}

		public String getLabel() {
			return label;
		}
	}
}
