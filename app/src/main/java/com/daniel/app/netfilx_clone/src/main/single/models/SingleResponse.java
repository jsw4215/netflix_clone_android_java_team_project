package com.daniel.app.netfilx_clone.src.main.single.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SingleResponse {

    @SerializedName("result")
    ContentsResult contentsResult;

    @SerializedName("similarContents")
    List<Similar> similarContents;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    public SingleResponse() {
    }

    public SingleResponse(ContentsResult contentsResult, List<Similar> similarContents, boolean isSuccess, int code, String message) {
        this.contentsResult = contentsResult;
        this.similarContents = similarContents;
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }

    public ContentsResult getContentsResult() {
        return contentsResult;
    }

    public void setContentsResult(ContentsResult contentsResult) {
        this.contentsResult = contentsResult;
    }

    public List<Similar> getSimilarContents() {
        return similarContents;
    }

    public void setSimilarContents(List<Similar> similarContents) {
        this.similarContents = similarContents;
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
