package com.didispace.web.weixin;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.didispace.dao.WeiXinPaymentsRepository;
import com.didispace.entity.WeiXinPaments;
import com.didispace.service.weixin.WeiXinService;
import com.didispace.util.weixin.HttpClientUtil;
import com.didispace.util.weixin.ParaInit;
import com.didispace.util.weixin.SendMsg;
import com.didispace.util.weixin.WeiXinInfo;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@RestController
@RequestMapping("/wx")
public class WeiXinController {
	@Autowired
	private WeiXinService weixinService;
	@Autowired
	private WeiXinPaymentsRepository weiXinPaymentsRepository;

	/**
	 * https://16u882035y.51mypc.cn/autoCheckin/wx/payBackInfo 扫码回调
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("payBackInfo")
	public String saoma(HttpServletRequest request) {
		return "";
	}

	/**
	 * 生成订单
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping("makeOrder")
	public JSONObject makeOrder(HttpServletRequest request) throws IOException {
		JSONObject payJson = weixinService.makeOrder(request);
		JSONObject returnJson = new JSONObject();
		if (payJson.getInteger("ST") == 0) {// 微信预下单成功
			returnJson.put("TIMESTAMP", payJson.getString("TIMESTAMP"));
			returnJson.put("NONCESTR", payJson.getString("NONCESTR"));
			returnJson.put("PACKAGE", payJson.getString("PACKAGE"));
			returnJson.put("SIGNTYPE", payJson.getString("SIGNTYPE"));
			returnJson.put("PAYSIGN", payJson.getString("PAYSIGN"));
		} else {
			returnJson = payJson;
		}

		return returnJson;
	}

	/**
	 * 订单支付回调
	 * 
	 * @param request
	 * @return
	 * @throws IOException 
	 * @throws DocumentException 
	 */
	@ResponseBody
	@RequestMapping("OrderPayBackInfo")
	public String OrderPayBackInfo(HttpServletRequest request) throws IOException, DocumentException {
		System.out.println("=============>");
		DataInputStream in = null;
		String payresult = "";
		int reqlen = request.getContentLength();
		in = new DataInputStream(request.getInputStream());
		byte[] byteReq = new byte[reqlen];
		in.readFully(byteReq);
		payresult = new String(byteReq, "UTF-8");
		Document reqd = DocumentHelper.parseText(payresult);

		Element req = reqd.getRootElement();
		String respxml = "";
		System.out.println("return_code"+req.elementText("return_code"));
		System.out.println("result_code"+req.elementText("result_code"));
		if ("SUCCESS".equals(req.elementText("return_code"))) {
			if ("SUCCESS".equals(req.elementText("result_code"))) {// 支付成功
				// 商户订单号
				String out_trade_no = req.elementText("out_trade_no");
				// 支付总金额
				int total_fee = Integer.parseInt(req.elementText("total_fee"));
				// 微信支付订单号
				String transaction_id = req.elementText("transaction_id");
				System.out.println("out_trade_no"+out_trade_no);
				System.out.println("total_fee"+total_fee);
				System.out.println("transaction_id"+transaction_id);
				
				WeiXinPaments payments = weiXinPaymentsRepository.findByOrderid(out_trade_no).get();
				System.out.println("payments id: "+payments.getId());
				payments.setSuccess(true);
				payments.setWxorderid(transaction_id);
				payments.setTransamt(total_fee);
				weiXinPaymentsRepository.save(payments);
				respxml = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
				System.out.println(respxml);
				
			}
		}
			
		return respxml;
	}

	
	@ResponseBody
	@RequestMapping("/wx")
	public String hello(HttpServletRequest request) throws IOException, DocumentException {
		// 微信设置
		// String signature = request.getParameter("signature");
		// String timestamp = request.getParameter("timestamp");
		// String nonce = request.getParameter("nonce");
		// String echostr = request.getParameter("echostr");
		//
		// String token = "asdf0707"; // #请按照公众平台官网\基本配置中信息填写
		//
		// ArrayList<String> array = new ArrayList<String>();
		// array.add(token);
		// array.add(timestamp);
		// array.add(nonce);
		// Collections.sort(array);
		//
		// StringBuilder sb = new StringBuilder();
		//
		// sb.append(array.get(0));
		// sb.append(array.get(1));
		// sb.append(array.get(2));
		//
		// if (SHA1(sb.toString()).equals(signature)) {
		// return echostr;
		// }
		// return "";

		try {
			DataInputStream in = new DataInputStream(request.getInputStream());
			byte[] b = new byte[request.getContentLength()];
			in.readFully(b);
			SAXReader reader = new SAXReader();
			Document doc = reader.read(new ByteArrayInputStream(b));
			Element root = doc.getRootElement();
			String fromUser = root.elementText("FromUserName");
			String toUser = root.elementText("ToUserName");
			String result = null;
			if (toUser.equals(ParaInit.WX_WID)) {// 勾勾总号
				if (root.elementText("MsgType").equals("event")) {
					result = SendMsg.getInstance().sendText(toUser, fromUser, root.elementText("Event"));
					if (root.elementText("Event").equals("subscribe")) {
						result = SendMsg.getInstance().sendText(toUser, fromUser, "关注成功");
					} else if (root.elementText("Event").equals("unsubscribe")) {
						result = SendMsg.getInstance().sendText(toUser, fromUser, "取关成功");
					} else if (root.elementText("Event").equals("LOCATION")) {
						result = SendMsg.getInstance().sendText(toUser, fromUser,
								"你的经纬度： " + root.elementText("Longitude") + "," + root.elementText("Latitude")
										+ ". 地理位置精度：" + root.elementText("Precision"));
					}

				} else if (root.elementText("MsgType").equals("text")) {
					result = SendMsg.getInstance().sendText(toUser, fromUser, ParaInit.AUTOSEND_MSG);
				} else if (root.elementText("MsgType").equals("image") || root.elementText("MsgType").equals("voice")
						|| root.elementText("MsgType").equals("shortvideo")
						|| root.elementText("MsgType").equals("link") || root.elementText("MsgType").equals("video")) {
					result = SendMsg.getInstance().sendText(toUser, fromUser,
							"已经收到你的消息啦！\nJ姐强烈推荐！\n逗比趣闻点【娱乐】，撩男约女点【交友】\n祝你玩的开心！");
				}
			} else if (toUser.equals("gh_6ffb73adb1a6")) {// 勾勾太原
				if (root.elementText("MsgType").equals("event")) {// 事件类型
					if (root.elementText("Event").equals("subscribe")) {// 订阅
						result = SendMsg.getInstance().sendText(toUser, fromUser, "欢迎新朋友，勾勾越夜越精彩。");
					} else if (root.elementText("Event").equals("unsubscribe")) {// 取消订阅
						result = SendMsg.getInstance().sendText(toUser, fromUser, "取消成功");
					}
				} else if (root.elementText("MsgType").equals("text") || root.elementText("MsgType").equals("image")) {// 发送文字或图片
					result = SendMsg.getInstance().sendText(toUser, fromUser,
							"品质聚会从宝哥开始，越夜越精彩，请朋友们继续支持。\n15034120031 宝哥");
				}
			}
			if (result == null) {
				return "";
			} else {
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	@ResponseBody
	@RequestMapping("/chat")
	public String chat(HttpServletRequest request) throws IOException, DocumentException {
		DataInputStream in = new DataInputStream(request.getInputStream());
		byte[] b = new byte[request.getContentLength()];
		in.readFully(b);
		SAXReader reader = new SAXReader();
		Document doc = reader.read(new ByteArrayInputStream(b));
		Element root = doc.getRootElement();
		String fromUser = root.elementText("FromUserName");
		String toUser = root.elementText("ToUserName");
		String content = root.elementText("Content");

		StringBuilder sb = new StringBuilder();
		sb.append("<xml><ToUserName><![CDATA[");
		sb.append(fromUser);
		sb.append("]]></ToUserName><FromUserName><![CDATA[");
		sb.append(toUser);
		sb.append("]]></FromUserName><CreateTime>");
		sb.append((int) (System.currentTimeMillis() / 1000));
		sb.append("</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[");
		sb.append(content);
		sb.append("]]></Content></xml>");
		return sb.toString();
	}

	@ResponseBody
	@RequestMapping("/tokenTest")
	public String tokenTest() {

		return WeiXinInfo.getInstance().getAccesstoken();

	}

	@ResponseBody
	@RequestMapping("/createMethod")
	public String method(HttpServletRequest request) {
		JsonObject json = new JsonObject();
		JsonArray jsonArray = new JsonArray();
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("type", "click");
		jsonObject.addProperty("name", "点我有惊喜");
		jsonObject.addProperty("key", "V1001_TEST");
		jsonArray.add(jsonObject);
		JsonArray jsonArray2 = new JsonArray();
		jsonObject = new JsonObject();
		jsonObject.addProperty("type", "view");
		jsonObject.addProperty("name", "探索");
		jsonObject.addProperty("url", "http://www.soso.com/");
		jsonArray2.add(jsonObject);
		jsonObject = new JsonObject();
		jsonObject.addProperty("type", "click");
		jsonObject.addProperty("name", "赞一下我们");
		jsonObject.addProperty("key", "V1001_GOOD");
		jsonArray2.add(jsonObject);
		jsonObject = new JsonObject();
		jsonObject.addProperty("name", "菜单");
		jsonObject.add("sub_button", jsonArray2);
		jsonArray.add(jsonObject);
		json.add("button", jsonArray);
		String result = HttpClientUtil.doPost("https://api.weixin.qq.com/cgi-bin/menu/create?access_token="
				+ WeiXinInfo.getInstance().getAccesstoken(), json.toString());
		return result;
	}

	// @ResponseBody
	// @RequestMapping("/uploadimg")
	// public void uploadimg(){
	//
	// String result =
	// HttpClientUtil.doPost("https://api.weixin.qq.com/card/create?access_token="
	// + WeiXinInfo.getInstance().getAccesstoken(), reqJson.toString());
	// JsonObject json = new JsonParser().parse(result).getAsJsonObject();
	//
	// }
	//
	// @ResponseBody
	// @RequestMapping("/createCard")
	// public void createCard(){
	//
	// JsonObject reqJson = new JsonObject();
	//// reqJson
	//
	// String result =
	// HttpClientUtil.doPost("https://api.weixin.qq.com/card/create?access_token="
	// + WeiXinInfo.getInstance().getAccesstoken(), reqJson.toString());
	// JsonObject json = new JsonParser().parse(result).getAsJsonObject();
	//
	//
	// }

	private String SHA1(String decript) {
		try {
			MessageDigest digest = java.security.MessageDigest.getInstance("SHA-1");
			digest.update(decript.getBytes());
			byte messageDigest[] = digest.digest();
			// Create Hex String
			StringBuffer hexString = new StringBuffer();
			// 字节数组转换为 十六进制 数
			for (int i = 0; i < messageDigest.length; i++) {
				String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexString.append(0);
				}
				hexString.append(shaHex);
			}
			return hexString.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}
}
