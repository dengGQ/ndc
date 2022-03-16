
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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS}MaximumWeightMeasure"/>
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
    "maximumWeightMeasure"
})
@XmlRootElement(name = "PieceWeightAllowance", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS")
public class PieceWeightAllowance {

    @XmlElement(name = "MaximumWeightMeasure", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS", required = true)
    protected MaximumWeightMeasure maximumWeightMeasure;

    /**
     * 获取maximumWeightMeasure属性的值。
     * 
     * @return
     *     possible object is
     *     {@link MaximumWeightMeasure }
     *     
     */
    public MaximumWeightMeasure getMaximumWeightMeasure() {
        return maximumWeightMeasure;
    }

    /**
     * 设置maximumWeightMeasure属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link MaximumWeightMeasure }
     *     
     */
    public void setMaximumWeightMeasure(MaximumWeightMeasure value) {
        this.maximumWeightMeasure = value;
    }

}
