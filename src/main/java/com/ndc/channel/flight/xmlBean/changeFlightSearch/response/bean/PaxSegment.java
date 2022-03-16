
package com.ndc.channel.flight.xmlBean.changeFlightSearch.response.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;


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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS}Arrival"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS}DatedOperatingLeg"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS}Dep"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS}Duration"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS}MarketingCarrierInfo"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS}OperatingCarrierInfo"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS}PaxSegmentID"/>
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
    "datedOperatingLeg",
    "dep",
    "duration",
    "marketingCarrierInfo",
    "operatingCarrierInfo",
    "paxSegmentID"
})
@XmlRootElement(name = "PaxSegment", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS")
public class PaxSegment {

    @XmlElement(name = "Arrival", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS", required = true)
    protected Arrival arrival;
    @XmlElement(name = "DatedOperatingLeg", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS", required = true)
    protected List<DatedOperatingLeg> datedOperatingLeg;
    @XmlElement(name = "Dep", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS", required = true)
    protected Dep dep;
    @XmlElement(name = "Duration", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS", required = true)
    protected String duration;
    @XmlElement(name = "MarketingCarrierInfo", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS", required = true)
    protected MarketingCarrierInfo marketingCarrierInfo;
    @XmlElement(name = "OperatingCarrierInfo", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS", required = true)
    protected OperatingCarrierInfo operatingCarrierInfo;
    @XmlElement(name = "PaxSegmentID", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS", required = true)
    protected String paxSegmentID;

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

    public List<DatedOperatingLeg> getDatedOperatingLeg() {
        return datedOperatingLeg;
    }

    public void setDatedOperatingLeg(List<DatedOperatingLeg> datedOperatingLeg) {
        this.datedOperatingLeg = datedOperatingLeg;
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

}
