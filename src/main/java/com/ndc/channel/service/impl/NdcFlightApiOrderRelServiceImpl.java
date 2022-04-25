package com.ndc.channel.service.impl;

import com.alibaba.fastjson.JSON;
import com.ndc.channel.common.TableName;
import com.ndc.channel.common.service.IIdGeneratorService;
import com.ndc.channel.entity.NdcFlightApiOrderRel;
import com.ndc.channel.flight.dto.createOrder.CorpApiFlightOrderCreateData;
import com.ndc.channel.flight.dto.createOrder.FlightOrderCreateReq;
import com.ndc.channel.flight.dto.flightSearch.CorpApiTicketData;
import com.ndc.channel.mapper.NdcFlightApiOrderRelMapper;
import com.ndc.channel.service.NdcFlightApiOrderRelService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
@Service
public class NdcFlightApiOrderRelServiceImpl implements NdcFlightApiOrderRelService {

    @Resource
    private NdcFlightApiOrderRelMapper apiOrderRelMapper;

    @Resource
    private IIdGeneratorService idGeneratorService;

    @Override
    public void insertEntity(FlightOrderCreateReq orderCreateReq, CorpApiFlightOrderCreateData orderCreateData, CorpApiTicketData ticketData) {

        try {
            NdcFlightApiOrderRel rel = new NdcFlightApiOrderRel();

            rel.setRelId(idGeneratorService.getPrimaryKey(TableName.NDC_FLIGHT_API_ORDER_REL));
            rel.setOrderId(orderCreateData.getOrderNumber());
            rel.setOrderItemId(orderCreateData.getOrderItemId());
            rel.setAfterTicketUrl(orderCreateReq.getAfterTicketUrl());
            rel.setExternalOrderNumber(orderCreateReq.getExternalOrderNumber());
            rel.setCreateTime(new Date());
            rel.setTotalAmount(orderCreateData.getSettlementMoney());
            rel.setTicketPrice(orderCreateData.getTicketPrice());
            if (CollectionUtils.isNotEmpty(ticketData.getRightsList())) {
                rel.setProductRights(ticketData.getRightsList().stream().collect(Collectors.joining(",")));
            }

            rel.setProductConstraint(ticketData.getProductNotice());

            rel.setOwnerCode(orderCreateData.getOwnerCode());
            rel.setOwnerTypeCode(orderCreateData.getOwnerTypeCode());
            rel.setRequestId(orderCreateData.getRequestId());

            apiOrderRelMapper.insertSelective(rel);
        }catch (Exception e) {
            log.error("ndc机票订单保存失败，req="+JSON.toJSONString(orderCreateReq)+", resp="+JSON.toJSONString(orderCreateData), e);
        }
    }
}
