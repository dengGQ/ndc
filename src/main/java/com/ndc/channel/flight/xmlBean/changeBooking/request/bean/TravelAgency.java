
package com.ndc.channel.flight.xmlBean.changeBooking.request.bean;

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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ}AgencyID"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ}ContactInfoRefID" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ}IATANumber" minOccurs="0"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ}Name"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ}TravelAgent" minOccurs="0"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ}PseudoCityID" minOccurs="0"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ}TypeCode"/>
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
    "agencyID",
    "contactInfoRefID",
    "iataNumber",
    "name",
    "travelAgent",
    "pseudoCityID",
    "typeCode"
})
@XmlRootElement(name = "TravelAgency", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ")
public class TravelAgency {

    @XmlElement(name = "AgencyID", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", required = true)
    protected String agencyID;
    @XmlElement(name = "ContactInfoRefID", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ")
    protected List<String> contactInfoRefID;
    @XmlElement(name = "IATANumber", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ")
    protected String iataNumber;
    @XmlElement(name = "Name", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", required = true)
    protected String name;
    @XmlElement(name = "TravelAgent", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ")
    protected TravelAgent travelAgent;
    @XmlElement(name = "PseudoCityID", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ")
    protected String pseudoCityID;
    @XmlElement(name = "TypeCode", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", required = true)
    protected String typeCode;

    public TravelAgency() {
    }

    public TravelAgency(List<String> contactInfoRefID) {
        this.contactInfoRefID = contactInfoRefID;
        this.agencyID = "1462";
        this.iataNumber = "8332730";
        this.name = "杭州飞巴网络科技有限公司";
        this.pseudoCityID = "HGH121";
        this.typeCode = "OnlineTravelAgency";
    }

    /**
     * 获取agencyID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAgencyID() {
        return agencyID;
    }

    /**
     * 设置agencyID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAgencyID(String value) {
        this.agencyID = value;
    }

    /**
     * Gets the value of the contactInfoRefID property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the contactInfoRefID property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContactInfoRefID().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getContactInfoRefID() {
        if (contactInfoRefID == null) {
            contactInfoRefID = new ArrayList<String>();
        }
        return this.contactInfoRefID;
    }

    /**
     * 获取iataNumber属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIATANumber() {
        return iataNumber;
    }

    /**
     * 设置iataNumber属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIATANumber(String value) {
        this.iataNumber = value;
    }

    /**
     * 获取name属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * 设置name属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * 获取travelAgent属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TravelAgent }
     *     
     */
    public TravelAgent getTravelAgent() {
        return travelAgent;
    }

    /**
     * 设置travelAgent属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TravelAgent }
     *     
     */
    public void setTravelAgent(TravelAgent value) {
        this.travelAgent = value;
    }

    /**
     * 获取pseudoCityID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPseudoCityID() {
        return pseudoCityID;
    }

    /**
     * 设置pseudoCityID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPseudoCityID(String value) {
        this.pseudoCityID = value;
    }

    /**
     * 获取typeCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTypeCode() {
        return typeCode;
    }

    /**
     * 设置typeCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTypeCode(String value) {
        this.typeCode = value;
    }

}
