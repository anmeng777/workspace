package com.didispace.util.weixin;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class WeiXinInfo {

	public static String WX_TOKEN = "asdf0707";
	private long accesstoken_time = 0;
	private long jsapiticket_time = 0;
	private String accesstoken;
	private String jsapiticket;
	private static WeiXinInfo WXI = null;

	public static WeiXinInfo getInstance() {
		if (WXI == null) {
			synchronized (WeiXinInfo.class) {
				if (WXI == null) {
					WXI = new WeiXinInfo();
				}
			}
		}
		return WXI;
	}

	public String getAccesstoken() {
		synchronized (this) {
			if (System.currentTimeMillis() - this.accesstoken_time > 3600000 || this.accesstoken == null) {
				String result = HttpClientUtil
						.doGet("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
								+ ParaInit.WX_APPID + "&secret=" + ParaInit.WX_APPSECRET);
				try {
					JsonObject jsonre = new JsonParser().parse(result).getAsJsonObject();
					this.accesstoken = jsonre.get("access_token").getAsString();
					this.accesstoken_time = System.currentTimeMillis();
				} catch (Exception e) {
					this.accesstoken_time = 0;
					e.printStackTrace();
				}
			}
		}
		return this.accesstoken;
	}

	public void initAccesstoken() {
		String result = HttpClientUtil
				.doGet("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + ParaInit.WX_APPID
						+ "&secret=" + ParaInit.WX_APPSECRET);
		try {
			JsonObject jsonre = new JsonParser().parse(result).getAsJsonObject();
			this.accesstoken = jsonre.get("access_token").getAsString();
			this.accesstoken_time = System.currentTimeMillis();
		} catch (Exception e) {
			this.accesstoken_time = 0;
			e.printStackTrace();
		}
	}

	public String getJsapiticket() {
		synchronized (this) {
			if (System.currentTimeMillis() - this.jsapiticket_time > 3600000 || this.jsapiticket == null) {
				String result = HttpClientUtil.doGet("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="
						+ this.getAccesstoken() + "&type=jsapi");
				try {
					JsonObject jsonre = new JsonParser().parse(result).getAsJsonObject();
					this.jsapiticket = jsonre.get("ticket").getAsString();
					this.jsapiticket_time = System.currentTimeMillis();
				} catch (Exception e) {
					this.jsapiticket_time = 0;
					e.printStackTrace();
				}
			}
		}
		return this.jsapiticket;
	}

	public void initJsapiticket() {
		String result = HttpClientUtil.doGet("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="
				+ this.getAccesstoken() + "&type=jsapi");
		try {
			JsonObject jsonre = new JsonParser().parse(result).getAsJsonObject();
			this.jsapiticket = jsonre.get("ticket").getAsString();
			this.jsapiticket_time = System.currentTimeMillis();
		} catch (Exception e) {
			this.jsapiticket_time = 0;
			e.printStackTrace();
		}
	}

//	private String certPath = ParaInit.WX_PAYKEYPATH + "apiclient_cert.p12";
	private static byte[] CERTDATA = null;

	/**
	 * 读取微信支付证书
	 * 
	 * @author 张国良
	 * @date 2017年8月16日
	 */
//	private void readCert() {
//		try {
//			File file = new File(certPath);
//			InputStream certStream = new FileInputStream(file);
//			this.CERTDATA = new byte[(int) file.length()];
//			certStream.read(this.CERTDATA);
//			certStream.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

//	/**
//	 * 获取微信支付证书流
//	 * 
//	 * @author 张国良
//	 * @return
//	 * @date 2017年8月16日
//	 */
//	public InputStream getCertStream() {
//		if (CERTDATA == null || CERTDATA.length < 5) {
//			readCert();
//		}
//		ByteArrayInputStream certBis;
//		certBis = new ByteArrayInputStream(CERTDATA);
//		return certBis;
//	}
}
