
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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRQ}OriginDestList"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRQ}PaxJourneyList"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRQ}PaxSegmentList"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRQ}PriceClassList"/>
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
    "originDestList",
    "paxJourneyList",
    "paxSegmentList",
    "priceClassList"
})
@XmlRootElement(name = "DataLists", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRQ")
public class DataLists {

    @XmlElement(name = "OriginDestList", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRQ", required = true)
    protected OriginDestList originDestList;
    @XmlElement(name = "PaxJourneyList", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRQ", required = true)
    protected PaxJourneyList paxJourneyList;
    @XmlElement(name = "PaxSegmentList", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRQ", required = true)
    protected PaxSegmentList paxSegmentList;
    @XmlElement(name = "PriceClassList", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRQ", required = true)
    protected PriceClassList priceClassList;

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

}
