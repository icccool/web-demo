package com.web.utils;

import com.alibaba.fastjson.JSON;

/**
 * Created by WANG on 2018/6/11.
 */
public class JsonResult {

	public static final String SUC_CODE = "1";
	public static final String SUC_MSG = "成功";

	public static final String FAL_CODE = "0";
	public static final String FAL_MSG = "失败";

	private String code = null;
	private String msg = null;
	private Object data = null;

	public JsonResult(String code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

	public JsonResult(String code, String msg, Object data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public JsonResult() {
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
	
	

}
