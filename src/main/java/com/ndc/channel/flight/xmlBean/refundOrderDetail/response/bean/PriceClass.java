
package com.ndc.channel.flight.xmlBean.refundOrderDetail.response.bean;

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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}CabinType"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}Desc"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}PriceClassID"/>
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
    "cabinType",
    "desc",
    "priceClassID"
})
@XmlRootElement(name = "PriceClass", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS")
public class PriceClass {

    @XmlElement(name = "CabinType", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", required = true)
    protected CabinType cabinType;
    @XmlElement(name = "Desc", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", required = true)
    protected Desc desc;
    @XmlElement(name = "PriceClassID", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", required = true)
    protected String priceClassID;

    /**
     * 获取cabinType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CabinType }
     *     
     */
    public CabinType getCabinType() {
        return cabinType;
    }

    /**
     * 设置cabinType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CabinType }
     *     
     */
    public void setCabinType(CabinType value) {
        this.cabinType = value;
    }

    /**
     * 获取desc属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Desc }
     *     
     */
    public Desc getDesc() {
        return desc;
    }

    /**
     * 设置desc属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Desc }
     *     
     */
    public void setDesc(Desc value) {
        this.desc = value;
    }

    /**
     * 获取priceClassID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPriceClassID() {
        return priceClassID;
    }

    /**
     * 设置priceClassID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPriceClassID(String value) {
        this.priceClassID = value;
    }

}
