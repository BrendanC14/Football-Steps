package com.cutlerdevelopment.footballsteps.Models.TeamCareer;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.cutlerdevelopment.footballsteps.Constants.Colour;
import com.cutlerdevelopment.footballsteps.Constants.MatchResult;

@Entity
public class TeamAITeam {

    /**
     * Used when creating a new Team when a new game is created or a custom team is added
     * @param name name of the club
     * @param colour colour of the club
     */
    @Ignore
    public TeamAITeam(String name, int colour, int league) {
        this.ID = OfflineTeamSavedData.getInstance().getNumRowsFromTeamTable() + 1;
        this.name = name;
        this.colour = colour;
        this.league = league;

        OfflineTeamSavedData.getInstance().saveObject(this);
    }

    /**
     * Used when loading a player for the solo career. Doesn't take any variables as takes them from RoomDB
     */
    public TeamAITeam() {

    }

    @PrimaryKey
    private int ID;
    public int getID() { return ID; }
    public void setID(int id) { this.ID = id;}


    private String name;
    public String getName() {return  name; }
    public void setName(String newName) { name = newName; }
    public void changeName(String newName) {
        name = newName;
        OfflineTeamSavedData.getInstance().updateObject(this);
    }

    private int colour;
    public int getColour() { return colour;}
    public void setColour(int newColour) { colour = newColour; }
    public void changeColour(int newColour) {
        if (newColour >= 1 && newColour <= Colour.NUM_TEAM_COLOURS) {
            colour = newColour;
            OfflineTeamSavedData.getInstance().updateObject(this);
        }
    }

    private int league;
    public int getLeague() { return league; }
    public void setLeague(int newLeague) { this.league = league; }
    public void changeLeague(int newLeague) {
        this.league = newLeague;
        OfflineTeamSavedData.getInstance().updateObject(league);
    }

    private int points;
    public int getPoints() { return points;}
    public void setPoints(int pts) { this.points = pts; }
    public void addPoints(int pts) { points += pts; }

    private int wins;
    public int getWins() { return wins; }
    public void setWins(int w) { this.wins = w; }
    public void addWin() {
        wins++;
        addPoints(OfflineTeamSettings.getInstance().getPointsForWin());
    }

    private int draws;
    public int getDraws() { return draws; }
    public void setDraws(int d) { this.draws = d; }
    public void addDraw() {
        draws++;
        addPoints(OfflineTeamSettings.getInstance().getPointsForDraw());
    }

    private int losses;
    public int getLosses() { return losses; }
    public void setLosses(int l) { this.losses = l; }
    public void addLoss() {
        losses++;
        addPoints(OfflineTeamSettings.getInstance().getPointsForLoss());
    }

    private int scored;
    public int getScored() { return this.scored; }
    public void setScored(int scored) {this.scored = scored; }
    public void addGoals(int goals) {
        this.scored += goals;

    }

    private int conceded;
    public int getConceded() { return this.conceded; }
    public void setConceded(int conceded) { this.conceded = conceded; }
    public void concedeGoals( int conceded) { this.conceded += conceded; }

    public void playMatch(int matchResult, int scored, int conceded) {
        addGoals(scored);
        concedeGoals(conceded);
        if (matchResult == MatchResult.DRAW) { addDraw(); }
        else if (matchResult == MatchResult.WIN) { addWin(); }
        else { addLoss(); }

        OfflineTeamSavedData.getInstance().updateObject(this);


    }
}
