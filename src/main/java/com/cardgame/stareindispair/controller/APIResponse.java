package com.cardgame.stareindispair.controller;

public class APIResponse {
    private boolean success;
    private String message;
    private Object data;

    public APIResponse(boolean success, String message, Object data){
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public boolean getSuccess(){
        return this.success;
    }

    public String getMessage(){
        return this.message;
    }

    public Object getData(){
        return this.data;
    }

    public void setSuccess(boolean success){
        this.success = success;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public void setData(Object data){
        this.data = data;
    }

    public static APIResponse success(String message, Object data) {
        return new APIResponse(true, message, data);
    }

    public static APIResponse failure(String message, Object data) {
        return new APIResponse(false, message, data);
    }
}
