package com.daniel.app.netfilx_clone.src.main.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Top10Response {

    @SerializedName("result")
    List<Top10Result> result;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    public Top10Response() {
    }

    public Top10Response(List<Top10Result> result, int code, String message, boolean isSuccess) {
        this.result = result;
        this.code = code;
        this.message = message;
        this.isSuccess = isSuccess;
    }

    public List<Top10Result> getResult() {
        return result;
    }

    public void setResult(List<Top10Result> result) {
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }
}
