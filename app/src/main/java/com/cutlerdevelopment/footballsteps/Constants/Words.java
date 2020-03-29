package com.cutlerdevelopment.footballsteps.Constants;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This is a constant class responsible for holding the default words, and also the key's to communicate with SharedPreferences
 */
public class Words {


    public static final List<String> TeamNames = Arrays.asList("Arsenal", "Liverpool", "Manchester City", "Leicester City", "Chelsea",
            "Manchester United", "Wolves", "Sheffield United", "Tottenham Hotspur", "Burnley", "Crystal Palace", "Everton",
            "Newcastle United", "Southampton", "Brighton", "West Ham", "Watford", "Bournemouth", "Aston Villa", "Norwich");


    //*****************************************SavedData*****************************************
    //Player
    public static final String SD_OFFLINEPLAYER_FIRST_NAME_KEY = "OPFirstName";
    public static final String SD_OFFLINEPLAYER_SURNAME_KEY = "OPSurname";
    public static final String SD_OFFLINEPLAYER_AGE_KEY = "OPAge";
    public static final String SD_OFFLINEPLAYER_POSITION_KEY = "OPPosition";
    public static final String SD_OFFLINEPLAYER_CURRTEAM_KEY = "OPCurrTeam";
    public static final String SD_OFFLINEPLAYER_FAVTEAM_KEY = "OPFavTeam";


    //Settings
    public static final String SD_OFFLINEPLAYER_STARTING_AGE_KEY = "OPStartingAge";


}
