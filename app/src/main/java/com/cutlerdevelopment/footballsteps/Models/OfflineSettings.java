package com.cutlerdevelopment.footballsteps.Models;

import android.content.SharedPreferences;

import com.cutlerdevelopment.footballsteps.Constants.Numbers;
import com.cutlerdevelopment.footballsteps.Constants.Words;

/**
 * OfflineSettings contains all of the settings for the offline solo game
 */
public class OfflineSettings {

    private static OfflineSettings instance = null;

    /**
     * Returns the current instance of OfflineSettings. If instance not created will return null
     * @return the OfflineSettings instance
     */
    public static OfflineSettings getInstance() {
        if (instance != null) {
            return instance;
        }
        return null;
    }

    /**
     * Creates an instance of the settings using default information from Numbers class
     */
    public static void createOfflineSettingsInstance() {
        new OfflineSettings(false);
    }

    /**
     * Creates an instance of the settings loading from SharedPreferences
     */
    public static void loadOfflineSettingsInstance() {
        new OfflineSettings(true);
    }

    /**
     * When creating the settings we'll be told whether to load settings for an existing save or use default.
     * If not loading existing settings instance will be populated using default information from Numbers class
     * If loading existing settings instance will be populated using information from SharedPreferences.
     * @param loadingExisting
     */
    private OfflineSettings(boolean loadingExisting) {
        if (loadingExisting) {
            startingAge = SavedData.getInstance().getSavedInt(Words.SD_OFFLINEPLAYER_STARTING_AGE_KEY, Numbers.DEFAULT_STARTING_AGE);
        }
        else {
            setStartingAge(Numbers.DEFAULT_STARTING_AGE);
        }
        instance = this;
    }

    int startingAge;
    public int getStartingAge() { return startingAge; }
    public void setStartingAge(int newAge) {
        startingAge = newAge;
        SavedData.getInstance().saveInt(Words.SD_OFFLINEPLAYER_STARTING_AGE_KEY, startingAge);
    }

}
