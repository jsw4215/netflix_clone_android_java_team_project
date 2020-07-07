package com.daniel.app.netfilx_clone.src.main.interfaces;

import com.daniel.app.netfilx_clone.src.main.toptools.models.ZzimResponse;
import com.daniel.app.netfilx_clone.src.main.toptools.models.ZzimResult;

public interface MainActivityView {

    void validateSuccess(String text);

    void zzzimSuccess(ZzimResponse zzimResponse);

    void validateFailure(String message);
}
