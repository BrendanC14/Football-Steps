package com.cutlerdevelopment.footballsteps.Models;

import android.text.format.DateUtils;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.cutlerdevelopment.footballsteps.Constants.Colour;
import com.cutlerdevelopment.footballsteps.Constants.Words;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Entity
public class OfflineGame {



    private static OfflineGame instance = null;
    public static OfflineGame getInstance() {
        if (instance != null) {
            return instance;
        }
        instance = new OfflineGame();
        return instance;
    }

    public OfflineGame() {

        instance = this;
    }

    public void startNewGame() {

        this.startDate = new Date();
        Random r = new Random();
        for (String teamName : Words.TeamNames) {
            new Team(SavedData.getInstance().getNumRowsFromTeamTable() + 1,
                    teamName, Colour.getDefaultColourForTeam(teamName), 1);
        }


        createFixtures();

        SavedData.getInstance().saveObject(this);

    }

    @PrimaryKey
    private Date startDate;
    public Date getStartDate() { return startDate; }
    public void setStartDate(Date date) { startDate = date; }
    public void changeStartDate(Date date) {
        startDate = date;
        SavedData.getInstance().updateObject(this);
    }

    private int season;
    public int getSeason() { return  season; }
    public void setSeason(int newSeason) { season  = newSeason; }
    public void changeSeason(int newSeason) {
        season = newSeason;
        SavedData.getInstance().updateObject(this);
    }

    public String formatDate(Date date) {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(date);
    }

    void createFixtures() {

        List<Integer> availableWeeks = new ArrayList<>();
        for (int i = 1; i < 20; i++) {
            availableWeeks.add(i);
        }
        Collections.shuffle(availableWeeks);
        int round;

        for (int gameweek = 0; gameweek < 19; gameweek++) {
            round = availableWeeks.get(0);
            availableWeeks.remove(0);
            for (int match = 0; match < 10; match++) {
                int homeTeam = ((gameweek + match) % 19) + 1;
                int awayTeam = ((gameweek - match + 19) % 19) +1;

                if (match == 0) {awayTeam = 19; }

                Calendar cal = Calendar.getInstance();
                cal.setTime(startDate);
                cal.add(Calendar.DATE, gameweek);
                Date matchDate = cal.getTime();
                new Fixture(SavedData.getInstance().getNumRowsFromFixtureTable(),
                        homeTeam,
                        awayTeam,
                        round,
                        matchDate,
                        1);
            }
        }

        availableWeeks = new ArrayList<>();
        for (int i = 20; i < 39; i++) {
            availableWeeks.add(i);
        }
        Collections.shuffle(availableWeeks);

        for (int gameweek = 0; gameweek < 19; gameweek++) {
            round = availableWeeks.get(0);
            availableWeeks.remove(0);
            for (int match = 0; match < 10; match++) {
                int homeTeam = ((gameweek - match + 19) % 19) + 1;
                int awayTeam = ((gameweek + match) % 19) + 1;

                if (match == 0) { homeTeam = 19; }

                Calendar cal = Calendar.getInstance();
                cal.setTime(startDate);
                cal.add(Calendar.DATE, gameweek);
                Date matchDate = cal.getTime();
                new Fixture(SavedData.getInstance().getNumRowsFromFixtureTable(),
                        homeTeam,
                        awayTeam,
                        round,
                        matchDate,
                        1);
            }
        }

    }

}
