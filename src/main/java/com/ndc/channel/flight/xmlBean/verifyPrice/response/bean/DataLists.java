
package com.ndc.channel.flight.xmlBean.verifyPrice.response.bean;

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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS}BaggageAllowanceList"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS}OriginDestList"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS}PaxJourneyList"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS}PaxSegmentList"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS}PriceClassList"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS}ServiceDefinitionList"/>
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
    "paxSegmentList",
    "priceClassList",
    "serviceDefinitionList"
})
@XmlRootElement(name = "DataLists", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS")
public class DataLists {

    @XmlElementWrapper(name = "BaggageAllowanceList", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS", required = true)
    @XmlElement(name = "BaggageAllowance", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS")
    protected List<BaggageAllowance> baggageAllowanceList;

    @XmlElementWrapper(name = "OriginDestList", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS", required = true)
    @XmlElement(name = "OriginDest", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS", required = true)
    protected List<OriginDest> originDestList;

    @XmlElementWrapper(name = "PaxJourneyList", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS", required = true)
    @XmlElement(name = "PaxJourney", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS", required = true)
    protected List<PaxJourney> paxJourneyList;

    @XmlElementWrapper(name = "PaxSegmentList", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS", required = true)
    @XmlElement(name = "PaxSegment", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS", required = true)
    protected List<PaxSegment> paxSegmentList;

    @XmlElementWrapper(name = "PriceClassList", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS", required = true)
    @XmlElement(name = "PriceClass", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS", required = true)
    protected List<PriceClass> priceClassList;

    @XmlElementWrapper(name = "ServiceDefinitionList", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS", required = true)
    @XmlElement(name = "ServiceDefinition", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS")
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
