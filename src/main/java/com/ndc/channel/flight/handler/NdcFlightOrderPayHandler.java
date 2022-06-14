package com.ndc.channel.flight.handler;

import com.alibaba.fastjson.JSON;
import com.ndc.channel.entity.NdcFlightApiOrderRel;
import com.ndc.channel.enumtype.BusinessEnum;
import com.ndc.channel.exception.BusinessException;
import com.ndc.channel.exception.BusinessExceptionCode;
import com.ndc.channel.executor.OrderDetailDelayQueryExecutor;
import com.ndc.channel.flight.dto.MsgBody;
import com.ndc.channel.flight.dto.orderPay.OrderPayReqParams;
import com.ndc.channel.flight.tools.NdcApiTools;
import com.ndc.channel.flight.xmlBean.orderPay.request.bean.*;
import com.ndc.channel.flight.xmlBean.orderPay.response.bean.Error;
import com.ndc.channel.flight.xmlBean.orderPay.response.bean.IATAOrderViewRS;
import com.ndc.channel.flight.xmlBean.orderPay.response.bean.Response;
import com.ndc.channel.mapper.NdcAccountInfoMapper;
import com.ndc.channel.mapper.NdcFlightApiOrderRelMapper;
import com.ndc.channel.model.NdcAccountInfoData;
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
    private NdcFlightApiOrderRelMapper orderRelMapper;

    @Resource
    private NdcAccountInfoMapper accountInfoMapper;

    @Resource
    private OrderDetailDelayQueryExecutor detailDelayQueryExecutor;

    public Boolean orderPay(OrderPayReqParams orderPayReqParams) {
        final String orderNumber = orderPayReqParams.getOrderNumber();
        final NdcFlightApiOrderRel ndcFlightApiOrderRel = orderRelMapper.selectByOrderId(orderNumber);

        if (ndcFlightApiOrderRel == null){
            throw new BusinessException(BusinessExceptionCode.REQUEST_PARAM_ERROR, "订单不存在！channelOrderNumber="+ orderNumber);
        }

        final NdcAccountInfoData accountInfo = accountInfoMapper.selectByNdcCode("mu_ndc");

        IATAOrderChangeRQ orderPayRequestParams = createNdcOrderPayRequestParams(orderPayReqParams, ndcFlightApiOrderRel, accountInfo);

        com.ndc.channel.flight.xmlBean.orderPay.response.bean.PaymentInfo rsPaymentInfo = null;
        int loopNum = 0;
        do{

            if (loopNum > 0) {
                try {
                    // 一分钟后重试
                    Thread.sleep(60*1000);
                } catch (InterruptedException e) {
                    log.error("线程中断异常", e);
                }
            }

            IATAOrderViewRS iataOrderViewRS = doRequest(orderNumber, accountInfo, orderPayRequestParams);

            rsPaymentInfo = iataOrderViewRS.getPaymentInfo();

            loopNum++;
        }while (rsPaymentInfo == null || (rsPaymentInfo != null && BusinessEnum.PaymentStatusCode.getAllRetryRequiredStatus().contains(rsPaymentInfo.getPaymentStatusCode())));

        // 发布异步查询订单详情任务
        detailDelayQueryExecutor.submitTask(JSON.toJSONString(new MsgBody(ndcFlightApiOrderRel.getOrderId(), orderPayReqParams.getPayType())), 60L);
        return true;
    }

    private IATAOrderViewRS doRequest(String orderNumber, NdcAccountInfoData accountInfo, IATAOrderChangeRQ orderPayRequestParams) {
        IATAOrderViewRS iataOrderViewRS = ndcApiTools.orderPay(accountInfo, orderPayRequestParams);
        final Error error = iataOrderViewRS.getError();
        if (error != null) {
            log.error("ndc支付失败，channelOrderNumber={}, failReason={}", orderNumber, error.getDescText());
            throw new BusinessException(BusinessExceptionCode.REQUEST_PARAM_ERROR, "支付失败！failReason="+error.getDescText());
        }

        Response response = iataOrderViewRS.getResponse();

        String statusCode = response.getOrder().getOrderItem().getStatusCode();

        if (BusinessEnum.OrderItemStatusCode.CANCELLEDBYCUSTOMER.name().equals(statusCode)) {
            log.error("ndc支付失败，channelOrderNumber={}, failReason={}", orderNumber, error.getDescText());
            throw new BusinessException(BusinessExceptionCode.REQUEST_PARAM_ERROR, "支付失败！"+BusinessEnum.OrderItemStatusCode.CANCELLEDBYCUSTOMER.getLabel());
        }

        return iataOrderViewRS;
    }

    private IATAOrderChangeRQ createNdcOrderPayRequestParams(OrderPayReqParams orderPayReqParams, NdcFlightApiOrderRel ndcFlightApiOrderRel, NdcAccountInfoData accountInfo) {
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

        return iataOrderChangeRQ;
    }
}
