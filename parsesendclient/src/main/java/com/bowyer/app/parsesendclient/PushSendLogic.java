package com.bowyer.app.parsesendclient;

import android.util.Log;

import java.util.Calendar;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Bowyer on 2015/08/02.
 */
public class PushSendLogic {

    public static final String TAG = PushSendLogic.class.getSimpleName();

    public interface PushSendCallBack {

        void onSuccess();

        void onFailure(String message);
    }

    private static PushSendCallBack mPushSendCallBack;

    public static void sendPush(Object model, String[] channels, PushSendCallBack callBack) {

        mPushSendCallBack = callBack;
        ParsePushDto dto = new ParsePushDto().setParsePushModel(model).setChannels(channels);
        send(dto);
    }

    public static void sendSchedulingPush(Object model, Calendar calendar, String[] channels,
            PushSendCallBack callBack) {

        mPushSendCallBack = callBack;
        ParsePushDto dto = new ParsePushDto().setParsePushModel(model).setChannels(channels)
                .setPushTime(DateUtil.getUtcTime(calendar));
        send(dto);
    }

    public static void send(ParsePushDto dto) {
        Api api = ApiCreater.getInstance();
        api.sendNotification(dto, new Callback<Api.response>() {
            @Override
            public void success(Api.response response, Response response2) {

                if (BuildConfig.DEBUG) {
                    Log.d(TAG, response2.getBody().toString());
                }

                if (mPushSendCallBack != null) {
                    mPushSendCallBack.onSuccess();
                }

            }

            @Override
            public void failure(RetrofitError error) {

                if (BuildConfig.DEBUG) {
                    Log.d(TAG, error.getMessage());
                }

                if (mPushSendCallBack != null) {
                    mPushSendCallBack.onFailure(error.getMessage());
                }

            }
        });
    }
}
