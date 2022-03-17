package com.ndc.channel.flight.handler;

import com.alibaba.fastjson.JSON;
import com.ndc.channel.entity.NdcFlightApiOrderRel;
import com.ndc.channel.exception.BusinessException;
import com.ndc.channel.exception.BusinessExceptionCode;
import com.ndc.channel.executor.OrderDetailDelayQueryExecutor;
import com.ndc.channel.flight.dto.MsgBody;
import com.ndc.channel.flight.dto.orderPay.OrderPayReqParams;
import com.ndc.channel.flight.tools.NdcApiTools;
import com.ndc.channel.flight.xmlBean.orderPay.request.bean.*;
import com.ndc.channel.flight.xmlBean.orderPay.response.bean.Error;
import com.ndc.channel.flight.xmlBean.orderPay.response.bean.IATAOrderViewRS;
import com.ndc.channel.mapper.NdcAccountInfoMapper;
import com.ndc.channel.mapper.NdcFlightApiOrderRelMapper;
import com.ndc.channel.model.NdcAccountInfoData;
import com.ndc.channel.redis.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.UUID;

@Slf4j
@Component
public class NdcFlightOrderPayHandler {

    @Resource
    private NdcApiTools ndcApiTools;

    @Resource
    private NdcFlightApiOrderRelMapper orderRelMapper;

    @Resource
    private NdcAccountInfoMapper accountInfoMapper;

    @Resource
    private OrderDetailDelayQueryExecutor detailDelayQueryExecutor;

    public Boolean orderPay(OrderPayReqParams orderPayReqParams) {

        NdcFlightApiOrderRel ndcFlightApiOrderRel = orderRelMapper.selectByOrderId(orderPayReqParams.getOrderNumber());

        if (ndcFlightApiOrderRel == null){
            throw new BusinessException(BusinessExceptionCode.REQUEST_PARAM_ERROR, "订单不存在！channelOrderNumber="+orderPayReqParams.getOrderNumber());
        }

        NdcAccountInfoData accountInfo = accountInfoMapper.selectByNdcCode("mu_ndc");

        IATAOrderChangeRQ iataOrderChangeRQ = new IATAOrderChangeRQ();

        final Request request = new Request();

        final Order order = new Order();
        order.setOrderID(orderPayReqParams.getOrderNumber());
        order.setOwnerCode(ndcFlightApiOrderRel.getOwnerCode());
        order.setOwnerTypeCode(ndcFlightApiOrderRel.getOwnerTypeCode());
        request.setOrder(order);

        final PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setPaymentInfoID(UUID.randomUUID().toString());
        paymentInfo.setOrderItemRefID(ndcFlightApiOrderRel.getOrderItemId());
        paymentInfo.setTypeCode("PT");

        final Amount amount = new Amount();
        amount.setCurCode("CNY");
        amount.setValue(ndcFlightApiOrderRel.getTotalAmount().toPlainString());
        paymentInfo.setAmount(amount);

        final PaymentMethod paymentMethod = new PaymentMethod();
        final BankTransfer bankTransfer = new BankTransfer();
        bankTransfer.setAccountTypeText("WITHHOLDING");
        bankTransfer.setBankAccountID(accountInfo.getBankAccountID());
        paymentMethod.setBankTransfer(bankTransfer);
        paymentInfo.setPaymentMethod(paymentMethod);

        request.setPaymentInfo(paymentInfo);

        iataOrderChangeRQ.setRequest(request);

        IATAOrderViewRS iataOrderViewRS = ndcApiTools.orderPay(accountInfo, iataOrderChangeRQ);
        final Error error = iataOrderViewRS.getError();
        if (error != null) {
            log.error("ndc支付失败，channelOrderNumber={}, failReason={}", orderPayReqParams.getOrderNumber(), error.getDescText());
            throw new BusinessException(BusinessExceptionCode.REQUEST_PARAM_ERROR, "支付失败！failReason="+error.getDescText());
        }

        // 发布异步查询订单详情任务
        detailDelayQueryExecutor.submitTask(JSON.toJSONString(new MsgBody(ndcFlightApiOrderRel.getOrderId(), orderPayReqParams.getPayType())), 60L);
        return true;
    }
}
