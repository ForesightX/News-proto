package com.foresight.x.newsprotoapp.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// This is a Retrofit.Builder class
public class RetrofitInstance {

    private static Retrofit retrofit;
    private static final String BASE_URL = "https://newsapi.org";

    public static Retrofit getRetrofitInstance() {
        // Making sure there is only one Retrofit object at a time
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
