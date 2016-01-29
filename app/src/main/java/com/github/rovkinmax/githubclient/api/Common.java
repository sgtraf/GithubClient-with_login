package com.github.rovkinmax.githubclient.api;

import com.github.rovkinmax.githubclient.BuildConfig;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import io.realm.RealmObject;
import retrofit.RestAdapter;
import retrofit.android.AndroidLog;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 * @author Rovkin Max
 */
final class Common {
    private static final String LOG_TAG = "!GH_Client";
    private static final int TIMEOUT = 60;
    private static final int WRITE_TIMEOUT = 120;
    private static final int CONNECT_TIMEOUT = 10;
    private static final Gson GSON_REALM = new GsonBuilder()
            .setExclusionStrategies(new ExclusionStrategy() {
                @Override
                public boolean shouldSkipField(FieldAttributes f) {
                    return f.getDeclaringClass().equals(RealmObject.class);
                }

                @Override
                public boolean shouldSkipClass(Class<?> clazz) {
                    return false;
                }
            })
            .create();
    private static final OkHttpClient CLIENT = new OkHttpClient();

    static {
        CLIENT.setConnectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS);
        CLIENT.setWriteTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS);
        CLIENT.setReadTimeout(TIMEOUT, TimeUnit.SECONDS);
    }

    private Common() {
    }

    static RestAdapter.Builder getRestAdapter() {
        return new RestAdapter.Builder()
                .setLog(new AndroidLog(LOG_TAG))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setConverter(new GsonConverter(GSON_REALM))
                .setClient(new OkClient(CLIENT))
                .setEndpoint(BuildConfig.SERVER_URL);
    }
}
