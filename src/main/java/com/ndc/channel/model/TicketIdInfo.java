package com.ndc.channel.model;

import io.swagger.annotations.ApiModelProperty;

public class TicketIdInfo {

    @ApiModelProperty(value="渠道id",required=true)
    private Long channelId;

    @ApiModelProperty(value="舱位",required=true)
    private String seatClassCode;

    @ApiModelProperty(value="产品类型",required=true)
    private String productType;

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public String getSeatClassCode() {
        return seatClassCode;
    }

    public void setSeatClassCode(String seatClassCode) {
        this.seatClassCode = seatClassCode;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }
}
