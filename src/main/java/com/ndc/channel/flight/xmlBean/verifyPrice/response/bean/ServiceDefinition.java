
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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS}Name"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS}ServiceCode" minOccurs="0"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS}ServiceDefinitionAssociation" minOccurs="0"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS}ServiceDefinitionID"/>
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
    "name",
    "serviceCode",
    "serviceDefinitionAssociation",
    "serviceDefinitionID"
})
@XmlRootElement(name = "ServiceDefinition", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS")
public class ServiceDefinition {

    @XmlElement(name = "Name", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS", required = true)
    protected String name;
    @XmlElement(name = "ServiceCode", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS")
    protected String serviceCode;
    @XmlElement(name = "ServiceDefinitionAssociation", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS")
    protected ServiceDefinitionAssociation serviceDefinitionAssociation;
    @XmlElement(name = "ServiceDefinitionID", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS", required = true)
    protected String serviceDefinitionID;

    /**
     * 获取name属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * 设置name属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * 获取serviceCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceCode() {
        return serviceCode;
    }

    /**
     * 设置serviceCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceCode(String value) {
        this.serviceCode = value;
    }

    /**
     * 获取serviceDefinitionAssociation属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ServiceDefinitionAssociation }
     *     
     */
    public ServiceDefinitionAssociation getServiceDefinitionAssociation() {
        return serviceDefinitionAssociation;
    }

    /**
     * 设置serviceDefinitionAssociation属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceDefinitionAssociation }
     *     
     */
    public void setServiceDefinitionAssociation(ServiceDefinitionAssociation value) {
        this.serviceDefinitionAssociation = value;
    }

    /**
     * 获取serviceDefinitionID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceDefinitionID() {
        return serviceDefinitionID;
    }

    /**
     * 设置serviceDefinitionID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceDefinitionID(String value) {
        this.serviceDefinitionID = value;
    }

}
