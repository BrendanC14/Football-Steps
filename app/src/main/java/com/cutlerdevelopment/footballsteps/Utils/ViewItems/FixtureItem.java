package com.cutlerdevelopment.footballsteps.Utils.ViewItems;

import android.widget.Button;

public class FixtureItem {

    private int matchID;
    private String matchDate;
    private String opponentTeam;
    private String numSteps = "N/A";
    private int result;
    boolean selected;

    public int getMatchID() { return matchID; }
    public void setMatchID(int id) {this.matchID = id; }

    public String getMatchDate() { return matchDate; }
    public void setMatchDate(String date) {this.matchDate = date; }

    public String getOpponentTeam() { return opponentTeam; }
    public void setOpponentTeam(String team) { this.opponentTeam = team; }

    public String getNumSteps() { return  numSteps; }
    public void setNumSteps(String steps) { this.numSteps = steps; }

    public int getResult() { return result; }
    public void setResult(int result) {this.result = result; }

    public boolean getSelected() { return selected; }
    public void setSelected(boolean selected) { this.selected = selected;}

}
