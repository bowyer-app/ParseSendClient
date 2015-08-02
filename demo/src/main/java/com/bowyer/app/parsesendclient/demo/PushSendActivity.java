package com.bowyer.app.parsesendclient.demo;

import com.bowyer.app.parsesendclient.PushSendLogic;
import com.bowyer.app.parsesendclient.demo.model.ParsePushModel;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.widget.EditText;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class PushSendActivity extends ActionBarActivity {

    @InjectView(R.id.push_title)
    EditText mPushTitle;
    @InjectView(R.id.push_message)
    EditText mPushMessage;

    private final String DEF_PUSH_TITLE = "PushSendClient";
    private final String DEF_PUSH_MESSAGE = "Push send Test Message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_send);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.send_push)
    void sendPush() {
        String title = mPushTitle.getText().toString();
        String message = mPushMessage.getText().toString();

        String pushTitle = TextUtils.isEmpty(title) ? DEF_PUSH_TITLE : title;
        String pushMessage = TextUtils.isEmpty(message) ? DEF_PUSH_MESSAGE : message;

        ParsePushModel model = new ParsePushModel().setTitle(pushTitle).setMessage(pushMessage);
        String[] channel = new String[1];
        channel[0] = "demo";

        PushSendLogic.sendPush(model, channel, new PushSendLogic.PushSendCallBack() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onFailure(String message) {

            }
        });
    }
}
