
package com.ndc.channel.flight.xmlBean.refundAmountSearch.response.bean.refund;

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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}ActionTypeCode"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}OfferItemRefID"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}OwnerCode"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}OwnerTypeCode"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}PaxRefID"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}TicketDocInfo"/>
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
    "actionTypeCode",
    "offerItemRefID",
    "ownerCode",
    "ownerTypeCode",
    "paxRefID",
    "ticketDocInfo"
})
@XmlRootElement(name = "OrderAmendment", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS")
public class OrderAmendment {

    @XmlElement(name = "ActionTypeCode", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", required = true)
    protected String actionTypeCode;
    @XmlElement(name = "OfferItemRefID", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", required = true)
    protected String offerItemRefID;
    @XmlElement(name = "OwnerCode", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", required = true)
    protected String ownerCode;
    @XmlElement(name = "OwnerTypeCode", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", required = true)
    protected String ownerTypeCode;
    @XmlElement(name = "PaxRefID", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", required = true)
    protected List<String> paxRefID;
    @XmlElement(name = "TicketDocInfo", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", required = true)
    protected List<TicketDocInfo> ticketDocInfo;

    /**
     * 获取actionTypeCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActionTypeCode() {
        return actionTypeCode;
    }

    /**
     * 设置actionTypeCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActionTypeCode(String value) {
        this.actionTypeCode = value;
    }

    /**
     * 获取offerItemRefID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOfferItemRefID() {
        return offerItemRefID;
    }

    /**
     * 设置offerItemRefID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOfferItemRefID(String value) {
        this.offerItemRefID = value;
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

    public List<String> getPaxRefID() {
        return paxRefID;
    }

    public void setPaxRefID(List<String> paxRefID) {
        this.paxRefID = paxRefID;
    }

    public List<TicketDocInfo> getTicketDocInfo() {
        return ticketDocInfo;
    }

    public void setTicketDocInfo(List<TicketDocInfo> ticketDocInfo) {
        this.ticketDocInfo = ticketDocInfo;
    }
}
