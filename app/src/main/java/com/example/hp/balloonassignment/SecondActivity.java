package com.example.hp.balloonassignment;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by hp on 03-08-2018.
 */

public class SecondActivity extends MainActivity {
    String url;
    static WebView webView;
    String document;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent i = getIntent();
        url = i.getStringExtra("url");
        document = i.getStringExtra("document");

        webView = (WebView) findViewById(R.id.webview);

        webView.loadDataWithBaseURL(url , document , "text/html" , "utf-8" , "");
        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view , WebResourceRequest request){
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view , request);
            }
        });

    }

    @Override
    public void onBackPressed(){
        Intent i = new Intent(SecondActivity.this , MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }
}
