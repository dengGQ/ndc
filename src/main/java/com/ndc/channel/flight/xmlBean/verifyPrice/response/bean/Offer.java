
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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS}OfferID"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS}OfferItem"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS}OwnerCode"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS}OwnerTypeCode"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS}PTC_OfferParameters"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS}TotalPrice"/>
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
    "offerID",
    "offerItem",
    "ownerCode",
    "ownerTypeCode",
    "ptcOfferParameters",
    "totalPrice"
})
@XmlRootElement(name = "Offer", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS")
public class Offer {

    @XmlElement(name = "OfferID", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS", required = true)
    protected String offerID;
    @XmlElement(name = "OfferItem", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS", required = true)
    protected OfferItem offerItem;
    @XmlElement(name = "OwnerCode", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS", required = true)
    protected String ownerCode;
    @XmlElement(name = "OwnerTypeCode", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS", required = true)
    protected String ownerTypeCode;
    @XmlElement(name = "PTC_OfferParameters", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS", required = true)
    protected PTCOfferParameters ptcOfferParameters;
    @XmlElement(name = "TotalPrice", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS", required = true)
    protected TotalPrice totalPrice;

    /**
     * 获取offerID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOfferID() {
        return offerID;
    }

    /**
     * 设置offerID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOfferID(String value) {
        this.offerID = value;
    }

    /**
     * 获取offerItem属性的值。
     * 
     * @return
     *     possible object is
     *     {@link OfferItem }
     *     
     */
    public OfferItem getOfferItem() {
        return offerItem;
    }

    /**
     * 设置offerItem属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link OfferItem }
     *     
     */
    public void setOfferItem(OfferItem value) {
        this.offerItem = value;
    }

    /**
     * 获取ownerCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOwnerCode() {
        return ownerCode;
    }

    /**
     * 设置ownerCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOwnerCode(String value) {
        this.ownerCode = value;
    }

    /**
     * 获取ownerTypeCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOwnerTypeCode() {
        return ownerTypeCode;
    }

    /**
     * 设置ownerTypeCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOwnerTypeCode(String value) {
        this.ownerTypeCode = value;
    }

    /**
     * 获取ptcOfferParameters属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PTCOfferParameters }
     *     
     */
    public PTCOfferParameters getPTCOfferParameters() {
        return ptcOfferParameters;
    }

    /**
     * 设置ptcOfferParameters属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PTCOfferParameters }
     *     
     */
    public void setPTCOfferParameters(PTCOfferParameters value) {
        this.ptcOfferParameters = value;
    }

    /**
     * 获取totalPrice属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TotalPrice }
     *     
     */
    public TotalPrice getTotalPrice() {
        return totalPrice;
    }

    /**
     * 设置totalPrice属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TotalPrice }
     *     
     */
    public void setTotalPrice(TotalPrice value) {
        this.totalPrice = value;
    }

}
