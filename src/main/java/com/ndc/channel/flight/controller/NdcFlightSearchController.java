package com.ndc.channel.flight.controller;

import com.ndc.channel.flight.dto.CorpApiFlightListDataV2;
import com.ndc.channel.flight.handler.NdcFlightSearchHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/ndc/flight")
public class NdcFlightSearchController {

    @Resource
    private NdcFlightSearchHandler flightSearchHandler;

    @RequestMapping("/search/{flightDate}/{depCityCode}/{destCityCode}")
    public List<CorpApiFlightListDataV2> flightSearch(@PathVariable("flightDate") String flightDate, @PathVariable("depCityCode") String depCityCode, @PathVariable("destCityCode") String destCityCode) {
        return flightSearchHandler.flightSearch(flightDate, depCityCode, destCityCode);
    }
}
