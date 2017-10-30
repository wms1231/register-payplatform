
package com.bsoft.client.schema.ap;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element name="NextCommand" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="NextInterval" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="ApSvcAuthType" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="ApSvcPerfCmdType" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
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
    "nextCommand",
    "nextInterval",
    "apSvcAuthType",
    "apSvcPerfCmdType"
})
@XmlRootElement(name = "APStatusRepRsp")
public class APStatusRepRsp {

    @XmlElement(name = "NextCommand", required = true, nillable = true)
    protected String nextCommand;
    @XmlElement(name = "NextInterval")
    protected int nextInterval;
    @XmlElement(name = "ApSvcAuthType", nillable = true)
    protected List<String> apSvcAuthType;
    @XmlElement(name = "ApSvcPerfCmdType", nillable = true)
    protected List<String> apSvcPerfCmdType;

    /**
     * ��ȡnextCommand���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNextCommand() {
        return nextCommand;
    }

    /**
     * ����nextCommand���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNextCommand(String value) {
        this.nextCommand = value;
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

    /**
     * Gets the value of the apSvcAuthType property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the apSvcAuthType property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getApSvcAuthType().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getApSvcAuthType() {
        if (apSvcAuthType == null) {
            apSvcAuthType = new ArrayList<String>();
        }
        return this.apSvcAuthType;
    }

    /**
     * Gets the value of the apSvcPerfCmdType property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the apSvcPerfCmdType property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getApSvcPerfCmdType().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getApSvcPerfCmdType() {
        if (apSvcPerfCmdType == null) {
            apSvcPerfCmdType = new ArrayList<String>();
        }
        return this.apSvcPerfCmdType;
    }

}
