
package com.ndc.channel.flight.xmlBean.flightSearch.common;

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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRQ}AirlineDesigCode"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRQ}DuplicateDesigInd"/>
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
    "airlineDesigCode",
    "duplicateDesigInd"
})
@XmlRootElement(name = "MarketingCarrier", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRQ")
public class MarketingCarrier {

    @XmlElement(name = "AirlineDesigCode", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRQ", required = true)
    protected String airlineDesigCode;
    @XmlElement(name = "DuplicateDesigInd", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRQ", required = true)
    protected String duplicateDesigInd;

    public MarketingCarrier() {
        this.airlineDesigCode = "MU";
        this.duplicateDesigInd = "false";
    }

    public MarketingCarrier(String airlineDesigCode, String duplicateDesigInd) {
        this.airlineDesigCode = airlineDesigCode;
        this.duplicateDesigInd = duplicateDesigInd;
    }

    /**
     * 获取airlineDesigCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAirlineDesigCode() {
        return airlineDesigCode;
    }

    /**
     * 设置airlineDesigCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAirlineDesigCode(String value) {
        this.airlineDesigCode = value;
    }

    /**
     * 获取duplicateDesigInd属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDuplicateDesigInd() {
        return duplicateDesigInd;
    }

    /**
     * 设置duplicateDesigInd属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDuplicateDesigInd(String value) {
        this.duplicateDesigInd = value;
    }

}
