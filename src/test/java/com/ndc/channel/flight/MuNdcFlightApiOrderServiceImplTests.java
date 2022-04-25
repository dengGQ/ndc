package com.ndc.channel.flight;

import com.alibaba.fastjson.JSON;
import com.ndc.channel.ChannelApplication;
import com.ndc.channel.common.service.IIdGeneratorService;
import com.ndc.channel.flight.dto.createOrder.CorpApiFlightOrderCreateData;
import com.ndc.channel.flight.dto.createOrder.FlightOrderCreateReq;
import com.ndc.channel.flight.dto.flightSearch.CorpApiTicketData;
import com.ndc.channel.flight.dto.refund.RefundReapplyParams;
import com.ndc.channel.flight.handler.NdcFlightRefundReapplyHandler;
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
    IIdGeneratorService idGeneratorService;

    @Test
    public void insertEntity() {

        String s = "{\"afterTicketUrl\":\"http://localhost:8084/feiba/channel/callback/createOrder\",\"contacts\":[{}],\"externalOrderNumber\":\"128616493660711\",\"flightType\":1,\"passengers\":[{\"birthday\":\"1991-05-22\",\"flightPassengerName\":\"测试是\",\"idcardCode\":\"42080219910522033X\",\"idcardType\":\"1\",\"passengerType\":1,\"phone\":\"18357486995\",\"sex\":\"1\"}],\"tickets\":[{\"flightId\":\"2022-06-2808051030MU5301SHACAN\",\"flightType\":\"1\",\"price\":1780.00,\"seatClassCode\":\"Y\",\"ticketId\":\"2022-06-2808051030MU5301SHACANY1@10168\"}]}";

        String s1 = "{\"orderItemId\":\"1122032400188599\",\"orderNumber\":\"1022032400210853\",\"ownerCode\":\"MU\",\"ownerTypeCode\":\"ORA\",\"pnrNo\":\"PF84QD\",\"settlementMoney\":1870.0,\"ticketPrice\":1780.0,\"tktl\":\"2022-03-24T14:24:31.000+08:00\"}";

        final FlightOrderCreateReq orderCreateReq = JSON.parseObject(s, FlightOrderCreateReq.class);

        final CorpApiFlightOrderCreateData orderCreateData = JSON.parseObject(s1, CorpApiFlightOrderCreateData.class);

        CorpApiTicketData ticketData = redisUtils.hGet(RedisKeyConstants.getRedisTicketDataCacheKey("2022-06-2808051030MU5301SHACAN"), "2022-06-2808051030MU5301SHACANY1@10168", CorpApiTicketData.class);

        orderRelService.insertEntity(orderCreateReq, orderCreateData, ticketData);
    }

    @Resource
    private NdcFlightRefundReapplyHandler refundReapplyHandler;
    @Test
    public void reapplyRefund() {

        RefundReapplyParams refundReapplyParams = new RefundReapplyParams();
        refundReapplyParams.setNdcRefundRelId(55L);
        refundReapplyParams.setRefundReason(Byte.valueOf("3"));
        refundReapplyParams.setMemo("病退");
        refundReapplyParams.setRefundAttachmentUrl(Arrays.asList("https://www.zixi.org/static/uploads/2022/4/202204240001035537.jpg"));

        final boolean b = refundReapplyHandler.refundReapply(refundReapplyParams);
        System.out.println("-----------");
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
