package com.ndc.channel.flight.controller;

import com.ndc.channel.exception.BusinessException;
import com.ndc.channel.flight.dto.BusinessResponseFactory;
import com.ndc.channel.flight.dto.ResponseData;
import com.ndc.channel.flight.dto.createOrder.CorpApiFlightOrderCreateData;
import com.ndc.channel.flight.dto.createOrder.FlightOrderCreateReq;
import com.ndc.channel.flight.dto.flightSearch.CorpApiFlightListDataV2;
import com.ndc.channel.flight.dto.flightSearch.FlightSearchReq;
import com.ndc.channel.flight.dto.orderDetail.NdcOrderDetailData;
import com.ndc.channel.flight.dto.orderDetail.OrderTicketInfo;
import com.ndc.channel.flight.dto.orderPay.OrderPayReqParams;
import com.ndc.channel.flight.dto.verifyPrice.CorpApiFlightVerifyPriceData;
import com.ndc.channel.flight.dto.verifyPrice.FeiBaApiVerifyPriceReq;
import com.ndc.channel.flight.handler.*;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/ndc/flight/channel/standardChannel")
public class MuNdcFlightController {

    @Resource
    private NdcFlightSearchHandler flightSearchHandler;
    @Resource
    private NdcFlightVerifyPriceHandler flightVerifyPriceHandler;
    @Resource
    private NdcFlightCreateOrderHandler createOrderHandler;
    @Resource
    private NdcFlightOrderPayHandler orderPayHandler;
    @Resource
    private NdcFlightOrderDetailHandler orderDetailHandler;
    @Resource
    private NdcFlightOrderRefundHandler orderRefundHandler;

    @PostMapping("/corpapi/flight/search")
    @ApiOperation(value = "航班查询", notes = "航班查询", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseData<List<CorpApiFlightListDataV2>> flightSearch(@RequestBody FlightSearchReq searchReq) {
        try {

            List<CorpApiFlightListDataV2> flightListDataList = flightSearchHandler.flightSearch(searchReq.getFlightDate(), searchReq.getFromCity(), searchReq.getToCity());
            return BusinessResponseFactory.createSuccess(flightListDataList);
        }catch (BusinessException exception) {

            log.error("东航NDC机票查询失败，失败原因={}", exception.getMessage());
            return BusinessResponseFactory.createBusinessError(exception);
        }
    }


    @PostMapping("/corpapi/flight/price/verification")
    @ApiOperation(value = "验价", notes = "验价", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseData<CorpApiFlightVerifyPriceData> verifyPrice(@RequestBody FeiBaApiVerifyPriceReq req) {

        try {

            final CorpApiFlightVerifyPriceData verifyPriceData = flightVerifyPriceHandler.verifyPrice(req);

            return BusinessResponseFactory.createSuccess(verifyPriceData);
        }catch (BusinessException exception) {

            log.error("东航NDC机票验舱验价失败，失败原因={}", exception.getMessage());
            return BusinessResponseFactory.createBusinessError(exception);
        }
    }

    @PostMapping("/corpapi/flight/order/create")
    @ApiOperation(value = "创单", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseData<Object> createOrder(@RequestBody FlightOrderCreateReq req) {

        try {

            CorpApiFlightOrderCreateData order = createOrderHandler.createOrder(req);

            return BusinessResponseFactory.createSuccess(order);
        }catch (BusinessException exception) {

            log.error("东航NDC创建机票订单失败，失败原因={}", exception.getMessage());
            return BusinessResponseFactory.createBusinessError(exception);
        }
    }

    @PostMapping("/corpapi/flight/order/ticket")
    @ApiOperation(value = "支付出票", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseData<Boolean> orderPay(@RequestBody OrderPayReqParams payReqParams) {

        try {

            final Boolean result = orderPayHandler.orderPay(payReqParams);

            return BusinessResponseFactory.createSuccess(result);
        }catch (BusinessException exception) {

            log.error("东航NDC机票订单支付失败，失败原因={}", exception.getMessage());
            return BusinessResponseFactory.createBusinessError(exception);
        }
    }


    @PostMapping("/corpapi/flight/order/detail/{orderId}")
    @ApiOperation(value = "订单明细", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseData<List<OrderTicketInfo>> orderDetail(@PathVariable("orderId") String orderId) {

        try {

            final NdcOrderDetailData ndcOrderDetailData = orderDetailHandler.orderDetail(orderId);

            return BusinessResponseFactory.createSuccess(ndcOrderDetailData.getTicketInfoList());
        }catch (BusinessException exception) {

            log.error("东航NDC机票订单明细查询失败，失败原因={}", exception.getMessage());
            return BusinessResponseFactory.createBusinessError(exception);
        }
    }

    @PostMapping("/corpapi/flight/refund/create")
    @ApiOperation(value = "退票申请", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseData<String> refundApply(String orderId) {

        try {

            final String channelRefundOrderNumber = orderRefundHandler.refundApply(orderId);

            return BusinessResponseFactory.createSuccess(channelRefundOrderNumber);
        }catch (BusinessException exception) {

            log.error("东航NDC退票申请失败，失败原因={}", exception.getMessage());
            return BusinessResponseFactory.createBusinessError(exception);
        }
    }


}
