package com.daniel.app.netfilx_clone.src.register.models;

import com.google.gson.annotations.SerializedName;

public class RegisterBody {

    @SerializedName("email")
    private String email;

    @SerializedName("pw")
    private String pw;

    public RegisterBody(String email, String pw) {
        this.email = email;
        this.pw = pw;
    }
}
