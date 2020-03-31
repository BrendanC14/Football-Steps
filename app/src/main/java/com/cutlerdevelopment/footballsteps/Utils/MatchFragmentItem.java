package com.cutlerdevelopment.footballsteps.Utils;

import com.cutlerdevelopment.footballsteps.Constants.MatchResult;

import java.util.Date;

public class MatchFragmentItem {

    private String matchDate;
    private String homeTeam;
    private String homeScore = "N/A";
    private String awayScore = "N/A";
    private String awayTeam;
    private CharSequence perfHeader1;
    private String perfStat1;
    private CharSequence perfHeader2;
    private String perfStat2;
    private String numSteps = "N/A";
    private int result;

    public String getMatchDate() { return matchDate; }
    public void setMatchDate(String date) {this.matchDate = date; }

    public String getHomeTeam() { return  homeTeam; }
    public void setHomeTeam(String team) { this.homeTeam = team; }

    public String getHomeScore() { return  homeScore; }
    public void setHomeScore(String score) { this.homeScore = score; }

    public String getAwayScore() { return  awayScore; }
    public void setAwayScore(String score) { this.awayScore = score; }

    public String getAwayTeam() { return  awayTeam; }
    public void setAwayTeam(String team) { this.awayTeam = team; }

    public CharSequence getPerfHeader1() { return  perfHeader1; }
    public void setPerfHeader1(CharSequence header) { this.perfHeader1 = header; }

    public String getPerfStat1() { return  perfStat1; }
    public void setPerfStat1(String stat) { this.perfStat1 = stat; }

    public CharSequence getPerfHeader2() { return  perfHeader2; }
    public void setPerfHeader2(CharSequence header) { this.perfHeader2 = header; }

    public String getPerfStat2() { return  perfStat2; }
    public void setPerfStat2(String stat) { this.perfStat2 = stat; }

    public String getNumSteps() { return  numSteps; }
    public void setNumSteps(String steps) { this.numSteps = steps; }

    public int getResult() { return result; }
    public void setResult(int result) {this.result = result; }

}
