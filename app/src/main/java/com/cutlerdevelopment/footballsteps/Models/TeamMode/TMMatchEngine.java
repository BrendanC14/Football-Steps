package com.cutlerdevelopment.footballsteps.Models.TeamMode;

import com.cutlerdevelopment.footballsteps.Constants.Numbers;
import com.cutlerdevelopment.footballsteps.Models.SharedModels.AppSavedData;
import com.cutlerdevelopment.footballsteps.R;
import com.cutlerdevelopment.footballsteps.Utils.DateHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class TMMatchEngine {

    public static void playUserMatch(TMFixture fix) {

        List<Date> datesToCheck = new ArrayList<>();
        for (int i = 1; i <= TMSettings.getInstance().getDaysBetweenGames(); i++) {
            datesToCheck.add(DateHelper.addDays(fix.getDate(), i * -1));
        }
        Collections.shuffle(datesToCheck);

        int attackingSteps = 0;
        int attackingDays = 0;

        for (int i = 0; i < TMSettings.getInstance().getDaysBetweenGames() / 2; i++) {
            attackingSteps += AppSavedData.getInstance().getPlayerActivityOnDate(datesToCheck.get(i)).getSteps();
            attackingDays++;

        }
        for (int i = 0; i < attackingDays; i++) {
            datesToCheck.remove(i);
        }

        int defendingSteps = 0;
        int defendingDays = 0;

        for (int i = 0; i < TMSettings.getInstance().getDaysBetweenGames() / 2; i++) {
            defendingSteps += AppSavedData.getInstance().getPlayerActivityOnDate(datesToCheck.get(i)).getSteps();
            defendingDays++;
        }

        int attackingAverage = attackingSteps / attackingDays;
        int defendingAverage = defendingSteps / defendingDays;

        int goals = getGoals(attackingAverage, fix.getStepTarget(), fix.getStepTarget() / Numbers.TM_PERC_FOR_GOAL);
        int conceded = getConceded(defendingAverage, fix.getStepTarget(), fix.getStepTarget() / Numbers.TM_PERC_FOR_GOAL);


        int homeScore;
        int awayScore;
        if (fix.getHomeTeamID() == TMUserTeam.getInstance().getTeamID()) {
            homeScore = goals;
            awayScore = conceded;
        }
        else {
            homeScore = conceded;
            awayScore = goals;
        }

        fix.updateScores(homeScore, awayScore);

        for(TMFixture f : TMSavedData.getInstance().getWeeksFixtureFromLeague(fix.getDate(), fix.getLeague())) {
            if (!f.matchPlayed()) {
                playOtherGames(f);
            }
        }
    }

    static int getGoals(int attackingScore, int defendingScore, int amountNeededForGoal) {
        int goals = (attackingScore - defendingScore + amountNeededForGoal) / amountNeededForGoal;

        if (goals < 0) { goals =0; }
        else if (goals > 10) { goals = 10; }

        return goals;
    }

    static int getConceded(int defendingScore, int attackingScore, int amountNeededForGoal) {
        int conceded = (defendingScore - attackingScore - amountNeededForGoal) / amountNeededForGoal;

        if (conceded > 0) { conceded = 0; }
        else if (conceded < -10) { conceded = -10; }

        conceded *= -1;
        return conceded;
    }


     static void playOtherGames(TMFixture fix) {

        int homeAttackingScore = getAIRandomStep();
        int homeDefendingScore = getAIRandomStep();
        int awayAttackingScore = getAIRandomStep();
        int awayDefendingScore = getAIRandomStep();

        int amountNeededForGoal = fix.getStepTarget() / Numbers.TM_PERC_FOR_GOAL;

        int homeGoals = getGoals(homeAttackingScore, awayDefendingScore, amountNeededForGoal);
        int awayGoals = getGoals(awayAttackingScore, homeDefendingScore, amountNeededForGoal);

        fix.updateScores(homeGoals, awayGoals);

     }

     static int getAIRandomStep() {
        int target = TMSettings.getInstance().getStepTarget();
        int mod = target - ((target / Numbers.TM_PERC_FOR_GOAL) * 3);
        Random r = new Random();
        return (r.nextInt(target)) + mod;
     }
}
