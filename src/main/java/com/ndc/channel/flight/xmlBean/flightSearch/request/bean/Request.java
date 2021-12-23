
package com.ndc.channel.flight.xmlBean.flightSearch.request.bean;

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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRQ}FlightCriteria"/>
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
    "flightCriteria"
})
@XmlRootElement(name = "Request", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRQ")
public class Request {

    @XmlElement(name = "FlightCriteria", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRQ", required = true)
    protected FlightCriteria flightCriteria;

    /**
     * 获取flightCriteria属性的值。
     * 
     * @return
     *     possible object is
     *     {@link FlightCriteria }
     *     
     */
    public FlightCriteria getFlightCriteria() {
        return flightCriteria;
    }

    /**
     * 设置flightCriteria属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link FlightCriteria }
     *     
     */
    public void setFlightCriteria(FlightCriteria value) {
        this.flightCriteria = value;
    }

}
