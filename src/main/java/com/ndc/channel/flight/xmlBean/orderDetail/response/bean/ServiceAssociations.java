
package com.ndc.channel.flight.xmlBean.orderDetail.response.bean;

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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}ServiceDefinitionRef" minOccurs="0"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}SelectedSeat" minOccurs="0"/>
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
    "serviceDefinitionRef",
    "selectedSeat"
})
@XmlRootElement(name = "ServiceAssociations", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS")
public class ServiceAssociations {

    @XmlElement(name = "ServiceDefinitionRef", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS")
    protected ServiceDefinitionRef serviceDefinitionRef;
    @XmlElement(name = "SelectedSeat", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS")
    protected SelectedSeat selectedSeat;

    /**
     * 获取serviceDefinitionRef属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ServiceDefinitionRef }
     *     
     */
    public ServiceDefinitionRef getServiceDefinitionRef() {
        return serviceDefinitionRef;
    }

    /**
     * 设置serviceDefinitionRef属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceDefinitionRef }
     *     
     */
    public void setServiceDefinitionRef(ServiceDefinitionRef value) {
        this.serviceDefinitionRef = value;
    }

    /**
     * 获取selectedSeat属性的值。
     * 
     * @return
     *     possible object is
     *     {@link SelectedSeat }
     *     
     */
    public SelectedSeat getSelectedSeat() {
        return selectedSeat;
    }

    /**
     * 设置selectedSeat属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link SelectedSeat }
     *     
     */
    public void setSelectedSeat(SelectedSeat value) {
        this.selectedSeat = value;
    }

}
