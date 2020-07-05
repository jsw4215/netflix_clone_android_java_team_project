package com.daniel.app.netfilx_clone.src.profile.profile_selection.interfaces;

import com.daniel.app.netfilx_clone.src.profile.models.ProfileResponse;
import com.daniel.app.netfilx_clone.src.profile.profile_selection.models.ProfileSelectionResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ProfileSelectionRetrofitInterface {

    //    @GET("/test")
    @GET("/jwt")
    Call<ProfileResponse> getTest();

    @GET("/test/{number}")
    Call<ProfileResponse> getTestPathAndQuery(
            @Path("number") int number,
            @Query("content") final String content
    );

    @GET("/profiles/images")
    Call<ProfileSelectionResponse> getProfileImg(@Header("x-access-token") String jwt);

}
