
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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}Response"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}MessageDoc"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}PayloadAttributes"/>
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
