
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
 *         &lt;element name="returnMessage" type="{http://www.csapi.org/schema/ussd}UssdArray"/&gt;
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
    "returnMessage"
})
@XmlRootElement(name = "ussdContinueResponse")
public class UssdContinueResponse {

    @XmlElement(required = true)
    protected UssdArray returnMessage;

    /**
     * ��ȡreturnMessage���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link UssdArray }
     *     
     */
    public UssdArray getReturnMessage() {
        return returnMessage;
    }

    /**
     * ����returnMessage���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link UssdArray }
     *     
     */
    public void setReturnMessage(UssdArray value) {
        this.returnMessage = value;
    }

}
