package com.daniel.app.netfilx_clone.src.register;

import com.daniel.app.netfilx_clone.src.register.interfaces.RegisterActivityView;
import com.daniel.app.netfilx_clone.src.register.interfaces.RegisterRetrofitInterface;
import com.daniel.app.netfilx_clone.src.register.models.RegisterBody;
import com.daniel.app.netfilx_clone.src.register.models.RegisterResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.daniel.app.netfilx_clone.src.ApplicationClass.getRetrofit;

public class RegisterService {

    private final RegisterActivityView mRegisterActivityView;

    RegisterService(final RegisterActivityView registerActivityView) {
        this.mRegisterActivityView = registerActivityView;
    }

    void postRegisterInfo(String email, String pw) {
        final RegisterRetrofitInterface registerRetrofitInterface = getRetrofit().create(RegisterRetrofitInterface.class);
        registerRetrofitInterface.postRegisterInfo(new RegisterBody(email, pw)).enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                final RegisterResponse registerResponse = response.body();
                if (registerResponse == null) {
                    mRegisterActivityView.validateFailure(null);
                    return;
                }

                mRegisterActivityView.validateSuccess(registerResponse.getJwt());
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                mRegisterActivityView.validateFailure(null);
            }
        });
    }

}
