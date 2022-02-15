
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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS}ServiceAssociations"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS}ServiceID"/>
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
    "serviceAssociations",
    "serviceID"
})
@XmlRootElement(name = "Service", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS")
public class Service {

    @XmlElement(name = "ServiceAssociations", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS", required = true)
    protected ServiceAssociations serviceAssociations;
    @XmlElement(name = "ServiceID", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS", required = true)
    protected String serviceID;

    /**
     * 获取serviceAssociations属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ServiceAssociations }
     *     
     */
    public ServiceAssociations getServiceAssociations() {
        return serviceAssociations;
    }

    /**
     * 设置serviceAssociations属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceAssociations }
     *     
     */
    public void setServiceAssociations(ServiceAssociations value) {
        this.serviceAssociations = value;
    }

    /**
     * 获取serviceID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceID() {
        return serviceID;
    }

    /**
     * 设置serviceID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceID(String value) {
        this.serviceID = value;
    }

}
