
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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS}DataLists"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS}ReshopResults"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS}ShoppingResponse"/>
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
    "reshopResults",
    "shoppingResponse"
})
@XmlRootElement(name = "Response", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS")
public class Response {

    @XmlElement(name = "DataLists", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS", required = true)
    protected DataLists dataLists;
    @XmlElement(name = "ReshopResults", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS", required = true)
    protected ReshopResults reshopResults;
    @XmlElement(name = "ShoppingResponse", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS", required = true)
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
     * 获取reshopResults属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ReshopResults }
     *     
     */
    public ReshopResults getReshopResults() {
        return reshopResults;
    }

    /**
     * 设置reshopResults属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ReshopResults }
     *     
     */
    public void setReshopResults(ReshopResults value) {
        this.reshopResults = value;
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
