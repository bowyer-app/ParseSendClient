package com.bowyer.app.parsesendclient.demo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Bowyer on 15/08/07.
 */
public class WebViewActivity extends ActionBarActivity
        implements SwipeRefreshLayout.OnRefreshListener {

    public static final String KEY_URL = "key_url";

    @InjectView(R.id.pull_reflesh)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @InjectView(R.id.web_view)
    WebView mWebView;

    @InjectView(R.id.progress_bar)
    ProgressBar mProgressBar;

    String mUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.inject(this);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        initWebView();
    }

    private void initWebView() {
        Intent intent = getIntent();
        mUrl = intent.getExtras().getString(KEY_URL);
        mWebView.loadUrl(mUrl);
        mWebView.setWebViewClient(new CustomWebViewClient());
        mWebView.getSettings().setJavaScriptEnabled(true);
    }

    @Override
    public void onRefresh() {
        mWebView.reload();
    }

    public static void startWebActivity(Context context, String url) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra(KEY_URL, url);
        context.startActivity(intent);
    }

    class CustomWebViewClient extends WebViewClient {

        @Override
        public void onPageStarted(final WebView view, final String url, final Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            if (mProgressBar != null) {
                mProgressBar.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageFinished(final WebView view, final String url) {
            super.onPageFinished(view, url);

            if (mProgressBar != null) {
                mProgressBar.setVisibility(View.GONE);
            }

            if (mSwipeRefreshLayout != null) {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }
    }
}
