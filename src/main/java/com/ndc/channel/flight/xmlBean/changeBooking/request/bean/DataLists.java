
package com.ndc.channel.flight.xmlBean.changeBooking.request.bean;

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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ}ContactInfoList"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ}PaxList"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ}PaxSegmentList"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ}ServiceDefinitionList"/>
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
    "contactInfoList",
    "paxList",
    "paxSegmentList",
    "serviceDefinitionList"
})
@XmlRootElement(name = "DataLists", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ")
public class DataLists {

    @XmlElement(name = "ContactInfoList", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", required = true)
    protected ContactInfoList contactInfoList;
    @XmlElement(name = "PaxList", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", required = true)
    protected PaxList paxList;
    @XmlElement(name = "PaxSegmentList", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", required = true)
    protected PaxSegmentList paxSegmentList;
    @XmlElement(name = "ServiceDefinitionList", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", required = true)
    protected ServiceDefinitionList serviceDefinitionList;

    /**
     * 获取contactInfoList属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ContactInfoList }
     *     
     */
    public ContactInfoList getContactInfoList() {
        return contactInfoList;
    }

    /**
     * 设置contactInfoList属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ContactInfoList }
     *     
     */
    public void setContactInfoList(ContactInfoList value) {
        this.contactInfoList = value;
    }

    /**
     * 获取paxList属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PaxList }
     *     
     */
    public PaxList getPaxList() {
        return paxList;
    }

    /**
     * 设置paxList属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PaxList }
     *     
     */
    public void setPaxList(PaxList value) {
        this.paxList = value;
    }

    /**
     * 获取paxSegmentList属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PaxSegmentList }
     *     
     */
    public PaxSegmentList getPaxSegmentList() {
        return paxSegmentList;
    }

    /**
     * 设置paxSegmentList属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PaxSegmentList }
     *     
     */
    public void setPaxSegmentList(PaxSegmentList value) {
        this.paxSegmentList = value;
    }

    /**
     * 获取serviceDefinitionList属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ServiceDefinitionList }
     *     
     */
    public ServiceDefinitionList getServiceDefinitionList() {
        return serviceDefinitionList;
    }

    /**
     * 设置serviceDefinitionList属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceDefinitionList }
     *     
     */
    public void setServiceDefinitionList(ServiceDefinitionList value) {
        this.serviceDefinitionList = value;
    }

}
