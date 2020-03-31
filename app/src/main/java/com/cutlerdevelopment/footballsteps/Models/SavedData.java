package com.cutlerdevelopment.footballsteps.Models;

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

import com.cutlerdevelopment.footballsteps.Utils.Converters;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * SavedData instance contains the OfflinePlayerDao interface responsible for talking to the Room Database.
 * offlinePlayeDao is an instance of DAO that belongs to the AppDatabase called db.
 * From the db you can access offlinePlayeDao where different methods have been built to save, update or retrieve data from the DB.
 */
public class SavedData {

    public static void createSavedDataInstance(Context c) {
        new SavedData(c);
    }
    private static SavedData instance = null;

    public static SavedData getInstance() {
        if (instance != null) {
            return instance;
        }
        return null;
    }

    private SavedData(Context context) {
        instance = this;
        //TODO: stop allowing Main Thread Queries and figure out a better way to load a game
        db = Room.databaseBuilder(context, AppDatabase.class, "OfflinePlayerDB").allowMainThreadQueries().build();
    }

    /**
     * App Database class is the Room Database that contains the instances of the Dao interfaces.
     */
    @Database(entities = {OfflinePlayer.class, Team.class, OfflineSettings.class,
            OfflineGame.class, Fixture.class, PlayerActivity.class}, version = 1)
    @TypeConverters({Converters.class})
    public static abstract class AppDatabase extends RoomDatabase {
        public abstract OfflinePlayerDao offlinePlayerDao();
        public abstract OfflineSettingsDao offlineSettingsDao();
        public abstract OfflineGameDao offlineGameDao();
        public abstract PlayerActivityDao playerActivityDao();
        public abstract TeamDao teamDao();
        public abstract FixtureDao fixtureDao();
    }

    private static AppDatabase db;

    /**
     * The Dao interface responsible for the OfflinePlayer table
     */
    @Dao
    public interface OfflinePlayerDao {

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        void insertOfflinePlayer(OfflinePlayer player);

        @Update
        void updateOfflinePlayer(OfflinePlayer player);

        @Delete
        void deleteOfflinePlayer(OfflinePlayer player);

        @Query("SELECT * FROM offline_player")
        OfflinePlayer[] selectOfflinePlayer();

    }


    @Dao
    public interface OfflineSettingsDao {

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        void insertOfflineSettings(OfflineSettings settings);
        @Update
        void updateOfflineSettings(OfflineSettings settings);
        @Delete
        void deleteOfflineSettings(OfflineSettings settings);

        @Query("SELECT * FROM offlinesettings")
        OfflineSettings[] selectOfflineSettings();

    }

    @Dao
    public interface  OfflineGameDao {

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        void insertOfflineGame(OfflineGame game);
        @Update
        void updateOfflineGame(OfflineGame game);
        @Delete
        void deleteOfflineGame(OfflineGame game);

        @Query("SELECT * FROM offlinegame")
        OfflineGame[] selectOfflineGame();
    }

    @Dao public interface PlayerActivityDao {

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        void insertPlayerActivity(PlayerActivity activity);
        @Update
        void updatePlayerActivity(PlayerActivity activity);
        @Delete
        void deletePlayerActivity(PlayerActivity activity);

        @Query("SELECT * FROM playeractivity")
        PlayerActivity[] selectAllPlayerActivity();
        @Query("SELECT * FROM playeractivity WHERE date BETWEEN :date AND :date")
        PlayerActivity selectPlayerActivityWithDate(Date date);
        @Query("SELECT * FROM playeractivity ORDER BY date DESC LIMIT 1")
        PlayerActivity selectLastAddedActivity();

    }

    /**
     * The Dao interface responsible for the team table
     */
    @Dao
    public interface TeamDao {

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        void insertTeams(Team teams);
        @Update
        void updateTeam(Team teams);
        @Delete
        void deleteTeam(Team teams);

        @Query("SELECT * FROM team")
        Team[] selectAllTeams();
        @Query("SELECT * FROM team WHERE league = :league")
        Team[] selectAllTeamsInLeague(int league);
        @Query("SELECT * FROM team WHERE id = :teamID")
        Team selectTeamFromID(int teamID);
        @Query("SELECT * FROM team WHERE name = :teamName")
        Team selectTeamFromName(String teamName);
        @Query("SELECT COUNT(id) FROM team")
        int getRowCount();
    }

    @Dao
    public interface FixtureDao {
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        void insertFixtures(Fixture fixture);
        @Update
        void updateFixture(Fixture fixture);
        @Delete
        void deleteFixture(Fixture fixture);

