
package com.ndc.channel.flight.xmlBean.createOrder.request.bean;

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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderCreateRQ}EnterBeforeDate"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderCreateRQ}HostCountryCode"/>
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
    "enterBeforeDate",
    "hostCountryCode"
})
@XmlRootElement(name = "Visa", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderCreateRQ")
public class Visa {

    @XmlElement(name = "EnterBeforeDate", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderCreateRQ", required = true)
    protected String enterBeforeDate;
    @XmlElement(name = "HostCountryCode", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderCreateRQ", required = true)
    protected String hostCountryCode;

    /**
     * 获取enterBeforeDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnterBeforeDate() {
        return enterBeforeDate;
    }

    /**
     * 设置enterBeforeDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnterBeforeDate(String value) {
        this.enterBeforeDate = value;
    }

    /**
     * 获取hostCountryCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHostCountryCode() {
        return hostCountryCode;
    }

    /**
     * 设置hostCountryCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHostCountryCode(String value) {
        this.hostCountryCode = value;
    }

}
