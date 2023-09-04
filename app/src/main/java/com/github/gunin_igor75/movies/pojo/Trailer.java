package com.github.gunin_igor75.movies.pojo;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Trailer implements Serializable {
    @SerializedName("url")
    private String url;
    @SerializedName("name")
    private String name;


    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public Trailer(String url, String name) {
        this.url = url;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Trailer{" +
                "url='" + url + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
