package com.ndc.channel.service;

import com.ndc.channel.flight.dto.createOrder.CorpApiFlightOrderCreateData;
import com.ndc.channel.flight.dto.createOrder.FlightOrderCreateReq;
import com.ndc.channel.flight.dto.flightSearch.CorpApiTicketData;

public interface NdcFlightApiOrderRelService {

    void insertEntity(FlightOrderCreateReq orderCreateReq, CorpApiFlightOrderCreateData orderCreateData, CorpApiTicketData ticketData);
}
