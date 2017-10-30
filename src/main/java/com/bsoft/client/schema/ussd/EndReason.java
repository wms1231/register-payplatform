
package com.bsoft.client.schema.ussd;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>EndReason�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * <p>
 * <pre>
 * &lt;simpleType name="EndReason"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="UserEnd"/&gt;
 *     &lt;enumeration value="Busy"/&gt;
 *     &lt;enumeration value="UserAbsent"/&gt;
 *     &lt;enumeration value="IllegalEquipment"/&gt;
 *     &lt;enumeration value="SystemError"/&gt;
 *     &lt;enumeration value="TimeOut"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "EndReason")
@XmlEnum
public enum EndReason {

    @XmlEnumValue("UserEnd")
    USER_END("UserEnd"),
    @XmlEnumValue("Busy")
    BUSY("Busy"),
    @XmlEnumValue("UserAbsent")
    USER_ABSENT("UserAbsent"),
    @XmlEnumValue("IllegalEquipment")
    ILLEGAL_EQUIPMENT("IllegalEquipment"),
    @XmlEnumValue("SystemError")
    SYSTEM_ERROR("SystemError"),
    @XmlEnumValue("TimeOut")
    TIME_OUT("TimeOut");
    private final String value;

    EndReason(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EndReason fromValue(String v) {
        for (EndReason c: EndReason.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
