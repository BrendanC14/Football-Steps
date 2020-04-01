package com.cutlerdevelopment.footballsteps.Models;


import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.cutlerdevelopment.footballsteps.Constants.Colour;

@Entity
public class Team {

    /**
     * Used when creating a new Team when a new game is created or a custom team is added
     * @param name name of the club
     * @param colour colour of the club
     */
    @Ignore
    public Team(String name, int colour, int league) {
        this.id = SavedData.getInstance().getNumRowsFromTeamTable() + 1;
        this.name = name;
        this.colour = colour;
        this.league = league;

        SavedData.getInstance().saveObject(this);
    }

    /**
     * Used when loading a player for the solo career. Doesn't take any variables as takes them from RoomDB
     */
    public Team() {

    }

    @PrimaryKey
    int id;
    public int getID() { return id; }


    private String name;
    public String getName() {return  name; }
    public void setName(String newName) { name = newName; }
    public void changeName(String newName) {
        name = newName;
        SavedData.getInstance().updateObject(this);
    }

    private int colour;
    public int getColour() { return colour;}
    public void setColour(int newColour) { colour = newColour; }
    public void changeColour(int newColour) {
        if (newColour >= 1 && newColour <= Colour.NUM_TEAM_COLOURS) {
            colour = newColour;
            SavedData.getInstance().updateObject(this);
        }
    }

    private int league;
    public int getLeague() { return league; }
    public void setLeague(int newLeague) { this.league = league; }
    public void changeLeague(int newLeague) {
        this.league = newLeague;
        SavedData.getInstance().updateObject(league);
    }

}
