package com.example.a1.zhoumi2080806.app;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by 1 on 2018/8/6.
 */

public class MyApp extends Application{
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(getApplicationContext());
        mContext=getApplicationContext();
        //        GreenDaoManager.getInstance();
    }
    public static Context getContext(){
        return mContext;
    }
}
