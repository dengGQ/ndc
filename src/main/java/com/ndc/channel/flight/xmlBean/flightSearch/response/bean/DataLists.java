
package com.ndc.channel.flight.xmlBean.flightSearch.response.bean;

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
@XmlType(name = "", propOrder = {
    "baggageAllowanceList",
    "originDestList",
    "paxJourneyList",
    "paxList",
    "paxSegmentList",
    "priceClassList",
    "serviceDefinitionList"
})
@XmlRootElement(name = "DataLists", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS")
public class DataLists {

    @XmlElement(name = "BaggageAllowanceList", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS", required = true)
    protected BaggageAllowanceList baggageAllowanceList;
    @XmlElement(name = "OriginDestList", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS", required = true)
    protected OriginDestList originDestList;
    @XmlElement(name = "PaxJourneyList", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS", required = true)
    protected PaxJourneyList paxJourneyList;
    @XmlElement(name = "PaxList", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS", required = true)
    protected PaxList paxList;
    @XmlElement(name = "PaxSegmentList", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS", required = true)
    protected PaxSegmentList paxSegmentList;
    @XmlElement(name = "PriceClassList", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS", required = true)
    protected PriceClassList priceClassList;
    @XmlElement(name = "ServiceDefinitionList", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS", required = true)
    protected ServiceDefinitionList serviceDefinitionList;

    /**
     * 获取baggageAllowanceList属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BaggageAllowanceList }
     *     
     */
    public BaggageAllowanceList getBaggageAllowanceList() {
        return baggageAllowanceList;
    }

    /**
     * 设置baggageAllowanceList属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BaggageAllowanceList }
     *     
     */
    public void setBaggageAllowanceList(BaggageAllowanceList value) {
        this.baggageAllowanceList = value;
    }

    /**
     * 获取originDestList属性的值。
     * 
     * @return
     *     possible object is
     *     {@link OriginDestList }
     *     
     */
    public OriginDestList getOriginDestList() {
        return originDestList;
    }

    /**
     * 设置originDestList属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link OriginDestList }
     *     
     */
    public void setOriginDestList(OriginDestList value) {
        this.originDestList = value;
    }

    /**
     * 获取paxJourneyList属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PaxJourneyList }
     *     
     */
    public PaxJourneyList getPaxJourneyList() {
        return paxJourneyList;
    }

    /**
     * 设置paxJourneyList属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PaxJourneyList }
     *     
     */
    public void setPaxJourneyList(PaxJourneyList value) {
        this.paxJourneyList = value;
    }

    /**
     * 获取paxList属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PaxList }
     *     
     */
    public PaxList getPaxList() {
        return paxList;
    }

    /**
     * 设置paxList属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PaxList }
     *     
     */
    public void setPaxList(PaxList value) {
        this.paxList = value;
    }

    /**
     * 获取paxSegmentList属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PaxSegmentList }
     *     
     */
    public PaxSegmentList getPaxSegmentList() {
        return paxSegmentList;
    }

    /**
     * 设置paxSegmentList属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PaxSegmentList }
     *     
     */
    public void setPaxSegmentList(PaxSegmentList value) {
        this.paxSegmentList = value;
    }

    /**
     * 获取priceClassList属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PriceClassList }
     *     
     */
    public PriceClassList getPriceClassList() {
        return priceClassList;
    }

    /**
     * 设置priceClassList属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PriceClassList }
     *     
     */
    public void setPriceClassList(PriceClassList value) {
        this.priceClassList = value;
    }

    /**
     * 获取serviceDefinitionList属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ServiceDefinitionList }
     *     
     */
    public ServiceDefinitionList getServiceDefinitionList() {
        return serviceDefinitionList;
    }

    /**
     * 设置serviceDefinitionList属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceDefinitionList }
     *     
     */
    public void setServiceDefinitionList(ServiceDefinitionList value) {
        this.serviceDefinitionList = value;
    }

}
