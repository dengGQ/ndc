
package com.ndc.channel.flight.xmlBean.refundAmountSearch.response.bean.changeRefund;

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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}CarrierAircraftTypeCode"/>
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
    "carrierAircraftTypeCode"
})
@XmlRootElement(name = "CarrierAircraftType", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS")
public class CarrierAircraftType {

    @XmlElement(name = "CarrierAircraftTypeCode", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", required = true)
    protected String carrierAircraftTypeCode;

    /**
     * 获取carrierAircraftTypeCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCarrierAircraftTypeCode() {
        return carrierAircraftTypeCode;
    }

    /**
     * 设置carrierAircraftTypeCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCarrierAircraftTypeCode(String value) {
        this.carrierAircraftTypeCode = value;
    }

}
