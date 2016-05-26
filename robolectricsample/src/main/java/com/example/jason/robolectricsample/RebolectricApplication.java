package com.example.jason.robolectricsample;

import android.app.Application;

/**
 * Created by jsson on 16/5/26.
 */
public class RebolectricApplication extends Application {
    private static RebolectricApplication INSTANCE;

    public static RebolectricApplication getInstance() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
    }
}
