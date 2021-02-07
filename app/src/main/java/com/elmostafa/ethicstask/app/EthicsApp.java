package com.elmostafa.ethicstask.app;

import android.app.Application;
import android.content.Context;

public class EthicsApp extends Application {
    private static Context appContext;


    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
        // setLocalenew();

    }
    public static Context getAppContext() {
        return appContext;
    }

}
