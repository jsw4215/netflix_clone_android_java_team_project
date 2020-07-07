package com.daniel.app.netfilx_clone.src.main.interfaces;

import com.daniel.app.netfilx_clone.src.main.models.DefaultResponse;
import com.daniel.app.netfilx_clone.src.main.models.NetflixOriginalResponse;
import com.daniel.app.netfilx_clone.src.main.models.RecommendResponse;
import com.daniel.app.netfilx_clone.src.main.models.Top10Response;
import com.daniel.app.netfilx_clone.src.main.toptools.models.ZzimResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MainRetrofitInterface {

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

    @GET("/profiles/{profileId}/contents/hearts")
    Call<ZzimResponse> getZzim(@Header("x-access-token") String jwt, @Path("profileId") int profileId);

    @GET("/profiles/{profileId}/contents/top10")
    Call<Top10Response> getTop10(@Header("x-access-token") String jwt, @Path("profileId") int profileId);

    @GET("profiles/{profileId}/contents/recommend")
    Call<RecommendResponse> getRecommend(@Header("x-access-token") String jwt, @Path("profileId") int profileId);

    @GET("/profiles/{profileId}/contents/netflix/original")
    Call<NetflixOriginalResponse> getNetflixOriginal(@Header("x-access-token") String jwt, @Path("profileId") int profileId);
}
