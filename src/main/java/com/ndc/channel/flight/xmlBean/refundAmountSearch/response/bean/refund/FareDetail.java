
package com.ndc.channel.flight.xmlBean.refundAmountSearch.response.bean.refund;

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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}FareComponent"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}FarePriceType"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}PricingSystemCodeText"/>
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
    "pricingSystemCodeText"
})
@XmlRootElement(name = "FareDetail", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS")
public class FareDetail {

    @XmlElement(name = "FareComponent", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", required = true)
    protected FareComponent fareComponent;
    @XmlElement(name = "FarePriceType", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", required = true)
    protected FarePriceType farePriceType;
    @XmlElement(name = "PricingSystemCodeText", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", required = true)
    protected String pricingSystemCodeText;

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

}
