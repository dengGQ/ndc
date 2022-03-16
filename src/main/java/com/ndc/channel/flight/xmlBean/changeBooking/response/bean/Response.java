
package com.ndc.channel.flight.xmlBean.changeBooking.response.bean;

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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}DataLists"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}Order"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}OrderAmendment"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}TicketDocInfo"/>
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
    "dataLists",
    "order",
    "orderAmendment",
    "ticketDocInfo"
})
@XmlRootElement(name = "Response", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS")
public class Response {

    @XmlElement(name = "DataLists", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", required = true)
    protected DataLists dataLists;
    @XmlElement(name = "Order", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", required = true)
    protected Order order;
    @XmlElement(name = "OrderAmendment", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", required = true)
    protected OrderAmendment orderAmendment;
    @XmlElement(name = "TicketDocInfo", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", required = true)
    protected TicketDocInfo ticketDocInfo;

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

    /**
     * 获取order属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Order }
     *     
     */
    public Order getOrder() {
        return order;
    }

    /**
     * 设置order属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Order }
     *     
     */
    public void setOrder(Order value) {
        this.order = value;
    }

    /**
     * 获取orderAmendment属性的值。
     * 
     * @return
     *     possible object is
     *     {@link OrderAmendment }
     *     
     */
    public OrderAmendment getOrderAmendment() {
        return orderAmendment;
    }

    /**
     * 设置orderAmendment属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link OrderAmendment }
     *     
     */
    public void setOrderAmendment(OrderAmendment value) {
        this.orderAmendment = value;
    }

    /**
     * 获取ticketDocInfo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TicketDocInfo }
     *     
     */
    public TicketDocInfo getTicketDocInfo() {
        return ticketDocInfo;
    }

    /**
     * 设置ticketDocInfo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TicketDocInfo }
     *     
     */
    public void setTicketDocInfo(TicketDocInfo value) {
        this.ticketDocInfo = value;
    }

}
