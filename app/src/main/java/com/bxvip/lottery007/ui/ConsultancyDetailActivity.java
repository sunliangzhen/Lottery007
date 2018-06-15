package com.bxvip.lottery007.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bxvip.lottery007.R;
import com.bxvip.lottery007.base.BaseAppCompatActivity;

public class ConsultancyDetailActivity extends BaseAppCompatActivity {

    ProgressBar pb_index;
    WebView wv_consultancy_result;
    ImageView iv_consultancydetail_back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        setContentView(R.layout.activity_consultancy_detail);
        pb_index = (ProgressBar) findViewById(R.id.pb_index);
        wv_consultancy_result = (WebView) findViewById(R.id.wv_consultancy_result);
        iv_consultancydetail_back = (ImageView) findViewById(R.id.iv_consultancydetail_back);
        iv_consultancydetail_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        wv_consultancy_result.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        } );
        wv_consultancy_result.getSettings().setJavaScriptEnabled(true); //支持js
        wv_consultancy_result.getSettings().setUseWideViewPort(true);
        wv_consultancy_result.getSettings().setLoadWithOverviewMode(true);
        wv_consultancy_result.getSettings().setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        wv_consultancy_result.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    pb_index.setVisibility(View.GONE);
                } else {
                    if (pb_index.getVisibility() == View.GONE) {
                        pb_index.setVisibility(View.VISIBLE);
                    }
                    pb_index.setProgress(newProgress);
                }
                super.onProgressChanged(view, newProgress);
            }
        });
        wv_consultancy_result.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        wv_consultancy_result.loadUrl(url);
    }
}
