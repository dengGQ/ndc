
package com.ndc.channel.flight.xmlBean.refundApply.response.bean;

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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}BookingRef" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}FareDetail"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}PaxRefID"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}PaymentInfoRefID"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}RefdOrder"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}Ticket"/>
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
    "fareDetail",
    "paxRefID",
    "paymentInfoRefID",
    "refdOrder",
    "ticket"
})
@XmlRootElement(name = "TicketDocInfo", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS")
public class TicketDocInfo {

    @XmlElement(name = "BookingRef", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS")
    protected List<BookingRef> bookingRef;
    @XmlElement(name = "FareDetail", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", required = true)
    protected FareDetail fareDetail;
    @XmlElement(name = "PaxRefID", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", required = true)
    protected String paxRefID;
    @XmlElement(name = "PaymentInfoRefID", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", required = true)
    protected String paymentInfoRefID;
    @XmlElement(name = "RefdOrder", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", required = true)
    protected RefdOrder refdOrder;
    @XmlElement(name = "Ticket", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", required = true)
    protected Ticket ticket;

    /**
     * Gets the value of the bookingRef property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the bookingRef property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBookingRef().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BookingRef }
     * 
     * 
     */
    public List<BookingRef> getBookingRef() {
        if (bookingRef == null) {
            bookingRef = new ArrayList<BookingRef>();
        }
        return this.bookingRef;
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
     * 获取paxRefID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaxRefID() {
        return paxRefID;
    }

    /**
     * 设置paxRefID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaxRefID(String value) {
        this.paxRefID = value;
    }

    /**
     * 获取paymentInfoRefID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentInfoRefID() {
        return paymentInfoRefID;
    }

    /**
     * 设置paymentInfoRefID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentInfoRefID(String value) {
        this.paymentInfoRefID = value;
    }

    /**
     * 获取refdOrder属性的值。
     * 
     * @return
     *     possible object is
     *     {@link RefdOrder }
     *     
     */
    public RefdOrder getRefdOrder() {
        return refdOrder;
    }

    /**
     * 设置refdOrder属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link RefdOrder }
     *     
     */
    public void setRefdOrder(RefdOrder value) {
        this.refdOrder = value;
    }

    /**
     * 获取ticket属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Ticket }
     *     
     */
    public Ticket getTicket() {
        return ticket;
    }

    /**
     * 设置ticket属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Ticket }
     *     
     */
    public void setTicket(Ticket value) {
        this.ticket = value;
    }

}
