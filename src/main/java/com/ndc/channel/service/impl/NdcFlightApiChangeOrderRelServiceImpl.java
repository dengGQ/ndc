package com.ndc.channel.service.impl;

import com.alibaba.fastjson.JSON;
import com.ndc.channel.common.TableName;
import com.ndc.channel.common.service.IIdGeneratorService;
import com.ndc.channel.entity.NdcFlightApiChangeOrderRel;
import com.ndc.channel.flight.dto.changeBooking.ChangeBookingReqParams;
import com.ndc.channel.flight.xmlBean.changeBooking.response.bean.Order;
import com.ndc.channel.mapper.NdcFlightApiChangeOrderRelMapper;
import com.ndc.channel.service.NdcFlightApiChangeOrderRelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

@Slf4j
@Service
public class NdcFlightApiChangeOrderRelServiceImpl implements NdcFlightApiChangeOrderRelService {

    @Resource
    private NdcFlightApiChangeOrderRelMapper apiChangeOrderRelMapper;

    @Resource
    private IIdGeneratorService idGeneratorService;

    @Override
    public void insertEntity(ChangeBookingReqParams params, String orderId, Order order, BigDecimal upgradeTotalAmount) {
        try {
            NdcFlightApiChangeOrderRel rel = new NdcFlightApiChangeOrderRel();

            rel.setRelId(idGeneratorService.getPrimaryKey(TableName.NDC_FLIGHT_API_ORDER_REL));
            rel.setOrderId(orderId);
            rel.setChangeId(order.getOrderID());
            rel.setOrderItemId(order.getOrderItem().getOrderItemID());
            rel.setOwnerCode(order.getOwnerCode());
            rel.setOwnerTypeCode(order.getOwnerTypeCode());
            rel.setExternalChangeNumber(params.getExternalChangeNumber());
            rel.setAfterEndorseUrl(params.getAfterEndorseUrl());
            rel.setCreateTime(new Date());
            rel.setUpgradeTotalAmount(upgradeTotalAmount);

            apiChangeOrderRelMapper.insertSelective(rel);
        }catch (Exception e) {
            log.error("ndc改签订单保存失败，changeApplyParams={}, orderId={}, changeId={}", JSON.toJSONString(params), orderId, order.getOrderID());
        }
    }
}
