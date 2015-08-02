package com.bowyer.app.parsesendclient;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.Headers;
import retrofit.http.POST;

/**
 * Created by Bowyer on 2015/08/02.
 */
public interface Api {

    class response {

        public String result;
    }

    @Headers({
            "Content-Type: application/json"
    })
    @POST("/1/push")
    void sendNotification(@Body ParsePushDto parsePushDto, Callback<response> callback);
}
