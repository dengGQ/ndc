package com.ndc.channel.flight.handler;

import com.ndc.channel.flight.dto.orderDetail.NdcOrderDetailData;

public interface NdcOrderDetailHandler {

    NdcOrderDetailData orderDetail(String businessId);

    Boolean checkStatusComplete(NdcOrderDetailData detailData);

    void statusChangeNotice(NdcOrderDetailData ndcOrderDetailData);
}
