package com.didispace.service.miniProgram;

import java.io.UnsupportedEncodingException;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.spec.InvalidParameterSpecException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.client.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.didispace.dao.UserRepository;
import com.didispace.entity.User;
import com.didispace.util.HttpRequest;
import com.didispace.util.weixin.Sha1Util;

@Service
public class MiniProgramService {
	@Autowired
	private UserRepository userRepository;
	/**
	 * 初始设置
	 * @param request
	 * @return
	 */
	public String tokenCheck(HttpServletRequest request) {
		// 微信设置
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		String token = "asdf0707"; // #请按照公众平台官网\基本配置中信息填写
		ArrayList<String> array = new ArrayList<String>();
		array.add(token);
		array.add(timestamp);
		array.add(nonce);
		Collections.sort(array);
		StringBuilder sb = new StringBuilder();
		sb.append(array.get(0));
		sb.append(array.get(1));
		sb.append(array.get(2));
		System.out.println("echostr: " + echostr);
		System.out.println("success?");
		if (Sha1Util.SHA1(sb.toString()).equals(signature)) {
			System.out.println("success!");
			return echostr;
		}
		return "";
	}

	String appid = "wxf732e7fe3eb678e4";
	String secret = "42f1a0c4df1615861c52180a2c7d4fa0";

	public String getOpenid(String code) {
		String back = HttpRequest.sendGet("https://api.weixin.qq.com/sns/jscode2session",
				"appid="+appid
				+ "&secret="+secret
				+ "&js_code="+code
				+ "&grant_type=authorization_code");
		JSONObject jsonObject = JSONObject.parseObject(back);
		System.out.println(jsonObject.getString("openid"));
		try {
			if(userRepository.findByUserName(jsonObject.getString("openid")).size()==0) {
				userRepository.save(new User(jsonObject.getString("openid")));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "YES";
	}
	
	
	
}
