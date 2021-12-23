
package com.ndc.channel.flight.xmlBean.flightSearch.response.bean;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
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
 *       &lt;choice maxOccurs="unbounded" minOccurs="0">
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS}DestCode"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS}OriginCode"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS}OriginDestID"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS}PaxJourneyRefID"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "originCode",
    "originDestID",
    "destCode",
    "paxJourneyRefID"
})
@XmlRootElement(name = "OriginDest", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS")
public class OriginDest {

    @XmlElement(name = "OriginCode", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS", required = true)
    protected String originCode;
    @XmlElement(name = "OriginDestID", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS", required = true)
    protected String originDestID;
    @XmlElement(name = "DestCode", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS", required = true)
    protected String destCode;
    @XmlElement(name = "PaxJourneyRefID", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS", required = true)
    protected List<String> paxJourneyRefID;

    public String getOriginCode() {
        return originCode;
    }

    public void setOriginCode(String originCode) {
        this.originCode = originCode;
    }

    public String getOriginDestID() {
        return originDestID;
    }

    public void setOriginDestID(String originDestID) {
        this.originDestID = originDestID;
    }

    public String getDestCode() {
        return destCode;
    }

    public void setDestCode(String destCode) {
        this.destCode = destCode;
    }

    public List<String> getPaxJourneyRefID() {
        return paxJourneyRefID;
    }

    public void setPaxJourneyRefID(List<String> paxJourneyRefID) {
        this.paxJourneyRefID = paxJourneyRefID;
    }


    /*@XmlElementRefs({
        @XmlElementRef(name = "OriginCode", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "OriginDestID", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "DestCode", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "PaxJourneyRefID", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS", type = JAXBElement.class, required = false)
    })
    protected List<JAXBElement<String>> destCodeOrOriginCodeOrOriginDestID;

    *//**
     * Gets the value of the destCodeOrOriginCodeOrOriginDestID property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the destCodeOrOriginCodeOrOriginDestID property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDestCodeOrOriginCodeOrOriginDestID().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * 
     *//*
    public List<JAXBElement<String>> getDestCodeOrOriginCodeOrOriginDestID() {
        if (destCodeOrOriginCodeOrOriginDestID == null) {
            destCodeOrOriginCodeOrOriginDestID = new ArrayList<JAXBElement<String>>();
        }
        return this.destCodeOrOriginCodeOrOriginDestID;
    }*/

}
