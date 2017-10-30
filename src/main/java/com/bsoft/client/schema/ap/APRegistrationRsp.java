
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
 *         &lt;element name="RegResult" type="{http://www.csapi.org/schema/ap}APRegResult"/&gt;
 *         &lt;element name="NextInterval" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
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
    "regResult",
    "nextInterval"
})
@XmlRootElement(name = "APRegistrationRsp")
public class APRegistrationRsp {

    @XmlElement(name = "RegResult", required = true, nillable = true)
    @XmlSchemaType(name = "string")
    protected APRegResult regResult;
    @XmlElement(name = "NextInterval")
    protected int nextInterval;

    /**
     * ��ȡregResult���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link APRegResult }
     *     
     */
    public APRegResult getRegResult() {
        return regResult;
    }

    /**
     * ����regResult���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link APRegResult }
     *     
     */
    public void setRegResult(APRegResult value) {
        this.regResult = value;
    }

    /**
     * ��ȡnextInterval���Ե�ֵ��
     * 
     */
    public int getNextInterval() {
        return nextInterval;
    }

    /**
     * ����nextInterval���Ե�ֵ��
     * 
     */
    public void setNextInterval(int value) {
        this.nextInterval = value;
    }

}
