package com.cutlerdevelopment.footballsteps.Constants;

public class Colour {

    public static final int RED = 1;
    public static final int BLUE = 2;
    public static final int WHITE = 3;
    public static final int ORANGE = 4;
    public static final int GREEN = 5;
    public static final int YELLOW = 6;
    public static final int BLACK = 7;
    public static final int NUMCOLOURS = 7;


    public String getColourName(int colour) {
        switch (colour) {
            case 1:
                return "Red";
            case 2:
                return "Blue";
            case 3:
                return "White";
            case 4:
                return "Orange";
            case 5:
                return "Green";
            case 6:
                return "Yellow";
            case 7:
                return "Black";
        }
        return "Unknown";
    }

}
