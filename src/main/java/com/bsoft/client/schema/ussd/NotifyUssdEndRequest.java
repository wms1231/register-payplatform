
package com.bsoft.client.schema.ussd;

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
 *         &lt;element name="ussdIdentifier" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="endReason" type="{http://www.csapi.org/schema/ussd}EndReason"/&gt;
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
    "ussdIdentifier",
    "endReason"
})
@XmlRootElement(name = "notifyUssdEndRequest")
public class NotifyUssdEndRequest {

    @XmlElement(required = true)
    protected String ussdIdentifier;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected EndReason endReason;

    /**
     * ��ȡussdIdentifier���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUssdIdentifier() {
        return ussdIdentifier;
    }

    /**
     * ����ussdIdentifier���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUssdIdentifier(String value) {
        this.ussdIdentifier = value;
    }

    /**
     * ��ȡendReason���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link EndReason }
     *     
     */
    public EndReason getEndReason() {
        return endReason;
    }

    /**
     * ����endReason���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link EndReason }
     *     
     */
    public void setEndReason(EndReason value) {
        this.endReason = value;
    }

}
