
package com.bsoft.client.schema.common.v2_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>TimeMetric complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="TimeMetric"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Metric" type="{http://www.csapi.org/schema/common/v2_0}TimeMetricsValues"/&gt;
 *         &lt;element name="Units" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TimeMetric", propOrder = {
    "metric",
    "units"
})
public class TimeMetric {

    @XmlElement(name = "Metric", required = true, nillable = true)
    @XmlSchemaType(name = "string")
    protected TimeMetricsValues metric;
    @XmlElement(name = "Units")
    protected int units;

    /**
     * ��ȡmetric���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link TimeMetricsValues }
     *     
     */
    public TimeMetricsValues getMetric() {
        return metric;
    }

    /**
     * ����metric���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link TimeMetricsValues }
     *     
     */
    public void setMetric(TimeMetricsValues value) {
        this.metric = value;
    }

    /**
     * ��ȡunits���Ե�ֵ��
     * 
     */
    public int getUnits() {
        return units;
    }

    /**
     * ����units���Ե�ֵ��
     * 
     */
    public void setUnits(int value) {
        this.units = value;
    }

}
