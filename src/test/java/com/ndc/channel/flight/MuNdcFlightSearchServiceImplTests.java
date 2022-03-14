package com.ndc.channel.flight;

import com.alibaba.fastjson.JSON;
import com.ndc.channel.ChannelApplication;
import com.ndc.channel.entity.NdcFlightApiOrderRel;
import com.ndc.channel.executor.OrderDetailDelayQueryExecutor;
import com.ndc.channel.flight.dto.MsgBody;
import com.ndc.channel.flight.dto.createOrder.CorpApiOrderFlightTicketParams;
import com.ndc.channel.flight.dto.createOrder.CorpApiOrderPassengerParams;
import com.ndc.channel.flight.dto.createOrder.FlightOrderCreateReq;
import com.ndc.channel.flight.dto.flightSearch.CorpApiFlightListDataV2;
import com.ndc.channel.flight.dto.orderDetail.NdcOrderDetailData;
import com.ndc.channel.flight.dto.orderPay.OrderPayReqParams;
import com.ndc.channel.flight.dto.refund.RefundChangeMoneyQueryParams;
import com.ndc.channel.flight.dto.refund.RefundChangeMoneyQueryResp;
import com.ndc.channel.flight.dto.verifyPrice.CorpApiFlightVerifyPriceData;
import com.ndc.channel.flight.dto.verifyPrice.FeiBaApiVerifyPriceReq;
import com.ndc.channel.flight.dto.verifyPrice.FeibaApiVerificationParams;
import com.ndc.channel.flight.handler.*;
import com.ndc.channel.flight.xmlBean.refundOrderDetail.response.bean.Response;
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
        final NdcOrderDetailData response = detailHandler.orderDetail("1022030800206118");

        System.out.println(JSON.toJSONString(response));
    }

    @Test
    public void afterCreateOrder () {

        final MsgBody msgBody = new MsgBody();
        msgBody.setMsgType("1");
        msgBody.setBusinessNumber("1022031100207207");
        final String s = JSON.toJSONString(msgBody);

        queryExecutor.submitTask(s, 5);
        while (true){}
    }


    static class BitMap{

        // 能存下整数的个数：比如15个整数，len=15
        private int len;
        private byte[] byteArray;

        public BitMap(int len) {
            this.len = len;
            byteArray = new byte[(len>>3)+1];
        }

        public void set(int num, Boolean bool) {

            if (bool) {

                byteArray[num/8] |= 1<< (num%8);
            }else {
                byteArray[num/8] &= ~(1<< (num%8));
            }
        }

        /**
         * 将num所在的位置从0变成1
         * 将1左移position位后，position位置上自然就是1，其他位置都是0
         * 然后和以前的数据做‘或’运算，这样，以前的数据position位置上就替换成1了，其他位置保持不变
         */
        public void add(int num) {
            byteArray[getIdx(num)] |= 1 << getPosition(num);
        }

        /**
         * 将num所在的位置从0变成1
         * 将1左移position位后，position位置上自然就是1，其他位置都是0。然后再取反就变成position位置是0，其他位置都是1
         * 然后和以前的数据做‘与’运算，这样，以前的数据position位置上替换成0了，其他位置保持不变
         * @param num
         */
        public void remove(int num) {
            byteArray[getIdx(num)] &= ~(1 << getPosition(num));
        }

        /**
         * 判断num是否存在
         * 同样将1左移position位使position位置上变成1，其他位置都是0，然后用这个数跟原数做与运算
         * 因为其他位置都是0，只有position位置是1，所以只有原数据position位置也是1运算后的结果才会是1，否则就是0
         * 如此边确定了num是否存在了
         * @param num
         * @return
         */
        public boolean isExits(int num) {

            return (byteArray[num/8] & (1 << (num % 8))) != 0 ? true : false;
        }

        // num在byte[]数组中的位置
        public int getIdx(int num) {
            return num >> 3;
        }

        // num在byte[idx]中第几位, 等价于：num%8
        public int getPosition(int num) {
            return num & 0x07;
        }

        public byte[] getBitMap(){
            return byteArray;
        }

        public int getLen() {
            return len;
        }
    }

    public static void main(String[] args) {
        final BitMap bitMap = new BitMap(6);
        System.out.println(bitMap.getLen());
        System.out.println(bitMap.getBitMap().length);

        System.out.println(13%8);
        System.out.println(13 & 7);
    }

}
