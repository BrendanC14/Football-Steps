package com.cutlerdevelopment.footballsteps.Models.TeamCareer;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.cutlerdevelopment.footballsteps.Constants.Numbers;

@Entity
public class OfflineTeamSettings {


    private static OfflineTeamSettings instance = null;

    /**
     * Returns the current instance of ProSettings. If instance not created will return null
     * @return the ProSettings instance
     */
    public static OfflineTeamSettings getInstance() {
        if (instance != null) {
            return instance;
        }
        return null;
    }

    /**
     * Creates a new instance of ProSettings.
     */
    public OfflineTeamSettings() {

        instance = this;
    }

    /**
     * Assigns default settings to the Object and saves them.
     */
    public void assignDefaultSettings(int stepTarget) {

        this.stepTarget = stepTarget;
        this.pointsForWin = Numbers.POINTS_FOR_WIN;
        this.pointsForDraw = Numbers.POINTS_FOR_DRAW;
        this. pointsForLoss = Numbers.POINTS_FOR_LOSS;


        OfflineTeamSavedData.getInstance().saveObject(this);

    }

    @PrimaryKey
    private int stepTarget;
    public int getStepTarget() { return stepTarget; }
    public void setStepTarget(int target) { this.stepTarget = target; }
    public void changeStepTarget(int newTarget) {
        this.stepTarget = newTarget;
        OfflineTeamSavedData.getInstance().updateObject(this);
    }

    private int pointsForWin;
    public int getPointsForWin() { return  pointsForWin; }
    public void setPointsForWin(int pts) { this.pointsForWin = pts; }
    public void changePointsForWin(int pts) {
        this.pointsForWin = pts;
        OfflineTeamSavedData.getInstance().updateObject(this);
    }

    private int pointsForDraw;
    public int getPointsForDraw() { return  pointsForDraw; }
    public void setPointsForDraw(int pts) { this.pointsForDraw = pts; }
    public void changePointsForDraw(int pts) {
        this.pointsForDraw = pts;
        OfflineTeamSavedData.getInstance().updateObject(this);
    }

    private int pointsForLoss;
    public int getPointsForLoss() { return  pointsForLoss; }
    public void setPointsForLoss(int pts) { this.pointsForLoss = pts; }
    public void changePointsForLoss(int pts) {
        this.pointsForLoss = pts;
        OfflineTeamSavedData.getInstance().updateObject(this);
    }
}
