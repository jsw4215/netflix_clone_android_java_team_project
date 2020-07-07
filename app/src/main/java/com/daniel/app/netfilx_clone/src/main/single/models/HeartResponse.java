package com.daniel.app.netfilx_clone.src.main.single.models;

import com.google.gson.annotations.SerializedName;

public class HeartResponse {

    @SerializedName("result")
    HeartResult heartResult;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    public HeartResponse() {
    }

    public HeartResponse(HeartResult heartResult, boolean isSuccess, int code, String message) {
        this.heartResult = heartResult;
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }

    public HeartResult getHeartResult() {
        return heartResult;
    }

    public void setHeartResult(HeartResult heartResult) {
        this.heartResult = heartResult;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
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
}
