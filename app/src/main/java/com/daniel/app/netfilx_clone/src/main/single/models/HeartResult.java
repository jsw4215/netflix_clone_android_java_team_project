package com.daniel.app.netfilx_clone.src.main.single.models;

public class HeartResult {

    int profileId;

    int contentsId;

    String heartStatus;

    public HeartResult() {
    }

    public HeartResult(int profileId, int contentsId, String heartStatus) {
        this.profileId = profileId;
        this.contentsId = contentsId;
        this.heartStatus = heartStatus;
    }

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public int getContentsId() {
        return contentsId;
    }

    public void setContentsId(int contentsId) {
        this.contentsId = contentsId;
    }

    public String getHeartStatus() {
        return heartStatus;
    }

    public void setHeartStatus(String heartStatus) {
        this.heartStatus = heartStatus;
    }

}
