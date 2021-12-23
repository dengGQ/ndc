
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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRQ}TravelAgency"/>
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
    "travelAgency"
})
@XmlRootElement(name = "Sender", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRQ")
public class Sender {

    @XmlElement(name = "TravelAgency", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRQ", required = true)
    protected TravelAgency travelAgency;

    public Sender() {
        this.travelAgency = new TravelAgency();
    }

    public Sender(TravelAgency travelAgency) {
        this.travelAgency = travelAgency;
    }

    /**
     * 获取travelAgency属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TravelAgency }
     *     
     */
    public TravelAgency getTravelAgency() {
        return travelAgency;
    }

    /**
     * 设置travelAgency属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TravelAgency }
     *     
     */
    public void setTravelAgency(TravelAgency value) {
        this.travelAgency = value;
    }

}
