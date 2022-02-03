package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.newsapp.Adapter.CustomAdapter;
import com.example.newsapp.Model.NewsApiResponse;
import com.example.newsapp.Model.NewsHeadLines;
import com.example.newsapp.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SelectListener, View.OnClickListener {
    RecyclerView recyclerView;
    CustomAdapter adapter;
    ProgressDialog progressDialog;
    ActivityMainBinding mainBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         mainBinding=DataBindingUtil.setContentView(this,R.layout.activity_main);

        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Loading...");
        progressDialog.show();
        mainBinding.cat1.setOnClickListener(this);
        mainBinding.cat2.setOnClickListener(this);
        mainBinding.cat3.setOnClickListener(this);
        mainBinding.cat4.setOnClickListener(this);
        mainBinding.cat5.setOnClickListener(this);
        mainBinding.cat6.setOnClickListener(this);

        RequestManager requestManager=new RequestManager(this);
        requestManager.getNewHeadlines(listener,"general",null);



        mainBinding.searchBarview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                progressDialog.setTitle("Search News"+query);
                progressDialog.show();
                RequestManager requestManager=new RequestManager(MainActivity.this);
                requestManager.getNewHeadlines(listener,"general",query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private final OnFetchDataListener<NewsApiResponse>listener=new OnFetchDataListener<NewsApiResponse>() {
        @Override
        public void onFetchData(List<NewsHeadLines> list, String message) {
            if(list.isEmpty()){
                Toast.makeText(MainActivity.this,"No Data Found",Toast.LENGTH_SHORT).show();
            }else{
                showsNew(list);
                progressDialog.dismiss();
            }

        }

        @Override
        public void Error(String message) {

        }
    };

    private void showsNew(List<NewsHeadLines> list) {
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        adapter=new CustomAdapter(this,list,this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void OnNewsClicked(NewsHeadLines headLines) {
        startActivity(new Intent(MainActivity.this,DetailsActivity.class).putExtra("data",headLines));

    }

    @Override
    public void onClick(View view) {
        Button button=(Button) view;
        String category=button.getText().toString();
        progressDialog.setTitle("News Loading.."+category);
        progressDialog.show();
        RequestManager requestManager=new RequestManager(this);
        requestManager.getNewHeadlines(listener,category,null);
    }
}