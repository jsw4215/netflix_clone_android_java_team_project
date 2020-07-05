package com.daniel.app.netfilx_clone.src.profile.models;


    public class result {

        int profileId;

        String profileImgUrl;

        public result() {
        }

        public result(int profileId, String profileImgUrl) {
            this.profileId = profileId;
            this.profileImgUrl = profileImgUrl;
        }

        public int getProfileId() {
            return profileId;
        }

        public void setProfileId(int profileId) {
            this.profileId = profileId;
        }

        public String getProfileImgUrl() {
            return profileImgUrl;
        }

        public void setProfileImgUrl(String profileImgUrl) {
            this.profileImgUrl = profileImgUrl;
        }

    }

