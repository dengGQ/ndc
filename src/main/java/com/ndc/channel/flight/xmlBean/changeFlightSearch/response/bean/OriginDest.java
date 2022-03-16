
package com.ndc.channel.flight.xmlBean.changeFlightSearch.response.bean;

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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS}DestCode"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS}OriginCode"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS}OriginDestID"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS}PaxJourneyRefID"/>
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
    "destCode",
    "originCode",
    "originDestID",
    "paxJourneyRefID"
})
@XmlRootElement(name = "OriginDest", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS")
public class OriginDest {

    @XmlElement(name = "DestCode", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS", required = true)
    protected String destCode;
    @XmlElement(name = "OriginCode", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS", required = true)
    protected String originCode;
    @XmlElement(name = "OriginDestID", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS", required = true)
    protected String originDestID;
    @XmlElement(name = "PaxJourneyRefID", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS", required = true)
    protected String paxJourneyRefID;

    /**
     * 获取destCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestCode() {
        return destCode;
    }

    /**
     * 设置destCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestCode(String value) {
        this.destCode = value;
    }

    /**
     * 获取originCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOriginCode() {
        return originCode;
    }

    /**
     * 设置originCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOriginCode(String value) {
        this.originCode = value;
    }

    /**
     * 获取originDestID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOriginDestID() {
        return originDestID;
    }

    /**
     * 设置originDestID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOriginDestID(String value) {
        this.originDestID = value;
    }

    /**
     * 获取paxJourneyRefID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaxJourneyRefID() {
        return paxJourneyRefID;
    }

    /**
     * 设置paxJourneyRefID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaxJourneyRefID(String value) {
        this.paxJourneyRefID = value;
    }

}
