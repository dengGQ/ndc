package com.ndc.channel.flight.dto.changeBooking;

import com.ndc.channel.flight.dto.createOrder.CorpApiOrderPassengerParams;
import com.ndc.channel.flight.dto.createOrder.OrderContactParams;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

public class ChangeBookingReqParams {

    @ApiModelProperty(value = "改签回调通知地址", required = true)
    private String afterEndorseUrl;

    @ApiModelProperty(value = "改签附件地址", required = false)
    private List<String> changeAttachmentUrl;

    @ApiModelProperty(value = "改签说明", required = false)
    private String changeExplain;

    @ApiModelProperty(value = "改签原因： 1-行程变更，定错等主观原因；2-航司，机场或天气原因造成的航班延误或取消；3-个人健康原因", required = true)
    private Byte changeReason;

    @ApiModelProperty(value = "改签方式：1-自愿改签；2-非自愿改签", required = true)
    private Byte changeWay;

    @ApiModelProperty(value = "外部改签单号", required = true)
    private String externalChangeNumber;

    @ApiModelProperty(value = "改签后航班编号", required = true)
    private String flightId;

    @ApiModelProperty(value = "改签后机票编号", required = true)
    private String ticketId;

    @ApiModelProperty(value = "改签备注", required = false)
    private String memo;

    @ApiModelProperty(value = "原订单编号", required = true)
    private String orderNumber;

    @ApiModelProperty(value = "改签后的票价", required = true)
    private BigDecimal ticketPrice;

    @ApiModelProperty(value = "改签乘机人信息", required = true)
    private List<CorpApiOrderPassengerParams> passengers;

    @ApiModelProperty(value = "联系人信息", required = true)
    private List<OrderContactParams> contacts;

    @ApiModelProperty(value = "渠道原订单编号", required = true)
    private String channelOrderNumber;

    @ApiModelProperty(value = "改签单号", required = true)
    private String changeNumber;

    public String getAfterEndorseUrl() {
        return afterEndorseUrl;
    }

    public void setAfterEndorseUrl(String afterEndorseUrl) {
        this.afterEndorseUrl = afterEndorseUrl;
    }

    public List<String> getChangeAttachmentUrl() {
        return changeAttachmentUrl;
    }

    public void setChangeAttachmentUrl(List<String> changeAttachmentUrl) {
        this.changeAttachmentUrl = changeAttachmentUrl;
    }

    public String getChangeExplain() {
        return changeExplain;
    }

    public void setChangeExplain(String changeExplain) {
        this.changeExplain = changeExplain;
    }

    public Byte getChangeReason() {
        return changeReason;
    }

    public void setChangeReason(Byte changeReason) {
        this.changeReason = changeReason;
    }

    public Byte getChangeWay() {
        return changeWay;
    }

    public void setChangeWay(Byte changeWay) {
        this.changeWay = changeWay;
    }

    public String getExternalChangeNumber() {
        return externalChangeNumber;
    }

    public void setExternalChangeNumber(String externalChangeNumber) {
        this.externalChangeNumber = externalChangeNumber;
    }

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public List<CorpApiOrderPassengerParams> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<CorpApiOrderPassengerParams> passengers) {
        this.passengers = passengers;
    }

    public List<OrderContactParams> getContacts() {
        return contacts;
    }

    public void setContacts(List<OrderContactParams> contacts) {
        this.contacts = contacts;
    }

    public String getChannelOrderNumber() {
        return channelOrderNumber;
    }

    public void setChannelOrderNumber(String channelOrderNumber) {
        this.channelOrderNumber = channelOrderNumber;
    }

    public String getChangeNumber() {
        return changeNumber;
    }

    public void setChangeNumber(String changeNumber) {
        this.changeNumber = changeNumber;
    }
}
