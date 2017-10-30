
package com.bsoft.client.schema.location;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import com.bsoft.client.schema.common.v2_0.TimeMetric;


/**
 * <p>anonymous complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Requester" type="{http://www.w3.org/2001/XMLSchema}anyURI"/&gt;
 *         &lt;element name="Addresses" type="{http://www.w3.org/2001/XMLSchema}anyURI" maxOccurs="unbounded"/&gt;
 *         &lt;element name="RequestedAccuracy" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="AcceptableAccuracy" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="MaximumAge" type="{http://www.csapi.org/schema/common/v2_0}TimeMetric"/&gt;
 *         &lt;element name="ResponseTime" type="{http://www.csapi.org/schema/common/v2_0}TimeMetric"/&gt;
 *         &lt;element name="Tolerance" type="{http://www.csapi.org/schema/location}DelayTolerance"/&gt;
 *         &lt;element name="ApplicationId" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="serviceType" type="{http://www.csapi.org/schema/location}ServiceType"/&gt;
 *         &lt;element name="crs" type="{http://www.csapi.org/schema/location}CoordinateReferenceSystem"/&gt;
 *         &lt;element name="locType" type="{http://www.csapi.org/schema/location}LocType"/&gt;
 *         &lt;element name="prio" type="{http://www.csapi.org/schema/location}Priority"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "requester",
    "addresses",
    "requestedAccuracy",
    "acceptableAccuracy",
    "maximumAge",
    "responseTime",
    "tolerance",
    "applicationId",
    "serviceType",
    "crs",
    "locType",
    "prio"
})
@XmlRootElement(name = "getLocationForGroupRequest")
public class GetLocationForGroupRequest {

    @XmlElement(name = "Requester", required = true, nillable = true)
    @XmlSchemaType(name = "anyURI")
    protected String requester;
    @XmlElement(name = "Addresses", required = true, nillable = true)
    @XmlSchemaType(name = "anyURI")
    protected List<String> addresses;
    @XmlElement(name = "RequestedAccuracy")
    protected int requestedAccuracy;
    @XmlElement(name = "AcceptableAccuracy")
    protected int acceptableAccuracy;
    @XmlElement(name = "MaximumAge", required = true, nillable = true)
    protected TimeMetric maximumAge;
    @XmlElement(name = "ResponseTime", required = true, nillable = true)
    protected TimeMetric responseTime;
    @XmlElement(name = "Tolerance", required = true, nillable = true)
    @XmlSchemaType(name = "string")
    protected DelayTolerance tolerance;
    @XmlElement(name = "ApplicationId", required = true, nillable = true)
    protected String applicationId;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected ServiceType serviceType;
    @XmlElement(required = true)
    protected CoordinateReferenceSystem crs;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected LocType locType;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected Priority prio;

    /**
     * ��ȡrequester���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequester() {
        return requester;
    }

    /**
     * ����requester���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequester(String value) {
        this.requester = value;
    }

    /**
     * Gets the value of the addresses property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the addresses property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAddresses().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getAddresses() {
        if (addresses == null) {
            addresses = new ArrayList<String>();
        }
        return this.addresses;
    }

    /**
     * ��ȡrequestedAccuracy���Ե�ֵ��
     * 
     */
    public int getRequestedAccuracy() {
        return requestedAccuracy;
    }

    /**
     * ����requestedAccuracy���Ե�ֵ��
     * 
     */
    public void setRequestedAccuracy(int value) {
        this.requestedAccuracy = value;
    }

    /**
     * ��ȡacceptableAccuracy���Ե�ֵ��
     * 
     */
    public int getAcceptableAccuracy() {
        return acceptableAccuracy;
    }

    /**
     * ����acceptableAccuracy���Ե�ֵ��
     * 
     */
    public void setAcceptableAccuracy(int value) {
        this.acceptableAccuracy = value;
    }

    /**
     * ��ȡmaximumAge���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link TimeMetric }
     *     
     */
    public TimeMetric getMaximumAge() {
        return maximumAge;
    }

    /**
     * ����maximumAge���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link TimeMetric }
     *     
     */
    public void setMaximumAge(TimeMetric value) {
        this.maximumAge = value;
    }

    /**
     * ��ȡresponseTime���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link TimeMetric }
     *     
     */
    public TimeMetric getResponseTime() {
        return responseTime;
    }

    /**
     * ����responseTime���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link TimeMetric }
     *     
     */
    public void setResponseTime(TimeMetric value) {
        this.responseTime = value;
    }

    /**
     * ��ȡtolerance���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link DelayTolerance }
     *     
     */
    public DelayTolerance getTolerance() {
        return tolerance;
    }

    /**
     * ����tolerance���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link DelayTolerance }
     *     
     */
    public void setTolerance(DelayTolerance value) {
        this.tolerance = value;
    }

    /**
     * ��ȡapplicationId���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApplicationId() {
        return applicationId;
    }

    /**
     * ����applicationId���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplicationId(String value) {
        this.applicationId = value;
    }

    /**
     * ��ȡserviceType���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link ServiceType }
     *     
     */
    public ServiceType getServiceType() {
        return serviceType;
    }

    /**
     * ����serviceType���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceType }
     *     
     */
    public void setServiceType(ServiceType value) {
        this.serviceType = value;
    }

    /**
     * ��ȡcrs���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link CoordinateReferenceSystem }
     *     
     */
    public CoordinateReferenceSystem getCrs() {
        return crs;
    }

    /**
     * ����crs���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link CoordinateReferenceSystem }
     *     
     */
    public void setCrs(CoordinateReferenceSystem value) {
        this.crs = value;
    }

    /**
     * ��ȡlocType���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link LocType }
     *     
     */
    public LocType getLocType() {
        return locType;
    }

    /**
     * ����locType���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link LocType }
     *     
     */
    public void setLocType(LocType value) {
        this.locType = value;
    }

    /**
     * ��ȡprio���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Priority }
     *     
     */
    public Priority getPrio() {
        return prio;
    }

    /**
     * ����prio���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Priority }
     *     
     */
    public void setPrio(Priority value) {
        this.prio = value;
    }

}
