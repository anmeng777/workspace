package com.didispace.web;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.didispace.Chapter1Application;
import com.didispace.dao.NiceHashPaymentRepository;
import com.didispace.emus.LogTypeEnum;
import com.didispace.entity.NiceHashPayment;
import com.didispace.service.ActivityLogService;
import com.didispace.service.MailService;
import com.didispace.util.HttpRequest;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Component
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Chapter1Application.class)
public class WaKuangController {
	@Autowired
	private MailService mailRecordService;
	@Autowired
	private ActivityLogService activityLogService;
	@Autowired
	private NiceHashPaymentRepository niceHashPaymentRepository;

	@SuppressWarnings("static-access")
	@Scheduled(cron = "7 50 * * * ?") // cron接受cron表达式，根据cron表达式确定定时规则
	public void checkinCron() throws Exception {
		String regmsg = HttpRequest.sendGet("https://api.nicehash.com/api",
				"method=stats.provider.workers&addr=33RSJ1xxkiBEvcb1Gy4Fov3QVjaqtn5Rsw");
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("anmeng@live.cn");
		message.setTo("404570978@qq.com");
		message.setSubject("台式机矿机有问题");

		JsonArray array = new JsonParser().parse(regmsg).getAsJsonObject().get("result").getAsJsonObject()
				.get("workers").getAsJsonArray();
		double speed = 0.0d;
		for (int i = 0; i < array.size(); i++) {
			if (array.get(i).getAsJsonArray().get(1).getAsJsonObject().get("a") != null) {
				speed += array.get(i).getAsJsonArray().get(1).getAsJsonObject().get("a").getAsDouble();
			}
		}
		Thread.currentThread().sleep(1000 * 60);// 毫秒
		regmsg = HttpRequest.sendGet("https://api.nicehash.com/api",
				"method=stats.provider.workers&addr=33RSJ1xxkiBEvcb1Gy4Fov3QVjaqtn5Rsw");
		array = new JsonParser().parse(regmsg).getAsJsonObject().get("result").getAsJsonObject().get("workers")
				.getAsJsonArray();
		for (int i = 0; i < array.size(); i++) {
			if (array.get(i).getAsJsonArray().get(1).getAsJsonObject().get("a") != null) {
				speed += array.get(i).getAsJsonArray().get(1).getAsJsonObject().get("a").getAsDouble();
			}
		}
		if (speed == 0) {
			message.setText("算力为零");
			mailRecordService.mailLog(message, "我");
		}
		activityLogService.activityLog(LogTypeEnum.wakuangOk);
	}

	@Test
	@Scheduled(cron = "7 53 1 * * ?") // cron接受cron表达式，根据cron表达式确定定时规则
	public void finance() throws Exception {

		String financeInfo = HttpRequest.sendGet("https://api.nicehash.com/api",
				"method=stats.provider.payments&addr=33RSJ1xxkiBEvcb1Gy4Fov3QVjaqtn5Rsw");
		JsonObject payment = new JsonParser().parse(financeInfo).getAsJsonObject().get("result").getAsJsonObject()
				.get("payments").getAsJsonArray().get(0).getAsJsonObject();
		NiceHashPayment oldnice = niceHashPaymentRepository.findOrderByIdDescLimit1();
		if (!oldnice.getTime().equals(payment.get("time").getAsString())) {
//			SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
//		    String localDateTime = format.format(paymentss.get("time").getAsString());  

			NiceHashPayment niceHashPayment = new NiceHashPayment();
			niceHashPayment.setAmount(payment.get("amount").getAsString());
			niceHashPayment.setFee(payment.get("fee").getAsString());
			niceHashPayment.setTime(payment.get("time").getAsString());
//			niceHashPayment.setLocalTime(localDateTime);
			niceHashPayment.setType(payment.get("type").getAsShort());
			niceHashPaymentRepository.save(niceHashPayment);
		}
	}
}