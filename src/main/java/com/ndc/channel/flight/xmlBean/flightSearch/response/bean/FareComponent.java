
package com.ndc.channel.flight.xmlBean.flightSearch.response.bean;

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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS}CabinType"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS}FareBasisCode"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS}FareRule" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS}FareTypeCode"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS}PaxSegmentRefID"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS}Price"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS}PriceClassRefID"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS}RBD"/>
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
    "price",
    "priceClassRefID",
    "rbd"
})
@XmlRootElement(name = "FareComponent", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS")
public class FareComponent {

    @XmlElement(name = "CabinType", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS", required = true)
    protected CabinType cabinType;
    @XmlElement(name = "FareBasisCode", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS", required = true)
    protected String fareBasisCode;
    @XmlElement(name = "FareRule", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS")
    protected List<FareRule> fareRule;
    @XmlElement(name = "FareTypeCode", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS", required = true)
    protected String fareTypeCode;
    @XmlElement(name = "PaxSegmentRefID", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS", required = true)
    protected List<String> paxSegmentRefID;
    @XmlElement(name = "Price", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS", required = true)
    protected Price price;
    @XmlElement(name = "PriceClassRefID", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS", required = true)
    protected String priceClassRefID;
    @XmlElement(name = "RBD", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS", required = true)
    protected RBD rbd;

    public CabinType getCabinType() {
        return cabinType;
    }

    public void setCabinType(CabinType cabinType) {
        this.cabinType = cabinType;
    }

    public String getFareBasisCode() {
        return fareBasisCode;
    }

    public void setFareBasisCode(String fareBasisCode) {
        this.fareBasisCode = fareBasisCode;
    }

    public List<FareRule> getFareRule() {
        return fareRule;
    }

    public void setFareRule(List<FareRule> fareRule) {
        this.fareRule = fareRule;
    }

    public String getFareTypeCode() {
        return fareTypeCode;
    }

    public void setFareTypeCode(String fareTypeCode) {
        this.fareTypeCode = fareTypeCode;
    }

    public List<String> getPaxSegmentRefID() {
        return paxSegmentRefID;
    }

    public void setPaxSegmentRefID(List<String> paxSegmentRefID) {
        this.paxSegmentRefID = paxSegmentRefID;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public String getPriceClassRefID() {
        return priceClassRefID;
    }

    public void setPriceClassRefID(String priceClassRefID) {
        this.priceClassRefID = priceClassRefID;
    }

    public RBD getRbd() {
        return rbd;
    }

    public void setRbd(RBD rbd) {
        this.rbd = rbd;
    }
}
