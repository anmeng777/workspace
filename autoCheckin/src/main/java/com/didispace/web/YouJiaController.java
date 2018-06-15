package com.didispace.web;

import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.didispace.Chapter1Application;
import com.didispace.dao.GasPriceRepository;
import com.didispace.dao.MailRecieverRepository;
import com.didispace.emus.LogTypeEnum;
import com.didispace.entity.GasPrice;
import com.didispace.entity.MailReciever;
import com.didispace.service.ActivityLogService;
import com.didispace.service.MailService;
import com.didispace.util.HttpRequest;

@Component
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Chapter1Application.class)
public class YouJiaController {
	@Autowired
	private MailRecieverRepository mailRecieverRepository;
	@Autowired
	private GasPriceRepository gasPriceRepository;
	@Autowired
	private MailService mailRecordService;
	@Autowired
	private ActivityLogService activityLogService;
	
	@Test
    @Scheduled(cron = "0 10 0 * * ?")  //cron接受cron表达式，根据cron表达式确定定时规则
    public void checkinCron() throws Exception{
    	String regmsg = HttpRequest.sendGetGBK(
				"http://youjia.chemcp.com/hebei/shijiazhuangshi.html");
    	SimpleMailMessage message = new SimpleMailMessage();
    	message.setFrom("anmeng@live.cn");
    	List<MailReciever> mailRecievers = mailRecieverRepository
    	.findByIsEnableTrueAndYoujia(true);
    	String[] mailer = new String[mailRecievers.size()];
    	int i = 0;
    	for(MailReciever mReciever : mailRecievers) {
    		mailer[i] = mReciever.getMail();
    		i++;
    	}
    	message.setTo(mailer);
    	Document doc = Jsoup.parse(regmsg);
    	Elements elements = doc.select("table[width=300] font[color=red]");
    	String ee = elements.get(1).toString()
    			.replace("<font color=\"red\">", "").replace("元/升</font>", "");
    	Double price = Double.parseDouble(elements.get(1).toString()
    			.replace("<font color=\"red\">", "").replace("元/升</font>", ""));
    	GasPrice gasPrice = new GasPrice();
    	gasPrice.setPrice(price+"");
    	GasPrice prices = gasPriceRepository.findByPriceOrderByPriceDescLimit1();
    	if(price>Double.parseDouble(prices.getPrice())) {
    		message.setSubject("今日油价历史新高！ "+price+"元/升");
    		message.setText("");
    		mailRecordService.mailLog(message, mailRecievers);
    	}else {
    		prices = gasPriceRepository.findByPriceOrderByPriceLimit1();
    		if(price<Double.parseDouble(prices.getPrice())) {
    			message.setSubject("今日油价历史新低！ "+price+"元/升");
    			message.setText("");
    			mailRecordService.mailLog(message, mailRecievers);
    		}
    	}
    	gasPriceRepository.save(gasPrice);
    	activityLogService.activityLog(LogTypeEnum.youjiaOk);
    }
}