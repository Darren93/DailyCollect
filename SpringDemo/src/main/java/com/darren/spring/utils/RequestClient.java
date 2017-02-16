package com.darren.spring.utils;

public enum RequestClient {

	COMMON("10000", "AFK2365U"),
	// 安卓
	ANDROID_KEY("10001", "AFK2365U"),
	// 苹果
	IOS_KEY("10002", "AFK2365U"),
	// 微信
	WECHAT_KEY("10003", "AFK2365U");

	private String code, key;

	RequestClient(String code, String key) {
		this.code = code;
		this.key = key;
	}

	public String value() {
		return this.code;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public static String getCode(String code) {
		RequestClient[] list = RequestClient.values();
		for (int i = 0; i < list.length; i++) {
			if (list[i].code.equals(code)) {
				return list[i].key;
			}
		}
		return "";
	}

}
