
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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRQ}PaxJourney"/>
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
    "paxJourney"
})
@XmlRootElement(name = "PaxJourneyList", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRQ")
public class PaxJourneyList {

    @XmlElement(name = "PaxJourney", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRQ", required = true)
    protected PaxJourney paxJourney;

    /**
     * 获取paxJourney属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PaxJourney }
     *     
     */
    public PaxJourney getPaxJourney() {
        return paxJourney;
    }

    /**
     * 设置paxJourney属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PaxJourney }
     *     
     */
    public void setPaxJourney(PaxJourney value) {
        this.paxJourney = value;
    }

}
