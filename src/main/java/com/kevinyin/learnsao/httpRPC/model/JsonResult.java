package com.kevinyin.learnsao.httpRPC.model;

/**
 * Created by kevinyin on 2017/7/16.
 */
public class JsonResult {

    private int resultCode;
    private String message;
    private Object result;

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
