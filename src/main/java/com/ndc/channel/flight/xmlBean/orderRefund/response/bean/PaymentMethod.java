
package com.ndc.channel.flight.xmlBean.orderRefund.response.bean;

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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}BankTransfer"/>
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
    "bankTransfer"
})
@XmlRootElement(name = "PaymentMethod", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS")
public class PaymentMethod {

    @XmlElement(name = "BankTransfer", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", required = true)
    protected BankTransfer bankTransfer;

    /**
     * 获取bankTransfer属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BankTransfer }
     *     
     */
    public BankTransfer getBankTransfer() {
        return bankTransfer;
    }

    /**
     * 设置bankTransfer属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BankTransfer }
     *     
     */
    public void setBankTransfer(BankTransfer value) {
        this.bankTransfer = value;
    }

}
