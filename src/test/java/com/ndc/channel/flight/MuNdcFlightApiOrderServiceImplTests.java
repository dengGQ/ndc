package com.ndc.channel.flight;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ndc.channel.ChannelApplication;
import com.ndc.channel.executor.OrderDetailDelayQueryExecutor;
import com.ndc.channel.flight.dto.createOrder.CorpApiFlightOrderCreateData;
import com.ndc.channel.flight.dto.createOrder.FlightOrderCreateReq;
import com.ndc.channel.service.NdcFlightApiOrderRelService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ChannelApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
@Slf4j
public class MuNdcFlightApiOrderServiceImplTests {

    @Resource
    NdcFlightApiOrderRelService orderRelService;


    @Test
    public void insertEntity() {

        String s = "{\"afterTicketUrl\":\"http://localhost:8084/feiba/channel/callback/createOrder\",\"contacts\":[{}],\"externalOrderNumber\":\"128616493660711\",\"flightType\":1,\"passengers\":[{\"birthday\":\"1991-05-22\",\"flightPassengerName\":\"测试是\",\"idcardCode\":\"42080219910522033X\",\"idcardType\":\"1\",\"passengerType\":1,\"phone\":\"18357486995\",\"sex\":\"1\"}],\"tickets\":[{\"flightId\":\"2022-06-2808051030MU5301SHACAN\",\"flightType\":\"1\",\"price\":1780.00,\"seatClassCode\":\"Y\",\"ticketId\":\"2022-06-2808051030MU5301SHACANY1@10168\"}]}";

        String s1 = "{\"orderItemId\":\"1122032400188599\",\"orderNumber\":\"1022032400210853\",\"ownerCode\":\"MU\",\"ownerTypeCode\":\"ORA\",\"pnrNo\":\"PF84QD\",\"settlementMoney\":1870.0,\"ticketPrice\":1780.0,\"tktl\":\"2022-03-24T14:24:31.000+08:00\"}";

        final FlightOrderCreateReq orderCreateReq = JSON.parseObject(s, FlightOrderCreateReq.class);

        final CorpApiFlightOrderCreateData orderCreateData = JSON.parseObject(s1, CorpApiFlightOrderCreateData.class);

        orderRelService.insertEntity(orderCreateReq, orderCreateData);
    }

    public static void main(String[] args) throws InterruptedException {

        Thread th = null;

        while (true) {
            if (th == null || th.getState() == Thread.State.TERMINATED) {
                th = new Thread(new ExecutorTask());
                th.start();
            }

            Thread.sleep(500l);
        }
    }

    static class ExecutorTask implements Runnable{
        @Override
        public void run() {
            try {
                System.out.println("----------threadName="+Thread.currentThread().getName());
                Thread.sleep(5000l);
                System.out.println("----------============threadName="+Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
