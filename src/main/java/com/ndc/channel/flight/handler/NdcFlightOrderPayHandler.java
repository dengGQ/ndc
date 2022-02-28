package com.ndc.channel.flight.handler;

import com.ndc.channel.exception.BusinessException;
import com.ndc.channel.exception.BusinessExceptionCode;
import com.ndc.channel.flight.dto.orderPay.OrderPayReqParams;
import com.ndc.channel.flight.tools.NdcApiTools;
import com.ndc.channel.flight.xmlBean.orderPay.request.bean.*;
import com.ndc.channel.flight.xmlBean.orderPay.response.bean.Error;
import com.ndc.channel.flight.xmlBean.orderPay.response.bean.IATAOrderViewRS;
import com.ndc.channel.redis.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.UUID;

@Slf4j
@Component
public class NdcFlightOrderPayHandler {

    @Resource
    private NdcApiTools ndcApiTools;

    @Resource
    private RedisUtils redisUtils;

    public Boolean orderPay(OrderPayReqParams orderPayReqParams) {

        IATAOrderChangeRQ iataOrderChangeRQ = new IATAOrderChangeRQ();

        final Request request = new Request();

        final Order order = new Order();
        order.setOrderID(orderPayReqParams.getGroupOrderNumber());
        order.setOwnerCode("MU");
        order.setOwnerTypeCode(null);
        request.setOrder(order);

        final PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setPaymentInfoID(UUID.randomUUID().toString());
        paymentInfo.setOrderItemRefID(orderPayReqParams.getOrderNumber());
        paymentInfo.setTypeCode("PT");

        final PaymentMethod paymentMethod = new PaymentMethod();

        final BankTransfer bankTransfer = new BankTransfer();
        bankTransfer.setAccountTypeText("WITHHOLDING");
        paymentMethod.setBankTransfer(bankTransfer);
        paymentInfo.setPaymentMethod(paymentMethod);

        request.setPaymentInfo(paymentInfo);

        iataOrderChangeRQ.setRequest(request);

        IATAOrderViewRS iataOrderViewRS = ndcApiTools.orderPay(iataOrderChangeRQ);
        final Error error = iataOrderViewRS.getError();
        if (error != null) {
            log.error("ndc支付失败，channelOrderNumber={}", orderPayReqParams.getOrderNumber());
            throw new BusinessException(BusinessExceptionCode.REQUEST_PARAM_ERROR, "支付失败！");
        }
        return true;
    }
}
