package com.devils.hr.responses;

import java.util.Map;

/**
 * Created by AndyL on 2017/4/3.
 */
public class RespWrapper {

    private int     status;

    private String  message = null;

    private Map<String,Object> result = null;

    public RespWrapper(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public RespWrapper(int status, String message, Map<String, Object> result) {
        this.status = status;
        this.message = message;
        this.result = result;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getResult() {
        return result;
    }

    public void setResult(Map<String, Object> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "RespWrapper{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", result=" + result +
                '}';
    }
}
