package com.daniel.app.netfilx_clone.src.main.toptools.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ZzimResponse {

    @SerializedName("result")
    private List<ZzimResult> result;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    public ZzimResponse() {
    }

    public ZzimResponse(List<ZzimResult> result, int code, String message, boolean isSuccess) {
        this.result = result;
        this.code = code;
        this.message = message;
        this.isSuccess = isSuccess;
    }

    public List<ZzimResult> getResult() {
        return result;
    }

    public void setResult(List<ZzimResult> result) {
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
