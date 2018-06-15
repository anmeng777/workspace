package com.didispace.web.weixin;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.didispace.dao.WeixinUsersRepository;
import com.didispace.entity.WeixinUsers;
import com.didispace.util.weixin.HttpClientUtil;
import com.didispace.util.weixin.ParaInit;
import com.didispace.util.weixin.WeiXinInfo;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * 微信授权回调
 * 
 * @author Administrator
 * 
 */
@Controller
public class OAuth2Callback {
	public String nickname;
	
	@Autowired
	private WeixinUsersRepository weixinUsersRepository;

	@RequestMapping("OAuth2Callback")
	protected String doGet(HttpServletRequest req, ModelMap map) throws IOException {
		// TODO Auto-generated method stub
		
		String code = req.getParameter("code");
		String page = req.getParameter("page");
		page = "weixin";
		String userinfo = req.getParameter("userinfo");
		if (code != null && !code.equals("")) {// 获取到微信回调的CODE
			String result = HttpClientUtil
					.doGet("https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + ParaInit.WX_APPID + "&secret="
							+ ParaInit.WX_APPSECRET + "&code=" + code + "&grant_type=authorization_code");
			JsonObject token_json = new JsonParser().parse(result).getAsJsonObject();
			if (token_json.has("openid")) {// 获取到OPENID
				if (userinfo != null && userinfo.equals("snsapi_userinfo")) {// 授权获取用户信息调用userinfo=snsapi_userinfo
					AuthorizationUserInfo(req, page, token_json.get("openid").getAsString(),
							token_json.get("access_token").getAsString());
				} else {// 服务号调用，//
					ServiceNumberProcessing(req, page, token_json.get("openid").getAsString());
				}
			}
		}
		map.addAttribute("nickname", nickname);
		HttpSession session = req.getSession();
		session.setAttribute("NICKNAME", nickname);
		return page;
		// 未获取到OPENID跳转到微信打开页面
	}

