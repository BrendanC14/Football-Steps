package com.cutlerdevelopment.footballsteps.Models.ProCareer;

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

/**
 * SavedData instance contains the OfflinePlayerDao interface responsible for talking to the Room Database.
 * offlinePlayeDao is an instance of DAO that belongs to the AppDatabase called db.
 * From the db you can access offlinePlayeDao where different methods have been built to save, update or retrieve data from the DB.
 */
public class PMSavedData {

    public static void createSavedDataInstance(Context c) {
        new PMSavedData(c);
    }
    private static PMSavedData instance = null;

    public static PMSavedData getInstance() {
        if (instance != null) {
            return instance;
        }
        return null;
    }

    private PMSavedData(Context context) {
        instance = this;
        //TODO: stop allowing Main Thread Queries and figure out a better way to load a game
        db = Room.databaseBuilder(context, AppDatabase.class, Words.OFFLINE_PRO_CAREER_ROOM_DATABASE_NAME).allowMainThreadQueries().build();
    }

    /**
     * App Database class is the Room Database that contains the instances of the Dao interfaces.
     */
    @Database(entities = {PMUserPlayer.class, PMAITeam.class, PMSettings.class,
            PMGame.class, PMFixture.class, PMAIPlayer.class}, version = 1)
    @TypeConverters({Converters.class})
    public static abstract class AppDatabase extends RoomDatabase {
        public abstract OfflinePlayerDao offlinePlayerDao();
        public abstract OfflineSettingsDao offlineSettingsDao();
        public abstract OfflineGameDao offlineGameDao();
        public abstract TeamDao teamDao();
        public abstract OfflineAIPlayerDao offlineAIPlayerDao();
        public abstract FixtureDao fixtureDao();
    }

    private static AppDatabase db;

    /**
     * The Dao interface responsible for the ProUsersPlayer table
     */
    @Dao
    public interface OfflinePlayerDao {

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        void insertOfflinePlayer(PMUserPlayer player);

        @Update
        void updateOfflinePlayer(PMUserPlayer player);

        @Delete
        void deleteOfflinePlayer(PMUserPlayer player);

        @Query("SELECT * FROM PMUserPlayer")
        PMUserPlayer[] selectOfflinePlayer();

    }


    @Dao
    public interface OfflineSettingsDao {

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        void insertOfflineSettings(PMSettings settings);
        @Update
        void updateOfflineSettings(PMSettings settings);
        @Delete
        void deleteOfflineSettings(PMSettings settings);

        @Query("SELECT * FROM PMSettings")
        PMSettings[] selectOfflineSettings();

    }

    @Dao
    public interface  OfflineGameDao {

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        void insertOfflineGame(PMGame game);
        @Update
        void updateOfflineGame(PMGame game);
        @Delete
        void deleteOfflineGame(PMGame game);

        @Query("SELECT * FROM PMGame")
        PMGame[] selectOfflineGame();
    }

    /**
     * The Dao interface responsible for the team table
     */
    @Dao
    public interface TeamDao {

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        void insertTeams(PMAITeam teams);
        @Update
        void updateTeam(PMAITeam teams);
        @Delete
        void deleteTeam(PMAITeam teams);

        @Query("SELECT * FROM PMAITeam")
        PMAITeam[] selectAllTeams();
        @Query("SELECT * FROM PMAITeam WHERE league = :league")
        PMAITeam[] selectAllTeamsInLeague(int league);
        @Query("SELECT * FROM PMAITeam WHERE id = :teamID")
        PMAITeam selectTeamFromID(int teamID);
        @Query("SELECT * FROM PMAITeam WHERE name = :teamName")
        PMAITeam selectTeamFromName(String teamName);
        @Query("SELECT COUNT(id) FROM PMAITeam")
        int getRowCount();
    }

    @Dao
    public interface  OfflineAIPlayerDao {

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        void insertOfflineAIPlayer(PMAIPlayer PMAIPlayer);
        @Update
        void updateOfflineAIPlayer(PMAIPlayer PMAIPlayer);
        @Delete
        void deleteOfflineAIPlayer(PMAIPlayer PMAIPlayer);

