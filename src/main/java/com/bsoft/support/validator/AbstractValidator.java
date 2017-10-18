package com.bsoft.support.validator;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.dom4j.Element;
import org.dom4j.Node;

public abstract class AbstractValidator implements Validator {

	protected String name;
	protected String required;
	protected String msg;
	protected Number max;
	protected Number min;
	protected String pattern;

	protected Validator validator;

	private ValResult result = new ValResult();

	@Override
	public void setValidator(Validator validator) {
		if (this.validator != null)
			this.validator.setValidator(validator);
		else
			this.validator = validator;
	}

	@Override
	public Validator getValidator() {
		return validator;
	}

	@Override
	public ValResult doHandle(Map<String, Object> paramMap) {
		List<Error> eList = null;
		if (validator != null) {
			eList = validator.doHandle(paramMap).getErrorList();
			if (eList != null && eList.size() > 0)
				result.getErrorList().addAll(eList);
		}
		Error error = handle(paramMap);
		if (error != null)
			result.getErrorList().add(error);
		return result;
	}

	public abstract Error handle(Map<String, Object> paramMap);

	public void parse(Node node) {
		Element element = (Element) node;
		// 获得此标签所需属性
		if (StringUtils.isNotBlank(element.attributeValue("name")))
			name = element.attributeValue("name");
		if (StringUtils.isNotBlank(element.attributeValue("required")))
			required = element.attributeValue("required");
		if (StringUtils.isNotBlank(element.attributeValue("max")))
			max = NumberUtils.createNumber(element.attributeValue("max"));
		if (StringUtils.isNotBlank(element.attributeValue("min")))
			min = NumberUtils.createNumber(element.attributeValue("min"));
		if (StringUtils.isNotBlank(element.attributeValue("msg")))
			msg = element.attributeValue("msg");
		if (StringUtils.isNotBlank(element.attributeValue("pattern")))
			pattern = element.attributeValue("pattern");
	}

}
