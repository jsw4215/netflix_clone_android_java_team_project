package com.daniel.app.netfilx_clone.config;

import android.util.Log;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import static com.daniel.app.netfilx_clone.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.daniel.app.netfilx_clone.src.ApplicationClass.sSharedPreferences;

public class XAccessTokenInterceptor implements Interceptor {

    private static final String TAG = "XAccessTokenInterceptor";

    @Override
    @NonNull
    public Response intercept(@NonNull final Interceptor.Chain chain) throws IOException {

        final Request.Builder builder = chain.request().newBuilder();

        final String jwtToken = sSharedPreferences.getString(X_ACCESS_TOKEN, null);

        if (jwtToken != null) {
            builder.addHeader("X-ACCESS-TOKEN", jwtToken);
        }

        return chain.proceed(builder.build());
    }
}
