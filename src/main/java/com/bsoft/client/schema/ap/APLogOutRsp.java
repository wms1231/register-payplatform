
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
 *         &lt;element name="LogoutResult" type="{http://www.csapi.org/schema/ap}APLogoutResult"/&gt;
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
    "logoutResult"
})
@XmlRootElement(name = "APLogOutRsp")
public class APLogOutRsp {

    @XmlElement(name = "LogoutResult", required = true, nillable = true)
    @XmlSchemaType(name = "string")
    protected APLogoutResult logoutResult;

    /**
     * ��ȡlogoutResult���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link APLogoutResult }
     *     
     */
    public APLogoutResult getLogoutResult() {
        return logoutResult;
    }

    /**
     * ����logoutResult���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link APLogoutResult }
     *     
     */
    public void setLogoutResult(APLogoutResult value) {
        this.logoutResult = value;
    }

}
