package com.ndc.channel.flight.xmlBean.flightSearch.common;

import javax.xml.bind.annotation.XmlElement;

public class CommonRQ {

    @XmlElement(name = "MessageDoc", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRQ", required = true)
    protected MessageDoc messageDoc;
    @XmlElement(name = "Party", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRQ", required = true)
    protected Party party;
    @XmlElement(name = "PayloadAttributes", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRQ", required = true)
    protected PayloadAttributes payloadAttributes;

    public CommonRQ() {
        this.messageDoc = new MessageDoc();
        this.party = new Party();
        this.payloadAttributes = new PayloadAttributes();
    }

    public CommonRQ(MessageDoc messageDoc, Party party, PayloadAttributes payloadAttributes) {
        this.messageDoc = messageDoc;
        this.party = party;
        this.payloadAttributes = payloadAttributes;
    }


    public void setMessageDoc(MessageDoc messageDoc) {
        this.messageDoc = messageDoc;
    }


    public void setParty(Party party) {
        this.party = party;
    }

    public void setPayloadAttributes(PayloadAttributes payloadAttributes) {
        this.payloadAttributes = payloadAttributes;
    }

    public PayloadAttributes getPayloadAttributes() {
        return payloadAttributes;
    }
}
