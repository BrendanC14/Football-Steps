package com.cutlerdevelopment.footballsteps.Constants;

import android.util.Pair;

import com.cutlerdevelopment.footballsteps.Models.ProCareer.ProSettings;

import java.util.Random;

/**
 * This is a constant class responsible for holding default numbers
 */
public class Numbers {

    //Mocks
    public static final int MOCK_MIN_STEPS = 5000;
    public static final int MOCK_MAX_STEPS = 20000;
    public static final int MOCK_MIN_AMINUTES = 30;
    public static final int MOCK_MAX_AMINUTES = 120;


    //Settings
    public static final int MAX_NUM_STEPS_TARGET = 20;
    public static final int POINTS_FOR_WIN = 3;
    public static final int POINTS_FOR_DRAW = 1;
    public static final int POINTS_FOR_LOSS = 0;

    public static final int NUM_STEPS_FOR_MATCH_CALC = 2000;
    public static final int NUM_MINUTES_FOR_MATCH_CALC = 30;

    public static final int MATCH_STEP_ADJUSTMENT = 1000;
    public static final int MATCH_MINUTE_ADJUSTMENT = 30;

    public static final int DEFAULT_STARTING_AGE = 16;
    public static final int DEFAULT_LEAGUE_4__MIN_STEPS = 6000;
    public static final int DEFAULT_LEAGUE_4__MAX_STEPS = 7000;
    public static final int DEFAULT_LEAGUE_3__MIN_STEPS = 7000;
    public static final int DEFAULT_LEAGUE_3__MAX_STEPS = 8000;
    public static final int DEFAULT_LEAGUE_2__MIN_STEPS = 8000;
    public static final int DEFAULT_LEAGUE_2__MAX_STEPS = 9000;
    public static final int DEFAULT_LEAGUE_1__MIN_STEPS = 9000;
    public static final int DEFAULT_LEAGUE_1__MAX_STEPS = 10000;
    public static final int MEDIUM_STEP_INCREASE = 1000;
    public static final int HARD_STEP_INCREASE = 2000;


    public static final int DEFAULT_LEAGUE_4__MIN_MINUTES = 30;
    public static final int DEFAULT_LEAGUE_4__MAX_MINUTES = 40;
    public static final int DEFAULT_LEAGUE_3__MIN_MINUTES = 40;
    public static final int DEFAULT_LEAGUE_3__MAX_MINUTES = 50;
    public static final int DEFAULT_LEAGUE_2__MIN_MINUTES = 50;
    public static final int DEFAULT_LEAGUE_2__MAX_MINUTES = 60;
    public static final int DEFAULT_LEAGUE_1__MIN_MINUTES = 60;
    public static final int DEFAULT_LEAGUE_1__MAX_MINUTES = 70;
    public static final int MEDIUM_MINUTE_INCREASE = 10;
    public static final int HARD_MINUTE_INCREASE = 20;

    public static final int LEAGUE_BALANCE_STARTING_STEP_CHANGE = 2000;
    public static final int LEAGUE_BALANCE_SUBSEQUENT_STEP_CHANGE = 200;
    public static final int LEAGUE_BALANCE_STARTING_MINUTE_CHANGE = 20;
    public static final int LEAGUE_BALANCE_SUBSEQUENT_MINUTE_CHANGE = 2;


    public static Pair<Integer, Integer> getRandomStartingNumbers(int league) {
        Random r = new Random();
        int stepAverage = DEFAULT_LEAGUE_1__MIN_STEPS;
        int minuteAverage = DEFAULT_LEAGUE_1__MIN_MINUTES;
        switch (league) {
            case 1:
                stepAverage = r.nextInt(DEFAULT_LEAGUE_1__MAX_STEPS - DEFAULT_LEAGUE_1__MIN_STEPS) + DEFAULT_LEAGUE_1__MIN_STEPS;
                minuteAverage = r.nextInt(DEFAULT_LEAGUE_1__MAX_MINUTES - DEFAULT_LEAGUE_1__MIN_MINUTES) + DEFAULT_LEAGUE_1__MIN_MINUTES;
                break;
            case 2:
                stepAverage = r.nextInt(DEFAULT_LEAGUE_2__MAX_STEPS - DEFAULT_LEAGUE_2__MIN_STEPS) + DEFAULT_LEAGUE_2__MIN_STEPS;
                minuteAverage = r.nextInt(DEFAULT_LEAGUE_2__MAX_MINUTES - DEFAULT_LEAGUE_2__MIN_MINUTES) + DEFAULT_LEAGUE_2__MIN_MINUTES;
                break;
            case 3:
                stepAverage = r.nextInt(DEFAULT_LEAGUE_3__MAX_STEPS - DEFAULT_LEAGUE_3__MIN_STEPS) + DEFAULT_LEAGUE_3__MIN_STEPS;
                minuteAverage = r.nextInt(DEFAULT_LEAGUE_3__MAX_MINUTES - DEFAULT_LEAGUE_3__MIN_MINUTES) + DEFAULT_LEAGUE_3__MIN_MINUTES;
                break;
            case 4:
                stepAverage = r.nextInt(DEFAULT_LEAGUE_4__MAX_STEPS - DEFAULT_LEAGUE_4__MIN_STEPS) + DEFAULT_LEAGUE_4__MIN_STEPS;
                minuteAverage = r.nextInt(DEFAULT_LEAGUE_4__MAX_MINUTES - DEFAULT_LEAGUE_4__MIN_MINUTES) + DEFAULT_LEAGUE_4__MIN_MINUTES;
                break;
        }
        stepAverage += ProSettings.getInstance().getStepDifficultyModifier();
        minuteAverage += ProSettings.getInstance().getMinuteDifficultyModifier();
        Pair<Integer, Integer> pair = new Pair<>(stepAverage, minuteAverage);
        return pair;
    }

    public static int getTeamStepBalance(String teamName) {
        switch (teamName) {
            case "Arsenal":
                return 0;
            case "Liverpool":
                return 0;
            case "Manchester City":
                return 0;
            case "Leicester City":
                return 0;
            case "Chelsea":
                return 0;
            case "Manchester United":
                return 0;
            case "Wolves":
                return 0;
            case "Sheffield United":
                return 0;
            case "Tottenham Hotspur":
                return 0;
            case "Burnley":
                return 0;
            case "Crystal Palace":
                return 0;
            case "Everton":
                return 0;
            case "Newcastle United":
                return 0;
            case "Southampton":
                return 0;
            case "Brighton":
                return 0;
            case "West Ham":
                return 0;
            case "Watford":
                return 0;
            case "Bournemouth":
                return 0;
            case "Aston Villa":
                return 0;
            case "Norwich":
                return 0;

        }
        return 0;
    }

    public static int getTeamMinuteBalance(String teamName) {
        switch (teamName) {
            case "Arsenal":
                return 0;
            case "Liverpool":
                return 0;
            case "Manchester City":
                return 0;
            case "Leicester City":
                return 0;
            case "Chelsea":
                return 0;
            case "Manchester United":
                return 0;
            case "Wolves":
                return 0;
            case "Sheffield United":
                return 0;
            case "Tottenham Hotspur":
                return 0;
            case "Burnley":
                return 0;
            case "Crystal Palace":
                return 0;
            case "Everton":
                return 0;
            case "Newcastle United":
                return 0;
            case "Southampton":
                return 0;
            case "Brighton":
                return 0;
            case "West Ham":
                return 0;
            case "Watford":
                return 0;
            case "Bournemouth":
                return 0;
            case "Aston Villa":
                return 0;
            case "Norwich":
                return 0;

        }
        return 0;
    }
}
