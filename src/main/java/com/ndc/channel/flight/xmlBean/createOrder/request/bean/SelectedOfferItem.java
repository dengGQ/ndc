
package com.ndc.channel.flight.xmlBean.createOrder.request.bean;

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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderCreateRQ}OfferItemRefID"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderCreateRQ}PaxRefID"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderCreateRQ}SelectedALaCarteOfferItem"/>
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
    "offerItemRefID",
    "paxRefID",
    "selectedALaCarteOfferItem"
})
@XmlRootElement(name = "SelectedOfferItem", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderCreateRQ")
public class SelectedOfferItem {

    @XmlElement(name = "OfferItemRefID", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderCreateRQ", required = true)
    protected String offerItemRefID;
    @XmlElement(name = "PaxRefID", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderCreateRQ", required = true)
    protected List<String> paxRefID;
    @XmlElement(name = "SelectedALaCarteOfferItem", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderCreateRQ", required = true)
    protected SelectedALaCarteOfferItem selectedALaCarteOfferItem;

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
     * 获取paxRefID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public List<String> getPaxRefID() {
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
    public void setPaxRefID(List<String> value) {
        this.paxRefID = value;
    }

    /**
     * 获取selectedALaCarteOfferItem属性的值。
     * 
     * @return
     *     possible object is
     *     {@link SelectedALaCarteOfferItem }
     *     
     */
    public SelectedALaCarteOfferItem getSelectedALaCarteOfferItem() {
        return selectedALaCarteOfferItem;
    }

    /**
     * 设置selectedALaCarteOfferItem属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link SelectedALaCarteOfferItem }
     *     
     */
    public void setSelectedALaCarteOfferItem(SelectedALaCarteOfferItem value) {
        this.selectedALaCarteOfferItem = value;
    }

}
