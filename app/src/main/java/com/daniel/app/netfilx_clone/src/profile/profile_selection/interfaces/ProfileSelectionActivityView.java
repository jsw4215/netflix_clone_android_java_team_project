package com.daniel.app.netfilx_clone.src.profile.profile_selection.interfaces;

import com.daniel.app.netfilx_clone.src.profile.models.result;
import com.daniel.app.netfilx_clone.src.profile.profile_selection.models.results;

import java.util.List;

public interface ProfileSelectionActivityView {

    void validateSuccess(results result);

    void validateFailure(String message);

}
