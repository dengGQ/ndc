package com.ndc.channel.flight.dto.refund;

import java.util.List;

/**
 * 退票重新申请参数
 */
public class RefundReapplyParams {

    private Long ndcRefundRelId;

    private List<String> refundAttachmentUrl;

    private String memo;

    private Byte refundReason;

    public Long getNdcRefundRelId() {
        return ndcRefundRelId;
    }

    public void setNdcRefundRelId(Long ndcRefundRelId) {
        this.ndcRefundRelId = ndcRefundRelId;
    }

    public List<String> getRefundAttachmentUrl() {
        return refundAttachmentUrl;
    }

    public void setRefundAttachmentUrl(List<String> refundAttachmentUrl) {
        this.refundAttachmentUrl = refundAttachmentUrl;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Byte getRefundReason() {
        return refundReason;
    }

    public void setRefundReason(Byte refundReason) {
        this.refundReason = refundReason;
    }
}
