
package com.ndc.channel.flight.xmlBean.changeBooking.response.bean;

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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}ContactInfoList"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}PaxList"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}PaxSegmentList"/>
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
    "contactInfoList",
    "paxList",
    "paxSegmentList"
})
@XmlRootElement(name = "DataLists", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS")
public class DataLists {

    @XmlElement(name = "ContactInfoList", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", required = true)
    protected ContactInfoList contactInfoList;
    @XmlElement(name = "PaxList", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", required = true)
    protected PaxList paxList;
    @XmlElement(name = "PaxSegmentList", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", required = true)
    protected PaxSegmentList paxSegmentList;

    /**
     * 获取contactInfoList属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ContactInfoList }
     *     
     */
    public ContactInfoList getContactInfoList() {
        return contactInfoList;
    }

    /**
     * 设置contactInfoList属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ContactInfoList }
     *     
     */
    public void setContactInfoList(ContactInfoList value) {
        this.contactInfoList = value;
    }

    /**
     * 获取paxList属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PaxList }
     *     
     */
    public PaxList getPaxList() {
        return paxList;
    }

    /**
     * 设置paxList属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PaxList }
     *     
     */
    public void setPaxList(PaxList value) {
        this.paxList = value;
    }

    /**
     * 获取paxSegmentList属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PaxSegmentList }
     *     
     */
    public PaxSegmentList getPaxSegmentList() {
        return paxSegmentList;
    }

    /**
     * 设置paxSegmentList属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PaxSegmentList }
     *     
     */
    public void setPaxSegmentList(PaxSegmentList value) {
        this.paxSegmentList = value;
    }

}
