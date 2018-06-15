package com.didispace.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.didispace.Chapter1Application;
import com.didispace.dao.ActivityLogRepository;
import com.didispace.emus.LogTypeEnum;
import com.didispace.entity.ActivityLog;
import com.didispace.entity.FinanceInfo;
import com.didispace.entity.User;
import com.didispace.model.LoginParam;
import com.didispace.service.FinanceInfoService;
import com.didispace.util.DataDev;
import com.didispace.util.SmsUtil;
import com.google.gson.JsonObject;

@Component
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Chapter1Application.class)
@Controller
@RequestMapping("/finance")
public class FinanceController {
	private String userPhone;
	private String code;
	@Autowired
	private FinanceInfoService financeInfoService;

	@RequestMapping("/index")
	public String index2(ModelMap map) {
		return "index";
	}
	
	@RequestMapping("/getsms")
	@ResponseBody
	public JSONObject getsms(LoginParam loginParam, HttpServletRequest request) {
		this.userPhone = loginParam.getName();
		String code = DataDev.createpwd(3);
		SmsUtil.sendWithdrawNotice(this.userPhone, code);
		this.code = code;
		HttpSession session = request.getSession();
		session.setAttribute("code", this.code);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code", "201");
		jsonObject.put("message", "发送成功");
		return jsonObject;
	}
	
	@RequestMapping("/login")
	@ResponseBody
	public JSONObject login(LoginParam loginParam, HttpServletRequest request) {
		if(this.userPhone!=null&&this.code.equals(loginParam.getPwd())) {
			HttpSession session = request.getSession();
			session.setAttribute("userPhone", this.userPhone);
		}
		return financeInfoService.thisMonth();
	}
	
	@RequestMapping("/thisMonth")
	@ResponseBody
	public JSONObject thisMonth() {
		return financeInfoService.thisMonth();
	}
	
	@RequestMapping("/nextMonth")
	@ResponseBody
	public JSONObject nextMonth() {
		return financeInfoService.nextMonth();
	}
	
	@RequestMapping("/findAll")
	@ResponseBody
	public JSONObject findAll() {
		return financeInfoService.findAll();
	}
	
	@RequestMapping("/addOne")
	@ResponseBody
	public JSONObject addOne(FinanceInfo financeInfo) {
		return financeInfoService.addOne(financeInfo);
	}
}