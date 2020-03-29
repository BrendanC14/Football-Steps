package com.cutlerdevelopment.footballsteps.Constants;

/**
 * This is a Constant class for the different positions available. It has methods to return the long/short Strings for each position
 */
public class Position {
    public static final int GOALKEEPER = 1;
    public static final int DEFENDER = 2;
    public static final int MIDFIELDER =3 ;
    public static final int ATTACKER = 4;
    public static final int NUMPOSITIONS = 4;

    public static String getPositionLongName(int pos) {
        switch (pos) {
            case 1:
                return "Goalkeeper";
            case 2:
                return "Defender";
            case 3:
                return "Midfielder";
            case 4:
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
                return "MID";
            case 4:
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
            case "MID":
                return 3;
            case "ATK":
                return 4;
        }
        return 1;
    }

}
