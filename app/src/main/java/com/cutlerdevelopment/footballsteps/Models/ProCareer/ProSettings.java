package com.cutlerdevelopment.footballsteps.Models.ProCareer;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.cutlerdevelopment.footballsteps.Constants.Numbers;

/**
 * ProSettings contains all of the settings for the offline solo game
 */
@Entity
public class ProSettings {

    private static ProSettings instance = null;

    /**
     * Returns the current instance of ProSettings. If instance not created will return null
     * @return the ProSettings instance
     */
    public static ProSettings getInstance() {
        if (instance != null) {
            return instance;
        }
        return null;
    }

    /**
     * Creates a new instance of ProSettings.
     */
    public ProSettings() {

        instance = this;
    }

    /**
     * Assigns default settings to the Object and saves them.
     */
    public void assignDefaultSettings() {

        this.startingAge = Numbers.DEFAULT_STARTING_AGE;

        this.pointsForWin = Numbers.POINTS_FOR_WIN;
        this.pointsForDraw = Numbers.POINTS_FOR_DRAW;
        this. pointsForLoss = Numbers.POINTS_FOR_LOSS;


        OfflineProSavedData.getInstance().saveObject(this);

    }

    @PrimaryKey
    private int startingAge;
    public int getStartingAge() { return startingAge; }
    public void setStartingAge(int newAge) { startingAge = newAge; }
    public void changeStartingAge(int newAge) {
        startingAge = newAge;
        OfflineProSavedData.getInstance().updateObject(this);
    }

    private int stepDifficultyModifier;
    public int getStepDifficultyModifier() { return stepDifficultyModifier; }
    public void setStepDifficultyModifier(int diff) {this.stepDifficultyModifier = diff; }
    public void changeStepDifficultyModifier(int diff) {
        this.stepDifficultyModifier = diff;
        OfflineProSavedData.getInstance().updateObject(this);
    }

    private int minuteDifficultyModifier;
    public int getMinuteDifficultyModifier() { return minuteDifficultyModifier; }
    public void setMinuteDifficultyModifier(int diff) {this.minuteDifficultyModifier = diff; }
    public void changeMinuteDifficultyModifier(int diff) {
        this.minuteDifficultyModifier = diff;
        OfflineProSavedData.getInstance().updateObject(this);
    }

    private int pointsForWin;
    public int getPointsForWin() { return  pointsForWin; }
    public void setPointsForWin(int pts) { this.pointsForWin = pts; }
    public void changePointsForWin(int pts) {
        this.pointsForWin = pts;
        OfflineProSavedData.getInstance().updateObject(this);
    }

    private int pointsForDraw;
    public int getPointsForDraw() { return  pointsForDraw; }
    public void setPointsForDraw(int pts) { this.pointsForDraw = pts; }
    public void changePointsForDraw(int pts) {
        this.pointsForDraw = pts;
        OfflineProSavedData.getInstance().updateObject(this);
    }

    private int pointsForLoss;
    public int getPointsForLoss() { return  pointsForLoss; }
    public void setPointsForLoss(int pts) { this.pointsForLoss = pts; }
    public void changePointsForLoss(int pts) {
        this.pointsForLoss = pts;
        OfflineProSavedData.getInstance().updateObject(this);
    }


}
