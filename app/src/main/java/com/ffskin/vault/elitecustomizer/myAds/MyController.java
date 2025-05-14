package com.ffskin.vault.elitecustomizer.myAds;

import android.app.Application;

public class MyController extends Application {

    public static MyController myController;

    public static MyController get() {
        return myController;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myController = this;

    }
}
