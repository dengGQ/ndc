
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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRQ}OfferItemID"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRQ}OfferItemType"/>
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
    "offerItemID",
    "offerItemType"
})
@XmlRootElement(name = "CreateOrderItem", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRQ")
public class CreateOrderItem {

    @XmlElement(name = "OfferItemID", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRQ", required = true)
    protected String offerItemID;
    @XmlElement(name = "OfferItemType", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRQ", required = true)
    protected OfferItemType offerItemType;

    /**
     * 获取offerItemID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOfferItemID() {
        return offerItemID;
    }

    /**
     * 设置offerItemID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOfferItemID(String value) {
        this.offerItemID = value;
    }

    /**
     * 获取offerItemType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link OfferItemType }
     *     
     */
    public OfferItemType getOfferItemType() {
        return offerItemType;
    }

    /**
     * 设置offerItemType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link OfferItemType }
     *     
     */
    public void setOfferItemType(OfferItemType value) {
        this.offerItemType = value;
    }

}
