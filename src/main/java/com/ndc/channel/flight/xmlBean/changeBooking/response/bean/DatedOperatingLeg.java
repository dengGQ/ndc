
package com.ndc.channel.flight.xmlBean.changeBooking.response.bean;

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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}Arrival"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}CarrierAircraftType"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}DatedOperatingLegID"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}Dep"/>
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
    "arrival",
    "carrierAircraftType",
    "datedOperatingLegID",
    "dep"
})
@XmlRootElement(name = "DatedOperatingLeg", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS")
public class DatedOperatingLeg {

    @XmlElement(name = "Arrival", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", required = true)
    protected Arrival arrival;
    @XmlElement(name = "CarrierAircraftType", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", required = true)
    protected CarrierAircraftType carrierAircraftType;
    @XmlElement(name = "DatedOperatingLegID", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", required = true)
    protected String datedOperatingLegID;
    @XmlElement(name = "Dep", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", required = true)
    protected Dep dep;

    /**
     * 获取arrival属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Arrival }
     *     
     */
    public Arrival getArrival() {
        return arrival;
    }

    /**
     * 设置arrival属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Arrival }
     *     
     */
    public void setArrival(Arrival value) {
        this.arrival = value;
    }

    /**
     * 获取carrierAircraftType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CarrierAircraftType }
     *     
     */
    public CarrierAircraftType getCarrierAircraftType() {
        return carrierAircraftType;
    }

    /**
     * 设置carrierAircraftType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CarrierAircraftType }
     *     
     */
    public void setCarrierAircraftType(CarrierAircraftType value) {
        this.carrierAircraftType = value;
    }

    /**
     * 获取datedOperatingLegID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDatedOperatingLegID() {
        return datedOperatingLegID;
    }

    /**
     * 设置datedOperatingLegID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDatedOperatingLegID(String value) {
        this.datedOperatingLegID = value;
    }

    /**
     * 获取dep属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Dep }
     *     
     */
    public Dep getDep() {
        return dep;
    }

    /**
     * 设置dep属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Dep }
     *     
     */
    public void setDep(Dep value) {
        this.dep = value;
    }

}
