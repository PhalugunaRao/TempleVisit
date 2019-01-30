package com.temples.utils;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class NetworkCaller {
    public Context mActivity;
    public boolean isNetError = false;
    public boolean isConnectionTimeError = false;
    public boolean isOtherError = false;
    public boolean isDataError = false;
    Object mFillObject;
    HashMap<String, String> mHashMap;
    Gson mGson;
    PreferenceHelper prefs;

    public NetworkCaller(Context context) {
        mActivity = context;
        mGson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        prefs = new PreferenceHelper(mActivity);
    }

    public static boolean isInternetOncheck(Context context) {
        ConnectivityManager connec = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (!(connec.getActiveNetworkInfo() != null && connec.getActiveNetworkInfo().isAvailable() && connec.getActiveNetworkInfo().isConnected())) {
            //            AppLog.logString(TAG+"Internet not avialable");


            return false;
        } else {
            //            AppLog.logString(TAG+"Internet available");
            return true;
        }


    }

    public boolean check_Internet(Context mContext) {
        ConnectivityManager mConnectivityManager;
        NetworkInfo mNetworkInfo;
        mConnectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();

        if (mNetworkInfo != null && mNetworkInfo.isConnectedOrConnecting())
            return true;
        else
            return false;
    }


}

