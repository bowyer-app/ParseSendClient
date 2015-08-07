package com.bowyer.app.parsesendclient.demo;

import com.google.gson.Gson;

import com.bowyer.app.parsesendclient.demo.model.ParsePushModel;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MainActivity extends ActionBarActivity {

    final String KEY = "com.parse.Data";

    @InjectView(R.id.push_title)
    TextView mTitle;

    @InjectView(R.id.push_message)
    TextView mMessage;

    @InjectView(R.id.push_url)
    TextView mUrl;

    @InjectView(R.id.push_detail)
    LinearLayout mPushDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        initData();
    }

    private void initData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
        String data = bundle.getString(KEY);
        if (TextUtils.isEmpty(data)) {
            return;
        }
        ParsePushModel model = new Gson().fromJson(data, ParsePushModel.class);
        mTitle.setText(model.getTitle());
        mMessage.setText(model.getMessage());
        mPushDetail.setVisibility(View.VISIBLE);
        String url = model.getUrl();

        if (TextUtils.isEmpty(url)) {
            return;
        }
        mUrl.setText(url);
        WebViewActivity.startWebActivity(this, url);
    }

}
