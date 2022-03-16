
package com.ndc.channel.flight.xmlBean.changeFlightSearch.request.bean;

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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRQ}ReshopOrder"/>
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
    "reshopOrder"
})
@XmlRootElement(name = "UpdateOrder", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRQ")
public class UpdateOrder {

    @XmlElement(name = "ReshopOrder", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRQ", required = true)
    protected ReshopOrder reshopOrder;

    /**
     * 获取reshopOrder属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ReshopOrder }
     *     
     */
    public ReshopOrder getReshopOrder() {
        return reshopOrder;
    }

    /**
     * 设置reshopOrder属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ReshopOrder }
     *     
     */
    public void setReshopOrder(ReshopOrder value) {
        this.reshopOrder = value;
    }

}
