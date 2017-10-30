
package com.bsoft.client.schema.common.v2_0;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>MessageNotificationType complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="MessageNotificationType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CMAbility" type="{http://www.csapi.org/schema/common/v2_0}CMAbility"/&gt;
 *         &lt;element name="WSURI" type="{http://www.w3.org/2001/XMLSchema}anyURI" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MessageNotificationType", propOrder = {
    "cmAbility",
    "wsuri"
})
public class MessageNotificationType {

    @XmlElement(name = "CMAbility", required = true)
    @XmlSchemaType(name = "string")
    protected CMAbility cmAbility;
    @XmlElement(name = "WSURI")
    @XmlSchemaType(name = "anyURI")
    protected List<String> wsuri;

    /**
     * ��ȡcmAbility���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link CMAbility }
     *     
     */
    public CMAbility getCMAbility() {
        return cmAbility;
    }

    /**
     * ����cmAbility���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link CMAbility }
     *     
     */
    public void setCMAbility(CMAbility value) {
        this.cmAbility = value;
    }

    /**
     * Gets the value of the wsuri property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wsuri property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWSURI().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getWSURI() {
        if (wsuri == null) {
            wsuri = new ArrayList<String>();
        }
        return this.wsuri;
    }

}
