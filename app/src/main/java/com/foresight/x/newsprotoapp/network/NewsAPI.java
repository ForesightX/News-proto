package com.foresight.x.newsprotoapp.network;

import com.foresight.x.newsprotoapp.model.NewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/** Creating the REST API for Retrofit via this interface*/
public interface NewsAPI {

    @GET("/v2/top-headlines?country=ng&apiKey=40743f59597d4b12b85d4ed930c71e09")
    Call<NewsResponse> getHeadlines();
}
