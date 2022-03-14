
package com.ndc.channel.flight.xmlBean.refundOrderDetail.request.bean;

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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderRetrieveRQ}TravelAgentID"/>
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
    "travelAgentID"
})
@XmlRootElement(name = "TravelAgent", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderRetrieveRQ")
public class TravelAgent {

    @XmlElement(name = "TravelAgentID", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderRetrieveRQ", required = true)
    protected String travelAgentID;

    /**
     * 获取travelAgentID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTravelAgentID() {
        return travelAgentID;
    }

    /**
     * 设置travelAgentID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTravelAgentID(String value) {
        this.travelAgentID = value;
    }

}
