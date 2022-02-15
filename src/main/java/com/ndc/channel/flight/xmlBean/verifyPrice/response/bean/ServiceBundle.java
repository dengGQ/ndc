
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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS}MaximumServiceQty"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS}ServiceDefinitionRefID"/>
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
    "maximumServiceQty",
    "serviceDefinitionRefID"
})
@XmlRootElement(name = "ServiceBundle", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS")
public class ServiceBundle {

    @XmlElement(name = "MaximumServiceQty", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS", required = true)
    protected String maximumServiceQty;
    @XmlElement(name = "ServiceDefinitionRefID", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS", required = true)
    protected String serviceDefinitionRefID;

    /**
     * 获取maximumServiceQty属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaximumServiceQty() {
        return maximumServiceQty;
    }

    /**
     * 设置maximumServiceQty属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaximumServiceQty(String value) {
        this.maximumServiceQty = value;
    }

    /**
     * 获取serviceDefinitionRefID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceDefinitionRefID() {
        return serviceDefinitionRefID;
    }

    /**
     * 设置serviceDefinitionRefID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceDefinitionRefID(String value) {
        this.serviceDefinitionRefID = value;
    }

}
