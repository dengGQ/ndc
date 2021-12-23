
package com.ndc.channel.flight.xmlBean.flightSearch.response.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;


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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS}OriginDest"/>
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
    "originDest"
})
@XmlRootElement(name = "OriginDestList", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS")
public class OriginDestList {

    @XmlElement(name = "OriginDest", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS", required = true)
    protected List<OriginDest> originDest;

    public List<OriginDest> getOriginDest() {
        return originDest;
    }

    public void setOriginDest(List<OriginDest> originDest) {
        this.originDest = originDest;
    }
}
