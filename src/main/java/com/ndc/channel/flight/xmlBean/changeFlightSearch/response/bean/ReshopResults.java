
package com.ndc.channel.flight.xmlBean.changeFlightSearch.response.bean;

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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS}ReshopOffers"/>
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
    "reshopOffers"
})
@XmlRootElement(name = "ReshopResults", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS")
public class ReshopResults {

    @XmlElementWrapper(name = "ReshopOffers", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS", required = true)
    @XmlElement(name = "Offer", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderReshopRS", required = true)
    protected List<Offer> reshopOffers;

    public List<Offer> getReshopOffers() {
        return reshopOffers;
    }

    public void setReshopOffers(List<Offer> reshopOffers) {
        this.reshopOffers = reshopOffers;
    }
}
