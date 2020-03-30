package com.cutlerdevelopment.footballsteps.Models;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.cutlerdevelopment.footballsteps.Constants.Colour;

@Entity
public class Team {

    public Team(int id, String name, int colour) {
        this.id = id;
        this.name = name;
        this.colour = colour;

    }

    public Team() {
        //Empty constructor for RommDB
    }

    @PrimaryKey
    int id;
    public int getID() { return id; }


    private String name;
    public String getName() {return  name; }
    public void setName(String newName) {
        name = newName;

    }

    private int colour;
    public int getColour() { return colour;}
    public void setColour(int newColour) {
        if (newColour >= 1 && newColour <= Colour.NUMCOLOURS) {
            colour = newColour;
        }
    }

}
