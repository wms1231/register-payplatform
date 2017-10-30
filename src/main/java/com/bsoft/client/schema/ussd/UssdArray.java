
package com.bsoft.client.schema.ussd;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>UssdArray complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="UssdArray"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ussdMessage" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="ussdReturnRequest" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UssdArray", propOrder = {
    "ussdMessage",
    "ussdReturnRequest"
})
public class UssdArray {

    @XmlElement(required = true)
    protected String ussdMessage;
    protected boolean ussdReturnRequest;

    /**
     * ��ȡussdMessage���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUssdMessage() {
        return ussdMessage;
    }

    /**
     * ����ussdMessage���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUssdMessage(String value) {
        this.ussdMessage = value;
    }

    /**
     * ��ȡussdReturnRequest���Ե�ֵ��
     * 
     */
    public boolean isUssdReturnRequest() {
        return ussdReturnRequest;
    }

    /**
     * ����ussdReturnRequest���Ե�ֵ��
     * 
     */
    public void setUssdReturnRequest(boolean value) {
        this.ussdReturnRequest = value;
    }

}
