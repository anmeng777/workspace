package com.didispace.service.weixin;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.didispace.dao.WeiXinPaymentsRepository;
import com.didispace.entity.WeiXinPaments;
import com.didispace.model.WXRequestPara;
import com.didispace.util.weixin.ParaInit;
import com.didispace.util.weixin.SSLClient;
import com.didispace.util.weixin.WX_MD5;
import com.google.gson.JsonObject;

@Service
public class WeiXinService {
	@Autowired
	private WeiXinPaymentsRepository weiXinPaymentsRepository;

	/**
	 * 生成微信订单
	 * @param req
	 * @return
	 */
	public JSONObject makeOrder(HttpServletRequest req) {

		// 客户端IP用户微信支付
		String clientip = "";
		if (req.getHeader("x-forwarded-for") == null) {
			clientip = req.getRemoteAddr();
		} else {
			clientip = req.getHeader("x-forwarded-for");
			if (clientip.length() > 15) {
				clientip = clientip.split(",")[0];
			}
		}
		HttpSession session = req.getSession();
		String openid = (String) session.getAttribute("OPENID");
		String body = req.getParameter("body") == null ? "" : req.getParameter("body");

		int money = req.getParameter("money") == null ? 0 : (int) (Double.parseDouble(req.getParameter("money")) * 100);
		String nickname = (String) session.getAttribute("NICKNAME");

		WeiXinPaments weiXinPaments = new WeiXinPaments(money, openid, nickname, body);

		weiXinPaments = weiXinPaymentsRepository.save(weiXinPaments);
		String orderid = weiXinPaments.getOrderid();

		JSONObject json = new JSONObject();
		json.put("ST", 1);
		try {
			List<WXRequestPara> packageParams = new LinkedList<WXRequestPara>();
			packageParams.add(new WXRequestPara("appid", ParaInit.WX_APPID));// 公众帐号ID，32位
			// if (attach != null && attach.length() > 1) {
			// packageParams.add(new WXRequestPara("attach", attach));//
			// 附加数据，在查询API和支付通知中原样返回，可作为自定义参数使用。
			// }
			packageParams.add(new WXRequestPara("body", body));// 商品描述，128位
			packageParams.add(new WXRequestPara("device_info", "WEB"));// 终端设备号，32位
			packageParams.add(new WXRequestPara("mch_id", ParaInit.WX_MCH_ID));// 微信支付分配的商户号，32位
			packageParams.add(new WXRequestPara("nonce_str", WX_MD5.genNonceStr()));// 随机字符串，32位
			packageParams.add(new WXRequestPara("notify_url", ParaInit.NOTIFY_URL));// 通知回调地址，256位
			packageParams.add(new WXRequestPara("openid", openid));// 支付用户的OPENID，128位
			packageParams.add(new WXRequestPara("out_trade_no", orderid));// 商户订单号，32位
			packageParams.add(new WXRequestPara("spbill_create_ip", clientip));// 终端IP
			packageParams.add(new WXRequestPara("total_fee", money));// 总金额,INT，单位分
			packageParams.add(new WXRequestPara("trade_type", "JSAPI"));// 交易类型
			packageParams.add(new WXRequestPara("sign", WX_MD5.genPackageSign(packageParams)));// 签名

			HttpClient httpClient = new SSLClient();
			HttpPost httpPost = new HttpPost("https://api.mch.weixin.qq.com/pay/unifiedorder");
			StringEntity myEntity = new StringEntity(WX_MD5.toXml(packageParams), "UTF-8");
			httpPost.addHeader("Content-Type", "text/xml");
			httpPost.setEntity(myEntity);
			HttpResponse response = httpClient.execute(httpPost);
			if (response != null) {
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					Document respd = DocumentHelper.parseText(EntityUtils.toString(resEntity, "UTF-8"));
					Element resp = respd.getRootElement();
					System.out.println("  "+resp.asXML());
					
					if ("SUCCESS".equals(resp.elementText("return_code"))) {
						if ("SUCCESS".equals(resp.elementText("result_code"))
								&& resp.elementText("prepay_id") != null) {// 预支付是否成功

							// //以下字段在return_code 和result_code都为SUCCESS的时候有返回
							json.put("TIMESTAMP", System.currentTimeMillis() / 1000);
							json.put("NONCESTR", WX_MD5.genNonceStr());
							json.put("PACKAGE", "prepay_id=" + resp.elementText("prepay_id"));
							json.put("SIGNTYPE", "MD5");

							List<WXRequestPara> pack = new LinkedList<WXRequestPara>();
							pack.add(new WXRequestPara("appId", ParaInit.WX_APPID));// 公众帐号ID，32位
							pack.add(new WXRequestPara("nonceStr", json.getString("NONCESTR")));// 支付签名随机串，不长于32位
							pack.add(new WXRequestPara("package", json.getString("PACKAGE")));// 统一支付接口返回的prepay_id参数值，提交格式如：prepay_id=***
							pack.add(new WXRequestPara("signType", json.getString("SIGNTYPE")));// 签名方式，默认为'SHA1'，使用新版支付需传入'MD5'
							pack.add(new WXRequestPara("timeStamp", json.getString("TIMESTAMP")));// 支付签名时间戳
							json.put("PAYSIGN", WX_MD5.genPackageSign(pack));
							json.put("ST", 0);
						} else {
							json.put("ERR", resp.elementText("err_code_des"));
						}
					} else {
						json.put("ERR", resp.elementText("return_msg"));
					}
				} else {
					json.put("ERR", "请重新打开");
				}
			} else {
				json.put("ERR", "请重新打开");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return json;
	}

}
