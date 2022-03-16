
package com.ndc.channel.flight.xmlBean.changeFlightSearch.response.bean;

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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS}DifferentialAmountDue"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS}FareDetail"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS}NewOfferItemDifferential"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS}OfferItemID"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS}OrderItemRefID"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS}PenaltyDifferential"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS}Price"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS}Service" maxOccurs="unbounded" minOccurs="0"/>
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
    "differentialAmountDue",
    "fareDetail",
    "newOfferItemDifferential",
    "offerItemID",
    "orderItemRefID",
    "penaltyDifferential",
    "price",
    "service"
})
@XmlRootElement(name = "AddOfferItem", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS")
public class AddOfferItem {

    @XmlElement(name = "DifferentialAmountDue", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS", required = true)
    protected DifferentialAmountDue differentialAmountDue;
    @XmlElement(name = "FareDetail", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS", required = true)
    protected FareDetail fareDetail;
    @XmlElement(name = "NewOfferItemDifferential", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS", required = true)
    protected NewOfferItemDifferential newOfferItemDifferential;
    @XmlElement(name = "OfferItemID", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS", required = true)
    protected String offerItemID;
    @XmlElement(name = "OrderItemRefID", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS", required = true)
    protected String orderItemRefID;
    @XmlElement(name = "PenaltyDifferential", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS", required = true)
    protected PenaltyDifferential penaltyDifferential;
    @XmlElement(name = "Price", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS", required = true)
    protected Price price;
    @XmlElement(name = "Service", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS")
    protected List<Service> service;

    /**
     * 获取differentialAmountDue属性的值。
     * 
     * @return
     *     possible object is
     *     {@link DifferentialAmountDue }
     *     
     */
    public DifferentialAmountDue getDifferentialAmountDue() {
        return differentialAmountDue;
    }

    /**
     * 设置differentialAmountDue属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link DifferentialAmountDue }
     *     
     */
    public void setDifferentialAmountDue(DifferentialAmountDue value) {
        this.differentialAmountDue = value;
    }

    /**
     * 获取fareDetail属性的值。
     * 
     * @return
     *     possible object is
     *     {@link FareDetail }
     *     
     */
    public FareDetail getFareDetail() {
        return fareDetail;
    }

    /**
     * 设置fareDetail属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link FareDetail }
     *     
     */
    public void setFareDetail(FareDetail value) {
        this.fareDetail = value;
    }

    /**
     * 获取newOfferItemDifferential属性的值。
     * 
     * @return
     *     possible object is
     *     {@link NewOfferItemDifferential }
     *     
     */
    public NewOfferItemDifferential getNewOfferItemDifferential() {
        return newOfferItemDifferential;
    }

    /**
     * 设置newOfferItemDifferential属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link NewOfferItemDifferential }
     *     
     */
    public void setNewOfferItemDifferential(NewOfferItemDifferential value) {
        this.newOfferItemDifferential = value;
    }

    /**
     * 获取offerItemID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOfferItemID() {
        return offerItemID;
    }

    /**
     * 设置offerItemID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOfferItemID(String value) {
        this.offerItemID = value;
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
     * 获取penaltyDifferential属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PenaltyDifferential }
     *     
     */
    public PenaltyDifferential getPenaltyDifferential() {
        return penaltyDifferential;
    }

    /**
     * 设置penaltyDifferential属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PenaltyDifferential }
     *     
     */
    public void setPenaltyDifferential(PenaltyDifferential value) {
        this.penaltyDifferential = value;
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

}
