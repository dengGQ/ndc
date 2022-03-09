
package com.ndc.channel.flight.xmlBean.refundApply.request.bean;

import com.ndc.channel.entity.NdcFlightApiOrderRel;

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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderRetrieveRQ}MessageDoc"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderRetrieveRQ}Party"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderRetrieveRQ}PayloadAttributes"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderRetrieveRQ}Request"/>
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
    "messageDoc",
    "party",
    "payloadAttributes",
    "request"
})
@XmlRootElement(name = "IATA_OrderRetrieveRQ", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderRetrieveRQ")
public class IATAOrderRetrieveRQ {

    @XmlElement(name = "MessageDoc", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderRetrieveRQ", required = true)
    protected MessageDoc messageDoc;
    @XmlElement(name = "Party", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderRetrieveRQ", required = true)
    protected Party party;
    @XmlElement(name = "PayloadAttributes", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderRetrieveRQ", required = true)
    protected PayloadAttributes payloadAttributes;
    @XmlElement(name = "Request", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderRetrieveRQ", required = true)
    protected Request request;

    public IATAOrderRetrieveRQ() {
    }

    public IATAOrderRetrieveRQ(String orderId, NdcFlightApiOrderRel orderRel) {
        this.messageDoc = new MessageDoc();
        this.party = new Party();
        this.payloadAttributes = new PayloadAttributes();
        this.request = new Request(new Order(orderId, orderRel));
    }

    /**
     * 获取messageDoc属性的值。
     * 
     * @return
     *     possible object is
     *     {@link MessageDoc }
     *     
     */
    public MessageDoc getMessageDoc() {
        return messageDoc;
    }

    /**
     * 设置messageDoc属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link MessageDoc }
     *     
     */
    public void setMessageDoc(MessageDoc value) {
        this.messageDoc = value;
    }

    /**
     * 获取party属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Party }
     *     
     */
    public Party getParty() {
        return party;
    }

    /**
     * 设置party属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Party }
     *     
     */
    public void setParty(Party value) {
        this.party = value;
    }

    /**
     * 获取payloadAttributes属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PayloadAttributes }
     *     
     */
    public PayloadAttributes getPayloadAttributes() {
        return payloadAttributes;
    }

    /**
     * 设置payloadAttributes属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PayloadAttributes }
     *     
     */
    public void setPayloadAttributes(PayloadAttributes value) {
        this.payloadAttributes = value;
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

}
