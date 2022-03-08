
package com.ndc.channel.flight.xmlBean.refundAmountSearch.response.bean.changeRefund;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}CreationDateTime" minOccurs="0"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}Disclosure" minOccurs="0"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}OrderItemID" minOccurs="0"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}OrderItemRefID" minOccurs="0"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}OwnerCode"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}OwnerTypeCode"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}Penalty" minOccurs="0"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}Price" minOccurs="0"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}Service" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}StatusCode" minOccurs="0"/>
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
    "creationDateTime",
    "disclosure",
    "orderItemID",
    "orderItemRefID",
    "ownerCode",
    "ownerTypeCode",
    "penalty",
    "price",
    "service",
    "statusCode"
})
@XmlRootElement(name = "OrderItem", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS")
public class OrderItem {

    @XmlElement(name = "CreationDateTime", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS")
    protected String creationDateTime;
    @XmlElement(name = "Disclosure", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS")
    protected Disclosure disclosure;
    @XmlElement(name = "OrderItemID", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS")
    protected String orderItemID;
    @XmlElement(name = "OrderItemRefID", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS")
    protected String orderItemRefID;
    @XmlElement(name = "OwnerCode", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", required = true)
    protected String ownerCode;
    @XmlElement(name = "OwnerTypeCode", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", required = true)
    protected String ownerTypeCode;
    @XmlElement(name = "Penalty", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS")
    protected Penalty penalty;
    @XmlElement(name = "Price", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS")
    protected Price price;
    @XmlElement(name = "Service", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS")
    protected List<Service> service;
    @XmlElement(name = "StatusCode", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS")
    protected String statusCode;

    /**
     * 获取creationDateTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreationDateTime() {
        return creationDateTime;
    }

    /**
     * 设置creationDateTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreationDateTime(String value) {
        this.creationDateTime = value;
    }

    /**
     * 获取disclosure属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Disclosure }
     *     
     */
    public Disclosure getDisclosure() {
        return disclosure;
    }

    /**
     * 设置disclosure属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Disclosure }
     *     
     */
    public void setDisclosure(Disclosure value) {
        this.disclosure = value;
    }

    /**
     * 获取orderItemID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderItemID() {
        return orderItemID;
    }

    /**
     * 设置orderItemID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderItemID(String value) {
        this.orderItemID = value;
    }

    /**
     * 获取orderItemRefID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderItemRefID() {
        return orderItemRefID;
    }

    /**
     * 设置orderItemRefID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderItemRefID(String value) {
        this.orderItemRefID = value;
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
     * 获取penalty属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Penalty }
     *     
     */
    public Penalty getPenalty() {
        return penalty;
    }

    /**
     * 设置penalty属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Penalty }
     *     
     */
    public void setPenalty(Penalty value) {
        this.penalty = value;
    }

    /**
     * 获取price属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Price }
     *     
     */
    public Price getPrice() {
        return price;
    }

    /**
     * 设置price属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Price }
     *     
     */
    public void setPrice(Price value) {
        this.price = value;
    }

    /**
     * Gets the value of the service property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the service property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getService().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Service }
     * 
     * 
     */
    public List<Service> getService() {
        if (service == null) {
            service = new ArrayList<Service>();
        }
        return this.service;
    }

    /**
     * 获取statusCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatusCode() {
        return statusCode;
    }

    /**
     * 设置statusCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatusCode(String value) {
        this.statusCode = value;
    }

}
