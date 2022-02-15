
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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS}PriceClass"/>
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
    "priceClass"
})
@XmlRootElement(name = "PriceClassList", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS")
public class PriceClassList {

    @XmlElement(name = "PriceClass", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS", required = true)
    protected PriceClass priceClass;

    /**
     * 获取priceClass属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PriceClass }
     *     
     */
    public PriceClass getPriceClass() {
        return priceClass;
    }

    /**
     * 设置priceClass属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PriceClass }
     *     
     */
    public void setPriceClass(PriceClass value) {
        this.priceClass = value;
    }

}
