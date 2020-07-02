package com.daniel.app.netfilx_clone.src.register.models;

import com.google.gson.annotations.SerializedName;

public class RegisterResponse {

    @SerializedName("jwt")
    private String jwt;

    @SerializedName("userId")
    private int userId;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    @SerializedName("code")
    private String code;

    @SerializedName("message")
    private String message;

    public RegisterResponse(String jwt, int userId, boolean isSuccess, String code, String message) {
        this.jwt = jwt;
        this.userId = userId;
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
