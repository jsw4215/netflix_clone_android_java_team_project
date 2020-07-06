package com.daniel.app.netfilx_clone.src.profile.profile_selection;

import android.util.Log;

import com.daniel.app.netfilx_clone.src.profile.profile_selection.interfaces.ProfileSelectionActivityView;
import com.daniel.app.netfilx_clone.src.profile.profile_selection.interfaces.ProfileSelectionRetrofitInterface;
import com.daniel.app.netfilx_clone.src.profile.profile_selection.models.ProfileSelectionResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.daniel.app.netfilx_clone.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.daniel.app.netfilx_clone.src.ApplicationClass.getRetrofit;

public class ProfileSelectionService {
    private static final String TAG = "ProfileSelectionService";

    private final ProfileSelectionActivityView mProfileSelectionActivityView;

    ProfileSelectionService(final ProfileSelectionActivityView profileSelectionActivityView) {
        this.mProfileSelectionActivityView = profileSelectionActivityView;
    }

    void getProfileImg() {
        Log.d(TAG, "getProfiles: started");
        final ProfileSelectionRetrofitInterface profileSelectionRetrofitInterface = getRetrofit().create(ProfileSelectionRetrofitInterface.class);
        profileSelectionRetrofitInterface.getProfileImg(X_ACCESS_TOKEN).enqueue(new Callback<ProfileSelectionResponse>() {
            @Override
            public void onResponse(Call<ProfileSelectionResponse> call, Response<ProfileSelectionResponse> response) {
                Log.d(TAG, "onResponse: get response.");
                final ProfileSelectionResponse profileSelectionResponse = response.body();
                if (profileSelectionResponse == null) {
                    Log.d(TAG, "onResponse: null");
                    mProfileSelectionActivityView.validateFailure(null);
                    return;
                }
                if(profileSelectionResponse.isSuccess()){
                    Log.d(TAG, "validateSuccess: " + profileSelectionResponse.isSuccess());
                    if(profileSelectionResponse.getCode()==100){
                        Log.d(TAG, "onResponse: " + profileSelectionResponse.getCode());
                        mProfileSelectionActivityView.validateSuccess(profileSelectionResponse.getResults());
                    }
                }
            }

            @Override
            public void onFailure(Call<ProfileSelectionResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: ");

                mProfileSelectionActivityView.validateFailure(null);
            }

        });
    }

}
