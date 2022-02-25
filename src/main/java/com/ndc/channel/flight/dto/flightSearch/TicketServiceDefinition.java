package com.ndc.channel.flight.dto.flightSearch;


import com.ndc.channel.flight.xmlBean.flightSearch.response.bean.BaggageAllowance;
import lombok.Data;

@Data
public class TicketServiceDefinition {

    protected String serviceDefinitionID;
    protected String descText;
    protected String name;
    protected String serviceCode;
}
