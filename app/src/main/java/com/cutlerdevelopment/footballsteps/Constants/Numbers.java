package com.cutlerdevelopment.footballsteps.Constants;

import android.util.Pair;

import com.cutlerdevelopment.footballsteps.Models.OfflineSettings;

import java.util.HashMap;
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
    public static final int DEFAULT_STARTING_AGE = 16;
    public static final int DEFAULT_LEAGUE_4__MIN_STEPS = 2000;
    public static final int DEFAULT_LEAGUE_4__MAX_STEPS = 4000;
    public static final int DEFAULT_LEAGUE_3__MIN_STEPS = 4000;
    public static final int DEFAULT_LEAGUE_3__MAX_STEPS = 6000;
    public static final int DEFAULT_LEAGUE_2__MIN_STEPS = 6000;
    public static final int DEFAULT_LEAGUE_2__MAX_STEPS = 8000;
    public static final int DEFAULT_LEAGUE_1__MIN_STEPS = 8000;
    public static final int DEFAULT_LEAGUE_1__MAX_STEPS = 10000;
    public static final int MEDIUM_STEP_INCREASE = 1000;
    public static final int HARD_STEP_INCREASE = 1000;


    public static final int DEFAULT_LEAGUE_4__MIN_MINUTES = 0;
    public static final int DEFAULT_LEAGUE_4__MAX_MINUTES = 20;
    public static final int DEFAULT_LEAGUE_3__MIN_MINUTES = 40;
    public static final int DEFAULT_LEAGUE_3__MAX_MINUTES = 60;
    public static final int DEFAULT_LEAGUE_2__MIN_MINUTES = 60;
    public static final int DEFAULT_LEAGUE_2__MAX_MINUTES = 80;
    public static final int DEFAULT_LEAGUE_1__MIN_MINUTES = 80;
    public static final int DEFAULT_LEAGUE_1__MAX_MINUTES = 100;
    public static final int MEDIUM_MINUTE_INCREASE = 10;
    public static final int HARD_MINUTE_INCREASE = 20;
    


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
        stepAverage += OfflineSettings.getInstance().getStepDifficultyModifier();
        minuteAverage += OfflineSettings.getInstance().getMinuteDifficultyModifier();
        Pair<Integer, Integer> pair = new Pair<>(stepAverage, minuteAverage);
        return pair;
    }

}
