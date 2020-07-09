package com.daniel.app.netfilx_clone.src.search.models;

public class SearchResult {

    int id;

    String title;

    String thumbnailImgUrl;

    String nfOriginal;

    String videoUrl;

    public SearchResult() {
    }

    public SearchResult(int id, String title, String thumbnailImgUrl, String nfOriginal, String videoUrl) {
        this.id = id;
        this.title = title;
        this.thumbnailImgUrl = thumbnailImgUrl;
        this.nfOriginal = nfOriginal;
        this.videoUrl = videoUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnailImgUrl() {
        return thumbnailImgUrl;
    }

    public void setThumbnailImgUrl(String thumbnailImgUrl) {
        this.thumbnailImgUrl = thumbnailImgUrl;
    }

    public String getNfOriginal() {
        return nfOriginal;
    }

    public void setNfOriginal(String nfOriginal) {
        this.nfOriginal = nfOriginal;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
