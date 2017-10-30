
package com.bsoft.client.schema.mms;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.csapi.schema.mms package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.csapi.schema.mms
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SendMessageRequest }
     * 
     */
    public SendMessageRequest createSendMessageRequest() {
        return new SendMessageRequest();
    }

    /**
     * Create an instance of {@link SendMessageResponse }
     * 
     */
    public SendMessageResponse createSendMessageResponse() {
        return new SendMessageResponse();
    }

    /**
     * Create an instance of {@link GetMessageDeliveryStatusRequest }
     * 
     */
    public GetMessageDeliveryStatusRequest createGetMessageDeliveryStatusRequest() {
        return new GetMessageDeliveryStatusRequest();
    }

    /**
     * Create an instance of {@link GetMessageDeliveryStatusResponse }
     * 
     */
    public GetMessageDeliveryStatusResponse createGetMessageDeliveryStatusResponse() {
        return new GetMessageDeliveryStatusResponse();
    }

    /**
     * Create an instance of {@link DeliveryInformation }
     * 
     */
    public DeliveryInformation createDeliveryInformation() {
        return new DeliveryInformation();
    }

    /**
     * Create an instance of {@link GetReceivedMessagesRequest }
     * 
     */
    public GetReceivedMessagesRequest createGetReceivedMessagesRequest() {
        return new GetReceivedMessagesRequest();
    }

    /**
     * Create an instance of {@link GetReceivedMessagesResponse }
     * 
     */
    public GetReceivedMessagesResponse createGetReceivedMessagesResponse() {
        return new GetReceivedMessagesResponse();
    }

    /**
     * Create an instance of {@link MessageReference }
     * 
     */
    public MessageReference createMessageReference() {
        return new MessageReference();
    }

    /**
     * Create an instance of {@link GetMessageRequest }
     * 
     */
    public GetMessageRequest createGetMessageRequest() {
        return new GetMessageRequest();
    }

    /**
     * Create an instance of {@link GetMessageResponse }
     * 
     */
    public GetMessageResponse createGetMessageResponse() {
        return new GetMessageResponse();
    }

    /**
     * Create an instance of {@link MmsMessage }
     * 
     */
    public MmsMessage createMmsMessage() {
        return new MmsMessage();
    }

    /**
     * Create an instance of {@link NotifyMessageReceptionRequest }
     * 
     */
    public NotifyMessageReceptionRequest createNotifyMessageReceptionRequest() {
        return new NotifyMessageReceptionRequest();
    }

    /**
     * Create an instance of {@link NotifyMessageDeliveryReceiptRequest }
     * 
     */
    public NotifyMessageDeliveryReceiptRequest createNotifyMessageDeliveryReceiptRequest() {
        return new NotifyMessageDeliveryReceiptRequest();
    }

}
