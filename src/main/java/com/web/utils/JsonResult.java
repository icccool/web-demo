package com.web.utils;

/**
 * Created by WANG on 2018/6/11.
 */
public class JsonResult {

    public static final String SUC_CODE="1";
    public static final String FAL_CODE="0";

    private String status = null;

    private Object result = null;

    public JsonResult(){}

    public JsonResult(String status, Object result){
        this.status = status;
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }


}
