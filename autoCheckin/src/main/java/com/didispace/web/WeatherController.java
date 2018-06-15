package com.didispace.web;

import java.util.List;
import java.util.regex.Pattern;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.didispace.Chapter1Application;
import com.didispace.dao.MailRecieverRepository;
import com.didispace.emus.LogTypeEnum;
import com.didispace.entity.MailReciever;
import com.didispace.service.ActivityLogService;
import com.didispace.service.MailService;
import com.didispace.util.HttpRequest;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Component
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Chapter1Application.class)
@RequestMapping("/weather")
public class WeatherController {
	@Autowired
	private MailService mailRecordService;
	@Autowired
	private MailRecieverRepository mailRecieverRepository;
	@Autowired
	private ActivityLogService activityLogService;
	/**
	 * 正则表达式：验证邮箱
	 */
	public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
	
	@RequestMapping("/register")
    public String register() throws Exception {
		return "wetherRegister";
    }
	
	@RequestMapping("/registerAjax")
	@ResponseBody
    public String regisregisterAjaxter(
    		@RequestParam(value = "name") String name,
    		@RequestParam(value = "mail") String mail
    		) {
		
		if(!Pattern.matches(REGEX_EMAIL, mail)) {
			return "邮箱格式不正确";
		}
		
		if(mailRecieverRepository.findByMail(mail).isPresent()){
			return "邮箱已存在";
		}
		
		MailReciever mailReciever = new MailReciever();
		mailReciever.setEnable(true);
		mailReciever.setMail(mail);
		mailReciever.setName(name);
		mailReciever.setWeather(true);
		mailReciever.setXianhao(false);
		mailReciever.setYoujia(false);
		mailRecieverRepository.save(mailReciever);
		
		return "成功，三天内下雨会提醒";
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Test
    @Scheduled(cron = "0 11 0 * * ?")  //cron接受cron表达式，根据cron表达式确定定时规则
    public void checkinCron() throws Exception{
    	String regmsg = HttpRequest.sendGet(
				"https://api.seniverse.com/v3/weather/daily.json", 
				"key=deiwilnfqg9j9zy1&location=shijiazhuang&language=zh-Hans&unit=c&start=0&days=3");
    	SimpleMailMessage message = new SimpleMailMessage();
    	message.setFrom("anmeng@live.cn");
    	List<MailReciever> mailRecievers = mailRecieverRepository
    	    	.findByIsEnableTrueAndWeather(true);
    	    	
		String[] mailer = new String[mailRecievers.size()];
		int i = 0;
		for(MailReciever mReciever : mailRecievers) {
			mailer[i] = mReciever.getMail();
			i++;
		}
    	message.setTo(mailer);
    	JsonArray array = 
    			new JsonParser().parse(regmsg)
    			.getAsJsonObject().get("results")
    			.getAsJsonArray().get(0).getAsJsonObject().get("daily")
    			.getAsJsonArray();
    	boolean isSend = false;
    	for(JsonElement object : array) {
    		JsonObject jsonObject = object.getAsJsonObject();
    		if((jsonObject.get("code_day").getAsInt()>9
    				&&jsonObject.get("code_day").getAsInt()<37
    				&&jsonObject.get("code_day").getAsInt()!=32)
    				||(jsonObject.get("code_night").getAsInt()>9
    						&&jsonObject.get("code_night").getAsInt()<37
    						&&jsonObject.get("code_night").getAsInt()!=32)
    				) {
    			isSend = true;
    		}
    	}
    	if(isSend) {
    		StringBuffer stringBuffer = new StringBuffer();
    		for(JsonElement object : array) {
    			JsonObject jsonObject = object.getAsJsonObject();
    			stringBuffer.append(jsonObject.get("date").getAsString()).append("\t\t")
    			.append(jsonObject.get("text_day").getAsString()+" ~ "+jsonObject.get("text_night").getAsString()+"  ")
    			.append(jsonObject.get("high").getAsString()+" ~ "+jsonObject.get("low").getAsString()+"\n");
    		}
    		
    		message.setSubject("天气提醒");
    		message.setText(stringBuffer.toString());
    		mailRecordService.mailLog(message, mailRecievers);
    	}
    	activityLogService.activityLog(LogTypeEnum.weatherOk);
    }
}