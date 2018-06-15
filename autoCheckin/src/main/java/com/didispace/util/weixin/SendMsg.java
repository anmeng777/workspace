package com.didispace.util.weixin;


public class SendMsg {

	/**
	 * 普通回复消息
	 * 
	 * @param args
	 */
	private static SendMsg sm = null;

	private SendMsg() {
	}

	public synchronized static SendMsg getInstance() {
		if (sm == null) {
			sm = new SendMsg();
		}
		return sm;
	}

	/**
	 * 转至多客服
	 * @return
	 */
	public String sendCustomerService(String toUser, String fromUser) {
		StringBuilder sb = new StringBuilder();
		sb.append("<xml><ToUserName><![CDATA[");
		sb.append(fromUser);
		sb.append("]]></ToUserName><FromUserName><![CDATA[");
		sb.append(toUser);
		sb.append("]]></FromUserName><CreateTime>");
		sb.append((int) (System.currentTimeMillis() / 1000));
		sb.append("</CreateTime><MsgType><![CDATA[transfer_customer_service]]></MsgType></xml>");
		return sb.toString();
	}

	/**
	 * 发送图文链接信息
	 * 
	 * @param toUser
	 *            接收ID
	 * @param fromUser
	 *            发送者ID
	 * @return
	 */
	public String sendImgText(String toUser, String fromUser) {
		StringBuilder sb = new StringBuilder();
		sb.append("<xml><ToUserName><![CDATA[");
		sb.append(fromUser);
		sb.append("]]></ToUserName><FromUserName><![CDATA[");
		sb.append(toUser);
		sb.append("]]></FromUserName><CreateTime>");
		sb.append((int) (System.currentTimeMillis() / 1000));
		sb.append("</CreateTime><MsgType><![CDATA[news]]></MsgType><ArticleCount>1</ArticleCount><Articles><item>");
		sb.append("<Title><![CDATA[最会玩的庄里妹 有奖评选]]></Title>");
		sb.append("<Description><![CDATA[首届[最会玩的庄里妹]评选开赛，\n彰显个性秀自我，结识朋友得赞美，\n呼朋唤友做公益，传播爱心赢奖品。]]></Description>");
		sb.append("<PicUrl><![CDATA["+ParaInit.SKIP_URL+"/tp/img/top1.jpg]]></PicUrl>");
		sb.append("<Url><![CDATA["+ParaInit.SKIP_URL+"/tp/index.jsp?app=weixin]]></Url>");
		sb.append("</item></Articles></xml>");
		return sb.toString();
	}

//	/**
//	 * 发送图文链接信息
//	 * 
//	 * @param toUser
//	 *            接收ID
//	 * @param fromUser
//	 *            发送者ID
//	 * @return
//	 */
//	public String sendSceneImgText(String toUser, String fromUser, String scene) {
//		try {
//			if (scene.startsWith("qrscene_")) {
//				scene = scene.replaceFirst("qrscene_", "");
//			}
//			PlatformBar bar = (PlatformBar) DBOper.getDBClass(PlatformBar.class, scene);
//			if (bar == null) {
//				return sendText(toUser, fromUser, ParaInit.AUTOSEND_MSG);
//			} else {
//				StringBuilder sb = new StringBuilder();
//				sb.append("<xml><ToUserName><![CDATA[");
//				sb.append(fromUser);
//				sb.append("]]></ToUserName><FromUserName><![CDATA[");
//				sb.append(toUser);
//				sb.append("]]></FromUserName><CreateTime>");
//				sb.append((int) (System.currentTimeMillis() / 1000));
//				sb.append("</CreateTime><MsgType><![CDATA[news]]></MsgType><ArticleCount>1</ArticleCount><Articles><item>");
//				sb.append("<Title><![CDATA[" + bar.getBarName() + ParaInit.PRODUCT_NAME+" 点击加入]]></Title>");
//				sb.append("<Description></Description>");
//				sb.append("<PicUrl><![CDATA["+ParaInit.SKIP_URL+"/pl/img/scene1.jpg]]></PicUrl>");
//				sb.append("<Url><![CDATA["+ParaInit.SKIP_URL+"/pl/wsqmobile.jsp?bid=" + scene + "]]></Url>");
//				sb.append("</item></Articles></xml>");
//				return sb.toString();
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			return sendText(toUser, fromUser, ParaInit.AUTOSEND_MSG);
//		}
//	}

	/**
	 * 发送文字信息
	 * 
	 * @param toUser
	 *            接收ID
	 * @param fromUser
	 *            发送者ID
	 * @param msg
	 *            消息内容
	 * @return
	 */
	public String sendText(String toUser, String fromUser, String msg) {
		StringBuilder sb = new StringBuilder();
		sb.append("<xml><ToUserName><![CDATA[");
		sb.append(fromUser);
		sb.append("]]></ToUserName><FromUserName><![CDATA[");
		sb.append(toUser);
		sb.append("]]></FromUserName><CreateTime>");
		sb.append((int) (System.currentTimeMillis() / 1000));
		sb.append("</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[");
		sb.append(msg);
		sb.append("]]></Content></xml>");
		return sb.toString();
	}

	/**
	 * 发送图片消息
	 * 
	 * @param toUser
	 *            接收ID
	 * @param fromUser
	 *            发送者ID
	 * @param mediaid
	 *            图片MEDIAID
	 * @return
	 */
	public String sendImg(String toUser, String fromUser, String mediaid) {
		StringBuilder sb = new StringBuilder();
		sb.append("<xml><ToUserName><![CDATA[");
		sb.append(fromUser);
		sb.append("]]></ToUserName><FromUserName><![CDATA[");
		sb.append(toUser);
		sb.append("]]></FromUserName><CreateTime>");
		sb.append((int) (System.currentTimeMillis() / 1000));
		sb.append("</CreateTime><MsgType><![CDATA[image]]></MsgType><Image><MediaId><![CDATA[");
		sb.append(mediaid);
		sb.append("]]></MediaId></Image></xml>");
		return sb.toString();
	}

}
