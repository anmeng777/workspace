package com.didispace.emus;

public enum LogTypeEnum {

	checkinOk((short)1,"打卡","正常", "CheckinController"),
	checkinNo((short)1,"打卡","有问题", "CheckinController"),
	wakuangOk((short)2,"挖矿","正常", "WaKuangController"),
	wakuangNo((short)2,"挖矿","有问题", "WaKuangController"),
	weatherOk((short)3,"天气","正常", "WeatherController"),
	weatherNo((short)3,"天气","有问题", "WeatherController"),
	xianhaoOk((short)4,"限号","正常", "XianHaoController"),
	xianhaoNo((short)4,"限号","有问题", "XianHaoController"),
	youjiaOk((short)5,"油价","正常", "YouJiaController"),
	youjiaNo((short)5,"油价","有问题", "YouJiaController"),
	helloNo((short)6,"hello","有问题", "HelloController");
	
	private String status;
	private String desc;
	private short type;
	private String className;
	LogTypeEnum(short type,String desc,String status,String className){
		this.status = status;
		this.desc = desc;
		this.type = type;
		this.className = className;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public short getType() {
		return type;
	}
	public void setType(short type) {
		this.type = type;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
}
