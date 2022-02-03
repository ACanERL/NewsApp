package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newsapp.Model.NewsHeadLines;
import com.example.newsapp.databinding.ActivityDetailsBinding;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {
    NewsHeadLines headLines;
    ActivityDetailsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_details);

        headLines= (NewsHeadLines) getIntent().getSerializableExtra("data");

        binding.detailTitleTxt.setText(headLines.getTitle());
        binding.textDetailsAuthor.setText(headLines.getAuthor());
        binding.textDetailsTime.setText(headLines.getPublishedAt());
        binding.textDetailsDetail.setText(headLines.getDescription());
        Picasso.get().load(headLines.getUrlToImage()).into(binding.imgDetailNews);

    }
}