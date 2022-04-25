
package com.ndc.channel.flight.xmlBean.refundApply.response.bean;

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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}Code" minOccurs="0"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}DescText" minOccurs="0"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}TypeCode" minOccurs="0"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS}Error" minOccurs="0"/>
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
    "code",
    "descText",
    "typeCode",
    "error"
})
@XmlRootElement(name = "Error", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS")
public class Error {

    @XmlElement(name = "Code", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS")
    protected String code;
    @XmlElement(name = "DescText", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS")
    protected String descText;
    @XmlElement(name = "TypeCode", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS")
    protected String typeCode;
    @XmlElement(name = "Error", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS")
    protected Error error;

    private static Error defaultError = new Error();

    public static Error getDefaultError() {
        defaultError.setDescText("东航NDC接口异常！");
        return defaultError;
    }
    /**
     * 获取code属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置code属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCode(String value) {
        this.code = value;
    }

    /**
     * 获取descText属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescText() {
        return descText;
    }

    /**
     * 设置descText属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescText(String value) {
        this.descText = value;
    }

    /**
     * 获取typeCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTypeCode() {
        return typeCode;
    }

    /**
     * 设置typeCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTypeCode(String value) {
        this.typeCode = value;
    }

    /**
     * 获取error属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Error }
     *     
     */
    public Error getError() {
        return error;
    }

    /**
     * 设置error属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Error }
     *     
     */
    public void setError(Error value) {
        this.error = value;
    }

}
