package com.cutlerdevelopment.footballsteps.Models.ProCareer;

import com.cutlerdevelopment.footballsteps.Constants.Numbers;
import com.cutlerdevelopment.footballsteps.Constants.Position;
import com.cutlerdevelopment.footballsteps.Models.SharedModels.AppSavedData;
import com.cutlerdevelopment.footballsteps.Models.SharedModels.UserActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class PMMatchEngine {

    private static PMMatchEngine instance = null;
    public static PMMatchEngine getInstance() {
        if (instance != null) {
            return instance;
        }
        instance = new PMMatchEngine();
        return instance;
    }

    public PMMatchEngine() {

        instance = this;
    }

    PMFixture thisFixture;

    private PMAITeam homeTeam;
    private PMAITeam awayTeam;

    private List<PMMatchPlayer> homeDefensivePlayers;
    private  List<PMMatchPlayer> homeAttackingPlayers;
    private List<PMMatchPlayer> awayDefensivePlayers;
    private  List<PMMatchPlayer> awayAttackingPlayers;


    private int homeDefenseScore = 0;
    private int homeAttackScore = 0;
    private int awayDefenseScore = 0;
    private int awayAttackScore = 0;

    private int homeGoals;
    private int awayGoals;

    public void playPlayersMatch(PMFixture f) {
        thisFixture = f;
        PMSavedData sd = PMSavedData.getInstance();
        PMUserPlayer player = PMUserPlayer.getInstance();
        homeTeam = PMSavedData.getInstance().getTeamFromID(f.getHomeTeamID());
        awayTeam = PMSavedData.getInstance().getTeamFromID(f.getAwayTeamID());
        HashMap<Integer, String> eventSteps = new HashMap<>();

        homeDefensivePlayers = new ArrayList<>();
        homeAttackingPlayers = new ArrayList<>();
        awayDefensivePlayers = new ArrayList<>();
        awayAttackingPlayers = new ArrayList<>();

        homeGoals = 0;
        awayGoals = 0;

        List<Integer> minuteList = new ArrayList<>();
        for (int i = 1; i < 91; i++) {
            minuteList.add(i);
        }

        List<PMAIPlayer> homePlayers = sd.getAllOfflinePlayerFromTeam(homeTeam.getID());
        List<PMAIPlayer> awayPlayers = sd.getAllOfflinePlayerFromTeam(awayTeam.getID());

        for (PMAIPlayer aiPlayer : homePlayers) {
            addAIPlayerToLists(aiPlayer);
        }
        for (PMAIPlayer aiPlayer : awayPlayers) {
            addAIPlayerToLists(aiPlayer);
        }
        if (player.getCurrTeamID() == homeTeam.getID() || player.getCurrTeamID() == awayTeam.getID()) {
            addPlayerToList(player);
        }
        calculateDefAtkScores();
        Collections.shuffle(minuteList);

        for (int i = 0; i < homeAttackScore && i < 45; i++) {
            Collections.shuffle(homeAttackingPlayers);
            PMMatchPlayer attackingPlayer = homeAttackingPlayers.get(0);
            PMMatchPlayer defendingPlayer = null;
            if (awayDefensivePlayers.size() > 0) {
                Collections.shuffle(awayDefensivePlayers);
                defendingPlayer = awayDefensivePlayers.get(0);
            }

            if (defendingPlayer == null) {
                homeGoals++;
                eventSteps.put(minuteList.get(0), attackingPlayer.getSurname() + " the " +
                        Position.getPositionShortName(attackingPlayer.getPosition()) + " scored!");
            }
            else {
                if (attackingPlayer.getPosition() == Position.MIDFIELDER || attackingPlayer.getPosition() == Position.DEFENSIVE_MIDFIELDER) {
                    if (defendingPlayer.getPosition() == Position.DEFENDER) {
                        eventSteps.put(minuteList.get(0), attackingPlayer.getSurname() + " tries to pass..." +
                                " but " + defendingPlayer.getSurname() + " makes the tackle.");
                    }
                    else {
                        eventSteps.put(minuteList.get(0), attackingPlayer.getSurname() + " tries to cross..." +
                                " but " + defendingPlayer.getSurname() + " collects.");

                    }
                }
                else {
                    if (defendingPlayer.getPosition() == Position.DEFENDER) {
                        eventSteps.put(minuteList.get(0), attackingPlayer.getSurname() + " tries to shoot..." +
                                " but " + defendingPlayer.getSurname() + " makes the block.");
                    }
                    else {
                        eventSteps.put(minuteList.get(0), attackingPlayer.getSurname() + " tries to shoot..." +
                                " but " + defendingPlayer.getSurname() + " makes a great save.");

                    }

                }
            }

            homeAttackingPlayers.remove(attackingPlayer);
            awayDefensivePlayers.remove(defendingPlayer);
            minuteList.remove(0);

        }


        for (int i = 0; i < awayAttackScore && i < 45; i++) {
            Collections.shuffle(awayAttackingPlayers);
            PMMatchPlayer attackingPlayer = awayAttackingPlayers.get(0);
            PMMatchPlayer defendingPlayer = null;
            if (homeDefensivePlayers.size() > 0) {
                Collections.shuffle(homeDefensivePlayers);
                defendingPlayer = homeDefensivePlayers.get(0);
            }

            if (defendingPlayer == null) {
                awayGoals++;
                eventSteps.put(minuteList.get(0), attackingPlayer.getSurname() + " the " +
                        Position.getPositionShortName(attackingPlayer.getPosition()) + " scored!");
            }
            else {
                if (attackingPlayer.getPosition() == Position.MIDFIELDER || attackingPlayer.getPosition() == Position.DEFENSIVE_MIDFIELDER) {
                    if (defendingPlayer.getPosition() == Position.DEFENDER) {
                        eventSteps.put(minuteList.get(0), attackingPlayer.getSurname() + " tries to pass..." +
                                " but " + defendingPlayer.getSurname() + " makes the tackle.");
                    }
                    else {
                        eventSteps.put(minuteList.get(0), attackingPlayer.getSurname() + " tries to cross..." +
                                " but " + defendingPlayer.getSurname() + " collects.");

                    }
                }
                else {
                    if (defendingPlayer.getPosition() == Position.DEFENDER) {
                        eventSteps.put(minuteList.get(0), attackingPlayer.getSurname() + " tries to shoot..." +
                                " but " + defendingPlayer.getSurname() + " makes the block.");
                    }
                    else {
                        eventSteps.put(minuteList.get(0), attackingPlayer.getSurname() + " tries to shoot..." +
                                " but " + defendingPlayer.getSurname() + " makes a great save.");

                    }

                }
            }

            awayAttackingPlayers.remove(attackingPlayer);
            homeDefensivePlayers.remove(defendingPlayer);
            minuteList.remove(0);
        }


        f.updateScores(homeGoals, awayGoals);
        PMUserPlayer.getInstance().dateLastMatchPlayed = f.getDate();

    }


    void addAIPlayerToLists(PMAIPlayer aiPlayer) {
        Random r = new Random();
        int stepAdjustment = r.nextInt(Numbers.MATCH_STEP_ADJUSTMENT * 2) - Numbers.MATCH_STEP_ADJUSTMENT;
        double stepScore = aiPlayer.getAverageSteps() + stepAdjustment;
        stepScore /= Numbers.NUM_STEPS_FOR_MATCH_CALC;

        int minuteAdjustment = r.nextInt(Numbers.MATCH_MINUTE_ADJUSTMENT * 2) - Numbers.MATCH_MINUTE_ADJUSTMENT;
        double minuteScore = aiPlayer.getAverageMinutes() + minuteAdjustment;
        minuteScore /= Numbers.NUM_MINUTES_FOR_MATCH_CALC;
        int finalscore = (int)((stepScore) + (minuteScore));

        PMMatchPlayer player = new PMMatchPlayer(aiPlayer);

        if (player.getPosition() == Position.GOALKEEPER || player.getPosition() == Position.DEFENDER) {
            if (player.getCurrTeamID() == homeTeam.getID()) { addAIPlayer(homeDefensivePlayers, player, finalscore); }
            else { addAIPlayer(awayDefensivePlayers, player, finalscore); }
        }
        else if(player.getPosition() == Position.DEFENSIVE_MIDFIELDER) {
            if (player.getCurrTeamID() == homeTeam.getID()) { addAIPlayer(homeDefensivePlayers, player, finalscore / 2); addAIPlayer(homeAttackingPlayers, player, finalscore / 2); }
            else { addAIPlayer(awayDefensivePlayers, player, finalscore / 2); addAIPlayer(awayAttackingPlayers, player, finalscore / 2); }
        }
        else {
            if (player.getCurrTeamID() == homeTeam.getID()) { addAIPlayer(homeAttackingPlayers, player, finalscore); }
            else { addAIPlayer(awayAttackingPlayers, player, finalscore); }
        }
    }

    void addAIPlayer(List<PMMatchPlayer> list, PMMatchPlayer player, int score) {
        for (int i = 0; i < score; i++) {
            list.add(player);
        }
    }

    void addPlayerToList(PMUserPlayer player) {

        UserActivity activity = AppSavedData.getInstance().getPlayerActivityOnDate(thisFixture.getDate());

        double stepScore = 0;//activity.getSteps() / Numbers.NUM_STEPS_FOR_MATCH_CALC;
        double minuteScore =0;// activity.getActiveMinutes() / Numbers.NUM_MINUTES_FOR_MATCH_CALC;

        int finalscore = (int)((stepScore) + (minuteScore));

        PMMatchPlayer matchPlayer = new PMMatchPlayer(player);

        if (matchPlayer.getPosition() == Position.GOALKEEPER || matchPlayer.getPosition() == Position.DEFENDER) {
            if (matchPlayer.getCurrTeamID() == homeTeam.getID()) { addPlayer(homeDefensivePlayers, matchPlayer, finalscore); }
            else { addPlayer(awayDefensivePlayers, matchPlayer, finalscore); }
        }
        else if(matchPlayer.getPosition() == Position.DEFENSIVE_MIDFIELDER) {
            if (matchPlayer.getCurrTeamID() == homeTeam.getID()) { addPlayer(homeDefensivePlayers, matchPlayer, finalscore); addPlayer(homeAttackingPlayers, matchPlayer, finalscore); }
            else { addPlayer(awayDefensivePlayers, matchPlayer, finalscore / 2); addPlayer(awayAttackingPlayers, matchPlayer, finalscore / 2); }
        }
        else {
            if (matchPlayer.getCurrTeamID() == homeTeam.getID()) { addPlayer(homeAttackingPlayers, matchPlayer, finalscore); }
            else { addPlayer(awayAttackingPlayers, matchPlayer, finalscore); }
        }

    }

    void addPlayer(List<PMMatchPlayer> list, PMMatchPlayer player, int score) {
        for (int i = 0; i < score; i++) {
            list.add(player);
        }
    }

    void calculateDefAtkScores() {

        homeDefenseScore = homeDefensivePlayers.size();
        homeAttackScore = homeAttackingPlayers.size();
        awayDefenseScore = awayDefensivePlayers.size();
        awayAttackScore = awayAttackingPlayers.size();
    }
}
