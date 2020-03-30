package com.cutlerdevelopment.footballsteps.Models;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Fixture {

    @Ignore
    public Fixture(int id, int homeTeamID, int awayTeamID, int week, int league) {
        this.ID = id;
        this.homeTeamID = homeTeamID;
        this.awayTeamID = awayTeamID;
        this.week = week;
        this.league = league;

        SavedData.getInstance().saveObject(this);
    }

    /**
     * Used when loading a player for the solo career. Doesn't take any variables as takes them from RoomDB
     */
    public Fixture() {

    }

    @PrimaryKey
    private int ID;
    public int getID() {return  ID; }
    public void setID(int id) { this.ID = id; }
    public void changeID(int id) {
        this.ID = id;
        SavedData.getInstance().updateObject(this);
    }

    private int homeTeamID;
    public int getHomeTeamID() { return homeTeamID;}
    public void setHomeTeamID(int id) { this.homeTeamID = id; }
    public void changeHomeTeamID(int id) {
        this.homeTeamID = id;
        SavedData.getInstance().updateObject(this);
    }

    private int awayTeamID;
    public int getAwayTeamID() { return awayTeamID; }
    public void setAwayTeamID(int id) { this.awayTeamID = id; }
    public void changeAwayTeamID(int id) {
        this.awayTeamID = id;
        SavedData.getInstance().updateObject(this);
    }

    private int week;
    public int getWeek() { return week; }
    public void setWeek(int week) {this.week = week; }
    public void changeWeek(int week) {
        this.week = week;
        SavedData.getInstance().updateObject(this);
    }

    private int league;
    public int getLeague() { return  league; }
    public void setLeague(int league) {this.league = league; }
    public void changeLeague(int league) {
        this.league = league;
        SavedData.getInstance().updateObject(this);
    }
}
