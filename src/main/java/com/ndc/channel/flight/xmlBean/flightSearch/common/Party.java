
package com.ndc.channel.flight.xmlBean.flightSearch.common;

import javax.xml.bind.annotation.*;


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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRQ}Recipient"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRQ}Sender"/>
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
    "recipient",
    "sender"
})
@XmlRootElement(name = "Party", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRQ")
public class Party {

    @XmlElement(name = "Recipient", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRQ", required = true)
    protected Recipient recipient;
    @XmlElement(name = "Sender", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRQ", required = true)
    protected Sender sender;

    public Party() {
        this.recipient = new Recipient();
        this.sender = new Sender();
    }

    public Party(Recipient recipient, Sender sender) {
        this.recipient = recipient;
        this.sender = sender;
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
