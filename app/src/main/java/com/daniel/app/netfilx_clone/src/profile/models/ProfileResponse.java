package com.daniel.app.netfilx_clone.src.profile.models;

import com.daniel.app.netfilx_clone.src.profile.models.result;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ProfileResponse {

    @SerializedName("result")
    private List<result> result;

    @SerializedName("addProfileAvailable")
    private int addProfileAvailable;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;



    public ProfileResponse() {
    }

    public ProfileResponse(List<result> result, int addProfileAvailable, boolean isSuccess, int code, String message) {
        this.result = result;
        this.addProfileAvailable = addProfileAvailable;
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }

    public List<result> getResult() {
        return result;
    }

    public void setResult(List<result> result) {
        this.result = result;
    }

    public int getAddProfileAvailable() {
        return addProfileAvailable;
    }

    public void setAddProfileAvailable(int addProfileAvailable) {
        this.addProfileAvailable = addProfileAvailable;
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
