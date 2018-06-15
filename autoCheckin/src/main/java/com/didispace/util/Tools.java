package com.didispace.util;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import com.didispace.util.weixin.ParaInit;


public class Tools {

	/**
	 * 礼物兑换平台收取手续费
	 */
	public final static double GIFT_EXCHANGE_PLATFORM = 0.1;
	/**
	 * 礼物兑换酒吧收取手续费
	 */
	public final static double GIFT_EXCHANGE_BAR = 0.1;
	/**
	 * 礼物合伙人手续费
	 */
	public final static double GIFT_EXCHANGE_AGENT = 0.1;
	/**
	 * 消费VIP基数
	 */
	public final static int VIP = 10;
	/**
	 * 红包平台分成比例
	 */
	public final static double PLATFORMSCALE_REDMONEY = 0.05;
	/**
	 * 红包余额提现比例
	 */
	public final static double PLATFORMFACT_REDMONEY = 0.05;

	/**
	 * 数字正则表达式
	 */
	public final static String REGULAR_NUMBER = "[0-9]*";
	/**
	 * 手机号码正则表达式
	 */
	public final static String REGULAR_PHONE = "^1[345678]\\d{9}$";
	/**
	 * 邮箱正则表达式
	 */
	public final static String REGULAR_EMAIL = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(.[a-zA-Z0-9_-]+)+$";
	/**
	 * 管理员免费使用霸屏次数
	 */
	public final static short ADMIN_SHOW_FREE = 10;
	/**
	 * 管理员免费霸屏最大时长
	 */
	public final static short ADMIN_MAX_BPTI = 60;
	/**
	 * 独享歌曲默认金额
	 */
	public final static int UNSHARED_SONG_MONEY = 666;
	/**
	 * 上传文件操作类型为酒吧LOGO
	 */
	public final static int UPLOAD_FILE_1 = 1;
	/**
	 * 上传文件操作类型为酒吧二维码
	 */
	public final static int UPLOAD_FILE_2 = 2;
	/**
	 * 上传文件操作类型为背景视频
	 */
	public final static int UPLOAD_FILE_3 = 3;

	/**
	 * 上传文件操作类型为点歌房演唱者头像
	 */
	public final static int UPLOAD_FILE_4 = 4;

	/**
	 * 歌房财富计算系数
	 */
	public final static int DGF_COEFFICIENT = 3;
	/**
	 * 微上墙财富计算系数
	 */
	public final static int WSQ_COEFFICIENT = 3;
	/**
	 * 默认酒吧Logo图片位置
	 */
	public final static String DEFAULTBAR_LOGO = "/pl/img/barlogo.png";

	/**
	 * 默认酒吧二维码图片位置
	 */
	public final static String DEFALUTBAR_TWOCODE = "/pl/img/twocode_default.jpg";
	/**
	 * WEB 显示图片根路径
	 */
	public final static String WEB_BASICS_IMG = "/pl/barimg/";
	/**
	 * WEB 显示视频根路径
	 */
	public final static String WEB_BASICS_MOVIE = "/pl/movie/";
//	/**
//	 * 酒吧上传图片路径
//	 */
//	public final static String UPLOAD_IMG_PATH = ParaInit.MAIN_PATH + "/pl/barimg/";
//	/**
//	 * 存储excel的地方
//	 */
//	public final static String FILE_EXCEL_PATH = ParaInit.MAIN_PATH + "/pl/excel/";
//	/**
//	 * 酒吧上传视频路径
//	 */
//	public final static String UPLOAD_VIDEO_PATH = ParaInit.MAIN_PATH + "/pl/movie/";
//	/**
//	 * 投票上传图片路径
//	 */
//	public final static String VOTE_UPLOAD_IMG_PATH = ParaInit.MAIN_PATH + "/pl/voteimg/";
	/**
	 * WEB显示投票根路径
	 */
	public final static String VOTE_WEB_BASICS_IMG = "/pl/voteimg/";

//	/**
//	 * 打赏上传图片路径
//	 */
//	public final static String DS_UPLOAD_IMG_PATH = ParaInit.MAIN_PATH + "/pl/dsimg/";
	/**
	 * WEB显示打赏根路径
	 */
	public final static String DS_WEB_BASICS_IMG = "/pl/dsimg/";
//	/**
//	 * 摇大奖上传图片路径
//	 */
//	public final static String YDJ_UPLOAD_IMG_PATH = ParaInit.MAIN_PATH + "/pl/ydjimg/";
	/**
	 * WEB摇大奖路径
	 */
	public final static String YDJ_WEB_BASICS_IMG = ParaInit.SKIP_URL + "/pl/ydjimg/";

//	public final static String TRUTH_BASICS_PATH = ParaInit.MAIN_PATH + "/pl/truthImg/";

