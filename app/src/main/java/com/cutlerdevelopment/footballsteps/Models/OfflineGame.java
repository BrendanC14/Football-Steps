package com.cutlerdevelopment.footballsteps.Models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.cutlerdevelopment.footballsteps.Constants.Colour;
import com.cutlerdevelopment.footballsteps.Constants.Words;
import com.cutlerdevelopment.footballsteps.Mocks.GoogleFITAPIMock;
import com.cutlerdevelopment.footballsteps.Utils.DateHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

@Entity
public class OfflineGame {



    private static OfflineGame instance = null;
    public static OfflineGame getInstance() {
        if (instance != null) {
            return instance;
        }
        return null;
    }

    public OfflineGame() {

        //refreshPlayerActivity();
        instance = this;
    }

    public void startNewGame() {

        Date today = new Date();

        //TODO: Take out hardcoding once tested
        this.startDate = DateHelper.addDays(today, -14);

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

                Date matchDate = DateHelper.addDays(startDate, gameweek);
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

    public void refreshPlayerActivity() {
        Date lastActivityDate = startDate;

        PlayerActivity lastActivity = SavedData.getInstance().getLastAddedActivity();
        if (lastActivity != null) {
            lastActivityDate = lastActivity.getDate();
        }

        Date today = new Date();
        int daysBetween = DateHelper.getDaysBetween(lastActivityDate,today);
        if (daysBetween < 0) {
            List<Date> datesToCheck = new ArrayList<>();
            for (int i = daysBetween; i < 0; i++) {
                datesToCheck.add(DateHelper.addDays(today, i));
            }
            //TODO: Integrate real service
            HashMap<Date, Integer> dateStepMap = GoogleFITAPIMock.getInstance().getStepsForDates(datesToCheck);

            for (HashMap.Entry<Date, Integer> pair : dateStepMap.entrySet()) {
                new PlayerActivity(pair.getKey(), pair.getValue());
            }
        }
    }

}
