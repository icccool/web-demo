package com.web.utils.redis;

import java.io.Serializable;

public class RedisObject implements Serializable {

	private static final long serialVersionUID = -6011241820070393952L;

	private String key;
	private Object value;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

}