	/**
	 * 点歌房功能
	 */
	public final static String FUNCTION_DGF = "DGF";

	/**
	 * 微上墙功能
	 */
	public final static String FUNCTION_WSQ = "WSQ";
	/**
	 * 单人摇一摇功能
	 */
	public final static String FUNCTION_DRY = "DRY";
	/**
	 * 分桌摇一摇功能
	 */
	public final static String FUNCTION_FZY = "FZY";

	/**
	 * 赛马场功能
	 */
	public final static String FUNCTION_SMC = "SMC";
	
	/**
	 * 赛车场功能
	 */
	public final static String FUNCTION_SCC = "SCC";
	/**
	 * 数钱功能
	 */
	public final static String FUNCTION_SQ = "SQQ";

	/**
	 * 翻牌子功能
	 */
	public final static String FUNCTION_FPZ = "FPZ";
	/**
	 * 红包功能
	 */
	public final static String FUNCTION_HB = "HB";
	/**
	 * 投票功能
	 */
	public final static String FUNCTION_VOTE = "VOTE";
	/**
	 * 投票功能新版
	 */
	public final static String FUNCTION_TP = "TP";
	/**
	 * 打赏礼物
	 */
	public final static String FUNCTION_DS = "REWARD";
	/**
	 * 广告
	 */
	public final static String FUNCTION_AD = "AD";

	public final static String DS_YRK = "艺人库";
	/**
	 * 摇大奖功能
	 */
	public final static String FUNCTION_YDJ = "YDJ";
	/**
	 * 秒杀功能
	 */
	public final static String FUNCTION_DMS = "DMS";
	/**
	 * 夺宝功能
	 */
	public final static String FUNCTION_DDB = "DDB";

	/**
	 * 投票默认背景图片
	 */
	public final static String VOTE_DEFAULT_BJ = "/pl/voteimg/vote_bj_default.png";
//	/**
//	 * 敏感字库文件地址
//	 */
//	public final static String SENSITIVEWORD_FILTER_FILEDIR = ParaInit.MAIN_PATH + "/pl/sensitive/CensorWords.txt";

	/**
	 * 时间格式 yyyy-MM-dd HH:mm
	 */
	public final static SimpleDateFormat YYYYMMDDHHMI = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	/**
	 * 时间格式 MM-dd HH:mm
	 */
	public final static SimpleDateFormat MMDDHHMI = new SimpleDateFormat("MM-dd HH:mm");
	/**
	 * 时间格式 yyyy-MM-dd HH:mm:ss
	 */
	public final static SimpleDateFormat YYYYMMDDHHMISS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 时间格式MM-dd
	 */
	public final static SimpleDateFormat MMDD = new SimpleDateFormat("MM-dd");

	/**
	 * 时间格式YYYY-MM
	 */
	public final static SimpleDateFormat YYYYMM = new SimpleDateFormat("yyyy-MM");
	/**
	 * 时间格式YYYY-MM-DD
	 */
	public final static SimpleDateFormat YYYYMMDD = new SimpleDateFormat("yyyy-MM-dd");
	/**
	 * 时间格式YYYY-MM-DD-HH
	 */
	public final static SimpleDateFormat YYYYMMDDHH = new SimpleDateFormat("yyyyMMdd-HH");
	/**
	 * 时间格式HH小时
	 */
	public final static SimpleDateFormat HH = new SimpleDateFormat("HH");
	/**
	 * 单位为分，当前金额*100转换为分
	 */
	public final static int BRANCH = 100;

