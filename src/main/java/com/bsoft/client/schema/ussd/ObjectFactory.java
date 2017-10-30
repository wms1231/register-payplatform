
package com.bsoft.client.schema.ussd;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.csapi.schema.ussd package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.csapi.schema.ussd
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link MakeUssdRequest }
     * 
     */
    public MakeUssdRequest createMakeUssdRequest() {
        return new MakeUssdRequest();
    }

    /**
     * Create an instance of {@link UssdArray }
     * 
     */
    public UssdArray createUssdArray() {
        return new UssdArray();
    }

    /**
     * Create an instance of {@link MakeUssdResponse }
     * 
     */
    public MakeUssdResponse createMakeUssdResponse() {
        return new MakeUssdResponse();
    }

    /**
     * Create an instance of {@link HandleUssdRequest }
     * 
     */
    public HandleUssdRequest createHandleUssdRequest() {
        return new HandleUssdRequest();
    }

    /**
     * Create an instance of {@link HandleUssdResponse }
     * 
     */
    public HandleUssdResponse createHandleUssdResponse() {
        return new HandleUssdResponse();
    }

    /**
     * Create an instance of {@link UssdContinueRequest }
     * 
     */
    public UssdContinueRequest createUssdContinueRequest() {
        return new UssdContinueRequest();
    }

    /**
     * Create an instance of {@link UssdContinueResponse }
     * 
     */
    public UssdContinueResponse createUssdContinueResponse() {
        return new UssdContinueResponse();
    }

    /**
     * Create an instance of {@link EndUssdRequest }
     * 
     */
    public EndUssdRequest createEndUssdRequest() {
        return new EndUssdRequest();
    }

    /**
     * Create an instance of {@link NotifyUssdEndRequest }
     * 
     */
    public NotifyUssdEndRequest createNotifyUssdEndRequest() {
        return new NotifyUssdEndRequest();
    }

}
