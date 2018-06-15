package com.didispace.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.didispace.emus.LogTypeEnum;
import com.didispace.service.ActivityLogService;
import com.didispace.service.MailService;
import com.didispace.util.DataDev;

@ControllerAdvice
public class GlobalExceptionHandler {

	@Autowired
	private MailService mailService;
	@Autowired
	private ActivityLogService activityLogService;
	public static final String DEFAULT_ERROR_VIEW = "error";

	@ExceptionHandler(value = Exception.class)
	public ModelAndView defaultErrorHandler(Exception ex) throws Exception {

		String message = "";

		StackTraceElement[] st = ex.getStackTrace();
		StackTraceElement stackTraceElement = st[0];
		String exclassName = stackTraceElement.getClassName();// 类名
		String methodName = stackTraceElement.getMethodName();// 方法名
		int lineNumber = stackTraceElement.getLineNumber();// 第几行
		String exceptionType = ex.getClass().getName();// 异常类型

		message += DataDev.getTime() + ":" + "[类:" + exclassName + "]调用" + methodName + "时在第" + lineNumber
				+ "行代码处发生异常!异常类型:" + exceptionType + "\n";

		LogTypeEnum[] types = LogTypeEnum.values();
		for (int i = 0; i < types.length; i++) {
			if (exclassName.contains(types[i].getClassName()) && "有问题".equals(types[i].getStatus())) {
				activityLogService.activityLogExcepstion(types[i], ex);
				mailService.emailException("anmeng@live.cn", "404570978@qq.com", types[i].getClassName().replace("Controller", "")+"错误", message, ex);
			}
		}
		// ActivityLog activityLog = new ActivityLog();
		// activityLog.setDesc(desc);


		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", ex);
		mav.addObject("url", stackTraceElement.getClassName());
		mav.setViewName(DEFAULT_ERROR_VIEW);
		return mav;
	}
}
