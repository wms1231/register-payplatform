
package com.bsoft.client.schema.ap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="alarmId" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="orgseverity" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="orgtype" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="probablecause" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="eventTime" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="ackTime" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="clearTime" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="activestatus" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="alarmtitle" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="alarmText" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
    "alarmId",
    "orgseverity",
    "orgtype",
    "probablecause",
    "eventTime",
    "ackTime",
    "clearTime",
    "activestatus",
    "alarmtitle",
    "alarmText"
})
@XmlRootElement(name = "AlarmReq")
public class AlarmReq {

    @XmlElement(required = true)
    protected String alarmId;
    protected int orgseverity;
    protected int orgtype;
    @XmlElement(required = true)
    protected String probablecause;
    @XmlElement(required = true)
    protected String eventTime;
    @XmlElement(required = true)
    protected String ackTime;
    @XmlElement(required = true)
    protected String clearTime;
    protected int activestatus;
    @XmlElement(required = true)
    protected String alarmtitle;
    @XmlElement(required = true)
    protected String alarmText;

    /**
     * ��ȡalarmId���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlarmId() {
        return alarmId;
    }

    /**
     * ����alarmId���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlarmId(String value) {
        this.alarmId = value;
    }

    /**
     * ��ȡorgseverity���Ե�ֵ��
     * 
     */
    public int getOrgseverity() {
        return orgseverity;
    }

    /**
     * ����orgseverity���Ե�ֵ��
     * 
     */
    public void setOrgseverity(int value) {
        this.orgseverity = value;
    }

    /**
     * ��ȡorgtype���Ե�ֵ��
     * 
     */
    public int getOrgtype() {
        return orgtype;
    }

    /**
     * ����orgtype���Ե�ֵ��
     * 
     */
    public void setOrgtype(int value) {
        this.orgtype = value;
    }

    /**
     * ��ȡprobablecause���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProbablecause() {
        return probablecause;
    }

    /**
     * ����probablecause���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProbablecause(String value) {
        this.probablecause = value;
    }

    /**
     * ��ȡeventTime���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEventTime() {
        return eventTime;
    }

    /**
     * ����eventTime���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEventTime(String value) {
        this.eventTime = value;
    }

    /**
     * ��ȡackTime���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAckTime() {
        return ackTime;
    }

    /**
     * ����ackTime���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAckTime(String value) {
        this.ackTime = value;
    }

    /**
     * ��ȡclearTime���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClearTime() {
        return clearTime;
    }

    /**
     * ����clearTime���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClearTime(String value) {
        this.clearTime = value;
    }

    /**
     * ��ȡactivestatus���Ե�ֵ��
     * 
     */
    public int getActivestatus() {
        return activestatus;
    }

    /**
     * ����activestatus���Ե�ֵ��
     * 
     */
    public void setActivestatus(int value) {
        this.activestatus = value;
    }

    /**
     * ��ȡalarmtitle���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlarmtitle() {
        return alarmtitle;
    }

    /**
     * ����alarmtitle���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlarmtitle(String value) {
        this.alarmtitle = value;
    }

    /**
     * ��ȡalarmText���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlarmText() {
        return alarmText;
    }

    /**
     * ����alarmText���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlarmText(String value) {
        this.alarmText = value;
    }

}
