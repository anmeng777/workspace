package com.didispace.util;


import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;


public class DataDev {
	
/*
 * 生产当前时间加六位随机数
 * 不重复
 */
	public static  long RandomKey()
    {
			Date date=new Date();
			long l=date.getTime();
			String orderId=l+""+getRandomID();
            return Long.parseLong(orderId);
       
    }
   
    private static StringBuffer getRandomID()
    {
    	 int randNum = 1 + (int)(Math.random() * ((999999 - 1) + 1));
    	 String r=Integer.toString(randNum);
    	 int x=r.length();
    	 StringBuffer s=new StringBuffer();;
    	 if(x!=6){
    	 for(int i=0;i<6-x;i++){
    		 s.append('0');
    	 }
	 }
    	 return s.append(r);
    }
    public static int getNum(int start,int end) {  
        return (int)(Math.random()*(end-start+1)+start);  
    }  
   
    /*
     * 随机生成手机号
     */
    private static String[] telFirst="134,135,136,137,138,139,150,151,152,157,158,159,187,188,130,131,132,155,156,185,186,133,153,180,189".split(",");  
    public static String getTel() {  
        int index=getNum(0,telFirst.length-1);  
        String first=telFirst[index];  
//        String second=String.valueOf(getNum(1,888)+10000).substring(1);  
//        String thrid=String.valueOf(getNum(1,9100)+10000).substring(1);  
        String second=String.valueOf(getNum(1,9999)+10000).substring(1);  
        String thrid=String.valueOf(getNum(1,9999)+10000).substring(1);  
        return first+second+thrid;  
    }  
    /*
     * 生产当天日期  八位 
     */
    public static String getDate(){
    	Date date=new Date();
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String dateString = formatter.format(date); 
		return dateString;
    }
    public static String getDateWithCut(){
    	Date date=new Date();
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(date); 
		return dateString;
    }
    public static String getDateWithCut(Date date){
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(date); 
		return dateString;
    }
    /**
     * 生产当前时间yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getTime(){
    	Date date=new Date();
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(date); 
		return dateString;
    }
    /**
     * yyyyMMddHHmmss 
     * @return date类型
     */
    public static Date getTimeWithoutBlank(String param){
    	Date date=new Date();
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		Date dateString = null;
		try {
			dateString = formatter.parse(param);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return dateString;
    }
    
    public static String getName(){
    	String[] nameFirst="张三,李四,王五".split(",");
    	int index=getNum(0,nameFirst.length-1);  
        String first=nameFirst[index];  
    	String nameSecond=String.valueOf(getNum(1,9999)+10000).substring(1);
    	return first+nameSecond;
    }
    /*
     * 生产项目的截止日期 多七天
     */
    public static String getcloseDate(){
    	 String str = "";
         SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
         Calendar lastDate = Calendar.getInstance();
         lastDate.roll(Calendar.DATE, 7);
         str=sdf.format(lastDate.getTime());
		return str;
    }
    /*
     * 项目兑付日期  比当前日期多十天
     */
    public static String getchargeDate(){
   	 String str = "";
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        Calendar lastDate = Calendar.getInstance();
        lastDate.roll(Calendar.DATE, 10);
        str=sdf.format(lastDate.getTime());
		return str;
   }
    /*
     * 随机生产一个银行卡号    622588开头    10结尾
     */
    public static String getBankId(){
    	String bank="";
    
    	StringBuffer bankMid=new StringBuffer();
    	String bankIdFirst="622588";
    	String bankLast="10";
    
    	/*System.out.println(i);*/
    	int n;
    	boolean isBankId=false;
    	while(!isBankId){
    		StringBuffer bankId=new StringBuffer();
    		bankId.append(bankIdFirst);
    		//int d=10;
    		/*for(int i=0;i<d;i++){
    			n=getNum(0,9);
    			bankMid.append(n);
    		}*/
    		n=getNum(10000000,99999999);
    		bankId.append(n);
    		bankId.append(bankLast);
    		bank=new String(bankId);
    		if("true"==CheckBankNumber.luhmCheck(bank)){
    			isBankId=true;
    		}
    	}
    	
    	return bank;
    }
   /*
    * 获得邮箱号
    */
    public static String base = "abcdefghijklmnopqrstuvwxyz0123456789";
    private static final String[] email_suffix = "@gmail.com,@yahoo.com,@msn.com,@aol.com,@ask.com,@live.com,@qq.com,@0355.net,@163.com,@163.net,@263.net,@3721.net,@yeah.net,@126.com,@sina.com,@sohu.com"
            .split(",");
    public static String getEmail() {
        int length = getNum(4, 9);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = (int) (Math.random() * base.length());
            sb.append(base.charAt(number));
        }
        sb.append(email_suffix[(int) (Math.random() * email_suffix.length)]);
        return sb.toString();
    }
    /*
     * 获得身份证号
     */
   public static String getIdcard(){
	   return  new IdCardGenerator().generate();
   }
	public static void main(String[] args) {
//		System.out.println(RandomKey());
//		System.out.println(getTel());
//		System.out.println(getDate());
//		System.out.println(getName());
//		System.out.println(getBankId());
//		System.out.println(getIdcard());
//		System.out.println(getEmail());
//		System.out.println(getcloseDate());
		System.out.println(createpwd(3));
	}
	
	/**
	 * 创建随机密码,至少3位
	 * @param length
	 * @return
	 */
	public static String createpwd(int length) {
//		String[] pswdStr = { "qwertyuiopasdfghjklzxcvbnm",
//		    "QWERTYUIOPASDFGHJKLZXCVBNM", "0123456789"};
		String[] pswdStr = {"0123456789"};		
		if(length<3) {
			length=6;
		}
		int pswdLen = length;
		String pswd = " ";
		char[] chs = new char[pswdLen];
		for (int i = 0; i < pswdStr.length; i++) {
			int idx = (int) (Math.random() * pswdStr[i].length());
			chs[i] = pswdStr[i].charAt(idx);
		}

		for (int i = pswdStr.length; i < pswdLen; i++) {

			int arrIdx = (int) (Math.random() * pswdStr.length);
			int strIdx = (int) (Math.random() * pswdStr[arrIdx].length());

			chs[i] = pswdStr[arrIdx].charAt(strIdx);
		}

		for (int i = 0; i < 1000; i++) {
			int idx1 = (int) (Math.random() * chs.length);
			int idx2 = (int) (Math.random() * chs.length);

			if (idx1 == idx2) {
				continue;
			}

			char tempChar = chs[idx1];
			chs[idx1] = chs[idx2];
			chs[idx2] = tempChar;
		}

		pswd = new String(chs);
		System.out.println(pswd);
	 
		return pswd;
	}
	
	public static int transamtWithoutDocX100(double amt){
        DecimalFormat decimalFormat = new DecimalFormat("###################.###########");  
		return Integer.valueOf(decimalFormat.format(amt*100));
	}

}
