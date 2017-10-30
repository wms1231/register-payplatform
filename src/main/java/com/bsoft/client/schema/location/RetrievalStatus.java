
package com.bsoft.client.schema.location;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>RetrievalStatus�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * <p>
 * <pre>
 * &lt;simpleType name="RetrievalStatus"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Retrieved"/&gt;
 *     &lt;enumeration value="NotRetrieved"/&gt;
 *     &lt;enumeration value="Error"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "RetrievalStatus")
@XmlEnum
public enum RetrievalStatus {

    @XmlEnumValue("Retrieved")
    RETRIEVED("Retrieved"),
    @XmlEnumValue("NotRetrieved")
    NOT_RETRIEVED("NotRetrieved"),
    @XmlEnumValue("Error")
    ERROR("Error");
    private final String value;

    RetrievalStatus(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static RetrievalStatus fromValue(String v) {
        for (RetrievalStatus c: RetrievalStatus.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
