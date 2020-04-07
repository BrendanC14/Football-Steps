package com.cutlerdevelopment.footballsteps.Models.SharedModels;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.cutlerdevelopment.footballsteps.Models.ProCareer.PMSavedData;

import java.util.Date;

/**
 * PlayerActivity class is responsible for holding the information provided by the fitness API(s). A new instance of the class gets
 * created for each day of activity and saved in RoomDB.
 */
@Entity
public class UserActivity {

    @Ignore
    public UserActivity(Date date, int steps, int activeMinutes) {

        this.date = date;
        this.steps = steps;
        this.activeMinutes = activeMinutes;

        AppSavedData.getInstance().saveObject(this);
    }

    /**
     * Used when loading a player for the solo career. Doesn't take any variables as takes them from RoomDB
     */
    public UserActivity() {

    }

    @PrimaryKey
    private Date date;
    public Date getDate() { return  date; }
    public void setDate(Date date) { this.date = date; }
    public void changeDate(Date date) {
        this.date = date;
        PMSavedData.getInstance().updateObject(this);
    }

    private int steps;
    public int getSteps() { return steps; }
    public void setSteps(int steps) { this.steps = steps; }
    public void changeSteps(int steps) {
        this.steps = steps;
        PMSavedData.getInstance().updateObject(this);
    }

    private int activeMinutes;
    public int getActiveMinutes() { return  activeMinutes; }
    public void setActiveMinutes(int mins) {this.activeMinutes = mins; }
    public void changeActiveMinutes(int mins) {
        this.activeMinutes = mins;
        PMSavedData.getInstance().updateObject(this);
    }
}
