
package com.ndc.channel.flight.xmlBean.refundAmountSearch.request.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;


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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ}BookingRef"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ}DataLists"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ}Order"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ}OrderChangeParameters"/>
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
    "bookingRef",
    "dataLists",
    "order",
    "orderChangeParameters"
})
@XmlRootElement(name = "Request", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ")
public class Request {

    @XmlElement(name = "BookingRef", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", required = true)
    protected List<BookingRef> bookingRef;
    @XmlElement(name = "DataLists", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", required = true)
    protected DataLists dataLists;
    @XmlElement(name = "Order", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", required = true)
    protected Order order;
    @XmlElement(name = "OrderChangeParameters", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", required = true)
    protected OrderChangeParameters orderChangeParameters;

    /**
     * 获取bookingRef属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BookingRef }
     *     
     */
    public List<BookingRef> getBookingRef() {
        return bookingRef;
    }

    /**
     * 设置bookingRef属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BookingRef }
     *     
     */
    public void setBookingRef(List<BookingRef> value) {
        this.bookingRef = value;
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
     * 获取orderChangeParameters属性的值。
     * 
     * @return
     *     possible object is
     *     {@link OrderChangeParameters }
     *     
     */
    public OrderChangeParameters getOrderChangeParameters() {
        return orderChangeParameters;
    }

    /**
     * 设置orderChangeParameters属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link OrderChangeParameters }
     *     
     */
    public void setOrderChangeParameters(OrderChangeParameters value) {
        this.orderChangeParameters = value;
    }

}
