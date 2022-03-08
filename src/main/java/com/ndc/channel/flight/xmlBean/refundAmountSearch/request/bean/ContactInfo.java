
package com.ndc.channel.flight.xmlBean.refundAmountSearch.request.bean;

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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ}ContactInfoID"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ}ContactTypeText"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ}EmailAddress"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ}Individual"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ}Phone"/>
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
    "contactInfoID",
    "contactTypeText",
    "emailAddress",
    "individual",
    "phone"
})
@XmlRootElement(name = "ContactInfo", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ")
public class ContactInfo {

    @XmlElement(name = "ContactInfoID", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", required = true)
    protected String contactInfoID;
    @XmlElement(name = "ContactTypeText", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", required = true)
    protected String contactTypeText;
    @XmlElement(name = "EmailAddress", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", required = true)
    protected EmailAddress emailAddress;
    @XmlElement(name = "Individual", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", required = true)
    protected Individual individual;
    @XmlElement(name = "Phone", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", required = true)
    protected List<Phone> phone;

    /**
     * 获取contactInfoID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactInfoID() {
        return contactInfoID;
    }

    /**
     * 设置contactInfoID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactInfoID(String value) {
        this.contactInfoID = value;
    }

    /**
     * 获取contactTypeText属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactTypeText() {
        return contactTypeText;
    }

    /**
     * 设置contactTypeText属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactTypeText(String value) {
        this.contactTypeText = value;
    }

    /**
     * 获取emailAddress属性的值。
     * 
     * @return
     *     possible object is
     *     {@link EmailAddress }
     *     
     */
    public EmailAddress getEmailAddress() {
        return emailAddress;
    }

    /**
     * 设置emailAddress属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link EmailAddress }
     *     
     */
    public void setEmailAddress(EmailAddress value) {
        this.emailAddress = value;
    }

    /**
     * 获取individual属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Individual }
     *     
     */
    public Individual getIndividual() {
        return individual;
    }

    /**
     * 设置individual属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Individual }
     *     
     */
    public void setIndividual(Individual value) {
        this.individual = value;
    }

    /**
     * 获取phone属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Phone }
     *     
     */
    public List<Phone> getPhone() {
        return phone;
    }

    /**
     * 设置phone属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Phone }
     *     
     */
    public void setPhone(List<Phone> value) {
        this.phone = value;
    }

}
