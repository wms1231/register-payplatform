package com.bsoft.factory;

import com.bsoft.domain.HeadBean;

/**
 * 构建器接口，定义创建一个输出文件对象所需的各个部件的操作
 */
public interface XmlServiceBuilder {
	/**
	 * 构建head的公共部分
	 * 
	 * @param headBean
	 */
	public void buildRequestHeader(HeadBean headBean);

	/**
	 * 构建data私有属性部分
	 * 
	 * @param dataBean
	 */
	public void buildRequestData(Object dataBean,String serviceCode);

}