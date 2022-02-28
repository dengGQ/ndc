package com.ndc.channel.flight.dto.orderPay;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel("订单支付请求参数")
public class OrderPayReqParams {

    private String groupOrderNumber;

    private String orderNumber;
}
