package com.ndc.channel.flight;

import com.alibaba.fastjson.JSON;
import com.ndc.channel.ChannelApplication;
import com.ndc.channel.entity.NdcFlightApiOrderRel;
import com.ndc.channel.entity.NdcFlightApiRefundOrderRel;
import com.ndc.channel.flight.dto.createOrder.CorpApiFlightOrderCreateData;
import com.ndc.channel.flight.dto.createOrder.CorpApiOrderFlightTicketParams;
import com.ndc.channel.flight.dto.createOrder.CorpApiOrderPassengerParams;
import com.ndc.channel.flight.dto.createOrder.FlightOrderCreateReq;
import com.ndc.channel.flight.dto.flightSearch.CorpApiTicketData;
import com.ndc.channel.flight.dto.orderDetail.NdcOrderDetailData;
import com.ndc.channel.flight.dto.orderPay.OrderPayReqParams;
import com.ndc.channel.flight.dto.refund.RefundChangeMoneyQueryParams;
import com.ndc.channel.flight.dto.refund.RefundChangeMoneyQueryResp;
import com.ndc.channel.flight.dto.refund.RefundReapplyParams;
import com.ndc.channel.flight.handler.*;
import com.ndc.channel.mapper.NdcFlightApiOrderRelMapper;
import com.ndc.channel.mapper.NdcFlightApiRefundOrderRelMapper;
import com.ndc.channel.redis.RedisKeyConstants;
import com.ndc.channel.redis.RedisUtils;
import com.ndc.channel.service.NdcFlightApiOrderRelService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ChannelApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
@Slf4j
public class MuNdcFlightApiOrderServiceImplTests {

    @Resource
    NdcFlightApiOrderRelService orderRelService;

    @Resource
    RedisUtils redisUtils;

    @Resource
    private NdcFlightApiOrderRelMapper orderRelMapper;

    @Resource
    private NdcFlightApiRefundOrderRelMapper refundOrderRelMapper;

    @Resource
    private NdcFlightRefundAmountSearchHandler refundAmountSearchHandler;

    @Resource
    private NdcFlightRefundOrderDetailHandler detailHandler;

    @Resource
    private NdcFlightCreateOrderHandler createOrderHandler;

    @Resource
    private NdcFlightOrderPayHandler orderPayHandler;

    @Resource
    private NdcFlightOrderDetailHandler orderDetailHandler;

    @Test
    public void insertEntity() {

        String s = "{\"afterTicketUrl\":\"http://localhost:8084/feiba/channel/callback/createOrder\",\"contacts\":[{}],\"externalOrderNumber\":\"128616493660711\",\"flightType\":1,\"passengers\":[{\"birthday\":\"1991-05-22\",\"flightPassengerName\":\"测试是\",\"idcardCode\":\"42080219910522033X\",\"idcardType\":\"1\",\"passengerType\":1,\"phone\":\"18357486995\",\"sex\":\"1\"}],\"tickets\":[{\"flightId\":\"2022-06-2808051030MU5301SHACAN\",\"flightType\":\"1\",\"price\":1780.00,\"seatClassCode\":\"Y\",\"ticketId\":\"2022-06-2808051030MU5301SHACANY1@10168\"}]}";

        String s1 = "{\"orderItemId\":\"1122032400188599\",\"orderNumber\":\"1022032400210853\",\"ownerCode\":\"MU\",\"ownerTypeCode\":\"ORA\",\"pnrNo\":\"PF84QD\",\"settlementMoney\":1870.0,\"ticketPrice\":1780.0,\"tktl\":\"2022-03-24T14:24:31.000+08:00\"}";

        final FlightOrderCreateReq orderCreateReq = JSON.parseObject(s, FlightOrderCreateReq.class);

        final CorpApiFlightOrderCreateData orderCreateData = JSON.parseObject(s1, CorpApiFlightOrderCreateData.class);

        CorpApiTicketData ticketData = redisUtils.hGet(RedisKeyConstants.getRedisTicketDataCacheKey("2022-08-1206451415MU2335PVGLXA"), "2022-08-1206451415MU2335PVGLXAE1@10015", CorpApiTicketData.class);

        orderRelService.insertEntity(orderCreateReq, orderCreateData, ticketData);
    }

    @Resource
    private NdcFlightRefundReapplyHandler refundReapplyHandler;


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
        params.setChannelOrderNumber("1022042500220837");
        params.setTicketNumList(Arrays.asList("7811157639813"));

        RefundChangeMoneyQueryResp queryResp = refundAmountSearchHandler.refundMoneyQuery(params);
    }

    /**
     * 非自愿转自愿退票金额查询
     */
    @Test
    public void refundMoneyQuery_() {

        RefundChangeMoneyQueryParams params = new RefundChangeMoneyQueryParams();

        params.setRefundWay(Byte.valueOf("1"));
        params.setQueryType(2);
        params.setChannelOrderNumber("1022042500220837");
        params.setTicketNumList(Arrays.asList("7811157648859"));
        params.setNdcRefundRelId(57L);

        RefundChangeMoneyQueryResp queryResp = refundAmountSearchHandler.refundMoneyQuery(params);

        System.out.println(JSON.toJSONString(queryResp));
    }

    @Test
    public void reapplyRefund() {

        RefundReapplyParams refundReapplyParams = new RefundReapplyParams();
        refundReapplyParams.setNdcRefundRelId(57L);
        refundReapplyParams.setRefundReason(Byte.valueOf("1"));
        refundReapplyParams.setMemo("非自愿转自愿退票");
//        refundReapplyParams.setRefundAttachmentUrl(Arrays.asList("https://www.zixi.org/static/uploads/2022/4/202204240001035537.jpg"));

        final boolean b = refundReapplyHandler.refundReapply(refundReapplyParams);
        System.out.println("-----------");
    }

    @Test
    public void refundOrderQuery() {

//        final NdcRefundOrderSearchParams searchParams = new NdcRefundOrderSearchParams(000L, null);
//        final NdcOrderDetailData response = detailHandler.orderDetail(searchParams);

        NdcFlightApiRefundOrderRel refundOrderRel = refundOrderRelMapper.selectByPrimaryKey(57L);
        String refundId = refundOrderRel.getRefundId();

        NdcFlightApiOrderRel orderRel = orderRelMapper.selectByOrderId(refundOrderRel.getOrderId());

        final NdcOrderDetailData detailData = detailHandler.refundOrderDetail(orderRel, refundOrderRel);

        System.out.println(JSON.toJSONString(detailData));
    }

    @Test
    public void zaddSet(){


        ExecutorService executorService = Executors.newFixedThreadPool(10);
        IntStream.range(5, 25).forEach(i -> {

            final ExecutorTask executorTask = new ExecutorTask(String.valueOf(i));

            executorService.submit(executorTask);
        });

        executorService.shutdown();

        while (!executorService.isTerminated()) {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class ExecutorTask implements Runnable{

        private String member;

        public ExecutorTask(String member) {
            this.member = member;
        }

        @Override
        public void run() {
//            synchronized (ExecutorTask.class){
                final long score = System.currentTimeMillis()+Long.valueOf(member)*1000;
                log.info(score+"");
                redisUtils.zsetAddWithScore("orderStatusQueryKey1", "weeee-"+member, score);
//            }
        }
    }
}
