package com.bsoft.support.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bsoft.support.Pager;
import com.bsoft.support.service.ICommonService;

/**
 * Created by wjtc8 on 2016/8/16.
 */
@Controller
@RequestMapping("/common")
public class CommonController {

    @Autowired
    private ICommonService commonService;

    @RequestMapping("/select")
    public List<Map<String, Object>> select(HttpServletRequest request,
                                            @RequestParam(value = "sqlKey", required = true) String sqlKey,
                                            @RequestParam(value = "dataSource", required = false) String dataSource,
                                            @RequestParam(value = "orders", required = false) String[] orders) {
        Map<String, Object> param = getStringObjectMap(request);
        return commonService.selectList(sqlKey, dataSource, param, orders);
    }

    @RequestMapping("/selectOne")
    public Map<String, Object> selectOne(HttpServletRequest request,
                                         @RequestParam(value = "sqlKey", required = true) String sqlKey,
                                         @RequestParam(value = "dataSource", required = false) String dataSource) {
        Map<String, Object> param = getStringObjectMap(request);
        return commonService.selectOne(sqlKey, dataSource, param);
    }

    @RequestMapping("/selectByPage")
    public Map<String, Object> findRecordByPage(HttpServletRequest request,
                                                @RequestParam(value = "sqlKey", required = true) String sqlKey,
                                                @RequestParam(value = "dataSource", required = false) String dataSource,
                                                @RequestParam(value = "pageNo", required = false) int pageNo,
                                                @RequestParam(value = "pageSize", required = true) int pageSize,
                                                @RequestParam(value = "orders", required = false) String[] orders) {
        Map<String, Object> param = getStringObjectMap(request);
        pageNo = pageNo == 0 ? 1 : pageNo;
        Map<String, Object> data = new HashMap<>();
        //获得返回数据以及封装
        Pager pager = commonService.selectListByPage(sqlKey, dataSource, param, pageNo, pageSize, orders);
        data.put("rows", pager.getRowCount());
        data.put("total", pager.getResult());
        return data;
    }


    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public int insert(HttpServletRequest request,
                      @RequestBody Map<String, Object> param) {
        String sqlKey = param.get("sqlKey") == null ? null : param.get("sqlKey").toString();
        String dataSource = param.get("dataSource") == null ? null : param.get("dataSource").toString();
        return commonService.insert(sqlKey, dataSource, param);
    }

    @RequestMapping(value="/update", method = RequestMethod.POST)
    public int update(HttpServletRequest request,
                      @RequestBody Map<String, Object> param) {
        String sqlKey = param.get("sqlKey") == null ? null : param.get("sqlKey").toString();
        String dataSource = param.get("dataSource") == null ? null : param.get("dataSource").toString();
        return commonService.update(sqlKey, dataSource, param);
    }

    private Map<String, Object> getStringObjectMap(HttpServletRequest request) {
        Map<String, String[]> reqParam = request.getParameterMap();
        Set<String> keySet = reqParam.keySet();
        Map<String, Object> param = new HashMap<>();
        for (String key : keySet) {
            String[] values = reqParam.get(key);
            if (values.length > 1) {
                param.put(key, values);
            } else {
                param.put(key, values[0]);
            }
        }
        return param;
    }
}
