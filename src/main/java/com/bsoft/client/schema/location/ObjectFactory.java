
package com.bsoft.client.schema.location;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.csapi.schema.location package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.csapi.schema.location
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetLocationRequest }
     * 
     */
    public GetLocationRequest createGetLocationRequest() {
        return new GetLocationRequest();
    }

    /**
     * Create an instance of {@link CoordinateReferenceSystem }
     * 
     */
    public CoordinateReferenceSystem createCoordinateReferenceSystem() {
        return new CoordinateReferenceSystem();
    }

    /**
     * Create an instance of {@link GetLocationResponse }
     * 
     */
    public GetLocationResponse createGetLocationResponse() {
        return new GetLocationResponse();
    }

    /**
     * Create an instance of {@link LocationInfo }
     * 
     */
    public LocationInfo createLocationInfo() {
        return new LocationInfo();
    }

    /**
     * Create an instance of {@link GetLocationForGroupRequest }
     * 
     */
    public GetLocationForGroupRequest createGetLocationForGroupRequest() {
        return new GetLocationForGroupRequest();
    }

    /**
     * Create an instance of {@link GetLocationForGroupResponse }
     * 
     */
    public GetLocationForGroupResponse createGetLocationForGroupResponse() {
        return new GetLocationForGroupResponse();
    }

    /**
     * Create an instance of {@link LocationData }
     * 
     */
    public LocationData createLocationData() {
        return new LocationData();
    }

    /**
     * Create an instance of {@link StartPeriodicNotificationRequest }
     * 
     */
    public StartPeriodicNotificationRequest createStartPeriodicNotificationRequest() {
        return new StartPeriodicNotificationRequest();
    }

    /**
     * Create an instance of {@link StartPeriodicNotificationResponse }
     * 
     */
    public StartPeriodicNotificationResponse createStartPeriodicNotificationResponse() {
        return new StartPeriodicNotificationResponse();
    }

    /**
     * Create an instance of {@link EndNotificationRequest }
     * 
     */
    public EndNotificationRequest createEndNotificationRequest() {
        return new EndNotificationRequest();
    }

    /**
     * Create an instance of {@link LocationNotificationRequest }
     * 
     */
    public LocationNotificationRequest createLocationNotificationRequest() {
        return new LocationNotificationRequest();
    }

    /**
     * Create an instance of {@link LocationErrorRequest }
     * 
     */
    public LocationErrorRequest createLocationErrorRequest() {
        return new LocationErrorRequest();
    }

    /**
     * Create an instance of {@link LocationEndRequest }
     * 
     */
    public LocationEndRequest createLocationEndRequest() {
        return new LocationEndRequest();
    }

}
