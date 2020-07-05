package com.daniel.app.netfilx_clone.src.profile.profile_selection.models;


import java.util.List;

public class results {

        String profileName;

        List<selection_detail> details;

    public results() {
    }

    public results(String profileName, List<selection_detail> details) {
        this.profileName = profileName;
        this.details = details;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public List<selection_detail> getDetails() {
        return details;
    }

    public void setDetails(List<selection_detail> details) {
        this.details = details;
    }
}