	// 订单ID默认初始值
	private static int OrderSerialNumber = 100;
	// 图片名称生成默认初始值
	private static int ImgNameSerialNumber = 100;
//	/**
//	 * 功能二维码保存位置
//	 */
//	public final static String TWOCODE_PATH = ParaInit.MAIN_PATH + "/pl/barimg/twocode/";
	/**
	 * 二维码WEB使用
	 */
	public final static String TWOCODE_WEB = "/pl/barimg/twocode/";
	// 生成随机数实体类
	public final static Random RANDOM = new Random();
	/**
	 * 每日验证码次数
	 */
	public final static int CHECKCODE_NUMBER = 5;

	/**
	 * 验证码时效30分钟，单位毫秒
	 */
	public final static int CHECKCODE_TIMEOUT = 1800000;

	/**
	 * 生成验证码
	 * 
	 * @return
	 */
	public static String GETCHECKCODE() {
		int tmpint = RANDOM.nextInt(10000);
		while (tmpint < 1000) {
			tmpint = RANDOM.nextInt(10000);
		}
		String checkCode = Integer.toString(tmpint);
		return checkCode;
	}

	/**
	 * 生成订单ID
	 * @return 订单ID
	 */
	public synchronized static String generateOrderID() {
		if (OrderSerialNumber > 999) {
			OrderSerialNumber = 100;
		}
		return Long.toString(System.currentTimeMillis()) + Integer.toString(OrderSerialNumber++);
	}

	/**
	 * 生成图片名称
	 * @return 图片名称
	 */
	public synchronized static String generateImgName() {
		if (ImgNameSerialNumber > 999) {
			ImgNameSerialNumber = 100;
		}
		return Long.toString(System.currentTimeMillis()) + Integer.toString(ImgNameSerialNumber++);
	}

	/**
	 * 下一个流水初始值
	 */
	private static int SEQUENCE = 10000;

	/**
	 * 下一个流水ID18位
	 * 
	 * @return
	 */
	public static synchronized String nextSequence() {
		if (SEQUENCE > 99999) {
			SEQUENCE = 10000;
		}
		return Long.toString(System.currentTimeMillis()) + Integer.toString(SEQUENCE++);
	}

	/**
	 * 单位分的整型金额转换为浮点型带2位小数的金额
	 * 
	 * @param money
	 * @return
	 */
	public static String getMoneyIntTOString(int money) {
		float tmp = (float) money / 100;
		DecimalFormat fnum = new DecimalFormat("##0.00");
		return fnum.format(tmp);
	}

	/**
	 * 单位分的整型金额转换为浮点型带2位小数的金额￥1.00
	 * 
	 * @param money
	 * @return
	 */
	public static String getMoneyIntToStringFormat(int money) {
		float tmp = (float) money / 100;
		DecimalFormat fnum = new DecimalFormat("￥##0.00");
		return fnum.format(tmp);
	}

	/**
	 * 金额格式(三个零分割)
	 * @param value 金额
	 * @return 格式化后的金额
	 */
	public static String getWealthSeparate(int value) {
		DecimalFormat df = new DecimalFormat(",###");
		return df.format(value);
	}

	// 验证手机正则表达式串
	public static String Matcher_str() {
		return "1([\\d]{10})";
	}