        @Query("SELECT * FROM PMAIPlayer")
        PMAIPlayer[] selectAllOfflineAIPlayers();
        @Query("SELECT * FROM PMAIPlayer WHERE currTeamID = :teamID")
        PMAIPlayer[] selectOfflinePlayerForTeamID(int teamID);
        @Query("SELECT * FROM PMAIPlayer WHERE currTeamID = :teamID AND position = :pos")
        PMAIPlayer[] selectOfflinePlayerForTeamIDWithPos(int teamID, int pos);
        @Query("SELECT COUNT(id) FROM PMAIPlayer")
        int getRowCount();

    }
    @Dao
    public interface FixtureDao {
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        void insertFixtures(PMFixture fixture);
        @Update
        void updateFixture(PMFixture fixture);
        @Delete
        void deleteFixture(PMFixture fixture);

        @Query("SELECT * FROM PMFixture")
        PMFixture[] selectAllFixtures();
        @Query("SELECT * FROM PMFixture WHERE id = :fixtureID")
        PMFixture selectFixtureFromID(int fixtureID);
        @Query("SELECT * FROM PMFixture WHERE date = :date AND league = :league")
        PMFixture[] selectAllFixturesInLeagueFromDate(Date date, int league);
        @Query("SELECT * FROM PMFixture WHERE homeTeamID = :teamID OR awayTeamID = :teamID")
        PMFixture[] selectAllFixturesFromTeam(int teamID);
        @Query("SELECT * FROM PMFixture WHERE week = :week AND (homeTeamID = :teamID OR awayTeamID = :teamID)")
        PMFixture selectFixtureForWeekWithTeam(int week, int teamID);
        @Query("SELECT COUNT(id) FROM PMFixture")
        int getRowCount();
    }
    /**
     * A 'setter' method for inserting an object into the private db. Takes an object, validates the type and calls the appropriate dao.
     * @param obj the obj to be saved
     */
    public void saveObject(Object obj) {
        if (obj instanceof PMUserPlayer) {
            PMUserPlayer player = (PMUserPlayer) obj;
            db.offlinePlayerDao().insertOfflinePlayer(player);
        }
        else if (obj instanceof PMSettings) {
            PMSettings settings = (PMSettings) obj;
            db.offlineSettingsDao().insertOfflineSettings(settings);
        }
        else if (obj instanceof PMGame) {
            PMGame game = (PMGame) obj;
            db.offlineGameDao().insertOfflineGame(game);
        }
        else if (obj instanceof PMAITeam) {
            PMAITeam team = (PMAITeam) obj;
            db.teamDao().insertTeams(team);
        }
        else if (obj instanceof PMAIPlayer) {
            PMAIPlayer player = (PMAIPlayer) obj;
            db.offlineAIPlayerDao().insertOfflineAIPlayer(player);
        }
        else if (obj instanceof PMFixture) {
            PMFixture fix = (PMFixture) obj;
            db.fixtureDao().insertFixtures(fix);
        }
    }

    /**
     * A 'setter' method for updating an object row into the private db. Takes an object, validates the type and calls the appropriate dao.
     * @param obj the object to be updated
     */
    public void updateObject(Object obj) {
        if (obj instanceof PMUserPlayer) {
            PMUserPlayer player = (PMUserPlayer) obj;
            db.offlinePlayerDao().updateOfflinePlayer(player);
        }
        else if (obj instanceof PMSettings) {
            PMSettings settings = (PMSettings) obj;
            db.offlineSettingsDao().updateOfflineSettings(settings);
        }
        else if (obj instanceof PMGame) {
            PMGame game = (PMGame) obj;
            db.offlineGameDao().updateOfflineGame(game);
        }
        else if (obj instanceof PMAITeam) {
            PMAITeam team = (PMAITeam) obj;
            db.teamDao().updateTeam(team);
        }
        else if (obj instanceof PMAIPlayer) {
            PMAIPlayer player = (PMAIPlayer) obj;
            db.offlineAIPlayerDao().updateOfflineAIPlayer(player);
        }
        else if (obj instanceof PMFixture) {
            PMFixture fix = (PMFixture) obj;
            db.fixtureDao().updateFixture(fix);
        }
    }
    public void deleteObject(Object obj) {
        if (obj instanceof PMUserPlayer) {
            PMUserPlayer player = (PMUserPlayer) obj;
            db.offlinePlayerDao().deleteOfflinePlayer(player);
        }
        else if (obj instanceof PMSettings) {
            PMSettings settings = (PMSettings) obj;
            db.offlineSettingsDao().deleteOfflineSettings(settings);
        }
        else if (obj instanceof PMGame) {
            PMGame game = (PMGame) obj;
            db.offlineGameDao().deleteOfflineGame(game);
        }
        else if (obj instanceof PMAITeam) {
            PMAITeam team = (PMAITeam) obj;
            db.teamDao().deleteTeam(team);
        }
        else if (obj instanceof PMAIPlayer) {
            PMAIPlayer player = (PMAIPlayer) obj;
            db.offlineAIPlayerDao().deleteOfflineAIPlayer(player);
        }
        else if (obj instanceof PMFixture) {
            PMFixture fix = (PMFixture) obj;
            db.fixtureDao().deleteFixture(fix);
        }
    }

