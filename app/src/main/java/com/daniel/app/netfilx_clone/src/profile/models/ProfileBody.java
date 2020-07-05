package com.daniel.app.netfilx_clone.src.profile.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ProfileBody {

    String profileName;
    int profileImgId;

    public ProfileBody() {
    }

    public ProfileBody(String profileName, int profileImgId) {
        this.profileName = profileName;
        this.profileImgId = profileImgId;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public int getProfileImgId() {
        return profileImgId;
    }

    public void setProfileImgId(int profileImgId) {
        this.profileImgId = profileImgId;
    }
}
