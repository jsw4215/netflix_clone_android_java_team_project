package com.daniel.app.netfilx_clone.src.signin.interfaces;

import androidx.annotation.Nullable;

public interface SignInActivityView {

    void validateSuccess(boolean isSuccess, int code);

    void validateFailure(String message);

}
