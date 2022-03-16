
package com.ndc.channel.flight.xmlBean.changeFlightSearch.response.bean;

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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS}FareComponent"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS}FareIndCode"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS}FarePriceType"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS}FareRefText"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS}PaxRefID"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS}PricingSystemCodeText"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS}TourCode"/>
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
    "fareIndCode",
    "farePriceType",
    "fareRefText",
    "paxRefID",
    "pricingSystemCodeText",
    "tourCode"
})
@XmlRootElement(name = "FareDetail", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS")
public class FareDetail {

    @XmlElement(name = "FareComponent", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS", required = true)
    protected FareComponent fareComponent;
    @XmlElement(name = "FareIndCode", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS", required = true)
    protected String fareIndCode;
    @XmlElement(name = "FarePriceType", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS", required = true)
    protected FarePriceType farePriceType;
    @XmlElement(name = "FareRefText", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS", required = true)
    protected String fareRefText;
    @XmlElement(name = "PaxRefID", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS", required = true)
    protected String paxRefID;
    @XmlElement(name = "PricingSystemCodeText", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS", required = true)
    protected String pricingSystemCodeText;
    @XmlElement(name = "TourCode", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS", required = true)
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
     * 获取fareIndCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFareIndCode() {
        return fareIndCode;
    }

    /**
     * 设置fareIndCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFareIndCode(String value) {
        this.fareIndCode = value;
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
     * 获取paxRefID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaxRefID() {
        return paxRefID;
    }

    /**
     * 设置paxRefID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaxRefID(String value) {
        this.paxRefID = value;
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
