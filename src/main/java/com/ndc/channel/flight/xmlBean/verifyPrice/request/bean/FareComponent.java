
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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRQ}FareTypeCode"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRQ}PaxSegmentRefID"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRQ}PriceClassRefID"/>
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
    "fareTypeCode",
    "paxSegmentRefID",
    "priceClassRefID"
})
@XmlRootElement(name = "FareComponent", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRQ")
public class FareComponent {

    @XmlElement(name = "FareTypeCode", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRQ", required = true)
    protected String fareTypeCode;
    @XmlElement(name = "PaxSegmentRefID", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRQ", required = true)
    protected String paxSegmentRefID;
    @XmlElement(name = "PriceClassRefID", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRQ", required = true)
    protected String priceClassRefID;

    /**
     * 获取fareTypeCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFareTypeCode() {
        return fareTypeCode;
    }

    /**
     * 设置fareTypeCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFareTypeCode(String value) {
        this.fareTypeCode = value;
    }

    /**
     * 获取paxSegmentRefID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaxSegmentRefID() {
        return paxSegmentRefID;
    }

    /**
     * 设置paxSegmentRefID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaxSegmentRefID(String value) {
        this.paxSegmentRefID = value;
    }

    /**
     * 获取priceClassRefID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPriceClassRefID() {
        return priceClassRefID;
    }

    /**
     * 设置priceClassRefID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPriceClassRefID(String value) {
        this.priceClassRefID = value;
    }

}
