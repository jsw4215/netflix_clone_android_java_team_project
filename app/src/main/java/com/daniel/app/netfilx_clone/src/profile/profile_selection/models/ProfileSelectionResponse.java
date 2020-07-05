package com.daniel.app.netfilx_clone.src.profile.profile_selection.models;

import com.google.gson.annotations.SerializedName;

public class ProfileSelectionResponse {

    @SerializedName("results")
    private results results;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;



    public ProfileSelectionResponse() {
    }

    public ProfileSelectionResponse(results results, boolean isSuccess, int code, String message) {
        this.results = results;
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }

    public results getResults() {
        return results;
    }

    public void setResults(results results) {
        this.results = results;
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
