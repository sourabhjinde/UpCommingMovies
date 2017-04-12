package com.example.android.upcomingmovies.Rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by S on 07/04/2017.
 */

public class ApiClient {
    public static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static Retrofit mRetrofit = null;


    public static Retrofit getClient() {
        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return mRetrofit;
    }
}
