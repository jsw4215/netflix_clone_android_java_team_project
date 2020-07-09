package com.daniel.app.netfilx_clone.src.search.interfaces;

import com.daniel.app.netfilx_clone.src.main.models.NetflixOriginalResponse;
import com.daniel.app.netfilx_clone.src.main.models.RecommendResponse;
import com.daniel.app.netfilx_clone.src.main.models.Top10Response;
import com.daniel.app.netfilx_clone.src.main.toptools.models.ZzimResponse;
import com.daniel.app.netfilx_clone.src.search.models.SearchPopularResponse;
import com.daniel.app.netfilx_clone.src.search.models.SearchPopularResult;
import com.daniel.app.netfilx_clone.src.search.models.SearchResponse;
import com.daniel.app.netfilx_clone.src.search.models.SearchResult;

public interface SearchActivityView {

    void validateSuccess(String text);

    void validateFailure(String message);

    void searchPopularSuccess(SearchPopularResponse searchPopularResponse);

    void searchSuccess(SearchResponse searchResponse);
}
