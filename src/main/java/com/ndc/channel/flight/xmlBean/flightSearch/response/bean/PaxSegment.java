
package com.ndc.channel.flight.xmlBean.flightSearch.response.bean;

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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS}Arrival"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS}DatedOperatingLeg"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS}Dep"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS}Duration"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS}MarketingCarrierInfo"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS}OperatingCarrierInfo"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS}PaxSegmentID"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS}SecureFlightInd"/>
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
    "paxSegmentID",
    "secureFlightInd"
})
@XmlRootElement(name = "PaxSegment", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS")
public class PaxSegment {

    @XmlElement(name = "Arrival", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS", required = true)
    protected Arrival arrival;
    @XmlElement(name = "DatedOperatingLeg", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS", required = true)
    protected List<DatedOperatingLeg> datedOperatingLeg;
    @XmlElement(name = "Dep", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS", required = true)
    protected Dep dep;
    @XmlElement(name = "Duration", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS", required = true)
    protected String duration;
    @XmlElement(name = "MarketingCarrierInfo", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS", required = true)
    protected MarketingCarrierInfo marketingCarrierInfo;
    @XmlElement(name = "OperatingCarrierInfo", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS", required = true)
    protected OperatingCarrierInfo operatingCarrierInfo;
    @XmlElement(name = "PaxSegmentID", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS", required = true)
    protected String paxSegmentID;
    @XmlElement(name = "SecureFlightInd", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS", required = true)
    protected String secureFlightInd;

    public Arrival getArrival() {
        return arrival;
    }

    public void setArrival(Arrival arrival) {
        this.arrival = arrival;
    }

    public List<DatedOperatingLeg> getDatedOperatingLeg() {
        return datedOperatingLeg;
    }

    public void setDatedOperatingLeg(List<DatedOperatingLeg> datedOperatingLeg) {
        this.datedOperatingLeg = datedOperatingLeg;
    }

    public Dep getDep() {
        return dep;
    }

    public void setDep(Dep dep) {
        this.dep = dep;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public MarketingCarrierInfo getMarketingCarrierInfo() {
        return marketingCarrierInfo;
    }

    public void setMarketingCarrierInfo(MarketingCarrierInfo marketingCarrierInfo) {
        this.marketingCarrierInfo = marketingCarrierInfo;
    }

    public OperatingCarrierInfo getOperatingCarrierInfo() {
        return operatingCarrierInfo;
    }

    public void setOperatingCarrierInfo(OperatingCarrierInfo operatingCarrierInfo) {
        this.operatingCarrierInfo = operatingCarrierInfo;
    }

    public String getPaxSegmentID() {
        return paxSegmentID;
    }

    public void setPaxSegmentID(String paxSegmentID) {
        this.paxSegmentID = paxSegmentID;
    }

    public String getSecureFlightInd() {
        return secureFlightInd;
    }

    public void setSecureFlightInd(String secureFlightInd) {
        this.secureFlightInd = secureFlightInd;
    }
}
