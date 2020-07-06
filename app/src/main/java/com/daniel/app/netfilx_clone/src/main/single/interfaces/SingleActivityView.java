package com.daniel.app.netfilx_clone.src.main.single.interfaces;

import com.daniel.app.netfilx_clone.src.main.single.models.SingleResponse;

public interface SingleActivityView {

    void validateSuccess(SingleResponse singleResponse);

    void validateFailure(String message);
}
