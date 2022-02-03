package com.example.newsapp;

import com.example.newsapp.Model.NewsHeadLines;

import java.util.List;

public interface OnFetchDataListener<NewsApiResponse> {
    void onFetchData(List<NewsHeadLines>list,String message);
    void Error(String message);
}
