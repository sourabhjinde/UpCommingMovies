package com.example.android.upcomingmovies.Rest;

/**
 * Created by S on 07/04/2017.
 */

import retrofit2.Call;
import retrofit2.http.GET;

import com.example.android.upcomingmovies.Model.MovieImagesResponse;
import com.example.android.upcomingmovies.Model.MoviesResponse;

import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiInterface {
    @GET("movie/upcoming")
    Call<MoviesResponse> getUpComingMovies(@Query("api_key") String apiKey);

    @GET("movie/{id}/images")
    Call<MovieImagesResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);
}