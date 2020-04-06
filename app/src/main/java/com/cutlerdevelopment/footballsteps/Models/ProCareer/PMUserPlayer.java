package com.cutlerdevelopment.footballsteps.Models.ProCareer;

import androidx.annotation.NonNull;
import androidx.room.Entity;

import com.cutlerdevelopment.footballsteps.Constants.Position;
import com.cutlerdevelopment.footballsteps.Utils.DateHelper;

import java.util.Date;

/**
 * ProUsersPlayer class contains all the details about the player for the solo career.
 * In this class all the setters are used solely for the RoomDB creating the class.
 * If changes need to be made to the variables use the change methods instead.
 * This is so that the changes will update the db but the setters don't.
 */
@Entity(primaryKeys = {"firstName", "surname"})
public class PMUserPlayer {

    private static PMUserPlayer instance = null;

    /**
     * Returns the current instance of ProUsersPlayer. If instance not created will return null
     * @return the ProUsersPlayer instance
     */
    public static PMUserPlayer getInstance() {
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
    public PMUserPlayer(String fname, String sname, int pos, int favTeamID) {

        this.firstName = fname;
        this.surname = sname;
        this.position = pos;
        this.favTeamID = favTeamID;
        this.currTeamID = favTeamID;

        this.dateLastMatchPlayed = DateHelper.addDays(PMGame.getInstance().getStartDate(), -1);

        instance = this;
        PMSavedData.getInstance().saveObject(this);
    }

    /**
     * Used when loading a player for the solo career. Doesn't take any variables as takes them from RoomDB
     */
    public PMUserPlayer() {

        instance = this;
    }


    @NonNull
    private String firstName;
    public String getFirstName() { return firstName; }
    public void setFirstName(String fname) { firstName = fname; }
    public void changeFirstName(String newName) {
        firstName = newName;
        PMSavedData.getInstance().updateObject(this);
    }

    @NonNull
    private String surname;
    public String getSurname() { return surname; }
    public void setSurname(String sname) { surname = sname; }
    public void changeSurname(String newName) {
        surname = newName;
        PMSavedData.getInstance().updateObject(this);
    }

    private  int age;
    public int getAge() { return age; }
    public void setAge(int newAge) { age = newAge; }
    public void changeAge(int newAge) {
        age = newAge;
        PMSavedData.getInstance().updateObject(this);
    }

    private int position;
    public int getPosition() { return  position; }
    public void setPosition(int pos) { position = pos; }
    public void changePosition(int newPos) {
        if (newPos >= 1 && newPos <= Position.NUMPOSITIONS) {
            position = newPos;
            PMSavedData.getInstance().updateObject(this);
        }
    }

    private int currTeamID;
    public int getCurrTeamID() { return currTeamID; }
    public void setCurrTeamID(int newID) { currTeamID = newID; }
    public void changeCurrTeamID(int newID) {
        currTeamID = newID;
        PMSavedData.getInstance().updateObject(this);
    }
    public PMAITeam getCurrTeam() {
        return PMSavedData.getInstance().getTeamFromID(currTeamID);
    }

    private int favTeamID;
    public int getFavTeamID() { return favTeamID; }
    public void setFavTeamID(int newID) { favTeamID = newID; }
    public void changeFavTeamID(int newID) {
        favTeamID = newID;
        PMSavedData.getInstance().updateObject(this);
    }
    public PMAITeam getFavTeam() {
        return PMSavedData.getInstance().getTeamFromID(favTeamID);
    }

    public Date dateLastMatchPlayed;
    public Date getDateLastMatchPlayed() { return dateLastMatchPlayed; }
    public void setDateLastMatchPlayed(Date date) { this.dateLastMatchPlayed = date; }
    public void changeLastMatchPlayed(Date date) {
        this.dateLastMatchPlayed = date;
        PMSavedData.getInstance().updateObject(this);
    }

    private int appearances;
    public int getAppearances() { return appearances; }
    public void setAppearances(int apps) { this.appearances = apps; }
    public void addAppearance() {
        this.appearances++;
        PMSavedData.getInstance().updateObject(this);
    }

    private int saves;
    public int getSaves() { return saves; }
    public void setSaves(int saves) { this.saves = saves; }
    public void addSaves(int numSaves) {
        this.saves += numSaves;
        PMSavedData.getInstance().updateObject(this);
    }

    private int conceded;
    public int getConceded() { return conceded; }
    public void setConceded(int conc) { this.conceded = conc; }
    public void addConceded(int numConceded) {
        this.conceded += numConceded;
        PMSavedData.getInstance().updateObject(this);
    }

    private int tackles;
    public int getTackles() { return tackles; }
    public void setTackles(int tacks) { this.tackles = tacks; }
    public void addTackles(int numTackles) {
        this.tackles += numTackles;
        PMSavedData.getInstance().updateObject(this);
    }

    private int passes;
    public int getPasses() { return passes; }
    public void setPasses(int tacks) { this.passes = tacks; }
    public void addPasses(int numPasses) {
        this.passes += numPasses;
        PMSavedData.getInstance().updateObject(this);
    }

    private int assists;
    public int getAssists() { return assists; }
    public void setAssists(int ass) { this.assists = ass; }
    public void addAssists(int numAssists) {
        this.assists += numAssists;
        PMSavedData.getInstance().updateObject(this);
    }

    private int goals;
    public int getGoals() { return goals; }
    public void setGoals(int gls) { this.goals = gls; }
    public void addGoals(int numGoals) {
        this.goals += numGoals;
        PMSavedData.getInstance().updateObject(this);
    }

    private int offsides;
    public int getOffsides() { return offsides; }
    public void setOffsides(int offs) { this.offsides = offs; }
    public void addOffsides(int numOffside) {
        this.offsides += numOffside;
        PMSavedData.getInstance().updateObject(this);
    }



}
