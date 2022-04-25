package com.ndc.channel.service.impl;

import com.alibaba.fastjson.JSON;
import com.ndc.channel.common.TableName;
import com.ndc.channel.common.service.IIdGeneratorService;
import com.ndc.channel.entity.NdcFlightApiRefundOrderRel;
import com.ndc.channel.flight.dto.refund.RefundApplyParams;
import com.ndc.channel.mapper.NdcFlightApiRefundOrderRelMapper;
import com.ndc.channel.service.NdcFlightApiRefundOrderRelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Slf4j
@Service
public class NdcFlightApiRefundOrderRelServiceImpl implements NdcFlightApiRefundOrderRelService {

    @Resource
    private NdcFlightApiRefundOrderRelMapper apiRefundOrderRelMapper;

    @Resource
    private IIdGeneratorService idGeneratorService;

    @Override
    public Long insertEntity(RefundApplyParams params, String orderId, String requestId) {

        NdcFlightApiRefundOrderRel rel = new NdcFlightApiRefundOrderRel();
        Long primaryKey = idGeneratorService.getPrimaryKey(TableName.NDC_FLIGHT_API_REFUND_ORDER_REL);

        try {

            rel.setRelId(primaryKey);
            rel.setOrderId(orderId);
            rel.setExternalRefundNumber(params.getExternalRefundNumber());
            rel.setAfterRefundTicketUrl(params.getAfterRefundTicketUrl());
            rel.setCreateTime(new Date());
            rel.setRequestId(requestId);
            apiRefundOrderRelMapper.insertSelective(rel);
        }catch (Exception e) {
            log.info("ndc退票单订单保存失败，refundApplyParams={}, orderId={}", JSON.toJSONString(params), orderId);
            log.error("ndc退票单订单保存失败", e);
        }

        return primaryKey;
    }
}
