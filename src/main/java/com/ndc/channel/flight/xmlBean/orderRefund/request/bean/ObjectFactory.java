
package com.ndc.channel.flight.xmlBean.orderRefund.request.bean;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ndc.channel.flight.xmlBean.orderRefund.request.bean package. 
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

    private final static QName _CorrelationID_QNAME = new QName("http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderRetrieveRQ", "CorrelationID");
    private final static QName _TrxStatusCode_QNAME = new QName("http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderRetrieveRQ", "TrxStatusCode");
    private final static QName _RefVersionNumber_QNAME = new QName("http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderRetrieveRQ", "RefVersionNumber");
    private final static QName _AltLangID_QNAME = new QName("http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderRetrieveRQ", "AltLangID");
    private final static QName _TravelAgentID_QNAME = new QName("http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderRetrieveRQ", "TravelAgentID");
    private final static QName _OwnerCode_QNAME = new QName("http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderRetrieveRQ", "OwnerCode");
    private final static QName _PrimaryLangID_QNAME = new QName("http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderRetrieveRQ", "PrimaryLangID");
    private final static QName _RetransmissionInd_QNAME = new QName("http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderRetrieveRQ", "RetransmissionInd");
    private final static QName _AirlineDesigCode_QNAME = new QName("http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderRetrieveRQ", "AirlineDesigCode");
    private final static QName _DuplicateDesigInd_QNAME = new QName("http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderRetrieveRQ", "DuplicateDesigInd");
    private final static QName _Name_QNAME = new QName("http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderRetrieveRQ", "Name");
    private final static QName _VersionNumber_QNAME = new QName("http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderRetrieveRQ", "VersionNumber");
    private final static QName _Timestamp_QNAME = new QName("http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderRetrieveRQ", "Timestamp");
    private final static QName _OrderID_QNAME = new QName("http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderRetrieveRQ", "OrderID");
    private final static QName _SeqNumber_QNAME = new QName("http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderRetrieveRQ", "SeqNumber");
    private final static QName _TrxID_QNAME = new QName("http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderRetrieveRQ", "TrxID");
    private final static QName _OwnerTypeCode_QNAME = new QName("http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderRetrieveRQ", "OwnerTypeCode");
    private final static QName _TypeCode_QNAME = new QName("http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderRetrieveRQ", "TypeCode");
    private final static QName _AgencyID_QNAME = new QName("http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderRetrieveRQ", "AgencyID");
    private final static QName _EchoTokenText_QNAME = new QName("http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderRetrieveRQ", "EchoTokenText");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ndc.channel.flight.xmlBean.orderRefund.request.bean
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link OrderFilterCriteria }
     * 
     */
    public OrderFilterCriteria createOrderFilterCriteria() {
        return new OrderFilterCriteria();
    }

    /**
     * Create an instance of {@link Order }
     * 
     */
    public Order createOrder() {
        return new Order();
    }

    /**
     * Create an instance of {@link Party }
     * 
     */
    public Party createParty() {
        return new Party();
    }

    /**
     * Create an instance of {@link Recipient }
     * 
     */
    public Recipient createRecipient() {
        return new Recipient();
    }

    /**
     * Create an instance of {@link MarketingCarrier }
     * 
     */
    public MarketingCarrier createMarketingCarrier() {
        return new MarketingCarrier();
    }

    /**
     * Create an instance of {@link Sender }
     * 
     */
    public Sender createSender() {
        return new Sender();
    }

    /**
     * Create an instance of {@link TravelAgency }
     * 
     */
    public TravelAgency createTravelAgency() {
        return new TravelAgency();
    }

    /**
     * Create an instance of {@link TravelAgent }
     * 
     */
    public TravelAgent createTravelAgent() {
        return new TravelAgent();
    }

    /**
     * Create an instance of {@link MessageDoc }
     * 
     */
    public MessageDoc createMessageDoc() {
        return new MessageDoc();
    }

    /**
     * Create an instance of {@link Request }
     * 
     */
    public Request createRequest() {
        return new Request();
    }

    /**
     * Create an instance of {@link IATAOrderRetrieveRQ }
     * 
     */
    public IATAOrderRetrieveRQ createIATAOrderRetrieveRQ() {
        return new IATAOrderRetrieveRQ();
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
    @XmlElementDecl(namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderRetrieveRQ", name = "CorrelationID")
    public JAXBElement<String> createCorrelationID(String value) {
        return new JAXBElement<String>(_CorrelationID_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderRetrieveRQ", name = "TrxStatusCode")
    public JAXBElement<String> createTrxStatusCode(String value) {
        return new JAXBElement<String>(_TrxStatusCode_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderRetrieveRQ", name = "RefVersionNumber")
    public JAXBElement<String> createRefVersionNumber(String value) {
        return new JAXBElement<String>(_RefVersionNumber_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderRetrieveRQ", name = "AltLangID")
    public JAXBElement<String> createAltLangID(String value) {
        return new JAXBElement<String>(_AltLangID_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderRetrieveRQ", name = "TravelAgentID")
    public JAXBElement<String> createTravelAgentID(String value) {
        return new JAXBElement<String>(_TravelAgentID_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderRetrieveRQ", name = "OwnerCode")
    public JAXBElement<String> createOwnerCode(String value) {
        return new JAXBElement<String>(_OwnerCode_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderRetrieveRQ", name = "PrimaryLangID")
    public JAXBElement<String> createPrimaryLangID(String value) {
        return new JAXBElement<String>(_PrimaryLangID_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderRetrieveRQ", name = "RetransmissionInd")
    public JAXBElement<String> createRetransmissionInd(String value) {
        return new JAXBElement<String>(_RetransmissionInd_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderRetrieveRQ", name = "AirlineDesigCode")
    public JAXBElement<String> createAirlineDesigCode(String value) {
        return new JAXBElement<String>(_AirlineDesigCode_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderRetrieveRQ", name = "DuplicateDesigInd")
    public JAXBElement<String> createDuplicateDesigInd(String value) {
        return new JAXBElement<String>(_DuplicateDesigInd_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderRetrieveRQ", name = "Name")
    public JAXBElement<String> createName(String value) {
        return new JAXBElement<String>(_Name_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderRetrieveRQ", name = "VersionNumber")
    public JAXBElement<String> createVersionNumber(String value) {
        return new JAXBElement<String>(_VersionNumber_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderRetrieveRQ", name = "Timestamp")
    public JAXBElement<String> createTimestamp(String value) {
        return new JAXBElement<String>(_Timestamp_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderRetrieveRQ", name = "OrderID")
    public JAXBElement<String> createOrderID(String value) {
        return new JAXBElement<String>(_OrderID_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderRetrieveRQ", name = "SeqNumber")
    public JAXBElement<String> createSeqNumber(String value) {
        return new JAXBElement<String>(_SeqNumber_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderRetrieveRQ", name = "TrxID")
    public JAXBElement<String> createTrxID(String value) {
        return new JAXBElement<String>(_TrxID_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderRetrieveRQ", name = "OwnerTypeCode")
    public JAXBElement<String> createOwnerTypeCode(String value) {
        return new JAXBElement<String>(_OwnerTypeCode_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderRetrieveRQ", name = "TypeCode")
    public JAXBElement<String> createTypeCode(String value) {
        return new JAXBElement<String>(_TypeCode_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderRetrieveRQ", name = "AgencyID")
    public JAXBElement<String> createAgencyID(String value) {
        return new JAXBElement<String>(_AgencyID_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderRetrieveRQ", name = "EchoTokenText")
    public JAXBElement<String> createEchoTokenText(String value) {
        return new JAXBElement<String>(_EchoTokenText_QNAME, String.class, null, value);
    }

}
