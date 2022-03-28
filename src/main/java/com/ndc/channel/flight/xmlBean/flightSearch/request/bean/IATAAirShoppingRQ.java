
package com.ndc.channel.flight.xmlBean.flightSearch.request.bean;

import com.ndc.channel.flight.xmlBean.flightSearch.common.CommonRQ;
import com.ndc.channel.flight.xmlBean.flightSearch.common.MessageDoc;
import com.ndc.channel.flight.xmlBean.flightSearch.common.Party;
import com.ndc.channel.flight.xmlBean.flightSearch.common.PayloadAttributes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * <p>anonymous complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRQ}MessageDoc"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRQ}Party"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRQ}PayloadAttributes"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRQ}Request"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "IATA_AirShoppingRQ", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRQ")
public class IATAAirShoppingRQ {

    @XmlElement(name = "Request", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRQ", required = true)
    protected Request request;
    @XmlElement(name = "MessageDoc", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRQ", required = true)
    protected MessageDoc messageDoc;
    @XmlElement(name = "Party", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRQ", required = true)
    protected Party party;
    @XmlElement(name = "PayloadAttributes", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRQ", required = true)
    protected PayloadAttributes payloadAttributes;

    public IATAAirShoppingRQ() {
        this.messageDoc = new MessageDoc();
        this.party = new Party();
        this.payloadAttributes = new PayloadAttributes();
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request value) {
        this.request = value;
    }

    public MessageDoc getMessageDoc() {
        return messageDoc;
    }

    public void setMessageDoc(MessageDoc messageDoc) {
        this.messageDoc = messageDoc;
    }

    public Party getParty() {
        return party;
    }

    public void setParty(Party party) {
        this.party = party;
    }

    public PayloadAttributes getPayloadAttributes() {
        return payloadAttributes;
    }

    public void setPayloadAttributes(PayloadAttributes payloadAttributes) {
        this.payloadAttributes = payloadAttributes;
    }
}
