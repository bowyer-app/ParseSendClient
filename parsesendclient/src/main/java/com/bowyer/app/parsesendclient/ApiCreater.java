package com.bowyer.app.parsesendclient;

import com.squareup.okhttp.OkHttpClient;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by Bowyer on 2015/08/02.
 */
public class ApiCreater {

    private static final String PARSE_APPLICATION_ID = "X-Parse-Application-Id";
    private static final String PARSE_REST_API_KEY = "X-Parse-REST-API-Key";
    private static final String serverUrl = "https://api.parse.com";
    public static Api sharedInstance;

    public static synchronized Api getInstance() {
        if (sharedInstance != null) {
            return sharedInstance;
        }

        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader(PARSE_APPLICATION_ID, EnvConst.PARSE_APPLICATION_ID);
                request.addHeader(PARSE_REST_API_KEY, EnvConst.PARSE_REST_API_KEY);
            }
        };
        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setEndpoint(serverUrl)
                .setRequestInterceptor(requestInterceptor)
                .setClient(new OkClient(new OkHttpClient()));

        if (BuildConfig.DEBUG) {
            builder.setLogLevel(RestAdapter.LogLevel.FULL).setLog(new BetterLog("RETROFIT"));
        }

        return sharedInstance = builder.build().create(Api.class);
    }
}
