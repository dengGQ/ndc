
package com.ndc.channel.flight.xmlBean.changeFlightSearch.request.bean;

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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRQ}Aggregator"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRQ}Participant"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRQ}Recipient"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRQ}Sender"/>
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
    "aggregator",
    "participant",
    "recipient",
    "sender"
})
@XmlRootElement(name = "Party", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRQ")
public class Party {

    @XmlElement(name = "Aggregator", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRQ", required = true)
    protected Aggregator aggregator;
    @XmlElement(name = "Participant", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRQ", required = true)
    protected Participant participant;
    @XmlElement(name = "Recipient", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRQ", required = true)
    protected Recipient recipient;
    @XmlElement(name = "Sender", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRQ", required = true)
    protected Sender sender;

    public Party() {
        this.recipient = new Recipient();
        this.sender = new Sender();
    }

    /**
     * 获取aggregator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Aggregator }
     *     
     */
    public Aggregator getAggregator() {
        return aggregator;
    }

    /**
     * 设置aggregator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Aggregator }
     *     
     */
    public void setAggregator(Aggregator value) {
        this.aggregator = value;
    }

    /**
     * 获取participant属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Participant }
     *     
     */
    public Participant getParticipant() {
        return participant;
    }

    /**
     * 设置participant属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Participant }
     *     
     */
    public void setParticipant(Participant value) {
        this.participant = value;
    }

    /**
     * 获取recipient属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Recipient }
     *     
     */
    public Recipient getRecipient() {
        return recipient;
    }

    /**
     * 设置recipient属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Recipient }
     *     
     */
    public void setRecipient(Recipient value) {
        this.recipient = value;
    }

    /**
     * 获取sender属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Sender }
     *     
     */
    public Sender getSender() {
        return sender;
    }

    /**
     * 设置sender属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Sender }
     *     
     */
    public void setSender(Sender value) {
        this.sender = value;
    }

}
