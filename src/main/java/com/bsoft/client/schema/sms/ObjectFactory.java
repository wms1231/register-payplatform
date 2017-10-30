
package com.bsoft.client.schema.sms;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.csapi.schema.sms package. 
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


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.csapi.schema.sms
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SendSmsRequest }
     * 
     */
    public SendSmsRequest createSendSmsRequest() {
        return new SendSmsRequest();
    }

    /**
     * Create an instance of {@link SendSmsResponse }
     * 
     */
    public SendSmsResponse createSendSmsResponse() {
        return new SendSmsResponse();
    }

    /**
     * Create an instance of {@link NotifySmsDeliveryStatusRequest }
     * 
     */
    public NotifySmsDeliveryStatusRequest createNotifySmsDeliveryStatusRequest() {
        return new NotifySmsDeliveryStatusRequest();
    }

    /**
     * Create an instance of {@link DeliveryInformation }
     * 
     */
    public DeliveryInformation createDeliveryInformation() {
        return new DeliveryInformation();
    }

    /**
     * Create an instance of {@link GetReceivedSmsRequest }
     * 
     */
    public GetReceivedSmsRequest createGetReceivedSmsRequest() {
        return new GetReceivedSmsRequest();
    }

    /**
     * Create an instance of {@link GetReceivedSmsResponse }
     * 
     */
    public GetReceivedSmsResponse createGetReceivedSmsResponse() {
        return new GetReceivedSmsResponse();
    }

    /**
     * Create an instance of {@link SMSMessage }
     * 
     */
    public SMSMessage createSMSMessage() {
        return new SMSMessage();
    }

    /**
     * Create an instance of {@link GetSmsDeliveryStatusRequest }
     * 
     */
    public GetSmsDeliveryStatusRequest createGetSmsDeliveryStatusRequest() {
        return new GetSmsDeliveryStatusRequest();
    }

    /**
     * Create an instance of {@link GetSmsDeliveryStatusResponse }
     * 
     */
    public GetSmsDeliveryStatusResponse createGetSmsDeliveryStatusResponse() {
        return new GetSmsDeliveryStatusResponse();
    }

    /**
     * Create an instance of {@link NotifySmsReceptionRequest }
     * 
     */
    public NotifySmsReceptionRequest createNotifySmsReceptionRequest() {
        return new NotifySmsReceptionRequest();
    }

}
