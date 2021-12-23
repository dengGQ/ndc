
package com.ndc.channel.flight.xmlBean.flightSearch.request.bean;

import javax.xml.bind.annotation.*;


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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRQ}CountrySubDivisionCode"/>
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
    "countrySubDivisionCode"
})
@XmlRootElement(name = "CountrySubDivision", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRQ")
public class CountrySubDivision {

    @XmlElement(name = "CountrySubDivisionCode", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRQ", required = true)
    protected String countrySubDivisionCode;

    /**
     * 获取countrySubDivisionCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountrySubDivisionCode() {
        return countrySubDivisionCode;
    }

    /**
     * 设置countrySubDivisionCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountrySubDivisionCode(String value) {
        this.countrySubDivisionCode = value;
    }

}
