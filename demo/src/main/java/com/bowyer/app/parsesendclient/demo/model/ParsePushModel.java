package com.bowyer.app.parsesendclient.demo.model;

/**
 * Created by Bowyer on 2015/08/02.
 */
public class ParsePushModel {

    String title;

    String message;

    String url;

    boolean forceSound;

    public static ParsePushModel to() {
        return new ParsePushModel();
    }

    public ParsePushModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public ParsePushModel setMessage(String message) {
        this.message = message;
        return this;
    }

    public ParsePushModel setUrl(String url) {
        this.url = url;
        return this;
    }

    public ParsePushModel setForceSound(boolean forceSound) {
        this.forceSound = forceSound;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public String getUrl() {
        return url;
    }

    public boolean forceSound() {
        return forceSound;
    }
}
