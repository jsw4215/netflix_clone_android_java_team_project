package com.daniel.app.netfilx_clone.src.main.models;

public class NetflixOriginalResult {

    int contentsId;

    String thumbnailImgUrl;

    String nfOriginal;

    public NetflixOriginalResult() {
    }

    public NetflixOriginalResult(int contentsId, String thumbnailImgUrl, String nfOriginal) {
        this.contentsId = contentsId;
        this.thumbnailImgUrl = thumbnailImgUrl;
        this.nfOriginal = nfOriginal;
    }

    public int getContentsId() {
        return contentsId;
    }

    public void setContentsId(int contentsId) {
        this.contentsId = contentsId;
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
}
