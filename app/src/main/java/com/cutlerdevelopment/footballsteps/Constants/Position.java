package com.cutlerdevelopment.footballsteps.Constants;

/**
 * This is a Constant class for the different positions available. It has methods to return the long/short Strings for each position
 */
public class Position {
    public static final int GOALKEEPER = 1;
    public static final int DEFENDER = 2;
    public static final int DEFENSIVE_MIDFIELDER = 3;
    public static final int MIDFIELDER =4 ;
    public static final int ATTACKER = 5;
    public static final int NUMPOSITIONS = 5;

    public static String getPositionLongName(int pos) {
        switch (pos) {
            case 1:
                return "Goalkeeper";
            case 2:
                return "Defender";
            case 3:
                return "Defensive Midfielder";
            case 4:
                return "Midfielder";
            case 5:
                return "Attacker";
        }
        return "";
    }

    public static String getPositionShortName(int pos) {
        switch (pos) {
            case 1:
                return "GK";
            case 2:
                return "DEF";
            case 3:
                return "DM";
            case 4:
                return "MID";
            case 5:
                return "ATK";
        }
        return "";
    }

    public static int getPositionFromShortString(String pos) {
        switch (pos) {
            case "GK":
                return 1;
            case "DEF":
                return 2;
            case "DM":
                return 3;
            case "MID":
                return 4;
            case "ATK":
                return 5;
        }
        return 1;
    }

}
