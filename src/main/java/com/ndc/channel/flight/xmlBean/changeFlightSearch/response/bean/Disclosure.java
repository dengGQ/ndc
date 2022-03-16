
package com.ndc.channel.flight.xmlBean.changeFlightSearch.response.bean;

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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS}Desc" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS}DisclosureID"/>
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
    "desc",
    "disclosureID"
})
@XmlRootElement(name = "Disclosure", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS")
public class Disclosure {

    @XmlElement(name = "Desc", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS")
    protected List<Desc> desc;
    @XmlElement(name = "DisclosureID", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS", required = true)
    protected String disclosureID;

    /**
     * Gets the value of the desc property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the desc property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDesc().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Desc }
     * 
     * 
     */
    public List<Desc> getDesc() {
        if (desc == null) {
            desc = new ArrayList<Desc>();
        }
        return this.desc;
    }

    /**
     * 获取disclosureID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDisclosureID() {
        return disclosureID;
    }

    /**
     * 设置disclosureID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDisclosureID(String value) {
        this.disclosureID = value;
    }

}
