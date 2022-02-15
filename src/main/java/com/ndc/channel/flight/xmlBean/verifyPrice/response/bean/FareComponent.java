
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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS}CabinType"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS}FareBasisCode"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS}FareRule" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS}FareTypeCode"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS}PaxSegmentRefID"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS}PriceClassRefID"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS}RBD"/>
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
    "cabinType",
    "fareBasisCode",
    "fareRule",
    "fareTypeCode",
    "paxSegmentRefID",
    "priceClassRefID",
    "rbd"
})
@XmlRootElement(name = "FareComponent", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS")
public class FareComponent {

    @XmlElement(name = "CabinType", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS", required = true)
    protected CabinType cabinType;
    @XmlElement(name = "FareBasisCode", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS", required = true)
    protected String fareBasisCode;
    @XmlElement(name = "FareRule", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS")
    protected List<FareRule> fareRule;
    @XmlElement(name = "FareTypeCode", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS", required = true)
    protected String fareTypeCode;
    @XmlElement(name = "PaxSegmentRefID", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS", required = true)
    protected String paxSegmentRefID;
    @XmlElement(name = "PriceClassRefID", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS", required = true)
    protected String priceClassRefID;
    @XmlElement(name = "RBD", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS", required = true)
    protected RBD rbd;

    /**
     * 获取cabinType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CabinType }
     *     
     */
    public CabinType getCabinType() {
        return cabinType;
    }

    /**
     * 设置cabinType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CabinType }
     *     
     */
    public void setCabinType(CabinType value) {
        this.cabinType = value;
    }

    /**
     * 获取fareBasisCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFareBasisCode() {
        return fareBasisCode;
    }

    /**
     * 设置fareBasisCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFareBasisCode(String value) {
        this.fareBasisCode = value;
    }

    /**
     * Gets the value of the fareRule property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fareRule property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFareRule().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FareRule }
     * 
     * 
     */
    public List<FareRule> getFareRule() {
        if (fareRule == null) {
            fareRule = new ArrayList<FareRule>();
        }
        return this.fareRule;
    }

    /**
     * 获取fareTypeCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFareTypeCode() {
        return fareTypeCode;
    }

    /**
     * 设置fareTypeCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFareTypeCode(String value) {
        this.fareTypeCode = value;
    }

    /**
     * 获取paxSegmentRefID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaxSegmentRefID() {
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
    public void setPaxSegmentRefID(String value) {
        this.paxSegmentRefID = value;
    }

    /**
     * 获取priceClassRefID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPriceClassRefID() {
        return priceClassRefID;
    }

    /**
     * 设置priceClassRefID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPriceClassRefID(String value) {
        this.priceClassRefID = value;
    }

    /**
     * 获取rbd属性的值。
     * 
     * @return
     *     possible object is
     *     {@link RBD }
     *     
     */
    public RBD getRBD() {
        return rbd;
    }

    /**
     * 设置rbd属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link RBD }
     *     
     */
    public void setRBD(RBD value) {
        this.rbd = value;
    }

}
