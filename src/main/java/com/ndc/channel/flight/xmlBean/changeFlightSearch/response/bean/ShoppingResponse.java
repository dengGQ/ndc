
package com.ndc.channel.flight.xmlBean.changeFlightSearch.response.bean;

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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS}OwnerCode"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS}ShoppingResponseID"/>
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
    "ownerCode",
    "shoppingResponseID"
})
@XmlRootElement(name = "ShoppingResponse", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS")
public class ShoppingResponse {

    @XmlElement(name = "OwnerCode", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS", required = true)
    protected String ownerCode;
    @XmlElement(name = "ShoppingResponseID", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS", required = true)
    protected String shoppingResponseID;

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
     * 获取shoppingResponseID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShoppingResponseID() {
        return shoppingResponseID;
    }

    /**
     * 设置shoppingResponseID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShoppingResponseID(String value) {
        this.shoppingResponseID = value;
    }

}
