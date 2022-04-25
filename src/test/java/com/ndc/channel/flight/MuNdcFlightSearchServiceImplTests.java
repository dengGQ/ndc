package com.ndc.channel.flight;

import com.alibaba.fastjson.JSON;
import com.ndc.channel.ChannelApplication;
import com.ndc.channel.executor.OrderDetailDelayQueryExecutor;
import com.ndc.channel.flight.dto.MsgBody;
import com.ndc.channel.flight.dto.createOrder.CorpApiOrderFlightTicketParams;
import com.ndc.channel.flight.dto.createOrder.CorpApiOrderPassengerParams;
import com.ndc.channel.flight.dto.createOrder.FlightOrderCreateReq;
import com.ndc.channel.flight.dto.flightSearch.CorpApiFlightListDataV2;
import com.ndc.channel.flight.dto.orderDetail.NdcOrderDetailData;
import com.ndc.channel.flight.dto.orderPay.OrderPayReqParams;
import com.ndc.channel.flight.dto.refund.NdcRefundOrderSearchParams;
import com.ndc.channel.flight.dto.refund.RefundChangeMoneyQueryParams;
import com.ndc.channel.flight.dto.refund.RefundChangeMoneyQueryResp;
import com.ndc.channel.flight.dto.verifyPrice.CorpApiFlightVerifyPriceData;
import com.ndc.channel.flight.dto.verifyPrice.FeiBaApiVerifyPriceReq;
import com.ndc.channel.flight.dto.verifyPrice.FeibaApiVerificationParams;
import com.ndc.channel.flight.handler.*;
import com.ndc.channel.mapper.NdcFlightApiOrderRelMapper;
import com.ndc.channel.redis.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ChannelApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
@Slf4j
public class MuNdcFlightSearchServiceImplTests {

    @Resource
    private NdcFlightSearchHandler searchHandler;

    @Resource
    private NdcFlightVerifyPriceHandler verifyPriceHandler;

    @Resource
    private NdcFlightCreateOrderHandler createOrderHandler;

    @Resource
    private NdcFlightOrderPayHandler orderPayHandler;

    @Resource
    private NdcFlightOrderDetailHandler orderDetailHandler;
    @Resource
    private RedisUtils redisUtils;
    @Resource
    private OrderDetailDelayQueryExecutor queryExecutor;

    @Resource
    private NdcFlightOrderRefundHandler orderRefundHandler;

    @Resource
    private NdcFlightRefundOrderDetailHandler detailHandler;

    @Test
    public void ndcFlightSearch() {
        final List<CorpApiFlightListDataV2> corpApiFlightListDataV2s = searchHandler.flightSearch("2022-06-26", "SHA", "PEK");
        log.info("ndcFlightSearch={}", JSON.toJSONString(corpApiFlightListDataV2s));
    }

    @Test
    public void offerPrice() {

        FeiBaApiVerifyPriceReq req = new FeiBaApiVerifyPriceReq();
        final FeibaApiVerificationParams verificationParams = new FeibaApiVerificationParams();

        verificationParams.setFlightId("2022-06-2609301200MU5153SHAPEK");
        verificationParams.setTicketId("2022-06-2609301200MU5153SHAPEKY1@10793");

        req.setPriceVerificationParams(verificationParams);
        final CorpApiFlightVerifyPriceData corpApiFlightVerifyPriceData = verifyPriceHandler.verifyPrice(req);

        log.info("offerPrice={}", JSON.toJSONString(corpApiFlightVerifyPriceData));
    }


    @Test
    public void createOrder() {

        FlightOrderCreateReq flightOrderCreateReq = new FlightOrderCreateReq();

        CorpApiOrderFlightTicketParams ticketParams = new CorpApiOrderFlightTicketParams();
        ticketParams.setFlightId("2022-06-2609301200MU5153SHAPEK");
        ticketParams.setTicketId("2022-06-2609301200MU5153SHAPEKY1@10793");
        ticketParams.setSeatClassCode("Y");
        ticketParams.setPrice(new BigDecimal("1630"));

        flightOrderCreateReq.setTickets(Arrays.asList(ticketParams));

        CorpApiOrderPassengerParams passengerParams = new CorpApiOrderPassengerParams();
        passengerParams.setFlightPassengerName("测试");
        passengerParams.setPhone("18611312771");
        passengerParams.setBirthday("1994-08-20");
        passengerParams.setIdcardCode("411302199408201314");
        passengerParams.setIdcardType("1");
        passengerParams.setSex("1");
        flightOrderCreateReq.setPassengers(Arrays.asList(passengerParams));


//        OrderContactParams contactParams = new OrderContactParams();
//        contactParams.setName("邓国泉");
//        contactParams.setPhone("18611312771");
//        List<OrderContactParams> objects = new ArrayList<>();
//        objects.add(contactParams);
//        flightOrderCreateReq.setContacts(objects);

        createOrderHandler.createOrder(flightOrderCreateReq);
    }


    @Test
    public void orderPay() {

        final OrderPayReqParams payReqParams = new OrderPayReqParams();

        payReqParams.setOrderNumber("1022030800206114");
        orderPayHandler.orderPay(payReqParams);
    }


    @Test
    public void orderDetail() {

        // 1022030800206118 已退
        // 1022030900206462
        // 1022031000207068
        final NdcOrderDetailData ndcOrderDetailData = orderDetailHandler.orderDetail("1022031400207425");

        System.out.println(JSON.toJSONString(ndcOrderDetailData));
    }

    @Test
    public void refundMoneyQuery() {

        RefundChangeMoneyQueryParams params = new RefundChangeMoneyQueryParams();

        params.setRefundWay(Byte.valueOf("1"));
        params.setChannelOrderNumber("1022030800206118");
        params.setTicketNumList(Arrays.asList("7811157639813"));

        RefundChangeMoneyQueryResp queryResp = orderRefundHandler.refundMoneyQuery(params);
    }


    @Resource
    private NdcFlightApiOrderRelMapper orderRelMapper;

    @Test
    public void refundOrderQuery() {

        final NdcRefundOrderSearchParams searchParams = new NdcRefundOrderSearchParams(000L, null);
        final NdcOrderDetailData response = detailHandler.orderDetail(searchParams);

        System.out.println(JSON.toJSONString(response));
    }

    @Test
    public void afterCreateOrder () {

        final MsgBody msgBody = new MsgBody();
        msgBody.setMsgType("1");
        msgBody.setBusinessData("1022031100207207");
        final String s = JSON.toJSONString(msgBody);

        queryExecutor.submitTask(s, 5);
        while (true){}
    }

    @Test
    public void refundNotice() {
        String str = "{\"channelOrderNumber\":\"1022032500211472\",\"orderStatus\":\"RF\",\"refundMoney\":1870.0,\"ticketInfoList\":[{\"idCardNo\":\"42080219910522033X\",\"passengerName\":\"测试\",\"refundAmount\":1870.0,\"refundAuditingStatus\":\"501\",\"refundFee\":0.0,\"ticketNumber\":\"7811157642985\",\"ticketStatus\":\"RF\"}]}";

        final NdcOrderDetailData detailData = JSON.parseObject(str, NdcOrderDetailData.class);
        detailHandler.statusChangeNotice(detailData);
    }
}
