
package com.ndc.channel.flight.xmlBean.refundOrderDetail.request.bean;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.*;


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
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderRetrieveRQ}AltLangID"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderRetrieveRQ}CorrelationID"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderRetrieveRQ}EchoTokenText"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderRetrieveRQ}PrimaryLangID"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderRetrieveRQ}RetransmissionInd"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderRetrieveRQ}SeqNumber"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderRetrieveRQ}Timestamp"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderRetrieveRQ}TrxID"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderRetrieveRQ}TrxStatusCode"/>
 *         &lt;element ref="{http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderRetrieveRQ}VersionNumber"/>
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
    "altLangID",
    "correlationID",
    "echoTokenText",
    "primaryLangID",
    "retransmissionInd",
    "seqNumber",
    "timestamp",
    "trxID",
    "trxStatusCode",
    "versionNumber"
})
@XmlRootElement(name = "PayloadAttributes", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderRetrieveRQ")
public class PayloadAttributes {


    @XmlElement(name = "AltLangID", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderRetrieveRQ", required = true)
    protected String altLangID;
    @XmlElement(name = "CorrelationID", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderRetrieveRQ", required = true)
    protected String correlationID;
    @XmlElement(name = "EchoTokenText", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderRetrieveRQ", required = true)
    protected String echoTokenText;
    @XmlElement(name = "PrimaryLangID", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderRetrieveRQ", required = true)
    protected String primaryLangID;
    @XmlElement(name = "RetransmissionInd", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderRetrieveRQ", required = true)
    protected String retransmissionInd;
    @XmlElement(name = "SeqNumber", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderRetrieveRQ", required = true)
    protected String seqNumber;
    @XmlElement(name = "Timestamp", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderRetrieveRQ", required = true)
    protected String timestamp;
    @XmlElement(name = "TrxID", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderRetrieveRQ", required = true)
    protected String trxID;
    @XmlElement(name = "TrxStatusCode", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderRetrieveRQ", required = true)
    protected String trxStatusCode;
    @XmlElement(name = "VersionNumber", namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderRetrieveRQ", required = true)
    protected String versionNumber;

    public PayloadAttributes() {
        this.altLangID = "CN";
        this.correlationID = "";
        this.echoTokenText = UUID.randomUUID().toString();
        this.primaryLangID = "CN";
        this.retransmissionInd = "false";
        this.seqNumber = "1";
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
        this.trxID = "";
        this.trxStatusCode = "0";
        this.versionNumber = "";
    }

    public String getAltLangID() {
        return altLangID;
    }

    public void setAltLangID(String altLangID) {
        this.altLangID = altLangID;
    }

    public String getCorrelationID() {
        return correlationID;
    }

    public void setCorrelationID(String correlationID) {
        this.correlationID = correlationID;
    }

    public String getEchoTokenText() {
        return echoTokenText;
    }

    public void setEchoTokenText(String echoTokenText) {
        this.echoTokenText = echoTokenText;
    }

    public String getPrimaryLangID() {
        return primaryLangID;
    }

    public void setPrimaryLangID(String primaryLangID) {
        this.primaryLangID = primaryLangID;
    }

    public String getRetransmissionInd() {
        return retransmissionInd;
    }

    public void setRetransmissionInd(String retransmissionInd) {
        this.retransmissionInd = retransmissionInd;
    }

    public String getSeqNumber() {
        return seqNumber;
    }

    public void setSeqNumber(String seqNumber) {
        this.seqNumber = seqNumber;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getTrxID() {
        return trxID;
    }

    public void setTrxID(String trxID) {
        this.trxID = trxID;
    }

    public String getTrxStatusCode() {
        return trxStatusCode;
    }

    public void setTrxStatusCode(String trxStatusCode) {
        this.trxStatusCode = trxStatusCode;
    }

    public String getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(String versionNumber) {
        this.versionNumber = versionNumber;
    }
}
