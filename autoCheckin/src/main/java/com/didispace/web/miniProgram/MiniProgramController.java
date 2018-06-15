package com.didispace.web.miniProgram;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.didispace.dao.WeiXinPaymentsRepository;
import com.didispace.service.miniProgram.MiniProgramService;

@RestController
@RequestMapping("/miniProgram")
public class MiniProgramController {
	@Autowired
	private MiniProgramService miniProgramService;
	@Autowired
	private WeiXinPaymentsRepository weiXinPaymentsRepository;

	@ResponseBody
	@RequestMapping("/tokenCheck")
	public String hello(HttpServletRequest request) throws IOException, DocumentException {
		return miniProgramService.tokenCheck(request);
	}

	@ResponseBody
	@RequestMapping("/getOpenid")
	public String getOpenid(@RequestParam(value = "code", required = false) String getcode)
			throws IOException, DocumentException {
		return miniProgramService.getOpenid(getcode);
	}
}
