package com.ndc.channel.flight.dto.refund;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class RefundChangeMoneyQueryParams {

    @ApiModelProperty(value = "订单号",required = true)
    private String channelOrderNumber;

    @ApiModelProperty(value = "票号",required = true)
    private List<String> ticketNumList;

    @ApiModelProperty(value = "查询类型（0是退票手续费查询， 1是改期手续费查询）",required = true)
    private Integer queryType;

    @ApiModelProperty(value = "申请类型(0是非自愿)",required = true)
    private Integer applyType;

    @ApiModelProperty(value = "退票方式：1-自愿退票；2-非自愿退票,航变；3-非自愿，病退;4-非自愿,升舱或换开；5-其他", required = true)
    private Byte refundWay;

    public String getChannelOrderNumber() {
        return channelOrderNumber;
    }

    public void setChannelOrderNumber(String channelOrderNumber) {
        this.channelOrderNumber = channelOrderNumber;
    }

    public List<String> getTicketNumList() {
        return ticketNumList;
    }

    public void setTicketNumList(List<String> ticketNumList) {
        this.ticketNumList = ticketNumList;
    }

    public Integer getQueryType() {
        return queryType;
    }

    public void setQueryType(Integer queryType) {
        this.queryType = queryType;
    }

    public Integer getApplyType() {
        return applyType;
    }

    public void setApplyType(Integer applyType) {
        this.applyType = applyType;
    }

    public Byte getRefundWay() {
        return refundWay;
    }

    public void setRefundWay(Byte refundWay) {
        this.refundWay = refundWay;
    }
}
