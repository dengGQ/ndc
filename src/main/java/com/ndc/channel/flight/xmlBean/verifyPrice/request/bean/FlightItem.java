
package com.ndc.channel.flight.xmlBean.verifyPrice.request.bean;

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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRQ}FareDetail"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRQ}OriginDestRefID"/>
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
    "fareDetail",
    "originDestRefID"
})
@XmlRootElement(name = "FlightItem", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRQ")
public class FlightItem {

    @XmlElement(name = "FareDetail", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRQ", required = true)
    protected FareDetail fareDetail;
    @XmlElement(name = "OriginDestRefID", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRQ", required = true)
    protected String originDestRefID;

    /**
     * 获取fareDetail属性的值。
     * 
     * @return
     *     possible object is
     *     {@link FareDetail }
     *     
     */
    public FareDetail getFareDetail() {
        return fareDetail;
    }

    /**
     * 设置fareDetail属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link FareDetail }
     *     
     */
    public void setFareDetail(FareDetail value) {
        this.fareDetail = value;
    }

    /**
     * 获取originDestRefID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOriginDestRefID() {
        return originDestRefID;
    }

    /**
     * 设置originDestRefID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOriginDestRefID(String value) {
        this.originDestRefID = value;
    }

}
