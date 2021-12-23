
package com.ndc.channel.flight.xmlBean.flightSearch.request.bean;

import javax.xml.bind.annotation.*;
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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRQ}AffinityOriginDest"/>
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
    "affinityOriginDest"
})
@XmlRootElement(name = "AffinityShoppingCriteria", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRQ")
public class AffinityShoppingCriteria {

    @XmlElement(name = "AffinityOriginDest", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRQ", required = true)
    protected List<AffinityOriginDest> affinityOriginDest;

    public List<AffinityOriginDest> getAffinityOriginDest() {
        return affinityOriginDest;
    }

    public void setAffinityOriginDest(List<AffinityOriginDest> affinityOriginDest) {
        this.affinityOriginDest = affinityOriginDest;
    }
}
