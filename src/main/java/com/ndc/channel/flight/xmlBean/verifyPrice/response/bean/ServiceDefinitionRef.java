
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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS}PaxSegmentRefID"/>
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
    "paxSegmentRefID",
    "serviceDefinitionRefID"
})
@XmlRootElement(name = "ServiceDefinitionRef", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS")
public class ServiceDefinitionRef {

    @XmlElement(name = "PaxSegmentRefID", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS", required = true)
    protected String paxSegmentRefID;
    @XmlElement(name = "ServiceDefinitionRefID", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS", required = true)
    protected String serviceDefinitionRefID;

    /**
     * 获取paxSegmentRefID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaxSegmentRefID() {
        return paxSegmentRefID;
    }

    /**
     * 设置paxSegmentRefID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaxSegmentRefID(String value) {
        this.paxSegmentRefID = value;
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
