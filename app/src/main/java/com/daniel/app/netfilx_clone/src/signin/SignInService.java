package com.daniel.app.netfilx_clone.src.signin;

import com.daniel.app.netfilx_clone.src.register.interfaces.RegisterActivityView;
import com.daniel.app.netfilx_clone.src.register.interfaces.RegisterRetrofitInterface;
import com.daniel.app.netfilx_clone.src.register.models.RegisterBody;
import com.daniel.app.netfilx_clone.src.register.models.RegisterResponse;
import com.daniel.app.netfilx_clone.src.signin.interfaces.SignInActivityView;
import com.daniel.app.netfilx_clone.src.signin.interfaces.SignInRetrofitInterface;
import com.daniel.app.netfilx_clone.src.signin.models.SignInBody;
import com.daniel.app.netfilx_clone.src.signin.models.SignInResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.daniel.app.netfilx_clone.src.ApplicationClass.getRetrofit;

public class SignInService {

    private final SignInActivityView mSignInActivityView;

    SignInService(final SignInActivityView signInActivityView) {
        this.mSignInActivityView = signInActivityView;
    }

    void postSignInInfo(String email, String pw) {
        final SignInRetrofitInterface signInRetrofitInterface = getRetrofit().create(SignInRetrofitInterface.class);
        signInRetrofitInterface.postSignInInfo(new SignInBody(email, pw)).enqueue(new Callback<SignInResponse>() {
            @Override
            public void onResponse(Call<SignInResponse> call, Response<SignInResponse> response) {
                final SignInResponse signInResponse = response.body();
                if (signInResponse == null) {
                    mSignInActivityView.validateFailure(null);
                    return;
                }
                mSignInActivityView.validateSuccess(signInResponse.isSuccess(), signInResponse.getCode());
            }

            @Override
            public void onFailure(Call<SignInResponse> call, Throwable t) {
                mSignInActivityView.validateFailure(null);
            }
        });
    }

}
