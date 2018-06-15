package com.didispace.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.didispace.Chapter1Application;
import com.didispace.emus.LogTypeEnum;
import com.didispace.service.ActivityLogService;
import com.didispace.service.MailService;
import com.didispace.util.HttpRequest;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Component
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Chapter1Application.class)
public class CheckinController {
	@Autowired
	private MailService mailRecordService;
	@Autowired
	private ActivityLogService activityLogService;
	
    @Scheduled(cron = "6 11 11 * * ?")  //cron接受cron表达式，根据cron表达式确定定时规则
    public void checkinCron() throws Exception{
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("anmeng@live.cn");
		message.setTo("404570978@qq.com");
		String param = "username=" + "18633815628" + "&password=" 
				+ "E10ADC3949BA59ABBE56E057F20F883E" 
				+ "&grant_type=" + "password" + "&client_id="
				+ "rongtoudai2016" + "&client_secret=" 
				+ "G9w0BkqhkiAQEFAASCBK" 
				+ "&scope=" + "write" + "";
		String regmsg = HttpRequest.sendBasicPost(
				"https://api.rongtoudai.cn:8443/HfServer/"+"oauth/token", 
				param);
		System.out.println("登陆返回： "+regmsg);
		JsonObject returnData = 
				new JsonParser().parse(regmsg).getAsJsonObject();
		String token = returnData.get("access_token").getAsString();
		String reString = HttpRequest.sendTokenGet(
				"https://api.rongtoudai.cn:8443/HfServer/"+"user/checkin",
				null, token);
		System.out.println(reString);
		returnData = 
				new JsonParser().parse(reString).getAsJsonObject();
		
		String item = returnData.get("message").getAsString();
		if(!"200".equals(returnData.get("code").getAsString())) {
			message.setSubject(item);
			message.setText(reString);
			mailRecordService.mailLog(message, "我");
		}
		activityLogService.activityLog(LogTypeEnum.checkinOk);
    }
	
    @Test
    public void index() throws Exception  {
    	throw new Exception("发生错误");
    }
}