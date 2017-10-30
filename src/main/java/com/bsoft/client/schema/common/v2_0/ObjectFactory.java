
package com.bsoft.client.schema.common.v2_0;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.csapi.schema.common.v2_0 package. 
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

    private final static QName _PolicyException_QNAME = new QName("http://www.csapi.org/schema/common/v2_0", "PolicyException");
    private final static QName _ServiceException_QNAME = new QName("http://www.csapi.org/schema/common/v2_0", "ServiceException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.csapi.schema.common.v2_0
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PolicyException }
     * 
     */
    public PolicyException createPolicyException() {
        return new PolicyException();
    }

    /**
     * Create an instance of {@link ServiceException }
     * 
     */
    public ServiceException createServiceException() {
        return new ServiceException();
    }

    /**
     * Create an instance of {@link MessageNotificationType }
     * 
     */
    public MessageNotificationType createMessageNotificationType() {
        return new MessageNotificationType();
    }

    /**
     * Create an instance of {@link TimeMetric }
     * 
     */
    public TimeMetric createTimeMetric() {
        return new TimeMetric();
    }

    /**
     * Create an instance of {@link ServiceError }
     * 
     */
    public ServiceError createServiceError() {
        return new ServiceError();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PolicyException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.csapi.org/schema/common/v2_0", name = "PolicyException")
    public JAXBElement<PolicyException> createPolicyException(PolicyException value) {
        return new JAXBElement<PolicyException>(_PolicyException_QNAME, PolicyException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ServiceException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.csapi.org/schema/common/v2_0", name = "ServiceException")
    public JAXBElement<ServiceException> createServiceException(ServiceException value) {
        return new JAXBElement<ServiceException>(_ServiceException_QNAME, ServiceException.class, null, value);
    }

}
