package com.foresight.x.newsprotoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView

class NewsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        val intent = intent
        val urlToLoad: String? = intent.action

        if (urlToLoad != null) {
            val webView: WebView = findViewById(R.id.webView)
            webView.settings.javaScriptEnabled = true;

            webView.loadUrl(urlToLoad)
        }
    }
}