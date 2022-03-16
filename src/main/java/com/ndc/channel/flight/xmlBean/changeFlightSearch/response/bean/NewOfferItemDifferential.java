
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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS}Amount"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS}TaxSummary"/>
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
    "amount",
    "taxSummary"
})
@XmlRootElement(name = "NewOfferItemDifferential", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS")
public class NewOfferItemDifferential {

    @XmlElement(name = "Amount", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS", required = true)
    protected Amount amount;
    @XmlElement(name = "TaxSummary", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS", required = true)
    protected TaxSummary taxSummary;

    /**
     * 获取amount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Amount }
     *     
     */
    public Amount getAmount() {
        return amount;
    }

    /**
     * 设置amount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Amount }
     *     
     */
    public void setAmount(Amount value) {
        this.amount = value;
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

}
