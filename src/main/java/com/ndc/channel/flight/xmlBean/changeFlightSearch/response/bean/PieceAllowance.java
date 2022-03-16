
package com.ndc.channel.flight.xmlBean.changeFlightSearch.response.bean;

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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS}ApplicablePartyText"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS}PieceWeightAllowance"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS}TotalQty"/>
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
    "applicablePartyText",
    "pieceWeightAllowance",
    "totalQty"
})
@XmlRootElement(name = "PieceAllowance", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS")
public class PieceAllowance {

    @XmlElement(name = "ApplicablePartyText", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS", required = true)
    protected String applicablePartyText;
    @XmlElement(name = "PieceWeightAllowance", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS", required = true)
    protected PieceWeightAllowance pieceWeightAllowance;
    @XmlElement(name = "TotalQty", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS", required = true)
    protected String totalQty;

    /**
     * 获取applicablePartyText属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApplicablePartyText() {
        return applicablePartyText;
    }

    /**
     * 设置applicablePartyText属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplicablePartyText(String value) {
        this.applicablePartyText = value;
    }

    /**
     * 获取pieceWeightAllowance属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PieceWeightAllowance }
     *     
     */
    public PieceWeightAllowance getPieceWeightAllowance() {
        return pieceWeightAllowance;
    }

    /**
     * 设置pieceWeightAllowance属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PieceWeightAllowance }
     *     
     */
    public void setPieceWeightAllowance(PieceWeightAllowance value) {
        this.pieceWeightAllowance = value;
    }

    /**
     * 获取totalQty属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalQty() {
        return totalQty;
    }

    /**
     * 设置totalQty属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalQty(String value) {
        this.totalQty = value;
    }

}
