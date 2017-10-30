
package com.bsoft.client.schema.location;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>LocType�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * <p>
 * <pre>
 * &lt;simpleType name="LocType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="CURRENT"/&gt;
 *     &lt;enumeration value="LAST"/&gt;
 *     &lt;enumeration value="CURRENT_OR_LAST"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "LocType")
@XmlEnum
public enum LocType {

    CURRENT,
    LAST,
    CURRENT_OR_LAST;

    public String value() {
        return name();
    }

    public static LocType fromValue(String v) {
        return valueOf(v);
    }

}
