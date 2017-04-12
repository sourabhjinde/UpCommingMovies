package com.example.android.upcomingmovies.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by S on 09/04/2017.
 */

public class MovieImages {
    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @SerializedName("file_path")
    private String filePath;
}
