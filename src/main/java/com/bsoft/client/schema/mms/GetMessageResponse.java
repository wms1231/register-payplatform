
package com.bsoft.client.schema.mms;

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
 *         &lt;element name="mmsMessage" type="{http://www.csapi.org/schema/mms}MmsMessage"/&gt;
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
    "mmsMessage"
})
@XmlRootElement(name = "getMessageResponse")
public class GetMessageResponse {

    @XmlElement(required = true)
    protected MmsMessage mmsMessage;

    /**
     * ��ȡmmsMessage���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link MmsMessage }
     *     
     */
    public MmsMessage getMmsMessage() {
        return mmsMessage;
    }

    /**
     * ����mmsMessage���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link MmsMessage }
     *     
     */
    public void setMmsMessage(MmsMessage value) {
        this.mmsMessage = value;
    }

}
