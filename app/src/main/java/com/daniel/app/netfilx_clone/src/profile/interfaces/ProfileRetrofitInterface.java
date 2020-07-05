package com.daniel.app.netfilx_clone.src.profile.interfaces;

import com.daniel.app.netfilx_clone.src.main.models.DefaultResponse;
import com.daniel.app.netfilx_clone.src.profile.models.ProfileAddResponse;
import com.daniel.app.netfilx_clone.src.profile.models.ProfileBody;
import com.daniel.app.netfilx_clone.src.profile.models.ProfileResponse;
import com.daniel.app.netfilx_clone.src.signin.models.SignInBody;
import com.daniel.app.netfilx_clone.src.signin.models.SignInResponse;

import java.lang.ref.Reference;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

import static com.daniel.app.netfilx_clone.src.ApplicationClass.X_ACCESS_TOKEN;

public interface ProfileRetrofitInterface {

    //    @GET("/test")
    @GET("/jwt")
    Call<ProfileResponse> getTest();

    @GET("/test/{number}")
    Call<ProfileResponse> getTestPathAndQuery(
            @Path("number") int number,
            @Query("content") final String content
    );

    @GET("/profiles")
    Call<ProfileResponse> getProfiles(@Header("x-access-token") String jwt);

    @POST("/profiles")
    Call<ProfileAddResponse> postProfile(@Header("x-access-token") String jwt, @Body ProfileBody profileBody);

    @DELETE("/profiles/{profileId}")
    Call<ProfileAddResponse> deleteProfile(@Header("x-access-token") String jwt, @Path("profileId") int Id);

    @PATCH("/profiles/{profileId}")
    Call<ProfileAddResponse> modifyProfile(@Header("x-access-token") String jwt, @Path("profileId") int Id, @Body ProfileBody profileBody);

}
