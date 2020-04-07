package com.cutlerdevelopment.footballsteps.Models.TeamMode;

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
import com.cutlerdevelopment.footballsteps.Utils.Converters;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class TMSavedData {


    public static void createOfflineTeamSavedData(Context c) {
        new TMSavedData(c);
    }
    private static TMSavedData instance = null;

    public static TMSavedData getInstance() {
        if (instance != null) {
            return instance;
        }
        return null;
    }

    private TMSavedData(Context context) {
        instance = this;
        //TODO: stop allowing Main Thread Queries and figure out a better way to load a game
        db = Room.databaseBuilder(context, AppDatabase.class, Words.OFFLINE_TEAM_CAREER_ROOM_DATABASE_NAME).allowMainThreadQueries().build();
    }

    /**
     * App Database class is the Room Database that contains the instances of the Dao interfaces.
     */
    @Database(entities = {TMUserTeam.class, TMTeam.class, TMSettings.class,
            TMGame.class, TMFixture.class}, version = 1)
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
        void insertUserTeam(TMUserTeam team);

        @Update
        void updateUserTeam(TMUserTeam team);

        @Delete
        void deleteUserTeam(TMUserTeam team);

        @Query("SELECT * FROM TMUserTeam")
        TMUserTeam[] selectUserTeam();


    }

    @Dao
    public interface OfflineTeamSettingsDao {

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        void insertTeamSettings(TMSettings settings);
        @Update
        void updateTeamSettings(TMSettings settings);
        @Delete
        void deleteTeamSettings(TMSettings settings);

        @Query("SELECT * FROM TMSettings")
        TMSettings[] selectTeamSettings();

    }

    @Dao
    public interface TeamGameDao {
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        void insertTeamGame(TMGame game);
        @Update
        void updateTeamGame(TMGame game);
        @Delete
        void deleteTeamGame(TMGame game);

        @Query("SELECT * FROM TMGame")
        TMGame[] selectTeamGame();
    }

    @Dao
    public interface TeamDao {
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        void insertTeams(TMTeam teams);
        @Update
        void updateTeam(TMTeam teams);
        @Delete
        void deleteTeam(TMTeam teams);

        @Query("SELECT * FROM TMTeam")
        TMTeam[] selectAllTeams();
        @Query("SELECT * FROM TMTeam WHERE league = :league")
        TMTeam[] selectAllTeamsInLeague(int league);
        @Query("SELECT * FROM TMTeam WHERE id = :teamID")
        TMTeam selectTeamFromID(int teamID);
        @Query("SELECT * FROM TMTeam WHERE name = :teamName")
        TMTeam selectTeamFromName(String teamName);
        @Query("SELECT COUNT(id) FROM TMTeam")
        int getRowCount();
    }
    @Dao
    public interface  FixtureDao {
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        void insertFixtures(TMFixture fixture);
        @Update
        void updateFixture(TMFixture fixture);
        @Delete
        void deleteFixture(TMFixture fixture);

        @Query("SELECT * FROM TMFixture")
        TMFixture[] selectAllFixtures();
        @Query("SELECT * FROM TMFixture WHERE id = :fixtureID")
        TMFixture selectFixtureFromID(int fixtureID);
        @Query("SELECT * FROM TMFixture WHERE date = :date AND league = :league")
        TMFixture[] selectAllFixturesInLeagueFromDate(Date date, int league);
        @Query("SELECT * FROM TMFixture WHERE homeTeamID = :teamID OR awayTeamID = :teamID")
        TMFixture[] selectAllFixturesFromTeam(int teamID);
        @Query("SELECT * FROM TMFixture WHERE homeScore = -1 AND (homeTeamID = :teamID OR awayTeamID = :teamID)")
        TMFixture[] selectAllUpcomingFixturesFromTeam(int teamID);
        @Query("SELECT * FROM TMFixture WHERE homeScore > -1 AND (homeTeamID = :teamID OR awayTeamID = :teamID)")
        TMFixture[] selectAllResultsFromTeam(int teamID);
        @Query("SELECT * FROM TMFixture WHERE week = :week AND (homeTeamID = :teamID OR awayTeamID = :teamID)")
        TMFixture selectFixtureForWeekWithTeam(int week, int teamID);
        @Query("SELECT COUNT(id) FROM TMFixture")
        int getRowCount();
    }



    public void saveObject(Object obj) {
        if (obj instanceof TMUserTeam) {
            TMUserTeam team = (TMUserTeam) obj;
            db.userTeamDao().insertUserTeam(team);
        }
        else if (obj instanceof TMSettings) {
            TMSettings settings = (TMSettings) obj;
            db.offlineTeamSettingsDao().insertTeamSettings(settings);
        }
        else if (obj instanceof TMGame) {
            TMGame game = (TMGame) obj;
            db.teamGameDao().insertTeamGame(game);
        }
        else if (obj instanceof TMTeam) {
            TMTeam team = (TMTeam) obj;
            db.teamDao().insertTeams(team);
        }
        else if (obj instanceof TMFixture) {
            TMFixture fix = (TMFixture) obj;
            db.fixtureDao().insertFixtures(fix);
        }
    }

    public void updateObject(Object obj) {
        if (obj instanceof TMUserTeam) {
            TMUserTeam team = (TMUserTeam) obj;
            db.userTeamDao().updateUserTeam(team);
        }
        else if (obj instanceof TMSettings) {
            TMSettings settings = (TMSettings) obj;
            db.offlineTeamSettingsDao().updateTeamSettings(settings);
        }
        else if (obj instanceof TMGame) {
            TMGame game = (TMGame) obj;
            db.teamGameDao().updateTeamGame(game);
        }
        else if (obj instanceof TMTeam) {
            TMTeam team = (TMTeam) obj;
            db.teamDao().updateTeam(team);
        }
        else if (obj instanceof TMFixture) {
            TMFixture fix = (TMFixture) obj;
            db.fixtureDao().updateFixture(fix);
        }
    }


    public void deleteObject(Object obj) {
        if (obj instanceof TMUserTeam) {
            TMUserTeam team = (TMUserTeam) obj;
            db.userTeamDao().deleteUserTeam(team);
        }
        else if (obj instanceof TMSettings) {
            TMSettings settings = (TMSettings) obj;
            db.offlineTeamSettingsDao().deleteTeamSettings(settings);
        }
        else if (obj instanceof TMGame) {
            TMGame game = (TMGame) obj;
            db.teamGameDao().deleteTeamGame(game);
        }
        else if (obj instanceof TMTeam) {
            TMTeam team = (TMTeam) obj;
            db.teamDao().deleteTeam(team);
        }
        else if (obj instanceof TMFixture) {
            TMFixture fix = (TMFixture) obj;
            db.fixtureDao().deleteFixture(fix);
        }
    }

    public TMUserTeam getOfflineTeam() {
        TMUserTeam[] teams = db.userTeamDao().selectUserTeam();
        if (teams.length > 0) {
            return teams[0];
        }
        return null;
    }
    public TMSettings getTeamSettings() {
        TMSettings settings[] = db.offlineTeamSettingsDao().selectTeamSettings();
        if (settings.length >0) {
            return settings[0];
        }
        return null;
    }
    public TMGame getTeamGame() {
        TMGame game[] = db.teamGameDao().selectTeamGame();
        if (game.length >0) {
            return game[0];
        }
        return null;
    }


    public List<TMTeam> getAllTeams() {
        return Arrays.asList(db.teamDao().selectAllTeams());
    }
    public List<TMTeam> getAllTeamsInLeague(int league) { return Arrays.asList(db.teamDao().selectAllTeamsInLeague(league));}
    public TMTeam getTeamFromID(int ID) {
        return db.teamDao().selectTeamFromID(ID);
    }
    public TMTeam getTeamFromName(String name) {
        return db.teamDao().selectTeamFromName(name);
    }
    public int getIDFromName(String name) {
        return db.teamDao().selectTeamFromName(name).getID();
    }
    public int getNumRowsFromTeamTable() {
        return db.teamDao().getRowCount();
    }


    public List<TMFixture> getAllFixtures() { return Arrays.asList(db.fixtureDao().selectAllFixtures());}
    public TMFixture getFixtureFromID(int ID) {return db.fixtureDao().selectFixtureFromID(ID); }
    public List<TMFixture> getWeeksFixtureFromLeague(Date date, int league) { return Arrays.asList(db.fixtureDao().selectAllFixturesInLeagueFromDate(date, league)); }
    public List<TMFixture> getFixturesForTeam(int teamID) { return Arrays.asList(db.fixtureDao().selectAllFixturesFromTeam(teamID)); }
    public TMFixture getTeamsFixtureForWeek(int week, int teamID) { return db.fixtureDao().selectFixtureForWeekWithTeam(week, teamID); }
    public int getNumRowsFromFixtureTable() { return db.fixtureDao().getRowCount(); }
    public List<TMFixture> getAllUpcomingFixturesForTeam(int teamID) { return Arrays.asList(db.fixtureDao().selectAllUpcomingFixturesFromTeam(teamID)); }
    public List<TMFixture> getAllResultsForTeam(int teamID) { return Arrays.asList(db.fixtureDao().selectAllResultsFromTeam(teamID)); }

    public void resetDB() {
        db.clearAllTables();
    }
}
