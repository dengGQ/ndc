
package com.ndc.channel.flight.xmlBean.flightSearch.response.bean;

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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS}DiscountAmount"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS}PreDiscountedAmount"/>
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
    "discountAmount",
    "preDiscountedAmount"
})
@XmlRootElement(name = "Discount", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS")
public class Discount {

    @XmlElement(name = "DiscountAmount", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS", required = true)
    protected DiscountAmount discountAmount;
    @XmlElement(name = "PreDiscountedAmount", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS", required = true)
    protected PreDiscountedAmount preDiscountedAmount;

    /**
     * 获取discountAmount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link DiscountAmount }
     *     
     */
    public DiscountAmount getDiscountAmount() {
        return discountAmount;
    }

    /**
     * 设置discountAmount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link DiscountAmount }
     *     
     */
    public void setDiscountAmount(DiscountAmount value) {
        this.discountAmount = value;
    }

    /**
     * 获取preDiscountedAmount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PreDiscountedAmount }
     *     
     */
    public PreDiscountedAmount getPreDiscountedAmount() {
        return preDiscountedAmount;
    }

    /**
     * 设置preDiscountedAmount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PreDiscountedAmount }
     *     
     */
    public void setPreDiscountedAmount(PreDiscountedAmount value) {
        this.preDiscountedAmount = value;
    }

}
