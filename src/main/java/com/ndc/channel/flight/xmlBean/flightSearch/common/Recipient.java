
package com.ndc.channel.flight.xmlBean.flightSearch.common;

import javax.xml.bind.annotation.*;


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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRQ}MarketingCarrier"/>
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
    "marketingCarrier"
})
@XmlRootElement(name = "Recipient", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRQ")
public class Recipient {

    @XmlElement(name = "MarketingCarrier", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRQ", required = true)
    protected MarketingCarrier marketingCarrier;

    public Recipient() {
        this.marketingCarrier = new MarketingCarrier();
    }

    public Recipient(MarketingCarrier marketingCarrier) {
        this.marketingCarrier = marketingCarrier;
    }

    /**
     * 获取marketingCarrier属性的值。
     * 
     * @return
     *     possible object is
     *     {@link MarketingCarrier }
     *     
     */
    public MarketingCarrier getMarketingCarrier() {
        return marketingCarrier;
    }

    /**
     * 设置marketingCarrier属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link MarketingCarrier }
     *     
     */
    public void setMarketingCarrier(MarketingCarrier value) {
        this.marketingCarrier = value;
    }

}
