package com.daniel.app.netfilx_clone.src.signin.models;

import com.google.gson.annotations.SerializedName;

public class SignInBody {

    @SerializedName("email")
    private String email;

    @SerializedName("pw")
    private String pw;

    public SignInBody(String email, String pw) {
        this.email = email;
        this.pw = pw;
    }
}
