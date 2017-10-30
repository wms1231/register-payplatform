
package com.bsoft.client.schema.ussd;

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
 *         &lt;element name="ussdIdentifier" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="ussdMessage" type="{http://www.csapi.org/schema/ussd}UssdArray"/&gt;
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
    "ussdMessage"
})
@XmlRootElement(name = "ussdContinueRequest")
public class UssdContinueRequest {

    @XmlElement(required = true)
    protected String ussdIdentifier;
    @XmlElement(required = true)
    protected UssdArray ussdMessage;

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
     * ��ȡussdMessage���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link UssdArray }
     *     
     */
    public UssdArray getUssdMessage() {
        return ussdMessage;
    }

    /**
     * ����ussdMessage���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link UssdArray }
     *     
     */
    public void setUssdMessage(UssdArray value) {
        this.ussdMessage = value;
    }

}
