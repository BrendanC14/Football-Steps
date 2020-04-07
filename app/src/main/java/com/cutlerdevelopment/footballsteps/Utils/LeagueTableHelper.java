package com.cutlerdevelopment.footballsteps.Utils;

import com.cutlerdevelopment.footballsteps.Models.TeamMode.TMSavedData;
import com.cutlerdevelopment.footballsteps.Models.TeamMode.TMTeam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LeagueTableHelper {

    public static List<TMTeam> getTMLeagueList(int leagueID) {
        List<TMTeam> allTeams = TMSavedData.getInstance().getAllTeamsInLeague(leagueID);

        Collections.sort(allTeams, new Comparator<TMTeam>() {
            @Override
            public int compare(TMTeam tmTeam, TMTeam t1) {
                int value1 = tmTeam.getPoints() - t1.getPoints();
                if (value1 == 0) {
                    int value2 = tmTeam.getGoalDifference() - t1.getGoalDifference();
                    if (value2 == 0) {
                        int value3 = tmTeam.getWins() - t1.getWins();
                        if (value3 == 0) {
                            return tmTeam.getName().compareTo(t1.getName());
                        }
                        return value3;
                    }
                    return value2;
                }
                return value1;
            }
        });

        return allTeams;
    }

    public static int getPositionInLeague(int teamID, int leagueID) {
        List<TMTeam> teams = getTMLeagueList(leagueID);
        //TODO: This isn't great...
        List<Integer> idList = new ArrayList<>();
        for (TMTeam t : teams) {
            idList.add(t.getID());
        }
        return idList.indexOf(teamID) + 1;
    }

    public static TMTeam getTeamAtPosition(int position, int leagueID) {
        List<TMTeam> teams = getTMLeagueList(leagueID);
        return teams.get(position - 1);
    }


    public static List<TMTeam> getLeagueNeighbours(TMTeam team, int numOfNeighbours) {

        List<TMTeam> neighbouringTeams = new ArrayList<>();
        List<TMTeam> allTeams = getTMLeagueList(team.getLeague());
        int homePosition = getPositionInLeague(team.getID(), team.getLeague());

        if (homePosition == 1) {
            neighbouringTeams.add(allTeams.get(homePosition - 1));
            neighbouringTeams.add(allTeams.get(homePosition));
            neighbouringTeams.add(allTeams.get(homePosition + 1));
            neighbouringTeams.add(allTeams.get(homePosition + 2));
            neighbouringTeams.add(allTeams.get(homePosition + 3));
        }
        else if (homePosition == 2) {
            neighbouringTeams.add(allTeams.get(homePosition - 2));
            neighbouringTeams.add(allTeams.get(homePosition - 1));
            neighbouringTeams.add(allTeams.get(homePosition));
            neighbouringTeams.add(allTeams.get(homePosition + 1));
            neighbouringTeams.add(allTeams.get(homePosition + 2));
        }

        else if (homePosition == allTeams.size() - 1) {
            neighbouringTeams.add(allTeams.get(homePosition - 4));
            neighbouringTeams.add(allTeams.get(homePosition - 3));
            neighbouringTeams.add(allTeams.get(homePosition - 2));
            neighbouringTeams.add(allTeams.get(homePosition - 1));
            neighbouringTeams.add(allTeams.get(homePosition));
        }
        else if (homePosition == allTeams.size()) {
            neighbouringTeams.add(allTeams.get(homePosition - 5));
            neighbouringTeams.add(allTeams.get(homePosition - 4));
            neighbouringTeams.add(allTeams.get(homePosition - 3));
            neighbouringTeams.add(allTeams.get(homePosition - 2));
            neighbouringTeams.add(allTeams.get(homePosition - 1));
        }
        else {
            neighbouringTeams.add(allTeams.get(homePosition - 3));
            neighbouringTeams.add(allTeams.get(homePosition - 2));
            neighbouringTeams.add(allTeams.get(homePosition - 1));
            neighbouringTeams.add(allTeams.get(homePosition ));
            neighbouringTeams.add(allTeams.get(homePosition + 1));

        }

        return neighbouringTeams;
    }
}
