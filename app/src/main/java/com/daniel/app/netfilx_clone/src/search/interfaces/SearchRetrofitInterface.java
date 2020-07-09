package com.daniel.app.netfilx_clone.src.search.interfaces;

import com.daniel.app.netfilx_clone.src.main.models.DefaultResponse;
import com.daniel.app.netfilx_clone.src.main.models.NetflixOriginalResponse;
import com.daniel.app.netfilx_clone.src.main.models.RecommendResponse;
import com.daniel.app.netfilx_clone.src.main.models.Top10Response;
import com.daniel.app.netfilx_clone.src.main.toptools.models.ZzimResponse;
import com.daniel.app.netfilx_clone.src.search.models.SearchBody;
import com.daniel.app.netfilx_clone.src.search.models.SearchPopularResponse;
import com.daniel.app.netfilx_clone.src.search.models.SearchResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SearchRetrofitInterface {

    @GET("/profiles/{profileId}/contents/popular/search")
    Call<SearchPopularResponse> getPopularSearch(@Header("x-access-token") String jwt, @Path("profileId") int profileId);

    @GET("/profiles/{profileId}/contents/search")
    Call<SearchResponse> getSearch(@Header("x-access-token") String jwt, @Path("profileId") int profileId, @Query("searchKeyword") String searchKeyword);

    @GET("/profiles/{profileId}/contents/search")
    Call<SearchResponse> getSearchContents(@Header("x-access-token") String jwt, @Path("profileId") int profileId, @Query("searchKeyword") String searchKeyword, @Query("searchStatus") SearchBody searchBody);
}
