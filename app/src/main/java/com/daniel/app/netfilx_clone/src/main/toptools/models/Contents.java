package com.daniel.app.netfilx_clone.src.main.toptools.models;

public class Contents {

    String title;
    int ImgURL;

    public Contents() {
    }

    public Contents(String title, int imgURL) {
        this.title = title;
        ImgURL = imgURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImgURL() {
        return ImgURL;
    }

    public void setImgURL(int imgURL) {
        ImgURL = imgURL;
    }
}
