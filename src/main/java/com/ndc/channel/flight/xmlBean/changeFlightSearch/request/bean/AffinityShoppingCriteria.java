
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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRQ}AffinityOriginDest"/>
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
    "affinityOriginDest"
})
@XmlRootElement(name = "AffinityShoppingCriteria", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRQ")
public class AffinityShoppingCriteria {

    @XmlElement(name = "AffinityOriginDest", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRQ", required = true)
    protected AffinityOriginDest affinityOriginDest;

    /**
     * 获取affinityOriginDest属性的值。
     * 
     * @return
     *     possible object is
     *     {@link AffinityOriginDest }
     *     
     */
    public AffinityOriginDest getAffinityOriginDest() {
        return affinityOriginDest;
    }

    /**
     * 设置affinityOriginDest属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link AffinityOriginDest }
     *     
     */
    public void setAffinityOriginDest(AffinityOriginDest value) {
        this.affinityOriginDest = value;
    }

}
