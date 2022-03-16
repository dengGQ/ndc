
package com.ndc.channel.flight.xmlBean.changeFlightSearch.response.bean;

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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS}BaggageAllowanceList"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS}OriginDestList"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS}PaxJourneyList"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS}PaxList"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS}PaxSegmentList"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS}PriceClassList"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS}ServiceDefinitionList"/>
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
    "baggageAllowanceList",
    "originDestList",
    "paxJourneyList",
    "paxList",
    "paxSegmentList",
    "priceClassList",
    "serviceDefinitionList"
})
@XmlRootElement(name = "DataLists", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS")
public class DataLists {

    @XmlElement(name = "BaggageAllowanceList", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS", required = true)
    @XmlElementWrapper(name = "BaggageAllowance")
    protected List<BaggageAllowance> baggageAllowanceList;

    @XmlElement(name = "OriginDestList", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS", required = true)
    @XmlElementWrapper(name = "OriginDest")
    protected List<OriginDest> originDestList;

    @XmlElement(name = "PaxJourneyList", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS", required = true)
    @XmlElementWrapper(name = "PaxJourney")
    protected List<PaxJourney> paxJourneyList;

    @XmlElement(name = "PaxList", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS", required = true)
    @XmlElementWrapper(name = "Pax")
    protected List<Pax> paxList;
    
    @XmlElement(name = "PaxSegmentList", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS", required = true)
    @XmlElementWrapper(name = "PaxSegment")
    protected List<PaxSegment> paxSegmentList;

    @XmlElement(name = "PriceClassList", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS", required = true)
    @XmlElementWrapper(name = "PriceClass")
    protected List<PriceClass> priceClassList;

    @XmlElement(name = "ServiceDefinitionList", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS", required = true)
    @XmlElementWrapper(name = "ServiceDefinition")
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
