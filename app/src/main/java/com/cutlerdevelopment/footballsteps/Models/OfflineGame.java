package com.cutlerdevelopment.footballsteps.Models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    }

    Date startDate;
    public Date getStartDate() { return startDate; }

    int season;
    public int getSeason() { return  season; }
    public void setSeason(int newSeason) {
        season  = newSeason;
    }

    List<Team> allTeams;
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




    public Date formatDate(Date date) {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return new Date(formatter.format(date));
    }

}
