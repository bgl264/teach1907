package com.yiyatech.utils;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

/**
 * Created by 任小龙 on 2020/6/2.
 */
public class UtilsApplication extends MultiDexApplication {
    private static UtilsApplication mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        MultiDex.install(this);
    }

    public static Context getUtilsApplicationContext(){
        return mApplication.getApplicationContext();
    }
}
