
package com.bsoft.client.schema.location;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>LocationInfo complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="LocationInfo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Latitude" type="{http://www.w3.org/2001/XMLSchema}float"/&gt;
 *         &lt;element name="Longitude" type="{http://www.w3.org/2001/XMLSchema}float"/&gt;
 *         &lt;element name="Altitude" type="{http://www.w3.org/2001/XMLSchema}float"/&gt;
 *         &lt;element name="Accuracy" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="Timestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LocationInfo", propOrder = {
    "latitude",
    "longitude",
    "altitude",
    "accuracy",
    "timestamp"
})
public class LocationInfo {

    @XmlElement(name = "Latitude")
    protected float latitude;
    @XmlElement(name = "Longitude")
    protected float longitude;
    @XmlElement(name = "Altitude")
    protected float altitude;
    @XmlElement(name = "Accuracy")
    protected int accuracy;
    @XmlElement(name = "Timestamp", required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar timestamp;

    /**
     * ��ȡlatitude���Ե�ֵ��
     * 
     */
    public float getLatitude() {
        return latitude;
    }

    /**
     * ����latitude���Ե�ֵ��
     * 
     */
    public void setLatitude(float value) {
        this.latitude = value;
    }

    /**
     * ��ȡlongitude���Ե�ֵ��
     * 
     */
    public float getLongitude() {
        return longitude;
    }

    /**
     * ����longitude���Ե�ֵ��
     * 
     */
    public void setLongitude(float value) {
        this.longitude = value;
    }

    /**
     * ��ȡaltitude���Ե�ֵ��
     * 
     */
    public float getAltitude() {
        return altitude;
    }

    /**
     * ����altitude���Ե�ֵ��
     * 
     */
    public void setAltitude(float value) {
        this.altitude = value;
    }

    /**
     * ��ȡaccuracy���Ե�ֵ��
     * 
     */
    public int getAccuracy() {
        return accuracy;
    }

    /**
     * ����accuracy���Ե�ֵ��
     * 
     */
    public void setAccuracy(int value) {
        this.accuracy = value;
    }

    /**
     * ��ȡtimestamp���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTimestamp() {
        return timestamp;
    }

    /**
     * ����timestamp���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTimestamp(XMLGregorianCalendar value) {
        this.timestamp = value;
    }

}
