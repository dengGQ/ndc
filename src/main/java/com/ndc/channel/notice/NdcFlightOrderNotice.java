package com.ndc.channel.notice;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ndc.channel.entity.NdcFlightApiOrderRel;
import com.ndc.channel.enumtype.BusinessEnum;
import com.ndc.channel.exception.BusinessException;
import com.ndc.channel.exception.BusinessExceptionCode;
import com.ndc.channel.executor.OrderDetailDelayQueryExecutor;
import com.ndc.channel.flight.dto.MsgBody;
import com.ndc.channel.flight.dto.createOrder.FlightOrderNoticeData;
import com.ndc.channel.flight.dto.createOrder.FlightOrderPassengerData;
import com.ndc.channel.flight.dto.orderDetail.NdcOrderDetailData;
import com.ndc.channel.flight.dto.orderDetail.OrderTicketInfo;
import com.ndc.channel.flight.handler.NdcFlightOrderDetailHandler;
import com.ndc.channel.flight.handler.NdcFlightOrderRefundHandler;
import com.ndc.channel.flight.handler.NdcOrderDetailHandler;
import com.ndc.channel.http.ChannelOKHttpService;
import com.ndc.channel.mapper.NdcFlightApiOrderRelMapper;
import com.ndc.channel.mapper.NdcFlightApiRefundOrderRelMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
public class NdcFlightOrderNotice {

    @Resource
    private OrderDetailDelayQueryExecutor orderDetailDelayQueryExecutor;

    @Resource
    private Map<String, NdcOrderDetailHandler> orderDetailHandlerMap;

    public static final String ORDER_DETAIL_HANDLER_NAME_PREFIX = "orderDetailHandler";

    public void orderStatusProcess(String msgBody) {

        final MsgBody mb = JSONObject.parseObject(msgBody, MsgBody.class);
        final String msgType = mb.getMsgType();
        final String businessNumber = mb.getBusinessNumber();

        final NdcOrderDetailHandler detailHandler = orderDetailHandlerMap.get( ORDER_DETAIL_HANDLER_NAME_PREFIX + msgType);
        NdcOrderDetailData ndcOrderDetailData = detailHandler.orderDetail(businessNumber);

        log.info("NDC订单明细查询结果={}", JSON.toJSONString(ndcOrderDetailData));

        if (detailHandler.checkStatusComplete(ndcOrderDetailData)) {

            detailHandler.statusChangeNotice(ndcOrderDetailData);
        }else {

            orderDetailDelayQueryExecutor.submitTask(msgBody, 60*10);
        }
    }
}
