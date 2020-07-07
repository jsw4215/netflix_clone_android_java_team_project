package com.daniel.app.netfilx_clone.src.main.single.interfaces;

import com.daniel.app.netfilx_clone.src.main.models.DefaultResponse;
import com.daniel.app.netfilx_clone.src.main.single.models.EvaluateBody;
import com.daniel.app.netfilx_clone.src.main.single.models.EvaluateResponse;
import com.daniel.app.netfilx_clone.src.main.single.models.HeartResponse;
import com.daniel.app.netfilx_clone.src.main.single.models.SingleResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SingleRetrofitInterface {

    @GET("/test/{number}")
    Call<DefaultResponse> getTestPathAndQuery(
            @Path("number") int number,
            @Query("content") final String content
    );

    @GET("/profiles/{profileId}/contents/{contentsId}")
    Call<SingleResponse> getSingleInfo(@Header("x-access-token") String jwt, @Path("profileId") int profileId, @Path("contentsId") int contentsId);

    @POST("/profiles/{profileId}/contents/{contentsId}/eval")
    Call<EvaluateResponse> postEvaluate(@Header("x-access-token") String jwt, @Path("profileId") int profileId, @Path("contentsId") int contentsId, @Body EvaluateBody evaluateBody);

    @POST("/profiles/{profileId}/contents/{contentsId}/heart")
    Call<HeartResponse> postHeart(@Header("x-access-token") String jwt, @Path("profileId") int profileId, @Path("contentsId") int contentsId);

}
