
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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRQ}AffinityArrivalRequest"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRQ}AffinityDepRequest"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRQ}ConnectionPRefRefID"/>
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
    "affinityArrivalRequest",
    "affinityDepRequest",
    "connectionPRefRefID"
})
@XmlRootElement(name = "AffinityOriginDest", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRQ")
public class AffinityOriginDest {

    @XmlElement(name = "AffinityArrivalRequest", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRQ", required = true)
    protected AffinityArrivalRequest affinityArrivalRequest;
    @XmlElement(name = "AffinityDepRequest", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRQ", required = true)
    protected AffinityDepRequest affinityDepRequest;
    @XmlElement(name = "ConnectionPRefRefID", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRQ", required = true)
    protected String connectionPRefRefID;

    /**
     * 获取affinityArrivalRequest属性的值。
     * 
     * @return
     *     possible object is
     *     {@link AffinityArrivalRequest }
     *     
     */
    public AffinityArrivalRequest getAffinityArrivalRequest() {
        return affinityArrivalRequest;
    }

    /**
     * 设置affinityArrivalRequest属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link AffinityArrivalRequest }
     *     
     */
    public void setAffinityArrivalRequest(AffinityArrivalRequest value) {
        this.affinityArrivalRequest = value;
    }

    /**
     * 获取affinityDepRequest属性的值。
     * 
     * @return
     *     possible object is
     *     {@link AffinityDepRequest }
     *     
     */
    public AffinityDepRequest getAffinityDepRequest() {
        return affinityDepRequest;
    }

    /**
     * 设置affinityDepRequest属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link AffinityDepRequest }
     *     
     */
    public void setAffinityDepRequest(AffinityDepRequest value) {
        this.affinityDepRequest = value;
    }

    /**
     * 获取connectionPRefRefID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConnectionPRefRefID() {
        return connectionPRefRefID;
    }

    /**
     * 设置connectionPRefRefID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConnectionPRefRefID(String value) {
        this.connectionPRefRefID = value;
    }

}
