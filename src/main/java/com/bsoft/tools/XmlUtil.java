package com.bsoft.tools;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;

/**
 * xml格式化转换工具 注意，对于<Person id="1" name="wms">安徽省<Person>
 * 
 * @author asus
 *
 */
public class XmlUtil {
	//private static XStream xs = new XStream(new StaxDriver());// 会转成一行
	private static XStream xs = new XStream(new DomDriver(null,new XmlFriendlyNameCoder("_-", "_")));

	/**
	 * 将对象转为xml字符串，如果传入对象为空直接返回空字符串
	 * 
	 * @param obj
	 * @return
	 */
	public static String objToXml(Object obj) {
		if (obj == null) {
			return "";
		}

		if (xs != null) {
			return xs.toXML(obj);
		}

		return "";

	}

	/**
	 * 为根节点创建别名
	 * 
	 * @param obj
	 */
	public static void aliasRootField(Class clazz, String aliasName) {
		if (xs != null && !isEmpty(aliasName)) {
			xs.alias(aliasName, clazz);
		}
	}

	/**
	 * 将xml转化为对象，如果入参为null或者空都返回null
	 * 
	 * @return
	 */
	public static <T> T xmlToObj(Class<T> clazz, String xml, String rootNode) {
		T t = null;
		try {
			if (clazz == null) {
				return t;
			}

			if (isEmpty(xml)) {
				return t;
			}

			if (isEmpty(rootNode)) {
				return t;
			}

			if (xs != null) {
				xs.alias(rootNode, clazz);
				t = (T) xs.fromXML(xml);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return t;
	}

	/**
	 * 判断xml对象是否为空
	 * 
	 * @param xml
	 * @return
	 */
	public static boolean isEmpty(String xml) {
		return xml == null || xml.trim().length() == 0;
	}

	/**
	 * 为字段名创建别名
	 * 
	 * @param alias
	 * @param clazz
	 * @param fieldName
	 */
	public static void aliasForField(String alias, Class clazz, String fieldName) {
		if (xs != null) {
			xs.aliasField(alias, clazz, fieldName);
		}

	}

	/**
	 * 为字段名创建别名，并为字段名创建别名
	 * 
	 * @param clazz
	 * @param attributeFieldName
	 */
	public static void useAttributeWithAlias(String attributeFieldAlias, Class clazz, String attributeFieldName) {
		if (xs != null) {
			xs.useAttributeFor(clazz, attributeFieldName);
			xs.aliasAttribute(clazz, attributeFieldName, attributeFieldAlias);
		}
	}

}
