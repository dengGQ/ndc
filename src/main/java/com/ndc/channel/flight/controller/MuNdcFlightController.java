package com.ndc.channel.flight.controller;

import com.ndc.channel.exception.BusinessException;
import com.ndc.channel.flight.dto.BusinessResponseFactory;
import com.ndc.channel.flight.dto.ResponseData;
import com.ndc.channel.flight.dto.flightSearch.CorpApiFlightListDataV2;
import com.ndc.channel.flight.dto.verifyPrice.CorpApiFlightVerifyPriceData;
import com.ndc.channel.flight.dto.verifyPrice.FeiBaApiVerifyPriceReq;
import com.ndc.channel.flight.handler.NdcFlightSearchHandler;
import com.ndc.channel.flight.handler.NdcFlightVerifyPriceHandler;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/ndc/flight")
public class MuNdcFlightController {

    @Resource
    private NdcFlightSearchHandler flightSearchHandler;

    @Resource
    private NdcFlightVerifyPriceHandler flightVerifyPriceHandler;

    @PostMapping("/search/{flightDate}/{depCityCode}/{destCityCode}")
    @ApiOperation(value = "航班查询", notes = "航班查询", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseData<List<CorpApiFlightListDataV2>> flightSearch(@PathVariable("flightDate") String flightDate, @PathVariable("depCityCode") String depCityCode, @PathVariable("destCityCode") String destCityCode) {

        try {

            List<CorpApiFlightListDataV2> flightListDataList = flightSearchHandler.flightSearch(flightDate, depCityCode, destCityCode);

            return BusinessResponseFactory.createSuccess(flightListDataList);
        }catch (BusinessException exception) {

            log.error("东航NDC机票查询失败，失败原因={}", exception.getMessage());
            return BusinessResponseFactory.createBusinessError(exception);
        }
    }


    @PostMapping("/price/verification")
    @ApiOperation(value = "验价", notes = "验价", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseData<CorpApiFlightVerifyPriceData> verifyPrice(@RequestBody FeiBaApiVerifyPriceReq req) {

        try {

            final CorpApiFlightVerifyPriceData verifyPriceData = flightVerifyPriceHandler.verifyPrice(req);

            return BusinessResponseFactory.createSuccess(verifyPriceData);
        }catch (BusinessException exception) {

            log.error("东航NDC创建机票订单失败，失败原因={}", exception.getMessage());
            return BusinessResponseFactory.createBusinessError(exception);
        }
    }


}
