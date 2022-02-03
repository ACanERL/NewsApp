package com.example.newsapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newsapp.Model.NewsHeadLines;
import com.example.newsapp.R;
import com.example.newsapp.SelectListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private Context context;
    private List<NewsHeadLines>headLines;
    private SelectListener Listener;

    public CustomAdapter(Context context, List<NewsHeadLines> headLines,SelectListener listener) {
        this.context = context;
        this.headLines = headLines;
        this.Listener=listener;
    }

    @NonNull
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.ViewHolder holder, int position) {
        holder.txt_title.setText(headLines.get(position).getTitle());
        holder.txt_source.setText(headLines.get(position).getSource().getName());
        if(headLines.get(position).getUrlToImage()!=null){
            Picasso.get().load(headLines.get(position).getUrlToImage()).into(holder.imgheadline);
        }
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Listener.OnNewsClicked(headLines.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return headLines.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{
        TextView txt_title,txt_source;
        ImageView imgheadline;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_title=itemView.findViewById(R.id.text_title);
            txt_source=itemView.findViewById(R.id.text_source);
            imgheadline=itemView.findViewById(R.id.img_headline);
            cardView=itemView.findViewById(R.id.container);

        }
    }
}
