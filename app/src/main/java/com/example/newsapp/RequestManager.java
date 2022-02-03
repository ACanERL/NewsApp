package com.example.newsapp;

import android.content.Context;
import android.widget.Toast;

import com.example.newsapp.Model.NewsApiResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class RequestManager {
    Context context;
    Retrofit retrofit=new Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/").addConverterFactory(GsonConverterFactory.create()).build();

    public void getNewHeadlines(OnFetchDataListener listener,String category,String query){

        CallNewsApi callNewsApi=retrofit.create(CallNewsApi.class);
        Call<NewsApiResponse>call=callNewsApi.callheadlines("us",category,query,context.getString(R.string.apikey));
        try {
            call.enqueue(new Callback<NewsApiResponse>() {
                @Override
                public void onResponse(Call<NewsApiResponse> call, Response<NewsApiResponse> response) {
                    if(!response.isSuccessful()){
                        Toast.makeText(context,"Error",Toast.LENGTH_SHORT).show();
                    }else {
                        listener.onFetchData(response.body().getArticles(),response.message());
                    }
                }

                @Override
                public void onFailure(Call<NewsApiResponse> call, Throwable t) {
                    listener.Error("Request Fail");
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public RequestManager(Context context) {
        this.context = context;
    }
    public interface CallNewsApi{
        @GET("top-headlines")
        Call<NewsApiResponse>callheadlines(
                @Query("country") String country,
                @Query("category") String category,
                @Query("q") String q,
                @Query("apiKey") String apiKey
        );
    }
}
