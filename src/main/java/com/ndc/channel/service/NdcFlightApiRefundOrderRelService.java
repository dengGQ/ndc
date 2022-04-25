package com.ndc.channel.service;

import com.ndc.channel.flight.dto.refund.RefundApplyParams;

public interface NdcFlightApiRefundOrderRelService {

    Long insertEntity(RefundApplyParams params, String orderId, String requestId);
}
