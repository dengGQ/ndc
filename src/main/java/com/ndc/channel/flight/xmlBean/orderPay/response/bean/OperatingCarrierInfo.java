
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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}CarrierDesigCode"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}OperatingCarrierFlightNumberText"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}RBDCode"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}StatusCode"/>
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
    "carrierDesigCode",
    "operatingCarrierFlightNumberText",
    "rbdCode",
    "statusCode"
})
@XmlRootElement(name = "OperatingCarrierInfo", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS")
public class OperatingCarrierInfo {

    @XmlElement(name = "CarrierDesigCode", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", required = true)
    protected String carrierDesigCode;
    @XmlElement(name = "OperatingCarrierFlightNumberText", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", required = true)
    protected String operatingCarrierFlightNumberText;
    @XmlElement(name = "RBDCode", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", required = true)
    protected String rbdCode;
    @XmlElement(name = "StatusCode", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", required = true)
    protected String statusCode;

    /**
     * 获取carrierDesigCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCarrierDesigCode() {
        return carrierDesigCode;
    }

    /**
     * 设置carrierDesigCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCarrierDesigCode(String value) {
        this.carrierDesigCode = value;
    }

    /**
     * 获取operatingCarrierFlightNumberText属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperatingCarrierFlightNumberText() {
        return operatingCarrierFlightNumberText;
    }

    /**
     * 设置operatingCarrierFlightNumberText属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperatingCarrierFlightNumberText(String value) {
        this.operatingCarrierFlightNumberText = value;
    }

    /**
     * 获取rbdCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRBDCode() {
        return rbdCode;
    }

    /**
     * 设置rbdCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRBDCode(String value) {
        this.rbdCode = value;
    }

    /**
     * 获取statusCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatusCode() {
        return statusCode;
    }

    /**
     * 设置statusCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatusCode(String value) {
        this.statusCode = value;
    }

}
