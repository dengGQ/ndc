package com.ndc.channel.flight.dto.createOrder;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel("订单提交中的乘机人信息")
public class CorpApiOrderPassengerParams implements Serializable {

	@ApiModelProperty(value = "生日, 格式 yyyy-MM-dd", required = true)
	private String birthday;

	@ApiModelProperty(value = "乘机人姓名", required = true)
	private String flightPassengerName;

	@ApiModelProperty(value = "乘机人证件号码", required = true)
	private String idcardCode;

	@ApiModelProperty(value = "乘机人证件类型, 1 身份证； 2 护照； 3 军官证； 4 港澳通行证； 5 台胞证； 6 其他.", required = true)
	private String idcardType;

	@ApiModelProperty(value = "旅客手机号", required = true)
	private String phone;

	@ApiModelProperty(value = "性别, 1-男 2-女", required = true)
	private String sex;

	@ApiModelProperty(value = "乘客类型", required = true)
	private Byte passengerType;

	public Byte getPassengerType() {
		return passengerType;
	}

	public void setPassengerType(Byte passengerType) {
		this.passengerType = passengerType;
	}


	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}


	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getFlightPassengerName() {
		return flightPassengerName;
	}

	public void setFlightPassengerName(String flightPassengerName) {
		this.flightPassengerName = flightPassengerName;
	}

	public String getIdcardCode() {
		return idcardCode;
	}

	public void setIdcardCode(String idcardCode) {
		this.idcardCode = idcardCode;
	}

	public String getIdcardType() {
		return idcardType;
	}

	public void setIdcardType(String idcardType) {
		this.idcardType = idcardType;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
