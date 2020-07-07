package com.daniel.app.netfilx_clone.src.main;

import android.util.Log;

import com.daniel.app.netfilx_clone.src.main.interfaces.MainActivityView;
import com.daniel.app.netfilx_clone.src.main.interfaces.MainRetrofitInterface;
import com.daniel.app.netfilx_clone.src.main.models.DefaultResponse;
import com.daniel.app.netfilx_clone.src.main.toptools.models.ZzimResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.daniel.app.netfilx_clone.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.daniel.app.netfilx_clone.src.ApplicationClass.getRetrofit;

public class MainService {
    private final MainActivityView mMainActivityView;
    private static final String TAG = "MainService";

    public MainService(final MainActivityView mainActivityView) {
        this.mMainActivityView = mainActivityView;
    }

    void getTest() {
        final MainRetrofitInterface mainRetrofitInterface = getRetrofit().create(MainRetrofitInterface.class);
        mainRetrofitInterface.getTest().enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                final DefaultResponse defaultResponse = response.body();
                if (defaultResponse == null) {
                    mMainActivityView.validateFailure(null);
                    return;
                }

                mMainActivityView.validateSuccess(defaultResponse.getMessage());
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                mMainActivityView.validateFailure(null);
            }
        });
    }

    public void getZzim(int profileId) {
        Log.d(TAG, "getZzim: " + profileId);
        final MainRetrofitInterface mainRetrofitInterface = getRetrofit().create(MainRetrofitInterface.class);
        mainRetrofitInterface.getZzim(X_ACCESS_TOKEN, profileId).enqueue(new Callback<ZzimResponse>() {
            @Override
            public void onResponse(Call<ZzimResponse> call, Response<ZzimResponse> response) {
                Log.d(TAG, "onResponse: received.");
                final ZzimResponse zzimResponse = response.body();
                if (zzimResponse == null) {
                    mMainActivityView.validateFailure(null);
                    return;
                }
                Log.d(TAG, "onResponse: " + zzimResponse.getMessage());
                mMainActivityView.zzzimSuccess(zzimResponse);
            }

            @Override
            public void onFailure(Call<ZzimResponse> call, Throwable t) {
                mMainActivityView.validateFailure(null);
            }
        });
    }


}
