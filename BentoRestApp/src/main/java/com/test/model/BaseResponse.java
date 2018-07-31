package com.test.model;

import java.util.List;
public class BaseResponse {
	private String request;
    private List<String> hops;
    private String result;
    private int code;
 
    public List<String> getHops() {
        return hops;
    }
 
    public void setHops(List<String> hops) {
        this.hops = hops;
    }
 
    public String getResult() {
        return result;
    }
 
    public void setResult(String result) {
        this.result = result;
    }
    public String getRequest() {
        return request;
    }
 
    public void setRequest(String result) {
        this.request = result;
    }
    public int getCode() {
        return code;
    }
 
    public void setCode(int result) {
        this.code = result;
    }
}