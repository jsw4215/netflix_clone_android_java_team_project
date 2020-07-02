package com.daniel.app.netfilx_clone.src.register;

import com.daniel.app.netfilx_clone.src.register.interfaces.RegisterActivityView;
import com.daniel.app.netfilx_clone.src.register.interfaces.RegisterRetrofitInterface;
import com.daniel.app.netfilx_clone.src.register.models.RegisterBody;
import com.daniel.app.netfilx_clone.src.register.models.RegisterPayBody;
import com.daniel.app.netfilx_clone.src.register.models.RegisterPayResponse;
import com.daniel.app.netfilx_clone.src.register.models.RegisterResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.daniel.app.netfilx_clone.src.ApplicationClass.getRetrofit;

public class RegisterPayService {

    private final RegisterActivityView mRegisterActivityView;
    private HashMap<String, Object> mParams;

    RegisterPayService(final RegisterActivityView registerActivityView, HashMap<String, Object> mParams) {
        this.mRegisterActivityView = registerActivityView;
        this.mParams = mParams;
    }

    void postRegisterPayInfo() {
        final RegisterRetrofitInterface registerRetrofitInterface = getRetrofit().create(RegisterRetrofitInterface.class);
        registerRetrofitInterface.postRegisterPayInfo(mParams).enqueue(new Callback<RegisterPayResponse>() {
            @Override
            public void onResponse(Call<RegisterPayResponse> call, Response<RegisterPayResponse> response) {
                final RegisterPayResponse registerPayResponse = response.body();
                if (registerPayResponse == null) {
                    mRegisterActivityView.validateFailure(null);
                    return;
                }

                mRegisterActivityView.validateSuccess(registerPayResponse.getMessage());
            }

            @Override
            public void onFailure(Call<RegisterPayResponse> call, Throwable t) {
                mRegisterActivityView.validateFailure(null);
            }
        });
    }
}
