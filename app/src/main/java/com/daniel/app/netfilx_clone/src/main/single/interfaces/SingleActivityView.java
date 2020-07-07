package com.daniel.app.netfilx_clone.src.main.single.interfaces;

import com.daniel.app.netfilx_clone.src.main.single.models.EvaluateResponse;
import com.daniel.app.netfilx_clone.src.main.single.models.HeartResponse;
import com.daniel.app.netfilx_clone.src.main.single.models.HeartResult;
import com.daniel.app.netfilx_clone.src.main.single.models.SingleResponse;

public interface SingleActivityView {

    void validateSuccess(SingleResponse singleResponse);

    void evaluateSuccess(EvaluateResponse evaluateResponse);

    void heartSuccess(HeartResponse heartResponse);

    void validateFailure(String message);
}
