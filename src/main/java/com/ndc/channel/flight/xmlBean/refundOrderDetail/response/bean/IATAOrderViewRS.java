
package com.ndc.channel.flight.xmlBean.refundOrderDetail.response.bean;

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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}Response"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}MessageDoc"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}PayloadAttributes"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}PaymentInfo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}Error"/>
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
    "response",
    "messageDoc",
    "payloadAttributes",
    "paymentInfo",
    "error"
})
@XmlRootElement(name = "IATA_OrderViewRS", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS")
public class IATAOrderViewRS {

    @XmlElement(name = "Response", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", required = true)
    protected Response response;
    @XmlElement(name = "MessageDoc", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", required = true)
    protected MessageDoc messageDoc;
    @XmlElement(name = "PayloadAttributes", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", required = true)
    protected PayloadAttributes payloadAttributes;
    @XmlElement(name = "PaymentInfo", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS")
    protected List<PaymentInfo> paymentInfo;
    @XmlElement(name = "Error", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", required = true)
    protected Error error;

    /**
     * 获取response属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Response }
     *     
     */
    public Response getResponse() {
        return response;
    }

    /**
     * 设置response属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Response }
     *     
     */
    public void setResponse(Response value) {
        this.response = value;
    }

    /**
     * 获取messageDoc属性的值。
     * 
     * @return
     *     possible object is
     *     {@link MessageDoc }
     *     
     */
    public MessageDoc getMessageDoc() {
        return messageDoc;
    }

    /**
     * 设置messageDoc属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link MessageDoc }
     *     
     */
    public void setMessageDoc(MessageDoc value) {
        this.messageDoc = value;
    }

    /**
     * 获取payloadAttributes属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PayloadAttributes }
     *     
     */
    public PayloadAttributes getPayloadAttributes() {
        return payloadAttributes;
    }

    /**
     * 设置payloadAttributes属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PayloadAttributes }
     *     
     */
    public void setPayloadAttributes(PayloadAttributes value) {
        this.payloadAttributes = value;
    }

    /**
     * Gets the value of the paymentInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the paymentInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPaymentInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PaymentInfo }
     * 
     * 
     */
    public List<PaymentInfo> getPaymentInfo() {
        if (paymentInfo == null) {
            paymentInfo = new ArrayList<PaymentInfo>();
        }
        return this.paymentInfo;
    }

    /**
     * 获取error属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Error }
     *     
     */
    public Error getError() {
        return error;
    }

    /**
     * 设置error属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Error }
     *     
     */
    public void setError(Error value) {
        this.error = value;
    }

}
