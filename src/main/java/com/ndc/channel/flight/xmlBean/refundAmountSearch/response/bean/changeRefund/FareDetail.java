
package com.ndc.channel.flight.xmlBean.refundAmountSearch.response.bean.changeRefund;

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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}FareComponent"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}FarePriceType" maxOccurs="unbounded" minOccurs="0"/>
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
    "fareComponent",
    "farePriceType"
})
@XmlRootElement(name = "FareDetail", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS")
public class FareDetail {

    @XmlElement(name = "FareComponent", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", required = true)
    protected FareComponent fareComponent;
    @XmlElement(name = "FarePriceType", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS")
    protected List<FarePriceType> farePriceType;

    /**
     * 获取fareComponent属性的值。
     * 
     * @return
     *     possible object is
     *     {@link FareComponent }
     *     
     */
    public FareComponent getFareComponent() {
        return fareComponent;
    }

    /**
     * 设置fareComponent属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link FareComponent }
     *     
     */
    public void setFareComponent(FareComponent value) {
        this.fareComponent = value;
    }

    /**
     * Gets the value of the farePriceType property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the farePriceType property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFarePriceType().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FarePriceType }
     * 
     * 
     */
    public List<FarePriceType> getFarePriceType() {
        if (farePriceType == null) {
            farePriceType = new ArrayList<FarePriceType>();
        }
        return this.farePriceType;
    }

}
