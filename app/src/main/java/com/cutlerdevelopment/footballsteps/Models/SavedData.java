package com.cutlerdevelopment.footballsteps.Models;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * SavedData class is responsible for taking to the SharedPreferences and getting responses.
 * Contains methods responsible for retrieving and saving Strings, ints and booleans.
 */
public class SavedData {


    private static SavedData instance = null;

    private static SharedPreferences sharedPreferences;
    static final String PREFS_NAME = "OfflineData";

    public static SavedData getInstance() {
        if (instance != null) {
            return instance;
        }
        instance = new SavedData();
        return instance;
    }

    private SavedData() {

    }

    public String getSavedString(String key, String defaultValue) {
        if (sharedPreferences == null) {
            //TODO throw an error
            return defaultValue;
        }
        return sharedPreferences.getString(key, defaultValue);
    }
    public void saveString(String key, String value) {
        if (sharedPreferences == null) {
            //TODO throw an error
            return;
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public int getSavedInt(String key, int defaultValue) {
        if (sharedPreferences == null) {
            //TODO throw an error
            return defaultValue;
        }
        return sharedPreferences.getInt(key, defaultValue);
    }
    public void saveInt(String key, int value) {
        if (sharedPreferences == null) {
            //TODO throw an error
            return;
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public boolean getSavedBoolean(String key, boolean defaultValue) {
        if (sharedPreferences == null) {
            //TODO throw an error
            return defaultValue;
        }
        return sharedPreferences.getBoolean(key, defaultValue);
    }
    public void saveBoolean(String key, boolean value) {
        if (sharedPreferences == null) {
            //TODO throw an error
            return;
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static void createSavedDataInstance(Context c) {
        sharedPreferences = c.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

}
