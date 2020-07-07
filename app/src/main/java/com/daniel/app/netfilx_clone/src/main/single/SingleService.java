package com.daniel.app.netfilx_clone.src.main.single;

import android.util.Log;

import com.daniel.app.netfilx_clone.src.main.interfaces.MainActivityView;
import com.daniel.app.netfilx_clone.src.main.interfaces.MainRetrofitInterface;
import com.daniel.app.netfilx_clone.src.main.models.DefaultResponse;
import com.daniel.app.netfilx_clone.src.main.single.interfaces.SingleActivityView;
import com.daniel.app.netfilx_clone.src.main.single.interfaces.SingleRetrofitInterface;
import com.daniel.app.netfilx_clone.src.main.single.models.EvaluateBody;
import com.daniel.app.netfilx_clone.src.main.single.models.EvaluateResponse;
import com.daniel.app.netfilx_clone.src.main.single.models.HeartResponse;
import com.daniel.app.netfilx_clone.src.main.single.models.SingleResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.daniel.app.netfilx_clone.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.daniel.app.netfilx_clone.src.ApplicationClass.getRetrofit;
import static com.daniel.app.netfilx_clone.src.ApplicationClass.sSharedPreferences;

class SingleService {

    private static final String TAG = "SingleService";
    
    private final SingleActivityView mSingleActivityView;

    SingleService(final SingleActivityView singleActivityView) {
        this.mSingleActivityView = singleActivityView;
    }

    void getSingleInfo(int profileId ,int contentsId) {
        Log.d(TAG, "getSingleInfo: started." + sSharedPreferences.getString(X_ACCESS_TOKEN,"") + "and" + profileId + " and " + contentsId);
        final SingleRetrofitInterface singleRetrofitInterface = getRetrofit().create(SingleRetrofitInterface.class);
        Log.d(TAG, "getSingleInfo: started." + profileId + " and " + contentsId);
        singleRetrofitInterface.getSingleInfo(X_ACCESS_TOKEN, profileId, contentsId).enqueue(new Callback<SingleResponse>() {
            @Override
            public void onResponse(Call<SingleResponse> call, Response<SingleResponse> response) {
                Log.d(TAG, "onResponse: received.");
                final SingleResponse singleResponse = response.body();
                if (singleResponse == null) {
                    Log.d(TAG, "onResponse: null");
                    mSingleActivityView.validateFailure(null);
                    return;
                }
                Log.d(TAG, "onResponse: success");
                mSingleActivityView.validateSuccess(singleResponse);
            }

            @Override
            public void onFailure(Call<SingleResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
                mSingleActivityView.validateFailure(null);
            }
        });
    }

    void postEvaluate(int profileId,int contentsId, String choice) {
        Log.d(TAG, "postEvaluate: " + choice);
        final SingleRetrofitInterface singleRetrofitInterface = getRetrofit().create(SingleRetrofitInterface.class);
        singleRetrofitInterface.postEvaluate(X_ACCESS_TOKEN, profileId, contentsId, new EvaluateBody(choice)).enqueue(new Callback<EvaluateResponse>() {
            @Override
            public void onResponse(Call<EvaluateResponse> call, Response<EvaluateResponse> response) {
                Log.d(TAG, "onResponse: received." + response.body().getMessage() + response.body().getResult().getEvalStatus());
                final EvaluateResponse evaluateResponse = response.body();
                if (evaluateResponse == null) {
                    Log.d(TAG, "onResponse: null");
                    mSingleActivityView.validateFailure(null);
                    return;
                }
                if(evaluateResponse.isSuccess()){
                Log.d(TAG, "onResponse: success");
                mSingleActivityView.evaluateSuccess(evaluateResponse);}
            }

            @Override
            public void onFailure(Call<EvaluateResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
                mSingleActivityView.validateFailure(null);
            }
        });
    }

    void postHeart(int profileId,int contentsId) {
        final SingleRetrofitInterface singleRetrofitInterface = getRetrofit().create(SingleRetrofitInterface.class);
        singleRetrofitInterface.postHeart(X_ACCESS_TOKEN, profileId, contentsId).enqueue(new Callback<HeartResponse>() {
            @Override
            public void onResponse(Call<HeartResponse> call, Response<HeartResponse> response) {
                Log.d(TAG, "onResponse: ");
                final HeartResponse heartResponse = response.body();
                if (heartResponse == null) {
                    Log.d(TAG, "onResponse: null");
                    mSingleActivityView.validateFailure(null);
                    return;
                }
                if (heartResponse.isSuccess()) {
                    Log.d(TAG, "onResponse: success");
                    mSingleActivityView.heartSuccess(heartResponse);
                }
            }
            @Override
            public void onFailure(Call<HeartResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
                mSingleActivityView.validateFailure(null);
            }
        });
    }


}
