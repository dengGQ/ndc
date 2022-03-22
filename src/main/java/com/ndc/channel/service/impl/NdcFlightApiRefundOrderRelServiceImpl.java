package com.ndc.channel.service.impl;

import com.alibaba.fastjson.JSON;
import com.ndc.channel.entity.NdcFlightApiRefundOrderRel;
import com.ndc.channel.flight.dto.refund.RefundApplyParams;
import com.ndc.channel.flight.dto.refund.RefundChangeMoneyQueryResp;
import com.ndc.channel.mapper.NdcFlightApiRefundOrderRelMapper;
import com.ndc.channel.service.NdcFlightApiRefundOrderRelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

@Slf4j
@Service
public class NdcFlightApiRefundOrderRelServiceImpl implements NdcFlightApiRefundOrderRelService {

    @Resource
    private NdcFlightApiRefundOrderRelMapper apiRefundOrderRelMapper;

    @Override
    public void insertEntity(RefundApplyParams params, String orderId, String refundId) {

        try {
            NdcFlightApiRefundOrderRel rel = new NdcFlightApiRefundOrderRel();

            rel.setOrderId(orderId);
            rel.setRefundId(refundId);
            rel.setExternalRefundNumber(params.getExternalRefundNumber());
            rel.setAfterRefundTicketUrl(params.getAfterRefundTicketUrl());
            rel.setCreateTime(new Date());
//            rel.setRefundFee(refundMoneyData.getRefundFee());
//            rel.setRefundMoney(refundMoneyData.getRefundMoney());

            apiRefundOrderRelMapper.insertSelective(rel);
        }catch (Exception e) {
            log.error("ndc退票单订单保存失败，refundApplyParams={}, orderId={}, refundId={}", JSON.toJSONString(params), orderId, refundId);
        }
    }
}
