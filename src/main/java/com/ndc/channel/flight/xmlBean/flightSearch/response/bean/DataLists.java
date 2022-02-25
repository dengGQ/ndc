
package com.ndc.channel.flight.xmlBean.flightSearch.response.bean;

import javax.xml.bind.annotation.*;
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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS}BaggageAllowanceList"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS}OriginDestList"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS}PaxJourneyList"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS}PaxList"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS}PaxSegmentList"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS}PriceClassList"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS}ServiceDefinitionList"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "DataLists", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS")
public class DataLists {

    @XmlElementWrapper(name = "BaggageAllowanceList", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS")
    @XmlElement(name = "BaggageAllowance", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS")
    protected List<BaggageAllowance> baggageAllowanceList;

    @XmlElementWrapper(name = "OriginDestList", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS")
    @XmlElement(name = "OriginDest", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS", required = true)
    protected List<OriginDest> originDestList;

    @XmlElementWrapper(name = "PaxJourneyList", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS")
    @XmlElement(name = "PaxJourney", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS")
    protected List<PaxJourney> paxJourneyList;

    @XmlElementWrapper(name = "PaxList", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS")
    @XmlElement(name = "Pax", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS")
    protected List<Pax> paxList;

    @XmlElementWrapper(name = "PaxSegmentList", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS")
    @XmlElement(name = "PaxSegment", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS")
    protected List<PaxSegment> paxSegmentList;

    @XmlElementWrapper(name = "PriceClassList", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS")
    @XmlElement(name = "PriceClass", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS", required = true)
    protected List<PriceClass> priceClassList;

    @XmlElementWrapper(name = "ServiceDefinitionList", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS")
    @XmlElement(name = "ServiceDefinition", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS", required = true)
    protected List<ServiceDefinition> serviceDefinitionList;

    public List<BaggageAllowance> getBaggageAllowanceList() {
        return baggageAllowanceList;
    }

    public void setBaggageAllowanceList(List<BaggageAllowance> baggageAllowanceList) {
        this.baggageAllowanceList = baggageAllowanceList;
    }

    public List<OriginDest> getOriginDestList() {
        return originDestList;
    }

    public void setOriginDestList(List<OriginDest> originDestList) {
        this.originDestList = originDestList;
    }

    public List<PaxJourney> getPaxJourneyList() {
        return paxJourneyList;
    }

    public void setPaxJourneyList(List<PaxJourney> paxJourneyList) {
        this.paxJourneyList = paxJourneyList;
    }

    public List<Pax> getPaxList() {
        return paxList;
    }

    public void setPaxList(List<Pax> paxList) {
        this.paxList = paxList;
    }

    public List<PaxSegment> getPaxSegmentList() {
        return paxSegmentList;
    }

    public void setPaxSegmentList(List<PaxSegment> paxSegmentList) {
        this.paxSegmentList = paxSegmentList;
    }

    public List<PriceClass> getPriceClassList() {
        return priceClassList;
    }

    public void setPriceClassList(List<PriceClass> priceClassList) {
        this.priceClassList = priceClassList;
    }

    public List<ServiceDefinition> getServiceDefinitionList() {
        return serviceDefinitionList;
    }

    public void setServiceDefinitionList(List<ServiceDefinition> serviceDefinitionList) {
        this.serviceDefinitionList = serviceDefinitionList;
    }
}
