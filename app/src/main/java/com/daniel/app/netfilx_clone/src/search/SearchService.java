package com.daniel.app.netfilx_clone.src.search;

import android.util.Log;

import com.daniel.app.netfilx_clone.src.main.interfaces.MainActivityView;
import com.daniel.app.netfilx_clone.src.main.interfaces.MainRetrofitInterface;
import com.daniel.app.netfilx_clone.src.main.models.DefaultResponse;
import com.daniel.app.netfilx_clone.src.main.models.NetflixOriginalResponse;
import com.daniel.app.netfilx_clone.src.main.models.RecommendResponse;
import com.daniel.app.netfilx_clone.src.main.models.Top10Response;
import com.daniel.app.netfilx_clone.src.main.toptools.models.ZzimResponse;
import com.daniel.app.netfilx_clone.src.search.interfaces.SearchActivityView;
import com.daniel.app.netfilx_clone.src.search.interfaces.SearchRetrofitInterface;
import com.daniel.app.netfilx_clone.src.search.models.SearchBody;
import com.daniel.app.netfilx_clone.src.search.models.SearchPopularResponse;
import com.daniel.app.netfilx_clone.src.search.models.SearchResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.daniel.app.netfilx_clone.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.daniel.app.netfilx_clone.src.ApplicationClass.getRetrofit;
import static com.daniel.app.netfilx_clone.src.ApplicationClass.sSharedPreferences;

public class SearchService {
    private final SearchActivityView mSearchActivityView;
    private static final String TAG = "MainService";

    public SearchService(final SearchActivityView searchActivityView) {
        this.mSearchActivityView = searchActivityView;
    }

    void getPopularSearch(){
        int profileId = Integer.parseInt(sSharedPreferences.getString("profileId", String.valueOf(-1)));
        final SearchRetrofitInterface searchRetrofitInterface = getRetrofit().create(SearchRetrofitInterface.class);
        searchRetrofitInterface.getPopularSearch(X_ACCESS_TOKEN,profileId).enqueue(new Callback<SearchPopularResponse>() {
            @Override
            public void onResponse(Call<SearchPopularResponse> call, Response<SearchPopularResponse> response) {
                final SearchPopularResponse searchPopularResponse = response.body();
                if (searchPopularResponse == null) {
                    mSearchActivityView.validateFailure(null);
                    return;
                }

                mSearchActivityView.searchPopularSuccess(searchPopularResponse);
            }

            @Override
            public void onFailure(Call<SearchPopularResponse> call, Throwable t) {
                mSearchActivityView.validateFailure(null);
            }
        });
    }

    void getSearch(String searchWord){
        Log.d(TAG, "getSearch: " + searchWord);
        int profileId = Integer.parseInt(sSharedPreferences.getString("profileId", String.valueOf(-1)));
        final SearchRetrofitInterface searchRetrofitInterface = getRetrofit().create(SearchRetrofitInterface.class);
        searchRetrofitInterface.getSearch(X_ACCESS_TOKEN,profileId,searchWord).enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                final SearchResponse searchResponse = response.body();
                if (searchResponse == null) {
                    mSearchActivityView.validateFailure(null);
                    return;
                }
                if(searchResponse.getResult() != null) {
                    mSearchActivityView.searchSuccess(searchResponse);
                }else{
                    mSearchActivityView.validateFailure(searchResponse.getMessage());
                }
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                mSearchActivityView.validateFailure(null);
            }
        });
    }

    void getSearchContents(String searchWord){
        SearchBody searchBody = new SearchBody();
        searchBody.setSearchStatus("Y");
        int profileId = Integer.parseInt(sSharedPreferences.getString("profileId", String.valueOf(-1)));
        Log.d(TAG, "getSearchContents: body" + profileId + searchWord + searchBody.getSearchStatus());
        final SearchRetrofitInterface searchRetrofitInterface = getRetrofit().create(SearchRetrofitInterface.class);
        searchRetrofitInterface.getSearchContents(X_ACCESS_TOKEN, profileId, searchWord, searchBody).enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                final SearchResponse searchResponse = response.body();
                if (searchResponse == null) {
                    mSearchActivityView.validateFailure(null);
                    return;
                }
                if(searchResponse.getResult() != null) {
                    Log.d(TAG, "onResponse: getSearchContents.");
                    mSearchActivityView.searchSuccess(searchResponse);
                }else{
                    mSearchActivityView.validateFailure(searchResponse.getMessage());
                }
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                mSearchActivityView.validateFailure(null);
            }
        });
    }

}
