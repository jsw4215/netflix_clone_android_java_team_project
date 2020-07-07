package com.daniel.app.netfilx_clone.src.main.single.models;

public class EvaluateResult {

    int profileId;

    int contentsId;

    String evalStatus;

    String choice;

    public EvaluateResult() {
    }

    public EvaluateResult(int profileId, int contentsId, String evalStatus, String choice) {
        this.profileId = profileId;
        this.contentsId = contentsId;
        this.evalStatus = evalStatus;
        this.choice = choice;
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

    public String getEvalStatus() {
        return evalStatus;
    }

    public void setEvalStatus(String evalStatus) {
        this.evalStatus = evalStatus;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }
}
