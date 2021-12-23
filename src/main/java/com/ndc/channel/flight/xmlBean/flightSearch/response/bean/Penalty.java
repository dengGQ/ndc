
package com.ndc.channel.flight.xmlBean.flightSearch.response.bean;

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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS}AppCode"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS}DescText"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS}PenaltyPercent" minOccurs="0"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS}PenaltyAmount" minOccurs="0"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS}TypeCode"/>
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
    "appCode",
    "descText",
    "penaltyPercent",
    "penaltyAmount",
    "typeCode"
})
@XmlRootElement(name = "Penalty", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS")
public class Penalty {

    @XmlElement(name = "AppCode", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS", required = true)
    protected String appCode;
    @XmlElement(name = "DescText", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS", required = true)
    protected String descText;
    @XmlElement(name = "PenaltyPercent", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS")
    protected String penaltyPercent;
    @XmlElement(name = "PenaltyAmount", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS")
    protected PenaltyAmount penaltyAmount;
    @XmlElement(name = "TypeCode", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS", required = true)
    protected String typeCode;

    /**
     * 获取appCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAppCode() {
        return appCode;
    }

    /**
     * 设置appCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAppCode(String value) {
        this.appCode = value;
    }

    /**
     * 获取descText属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescText() {
        return descText;
    }

    /**
     * 设置descText属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescText(String value) {
        this.descText = value;
    }

    /**
     * 获取penaltyPercent属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPenaltyPercent() {
        return penaltyPercent;
    }

    /**
     * 设置penaltyPercent属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPenaltyPercent(String value) {
        this.penaltyPercent = value;
    }

    /**
     * 获取penaltyAmount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PenaltyAmount }
     *     
     */
    public PenaltyAmount getPenaltyAmount() {
        return penaltyAmount;
    }

    /**
     * 设置penaltyAmount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PenaltyAmount }
     *     
     */
    public void setPenaltyAmount(PenaltyAmount value) {
        this.penaltyAmount = value;
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
