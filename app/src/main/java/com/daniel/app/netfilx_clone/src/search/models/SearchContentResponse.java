package com.daniel.app.netfilx_clone.src.search.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchContentResponse {

    @SerializedName("result")
    List<SearchPopularResult> result;

    @SerializedName("searchKeyword")
    private String searchStatus;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;



}
