
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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRQ}CountrySubDivision"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRQ}Date"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRQ}Station"/>
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
    "countrySubDivision",
    "date",
    "station"
})
@XmlRootElement(name = "AffinityDepRequest", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRQ")
public class AffinityDepRequest {

    @XmlElement(name = "CountrySubDivision", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRQ", required = true)
    protected CountrySubDivision countrySubDivision;
    @XmlElement(name = "Date", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRQ", required = true)
    protected String date;
    @XmlElement(name = "Station", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRQ", required = true)
    protected Station station;

    /**
     * 获取countrySubDivision属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CountrySubDivision }
     *     
     */
    public CountrySubDivision getCountrySubDivision() {
        return countrySubDivision;
    }

    /**
     * 设置countrySubDivision属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CountrySubDivision }
     *     
     */
    public void setCountrySubDivision(CountrySubDivision value) {
        this.countrySubDivision = value;
    }

    /**
     * 获取date属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDate() {
        return date;
    }

    /**
     * 设置date属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDate(String value) {
        this.date = value;
    }

    /**
     * 获取station属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Station }
     *     
     */
    public Station getStation() {
        return station;
    }

    /**
     * 设置station属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Station }
     *     
     */
    public void setStation(Station value) {
        this.station = value;
    }

}
