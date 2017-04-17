package com.example.hoomsun.testrxlife;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by hoomsun on 2017/4/17.
 */

public class BaseApp extends Application {


    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
        context = this;
    }

    public static Context getContext() {
        return context;
    }
}
