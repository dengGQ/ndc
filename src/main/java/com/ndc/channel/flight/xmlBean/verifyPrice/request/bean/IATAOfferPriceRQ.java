
package com.ndc.channel.flight.xmlBean.verifyPrice.request.bean;

import com.ndc.channel.flight.xmlBean.verifyPrice.common.CommonRQ;
import com.ndc.channel.flight.xmlBean.verifyPrice.common.MessageDoc;
import com.ndc.channel.flight.xmlBean.verifyPrice.common.Party;
import com.ndc.channel.flight.xmlBean.verifyPrice.common.PayloadAttributes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRQ}MessageDoc"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRQ}Party"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRQ}PayloadAttributes"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRQ}Request"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "request","messageDoc","party","payloadAttributes"
})
@XmlRootElement(name = "IATA_OfferPriceRQ", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRQ")
public class IATAOfferPriceRQ {

    @XmlElement(name = "Request", required = true, namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRQ")
    protected Request request;

    @XmlElement(name = "MessageDoc", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRQ", required = true)
    protected com.ndc.channel.flight.xmlBean.verifyPrice.common.MessageDoc messageDoc;
    @XmlElement(name = "Party", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRQ", required = true)
    protected com.ndc.channel.flight.xmlBean.verifyPrice.common.Party party;
    @XmlElement(name = "PayloadAttributes", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRQ", required = true)
    protected com.ndc.channel.flight.xmlBean.verifyPrice.common.PayloadAttributes payloadAttributes;

    public IATAOfferPriceRQ() {
        this.messageDoc = new MessageDoc();
        this.party = new Party();
        this.payloadAttributes = new PayloadAttributes();
    }

    /**
     * 获取request属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Request }
     *     
     */
    public Request getRequest() {
        return request;
    }

    /**
     * 设置request属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Request }
     *     
     */
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
