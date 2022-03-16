
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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRQ}TravelAgency"/>
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
    "travelAgency"
})
@XmlRootElement(name = "Sender", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRQ")
public class Sender {

    @XmlElement(name = "Aggregator", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRQ", required = true)
    protected Aggregator aggregator;
    @XmlElement(name = "TravelAgency", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRQ", required = true)
    protected TravelAgency travelAgency;

    public Sender() {
        this.travelAgency = new TravelAgency();
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
