package com.github.gunin_igor75.movies.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReviewResponse {

    @SerializedName("docs")
    private List<Review> reviews;

    public ReviewResponse(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    @Override
    public String toString() {
        return "ReviewResponse{" +
                "reviews=" + reviews +
                '}';
    }
}
