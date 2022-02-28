
package com.ndc.channel.flight.xmlBean.orderPay.request.bean;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ndc.channel.flight.xmlBean.orderPay.request.bean package. 
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

    private final static QName _AltLangID_QNAME = new QName("http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", "AltLangID");
    private final static QName _PseudoCityID_QNAME = new QName("http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", "PseudoCityID");
    private final static QName _TrxStatusCode_QNAME = new QName("http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", "TrxStatusCode");
    private final static QName _BankAccountID_QNAME = new QName("http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", "BankAccountID");
    private final static QName _OwnerCode_QNAME = new QName("http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", "OwnerCode");
    private final static QName _TravelAgentID_QNAME = new QName("http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", "TravelAgentID");
    private final static QName _SeqNumber_QNAME = new QName("http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", "SeqNumber");
    private final static QName _OrderID_QNAME = new QName("http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", "OrderID");
    private final static QName _PaymentInfoID_QNAME = new QName("http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", "PaymentInfoID");
    private final static QName _Timestamp_QNAME = new QName("http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", "Timestamp");
    private final static QName _Name_QNAME = new QName("http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", "Name");
    private final static QName _DuplicateDesigInd_QNAME = new QName("http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", "DuplicateDesigInd");
    private final static QName _IATANumber_QNAME = new QName("http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", "IATANumber");
    private final static QName _OwnerTypeCode_QNAME = new QName("http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", "OwnerTypeCode");
    private final static QName _AggregatorID_QNAME = new QName("http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", "AggregatorID");
    private final static QName _OrderItemRefID_QNAME = new QName("http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", "OrderItemRefID");
    private final static QName _RefVersionNumber_QNAME = new QName("http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", "RefVersionNumber");
    private final static QName _AccountTypeText_QNAME = new QName("http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", "AccountTypeText");
    private final static QName _CorrelationID_QNAME = new QName("http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", "CorrelationID");
    private final static QName _VersionNumber_QNAME = new QName("http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", "VersionNumber");
    private final static QName _AirlineDesigCode_QNAME = new QName("http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", "AirlineDesigCode");
    private final static QName _PrimaryLangID_QNAME = new QName("http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", "PrimaryLangID");
    private final static QName _RetransmissionInd_QNAME = new QName("http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", "RetransmissionInd");
    private final static QName _AgencyID_QNAME = new QName("http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", "AgencyID");
    private final static QName _EchoTokenText_QNAME = new QName("http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", "EchoTokenText");
    private final static QName _TypeCode_QNAME = new QName("http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", "TypeCode");
    private final static QName _TrxID_QNAME = new QName("http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", "TrxID");
    private final static QName _ContactInfoRefID_QNAME = new QName("http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", "ContactInfoRefID");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ndc.channel.flight.xmlBean.orderPay.request.bean
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Party }
     * 
     */
    public Party createParty() {
        return new Party();
    }

    /**
     * Create an instance of {@link Aggregator }
     * 
     */
    public Aggregator createAggregator() {
        return new Aggregator();
    }

    /**
     * Create an instance of {@link Participant }
     * 
     */
    public Participant createParticipant() {
        return new Participant();
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
     * Create an instance of {@link MessageDoc }
     * 
     */
    public MessageDoc createMessageDoc() {
        return new MessageDoc();
    }

    /**
     * Create an instance of {@link Order }
     * 
     */
    public Order createOrder() {
        return new Order();
    }

    /**
     * Create an instance of {@link PaymentInfo }
     * 
     */
    public PaymentInfo createPaymentInfo() {
        return new PaymentInfo();
    }

    /**
     * Create an instance of {@link Amount }
     * 
     */
    public Amount createAmount() {
        return new Amount();
    }

    /**
     * Create an instance of {@link PaymentMethod }
     * 
     */
    public PaymentMethod createPaymentMethod() {
        return new PaymentMethod();
    }

    /**
     * Create an instance of {@link BankTransfer }
     * 
     */
    public BankTransfer createBankTransfer() {
        return new BankTransfer();
    }

    /**
     * Create an instance of {@link Request }
     * 
     */
    public Request createRequest() {
        return new Request();
    }

    /**
     * Create an instance of {@link IATAOrderChangeRQ }
     * 
     */
    public IATAOrderChangeRQ createIATAOrderChangeRQ() {
        return new IATAOrderChangeRQ();
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
    @XmlElementDecl(namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", name = "AltLangID")
    public JAXBElement<String> createAltLangID(String value) {
        return new JAXBElement<String>(_AltLangID_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", name = "PseudoCityID")
    public JAXBElement<String> createPseudoCityID(String value) {
        return new JAXBElement<String>(_PseudoCityID_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", name = "TrxStatusCode")
    public JAXBElement<String> createTrxStatusCode(String value) {
        return new JAXBElement<String>(_TrxStatusCode_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", name = "BankAccountID")
    public JAXBElement<String> createBankAccountID(String value) {
        return new JAXBElement<String>(_BankAccountID_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", name = "OwnerCode")
    public JAXBElement<String> createOwnerCode(String value) {
        return new JAXBElement<String>(_OwnerCode_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", name = "TravelAgentID")
    public JAXBElement<String> createTravelAgentID(String value) {
        return new JAXBElement<String>(_TravelAgentID_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", name = "SeqNumber")
    public JAXBElement<String> createSeqNumber(String value) {
        return new JAXBElement<String>(_SeqNumber_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", name = "OrderID")
    public JAXBElement<String> createOrderID(String value) {
        return new JAXBElement<String>(_OrderID_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", name = "PaymentInfoID")
    public JAXBElement<String> createPaymentInfoID(String value) {
        return new JAXBElement<String>(_PaymentInfoID_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", name = "Timestamp")
    public JAXBElement<String> createTimestamp(String value) {
        return new JAXBElement<String>(_Timestamp_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", name = "Name")
    public JAXBElement<String> createName(String value) {
        return new JAXBElement<String>(_Name_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", name = "DuplicateDesigInd")
    public JAXBElement<String> createDuplicateDesigInd(String value) {
        return new JAXBElement<String>(_DuplicateDesigInd_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", name = "IATANumber")
    public JAXBElement<String> createIATANumber(String value) {
        return new JAXBElement<String>(_IATANumber_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", name = "OwnerTypeCode")
    public JAXBElement<String> createOwnerTypeCode(String value) {
        return new JAXBElement<String>(_OwnerTypeCode_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", name = "AggregatorID")
    public JAXBElement<String> createAggregatorID(String value) {
        return new JAXBElement<String>(_AggregatorID_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", name = "OrderItemRefID")
    public JAXBElement<String> createOrderItemRefID(String value) {
        return new JAXBElement<String>(_OrderItemRefID_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", name = "RefVersionNumber")
    public JAXBElement<String> createRefVersionNumber(String value) {
        return new JAXBElement<String>(_RefVersionNumber_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", name = "AccountTypeText")
    public JAXBElement<String> createAccountTypeText(String value) {
        return new JAXBElement<String>(_AccountTypeText_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", name = "CorrelationID")
    public JAXBElement<String> createCorrelationID(String value) {
        return new JAXBElement<String>(_CorrelationID_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", name = "VersionNumber")
    public JAXBElement<String> createVersionNumber(String value) {
        return new JAXBElement<String>(_VersionNumber_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", name = "AirlineDesigCode")
    public JAXBElement<String> createAirlineDesigCode(String value) {
        return new JAXBElement<String>(_AirlineDesigCode_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", name = "PrimaryLangID")
    public JAXBElement<String> createPrimaryLangID(String value) {
        return new JAXBElement<String>(_PrimaryLangID_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", name = "RetransmissionInd")
    public JAXBElement<String> createRetransmissionInd(String value) {
        return new JAXBElement<String>(_RetransmissionInd_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", name = "AgencyID")
    public JAXBElement<String> createAgencyID(String value) {
        return new JAXBElement<String>(_AgencyID_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", name = "EchoTokenText")
    public JAXBElement<String> createEchoTokenText(String value) {
        return new JAXBElement<String>(_EchoTokenText_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", name = "TypeCode")
    public JAXBElement<String> createTypeCode(String value) {
        return new JAXBElement<String>(_TypeCode_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", name = "TrxID")
    public JAXBElement<String> createTrxID(String value) {
        return new JAXBElement<String>(_TrxID_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.iata.org/IATA/2015/00/2018.2/IATA_OrderChangeRQ", name = "ContactInfoRefID")
    public JAXBElement<String> createContactInfoRefID(String value) {
        return new JAXBElement<String>(_ContactInfoRefID_QNAME, String.class, null, value);
    }

}
