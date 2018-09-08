package com.jc.road.global;

import android.app.Application;

public class ColdStartApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
