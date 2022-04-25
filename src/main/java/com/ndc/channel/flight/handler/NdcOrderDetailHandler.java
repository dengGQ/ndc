package com.ndc.channel.flight.handler;

import com.ndc.channel.flight.dto.orderDetail.NdcOrderDetailData;

public interface NdcOrderDetailHandler<P> {

    P resolveParams(Object params);

    NdcOrderDetailData orderDetail(P params);

    Boolean checkStatusComplete(NdcOrderDetailData detailData);

    void statusChangeNotice(NdcOrderDetailData ndcOrderDetailData);
}