	/**
	 * 隐藏部分内容(规则隐藏后8-5位)
	 * @param bankNumber 需要隐藏的手机号，银行卡号等
	 * @return 隐藏活动内容
	 */
	public static String hiddenPart(String bankNumber) {
		StringBuilder sb = new StringBuilder();
		sb.append(bankNumber.substring(0, bankNumber.length() - 8));
		sb.append("****");
		sb.append(bankNumber.substring(bankNumber.length() - 4, bankNumber.length()));
		return sb.toString();
	}

//	/**
//	 * 分配红包
//	 * @param redSize 红包个数
//	 * @param redMoney 红包总金额
//	 * @return JSONArray{单个红包金额}
//	 */
//	public static JSONArray RandomRedMoney(int redSize, int redMoney) {
//		JSONArray json = new JSONArray();
//		JSONArray jsonTemp = new JSONArray();
//		Random random = new Random();
//		int temp = redMoney;
//		for (int i = 0, j; i < redSize - 1; i++) {
//			j = random.nextInt(temp / (redSize - i)) + 1;
//			temp -= j;
//			jsonTemp.add(j);
//		}
//		jsonTemp.add(temp);
//
//		while (!jsonTemp.isEmpty()) {
//			json.add(jsonTemp.remove(random.nextInt(jsonTemp.size())));
//		}
//		return json;
//	}

	/**
	 * 当前时间小于13点，返回-1天的时间。13点以后为当前时间
	 * @return
	 */
	public static Date minusOneDay() {
		// long l = System.currentTimeMillis();
		// SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cale = Calendar.getInstance();
		// cale.setTimeInMillis(System.currentTimeMillis());
		int hour = cale.get(Calendar.HOUR_OF_DAY);
		if (hour < 13) {
			cale.add(Calendar.DATE, -1);
		}
		return cale.getTime();
	}

	/**
	 * 格式化当前时间，当前时间小于8点记为昨天
	 * @return 如：2017-06-13 07:50:00 返回 2017-06-12
	 * @author wei
	 * @date 2017年6月13日
	 */
	public static String getFormatDate() {
		Calendar cale = Calendar.getInstance();
		cale.setTimeInMillis(System.currentTimeMillis());
		int hour = cale.get(Calendar.HOUR_OF_DAY);
		if (hour < 8) {
			cale.set(Calendar.DAY_OF_MONTH, cale.get(Calendar.DAY_OF_MONTH) - 1);
		}
		return YYYYMMDD.format(cale.getTime());
	}

	/**
	 * 按大小排序OPENID
	 * @param openid 用户1
	 * @param openid2 用户2
	 * @return 用户数组大小为2第一位和第二位
	 */
	public static String[] sortOpenid(String openid, String openid2) {
		String[] str = new String[2];
		if (openid.compareTo(openid2) > 0) {
			str[0] = openid;
			str[1] = openid2;
		} else {
			str[0] = openid2;
			str[1] = openid;
		}
		return str;
	}

	/**
	 * 验证日期时间格式
	 * @param str 日期时间数据
	 * @return 是否为日期时间格式true or false
	 */
	public static boolean isValidDate(String str) {
		boolean convertSuccess = true;
		// 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			// 设置lenient为false.
			// 否则SimpleDateFormat会比较宽松地验证日期，比如2007-02-29会被接受，并转换成2007-03-01
			format.setLenient(false);
			format.parse(str);
		} catch (ParseException e) {
			// e.printStackTrace();
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			convertSuccess = false;
		}
		return convertSuccess;
	}

	/**
	 * 返回年，输入月份大于当前月份年减1
	 * @author 张国良 
	 * @param month
	 * @return
	 * @date 2017年11月14日
	 */
	public static String getYear(int month) {
		Calendar cal = Calendar.getInstance();
		String year = Integer.toString(cal.get(Calendar.YEAR));
		if (cal.get(Calendar.MONTH) + 1 < month) {
			cal.add(Calendar.YEAR, -1);
			year = Integer.toString(cal.get(Calendar.YEAR));
		}
		return year;
	}

	/**
	 * 补齐月份
	 * @author 张国良 
	 * @param month
	 * @return
	 * @date 2017年11月14日
	 */
	public static String getMonth(int month) {
		if (month < 10) {
			return "0" + month;
		} else {
			return Integer.toString(month);
		}
	}
}
