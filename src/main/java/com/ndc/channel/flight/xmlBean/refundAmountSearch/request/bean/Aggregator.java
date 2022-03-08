
package com.ndc.channel.flight.xmlBean.refundAmountSearch.request.bean;

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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ}AggregatorID"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ}Name"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ}ContactInfoRefID"/>
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
    "aggregatorID",
    "name",
    "contactInfoRefID"
})
@XmlRootElement(name = "Aggregator", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ")
public class Aggregator {

    @XmlElement(name = "AggregatorID", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", required = true)
    protected String aggregatorID;
    @XmlElement(name = "Name", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", required = true)
    protected String name;
    @XmlElement(name = "ContactInfoRefID", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", required = true)
    protected String contactInfoRefID;

    /**
     * 获取aggregatorID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAggregatorID() {
        return aggregatorID;
    }

    /**
     * 设置aggregatorID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAggregatorID(String value) {
        this.aggregatorID = value;
    }

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
     * 获取contactInfoRefID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactInfoRefID() {
        return contactInfoRefID;
    }

    /**
     * 设置contactInfoRefID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactInfoRefID(String value) {
        this.contactInfoRefID = value;
    }

}
