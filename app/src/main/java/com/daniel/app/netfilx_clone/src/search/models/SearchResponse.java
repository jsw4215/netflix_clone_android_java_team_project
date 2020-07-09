package com.daniel.app.netfilx_clone.src.search.models;

import com.daniel.app.netfilx_clone.src.main.models.NetflixOriginalResult;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchResponse {

    @SerializedName("result")
    List<SearchResult> result;

    @SerializedName("searchStatus")
    private String searchStatus;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    public SearchResponse() {
    }

    public SearchResponse(List<SearchResult> result, String searchStatus, int code, String message, boolean isSuccess) {
        this.result = result;
        this.searchStatus = searchStatus;
        this.code = code;
        this.message = message;
        this.isSuccess = isSuccess;
    }

    public List<SearchResult> getResult() {
        return result;
    }

    public void setResult(List<SearchResult> result) {
        this.result = result;
    }

    public String getSearchStatus() {
        return searchStatus;
    }

    public void setSearchStatus(String searchStatus) {
        this.searchStatus = searchStatus;
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
