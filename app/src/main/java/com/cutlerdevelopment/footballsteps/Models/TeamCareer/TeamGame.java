package com.cutlerdevelopment.footballsteps.Models.TeamCareer;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.cutlerdevelopment.footballsteps.Constants.Colour;
import com.cutlerdevelopment.footballsteps.Constants.Words;
import com.cutlerdevelopment.footballsteps.Mocks.GoogleFITAPIMock;
import com.cutlerdevelopment.footballsteps.Models.SharedModels.AppSavedData;
import com.cutlerdevelopment.footballsteps.Models.ProCareer.ProFixture;
import com.cutlerdevelopment.footballsteps.Models.SharedModels.PlayerActivity;
import com.cutlerdevelopment.footballsteps.Models.ProCareer.ProAITeam;
import com.cutlerdevelopment.footballsteps.Utils.DateHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Entity
public class TeamGame {

    private static TeamGame instance = null;
    public static TeamGame getInstance() {
        if (instance != null) {
            return instance;
        }
        return null;
    }

    public TeamGame() {

        //refreshPlayerActivity();
        instance = this;
    }

    public void startNewGame() {

        Date today = new Date();

        //TODO: Take out hardcoding once tested
        this.startDate = DateHelper.addDays(today, -14);


        for (String teamName : Words.teamNames) {
            TeamAITeam t = new TeamAITeam(teamName, Colour.getDefaultColourForTeam(teamName), 1);
        }
        createFixtures();

        OfflineTeamSavedData.getInstance().saveObject(this);

    }

    @PrimaryKey
    private Date startDate;
    public Date getStartDate() { return startDate; }
    public void setStartDate(Date date) { startDate = date; }
    public void changeStartDate(Date date) {
        startDate = date;
        OfflineTeamSavedData.getInstance().updateObject(this);
    }

    private int season;
    public int getSeason() { return  season; }
    public void setSeason(int newSeason) { season  = newSeason; }
    public void changeSeason(int newSeason) {
        season = newSeason;
        OfflineTeamSavedData.getInstance().updateObject(this);
    }

    void createFixtures() {

        List<Integer> availableWeeks = new ArrayList<>();
        for (int i = 0; i < 19; i++) {
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

                if (match == 0) {awayTeam = 20; }

                Date matchDate = DateHelper.addDays(startDate, round);
                new TeamFixture(OfflineTeamSavedData.getInstance().getNumRowsFromFixtureTable(),
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

                if (match == 0) { homeTeam = 20; }

                Date matchDate = DateHelper.addDays(startDate, round);
                new TeamFixture(OfflineTeamSavedData.getInstance().getNumRowsFromFixtureTable(),
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

        PlayerActivity lastActivity = AppSavedData.getInstance().getLastAddedActivity();
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
            HashMap<Date, HashMap<Integer, Integer>> dateNumbersMap = GoogleFITAPIMock.getInstance().getStepsForDates(datesToCheck);

            for (HashMap.Entry<Date, HashMap<Integer, Integer>> pair : dateNumbersMap.entrySet()) {
                for (HashMap.Entry<Integer, Integer> numberPair : pair.getValue().entrySet()) {
                    new PlayerActivity(pair.getKey(), numberPair.getKey(), numberPair.getValue());
                }
            }
        }
    }
}
