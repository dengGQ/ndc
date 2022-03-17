package com.ndc.channel.flight.dto.orderPay;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel("订单支付请求参数")
public class OrderPayReqParams {

    @ApiModelProperty(value = "外部订单号", required = false, hidden = true)
    private String externalOrderNumber;

    @ApiModelProperty(value = "订单号", required = true)
    private String orderNumber;

    @ApiModelProperty(value = "支付类型，1-预定，2-改签", required = false, hidden = true)
    private String payType;
}
