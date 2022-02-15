
package com.ndc.channel.flight.xmlBean.verifyPrice.request.bean;

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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRQ}PaxJourneyID"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRQ}PaxSegmentRefID"/>
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
    "paxJourneyID",
    "paxSegmentRefID"
})
@XmlRootElement(name = "PaxJourney", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRQ")
public class PaxJourney {

    @XmlElement(name = "PaxJourneyID", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRQ", required = true)
    protected String paxJourneyID;
    @XmlElement(name = "PaxSegmentRefID", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRQ", required = true)
    protected List<String> paxSegmentRefID;

    /**
     * 获取paxJourneyID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaxJourneyID() {
        return paxJourneyID;
    }

    /**
     * 设置paxJourneyID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaxJourneyID(String value) {
        this.paxJourneyID = value;
    }

    /**
     * 获取paxSegmentRefID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public List<String> getPaxSegmentRefID() {
        return paxSegmentRefID;
    }

    /**
     * 设置paxSegmentRefID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaxSegmentRefID(List<String> value) {
        this.paxSegmentRefID = value;
    }

}
