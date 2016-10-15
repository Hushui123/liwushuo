package com.example.dllo.windonsaitl;

import android.app.Application;
import android.content.Context;

/**
 * Created by dllo on 16/10/15.
 */
public class MyApp extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static Context getmContext(){
        return mContext;
    }
}
