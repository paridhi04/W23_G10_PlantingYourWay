package com.example.plantingyourway;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;


public class SharedPref
{
    private static SharedPreferences mSharedPref;

    private SharedPref()
    {

    }

    public static void init(Context context)
    {
        if(mSharedPref == null)
            mSharedPref = context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE);
    }

    public static String readStringValue(String key, String defValue) {
        return mSharedPref.getString(key, defValue);
    }

    public static void writeStringValue(String key, String value) {
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putString(key, value);
        prefsEditor.apply();
    }

    public static boolean readBoolenValue(String key, boolean defValue) {
        return mSharedPref.getBoolean(key, defValue);
    }

    public static void writeBooleanValue(String key, boolean value) {
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putBoolean(key, value);
        prefsEditor.apply();
    }

    public static Integer readIntValue(String key, int defValue) {
        return mSharedPref.getInt(key, defValue);
    }

    public static void writeIntValue(String key, Integer value) {
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putInt(key, value).apply();
    }
    public static Integer readParcelableValue(String key, int defValue) {
        return mSharedPref.getInt(key, defValue);
    }

    public static void writeParcelableValue(String key, Integer value) {
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putInt(key, value).apply();
    }

    public static void writeLoggedUserDetails(String key,UserDetailsDataModel serviceProviderModel) {
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.apply();
    }

    public static void clearSharedPref(){
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.clear().apply();
    }

}