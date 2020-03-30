package com.cutlerdevelopment.footballsteps.Models;

import androidx.annotation.NonNull;
import androidx.room.Entity;

import com.cutlerdevelopment.footballsteps.Constants.Position;

/**
 * OfflinePlayer class contains all the details about the player for the solo career.
 * In this class all the setters are used solely for the RoomDB creating the class.
 * If changes need to be made to the variables use the change methods instead.
 * This is so that the changes will update the db but the setters don't.
 */
@Entity(tableName = "offline_player", primaryKeys = {"firstName", "surname"})
public class OfflinePlayer {

    private static OfflinePlayer instance = null;

    /**
     * Returns the current instance of OfflinePlayer. If instance not created will return null
     * @return the OfflinePlayer instance
     */
    public static OfflinePlayer getInstance() {
        if (instance != null) {
            return instance;
        }
        return null;
    }

    /**
     * Used to create a brand new player when starting a new solo career.
     * @param fname is the chosen first name
     * @param sname is the chosen surname
     * @param pos is the chosen position as an integer. Can be used with Position class
     * @param favTeamID is the chosen favourite team ID
     */
    public OfflinePlayer(String fname, String sname, int pos, int favTeamID) {

        this.firstName = fname;
        this.surname = sname;
        this.position = pos;
        this.favTeamID = favTeamID;

        instance = this;
        SavedData.getInstance().saveObject(this);
    }

    /**
     * Used when loading a player for the solo career. Doesn't take any variables as takes them from RoomDB
     */
    public OfflinePlayer() {

        instance = this;
    }


    @NonNull
    private String firstName;
    public String getFirstName() { return firstName; }
    public void setFirstName(String fname) { firstName = fname; }
    public void changeFirstName(String newName) {
        firstName = newName;
        SavedData.getInstance().updateObject(this);
    }

    @NonNull
    private String surname;
    public String getSurname() { return surname; }
    public void setSurname(String sname) { surname = sname; }
    public void changeSurname(String newName) {
        surname = newName;
        SavedData.getInstance().updateObject(this);
    }

    private  int age;
    public int getAge() { return age; }
    public void setAge(int newAge) { age = newAge; }
    public void changeAge(int newAge) {
        age = newAge;
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
    public void setCurrTeamID(int newID) { currTeamID = newID; }
    public void changeCurrTeamID(int newID) {
        currTeamID = newID;
        SavedData.getInstance().updateObject(this);
    }
    public Team getCurrTeam() {
        return SavedData.getInstance().getTeamFromID(currTeamID);
    }

    private int favTeamID;
    public int getFavTeamID() { return favTeamID; }
    public void setFavTeamID(int newID) { favTeamID = newID; }
    public void changeFavTeamID(int newID) {
        favTeamID = newID;
        SavedData.getInstance().updateObject(this);
    }
    public Team getFavTeam() {
        return SavedData.getInstance().getTeamFromID(favTeamID);
    }

}
