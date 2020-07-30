package com.foresight.x.newsprotoapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.foresight.x.newsprotoapp.R;
import com.foresight.x.newsprotoapp.model.NewsArticle;
import com.foresight.x.newsprotoapp.model.NewsResponse;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private Context context;
    private NewsArticleClickListener listener;
    private List<NewsArticle> articles;

    public NewsAdapter(Context context, NewsArticleClickListener listener, List<NewsArticle> articles) {
        this.context = context;
        this.listener = listener;
        this.articles = articles;
    }

    public interface NewsArticleClickListener {
        void onNewsClick(String url);
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_layout, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        String title = articles.get(position).getTitle();
        String urlToImage = articles.get(position).getUrlToImage();
        holder.tvNewsTitle.setText(title);

        if (urlToImage != null && !urlToImage.equals("")) {
            Picasso.get().load(urlToImage).into(holder.ivNewsImage);
        }

    }

    @Override
    public int getItemCount() {
        return articles == null ? 0 : articles.size();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView ivNewsImage;
        TextView tvNewsTitle;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            ivNewsImage = itemView.findViewById(R.id.ivNewsImage);
            tvNewsTitle = itemView.findViewById(R.id.tvNewsTitle);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onNewsClick(articles.get(getAdapterPosition()).getUrl());
        }
    }
}
