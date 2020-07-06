package com.daniel.app.netfilx_clone.src.main.single;

import android.util.Log;

import com.daniel.app.netfilx_clone.src.main.interfaces.MainActivityView;
import com.daniel.app.netfilx_clone.src.main.interfaces.MainRetrofitInterface;
import com.daniel.app.netfilx_clone.src.main.models.DefaultResponse;
import com.daniel.app.netfilx_clone.src.main.single.interfaces.SingleActivityView;
import com.daniel.app.netfilx_clone.src.main.single.interfaces.SingleRetrofitInterface;
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
}
