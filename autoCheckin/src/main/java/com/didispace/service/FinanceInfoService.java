package com.didispace.service;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.didispace.dao.FinanceInfoRepository;
import com.didispace.entity.FinanceInfo;
import com.didispace.model.LoginParam;

@Service
public class FinanceInfoService {
	@Autowired
	private FinanceInfoRepository financeInfoRepository;

	public JSONObject thisMonth() {
		JSONObject jsonObject = new JSONObject();
		LocalDate localDate = LocalDate.now();

		System.out.println(String.format("%02d", localDate.getMonthValue()));
		List<FinanceInfo> financeInfo = financeInfoRepository
				.findByStartDateContainingAndIsBackFalse("-" + String.format("%02d", localDate.getMonthValue()) + "-");
		jsonObject.put("code", "200");
		jsonObject.put("data", financeInfo);
		int totalAmt=0;
		for(FinanceInfo financeInfo2 : financeInfo) {
			totalAmt += financeInfo2.getTotalAmt();
		}
		jsonObject.put("totalAmt", totalAmt);
		return jsonObject;
	}

	public JSONObject nextMonth() {
		JSONObject jsonObject = new JSONObject();
		LocalDate localDate = LocalDate.now();
		List<FinanceInfo> financeInfo = financeInfoRepository.findByStartDateContainingAndIsBackFalse(
				"-" + String.format("%02d", localDate.getMonthValue() + 1) + "-");
		jsonObject.put("code", "200");
		jsonObject.put("data", financeInfo);
		int totalAmt=0;
		for(FinanceInfo financeInfo2 : financeInfo) {
			totalAmt += financeInfo2.getTotalAmt();
		}
		jsonObject.put("totalAmt", totalAmt);
		return jsonObject;
	}

	public JSONObject findAll() {
		JSONObject jsonObject = new JSONObject();
		List<FinanceInfo> financeInfo = financeInfoRepository.findAll();
		jsonObject.put("code", "200");
		jsonObject.put("data", financeInfo);
		int totalAmt=0;
		for(FinanceInfo financeInfo2 : financeInfo) {
			totalAmt += financeInfo2.getTotalAmt();
		}
		jsonObject.put("totalAmt", totalAmt);
		return jsonObject;
	}

	public JSONObject addOne(FinanceInfo financeInfo) {
		JSONObject jsonObject = new JSONObject();
		financeInfoRepository.save(financeInfo);
		jsonObject.put("code", "201");
		jsonObject.put("message", "添加成功");
		return jsonObject;
	}
	
	public JSONObject edit(FinanceInfo financeInfo) {
		JSONObject jsonObject = new JSONObject();
		if(financeInfoRepository.findOne(financeInfo.getId())==null){
			jsonObject.put("code", "301");
			jsonObject.put("message", "没找到这个记录");
			return jsonObject;
		}
		financeInfoRepository.save(financeInfo);
		jsonObject.put("code", "201");
		jsonObject.put("message", "修改成功");
		return jsonObject;
	}
	
	public JSONObject getOne(Long id) {
		JSONObject jsonObject = new JSONObject();
		if(financeInfoRepository.findOne(id)==null){
			jsonObject.put("code", "301");
			jsonObject.put("message", "没找到这个记录");
			return jsonObject;
		}
		jsonObject.put("code", "201");
		jsonObject.put("message", "获取成功");
		jsonObject.put("data", financeInfoRepository.findOne(id));
		return jsonObject;
	}

	public JSONObject login(LoginParam loginParam) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code", "201");
		jsonObject.put("message", "登陆成功");
		return jsonObject;
	}
}
