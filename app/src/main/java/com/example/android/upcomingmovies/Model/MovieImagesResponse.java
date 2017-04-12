package com.example.android.upcomingmovies.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by S on 09/04/2017.
 */

public class MovieImagesResponse {
    @SerializedName("id")
    private Integer id;
    @SerializedName("backdrops")
    private List<MovieImages> movieImagesList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<MovieImages> getMovieImagesList() {
        return movieImagesList;
    }

    public void setMovieImagesList(List<MovieImages> movieImagesList) {
        this.movieImagesList = movieImagesList;
    }
}
