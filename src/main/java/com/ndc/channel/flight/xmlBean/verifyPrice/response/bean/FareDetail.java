
package com.ndc.channel.flight.xmlBean.verifyPrice.response.bean;

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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS}FareComponent"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS}FarePriceType"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS}FareRefText"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS}PricingSystemCodeText"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS}StatisticalCodeText"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS}TourCode"/>
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
    "fareComponent",
    "farePriceType",
    "fareRefText",
    "pricingSystemCodeText",
    "statisticalCodeText",
    "tourCode"
})
@XmlRootElement(name = "FareDetail", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS")
public class FareDetail {

    @XmlElement(name = "FareComponent", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS", required = true)
    protected FareComponent fareComponent;
    @XmlElement(name = "FarePriceType", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS", required = true)
    protected FarePriceType farePriceType;
    @XmlElement(name = "FareRefText", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS", required = true)
    protected String fareRefText;
    @XmlElement(name = "PricingSystemCodeText", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS", required = true)
    protected String pricingSystemCodeText;
    @XmlElement(name = "StatisticalCodeText", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS", required = true)
    protected String statisticalCodeText;
    @XmlElement(name = "TourCode", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS", required = true)
    protected String tourCode;

    /**
     * 获取fareComponent属性的值。
     * 
     * @return
     *     possible object is
     *     {@link FareComponent }
     *     
     */
    public FareComponent getFareComponent() {
        return fareComponent;
    }

    /**
     * 设置fareComponent属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link FareComponent }
     *     
     */
    public void setFareComponent(FareComponent value) {
        this.fareComponent = value;
    }

    /**
     * 获取farePriceType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link FarePriceType }
     *     
     */
    public FarePriceType getFarePriceType() {
        return farePriceType;
    }

    /**
     * 设置farePriceType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link FarePriceType }
     *     
     */
    public void setFarePriceType(FarePriceType value) {
        this.farePriceType = value;
    }

    /**
     * 获取fareRefText属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFareRefText() {
        return fareRefText;
    }

    /**
     * 设置fareRefText属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFareRefText(String value) {
        this.fareRefText = value;
    }

    /**
     * 获取pricingSystemCodeText属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPricingSystemCodeText() {
        return pricingSystemCodeText;
    }

    /**
     * 设置pricingSystemCodeText属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPricingSystemCodeText(String value) {
        this.pricingSystemCodeText = value;
    }

    /**
     * 获取statisticalCodeText属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatisticalCodeText() {
        return statisticalCodeText;
    }

    /**
     * 设置statisticalCodeText属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatisticalCodeText(String value) {
        this.statisticalCodeText = value;
    }

    /**
     * 获取tourCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTourCode() {
        return tourCode;
    }

    /**
     * 设置tourCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTourCode(String value) {
        this.tourCode = value;
    }

}
