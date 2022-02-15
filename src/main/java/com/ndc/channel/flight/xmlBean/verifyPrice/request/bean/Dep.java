
package com.ndc.channel.flight.xmlBean.verifyPrice.request.bean;

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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRQ}AircraftScheduledDateTime" minOccurs="0"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRQ}IATALocationCode"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRQ}StationName" minOccurs="0"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRQ}TerminalName" minOccurs="0"/>
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
    "aircraftScheduledDateTime",
    "iataLocationCode",
    "stationName",
    "terminalName"
})
@XmlRootElement(name = "Dep", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRQ")
public class Dep {

    @XmlElement(name = "AircraftScheduledDateTime", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRQ")
    protected String aircraftScheduledDateTime;
    @XmlElement(name = "IATALocationCode", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRQ", required = true)
    protected String iataLocationCode;
    @XmlElement(name = "StationName", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRQ")
    protected String stationName;
    @XmlElement(name = "TerminalName", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRQ")
    protected String terminalName;

    /**
     * 获取aircraftScheduledDateTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAircraftScheduledDateTime() {
        return aircraftScheduledDateTime;
    }

    /**
     * 设置aircraftScheduledDateTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAircraftScheduledDateTime(String value) {
        this.aircraftScheduledDateTime = value;
    }

    /**
     * 获取iataLocationCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIATALocationCode() {
        return iataLocationCode;
    }

    /**
     * 设置iataLocationCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIATALocationCode(String value) {
        this.iataLocationCode = value;
    }

    /**
     * 获取stationName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStationName() {
        return stationName;
    }

    /**
     * 设置stationName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStationName(String value) {
        this.stationName = value;
    }

    /**
     * 获取terminalName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTerminalName() {
        return terminalName;
    }

    /**
     * 设置terminalName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTerminalName(String value) {
        this.terminalName = value;
    }

}
