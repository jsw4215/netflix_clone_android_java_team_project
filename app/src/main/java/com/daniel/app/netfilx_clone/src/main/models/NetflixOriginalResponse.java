package com.daniel.app.netfilx_clone.src.main.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NetflixOriginalResponse {

    @SerializedName("result")
    List<NetflixOriginalResult> result;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    public NetflixOriginalResponse() {
    }

    public NetflixOriginalResponse(List<NetflixOriginalResult> result, int code, String message, boolean isSuccess) {
        this.result = result;
        this.code = code;
        this.message = message;
        this.isSuccess = isSuccess;
    }

    public List<NetflixOriginalResult> getResult() {
        return result;
    }

    public void setResult(List<NetflixOriginalResult> result) {
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
