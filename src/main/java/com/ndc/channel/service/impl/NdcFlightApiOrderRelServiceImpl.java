package com.ndc.channel.service.impl;

import com.alibaba.fastjson.JSON;
import com.ndc.channel.entity.NdcFlightApiOrderRel;
import com.ndc.channel.flight.dto.createOrder.CorpApiFlightOrderCreateData;
import com.ndc.channel.flight.dto.createOrder.FlightOrderCreateReq;
import com.ndc.channel.mapper.NdcFlightApiOrderRelMapper;
import com.ndc.channel.service.NdcFlightApiOrderRelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

@Slf4j
@Service
public class NdcFlightApiOrderRelServiceImpl implements NdcFlightApiOrderRelService {

    @Resource
    private NdcFlightApiOrderRelMapper apiOrderRelMapper;

    @Override
    public void insertEntity(FlightOrderCreateReq orderCreateReq, CorpApiFlightOrderCreateData orderCreateData) {

        try {
            NdcFlightApiOrderRel rel = new NdcFlightApiOrderRel();

            rel.setOrderId(orderCreateData.getOrderNumber());
            rel.setOrderItemId(orderCreateData.getOrderItemId());
            rel.setAfterTicketUrl(orderCreateReq.getAfterTicketUrl());
            rel.setExternalOrderNumber(orderCreateReq.getExternalOrderNumber());
            rel.setCreateTime(new Date());
            rel.setTotalAmount(orderCreateData.getSettlementMoney());
            rel.setTicketPrice(orderCreateData.getTicketPrice());

            rel.setOwnerCode(orderCreateData.getOwnerCode());
            rel.setOwnerTypeCode(orderCreateData.getOwnerTypeCode());

            apiOrderRelMapper.insertSelective(rel);
        }catch (Exception e) {
            log.error("ndc机票订单保存失败，req={}, resp={}", JSON.toJSONString(orderCreateReq), JSON.toJSONString(orderCreateData));
        }
    }
}
