package com.didispace.util.weixin;

import java.text.SimpleDateFormat;

public class ParaInit {

	/**
	 * 参赛用户列表每页显示数量
	 */
//	public final static int SHOW_PAGE = 20;

	/**
	 * 文本信息
	 */
//	public static String MSG_TEXT = "text";
	/**
	 * 图片信息
	 */
//	public static String MSG_IMG = "image";
	/**
	 * 语音信息
	 */
//	public static String MSG_VOICE = "voice";
	/**
	 * 视频信息
	 */
//	public static String MSG_VIDEO = "video";
	/**
	 * 音乐信息
	 */
//	public static String MSG_MUSIC = "music";
	/**
	 * 图文信息
	 */
//	public static String MSG_NEWS = "news";

	/**
	 * yyyy-MM-dd HH:mm
	 */
	public final static SimpleDateFormat YYYYMMDDHHMI = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	// 上大屏的
	/**
	* 服务名称
	*/
//	public static String SERVICE_NAME = "勾勾";
	/**
	* 业务名称
	*/
//	public static String PRODUCT_NAME = "上大屏";
	/**
	* 服务电话
	*/
//	public static String SERVICE_TEL = "4000626687";
	/**
	* 官网
	*/
//	public static String SERVICE_INDEX = "http://www.jjpar.com";
	/**
	* 识别二维码后主动发送的消息
	*/
	public static String AUTOSEND_MSG = "自动回复消息";
	/**
	* 公众号原始ID
	*/
	public static String WX_WID = "gh_0d777b60b1ae";
	/**
	* 公众号APPID
	*/
	public static String WX_APPID = "wxbbea6d917de31d2f";
	/**
	* 公众号密钥
	*/
	public static String WX_APPSECRET = "d5d6c969f72d2e011b409e0ff4be0c35";
	/**
	* 商户帐号
	*/
	public static String WX_MCH_ID = "1490506492";

	/**
	* 支付密钥
	*/
	public static String WX_PAYAPPKEY = "05fD8173e7D1fcfeeD2279802117192b";

	/**
	* 支付回调URL
	*/
	public static String NOTIFY_URL = "https://16u882035y.51mypc.cn/autoCheckin/wx/OrderPayBackInfo";

	/**
	* 发送红包的证书
	*/
//	public static String WX_PAYKEYPATH = "/opt/pay/";

	/**
	* 发送红包的客户端IP，需要在微信支付添加
	*/
//	public static String HB_CLIENT_IP = "123.206.87.127";

	/**
	* 跳转URL
	*/
	public static String SKIP_URL = "http://wx.jjpar.com";

	/**
	* 文件主目录
	*/
//	public static String MAIN_PATH = "/opt/webapps/weixin";

	/**
	* 上传的文件路径
	*/
//	public static String FILE_PATH = "http://jjpar.oss-cn-shanghai.aliyuncs.com";

	/**
	* 待办事项提示的ID
	*/
//	public static String TEMPLATE_SENDMSG = "2yf0OZMxgUO-y2hX4Sd3Gv540-nRCOCRaRMk3UD1Z8o";
	/**
	* 用户收入的提示ID
	*/
//	public static String TEMPLATE_INCOMEMESSAGE = "75I1HZj2dB8mdAiqzrWvOAMM9gOAWzCZh8GL5IzC53M";
	/**
	* 待审核消息提示ID
	*/
//	public static String TEMPLATE_EXAMINE = "swuJxaNEiXwwu3r2KkPt5v-8JmoBtTotelemLZrfObY";

	/**
	* 待审核消息提示ID
	*/
//	public static String TEMPLATE_EXAMINENOTICE = "JjzbCWk5I_rSvdnuj4rQXMoVyY3ED1hyZjL4btP0cnY";

//	// 鲸鱼的
//	/**
//	* 服务名称
//	*/
//	public static String SERVICE_NAME = "鲸鱼互联";
//	/**
//	* 业务名称
//	*/
//	public static String PRODUCT_NAME = "霸屏";
//	/**
//	* 服务电话
//	*/
//	public static String SERVICE_TEL = "18031191182";
//	/**
//	* 官网
//	*/
//	public static String SERVICE_INDEX = "http://www.uulbs.com";
//	/**
//	* 识别二维码后主动发送的消息
//	*/
//	public static String AUTOSEND_MSG = "欢迎你来到鲸鱼互联！\n点击【霸屏】进入酒吧聊天室，点击【我的】看看个人信息。";
//	/**
//	* 公众号原始ID
//	*/
//	public static String WX_WID = "gh_0d777b60b1ae";
//	/**
//	* 公众号APPID
//	*/
//	public static String WX_APPID = "wxbbea6d917de31d2f";
//	/**
//	* 公众号密钥
//	*/
//	public static String WX_APPSECRET = "fda6096db0d7e6ae29cf5605b73e8c6d";
//	/**
//	* 商户帐号
//	*/
//	public static String WX_MCH_ID = "1490506492";
//
//	/**
//	* 支付密钥
//	*/
//	public static String WX_PAYAPPKEY = "05fD8173e7D1fcfeeD2279802117192b";
//
//	/**
//	* 支付回调URL
//	*/
//	public static String NOTIFY_URL = "http://wx.uulbs.cn/wxpaycallback";
//
//	/**
//	* 发送红包的证书
//	*/
//	public static String WX_PAYKEYPATH = "/opt/pay/JingYu";
//
//	/**
//	* 发送红包的客户端IP，需要在微信支付添加
//	*/
//	public static String HB_CLIENT_IP = "123.206.87.127";
//
//	/**
//	* 跳转URL
//	*/
//	public static String SKIP_URL = "http://wx.uulbs.cn";
//
//	/**
//	* 文件主目录
//	*/
//	public static String MAIN_PATH = "/opt/webapps/jingyu";
//
//	/**
//	* 上传的文件路径
//	*/
//	public static String FILE_PATH = "http://uulbs.oss-cn-shanghai.aliyuncs.com";
//
//	/**
//	* 待办事项提示的ID
//	*/
//	public static String TEMPLATE_SENDMSG = "2yf0OZMxgUO-y2hX4Sd3Gv540-nRCOCRaRMk3UD1Z8o";
//	/**
//	* 用户收入的提示ID
//	*/
//	public static String TEMPLATE_INCOMEMESSAGE = "75I1HZj2dB8mdAiqzrWvOAMM9gOAWzCZh8GL5IzC53M";
//	/**
//	 * 待审核消息提示ID
//	 */
//	public static String TEMPLATE_EXAMINE = "swuJxaNEiXwwu3r2KkPt5v-8JmoBtTotelemLZrfObY";
//
//	/**
//	 * 待审核消息提示ID
//	 */
//	public static String TEMPLATE_EXAMINENOTICE = "JjzbCWk5I_rSvdnuj4rQXMoVyY3ED1hyZjL4btP0cnY";

}
