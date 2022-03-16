
package com.ndc.channel.flight.xmlBean.changeBooking.request.bean;

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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ}SelectedServiceDefinitionRefID" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ}SelectedServiceRefID"/>
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
    "selectedServiceDefinitionRefID",
    "selectedServiceRefID"
})
@XmlRootElement(name = "SelectedBundleServices", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ")
public class SelectedBundleServices {

    @XmlElement(name = "SelectedServiceDefinitionRefID", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ")
    protected List<String> selectedServiceDefinitionRefID;
    @XmlElement(name = "SelectedServiceRefID", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", required = true)
    protected String selectedServiceRefID;

    /**
     * Gets the value of the selectedServiceDefinitionRefID property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the selectedServiceDefinitionRefID property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSelectedServiceDefinitionRefID().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getSelectedServiceDefinitionRefID() {
        if (selectedServiceDefinitionRefID == null) {
            selectedServiceDefinitionRefID = new ArrayList<String>();
        }
        return this.selectedServiceDefinitionRefID;
    }

    /**
     * 获取selectedServiceRefID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSelectedServiceRefID() {
        return selectedServiceRefID;
    }

    /**
     * 设置selectedServiceRefID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSelectedServiceRefID(String value) {
        this.selectedServiceRefID = value;
    }

}
