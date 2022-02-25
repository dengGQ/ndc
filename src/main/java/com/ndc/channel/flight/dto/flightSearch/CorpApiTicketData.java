package com.ndc.channel.flight.dto.flightSearch;

import com.ndc.channel.flight.xmlBean.flightSearch.response.bean.ServiceDefinition;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CorpApiTicketData {

	private static final long serialVersionUID = 8081981790757831368L;
	@ApiModelProperty(value = "航班ID", required = true)
	private String flightId;

	@ApiModelProperty(value = "舱位ID", required = true)
	private String ticketId;

	@ApiModelProperty(value = "折扣", required = true)
	private BigDecimal discount;

	//TODO 后续将删除,用productId代替
	@ApiModelProperty(value = "是否官网价格0-否；1-是", required = true)
	private String isWebSite;

	@ApiModelProperty(value = "产品类型: 0:标准产品 1：官网产品 2：特惠产品 3：大客户协议产品 4：政采产品 5:未知产品类型", required = true)
	private String productType;

	@ApiModelProperty(value = "主舱位代码", required = false)
	private String mainClassCode;

	@ApiModelProperty(value = "主舱位名称", required = false)
	private String mainClassName;

	@ApiModelProperty(value = "采购价", required = true)
	private BigDecimal price;
	/**
	 * 缓存中的采购
	 */
	private BigDecimal purchasePrice;

	@ApiModelProperty(value = "舱位代码", required = true)
	private String seatClassCode;

	@ApiModelProperty(value = "舱位名称", required = false)
	private String seatClassName;

	@ApiModelProperty(value = "票面价", required = true)
	private BigDecimal ticketPrice;

	@ApiModelProperty(value = "退改签政策", required = false)
	private CorpApiTicketPolicy policy;

	@ApiModelProperty(value = "数量", required = false)
	private String quantity;

	@ApiModelProperty(value = "返佣")
	private BigDecimal rebate;

	@ApiModelProperty(value = "服务费")
	private BigDecimal serviceFee;

	@ApiModelProperty(value = "政策id")
	private String policyId;

	@ApiModelProperty(value = "渠道政策类型",required = false,hidden = true)
	private String channelPolicyType;

	@ApiModelProperty(value = "公布运价")
	private BigDecimal fdPrice;

	@ApiModelProperty(value = "底价-飞巴进价")
	private BigDecimal basePrice ;

	@ApiModelProperty(value="大客户协议代码",required=false)
	private String vipCode;

	@ApiModelProperty(value="大客户价",required=false)
	private BigDecimal vipPrice;

	@ApiModelProperty(value = "价格类型, 0: 未知, 1: fdPrice, 2: nfdPrice", required = false)
	private Byte priceType;


	private String paxId;
	private String offerItemId;
	private String pricingSystemCodeText;

	private String priceClassID;
	private String priceClassCode;
	private String priceClassName;
	private String priceClassDesc;

	private String fareTypeCode;

	private List<TicketServiceDefinition> serviceDefinitionList;
}
