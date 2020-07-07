package com.daniel.app.netfilx_clone.src.main.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RecommendResponse {

    @SerializedName("recommendContents")
    List<RecommendResult> recommendContents;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    public RecommendResponse() {
    }

    public RecommendResponse(List<RecommendResult> recommendContents, int code, String message, boolean isSuccess) {
        this.recommendContents = recommendContents;
        this.code = code;
        this.message = message;
        this.isSuccess = isSuccess;
    }

    public List<RecommendResult> getRecommendContents() {
        return recommendContents;
    }

    public void setRecommendContents(List<RecommendResult> recommendContents) {
        this.recommendContents = recommendContents;
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
