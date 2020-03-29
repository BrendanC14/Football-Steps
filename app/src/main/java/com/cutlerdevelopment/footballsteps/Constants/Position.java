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

    public String getPositionLongName(int pos) {
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

    public String getPositionShortName(int pos) {
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


}
