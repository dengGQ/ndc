
package com.ndc.channel.flight.xmlBean.refundApply.response.bean;

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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}CouponNumber"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}CouponRefNumber"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}CouponSeqNumber"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}CouponStatusCode"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}NonRefundableInd"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}RemarkText"/>
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
    "couponNumber",
    "couponRefNumber",
    "couponSeqNumber",
    "couponStatusCode",
    "nonRefundableInd",
    "remarkText"
})
@XmlRootElement(name = "Coupon", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS")
public class Coupon {

    @XmlElement(name = "CouponNumber", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", required = true)
    protected String couponNumber;
    @XmlElement(name = "CouponRefNumber", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", required = true)
    protected String couponRefNumber;
    @XmlElement(name = "CouponSeqNumber", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", required = true)
    protected String couponSeqNumber;
    @XmlElement(name = "CouponStatusCode", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", required = true)
    protected String couponStatusCode;
    @XmlElement(name = "NonRefundableInd", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", required = true)
    protected Boolean nonRefundableInd;
    @XmlElement(name = "RemarkText", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", required = true)
    protected String remarkText;

    /**
     * 获取couponNumber属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCouponNumber() {
        return couponNumber;
    }

    /**
     * 设置couponNumber属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCouponNumber(String value) {
        this.couponNumber = value;
    }

    /**
     * 获取couponRefNumber属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCouponRefNumber() {
        return couponRefNumber;
    }

    /**
     * 设置couponRefNumber属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCouponRefNumber(String value) {
        this.couponRefNumber = value;
    }

    /**
     * 获取couponSeqNumber属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCouponSeqNumber() {
        return couponSeqNumber;
    }

    /**
     * 设置couponSeqNumber属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCouponSeqNumber(String value) {
        this.couponSeqNumber = value;
    }

    /**
     * 获取couponStatusCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCouponStatusCode() {
        return couponStatusCode;
    }

    /**
     * 设置couponStatusCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCouponStatusCode(String value) {
        this.couponStatusCode = value;
    }

    /**
     * 获取nonRefundableInd属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Boolean getNonRefundableInd() {
        return nonRefundableInd;
    }

    /**
     * 设置nonRefundableInd属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNonRefundableInd(Boolean value) {
        this.nonRefundableInd = value;
    }

    /**
     * 获取remarkText属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemarkText() {
        return remarkText;
    }

    /**
     * 设置remarkText属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemarkText(String value) {
        this.remarkText = value;
    }

}
