
package com.ndc.channel.flight.xmlBean.orderPay.response.bean;

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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}Tax" minOccurs="0"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}TotalTaxAmount"/>
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
    "tax",
    "totalTaxAmount"
})
@XmlRootElement(name = "TaxSummary", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS")
public class TaxSummary {

    @XmlElement(name = "Tax", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS")
    protected Tax tax;
    @XmlElement(name = "TotalTaxAmount", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", required = true)
    protected TotalTaxAmount totalTaxAmount;

    /**
     * 获取tax属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Tax }
     *     
     */
    public Tax getTax() {
        return tax;
    }

    /**
     * 设置tax属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Tax }
     *     
     */
    public void setTax(Tax value) {
        this.tax = value;
    }

    /**
     * 获取totalTaxAmount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TotalTaxAmount }
     *     
     */
    public TotalTaxAmount getTotalTaxAmount() {
        return totalTaxAmount;
    }

    /**
     * 设置totalTaxAmount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TotalTaxAmount }
     *     
     */
    public void setTotalTaxAmount(TotalTaxAmount value) {
        this.totalTaxAmount = value;
    }

}
