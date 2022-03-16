
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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ}CountryDialingCode"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ}PhoneNumber"/>
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
    "countryDialingCode",
    "phoneNumber"
})
@XmlRootElement(name = "Phone", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ")
public class Phone {

    @XmlElement(name = "CountryDialingCode", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", required = true)
    protected String countryDialingCode;
    @XmlElement(name = "PhoneNumber", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", required = true)
    protected String phoneNumber;

    /**
     * 获取countryDialingCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountryDialingCode() {
        return countryDialingCode;
    }

    /**
     * 设置countryDialingCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountryDialingCode(String value) {
        this.countryDialingCode = value;
    }

    /**
     * 获取phoneNumber属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * 设置phoneNumber属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhoneNumber(String value) {
        this.phoneNumber = value;
    }

}
