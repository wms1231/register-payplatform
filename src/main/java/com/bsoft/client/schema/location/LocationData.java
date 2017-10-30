
package com.bsoft.client.schema.location;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import com.bsoft.client.schema.common.v2_0.ServiceError;


/**
 * <p>LocationData complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="LocationData"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Address" type="{http://www.w3.org/2001/XMLSchema}anyURI"/&gt;
 *         &lt;element name="ReportStatus" type="{http://www.csapi.org/schema/location}RetrievalStatus"/&gt;
 *         &lt;element name="CurrentLocation" type="{http://www.csapi.org/schema/location}LocationInfo"/&gt;
 *         &lt;element name="ErrorInformation" type="{http://www.csapi.org/schema/common/v2_0}ServiceError"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LocationData", propOrder = {
    "address",
    "reportStatus",
    "currentLocation",
    "errorInformation"
})
public class LocationData {

    @XmlElement(name = "Address", required = true, nillable = true)
    @XmlSchemaType(name = "anyURI")
    protected String address;
    @XmlElement(name = "ReportStatus", required = true, nillable = true)
    @XmlSchemaType(name = "string")
    protected RetrievalStatus reportStatus;
    @XmlElement(name = "CurrentLocation", required = true, nillable = true)
    protected LocationInfo currentLocation;
    @XmlElement(name = "ErrorInformation", required = true, nillable = true)
    protected ServiceError errorInformation;

    /**
     * ��ȡaddress���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddress() {
        return address;
    }

    /**
     * ����address���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddress(String value) {
        this.address = value;
    }

    /**
     * ��ȡreportStatus���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link RetrievalStatus }
     *     
     */
    public RetrievalStatus getReportStatus() {
        return reportStatus;
    }

    /**
     * ����reportStatus���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link RetrievalStatus }
     *     
     */
    public void setReportStatus(RetrievalStatus value) {
        this.reportStatus = value;
    }

    /**
     * ��ȡcurrentLocation���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link LocationInfo }
     *     
     */
    public LocationInfo getCurrentLocation() {
        return currentLocation;
    }

    /**
     * ����currentLocation���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link LocationInfo }
     *     
     */
    public void setCurrentLocation(LocationInfo value) {
        this.currentLocation = value;
    }

    /**
     * ��ȡerrorInformation���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link ServiceError }
     *     
     */
    public ServiceError getErrorInformation() {
        return errorInformation;
    }

    /**
     * ����errorInformation���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceError }
     *     
     */
    public void setErrorInformation(ServiceError value) {
        this.errorInformation = value;
    }

}
