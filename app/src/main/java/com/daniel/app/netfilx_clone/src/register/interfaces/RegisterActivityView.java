package com.daniel.app.netfilx_clone.src.register.interfaces;

import androidx.annotation.Nullable;

public interface RegisterActivityView {

    void validateSuccess(String token);

    void validateFailure(String message);

}
