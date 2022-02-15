
package com.ndc.channel.flight.xmlBean.verifyPrice.response.bean;

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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS}DataLists"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS}OtherOffers"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS}ShoppingResponse"/>
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
    "dataLists",
    "otherOffers",
    "shoppingResponse"
})
@XmlRootElement(name = "Response", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS")
public class Response {

    @XmlElement(name = "DataLists", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS", required = true)
    protected DataLists dataLists;
    @XmlElement(name = "OtherOffers", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS", required = true)
    protected OtherOffers otherOffers;
    @XmlElement(name = "ShoppingResponse", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS", required = true)
    protected ShoppingResponse shoppingResponse;

    /**
     * 获取dataLists属性的值。
     * 
     * @return
     *     possible object is
     *     {@link DataLists }
     *     
     */
    public DataLists getDataLists() {
        return dataLists;
    }

    /**
     * 设置dataLists属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link DataLists }
     *     
     */
    public void setDataLists(DataLists value) {
        this.dataLists = value;
    }

    /**
     * 获取otherOffers属性的值。
     * 
     * @return
     *     possible object is
     *     {@link OtherOffers }
     *     
     */
    public OtherOffers getOtherOffers() {
        return otherOffers;
    }

    /**
     * 设置otherOffers属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link OtherOffers }
     *     
     */
    public void setOtherOffers(OtherOffers value) {
        this.otherOffers = value;
    }

    /**
     * 获取shoppingResponse属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ShoppingResponse }
     *     
     */
    public ShoppingResponse getShoppingResponse() {
        return shoppingResponse;
    }

    /**
     * 设置shoppingResponse属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ShoppingResponse }
     *     
     */
    public void setShoppingResponse(ShoppingResponse value) {
        this.shoppingResponse = value;
    }

}
