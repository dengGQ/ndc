
package com.ndc.channel.flight.xmlBean.refundReapply.response.bean;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ndc.channel.flight.xmlBean.refundReapply.response.bean package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _PrimaryLangID_QNAME = new QName("http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", "PrimaryLangID");
    private final static QName _RetransmissionInd_QNAME = new QName("http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", "RetransmissionInd");
    private final static QName _Name_QNAME = new QName("http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", "Name");
    private final static QName _VersionNumber_QNAME = new QName("http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", "VersionNumber");
    private final static QName _Code_QNAME = new QName("http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", "Code");
    private final static QName _Timestamp_QNAME = new QName("http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", "Timestamp");
    private final static QName _OrderID_QNAME = new QName("http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", "OrderID");
    private final static QName _SeqNumber_QNAME = new QName("http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", "SeqNumber");
    private final static QName _DescText_QNAME = new QName("http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", "DescText");
    private final static QName _TrxID_QNAME = new QName("http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", "TrxID");
    private final static QName _OwnerTypeCode_QNAME = new QName("http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", "OwnerTypeCode");
    private final static QName _EchoTokenText_QNAME = new QName("http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", "EchoTokenText");
    private final static QName _CorrelationID_QNAME = new QName("http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", "CorrelationID");
    private final static QName _RefVersionNumber_QNAME = new QName("http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", "RefVersionNumber");
    private final static QName _TrxStatusCode_QNAME = new QName("http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", "TrxStatusCode");
    private final static QName _AltLangID_QNAME = new QName("http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", "AltLangID");
    private final static QName _OwnerCode_QNAME = new QName("http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", "OwnerCode");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ndc.channel.flight.xmlBean.refundReapply.response.bean
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Order }
     * 
     */
    public Order createOrder() {
        return new Order();
    }

    /**
     * Create an instance of {@link IATAOrderViewRS }
     * 
     */
    public IATAOrderViewRS createIATAOrderViewRS() {
        return new IATAOrderViewRS();
    }

    /**
     * Create an instance of {@link Error }
     * 
     */
    public Error createError() {
        return new Error();
    }

    /**
     * Create an instance of {@link Response }
     * 
     */
    public Response createResponse() {
        return new Response();
    }

    /**
     * Create an instance of {@link MessageDoc }
     * 
     */
    public MessageDoc createMessageDoc() {
        return new MessageDoc();
    }

    /**
     * Create an instance of {@link PayloadAttributes }
     * 
     */
    public PayloadAttributes createPayloadAttributes() {
        return new PayloadAttributes();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", name = "PrimaryLangID")
    public JAXBElement<String> createPrimaryLangID(String value) {
        return new JAXBElement<String>(_PrimaryLangID_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", name = "RetransmissionInd")
    public JAXBElement<String> createRetransmissionInd(String value) {
        return new JAXBElement<String>(_RetransmissionInd_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", name = "Name")
    public JAXBElement<String> createName(String value) {
        return new JAXBElement<String>(_Name_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", name = "VersionNumber")
    public JAXBElement<String> createVersionNumber(String value) {
        return new JAXBElement<String>(_VersionNumber_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", name = "Code")
    public JAXBElement<String> createCode(String value) {
        return new JAXBElement<String>(_Code_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", name = "Timestamp")
    public JAXBElement<String> createTimestamp(String value) {
        return new JAXBElement<String>(_Timestamp_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", name = "OrderID")
    public JAXBElement<String> createOrderID(String value) {
        return new JAXBElement<String>(_OrderID_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", name = "SeqNumber")
    public JAXBElement<String> createSeqNumber(String value) {
        return new JAXBElement<String>(_SeqNumber_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", name = "DescText")
    public JAXBElement<String> createDescText(String value) {
        return new JAXBElement<String>(_DescText_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", name = "TrxID")
    public JAXBElement<String> createTrxID(String value) {
        return new JAXBElement<String>(_TrxID_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", name = "OwnerTypeCode")
    public JAXBElement<String> createOwnerTypeCode(String value) {
        return new JAXBElement<String>(_OwnerTypeCode_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", name = "EchoTokenText")
    public JAXBElement<String> createEchoTokenText(String value) {
        return new JAXBElement<String>(_EchoTokenText_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", name = "CorrelationID")
    public JAXBElement<String> createCorrelationID(String value) {
        return new JAXBElement<String>(_CorrelationID_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", name = "RefVersionNumber")
    public JAXBElement<String> createRefVersionNumber(String value) {
        return new JAXBElement<String>(_RefVersionNumber_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", name = "TrxStatusCode")
    public JAXBElement<String> createTrxStatusCode(String value) {
        return new JAXBElement<String>(_TrxStatusCode_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", name = "AltLangID")
    public JAXBElement<String> createAltLangID(String value) {
        return new JAXBElement<String>(_AltLangID_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderViewRS", name = "OwnerCode")
    public JAXBElement<String> createOwnerCode(String value) {
        return new JAXBElement<String>(_OwnerCode_QNAME, String.class, null, value);
    }

}
