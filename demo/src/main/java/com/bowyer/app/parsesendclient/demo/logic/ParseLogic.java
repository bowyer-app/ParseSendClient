package com.bowyer.app.parsesendclient.demo.logic;

import com.bowyer.app.parsesendclient.demo.constant.EnvConst;
import com.parse.Parse;
import com.parse.ParseInstallation;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Bowyer on 2015/08/01.
 */
public class ParseLogic {

    public static void ParceInit(Context context) {
        Parse.initialize(context, EnvConst.PARSE_APPLICATION_ID,
                EnvConst.PARSE_CLIENT_KEY);
        setChannel("demo");
    }

    public static void setChannel(String channel) {
        ParseInstallation install = ParseInstallation.getCurrentInstallation();
        List<String> channels = new ArrayList<>();
        channels.add(channel);
        install.put("channels", channels);
        install.saveInBackground();
    }
}
