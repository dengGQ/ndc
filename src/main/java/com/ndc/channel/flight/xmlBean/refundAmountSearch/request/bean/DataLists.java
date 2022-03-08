
package com.ndc.channel.flight.xmlBean.refundAmountSearch.request.bean;

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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ}ContactInfoList"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ}PaxList"/>
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
    "contactInfoList",
    "paxList"
})
@XmlRootElement(name = "DataLists", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ")
public class DataLists {

    @XmlElement(name = "ContactInfo", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", required = true)
    @XmlElementWrapper(name = "ContactInfoList", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", required = true)
    protected List<ContactInfo> contactInfoList;

    @XmlElement(name = "Pax", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", required = true)
    @XmlElementWrapper(name = "PaxList", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", required = true)
    protected List<Pax> paxList;

    public List<ContactInfo> getContactInfoList() {
        return contactInfoList;
    }

    public void setContactInfoList(List<ContactInfo> contactInfoList) {
        this.contactInfoList = contactInfoList;
    }

    public List<Pax> getPaxList() {
        return paxList;
    }

    public void setPaxList(List<Pax> paxList) {
        this.paxList = paxList;
    }
}
