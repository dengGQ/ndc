
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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRQ}AffinityShoppingCriteria"/>
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
    "affinityShoppingCriteria"
})
@XmlRootElement(name = "FlightCriteria", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRQ")
public class FlightCriteria {

    @XmlElement(name = "AffinityShoppingCriteria", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRQ", required = true)
    protected AffinityShoppingCriteria affinityShoppingCriteria;

    /**
     * 获取affinityShoppingCriteria属性的值。
     * 
     * @return
     *     possible object is
     *     {@link AffinityShoppingCriteria }
     *     
     */
    public AffinityShoppingCriteria getAffinityShoppingCriteria() {
        return affinityShoppingCriteria;
    }

    /**
     * 设置affinityShoppingCriteria属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link AffinityShoppingCriteria }
     *     
     */
    public void setAffinityShoppingCriteria(AffinityShoppingCriteria value) {
        this.affinityShoppingCriteria = value;
    }

}
