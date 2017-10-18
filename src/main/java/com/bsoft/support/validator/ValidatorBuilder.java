package com.bsoft.support.validator;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.bsoft.support.utils.ExceptionUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import com.bsoft.support.validator.impl.DateValidator;
import com.bsoft.support.validator.impl.IdCardValidator;
import com.bsoft.support.validator.impl.NumberValidator;
import com.bsoft.support.validator.impl.StringValidator;

/**
 * validator生成器
 *
 * @author cuiweizheng
 * @version 1.0
 * @date 2017年1月18日下午3:07:15
 */
public class ValidatorBuilder {

    private Validator validator;

    /**
     * 注册以实现子类
     *
     * @param
     * @return
     */
    public Validator build(String validateName) {
        Document doc = null;
        try {
            doc = new SAXReader().read(new File(
                    ValidatorBuilder.class.getResource("/").getPath() +
                            "validateXML/" + validateName + ".xml"));
        } catch (DocumentException e) {
            throw new RuntimeException("无法读取验证xml：" + validateName, e);
        }
        // 获得标签map下所有子标签
        List<Node> list = doc.selectNodes("/map/*");
        for (Node node : list) {
            Validator v = null;
            if (node.getName().equals("string")) {
                v = new StringValidator();
            }
            if (node.getName().equals("number")) {
                v = new NumberValidator();
            }
            if (node.getName().equals("date")) {
                v = new DateValidator();
            }
            if (node.getName().equals("idcard")) {
                v = new IdCardValidator();
            }
            v.parse(node);
            register(v);
        }
        return validator;
    }

    private void register(Validator validator) {
        // 初始化第一個驗證器
        if (this.validator == null) {
            this.validator = validator;
        } else {
            this.validator.setValidator(validator);
        }
    }
}
