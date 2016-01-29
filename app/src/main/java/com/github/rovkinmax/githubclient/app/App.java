package com.github.rovkinmax.githubclient.app;

import android.app.Application;

/**
 * @author Rovkin Max
 */
public class App extends Application {
    private static App instance;
    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();
    }

    public static App getInstance() {
        return instance;
    }
}
