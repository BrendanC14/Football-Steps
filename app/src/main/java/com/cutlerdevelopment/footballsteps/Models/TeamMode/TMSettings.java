package com.cutlerdevelopment.footballsteps.Models.TeamMode;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.cutlerdevelopment.footballsteps.Constants.Numbers;

@Entity
public class TMSettings {


    private static TMSettings instance = null;

    /**
     * Returns the current instance of ProSettings. If instance not created will return null
     * @return the ProSettings instance
     */
    public static TMSettings getInstance() {
        if (instance != null) {
            return instance;
        }
        return null;
    }

    /**
     * Creates a new instance of ProSettings.
     */
    public TMSettings() {

        instance = this;
    }

    /**
     * Assigns default settings to the Object and saves them.
     */
    public void assignDefaultSettings(int stepTarget) {

        this.stepTarget = stepTarget * 1000;
        this.daysBetweenGames = 2;
        this.pointsForWin = Numbers.POINTS_FOR_WIN;
        this.pointsForDraw = Numbers.POINTS_FOR_DRAW;
        this. pointsForLoss = Numbers.POINTS_FOR_LOSS;


        TMSavedData.getInstance().saveObject(this);

    }

    @PrimaryKey
    private int stepTarget;
    public int getStepTarget() { return stepTarget; }
    public void setStepTarget(int target) { this.stepTarget = target; }
    public void changeStepTarget(int newTarget) {
        this.stepTarget = newTarget;
        TMSavedData.getInstance().updateObject(this);
    }

    private int daysBetweenGames;
    public int getDaysBetweenGames() { return daysBetweenGames; }
    public void setDaysBetweenGames(int daysBetweenGames) { this.daysBetweenGames = daysBetweenGames; }
    public void changeDaysBetweenGames(int daysBetweenGames) {
        this.daysBetweenGames = daysBetweenGames;
        TMSavedData.getInstance().updateObject(this);
    }

    private int pointsForWin;
    public int getPointsForWin() { return  pointsForWin; }
    public void setPointsForWin(int pts) { this.pointsForWin = pts; }
    public void changePointsForWin(int pts) {
        this.pointsForWin = pts;
        TMSavedData.getInstance().updateObject(this);
    }

    private int pointsForDraw;
    public int getPointsForDraw() { return  pointsForDraw; }
    public void setPointsForDraw(int pts) { this.pointsForDraw = pts; }
    public void changePointsForDraw(int pts) {
        this.pointsForDraw = pts;
        TMSavedData.getInstance().updateObject(this);
    }

    private int pointsForLoss;
    public int getPointsForLoss() { return  pointsForLoss; }
    public void setPointsForLoss(int pts) { this.pointsForLoss = pts; }
    public void changePointsForLoss(int pts) {
        this.pointsForLoss = pts;
        TMSavedData.getInstance().updateObject(this);
    }
}
