
package com.ndc.channel.flight.xmlBean.verifyPrice.request.bean;

import com.ndc.channel.flight.xmlBean.verifyPrice.common.CommonRQ;

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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRQ}MessageDoc"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRQ}Party"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRQ}PayloadAttributes"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRQ}Request"/>
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
    "request"
})
@XmlRootElement(name = "IATA_OfferPriceRQ", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRQ")
public class IATAOfferPriceRQ extends CommonRQ {

    @XmlElement(name = "Request", required = true, namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRQ")
    protected Request request;

    /**
     * 获取request属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Request }
     *     
     */
    public Request getRequest() {
        return request;
    }

    /**
     * 设置request属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Request }
     *     
     */
    public void setRequest(Request value) {
        this.request = value;
    }

}
