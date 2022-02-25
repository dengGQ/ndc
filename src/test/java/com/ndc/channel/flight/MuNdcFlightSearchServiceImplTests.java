package com.ndc.channel.flight;

import com.alibaba.fastjson.JSON;
import com.ndc.channel.ChannelApplication;
import com.ndc.channel.exception.BusinessException;
import com.ndc.channel.exception.BusinessExceptionCode;
import com.ndc.channel.flight.dto.createOrder.CorpApiOrderFlightTicketParams;
import com.ndc.channel.flight.dto.createOrder.CorpApiOrderPassengerParams;
import com.ndc.channel.flight.dto.createOrder.FlightOrderCreateReq;
import com.ndc.channel.flight.dto.createOrder.OrderContactParams;
import com.ndc.channel.flight.dto.flightSearch.CorpApiFlightListDataV2;
import com.ndc.channel.flight.dto.verifyPrice.CorpApiFlightVerifyPriceData;
import com.ndc.channel.flight.dto.verifyPrice.FeiBaApiVerifyPriceReq;
import com.ndc.channel.flight.dto.verifyPrice.FeibaApiVerificationParams;
import com.ndc.channel.flight.handler.NdcFlightCreateOrderHandler;
import com.ndc.channel.flight.handler.NdcFlightSearchHandler;
import com.ndc.channel.flight.handler.NdcFlightVerifyPriceHandler;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    private final static Logger LOGGER = LoggerFactory.getLogger(MuNdcFlightSearchServiceImplTests.class);

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
        passengerParams.setFlightPassengerName("邓国泉");
        passengerParams.setPhone("18611312771");
        passengerParams.setBirthday("1989-10-05");
        passengerParams.setIdcardCode("411527198910052071");
        passengerParams.setIdcardType("1");
        passengerParams.setSex("1");
        flightOrderCreateReq.setPassengers(Arrays.asList(passengerParams));


        OrderContactParams contactParams = new OrderContactParams();
        contactParams.setName("邓国泉");
        contactParams.setPhone("18611312771");
        flightOrderCreateReq.setContacts(Arrays.asList(contactParams));

        createOrderHandler.createOrder(flightOrderCreateReq);
    }




    static class BitMap{

        private int len;
        private byte[] bitMap;

        public BitMap(int len) {
            this.len = len;
            bitMap = new byte[(len>>3)+1];
        }

        public void set(int num, Boolean bool) {

            if (bool) {

                bitMap[num/8] |= 1<< (num%8);
            }else {
                bitMap[num/8] &= ~(1<< (num%8));
            }
        }

        public boolean get(int num) {

            return (bitMap[num/8] & (1 << (num % 8))) != 0 ? true : false;
        }

        public byte[] getBitMap(){
            return bitMap;
        }

        public int getLen() {
            return len;
        }
    }

    public static void main(String[] args) {

        final NdcFlightSearchHandler searchHandler = new NdcFlightSearchHandler();
        final List<CorpApiFlightListDataV2> corpApiFlightListDataV2s = searchHandler.flightSearch("2022-03-13", "SHA", "CAN");
        System.out.println(JSON.toJSONString(corpApiFlightListDataV2s));
    }

}
