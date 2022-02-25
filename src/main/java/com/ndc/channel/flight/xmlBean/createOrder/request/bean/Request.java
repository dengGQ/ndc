
package com.ndc.channel.flight.xmlBean.createOrder.request.bean;

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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderCreateRQ}CreateOrder"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderCreateRQ}DataLists"/>
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
    "createOrder",
    "dataLists"
})
@XmlRootElement(name = "Request", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderCreateRQ")
public class Request {

    @XmlElement(name = "CreateOrder", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderCreateRQ", required = true)
    protected CreateOrder createOrder;
    @XmlElement(name = "DataLists", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderCreateRQ", required = true)
    protected DataLists dataLists;

    /**
     * 获取createOrder属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CreateOrder }
     *     
     */
    public CreateOrder getCreateOrder() {
        return createOrder;
    }

    /**
     * 设置createOrder属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CreateOrder }
     *     
     */
    public void setCreateOrder(CreateOrder value) {
        this.createOrder = value;
    }

    /**
     * 获取dataLists属性的值。
     * 
     * @return
     *     possible object is
     *     {@link DataLists }
     *     
     */
    public DataLists getDataLists() {
        return dataLists;
    }

    /**
     * 设置dataLists属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link DataLists }
     *     
     */
    public void setDataLists(DataLists value) {
        this.dataLists = value;
    }

}
