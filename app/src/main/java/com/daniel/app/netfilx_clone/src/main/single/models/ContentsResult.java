package com.daniel.app.netfilx_clone.src.main.single.models;

public class ContentsResult {

    ContentsInfo contentsInfo;

    String heartStatus;

    String evaluationStatus;

    public ContentsResult() {
    }

    public ContentsResult(ContentsInfo contentsInfo, String heartStatus, String evaluationStatus) {
        this.contentsInfo = contentsInfo;
        this.heartStatus = heartStatus;
        this.evaluationStatus = evaluationStatus;
    }

    public ContentsInfo getContentsInfo() {
        return contentsInfo;
    }

    public void setContentsInfo(ContentsInfo contentsInfo) {
        this.contentsInfo = contentsInfo;
    }

    public String getHeartStatus() {
        return heartStatus;
    }

    public void setHeartStatus(String heartStatus) {
        this.heartStatus = heartStatus;
    }

    public String getEvaluationStatus() {
        return evaluationStatus;
    }

    public void setEvaluationStatus(String evaluationStatus) {
        this.evaluationStatus = evaluationStatus;
    }
}
