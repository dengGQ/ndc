package com.ndc.channel.flight.dto.refund;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class RefundApplyParams {
    @ApiModelProperty(value = "退票回调通知URL", required = true)
    private String afterRefundTicketUrl;

    @ApiModelProperty(value = "外部退票单号", required = true)
    private String externalRefundNumber;

    @ApiModelProperty(value = "退票备注", required = false)
    private String memo;

    @ApiModelProperty(value = "原订单号", required = true)
    private String orderNumber;

    @ApiModelProperty(value = "退票附件地址", required = false)
    private List<String> refundAttachmentUrl;

    @ApiModelProperty(value = "退票说明", required = false)
    private String refundExplain;

    @ApiModelProperty(value = "退票原因：1-行程变更，定错等主观原因；2-航司，机场或天气原因造成的航班延误或取消；" +
            "3-个人健康原因；4-升舱/名错换开,若有新票号请备注; 5-其他原因; 6-重购客票退票", required = true)
    private Byte refundReason;

    @ApiModelProperty(value = "退票方式：1-自愿退票；2-非自愿退票", required = true)
    private Byte refundWay;

    @ApiModelProperty(value = "乘客信息", required = true)
    private List<RefundApplyPassengerParams> refundPassengerList;

    public String getAfterRefundTicketUrl() {
        return afterRefundTicketUrl;
    }

    public void setAfterRefundTicketUrl(String afterRefundTicketUrl) {
        this.afterRefundTicketUrl = afterRefundTicketUrl;
    }

    public String getExternalRefundNumber() {
        return externalRefundNumber;
    }

    public void setExternalRefundNumber(String externalRefundNumber) {
        this.externalRefundNumber = externalRefundNumber;
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

    public List<String> getRefundAttachmentUrl() {
        return refundAttachmentUrl;
    }

    public void setRefundAttachmentUrl(List<String> refundAttachmentUrl) {
        this.refundAttachmentUrl = refundAttachmentUrl;
    }

    public String getRefundExplain() {
        return refundExplain;
    }

    public void setRefundExplain(String refundExplain) {
        this.refundExplain = refundExplain;
    }

    public Byte getRefundReason() {
        return refundReason;
    }

    public void setRefundReason(Byte refundReason) {
        this.refundReason = refundReason;
    }

    public Byte getRefundWay() {
        return refundWay;
    }

    public void setRefundWay(Byte refundWay) {
        this.refundWay = refundWay;
    }

    public List<RefundApplyPassengerParams> getRefundPassengerList() {
        return refundPassengerList;
    }

    public void setRefundPassengerList(List<RefundApplyPassengerParams> refundPassengerList) {
        this.refundPassengerList = refundPassengerList;
    }
}
