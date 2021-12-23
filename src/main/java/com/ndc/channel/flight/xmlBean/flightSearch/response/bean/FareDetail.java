
package com.ndc.channel.flight.xmlBean.flightSearch.response.bean;

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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS}FareComponent"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS}FareIndCode"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS}FarePriceType"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS}FareRefText"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS}PricingSystemCodeText"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS}StatisticalCodeText"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS}TourCode"/>
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
    "fareComponent",
    "fareIndCode",
    "farePriceType",
    "fareRefText",
    "pricingSystemCodeText",
    "statisticalCodeText",
    "tourCode"
})
@XmlRootElement(name = "FareDetail", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS")
public class FareDetail {

    @XmlElement(name = "FareComponent", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS", required = true)
    protected List<FareComponent> fareComponent;
    @XmlElement(name = "FareIndCode", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS", required = true)
    protected String fareIndCode;
    @XmlElement(name = "FarePriceType", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS", required = true)
    protected List<FarePriceType> farePriceType;
    @XmlElement(name = "FareRefText", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS", required = true)
    protected String fareRefText;
    @XmlElement(name = "PricingSystemCodeText", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS", required = true)
    protected String pricingSystemCodeText;
    @XmlElement(name = "StatisticalCodeText", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS", required = true)
    protected String statisticalCodeText;
    @XmlElement(name = "TourCode", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS", required = true)
    protected String tourCode;

    public List<FareComponent> getFareComponent() {
        return fareComponent;
    }

    public void setFareComponent(List<FareComponent> fareComponent) {
        this.fareComponent = fareComponent;
    }

    public String getFareIndCode() {
        return fareIndCode;
    }

    public void setFareIndCode(String fareIndCode) {
        this.fareIndCode = fareIndCode;
    }

    public List<FarePriceType> getFarePriceType() {
        return farePriceType;
    }

    public void setFarePriceType(List<FarePriceType> farePriceType) {
        this.farePriceType = farePriceType;
    }

    public String getFareRefText() {
        return fareRefText;
    }

    public void setFareRefText(String fareRefText) {
        this.fareRefText = fareRefText;
    }

    public String getPricingSystemCodeText() {
        return pricingSystemCodeText;
    }

    public void setPricingSystemCodeText(String pricingSystemCodeText) {
        this.pricingSystemCodeText = pricingSystemCodeText;
    }

    public String getStatisticalCodeText() {
        return statisticalCodeText;
    }

    public void setStatisticalCodeText(String statisticalCodeText) {
        this.statisticalCodeText = statisticalCodeText;
    }

    public String getTourCode() {
        return tourCode;
    }

    public void setTourCode(String tourCode) {
        this.tourCode = tourCode;
    }
}
