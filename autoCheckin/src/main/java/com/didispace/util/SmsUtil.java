package com.didispace.util;



import org.apache.log4j.Logger;
import org.junit.Test;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;


/**
 * 短信发送工具类
 * @author Administrator
 *
 */
public class SmsUtil {
	private static Logger logger = Logger.getLogger(SmsUtil.class.getName());
	// 产品名称:云通信短信API产品,开发者无需替换
    private static final String product = "Dysmsapi";
    // 产品域名,开发者无需替换
    private static final String domain = "dysmsapi.aliyuncs.com";

    // 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
    private static String accessKeyId;
    private static String accessKeySecret;
    private static String signName;
    private static String withDrawTempleteCode;
    private static String registTempleteCode;
    private static String changeLoginPassCode;

    static{
    	accessKeyId="LTAIzMsH30HTysGO";
    	accessKeySecret="DHDjXWdwQwmFfBFQBOEwB3bVDO9MKu";
    	signName="栀旅";
    	withDrawTempleteCode="SMS_117512096";
    	registTempleteCode="SMS_117527103";
    	changeLoginPassCode=null;
    }
    
    public static SendSmsResponse sendSms(String mobile, String templateParam, String templateCode)
            throws ClientException {
    	logger.info("发送短信, 手机号:"+mobile);
        // 可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        // 初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        // 组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();

        // 必填:待发送手机号
        request.setPhoneNumbers(mobile);
        // 必填:短信签名-可在短信控制台中找到
        request.setSignName(signName);
        // 必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(templateCode);

        // 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam(templateParam);

        // 选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        // request.setSmsUpExtendCode("90997");

        // 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");

        // hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

        return sendSmsResponse;
    }

    public static SendSmsResponse sendUserRegistCode(String mobile, String code) {
        try {
            return sendSms(mobile, "{\"code\":\"" + code+"\"}",
                    registTempleteCode);
        } catch (ClientException e) {
//            throw new CustomException(e.getMessage());
        }
		return null;
    }

    public static SendSmsResponse sendWithdrawNotice(String mobile, String username) {
        try {
            return sendSms(mobile, "{\"name\":\"" + username+"\"}", withDrawTempleteCode);
        } catch (ClientException e) {
//            throw new CustomException(e.getMessage());
        }
		return null;
    }
    public static SendSmsResponse sendChangeLoginPassCode(String mobile, String code) {
    	try {
            return sendSms(mobile, "{\"code\":\"" + code+"\"}",
            		changeLoginPassCode);
        } catch (ClientException e) {
//            throw new CustomException(e.getMessage());
        }
		return null;
    }
    /**
     * 绑定银行卡-的验证码
     * @param mobile
     * @param code
     * @return
     * @throws CustomException
     */
	public static SendSmsResponse sendBandCardCode(String mobile, String code) {
		try {
            return sendSms(mobile, "{\"code\":\"" + code+"\"}",
            		registTempleteCode);
        } catch (Exception e) {
//            throw new CustomException(e.getMessage());
        }
		return null;
	}
	/**
	 * 更换手机-的验证码
	 * @param telephone
	 * @param code
	 * @return
	 * @throws CustomException
	 */
	public static SendSmsResponse sendChangeTelCode(String telephone, String code) {
		try {
            return sendSms(telephone, "{\"code\":\"" + code+"\"}",
            		registTempleteCode);
        } catch (ClientException e) {
//            throw new CustomException(e.getMessage());
        }
		return null;
	}
    public static String getCode(){
    	int d=(int)((Math.random()*9+1)*1000);
    	String res=String.valueOf(d);
    	return res;
    }
  /*  public static void main(String[] args) {
		System.out.println(getCode());
	}*/
    @Test
    public void name() {
    	try {
    		SendSmsResponse smsResponse = sendChangeLoginPassCode("18633815628", "1234");
    		System.out.println(smsResponse.toString());
    	} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
