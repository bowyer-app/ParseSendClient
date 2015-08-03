package com.bowyer.app.parsesendclient.senddemo.model;

/**
 * Created by Bowyer on 2015/08/02.
 */
public class ParsePushModel {

    String title;
    String message;


    public ParsePushModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public ParsePushModel setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

}
