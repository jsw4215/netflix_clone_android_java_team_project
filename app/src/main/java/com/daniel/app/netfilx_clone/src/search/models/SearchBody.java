package com.daniel.app.netfilx_clone.src.search.models;

import com.google.gson.annotations.SerializedName;

public class SearchBody {

    @SerializedName("searchStatus")
    String searchStatus;

    public SearchBody() {
    }

    public SearchBody(String searchStatus) {
        this.searchStatus = searchStatus;
    }

    public String getSearchStatus() {
        return searchStatus;
    }

    public void setSearchStatus(String searchStatus) {
        this.searchStatus = searchStatus;
    }
}
