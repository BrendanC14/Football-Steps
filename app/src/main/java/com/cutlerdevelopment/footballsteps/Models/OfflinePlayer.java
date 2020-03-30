package com.cutlerdevelopment.footballsteps.Models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.cutlerdevelopment.footballsteps.Constants.Position;
import com.cutlerdevelopment.footballsteps.Constants.Words;

/**
 * OfflinePlayer class contains all the details about the player for the solo career.
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
     * Creates an instance of the player from the following variables
     * @param fName is the chosen first name
     * @param sName is the chosen surname
     * @param pos is the chosen position as an integer. Can be used with Position class
     * @param favTeam is the chosen favourite currTeam
     */
    public static void CreateOfflinePlayer(String fName, String sName, int pos,
                                           Team favTeam) {
        new OfflinePlayer(fName, sName, pos, favTeam);
    }


    /**
     * Creates an instance of the player loading from SharedPreferences
     */
    public static void LoadOfflinePlayer() {
        new OfflinePlayer();

    }

    /**
     * Used to create a brand new player when starting a new solo career.
     * @param fname is the chosen first name
     * @param sname is the chosen surname
     * @param pos is the chosen position as an integer. Can be used with Position class
     * @param favTeam is the chosen favourite currTeam
     */
    private OfflinePlayer(String fname, String sname, int pos, Team favTeam) {

        setFirstName(fname);
        setSurname(sname);
        setPosition(pos);
        setFavTeam(favTeam);

        instance = this;
        SavedData.getInstance().saveOfflinePlayer(this);
    }

    /**
     * Used when loading a player for the solo career. Doesn't take any variables as takes them from SavedPreferences
     */
    public OfflinePlayer() {

        instance = this;
    }


    @NonNull
    private String firstName;
    public String getFirstName() { return firstName; }
    public void setFirstName(String fname) {
        firstName = fname;
        SavedData.getInstance().saveString(Words.SD_OFFLINEPLAYER_FIRST_NAME_KEY, fname);
    }

    @NonNull
    private String surname;
    public String getSurname() { return surname; }
    public void setSurname(String sname) {
        surname = sname;
        SavedData.getInstance().saveString(Words.SD_OFFLINEPLAYER_SURNAME_KEY, sname);
    }

    private  int age;
    public int getAge() { return age; }
    public void setAge(int newAge) {
        age = newAge;
        SavedData.getInstance().saveInt(Words.SD_OFFLINEPLAYER_AGE_KEY, newAge);
    }

    private int position;
    public int getPosition() { return  position; }
    public void setPosition(int pos) {
        if (pos >= 1 && pos <= Position.NUMPOSITIONS) {
            position = pos;
            SavedData.getInstance().saveInt(Words.SD_OFFLINEPLAYER_POSITION_KEY, pos);
        }
    }

    private Team currTeam;
    public Team getCurrTeam() { return currTeam; }
    public void setCurrTeam(Team t) {
        currTeam = t;
        SavedData.getInstance().saveInt(Words.SD_OFFLINEPLAYER_CURRTEAM_KEY, t.getID());
    }

    private Team favTeam;
    public Team getFavTeam() { return  favTeam; }
    public void setFavTeam(Team t) {
        favTeam = t;
        SavedData.getInstance().saveInt(Words.SD_OFFLINEPLAYER_FAVTEAM_KEY, t.getID());
    }

}
