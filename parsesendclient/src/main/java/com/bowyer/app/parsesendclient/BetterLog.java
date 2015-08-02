package com.bowyer.app.parsesendclient;

import android.util.Log;

import retrofit.RestAdapter;

public class BetterLog implements RestAdapter.Log {

    private static final int LOG_CHUNK_SIZE = 4000;

    private final String tag;

    public BetterLog(String tag) {
        this.tag = tag;
    }

    @Override
    public final void log(String message) {
        int index = message.indexOf("Content-Transfer-Encoding: binary");
        if (index != -1) { // ignore binary multipart body.
            logChunk(message.substring(0, index + "Content-Transfer-Encoding: binary".length())
                    + " <<< binary data >>>");
            return;
        }

        for (int i = 0, len = message.length(); i < len; i += LOG_CHUNK_SIZE) {
            int end = Math.min(len, i + LOG_CHUNK_SIZE);
            logChunk(message.substring(i, end));
        }
    }

    /**
     * Called one or more times for each call to {@link #log(String)}. The length of {@code chunk}
     * will be no more than 4000 characters to support Android's {@link Log} class.
     */
    public void logChunk(String chunk) {
        Log.d(getTag(), chunk);
    }

    public String getTag() {
        return tag;
    }
}
