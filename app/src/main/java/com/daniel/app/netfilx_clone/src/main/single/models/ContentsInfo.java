package com.daniel.app.netfilx_clone.src.main.single.models;

public class ContentsInfo {

    String genres;

    String thumbnailImgUrl;

    String nfOriginal;

    int year;

    String age;

    String runtime;

    String videoUrl;

    String details;

    String actors;

    String directors;

    public ContentsInfo() {

    }

    public ContentsInfo(String genres, String thumbnailImgUrl, String nfOriginal, int year, String age, String runtime, String videoUrl, String details, String actors, String directors) {
        this.genres = genres;
        this.thumbnailImgUrl = thumbnailImgUrl;
        this.nfOriginal = nfOriginal;
        this.year = year;
        this.age = age;
        this.runtime = runtime;
        this.videoUrl = videoUrl;
        this.details = details;
        this.actors = actors;
        this.directors = directors;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getDirectors() {
        return directors;
    }

    public void setDirectors(String directors) {
        this.directors = directors;
    }
}
