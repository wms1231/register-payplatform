package com.bsoft.register.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import com.bsoft.constant.CommonConstant;
import com.bsoft.register.service.MtcService;
import com.bsoft.support.service.ICommonService;
import com.bsoft.support.utils.MD5Util;
import com.bsoft.tools.CookieUtils;

@Service
public class MtcServiceImpl implements MtcService {

	@Autowired
	private ICommonService commonService;

	@Override
	public String changePwd(String number, String inputOldPwd, String inputNewPwd) {
		JSONObject json = new JSONObject();
		json.put("code", 0);
		json.put("msg", "success");
		// 有效数值检测
		Map<String, Object> param = new HashMap<String, Object>();
		// 加密
		String searcPassword = MD5Util.md5Encode(number + inputNewPwd);
		String userOldpwd = MD5Util.md5Encode(number + inputOldPwd);

		param.put("searcPassword", searcPassword);
		param.put("userOldpwd", userOldpwd);
		param.put("number", number);

		// 比较密码
		Map<String, Object> map = commonService.selectOne("user.queryUserByNumber", null, param);
		String oldPassword = (String) map.get("YHMM");
		if (oldPassword.equals(userOldpwd) & oldPassword.equals(searcPassword) != true) {
			if (commonService.update("user.changePwd", null, param) != 1) {// 更新失败
				json.put("code", -1);
				json.put("msg", "fail");
			}
		} else {
			json.put("code", -1);
			json.put("msg", "fail");
		}
		return json.toJSONString();

	}

	@Override
	public String login(HttpServletRequest request, HttpServletResponse response, Map<String, Object> param) {
		JSONObject json = new JSONObject();
		if (param != null && param.size() > 0) {
			String sqlKey = param.get("sqlKey") == null ? null : param.get("sqlKey").toString();
			String dataSource = param.get("dataSource") == null ? null : param.get("dataSource").toString();
			param.put("userStatus", 0);// 禁用标志 0 表示用户可用
			String userpasswd = (String) param.get("userpasswd");// 获取密码并进行md5加密
			String userName = (String) param.get("userName");
			if (StringUtils.isNotBlank(userpasswd) && StringUtils.isNotBlank(userName)) {
				String searcPassword = MD5Util.md5Encode(userName + userpasswd);
				param.put("userpasswd", searcPassword);
				// 进行数据库查询
				Map<String, Object> userMap = commonService.selectOne(sqlKey, dataSource, param);
				if (userMap != null && userMap.size() >= 1) {// 找到了
					// 进行cookie处理,设置过期时间1h,仅仅测试
					String tempStr = userName + "#" + searcPassword;
					CookieUtils.setCookie(request, response, "userAccount", tempStr, 0, false);
					// 登陆成功
					json.put("code", CommonConstant.LOGIN_SUCCESS_RETURN_CODE);
					json.put("msg", CommonConstant.LOGIN_SUCCESS_RETURN_MESSAGE);
					request.getSession().setAttribute("name", userName);
				} else {// 没找到
					json.put("code", CommonConstant.LOGIN_FAIL_RETURN_CODE);
					json.put("msg", CommonConstant.LOGIN_FAIL_RETURN_MESSAGE);
				}
			} else {
				json.put("code", CommonConstant.LOGIN_FAIL_RETURN_CODE);
				json.put("msg", CommonConstant.LOGIN_FAIL_RETURN_MESSAGE);
			}
		} else {// 登录失败
			json.put("code", CommonConstant.LOGIN_FAIL_RETURN_CODE);
			json.put("msg", CommonConstant.LOGIN_FAIL_RETURN_MESSAGE);
		}
		// 返回结果
		return json.toJSONString();
	}

}
