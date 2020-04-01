package com.cutlerdevelopment.footballsteps.Models;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

/**
 * PlayerActivity class is responsible for holding the information provided by the fitness API(s). A new instance of the class gets
 * created for each day of activity and saved in RoomDB.
 */
@Entity
public class PlayerActivity {

    @Ignore
    public PlayerActivity(Date date, int steps, int activeMinutes) {

        this.date = date;
        this.steps = steps;
        this.activeMinutes = activeMinutes;

        SavedData.getInstance().saveObject(this);
    }

    /**
     * Used when loading a player for the solo career. Doesn't take any variables as takes them from RoomDB
     */
    public PlayerActivity() {

    }

    @PrimaryKey
    private Date date;
    public Date getDate() { return  date; }
    public void setDate(Date date) { this.date = date; }
    public void changeDate(Date date) {
        this.date = date;
        SavedData.getInstance().updateObject(this);
    }

    private int steps;
    public int getSteps() { return steps; }
    public void setSteps(int steps) { this.steps = steps; }
    public void changeSteps(int steps) {
        this.steps = steps;
        SavedData.getInstance().updateObject(this);
    }

    private int activeMinutes;
    public int getActiveMinutes() { return  activeMinutes; }
    public void setActiveMinutes(int mins) {this.activeMinutes = mins; }
    public void changeActiveMinutes(int mins) {
        this.activeMinutes = mins;
        SavedData.getInstance().updateObject(this);
    }
}
