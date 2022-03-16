
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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ}Arrival"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ}CabinType"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ}DatedOperatingLeg"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ}Dep"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ}Duration"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ}MarketingCarrierInfo"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ}OperatingCarrierInfo"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ}PaxSegmentID"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ}SecureFlightInd"/>
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
    "arrival",
    "cabinType",
    "datedOperatingLeg",
    "dep",
    "duration",
    "marketingCarrierInfo",
    "operatingCarrierInfo",
    "paxSegmentID",
    "secureFlightInd"
})
@XmlRootElement(name = "PaxSegment", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ")
public class PaxSegment {

    @XmlElement(name = "Arrival", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", required = true)
    protected Arrival arrival;
    @XmlElement(name = "CabinType", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", required = true)
    protected CabinType cabinType;
    @XmlElement(name = "DatedOperatingLeg", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", required = true)
    protected DatedOperatingLeg datedOperatingLeg;
    @XmlElement(name = "Dep", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", required = true)
    protected Dep dep;
    @XmlElement(name = "Duration", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", required = true)
    protected String duration;
    @XmlElement(name = "MarketingCarrierInfo", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", required = true)
    protected MarketingCarrierInfo marketingCarrierInfo;
    @XmlElement(name = "OperatingCarrierInfo", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", required = true)
    protected OperatingCarrierInfo operatingCarrierInfo;
    @XmlElement(name = "PaxSegmentID", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", required = true)
    protected String paxSegmentID;
    @XmlElement(name = "SecureFlightInd", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", required = true)
    protected String secureFlightInd;

    /**
     * 获取arrival属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Arrival }
     *     
     */
    public Arrival getArrival() {
        return arrival;
    }

    /**
     * 设置arrival属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Arrival }
     *     
     */
    public void setArrival(Arrival value) {
        this.arrival = value;
    }

    /**
     * 获取cabinType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CabinType }
     *     
     */
    public CabinType getCabinType() {
        return cabinType;
    }

    /**
     * 设置cabinType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CabinType }
     *     
     */
    public void setCabinType(CabinType value) {
        this.cabinType = value;
    }

    /**
     * 获取datedOperatingLeg属性的值。
     * 
     * @return
     *     possible object is
     *     {@link DatedOperatingLeg }
     *     
     */
    public DatedOperatingLeg getDatedOperatingLeg() {
        return datedOperatingLeg;
    }

    /**
     * 设置datedOperatingLeg属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link DatedOperatingLeg }
     *     
     */
    public void setDatedOperatingLeg(DatedOperatingLeg value) {
        this.datedOperatingLeg = value;
    }

    /**
     * 获取dep属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Dep }
     *     
     */
    public Dep getDep() {
        return dep;
    }

    /**
     * 设置dep属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Dep }
     *     
     */
    public void setDep(Dep value) {
        this.dep = value;
    }

    /**
     * 获取duration属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDuration() {
        return duration;
    }

    /**
     * 设置duration属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDuration(String value) {
        this.duration = value;
    }

    /**
     * 获取marketingCarrierInfo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link MarketingCarrierInfo }
     *     
     */
    public MarketingCarrierInfo getMarketingCarrierInfo() {
        return marketingCarrierInfo;
    }

    /**
     * 设置marketingCarrierInfo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link MarketingCarrierInfo }
     *     
     */
    public void setMarketingCarrierInfo(MarketingCarrierInfo value) {
        this.marketingCarrierInfo = value;
    }

    /**
     * 获取operatingCarrierInfo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link OperatingCarrierInfo }
     *     
     */
    public OperatingCarrierInfo getOperatingCarrierInfo() {
        return operatingCarrierInfo;
    }

    /**
     * 设置operatingCarrierInfo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link OperatingCarrierInfo }
     *     
     */
    public void setOperatingCarrierInfo(OperatingCarrierInfo value) {
        this.operatingCarrierInfo = value;
    }

    /**
     * 获取paxSegmentID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaxSegmentID() {
        return paxSegmentID;
    }

    /**
     * 设置paxSegmentID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaxSegmentID(String value) {
        this.paxSegmentID = value;
    }

    /**
     * 获取secureFlightInd属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSecureFlightInd() {
        return secureFlightInd;
    }

    /**
     * 设置secureFlightInd属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSecureFlightInd(String value) {
        this.secureFlightInd = value;
    }

}
