
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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS}BaggageAllowanceRefID" minOccurs="0"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS}ServiceBundle" minOccurs="0"/>
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
    "baggageAllowanceRefID",
    "serviceBundle"
})
@XmlRootElement(name = "ServiceDefinitionAssociation", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS")
public class ServiceDefinitionAssociation {

    @XmlElement(name = "BaggageAllowanceRefID", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS")
    protected String baggageAllowanceRefID;
    @XmlElement(name = "ServiceBundle", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS")
    protected ServiceBundle serviceBundle;

    /**
     * 获取baggageAllowanceRefID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBaggageAllowanceRefID() {
        return baggageAllowanceRefID;
    }

    /**
     * 设置baggageAllowanceRefID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBaggageAllowanceRefID(String value) {
        this.baggageAllowanceRefID = value;
    }

    /**
     * 获取serviceBundle属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ServiceBundle }
     *     
     */
    public ServiceBundle getServiceBundle() {
        return serviceBundle;
    }

    /**
     * 设置serviceBundle属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceBundle }
     *     
     */
    public void setServiceBundle(ServiceBundle value) {
        this.serviceBundle = value;
    }

}
