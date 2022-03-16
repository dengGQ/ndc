
package com.ndc.channel.flight.xmlBean.changeFlightSearch.request.bean;

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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRQ}BookingRef"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRQ}OrderActionContextText"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRQ}OrderRefID"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRQ}UpdateOrder"/>
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
    "orderActionContextText",
    "orderRefID",
    "updateOrder"
})
@XmlRootElement(name = "Request", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRQ")
public class Request {

    @XmlElement(name = "BookingRef", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRQ", required = true)
    protected List<BookingRef> bookingRef;
    @XmlElement(name = "OrderActionContextText", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRQ", required = true)
    protected String orderActionContextText;
    @XmlElement(name = "OrderRefID", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRQ", required = true)
    protected String orderRefID;
    @XmlElement(name = "UpdateOrder", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRQ", required = true)
    protected UpdateOrder updateOrder;

    public List<BookingRef> getBookingRef() {
        return bookingRef;
    }

    public void setBookingRef(List<BookingRef> bookingRef) {
        this.bookingRef = bookingRef;
    }

    /**
     * 获取orderActionContextText属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderActionContextText() {
        return orderActionContextText;
    }

    /**
     * 设置orderActionContextText属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderActionContextText(String value) {
        this.orderActionContextText = value;
    }

    /**
     * 获取orderRefID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderRefID() {
        return orderRefID;
    }

    /**
     * 设置orderRefID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderRefID(String value) {
        this.orderRefID = value;
    }

    /**
     * 获取updateOrder属性的值。
     * 
     * @return
     *     possible object is
     *     {@link UpdateOrder }
     *     
     */
    public UpdateOrder getUpdateOrder() {
        return updateOrder;
    }

    /**
     * 设置updateOrder属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link UpdateOrder }
     *     
     */
    public void setUpdateOrder(UpdateOrder value) {
        this.updateOrder = value;
    }

}
