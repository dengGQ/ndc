
package com.ndc.channel.flight.xmlBean.refundAmountSearch.response.bean.refund;

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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}BookingEntity" minOccurs="0"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}BookingID"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}BookingRefTypeCode"/>
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
    "bookingEntity",
    "bookingID",
    "bookingRefTypeCode"
})
@XmlRootElement(name = "BookingRef", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS")
public class BookingRef {

    @XmlElement(name = "BookingEntity", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS")
    protected BookingEntity bookingEntity;
    @XmlElement(name = "BookingID", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", required = true)
    protected String bookingID;
    @XmlElement(name = "BookingRefTypeCode", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", required = true)
    protected String bookingRefTypeCode;

    /**
     * 获取bookingEntity属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BookingEntity }
     *     
     */
    public BookingEntity getBookingEntity() {
        return bookingEntity;
    }

    /**
     * 设置bookingEntity属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BookingEntity }
     *     
     */
    public void setBookingEntity(BookingEntity value) {
        this.bookingEntity = value;
    }

    /**
     * 获取bookingID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBookingID() {
        return bookingID;
    }

    /**
     * 设置bookingID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBookingID(String value) {
        this.bookingID = value;
    }

    /**
     * 获取bookingRefTypeCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBookingRefTypeCode() {
        return bookingRefTypeCode;
    }

    /**
     * 设置bookingRefTypeCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBookingRefTypeCode(String value) {
        this.bookingRefTypeCode = value;
    }

}
