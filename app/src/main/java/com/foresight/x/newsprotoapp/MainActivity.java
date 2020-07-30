package com.foresight.x.newsprotoapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.foresight.x.newsprotoapp.adapter.NewsAdapter;
import com.foresight.x.newsprotoapp.model.NewsResponse;
import com.foresight.x.newsprotoapp.network.NewsAPI;
import com.foresight.x.newsprotoapp.network.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements NewsAdapter.NewsArticleClickListener {

    private NewsAdapter adapter;
    private RecyclerView recyclerView;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Showing a ProgressDialog till the data become available
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        /*Create handle for the RetrofitInstance interface*/
        NewsAPI service = RetrofitInstance.getRetrofitInstance().create(NewsAPI.class);
        Call<NewsResponse> call = service.getHeadlines();

        // This method is performed asynchronously
        call.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                progressDialog.dismiss();
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateDataList(NewsResponse newsResponse) {
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new NewsAdapter(this, this, newsResponse.getArticles());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        DividerItemDecoration decoration = new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL);
        recyclerView.addItemDecoration(decoration);
    }

    @Override
    public void onNewsClick(String url) {
        Intent intent = new Intent(this, NewsActivity.class);
        intent.setAction(url);
        startActivity(intent);
    }
}