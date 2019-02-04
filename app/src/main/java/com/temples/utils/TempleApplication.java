package com.temples.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TempleApplication extends Application {


    private static TempleApplication mInstance;

    public static synchronized TempleApplication getInstance() {
        if (mInstance == null) {
            mInstance = new TempleApplication();
        }
        return mInstance;
    }

    @Override
    public void onCreate() {

        super.onCreate();
        mInstance = this;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

}