    public PMUserPlayer getOfflinePlayer() {
        PMUserPlayer[] player = db.offlinePlayerDao().selectOfflinePlayer();
        if (player.length > 0) {
            return player[0];
        }
        return null;
    }
    public PMSettings getOfflineSettings() {
        PMSettings settings[] = db.offlineSettingsDao().selectOfflineSettings();
        if (settings.length >0) {
            return settings[0];
        }
        return null;
    }
    public PMGame getOfflineGame() {
        PMGame game[] = db.offlineGameDao().selectOfflineGame();
        if (game.length >0) {
            return game[0];
        }
        return null;
    }



    public List<PMAITeam> getAllTeams() {
        return Arrays.asList(db.teamDao().selectAllTeams());
    }
    public List<PMAITeam> getAllTeamsInLeague(int league) { return Arrays.asList(db.teamDao().selectAllTeamsInLeague(league));}
    public PMAITeam getTeamFromID(int ID) {
        return db.teamDao().selectTeamFromID(ID);
    }
    public PMAITeam getTeamFromName(String name) {
        return db.teamDao().selectTeamFromName(name);
    }
    public int getIDFromName(String name) {
        return db.teamDao().selectTeamFromName(name).getID();
    }
    public int getNumRowsFromTeamTable() {
        return db.teamDao().getRowCount();
    }

    public List<PMAIPlayer> getAllOfflineAIPlayers() {
        return Arrays.asList(db.offlineAIPlayerDao().selectAllOfflineAIPlayers());
    }
    public List<PMAIPlayer> getAllOfflinePlayerFromTeam(int teamID) {
        return Arrays.asList(db.offlineAIPlayerDao().selectOfflinePlayerForTeamID(teamID));
    }
    public List<PMAIPlayer> getAllOfflinePlayerOfPositionFromTeam(int teamID, int pos) {
        return Arrays.asList(db.offlineAIPlayerDao().selectOfflinePlayerForTeamIDWithPos(teamID, pos));
    }
    public int getNumRowsFromOfflineAIPlayerTable() {
        return db.offlineAIPlayerDao().getRowCount();
    }

    public List<PMFixture> getAllFixtures() { return Arrays.asList(db.fixtureDao().selectAllFixtures());}
    public PMFixture getFixtureFromID(int ID) {return db.fixtureDao().selectFixtureFromID(ID); }
    public List<PMFixture> getWeeksFixtureFromLeague(Date date, int league) { return Arrays.asList(db.fixtureDao().selectAllFixturesInLeagueFromDate(date, league)); }
    public List<PMFixture> getFixturesForTeam(int teamID) { return Arrays.asList(db.fixtureDao().selectAllFixturesFromTeam(teamID)); }
    public PMFixture getTeamsFixtureForWeek(int week, int teamID) { return db.fixtureDao().selectFixtureForWeekWithTeam(week, teamID); }
    public int getNumRowsFromFixtureTable() { return db.fixtureDao().getRowCount(); }

    public void resetDB() {
        db.clearAllTables();
    }






}
