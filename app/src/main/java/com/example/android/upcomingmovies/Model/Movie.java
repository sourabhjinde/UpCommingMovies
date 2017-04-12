package com.example.android.upcomingmovies.Model;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by S on 04/04/2017.
 */

public class Movie implements Serializable {

    @SerializedName("id")
    private Integer id;
    @SerializedName("original_title")
    private String mTitle;
    @SerializedName("poster_path")
    private String mPosterPath;
    @SerializedName("backdrop_path")
    private String backdropPath;
    @SerializedName("release_date")
    private String mReleaseDate;
    @SerializedName("adult")
    private boolean mIsAdult;
    @SerializedName("overview")
    private String mOverview;
    @SerializedName("popularity")
    private float mPopularity;

    public Movie() {
    }

    public Movie(Integer id, String mTitle, String mPosterPath, String backdropPath, String mReleaseDate, boolean mIsAdult, String mOverview, float mPopularity) {
        this.id = id;
        this.mTitle = mTitle;
        this.mPosterPath = mPosterPath;
        this.backdropPath = backdropPath;
        this.mReleaseDate = mReleaseDate;
        this.mIsAdult = mIsAdult;
        this.mOverview = mOverview;
        this.mPopularity = mPopularity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmPosterPath() {
        return mPosterPath;
    }

    public void setmPosterPath(String mPosterPath) {
        this.mPosterPath = mPosterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getmReleaseDate() {
        return mReleaseDate;
    }

    public void setmReleaseDate(String mReleaseDate) {
        this.mReleaseDate = mReleaseDate;
    }

    public boolean ismIsAdult() {
        return mIsAdult;
    }

    public void setmIsAdult(boolean mIsAdult) {
        this.mIsAdult = mIsAdult;
    }

    public String getmOverview() {
        return mOverview;
    }

    public void setmOverview(String mOverview) {
        this.mOverview = mOverview;
    }

    public float getmPopularity() {
        return mPopularity;
    }

    public void setmPopularity(float mPopularity) {
        this.mPopularity = mPopularity;
    }
}
