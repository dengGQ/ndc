
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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}CarrierFee" minOccurs="0"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}PaxRefID" minOccurs="0"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}Ticket" minOccurs="0"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}BookingRef" minOccurs="0"/>
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
    "carrierFee",
    "paxRefID",
    "ticket",
    "bookingRef"
})
@XmlRootElement(name = "TicketDocInfo", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS")
public class TicketDocInfo {

    @XmlElement(name = "CarrierFee", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS")
    protected CarrierFee carrierFee;
    @XmlElement(name = "PaxRefID", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS")
    protected String paxRefID;
    @XmlElement(name = "Ticket", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS")
    protected Ticket ticket;
    @XmlElement(name = "BookingRef", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS")
    protected BookingRef bookingRef;

    /**
     * 获取carrierFee属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CarrierFee }
     *     
     */
    public CarrierFee getCarrierFee() {
        return carrierFee;
    }

    /**
     * 设置carrierFee属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CarrierFee }
     *     
     */
    public void setCarrierFee(CarrierFee value) {
        this.carrierFee = value;
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

    /**
     * 获取bookingRef属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BookingRef }
     *     
     */
    public BookingRef getBookingRef() {
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
    public void setBookingRef(BookingRef value) {
        this.bookingRef = value;
    }

}