        @Query("SELECT * FROM fixture")
        Fixture[] selectAllFixtures();
        @Query("SELECT * FROM fixture WHERE id = :fixtureID")
        Fixture selectFixtureFromID(int fixtureID);
        @Query("SELECT * FROM fixture WHERE week = :week AND league = :league")
        Fixture[] selectAllFixturesInLeagueFromWeek(int week, int league);
        @Query("SELECT * FROM fixture WHERE homeTeamID = :teamID OR awayTeamID = :teamID")
        Fixture[] selectAllFixturesFromTeam(int teamID);
        @Query("SELECT * FROM fixture WHERE week = :week AND (homeTeamID = :teamID OR awayTeamID = :teamID)")
        Fixture selectFixtureForWeekWithTeam(int week, int teamID);
        @Query("SELECT COUNT(id) FROM fixture")
        int getRowCount();
    }
    /**
     * A 'setter' method for inserting an object into the private db. Takes an object, validates the type and calls the appropriate dao.
     * @param obj the obj to be saved
     */
    public void saveObject(Object obj) {
        if (obj instanceof OfflinePlayer) {
            OfflinePlayer player = (OfflinePlayer) obj;
            db.offlinePlayerDao().insertOfflinePlayer(player);
        }
        else if (obj instanceof OfflineSettings) {
            OfflineSettings settings = (OfflineSettings) obj;
            db.offlineSettingsDao().insertOfflineSettings(settings);
        }
        else if (obj instanceof OfflineGame) {
            OfflineGame game = (OfflineGame) obj;
            db.offlineGameDao().insertOfflineGame(game);
        }
        else if (obj instanceof PlayerActivity) {
            PlayerActivity activity = (PlayerActivity) obj;
            db.playerActivityDao().insertPlayerActivity(activity);
        }
        else if (obj instanceof Team) {
            Team team = (Team) obj;
            db.teamDao().insertTeams(team);
        }
        else if (obj instanceof Fixture) {
            Fixture fix = (Fixture) obj;
            db.fixtureDao().insertFixtures(fix);
        }
    }

    /**
     * A 'setter' method for updating an object row into the private db. Takes an object, validates the type and calls the appropriate dao.
     * @param obj the object to be updated
     */
    public void updateObject(Object obj) {
        if (obj instanceof OfflinePlayer) {
            OfflinePlayer player = (OfflinePlayer) obj;
            db.offlinePlayerDao().updateOfflinePlayer(player);
        }
        else if (obj instanceof OfflineSettings) {
            OfflineSettings settings = (OfflineSettings) obj;
            db.offlineSettingsDao().updateOfflineSettings(settings);
        }
        else if (obj instanceof OfflineGame) {
            OfflineGame game = (OfflineGame) obj;
            db.offlineGameDao().updateOfflineGame(game);
        }
        else if (obj instanceof  PlayerActivity) {
            PlayerActivity activity = (PlayerActivity) obj;
            db.playerActivityDao().updatePlayerActivity(activity);
        }
        else if (obj instanceof Team) {
            Team team = (Team) obj;
            db.teamDao().updateTeam(team);
        }
        else if (obj instanceof Fixture) {
            Fixture fix = (Fixture) obj;
            db.fixtureDao().updateFixture(fix);
        }
    }

    public OfflinePlayer getOfflinePlayer() {
        OfflinePlayer[] player = db.offlinePlayerDao().selectOfflinePlayer();
        if (player.length > 0) {
            return player[0];
        }
        return null;
    }
    public OfflineSettings getOfflineSettings() {
        OfflineSettings settings[] = db.offlineSettingsDao().selectOfflineSettings();
        if (settings.length >0) {
            return settings[0];
        }
        return null;
    }
    public OfflineGame getOfflineGame() {
        OfflineGame game[] = db.offlineGameDao().selectOfflineGame();
        if (game.length >0) {
            return game[0];
        }
        return null;
    }

    public List<PlayerActivity> getAllPlayerActivities() {
        return Arrays.asList(db.playerActivityDao().selectAllPlayerActivity());
    }
    public PlayerActivity getPlayerActivityOnDate(Date date) {
        return db.playerActivityDao().selectPlayerActivityWithDate(date);
    }
    public PlayerActivity getLastAddedActivity() {
        return db.playerActivityDao().selectLastAddedActivity();
    }

    public List<Team> getAllTeams() {
        return Arrays.asList(db.teamDao().selectAllTeams());
    }
    public List<Team> getAllTeamsInLeague(int league) { return Arrays.asList(db.teamDao().selectAllTeamsInLeague(league));}
    public Team getTeamFromID(int ID) {
        return db.teamDao().selectTeamFromID(ID);
    }
    public Team getTeamFromName(String name) {
        return db.teamDao().selectTeamFromName(name);
    }
    public int getIDFromName(String name) {
        return db.teamDao().selectTeamFromName(name).getID();
    }
    public int getNumRowsFromTeamTable() {
        return db.teamDao().getRowCount();
    }

    public List<Fixture> getAllFixtures() { return Arrays.asList(db.fixtureDao().selectAllFixtures());}
    public Fixture getFixtureFromID(int ID) {return db.fixtureDao().selectFixtureFromID(ID); }
    public List<Fixture> getWeeksFixtureFromLeague(int week, int league) { return Arrays.asList(db.fixtureDao().selectAllFixturesInLeagueFromWeek(week, league)); }
    public List<Fixture> getFixturesForTeam(int teamID) { return Arrays.asList(db.fixtureDao().selectAllFixturesFromTeam(teamID)); }
    public Fixture getTeamsFixtureForWeek(int week, int teamID) { return db.fixtureDao().selectFixtureForWeekWithTeam(week, teamID); }
    public int getNumRowsFromFixtureTable() { return db.fixtureDao().getRowCount(); }

    public void resetDB() {
        db.clearAllTables();
    }






}
