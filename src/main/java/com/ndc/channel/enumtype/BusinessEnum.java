package com.ndc.channel.enumtype;

import com.ndc.channel.exception.BusinessException;

import java.util.*;

public class BusinessEnum {

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
		CREATE_ORDER("A0536", "/ndc-flight-order-create/flight/flightOrderCreate");

		private String code;
		private String path;

		public String getCode() {
			return code;
		}

		public String getPath() {
			return path;
		}

		NdcApiInfo(String code, String path) {
			this.code = code;
			this.path = path;
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
