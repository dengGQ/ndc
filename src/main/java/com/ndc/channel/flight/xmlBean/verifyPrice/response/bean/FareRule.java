
package com.ndc.channel.flight.xmlBean.verifyPrice.response.bean;

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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS}Penalty" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS}Remark"/>
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
    "penalty",
    "remark"
})
@XmlRootElement(name = "FareRule", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS")
public class FareRule {

    @XmlElement(name = "Penalty", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS")
    protected List<Penalty> penalty;
    @XmlElement(name = "Remark", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OfferPriceRS", required = true)
    protected Remark remark;

    /**
     * Gets the value of the penalty property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the penalty property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPenalty().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Penalty }
     * 
     * 
     */
    public List<Penalty> getPenalty() {
        if (penalty == null) {
            penalty = new ArrayList<Penalty>();
        }
        return this.penalty;
    }

    /**
     * 获取remark属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Remark }
     *     
     */
    public Remark getRemark() {
        return remark;
    }

    /**
     * 设置remark属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Remark }
     *     
     */
    public void setRemark(Remark value) {
        this.remark = value;
    }

}
