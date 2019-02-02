package com.temples.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.HashSet;

public class PreferenceHelper { public final static String IS_HYDROCARE_NOTIFICATION_ENABLE = "isHydrocareNotificationEnable";
    static final String TAG = "RegisterGcmIdInfo";
    private final SharedPreferences mPrefs;
    Context context;
    private String PREF_IsLogin = "IsLogin";
    private String PREF_AppToken = "AppToken";
    private String PREF_USERNAME = "UserName";
    private String PREF_USEREMAIL = "UserEmail";
    private String PREF_MOBILE = "UserMobile";




    public PreferenceHelper(Context context) {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
    }




    public String getAppToken() {
        String str = mPrefs.getString(PREF_AppToken, "");
        return str;
    }

    public void setAppToken(String pREF_AppToken) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_AppToken, pREF_AppToken);
        mEditor.commit();
    }


    public String getFullName() {
        String str = mPrefs.getString(PREF_USERNAME, "");
        return str;
    }

    public void setFullName(String pref_username) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_USERNAME, pref_username);
        mEditor.commit();
    }


    public String getEmail() {
        String str = mPrefs.getString(PREF_USEREMAIL, "");
        return str;
    }

    public void setEmail(String pref_useremail) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_USEREMAIL, pref_useremail);
        mEditor.commit();
    }




    public String getMobile() {
        String str = mPrefs.getString(PREF_MOBILE, "");
        return str;
    }

    public void setMobile(String pref_mobile) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_MOBILE, pref_mobile);
        mEditor.commit();
    }


    public boolean getIsLogin() {
        boolean str = mPrefs.getBoolean(PREF_IsLogin, false);
        return str;
    }

    public void setIsLogin(boolean pREF_IsLogin) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_IsLogin, pREF_IsLogin);
        mEditor.commit();
    }










    public void ClearAllData() {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_AppToken, "");
        mEditor.putBoolean(PREF_IsLogin, false);
        mEditor.putString(PREF_USERNAME,"");
        mEditor.putString(PREF_USEREMAIL,"");
        mEditor.putString(PREF_MOBILE,"");

        mEditor.commit();

    }


}