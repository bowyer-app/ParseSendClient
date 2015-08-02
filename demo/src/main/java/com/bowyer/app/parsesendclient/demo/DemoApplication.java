package com.bowyer.app.parsesendclient.demo;

import com.bowyer.app.parsesendclient.demo.logic.ParseLogic;

import android.app.Application;

/**
 * Created by Bowyer on 2015/08/02.
 */
public class DemoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ParseLogic.ParseInit(getApplicationContext());
    }
}
