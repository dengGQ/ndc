package com.ndc.channel.flight.handler;

import com.ndc.channel.exception.BusinessException;
import com.ndc.channel.exception.BusinessExceptionCode;
import com.ndc.channel.flight.tools.NdcApiTools;
import com.ndc.channel.flight.xmlBean.orderRefund.request.bean.IATAOrderRetrieveRQ;
import com.ndc.channel.flight.xmlBean.orderRefund.response.bean.Error;
import com.ndc.channel.flight.xmlBean.orderRefund.response.bean.IATAOrderViewRS;
import com.ndc.channel.flight.xmlBean.orderRefund.response.bean.Order;
import com.ndc.channel.flight.xmlBean.orderRefund.response.bean.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class NdcFlightOrderRefundHandler {

    @Resource
    private NdcApiTools ndcApiTools;

    public String refundApply(String orderId) {

        IATAOrderRetrieveRQ iataOrderRetrieveRQ = new IATAOrderRetrieveRQ(orderId, "MU");

        IATAOrderViewRS refundApplyResult = ndcApiTools.refundApply(iataOrderRetrieveRQ);

        final Error error = refundApplyResult.getError();
        if (error != null) {
            throw new BusinessException(BusinessExceptionCode.FLIGHT_CHANNEL_ERROR, error.getDescText());
        }

        final Response response = refundApplyResult.getResponse();

        final Order order = response.getOrder();

        final String refundOrderID = order.getOrderID();
        return refundOrderID;
    }
}
