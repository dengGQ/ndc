
package com.ndc.channel.flight.xmlBean.flightSearch.response.bean;

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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS}CarrierOffers"/>
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
    "carrierOffers"
})
@XmlRootElement(name = "OffersGroup", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS")
public class OffersGroup {

    @XmlElementWrapper(name = "CarrierOffers", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS")
    @XmlElement(name = "Offer", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_AirShoppingRS")
    protected List<Offer> carrierOffers;

    public List<Offer> getCarrierOffers() {
        return carrierOffers;
    }

    public void setCarrierOffers(List<Offer> carrierOffers) {
        this.carrierOffers = carrierOffers;
    }
}
