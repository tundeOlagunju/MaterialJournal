package com.ajdi.yassin.instajournal;

import androidx.multidex.MultiDexApplication;

import timber.log.Timber;

public class AppApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
