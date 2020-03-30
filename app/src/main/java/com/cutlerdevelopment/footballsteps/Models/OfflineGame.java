package com.cutlerdevelopment.footballsteps.Models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.cutlerdevelopment.footballsteps.Constants.Colour;
import com.cutlerdevelopment.footballsteps.Constants.Words;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Entity
public class OfflineGame {



    private static OfflineGame instance = null;
    public static OfflineGame getInstance() {
        if (instance != null) {
            return instance;
        }
        instance = new OfflineGame();
        return instance;
    }

    public OfflineGame() {

        instance = this;
    }

    public void startNewGame() {

        this.startDate = new Date();
        Random r = new Random();
        for (String teamName : Words.TeamNames) {
            new Team(SavedData.getInstance().getNumRowsFromTeamTable() + 1,
                    teamName, r.nextInt(Colour.NUMCOLOURS + 1) + 1);
        }

        SavedData.getInstance().saveObject(this);

    }

    @PrimaryKey
    private Date startDate;
    public Date getStartDate() { return startDate; }
    public void setStartDate(Date date) { startDate = date; }
    public void changeStartDate(Date date) {
        startDate = date;
        SavedData.getInstance().updateObject(this);
    }

    private int season;
    public int getSeason() { return  season; }
    public void setSeason(int newSeason) { season  = newSeason; }
    public void changeSeason(int newSeason) {
        season = newSeason;
        SavedData.getInstance().updateObject(this);
    }

    public Date formatDate(Date date) {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return new Date(formatter.format(date));
    }

}
