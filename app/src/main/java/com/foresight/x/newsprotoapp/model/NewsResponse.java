package com.foresight.x.newsprotoapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsResponse {

    // @SerializedName is used to define the name of the field that is in the json response
    @SerializedName("status")
    private String status;
    @SerializedName("totalResults")
    private Integer totalResults;
    @SerializedName("articles")
    private List<NewsArticle> articles;


    public String getStatus() {
        return status;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public List<NewsArticle> getArticles() {
        return articles;
    }
}
