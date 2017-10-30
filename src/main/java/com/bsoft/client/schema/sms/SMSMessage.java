
package com.bsoft.client.schema.sms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>SMSMessage complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="SMSMessage"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Message" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="SmsServiceActivationNumber" type="{http://www.w3.org/2001/XMLSchema}anyURI"/&gt;
 *         &lt;element name="SenderAddress" type="{http://www.w3.org/2001/XMLSchema}anyURI"/&gt;
 *         &lt;element name="MessageFormat" type="{http://www.csapi.org/schema/sms}MessageFormat"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SMSMessage", propOrder = {
    "message",
    "smsServiceActivationNumber",
    "senderAddress",
    "messageFormat"
})
public class SMSMessage {

    @XmlElement(name = "Message", required = true, nillable = true)
    protected String message;
    @XmlElement(name = "SmsServiceActivationNumber", required = true, nillable = true)
    @XmlSchemaType(name = "anyURI")
    protected String smsServiceActivationNumber;
    @XmlElement(name = "SenderAddress", required = true, nillable = true)
    @XmlSchemaType(name = "anyURI")
    protected String senderAddress;
    @XmlElement(name = "MessageFormat", required = true, nillable = true)
    @XmlSchemaType(name = "string")
    protected MessageFormat messageFormat;

    /**
     * ��ȡmessage���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessage() {
        return message;
    }

    /**
     * ����message���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessage(String value) {
        this.message = value;
    }

    /**
     * ��ȡsmsServiceActivationNumber���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSmsServiceActivationNumber() {
        return smsServiceActivationNumber;
    }

    /**
     * ����smsServiceActivationNumber���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSmsServiceActivationNumber(String value) {
        this.smsServiceActivationNumber = value;
    }

    /**
     * ��ȡsenderAddress���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSenderAddress() {
        return senderAddress;
    }

    /**
     * ����senderAddress���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSenderAddress(String value) {
        this.senderAddress = value;
    }

    /**
     * ��ȡmessageFormat���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link MessageFormat }
     *     
     */
    public MessageFormat getMessageFormat() {
        return messageFormat;
    }

    /**
     * ����messageFormat���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link MessageFormat }
     *     
     */
    public void setMessageFormat(MessageFormat value) {
        this.messageFormat = value;
    }

}
