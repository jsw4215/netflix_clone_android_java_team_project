package com.daniel.app.netfilx_clone.src.main.single.models;

import com.google.gson.annotations.SerializedName;

public class EvaluateResponse {

    @SerializedName("result")
    private EvaluateResult result;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    public EvaluateResponse() {
    }

    public EvaluateResponse(EvaluateResult result, boolean isSuccess, int code, String message) {
        this.result = result;
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }

    public EvaluateResult getResult() {
        return result;
    }

    public void setResult(EvaluateResult result) {
        this.result = result;
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
