package com.ndc.channel.service;

import com.ndc.channel.flight.dto.changeBooking.ChangeBookingReqParams;
import com.ndc.channel.flight.dto.refund.RefundApplyParams;
import com.ndc.channel.flight.dto.refund.RefundChangeMoneyQueryResp;
import com.ndc.channel.flight.xmlBean.changeBooking.response.bean.Order;

import java.math.BigDecimal;

public interface NdcFlightApiChangeOrderRelService {

    void insertEntity(ChangeBookingReqParams params, String orderId, Order order, BigDecimal upgradeTotalAmount);
}