	/**
	 * 服务号的调用，处理用户详情后直接返回PAGE页面
	 * 
	 * @param req
	 * @param resp
	 * @param page
	 * @param openid
	 * @throws IOException
	 */
	private void ServiceNumberProcessing(HttpServletRequest req, String page, String openid)
			throws IOException {
		HttpSession session = req.getSession();
		session.setAttribute("OPENID", openid);
		session.setMaxInactiveInterval(86400);// SESSION24小时失效
		// 获取用户详细信息
		String result = HttpClientUtil.doGet("https://api.weixin.qq.com/cgi-bin/user/info?access_token="
				+ WeiXinInfo.getInstance().getAccesstoken() + "&openid=" + openid + "&lang=zh_CN");
		try {
			JsonObject userinfo = new JsonParser().parse(result).getAsJsonObject();
			saveUsers(userinfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 用户授权时，获取用户信息存储，userinfo=snsapi_userinfo时调用此处
	 * 
	 * @param req
	 * @param resp
	 * @param page
	 * @param openid
	 * @param accesstoken
	 * @throws IOException
	 */
	private void AuthorizationUserInfo(HttpServletRequest req, String page, String openid,
			String accesstoken) throws IOException {
		// 获取用户详细信息
		String result = HttpClientUtil.doGet("https://api.weixin.qq.com/sns/userinfo?access_token=" + accesstoken
				+ "&openid=" + openid + "&lang=zh_CN");
		JsonObject json = new JsonParser().parse(result).getAsJsonObject();
		HttpSession session = req.getSession();
		session.setAttribute("OPENID", openid);
		session.setMaxInactiveInterval(86400);// SESSION24小时失效
		if (json.has("openid")) {
			try {
				Optional<WeixinUsers> userOp = weixinUsersRepository.findByOpenid(json.get("openid").getAsString());

				WeixinUsers user = new WeixinUsers();
				if (!userOp.isPresent()) {
					user.setCity(json.get("city").getAsString());// 城市
					user.setCountry(json.get("country").getAsString());// 国家
					user.setHeadImgUrl(json.get("headimgurl").getAsString());// 头像URL
					user.setNickName(json.get("nickname").getAsString());// 昵称
					user.setOpenid(json.get("openid").getAsString());// 用户ID
					user.setProvince(json.get("province").getAsString());// 省份
					user.setSex(json.get("sex").getAsShort());// 性别
					user.setSubscribe((short) -1);// 是否关注
					user.setUnionid(json.has("unionid") ? json.get("unionid").getAsString() : "");// 勾勾唯
																									// 一标识
					weixinUsersRepository.save(user);

				} else {
					user = userOp.get();
					user.setCity(json.get("city").getAsString());// 城市
					user.setCountry(json.get("country").getAsString());// 国家
					user.setHeadImgUrl(json.get("headimgurl").getAsString());// 头像URL
					user.setNickName(json.get("nickname").getAsString());// 昵称
					user.setSex(json.get("sex").getAsShort());// 性别
				}
				weixinUsersRepository.save(user);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void saveUsers(JsonObject json) throws Exception {
		if (json.has("subscribe") && json.get("subscribe").getAsInt() != 0) {
			Optional<WeixinUsers> userOp = weixinUsersRepository.findByOpenid(json.get("openid").getAsString());
			WeixinUsers user = new WeixinUsers();
			if (!userOp.isPresent()) {
				user.setCity(json.get("city").getAsString());// 城市
				user.setCountry(json.get("country").getAsString());// 国家
				user.setGroupId(json.get("groupid").getAsInt());// 分组ID
				user.setHeadImgUrl(json.get("headimgurl").getAsString());// 头像URL
				user.setLanguage(json.get("language").getAsString());// 语言
				user.setFirstTime(new Timestamp(json.get("subscribe_time").getAsLong() * 1000));// 关注时间
				user.setLastSynTime(new Timestamp(System.currentTimeMillis()));// 最后操作时间
				user.setNickName(json.get("nickname").getAsString());// 昵称
				user.setOpenid(json.get("openid").getAsString());// 用户ID
				user.setProvince(json.get("province").getAsString());// 省份
				user.setRemark(json.has("remark") ? json.get("remark").getAsString() : "");// 备注
				user.setSex(json.get("sex").getAsShort());// 性别
				user.setSubscribe(json.get("subscribe").getAsShort());// 是否关注
				user.setUnionid(json.get("unionid").getAsString());// 勾勾唯 一标识
			} else {
				user = userOp.get();
				user.setCity(json.get("city").getAsString());// 城市
				user.setCountry(json.get("country").getAsString());// 国家
				user.setGroupId(json.get("groupid").getAsInt());// 分组ID
				user.setHeadImgUrl(json.get("headimgurl").getAsString());// 头像URL
				user.setLanguage(json.get("language").getAsString());// 语言
				user.setFirstTime(new Timestamp(json.get("subscribe_time").getAsLong() * 1000));// 关注时间
				user.setLastSynTime(new Timestamp(System.currentTimeMillis()));// 最后操作时间
				user.setNickName(json.get("nickname").getAsString());// 昵称
				user.setProvince(json.get("province").getAsString());// 省份
				user.setRemark(json.has("remark") ? json.get("remark").getAsString() : "");// 备注
				user.setSex(json.get("sex").getAsShort());// 性别
				user.setSubscribe(json.get("subscribe").getAsShort());// 是否关注
				user.setUnionid(json.get("unionid").getAsString());// 勾勾唯 一标识
			}
			weixinUsersRepository.save(user);
			this.nickname = json.get("nickname").getAsString();
			
		} else if (json.has("subscribe")) {
			Optional<WeixinUsers> userOp = weixinUsersRepository.findByOpenid(json.get("openid").getAsString());
			WeixinUsers user = new WeixinUsers();
			if (!userOp.isPresent()) {
				user.setLastSynTime(new Timestamp(System.currentTimeMillis()));// 最后操作时间
				user.setSubscribe((short) 0);// 是否关注
				user.setUnTime(new Timestamp(System.currentTimeMillis()));
			} else {
				user.setOpenid(json.get("openid").getAsString());// 用户ID
				user.setUnionid(json.get("unionid").getAsString());// 勾勾唯 一标识
			}
			weixinUsersRepository.save(user);
		}

	}

}
