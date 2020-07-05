package com.daniel.app.netfilx_clone.src.profile.interfaces;

import com.daniel.app.netfilx_clone.src.profile.models.result;

import java.util.ArrayList;
import java.util.List;

public interface ProfileActivityView {

    void validateSuccess(List<result> result, int addAvailable);

    void profileAddSuccess(boolean isSuccess);

    void validateFailure(String message);

}
