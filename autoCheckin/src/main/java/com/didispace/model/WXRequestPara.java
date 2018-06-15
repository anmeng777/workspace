package com.didispace.model;

public class WXRequestPara {
	private String key;
	private String value;

	public WXRequestPara(String key, int value) {
		this.key = key;
		this.value = Integer.toString(value);
	}

	public WXRequestPara(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}
}
