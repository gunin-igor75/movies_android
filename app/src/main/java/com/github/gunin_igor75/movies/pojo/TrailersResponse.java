package com.github.gunin_igor75.movies.pojo;

import com.google.gson.annotations.SerializedName;

public class TrailersResponse {

    @SerializedName("videos")
    private Videos videos;

    public Videos getVideos() {
        return videos;
    }

    public TrailersResponse(Videos videos) {
        this.videos = videos;
    }

    @Override
    public String toString() {
        return "TrailersResponse{" +
                "videos=" + videos +
                '}';
    }
}
