package com.github.gunin_igor75.movies.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Videos implements Serializable {

    @SerializedName("trailers")
    private List<Trailer> trailers;


    public List<Trailer> getTrailers() {
        return trailers;
    }

    public Videos(List<Trailer> trailers) {
        this.trailers = trailers;
    }

    @Override
    public String toString() {
        return "Videos{" +
                "trailers=" + trailers +
                '}';
    }
}
