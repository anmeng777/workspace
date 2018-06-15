package com.didispace.web;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.didispace.Chapter1Application;
import com.didispace.dao.ActivityLogRepository;
import com.didispace.dao.WeiXinPaymentsRepository;
import com.didispace.emus.LogTypeEnum;
import com.didispace.entity.ActivityLog;
import com.didispace.entity.WeiXinPaments;
import com.didispace.util.DataDev;

@Component
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Chapter1Application.class)
@Controller
public class HelloController {
	@Autowired
	private com.didispace.dao.UserRepository userRepository;
	@Autowired
	private ActivityLogRepository ActivityLogRepository;
	@Autowired
	private WeiXinPaymentsRepository weiXinPaymentsRepository;

	@RequestMapping("/index")
	public String index2(ModelMap map) {
		List<ActivityLog> checkinLogs = ActivityLogRepository
	    		.findByLogDateAndType(DataDev.getDate(), 
	    				LogTypeEnum.checkinOk.getType());
		String qiandao = "";
		if(checkinLogs.size()!=1) {
			qiandao = "记录条数为"+checkinLogs.size();
		}else {
			qiandao = checkinLogs.get(0).getStatus();
		}
		map.addAttribute("qiandao", qiandao);
		
		List<ActivityLog> wakuangLogs = ActivityLogRepository
	    		.findByLogDateAndType(DataDev.getDate(), 
	    				LogTypeEnum.wakuangOk.getType());
		String wakuang = "";
		int i = 0;
		for (ActivityLog activityLog : wakuangLogs) {
			if("正常".equals(activityLog.getStatus())) {
				i++;
			}else {
				wakuang += "\t" + activityLog.getLogTime()+"\n";
			}
			
		}
		wakuang = "共记录"+wakuangLogs.size()+"次， 有"+(wakuangLogs.size()-i)+ "次异常\n" + wakuang;
		map.addAttribute("wakuang", wakuang);
		
		return "index";
	}

    @RequestMapping("/hello")
    @ResponseBody
    @Test
    public void index() {
    	// 商户订单号
		String out_trade_no = "152886951962510000";
		// 支付总金额
		int total_fee = 1;
		// 微信支付订单号
		String transaction_id = "4211111111111111111111111111";
		System.out.println("out_trade_no"+out_trade_no);
		System.out.println("total_fee"+total_fee);
		System.out.println("transaction_id"+transaction_id);
		
		WeiXinPaments payments = weiXinPaymentsRepository.findOne(1l);
//		System.out.println(paymentsOp.isPresent());
//		WeiXinPaments payments = paymentsOp.get();
		System.out.println("payments id: "+payments.getId());
		payments.setSuccess(true);
		payments.setWxorderid(transaction_id);
		payments.setTransamt(total_fee);
		weiXinPaymentsRepository.saveAndFlush(payments);
    }
    
    
    

}