package com.daniel.app.netfilx_clone.src.profile.profile_selection.models;


    public class selection_detail {

        int profileImgId;

        String profileImgUrl;

        public selection_detail() {
        }

        public selection_detail(int profileImgId, String profileImgUrl) {
            this.profileImgId = profileImgId;
            this.profileImgUrl = profileImgUrl;
        }

        public int getProfileImgId() {
            return profileImgId;
        }

        public void setProfileImgId(int profileImgId) {
            this.profileImgId = profileImgId;
        }

        public String getProfileImgUrl() {
            return profileImgUrl;
        }

        public void setProfileImgUrl(String profileImgUrl) {
            this.profileImgUrl = profileImgUrl;
        }

    }

