
package com.bsoft.client.schema.sms;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>SendMethodType�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * <p>
 * <pre>
 * &lt;simpleType name="SendMethodType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Normal"/&gt;
 *     &lt;enumeration value="Instant"/&gt;
 *     &lt;enumeration value="Long"/&gt;
 *     &lt;enumeration value="Structured"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "SendMethodType")
@XmlEnum
public enum SendMethodType {

    @XmlEnumValue("Normal")
    NORMAL("Normal"),
    @XmlEnumValue("Instant")
    INSTANT("Instant"),
    @XmlEnumValue("Long")
    LONG("Long"),
    @XmlEnumValue("Structured")
    STRUCTURED("Structured");
    private final String value;

    SendMethodType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SendMethodType fromValue(String v) {
        for (SendMethodType c: SendMethodType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
