
package com.bsoft.client.schema.sms;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>MessageFormat�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * <p>
 * <pre>
 * &lt;simpleType name="MessageFormat"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="ASCII"/&gt;
 *     &lt;enumeration value="UCS2"/&gt;
 *     &lt;enumeration value="GB18030"/&gt;
 *     &lt;enumeration value="GB2312"/&gt;
 *     &lt;enumeration value="Binary"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "MessageFormat")
@XmlEnum
public enum MessageFormat {

    ASCII("ASCII"),
    @XmlEnumValue("UCS2")
    UCS_2("UCS2"),
    @XmlEnumValue("GB18030")
    GB_18030("GB18030"),
    @XmlEnumValue("GB2312")
    GB_2312("GB2312"),
    @XmlEnumValue("Binary")
    BINARY("Binary");
    private final String value;

    MessageFormat(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static MessageFormat fromValue(String v) {
        for (MessageFormat c: MessageFormat.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
