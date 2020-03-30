package com.cutlerdevelopment.footballsteps.Models;


import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.cutlerdevelopment.footballsteps.Constants.Colour;

@Entity
public class Team {

    @Ignore
    public Team(int id, String name, int colour) {
        this.id = id;
        this.name = name;
        this.colour = colour;

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
        if (newColour >= 1 && newColour <= Colour.NUMCOLOURS) {
            colour = newColour;
            SavedData.getInstance().updateObject(this);
        }
    }

}
