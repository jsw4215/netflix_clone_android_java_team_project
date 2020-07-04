package com.daniel.app.netfilx_clone.src.comingsoon.models;

import android.net.Uri;
import android.widget.ImageView;
import android.widget.VideoView;

public class MovieDetail {

    Uri videoView;
    String title;
    String contents;
    String genre;
    int titleImage;

    public MovieDetail() {
    }

    public MovieDetail(Uri videoView, String title, String contents, String genre, int titleImage) {
        this.videoView = videoView;
        this.title = title;
        this.contents = contents;
        this.genre = genre;
        this.titleImage = titleImage;
    }

    public Uri getVideoView() {
        return videoView;
    }

    public void setVideoView(Uri videoView) {
        this.videoView = videoView;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getTitleImage() {
        return titleImage;
    }

    public void setTitleImage(int titleImage) {
        this.titleImage = titleImage;
    }
}
