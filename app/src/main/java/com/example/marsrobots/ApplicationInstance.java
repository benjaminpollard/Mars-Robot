package com.example.marsrobots;

import android.content.Context;

import androidx.multidex.MultiDexApplication;

public class ApplicationInstance extends MultiDexApplication {

    private static ApplicationInstance mInstance;

    public static Context getContext() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        mInstance = this;
        super.onCreate();
        Bootstrap.start(this);
    }

}
