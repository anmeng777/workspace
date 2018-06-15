package com.didispace.util.weixin;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.List;
import java.util.Random;

import com.didispace.model.WXRequestPara;


public class WX_MD5 {
	public static long genTimeStamp() {
		return System.currentTimeMillis() / 1000;
	}

	public static String toXml(List<WXRequestPara> params) {
		StringBuilder sb = new StringBuilder();
		sb.append("<xml>");
		for (int i = 0; i < params.size(); i++) {
			sb.append("<" + params.get(i).getKey() + ">");

			sb.append(params.get(i).getValue());
			sb.append("</" + params.get(i).getKey() + ">");
		}
		sb.append("</xml>");

		return sb.toString();
	}

	public static String genNonceStr() {
		Random random = new Random();
		return getMessageDigest(String.valueOf(random.nextInt(10000)).getBytes());
	}

	/**
	 * 生成签名
	 * @throws UnsupportedEncodingException 
	 */
	public static String genPackageSign(List<WXRequestPara> params) throws UnsupportedEncodingException {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < params.size(); i++) {
			sb.append(params.get(i).getKey());
			sb.append('=');
			sb.append(params.get(i).getValue());
			sb.append('&');
		}

		sb.append("key=");
		sb.append(ParaInit.WX_PAYAPPKEY);
		String packageSign = WX_MD5.getMessageDigest(sb.toString().getBytes("UTF-8")).toUpperCase();
		return packageSign;
	}

	private final static String getMessageDigest(byte[] buffer) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(buffer);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}
}
