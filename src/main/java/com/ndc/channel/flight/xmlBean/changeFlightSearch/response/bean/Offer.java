
package com.ndc.channel.flight.xmlBean.changeFlightSearch.response.bean;

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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS}AddOfferItem"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS}Desc"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS}OfferID"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS}OwnerCode"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS}OwnerTypeCode"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS}PTC_OfferParameters"/>
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
    "addOfferItem",
    "desc",
    "offerID",
    "ownerCode",
    "ownerTypeCode",
    "ptcOfferParameters"
})
@XmlRootElement(name = "Offer", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS")
public class Offer {

    @XmlElement(name = "AddOfferItem", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS", required = true)
    protected List<AddOfferItem> addOfferItem;
    @XmlElement(name = "Desc", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS", required = true)
    protected Desc desc;
    @XmlElement(name = "OfferID", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS", required = true)
    protected String offerID;
    @XmlElement(name = "OwnerCode", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS", required = true)
    protected String ownerCode;
    @XmlElement(name = "OwnerTypeCode", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS", required = true)
    protected String ownerTypeCode;
    @XmlElement(name = "PTC_OfferParameters", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS", required = true)
    protected PTCOfferParameters ptcOfferParameters;



    /**
     * 获取desc属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Desc }
     *     
     */
    public Desc getDesc() {
        return desc;
    }

    /**
     * 设置desc属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Desc }
     *     
     */
    public void setDesc(Desc value) {
        this.desc = value;
    }

    /**
     * 获取offerID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOfferID() {
        return offerID;
    }

    /**
     * 设置offerID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOfferID(String value) {
        this.offerID = value;
    }

    /**
     * 获取ownerCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOwnerCode() {
        return ownerCode;
    }

    /**
     * 设置ownerCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOwnerCode(String value) {
        this.ownerCode = value;
    }

    /**
     * 获取ownerTypeCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOwnerTypeCode() {
        return ownerTypeCode;
    }

    /**
     * 设置ownerTypeCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOwnerTypeCode(String value) {
        this.ownerTypeCode = value;
    }

    /**
     * 获取ptcOfferParameters属性的值。
     *
     * @return
     *     possible object is
     *     {@link PTCOfferParameters }
     *
     */
    public PTCOfferParameters getPTCOfferParameters() {
        return ptcOfferParameters;
    }

    /**
     * 设置ptcOfferParameters属性的值。
     *
     * @param value
     *     allowed object is
     *     {@link PTCOfferParameters }
     *
     */
    public void setPTCOfferParameters(PTCOfferParameters value) {
        this.ptcOfferParameters = value;
    }

    public List<AddOfferItem> getAddOfferItem() {
        return addOfferItem;
    }

    public void setAddOfferItem(List<AddOfferItem> addOfferItem) {
        this.addOfferItem = addOfferItem;
    }

    public PTCOfferParameters getPtcOfferParameters() {
        return ptcOfferParameters;
    }

    public void setPtcOfferParameters(PTCOfferParameters ptcOfferParameters) {
        this.ptcOfferParameters = ptcOfferParameters;
    }
}
