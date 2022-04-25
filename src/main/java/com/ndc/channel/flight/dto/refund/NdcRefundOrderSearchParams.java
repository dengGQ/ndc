package com.ndc.channel.flight.dto.refund;

import java.util.List;

public class NdcRefundOrderSearchParams {

    private Long refundOrderPrimaryKey;

    private List<String> paxIdList;

    public NdcRefundOrderSearchParams(Long refundOrderPrimaryKey, List<String> paxIdList) {
        this.refundOrderPrimaryKey = refundOrderPrimaryKey;
        this.paxIdList = paxIdList;
    }

    public Long getRefundOrderPrimaryKey() {
        return refundOrderPrimaryKey;
    }

    public void setRefundOrderPrimaryKey(Long refundOrderPrimaryKey) {
        this.refundOrderPrimaryKey = refundOrderPrimaryKey;
    }

    public List<String> getPaxIdList() {
        return paxIdList;
    }

    public void setPaxIdList(List<String> paxIdList) {
        this.paxIdList = paxIdList;
    }
}
