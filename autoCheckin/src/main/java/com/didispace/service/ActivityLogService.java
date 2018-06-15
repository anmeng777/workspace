package com.didispace.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.didispace.dao.ActivityLogRepository;
import com.didispace.emus.LogTypeEnum;
import com.didispace.entity.ActivityLog;

@Service
public class ActivityLogService {

	@Autowired
	private ActivityLogRepository activityLogRepository;
	
	public void activityLog(LogTypeEnum LogTypeEnum) {
		ActivityLog activityLog = new ActivityLog();
		activityLog.setType(LogTypeEnum.getType());
		activityLog.setDesc(LogTypeEnum.getDesc());
		activityLog.setStatus(LogTypeEnum.getStatus());
		activityLogRepository.save(activityLog);
	}
	
	public void activityLogExcepstion(LogTypeEnum LogTypeEnum, Exception e) {
		StackTraceElement[] st = e.getStackTrace();
		StackTraceElement stackTraceElement = st[0];
		String exclassName = stackTraceElement.getClassName();// 类名
		String methodName = stackTraceElement.getMethodName();// 方法名
		int lineNumber = stackTraceElement.getLineNumber();// 第几行
		String exceptionType = e.getClass().getName();// 异常类型
		
		ActivityLog activityLog = new ActivityLog();
		activityLog.setType(LogTypeEnum.getType());
		activityLog.setDesc(LogTypeEnum.getDesc());
		activityLog.setStatus(LogTypeEnum.getStatus());
		activityLog.setClassName(exclassName);
		activityLog.setExceptionType(exceptionType);
		activityLog.setLineNumber(lineNumber);
		activityLog.setMethodName(methodName);
		activityLogRepository.save(activityLog);
	}

}
