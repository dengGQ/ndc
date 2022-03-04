package com.ndc.channel.flight;

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

        FlightOrderCreateReq orderCreateReq = new FlightOrderCreateReq();
        orderCreateReq.setExternalOrderNumber("1111");
        orderCreateReq.setAfterTicketUrl("http://");

        CorpApiFlightOrderCreateData orderCreateData = new CorpApiFlightOrderCreateData();
        orderCreateData.setOrderNumber("2222");
        orderCreateData.setOrderItemId("33333");

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
