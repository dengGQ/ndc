
package com.ndc.channel.flight.xmlBean.createOrder.response.bean;

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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}BaseAmount"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}TaxSummary"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}TotalAmount"/>
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
    "baseAmount",
    "taxSummary",
    "totalAmount"
})
@XmlRootElement(name = "TotalPrice", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS")
public class TotalPrice {

    @XmlElement(name = "BaseAmount", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", required = true)
    protected BaseAmount baseAmount;
    @XmlElement(name = "TaxSummary", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", required = true)
    protected TaxSummary taxSummary;
    @XmlElement(name = "TotalAmount", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", required = true)
    protected TotalAmount totalAmount;

    /**
     * 获取baseAmount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BaseAmount }
     *     
     */
    public BaseAmount getBaseAmount() {
        return baseAmount;
    }

    /**
     * 设置baseAmount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BaseAmount }
     *     
     */
    public void setBaseAmount(BaseAmount value) {
        this.baseAmount = value;
    }

    /**
     * 获取taxSummary属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TaxSummary }
     *     
     */
    public TaxSummary getTaxSummary() {
        return taxSummary;
    }

    /**
     * 设置taxSummary属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TaxSummary }
     *     
     */
    public void setTaxSummary(TaxSummary value) {
        this.taxSummary = value;
    }

    /**
     * 获取totalAmount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TotalAmount }
     *     
     */
    public TotalAmount getTotalAmount() {
        return totalAmount;
    }

    /**
     * 设置totalAmount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TotalAmount }
     *     
     */
    public void setTotalAmount(TotalAmount value) {
        this.totalAmount = value;
    }

}
