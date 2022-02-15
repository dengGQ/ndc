
package com.ndc.channel.flight.xmlBean.verifyPrice.response.bean;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS}FareDetail"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS}OfferItemID"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS}Price"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS}Service" maxOccurs="unbounded" minOccurs="0"/>
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
    "fareDetail",
    "offerItemID",
    "price",
    "service"
})
@XmlRootElement(name = "OfferItem", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS")
public class OfferItem {

    @XmlElement(name = "FareDetail", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS", required = true)
    protected FareDetail fareDetail;
    @XmlElement(name = "OfferItemID", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS", required = true)
    protected String offerItemID;
    @XmlElement(name = "Price", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS", required = true)
    protected Price price;
    @XmlElement(name = "Service", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS")
    protected List<Service> service;

    /**
     * 获取fareDetail属性的值。
     * 
     * @return
     *     possible object is
     *     {@link FareDetail }
     *     
     */
    public FareDetail getFareDetail() {
        return fareDetail;
    }

    /**
     * 设置fareDetail属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link FareDetail }
     *     
     */
    public void setFareDetail(FareDetail value) {
        this.fareDetail = value;
    }

    /**
     * 获取offerItemID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOfferItemID() {
        return offerItemID;
    }

    /**
     * 设置offerItemID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOfferItemID(String value) {
        this.offerItemID = value;
    }

    /**
     * 获取price属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Price }
     *     
     */
    public Price getPrice() {
        return price;
    }

    /**
     * 设置price属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Price }
     *     
     */
    public void setPrice(Price value) {
        this.price = value;
    }

    /**
     * Gets the value of the service property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the service property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getService().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Service }
     * 
     * 
     */
    public List<Service> getService() {
        if (service == null) {
            service = new ArrayList<Service>();
        }
        return this.service;
    }

}
