package com.ndc.channel.service;

import com.ndc.channel.flight.dto.createOrder.CorpApiFlightOrderCreateData;
import com.ndc.channel.flight.dto.createOrder.FlightOrderCreateReq;
import com.ndc.channel.flight.dto.refund.RefundApplyParams;

public interface NdcFlightApiRefundOrderRelService {

    void insertEntity(RefundApplyParams params, String orderId, String refundId);
}
