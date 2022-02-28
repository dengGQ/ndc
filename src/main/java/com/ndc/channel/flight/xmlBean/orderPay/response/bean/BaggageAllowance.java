
package com.ndc.channel.flight.xmlBean.orderPay.response.bean;

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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}BaggageAllowanceID"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}PieceAllowance" minOccurs="0"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}TypeCode"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}WeightAllowance" minOccurs="0"/>
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
    "baggageAllowanceID",
    "pieceAllowance",
    "typeCode",
    "weightAllowance"
})
@XmlRootElement(name = "BaggageAllowance", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS")
public class BaggageAllowance {

    @XmlElement(name = "BaggageAllowanceID", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", required = true)
    protected String baggageAllowanceID;
    @XmlElement(name = "PieceAllowance", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS")
    protected PieceAllowance pieceAllowance;
    @XmlElement(name = "TypeCode", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", required = true)
    protected String typeCode;
    @XmlElement(name = "WeightAllowance", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS")
    protected WeightAllowance weightAllowance;

    /**
     * 获取baggageAllowanceID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBaggageAllowanceID() {
        return baggageAllowanceID;
    }

    /**
     * 设置baggageAllowanceID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBaggageAllowanceID(String value) {
        this.baggageAllowanceID = value;
    }

    /**
     * 获取pieceAllowance属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PieceAllowance }
     *     
     */
    public PieceAllowance getPieceAllowance() {
        return pieceAllowance;
    }

    /**
     * 设置pieceAllowance属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PieceAllowance }
     *     
     */
    public void setPieceAllowance(PieceAllowance value) {
        this.pieceAllowance = value;
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

    /**
     * 获取weightAllowance属性的值。
     * 
     * @return
     *     possible object is
     *     {@link WeightAllowance }
     *     
     */
    public WeightAllowance getWeightAllowance() {
        return weightAllowance;
    }

    /**
     * 设置weightAllowance属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link WeightAllowance }
     *     
     */
    public void setWeightAllowance(WeightAllowance value) {
        this.weightAllowance = value;
    }

}
