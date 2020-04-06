package com.cutlerdevelopment.footballsteps.Models.TeamCareer;

import android.content.Context;

import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.Update;

import com.cutlerdevelopment.footballsteps.Constants.Words;
import com.cutlerdevelopment.footballsteps.Models.TeamCareer.TeamFixture;
import com.cutlerdevelopment.footballsteps.Models.TeamCareer.TeamAITeam;
import com.cutlerdevelopment.footballsteps.Utils.Converters;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class OfflineTeamSavedData {


    public static void createOfflineTeamSavedData(Context c) {
        new OfflineTeamSavedData(c);
    }
    private static OfflineTeamSavedData instance = null;

    public static OfflineTeamSavedData getInstance() {
        if (instance != null) {
            return instance;
        }
        return null;
    }

    private OfflineTeamSavedData(Context context) {
        instance = this;
        //TODO: stop allowing Main Thread Queries and figure out a better way to load a game
        db = Room.databaseBuilder(context, AppDatabase.class, Words.OFFLINE_TEAM_CAREER_ROOM_DATABASE_NAME).allowMainThreadQueries().build();
    }

    /**
     * App Database class is the Room Database that contains the instances of the Dao interfaces.
     */
    @Database(entities = {UserTeam.class, TeamAITeam.class, OfflineTeamSettings.class,
            TeamGame.class, TeamFixture.class}, version = 1)
    @TypeConverters({Converters.class})
    public static abstract class AppDatabase extends RoomDatabase {


        public abstract UserTeamDao userTeamDao();
        public abstract OfflineTeamSettingsDao offlineTeamSettingsDao();
        public abstract TeamGameDao teamGameDao();
        public abstract TeamDao teamDao();
        public abstract FixtureDao fixtureDao();
    }

    private static AppDatabase db;

    @Dao
    public interface UserTeamDao {

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        void insertUserTeam(UserTeam team);

        @Update
        void updateUserTeam(UserTeam team);

        @Delete
        void deleteUserTeam(UserTeam team);

        @Query("SELECT * FROM UserTeam")
        UserTeam[] selectUserTeam();


    }

    @Dao
    public interface OfflineTeamSettingsDao {

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        void insertTeamSettings(OfflineTeamSettings settings);
        @Update
        void updateTeamSettings(OfflineTeamSettings settings);
        @Delete
        void deleteTeamSettings(OfflineTeamSettings settings);

        @Query("SELECT * FROM OfflineTeamSettings")
        OfflineTeamSettings[] selectTeamSettings();

    }

    @Dao
    public interface TeamGameDao {
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        void insertTeamGame(TeamGame game);
        @Update
        void updateTeamGame(TeamGame game);
        @Delete
        void deleteTeamGame(TeamGame game);

        @Query("SELECT * FROM TeamGame")
        TeamGame[] selectTeamGame();
    }

    @Dao
    public interface TeamDao {
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        void insertTeams(TeamAITeam teams);
        @Update
        void updateTeam(TeamAITeam teams);
        @Delete
        void deleteTeam(TeamAITeam teams);

        @Query("SELECT * FROM TeamAITeam")
        TeamAITeam[] selectAllTeams();
        @Query("SELECT * FROM TeamAITeam WHERE league = :league")
        TeamAITeam[] selectAllTeamsInLeague(int league);
        @Query("SELECT * FROM TeamAITeam WHERE id = :teamID")
        TeamAITeam selectTeamFromID(int teamID);
        @Query("SELECT * FROM TeamAITeam WHERE name = :teamName")
        TeamAITeam selectTeamFromName(String teamName);
        @Query("SELECT COUNT(id) FROM TeamAITeam")
        int getRowCount();
    }
    @Dao
    public interface  FixtureDao {
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        void insertFixtures(TeamFixture fixture);
        @Update
        void updateFixture(TeamFixture fixture);
        @Delete
        void deleteFixture(TeamFixture fixture);

        @Query("SELECT * FROM TeamFixture")
        TeamFixture[] selectAllFixtures();
        @Query("SELECT * FROM TeamFixture WHERE id = :fixtureID")
        TeamFixture selectFixtureFromID(int fixtureID);
        @Query("SELECT * FROM TeamFixture WHERE date = :date AND league = :league")
        TeamFixture[] selectAllFixturesInLeagueFromDate(Date date, int league);
        @Query("SELECT * FROM TeamFixture WHERE homeTeamID = :teamID OR awayTeamID = :teamID")
        TeamFixture[] selectAllFixturesFromTeam(int teamID);
        @Query("SELECT * FROM TeamFixture WHERE week = :week AND (homeTeamID = :teamID OR awayTeamID = :teamID)")
        TeamFixture selectFixtureForWeekWithTeam(int week, int teamID);
        @Query("SELECT COUNT(id) FROM TeamFixture")
        int getRowCount();
    }



    public void saveObject(Object obj) {
        if (obj instanceof UserTeam) {
            UserTeam team = (UserTeam) obj;
            db.userTeamDao().insertUserTeam(team);
        }
        else if (obj instanceof OfflineTeamSettings) {
            OfflineTeamSettings settings = (OfflineTeamSettings) obj;
            db.offlineTeamSettingsDao().insertTeamSettings(settings);
        }
        else if (obj instanceof TeamGame) {
            TeamGame game = (TeamGame) obj;
            db.teamGameDao().insertTeamGame(game);
        }
        else if (obj instanceof TeamAITeam) {
            TeamAITeam team = (TeamAITeam) obj;
            db.teamDao().insertTeams(team);
        }
        else if (obj instanceof TeamFixture) {
            TeamFixture fix = (TeamFixture) obj;
            db.fixtureDao().insertFixtures(fix);
        }
    }

    public void updateObject(Object obj) {
        if (obj instanceof UserTeam) {
            UserTeam team = (UserTeam) obj;
            db.userTeamDao().updateUserTeam(team);
        }
        else if (obj instanceof OfflineTeamSettings) {
            OfflineTeamSettings settings = (OfflineTeamSettings) obj;
            db.offlineTeamSettingsDao().updateTeamSettings(settings);
        }
        else if (obj instanceof TeamGame) {
            TeamGame game = (TeamGame) obj;
            db.teamGameDao().updateTeamGame(game);
        }
        else if (obj instanceof TeamAITeam) {
            TeamAITeam team = (TeamAITeam) obj;
            db.teamDao().updateTeam(team);
        }
        else if (obj instanceof TeamFixture) {
            TeamFixture fix = (TeamFixture) obj;
            db.fixtureDao().updateFixture(fix);
        }
    }


    public void deleteObject(Object obj) {
        if (obj instanceof UserTeam) {
            UserTeam team = (UserTeam) obj;
            db.userTeamDao().deleteUserTeam(team);
        }
        else if (obj instanceof OfflineTeamSettings) {
            OfflineTeamSettings settings = (OfflineTeamSettings) obj;
            db.offlineTeamSettingsDao().deleteTeamSettings(settings);
        }
        else if (obj instanceof TeamGame) {
            TeamGame game = (TeamGame) obj;
            db.teamGameDao().deleteTeamGame(game);
        }
        else if (obj instanceof TeamAITeam) {
            TeamAITeam team = (TeamAITeam) obj;
            db.teamDao().deleteTeam(team);
        }
        else if (obj instanceof TeamFixture) {
            TeamFixture fix = (TeamFixture) obj;
            db.fixtureDao().deleteFixture(fix);
        }
    }

    public UserTeam getOfflineTeam() {
        UserTeam[] teams = db.userTeamDao().selectUserTeam();
        if (teams.length > 0) {
            return teams[0];
        }
        return null;
    }
    public OfflineTeamSettings getTeamSettings() {
        OfflineTeamSettings settings[] = db.offlineTeamSettingsDao().selectTeamSettings();
        if (settings.length >0) {
            return settings[0];
        }
        return null;
    }
    public TeamGame getTeamGame() {
        TeamGame game[] = db.teamGameDao().selectTeamGame();
        if (game.length >0) {
            return game[0];
        }
        return null;
    }


    public List<TeamAITeam> getAllTeams() {
        return Arrays.asList(db.teamDao().selectAllTeams());
    }
    public List<TeamAITeam> getAllTeamsInLeague(int league) { return Arrays.asList(db.teamDao().selectAllTeamsInLeague(league));}
    public TeamAITeam getTeamFromID(int ID) {
        return db.teamDao().selectTeamFromID(ID);
    }
    public TeamAITeam getTeamFromName(String name) {
        return db.teamDao().selectTeamFromName(name);
    }
    public int getIDFromName(String name) {
        return db.teamDao().selectTeamFromName(name).getID();
    }
    public int getNumRowsFromTeamTable() {
        return db.teamDao().getRowCount();
    }


    public List<TeamFixture> getAllFixtures() { return Arrays.asList(db.fixtureDao().selectAllFixtures());}
    public TeamFixture getFixtureFromID(int ID) {return db.fixtureDao().selectFixtureFromID(ID); }
    public List<TeamFixture> getWeeksFixtureFromLeague(Date date, int league) { return Arrays.asList(db.fixtureDao().selectAllFixturesInLeagueFromDate(date, league)); }
    public List<TeamFixture> getFixturesForTeam(int teamID) { return Arrays.asList(db.fixtureDao().selectAllFixturesFromTeam(teamID)); }
    public TeamFixture getTeamsFixtureForWeek(int week, int teamID) { return db.fixtureDao().selectFixtureForWeekWithTeam(week, teamID); }
    public int getNumRowsFromFixtureTable() { return db.fixtureDao().getRowCount(); }

    public void resetDB() {
        db.clearAllTables();
    }
}
