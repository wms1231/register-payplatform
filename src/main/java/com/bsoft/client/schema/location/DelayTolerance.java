
package com.bsoft.client.schema.location;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>DelayTolerance�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * <p>
 * <pre>
 * &lt;simpleType name="DelayTolerance"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="NoDelay"/&gt;
 *     &lt;enumeration value="LowDelay"/&gt;
 *     &lt;enumeration value="DelayTolerant"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "DelayTolerance")
@XmlEnum
public enum DelayTolerance {

    @XmlEnumValue("NoDelay")
    NO_DELAY("NoDelay"),
    @XmlEnumValue("LowDelay")
    LOW_DELAY("LowDelay"),
    @XmlEnumValue("DelayTolerant")
    DELAY_TOLERANT("DelayTolerant");
    private final String value;

    DelayTolerance(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DelayTolerance fromValue(String v) {
        for (DelayTolerance c: DelayTolerance.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
