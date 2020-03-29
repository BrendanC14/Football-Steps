package com.cutlerdevelopment.footballsteps.Models;

import com.cutlerdevelopment.footballsteps.Constants.Colour;
import com.cutlerdevelopment.footballsteps.Constants.Words;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class OfflineGame {



    private static OfflineGame instance = null;
    public static OfflineGame getInstance() {
        if (instance != null) {
            return instance;
        }
        instance = new OfflineGame();
        return instance;
    }

    private OfflineGame() {
        startDate = new Date();
        allTeams = new ArrayList<>();
        populateAllTeams();
        instance = this;
    }

    private Date startDate;
    public Date getStartDate() { return startDate; }

    private int season;
    public int getSeason() { return  season; }
    public void setSeason(int newSeason) {
        season  = newSeason;
    }

    private List<Team> allTeams;
    void populateAllTeams() {
        Random r = new Random();
        for (String teamName : Words.TeamNames) {
            addTeam(new Team(getAllTeamsSize(), teamName, r.nextInt(Colour.NUMCOLOURS + 1) + 1));
        }
    }
    public List<Team> getAllTeams() { return allTeams; }
    public void addTeam(Team t) {
        if (allTeams.contains(t)) {
            //TODO: Throw a warning
            return;
        }
        allTeams.add(t);
    }
    public void removeTeam(Team t) {
        if (!allTeams.contains(t)) {
            //TODO: Throw a warning
            return;
        }
        allTeams.remove(t);
    }
    public Team getTeamFromID(int ID) {
        for (Team t : allTeams) {
            if (t.getID() == ID) {
                return t;
            }
        }
        return null;
    }
    public Team getTeamFromName(String name) {
        for (Team t : allTeams) {
            if (t.getName() == name) {
                return t;
            }
        }
        return null;
    }
    public int getAllTeamsSize() {return  allTeams.size(); }





    public Date formatDate(Date date) {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return new Date(formatter.format(date));
    }

}
