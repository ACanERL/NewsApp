package com.example.newsapp.Model;

import java.io.Serializable;
import java.util.List;

public class NewsApiResponse implements Serializable {
    String status;
    int TotalResults;
    List<NewsHeadLines>articles;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResults() {
        return TotalResults;
    }

    public void setTotalResults(int totalResults) {
        TotalResults = totalResults;
    }

    public List<NewsHeadLines> getArticles() {
        return articles;
    }

    public void setArticles(List<NewsHeadLines> articles) {
        this.articles = articles;
    }
}
