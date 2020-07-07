package com.daniel.app.netfilx_clone.src.main;

import android.util.Log;

import com.daniel.app.netfilx_clone.src.main.interfaces.MainActivityView;
import com.daniel.app.netfilx_clone.src.main.interfaces.MainRetrofitInterface;
import com.daniel.app.netfilx_clone.src.main.models.DefaultResponse;
import com.daniel.app.netfilx_clone.src.main.models.NetflixOriginalResponse;
import com.daniel.app.netfilx_clone.src.main.models.RecommendResponse;
import com.daniel.app.netfilx_clone.src.main.models.Top10Response;
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
                Log.d(TAG, "zzim onResponse: received.");
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

    public void getTop10(int profileId) {
        Log.d(TAG, "getTop10: "+profileId);
        final MainRetrofitInterface mainRetrofitInterface = getRetrofit().create(MainRetrofitInterface.class);
        mainRetrofitInterface.getTop10(X_ACCESS_TOKEN, profileId).enqueue(new Callback<Top10Response>() {
            @Override
            public void onResponse(Call<Top10Response> call, Response<Top10Response> response) {
                Log.d(TAG, "top10 onResponse: received.");
                final Top10Response top10Response = response.body();
                if (top10Response == null) {
                    mMainActivityView.validateFailure(null);
                    return;
                }
                Log.d(TAG, "onResponse: " + top10Response.getMessage());
                mMainActivityView.top10Success(top10Response);
            }

            @Override
            public void onFailure(Call<Top10Response> call, Throwable t) {
                mMainActivityView.validateFailure(null);
            }
        });
    }

    public void getRecommend(int profileId) {
        Log.d(TAG, "getRecommend: " + profileId);
        final MainRetrofitInterface mainRetrofitInterface = getRetrofit().create(MainRetrofitInterface.class);
        mainRetrofitInterface.getRecommend(X_ACCESS_TOKEN, profileId).enqueue(new Callback<RecommendResponse>() {
            @Override
            public void onResponse(Call<RecommendResponse> call, Response<RecommendResponse> response) {
                Log.d(TAG, "recomm onResponse: received.");
                final RecommendResponse recommendResponse = response.body();
                if (recommendResponse == null) {
                    mMainActivityView.validateFailure(null);
                    return;
                }
                Log.d(TAG, "onResponse: " + recommendResponse.getMessage());
                mMainActivityView.recommendSuccess(recommendResponse);
            }

            @Override
            public void onFailure(Call<RecommendResponse> call, Throwable t) {
                mMainActivityView.validateFailure(null);
            }
        });
    }

    public void getNetflixOriginal(int profileId) {
        Log.d(TAG, "getNetflixOriginal: ");
        final MainRetrofitInterface mainRetrofitInterface = getRetrofit().create(MainRetrofitInterface.class);
        mainRetrofitInterface.getNetflixOriginal(X_ACCESS_TOKEN, profileId).enqueue(new Callback<NetflixOriginalResponse>() {
            @Override
            public void onResponse(Call<NetflixOriginalResponse> call, Response<NetflixOriginalResponse> response) {
                Log.d(TAG, "netflix origin onResponse: received.");
                final NetflixOriginalResponse netflixOriginalResponse = response.body();
                if (netflixOriginalResponse == null) {
                    mMainActivityView.validateFailure(null);
                    return;
                }
                Log.d(TAG, "onResponse: " + netflixOriginalResponse.getMessage());
                mMainActivityView.netflixOriginalSuccess(netflixOriginalResponse);
            }

            @Override
            public void onFailure(Call<NetflixOriginalResponse> call, Throwable t) {
                mMainActivityView.validateFailure(null);
            }
        });
    }

}
