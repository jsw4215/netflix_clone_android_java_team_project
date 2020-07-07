package com.daniel.app.netfilx_clone.src.main.interfaces;

import com.daniel.app.netfilx_clone.src.main.models.NetflixOriginalResponse;
import com.daniel.app.netfilx_clone.src.main.models.RecommendResponse;
import com.daniel.app.netfilx_clone.src.main.models.Top10Response;
import com.daniel.app.netfilx_clone.src.main.toptools.models.ZzimResponse;
import com.daniel.app.netfilx_clone.src.main.toptools.models.ZzimResult;

public interface MainActivityView {

    void validateSuccess(String text);

    void zzzimSuccess(ZzimResponse zzimResponse);

    void top10Success(Top10Response top10Response);

    void recommendSuccess(RecommendResponse recommendResponse);

    void netflixOriginalSuccess(NetflixOriginalResponse netflixOriginalResponse);

    void validateFailure(String message);
}
