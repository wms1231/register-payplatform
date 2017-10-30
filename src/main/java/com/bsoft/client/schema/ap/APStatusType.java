
package com.bsoft.client.schema.ap;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>APStatusType�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * <p>
 * <pre>
 * &lt;simpleType name="APStatusType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Normal"/&gt;
 *     &lt;enumeration value="OutofActiveTime"/&gt;
 *     &lt;enumeration value="NeedRegistration"/&gt;
 *     &lt;enumeration value="OutofService"/&gt;
 *     &lt;enumeration value="Paused"/&gt;
 *     &lt;enumeration value="Closed"/&gt;
 *     &lt;enumeration value="WaitingforConfirm"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "APStatusType")
@XmlEnum
public enum APStatusType {

    @XmlEnumValue("Normal")
    NORMAL("Normal"),
    @XmlEnumValue("OutofActiveTime")
    OUTOF_ACTIVE_TIME("OutofActiveTime"),
    @XmlEnumValue("NeedRegistration")
    NEED_REGISTRATION("NeedRegistration"),
    @XmlEnumValue("OutofService")
    OUTOF_SERVICE("OutofService"),
    @XmlEnumValue("Paused")
    PAUSED("Paused"),
    @XmlEnumValue("Closed")
    CLOSED("Closed"),
    @XmlEnumValue("WaitingforConfirm")
    WAITINGFOR_CONFIRM("WaitingforConfirm");
    private final String value;

    APStatusType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static APStatusType fromValue(String v) {
        for (APStatusType c: APStatusType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
