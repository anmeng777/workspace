package com.didispace.web;

import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.didispace.Chapter1Application;
import com.didispace.dao.MailRecieverRepository;
import com.didispace.emus.LogTypeEnum;
import com.didispace.entity.MailReciever;
import com.didispace.service.ActivityLogService;
import com.didispace.service.MailService;
import com.didispace.util.HttpRequest;

@Component
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Chapter1Application.class)
public class XianHaoController {
	@Autowired
	private MailService mailRecordService;
	@Autowired
	private MailRecieverRepository mailRecieverRepository;
	@Autowired
	private ActivityLogService activityLogService;
	
    @Scheduled(cron = "0 5 0 * * ?")  //cron接受cron表达式，根据cron表达式确定定时规则
    public void checkinCron() throws Exception{
    	String regmsg = HttpRequest.sendGet(
				"https://xianxing.911cha.com/shijiazhuang.html", 
				null);
    	SimpleMailMessage message = new SimpleMailMessage();
    	message.setFrom("anmeng@live.cn");
    	List<MailReciever> mailRecievers = mailRecieverRepository
    	    	.findByIsEnableTrueAndXianhao(true);
    	    	
		String[] mailer = new String[mailRecievers.size()];
		int i = 0;
		for(MailReciever mReciever : mailRecievers) {
			mailer[i] = mReciever.getMail();
			i++;
		}
    	message.setTo(mailer);
    	Document doc = Jsoup.parse(regmsg);
    	Elements elements = doc.select("div.f18.l200");
    	int index = 0;
    	boolean isSend = false;
    	StringBuffer stringBuffer = new StringBuffer();
    	for(Element element : elements) {
    		if(index%2==0) {
    			stringBuffer.append(String.format("%1$-" + (30-element.html().length()) + "s", element.html()));
    		}else {
    			stringBuffer.append(element.html()).append("\n");
    		}
    		if(!"不限行".equals(element.html())&&index%2==1){
    			isSend = true;
    		}
    		index++;
    	}
    	if(isSend) {
    		message.setSubject("限号提醒");
    		message.setText(stringBuffer.toString());
    		mailRecordService.mailLog(message, mailRecievers);
    	}
    	activityLogService.activityLog(LogTypeEnum.xianhaoOk);
    }
}