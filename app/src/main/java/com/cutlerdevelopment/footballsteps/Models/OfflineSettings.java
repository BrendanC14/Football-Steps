package com.cutlerdevelopment.footballsteps.Models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.cutlerdevelopment.footballsteps.Constants.Numbers;

/**
 * OfflineSettings contains all of the settings for the offline solo game
 */
@Entity
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
     * Creates a new instance of OfflineSettings.
     */
    public OfflineSettings() {

        instance = this;
    }

    /**
     * Assigns default settings to the Object and saves them.
     */
    public void assignDefaultSettings() {

        this.startingAge = Numbers.DEFAULT_STARTING_AGE;


        SavedData.getInstance().saveObject(this);

    }

    @PrimaryKey
    private int startingAge;
    public int getStartingAge() { return startingAge; }
    public void setStartingAge(int newAge) { startingAge = newAge; }
    public void changeStartingAge(int newAge) {
        startingAge = newAge;
        SavedData.getInstance().updateObject(this);
    }

    private int stepDifficultyModifier;
    public int getStepDifficultyModifier() { return stepDifficultyModifier; }
    public void setStepDifficultyModifier(int diff) {this.stepDifficultyModifier = diff; }
    public void changeStepDifficultyModifier(int diff) {
        this.stepDifficultyModifier = diff;
        SavedData.getInstance().updateObject(this);
    }

    private int minuteDifficultyModifier;
    public int getMinuteDifficultyModifier() { return minuteDifficultyModifier; }
    public void setMinuteDifficultyModifier(int diff) {this.minuteDifficultyModifier = diff; }
    public void changeMinuteDifficultyModifier(int diff) {
        this.minuteDifficultyModifier = diff;
        SavedData.getInstance().updateObject(this);
    }


}
