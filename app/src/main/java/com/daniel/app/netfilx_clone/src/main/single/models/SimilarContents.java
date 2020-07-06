package com.daniel.app.netfilx_clone.src.main.single.models;

import java.util.List;

public class SimilarContents {

    List<Similar> similarContents;

    public SimilarContents() {
    }

    public SimilarContents(List<Similar> similarContents) {
        this.similarContents = similarContents;
    }

    public List<Similar> getSimilarContents() {
        return similarContents;
    }

    public void setSimilarContents(List<Similar> similarContents) {
        this.similarContents = similarContents;
    }
}
