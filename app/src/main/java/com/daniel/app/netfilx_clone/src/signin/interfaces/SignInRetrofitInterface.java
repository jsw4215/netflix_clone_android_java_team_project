package com.daniel.app.netfilx_clone.src.signin.interfaces;

import com.daniel.app.netfilx_clone.src.main.models.DefaultResponse;
import com.daniel.app.netfilx_clone.src.register.models.RegisterBody;
import com.daniel.app.netfilx_clone.src.register.models.RegisterResponse;
import com.daniel.app.netfilx_clone.src.signin.models.SignInBody;
import com.daniel.app.netfilx_clone.src.signin.models.SignInResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SignInRetrofitInterface {
//    @GET("/test")
    @GET("/jwt")
    Call<DefaultResponse> getTest();

    @GET("/test/{number}")
    Call<DefaultResponse> getTestPathAndQuery(
            @Path("number") int number,
            @Query("content") final String content
    );

    @POST("/test")
    Call<DefaultResponse> postTest(@Body RequestBody params);

    @POST("/login")
    Call<SignInResponse> postSignInInfo(@Body SignInBody signInBody);
}
