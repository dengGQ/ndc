package com.ndc.channel.flight.dto.createOrder;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel("订单提交中的联系人信息")
public class OrderContactParams implements Serializable {
	
	@ApiModelProperty(value="联系人人姓名",required=true)
	private String name;
	
	@ApiModelProperty(value="联系人电话",required=true)
	private String phone;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
