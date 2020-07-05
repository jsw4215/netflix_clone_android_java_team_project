package com.daniel.app.netfilx_clone.src.signin.interfaces;

import androidx.annotation.Nullable;

public interface SignInActivityView {

    void validateSuccess(boolean isSuccess, int code, String jwt);

    void validateFailure(String message);

}
