
package com.bsoft.client.schema.ap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
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
 *         &lt;element name="APid" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="APStatus" type="{http://www.csapi.org/schema/ap}APStatusType"/&gt;
 *         &lt;element name="APPid" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
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
    "aPid",
    "apStatus",
    "apPid"
})
@XmlRootElement(name = "APStatusRepReq")
public class APStatusRepReq {

    @XmlElement(name = "APid", required = true)
    protected String aPid;
    @XmlElement(name = "APStatus", required = true)
    @XmlSchemaType(name = "string")
    protected APStatusType apStatus;
    @XmlElement(name = "APPid")
    protected int apPid;

    /**
     * ��ȡaPid���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAPid() {
        return aPid;
    }

    /**
     * ����aPid���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAPid(String value) {
        this.aPid = value;
    }

    /**
     * ��ȡapStatus���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link APStatusType }
     *     
     */
    public APStatusType getAPStatus() {
        return apStatus;
    }

    /**
     * ����apStatus���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link APStatusType }
     *     
     */
    public void setAPStatus(APStatusType value) {
        this.apStatus = value;
    }

    /**
     * ��ȡapPid���Ե�ֵ��
     * 
     */
    public int getAPPid() {
        return apPid;
    }

    /**
     * ����apPid���Ե�ֵ��
     * 
     */
    public void setAPPid(int value) {
        this.apPid = value;
    }

}
