
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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRQ}AgencyID"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRQ}IATANumber"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRQ}Name"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRQ}PseudoCityID"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRQ}TypeCode"/>
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
    "agencyID",
    "iataNumber",
    "name",
    "pseudoCityID",
    "typeCode"
})
@XmlRootElement(name = "TravelAgency", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRQ")
public class TravelAgency {

    @XmlElement(name = "AgencyID", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRQ", required = true)
    protected String agencyID;
    @XmlElement(name = "IATANumber", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRQ", required = true)
    protected String iataNumber;
    @XmlElement(name = "Name", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRQ", required = true)
    protected String name;
    @XmlElement(name = "PseudoCityID", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRQ", required = true)
    protected String pseudoCityID;
    @XmlElement(name = "TypeCode", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRQ", required = true)
    protected String typeCode;

    /**
     * 获取agencyID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAgencyID() {
        return agencyID;
    }

    /**
     * 设置agencyID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAgencyID(String value) {
        this.agencyID = value;
    }

    /**
     * 获取iataNumber属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIATANumber() {
        return iataNumber;
    }

    /**
     * 设置iataNumber属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIATANumber(String value) {
        this.iataNumber = value;
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
     * 获取pseudoCityID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPseudoCityID() {
        return pseudoCityID;
    }

    /**
     * 设置pseudoCityID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPseudoCityID(String value) {
        this.pseudoCityID = value;
    }

    /**
     * 获取typeCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTypeCode() {
        return typeCode;
    }

    /**
     * 设置typeCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTypeCode(String value) {
        this.typeCode = value;
    }

}
