
package com.ndc.channel.flight.xmlBean.refundApply.response.bean;

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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}OrderID"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}OrderItem"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}OwnerCode"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}OwnerTypeCode"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}TotalPrice"/>
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
    "orderID",
    "orderItem",
    "ownerCode",
    "ownerTypeCode",
    "totalPrice"
})
@XmlRootElement(name = "Order", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS")
public class Order {

    @XmlElement(name = "OrderID", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", required = true)
    protected String orderID;
    @XmlElement(name = "OrderItem", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", required = true)
    protected OrderItem orderItem;
    @XmlElement(name = "OwnerCode", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", required = true)
    protected String ownerCode;
    @XmlElement(name = "OwnerTypeCode", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", required = true)
    protected String ownerTypeCode;
    @XmlElement(name = "TotalPrice", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", required = true)
    protected TotalPrice totalPrice;

    /**
     * 获取orderID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderID() {
        return orderID;
    }

    /**
     * 设置orderID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderID(String value) {
        this.orderID = value;
    }

    /**
     * 获取orderItem属性的值。
     * 
     * @return
     *     possible object is
     *     {@link OrderItem }
     *     
     */
    public OrderItem getOrderItem() {
        return orderItem;
    }

    /**
     * 设置orderItem属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link OrderItem }
     *     
     */
    public void setOrderItem(OrderItem value) {
        this.orderItem = value;
    }

    /**
     * 获取ownerCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOwnerCode() {
        return ownerCode;
    }

    /**
     * 设置ownerCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOwnerCode(String value) {
        this.ownerCode = value;
    }

    /**
     * 获取ownerTypeCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOwnerTypeCode() {
        return ownerTypeCode;
    }

    /**
     * 设置ownerTypeCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOwnerTypeCode(String value) {
        this.ownerTypeCode = value;
    }

    /**
     * 获取totalPrice属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TotalPrice }
     *     
     */
    public TotalPrice getTotalPrice() {
        return totalPrice;
    }

    /**
     * 设置totalPrice属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TotalPrice }
     *     
     */
    public void setTotalPrice(TotalPrice value) {
        this.totalPrice = value;
    }

}
