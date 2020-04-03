package com.cutlerdevelopment.footballsteps.Models.ProCareer;

import android.util.Pair;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.cutlerdevelopment.footballsteps.Constants.Numbers;
import com.cutlerdevelopment.footballsteps.Constants.Position;
import com.cutlerdevelopment.footballsteps.Constants.Words;
import com.cutlerdevelopment.footballsteps.Models.SharedModels.SavedData;
import com.cutlerdevelopment.footballsteps.Models.SharedModels.Team;

@Entity
public class ProAIPlayer {


    /**
     * Used when creating a new ProAIPlayer when a new game is created or a custom team is added
     */
    @Ignore
    public ProAIPlayer(int clubID, int position, int stepReduction, int minReduction) {

        this.ID = SavedData.getInstance().getNumRowsFromOfflineAIPlayerTable() + 1;
        this.firstName = Words.getRandomFirstName();
        this.surname = Words.getRandomSurname();
        this.currTeamID = clubID;
        Pair<Integer, Integer> pair = Numbers.getRandomStartingNumbers(getCurrTeam().getLeague());
        this.averageSteps = pair.first + stepReduction;
        this.averageMinutes = pair.second + minReduction;

        this.position = position;


        SavedData.getInstance().saveObject(this);
    }

    /**
     * Used when loading a player for the solo career. Doesn't take any variables as takes them from RoomDB
     */
    public ProAIPlayer() {

    }

    @PrimaryKey
    private int ID;
    public int getID() { return ID; }
    public void setID(int id) {this.ID = id; }
    public void changeID(int id) {
        this.ID = id;
        SavedData.getInstance().updateObject(this);
    }

    private String firstName;
    public String getFirstName() { return firstName; }
    public void setFirstName(String name) {this.firstName = name; }
    public void changeFirstName(String name) {
        this.firstName = name;
        SavedData.getInstance().updateObject(this);
    }

    private String surname;
    public String getSurname() { return surname; }
    public void setSurname(String name) {this.surname = name; }
    public void changeSurname(String name) {
        this.firstName = name;
        SavedData.getInstance().updateObject(this);
    }

    private int age;
    public int getAge() { return age; }
    public void setAge(int age) {this.age = age; }
    public void changeAge(int age) {
        this.age = age;
        SavedData.getInstance().updateObject(this);
    }

    private int position;
    public int getPosition() { return  position; }
    public void setPosition(int pos) { position = pos; }
    public void changePosition(int newPos) {
        if (newPos >= 1 && newPos <= Position.NUMPOSITIONS) {
            position = newPos;
            SavedData.getInstance().updateObject(this);
        }
    }

    private int currTeamID;
    public int getCurrTeamID() { return currTeamID; }
    public void setCurrTeamID(int club) {this.currTeamID = club; }
    public void changeSurname(int club) {
        this.currTeamID = club;
        SavedData.getInstance().updateObject(this);
    }
    public Team getCurrTeam() { return SavedData.getInstance().getTeamFromID(currTeamID); }

    private int averageSteps;
    public int getAverageSteps() { return averageSteps; }
    public void setAverageSteps(int steps) {this.averageSteps = steps; }
    public void refreshAverageSteps(int newAverageSteps) {

        averageSteps = (averageSteps + newAverageSteps) / 2;

        SavedData.getInstance().updateObject(this);
    }

    private int averageMinutes;
    public int getAverageMinutes() { return averageMinutes; }
    public void setAverageMinutes(int minutes) {this.averageMinutes = minutes; }
    public void refreshAverageMinutes(int newAverageMinutes) {

        averageMinutes = (averageMinutes + newAverageMinutes) / 2;

        SavedData.getInstance().updateObject(this);
    }

    private int appearances;
    public int getAppearances() { return appearances; }
    public void setAppearances(int apps) { this.appearances = apps; }
    public void addAppearance() {
        this.appearances++;
        SavedData.getInstance().updateObject(this);
    }
}
