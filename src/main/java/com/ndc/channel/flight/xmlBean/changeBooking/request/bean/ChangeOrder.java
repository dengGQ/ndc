
package com.ndc.channel.flight.xmlBean.changeBooking.request.bean;

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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ}UpdateOrderItem"/>
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
    "updateOrderItem"
})
@XmlRootElement(name = "ChangeOrder", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ")
public class ChangeOrder {

    @XmlElement(name = "UpdateOrderItem", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", required = true)
    protected UpdateOrderItem updateOrderItem;

    /**
     * 获取updateOrderItem属性的值。
     * 
     * @return
     *     possible object is
     *     {@link UpdateOrderItem }
     *     
     */
    public UpdateOrderItem getUpdateOrderItem() {
        return updateOrderItem;
    }

    /**
     * 设置updateOrderItem属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link UpdateOrderItem }
     *     
     */
    public void setUpdateOrderItem(UpdateOrderItem value) {
        this.updateOrderItem = value;
    }

}
