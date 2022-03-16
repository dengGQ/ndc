
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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS}CarrierDesigCode"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS}Disclosure"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS}OperatingCarrierFlightNumberText"/>
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
    "disclosure",
    "operatingCarrierFlightNumberText"
})
@XmlRootElement(name = "OperatingCarrierInfo", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS")
public class OperatingCarrierInfo {

    @XmlElement(name = "CarrierDesigCode", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS", required = true)
    protected String carrierDesigCode;
    @XmlElement(name = "Disclosure", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS", required = true)
    protected Disclosure disclosure;
    @XmlElement(name = "OperatingCarrierFlightNumberText", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS", required = true)
    protected String operatingCarrierFlightNumberText;

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
     * 获取disclosure属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Disclosure }
     *     
     */
    public Disclosure getDisclosure() {
        return disclosure;
    }

    /**
     * 设置disclosure属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Disclosure }
     *     
     */
    public void setDisclosure(Disclosure value) {
        this.disclosure = value;
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

}
