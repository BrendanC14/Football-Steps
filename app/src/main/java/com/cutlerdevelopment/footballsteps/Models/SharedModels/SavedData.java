package com.cutlerdevelopment.footballsteps.Models.SharedModels;

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

import com.cutlerdevelopment.footballsteps.Models.ProCareer.ProAIPlayer;
import com.cutlerdevelopment.footballsteps.Models.ProCareer.ProGame;
import com.cutlerdevelopment.footballsteps.Models.ProCareer.ProSettings;
import com.cutlerdevelopment.footballsteps.Models.ProCareer.ProUsersPlayer;
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
    @Database(entities = {ProUsersPlayer.class, Team.class, ProSettings.class,
            ProGame.class, Fixture.class, PlayerActivity.class, ProAIPlayer.class}, version = 1)
    @TypeConverters({Converters.class})
    public static abstract class AppDatabase extends RoomDatabase {
        public abstract OfflinePlayerDao offlinePlayerDao();
        public abstract OfflineSettingsDao offlineSettingsDao();
        public abstract OfflineGameDao offlineGameDao();
        public abstract PlayerActivityDao playerActivityDao();
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
        void insertOfflinePlayer(ProUsersPlayer player);

        @Update
        void updateOfflinePlayer(ProUsersPlayer player);

        @Delete
        void deleteOfflinePlayer(ProUsersPlayer player);

        @Query("SELECT * FROM ProUsersPlayer")
        ProUsersPlayer[] selectOfflinePlayer();

    }


    @Dao
    public interface OfflineSettingsDao {

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        void insertOfflineSettings(ProSettings settings);
        @Update
        void updateOfflineSettings(ProSettings settings);
        @Delete
        void deleteOfflineSettings(ProSettings settings);

        @Query("SELECT * FROM ProSettings")
        ProSettings[] selectOfflineSettings();

    }

    @Dao
    public interface  OfflineGameDao {

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        void insertOfflineGame(ProGame game);
        @Update
        void updateOfflineGame(ProGame game);
        @Delete
        void deleteOfflineGame(ProGame game);

        @Query("SELECT * FROM ProGame")
        ProGame[] selectOfflineGame();
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
    public interface  OfflineAIPlayerDao {

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        void insertOfflineAIPlayer(ProAIPlayer proAIPlayer);
        @Update
        void updateOfflineAIPlayer(ProAIPlayer proAIPlayer);
        @Delete
        void deleteOfflineAIPlayer(ProAIPlayer proAIPlayer);

        @Query("SELECT * FROM ProAIPlayer")
        ProAIPlayer[] selectAllOfflineAIPlayers();
        @Query("SELECT * FROM ProAIPlayer WHERE currTeamID = :teamID")
        ProAIPlayer[] selectOfflinePlayerForTeamID(int teamID);
        @Query("SELECT * FROM ProAIPlayer WHERE currTeamID = :teamID AND position = :pos")
        ProAIPlayer[] selectOfflinePlayerForTeamIDWithPos(int teamID, int pos);
        @Query("SELECT COUNT(id) FROM ProAIPlayer")
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
        @Query("SELECT * FROM fixture WHERE date = :date AND league = :league")
        Fixture[] selectAllFixturesInLeagueFromDate(Date date, int league);
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
        if (obj instanceof ProUsersPlayer) {
            ProUsersPlayer player = (ProUsersPlayer) obj;
            db.offlinePlayerDao().insertOfflinePlayer(player);
        }
        else if (obj instanceof ProSettings) {
            ProSettings settings = (ProSettings) obj;
            db.offlineSettingsDao().insertOfflineSettings(settings);
        }
        else if (obj instanceof ProGame) {
            ProGame game = (ProGame) obj;
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
        else if (obj instanceof ProAIPlayer) {
            ProAIPlayer player = (ProAIPlayer) obj;
            db.offlineAIPlayerDao().insertOfflineAIPlayer(player);
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
        if (obj instanceof ProUsersPlayer) {
            ProUsersPlayer player = (ProUsersPlayer) obj;
            db.offlinePlayerDao().updateOfflinePlayer(player);
        }
        else if (obj instanceof ProSettings) {
            ProSettings settings = (ProSettings) obj;
            db.offlineSettingsDao().updateOfflineSettings(settings);
        }
        else if (obj instanceof ProGame) {
            ProGame game = (ProGame) obj;
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
        else if (obj instanceof ProAIPlayer) {
            ProAIPlayer player = (ProAIPlayer) obj;
            db.offlineAIPlayerDao().updateOfflineAIPlayer(player);
        }
        else if (obj instanceof Fixture) {
            Fixture fix = (Fixture) obj;
            db.fixtureDao().updateFixture(fix);
        }
    }

    public ProUsersPlayer getOfflinePlayer() {
        ProUsersPlayer[] player = db.offlinePlayerDao().selectOfflinePlayer();
        if (player.length > 0) {
            return player[0];
        }
        return null;
    }
    public ProSettings getOfflineSettings() {
        ProSettings settings[] = db.offlineSettingsDao().selectOfflineSettings();
        if (settings.length >0) {
            return settings[0];
        }
        return null;
    }
    public ProGame getOfflineGame() {
        ProGame game[] = db.offlineGameDao().selectOfflineGame();
        if (game.length >0) {
            return game[0];
        }
        return null;
    }

    public void deleteObject(Object obj) {
        if (obj instanceof ProUsersPlayer) {
            ProUsersPlayer player = (ProUsersPlayer) obj;
            db.offlinePlayerDao().deleteOfflinePlayer(player);
        }
        else if (obj instanceof ProSettings) {
            ProSettings settings = (ProSettings) obj;
            db.offlineSettingsDao().deleteOfflineSettings(settings);
        }
        else if (obj instanceof ProGame) {
            ProGame game = (ProGame) obj;
            db.offlineGameDao().deleteOfflineGame(game);
        }
        else if (obj instanceof  PlayerActivity) {
            PlayerActivity activity = (PlayerActivity) obj;
            db.playerActivityDao().deletePlayerActivity(activity);
        }
        else if (obj instanceof Team) {
            Team team = (Team) obj;
            db.teamDao().deleteTeam(team);
        }
        else if (obj instanceof ProAIPlayer) {
            ProAIPlayer player = (ProAIPlayer) obj;
            db.offlineAIPlayerDao().deleteOfflineAIPlayer(player);
        }
        else if (obj instanceof Fixture) {
            Fixture fix = (Fixture) obj;
            db.fixtureDao().deleteFixture(fix);
        }
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

    public List<ProAIPlayer> getAllOfflineAIPlayers() {
        return Arrays.asList(db.offlineAIPlayerDao().selectAllOfflineAIPlayers());
    }
    public List<ProAIPlayer> getAllOfflinePlayerFromTeam(int teamID) {
        return Arrays.asList(db.offlineAIPlayerDao().selectOfflinePlayerForTeamID(teamID));
    }
    public List<ProAIPlayer> getAllOfflinePlayerOfPositionFromTeam(int teamID, int pos) {
        return Arrays.asList(db.offlineAIPlayerDao().selectOfflinePlayerForTeamIDWithPos(teamID, pos));
    }
    public int getNumRowsFromOfflineAIPlayerTable() {
        return db.offlineAIPlayerDao().getRowCount();
    }

    public List<Fixture> getAllFixtures() { return Arrays.asList(db.fixtureDao().selectAllFixtures());}
    public Fixture getFixtureFromID(int ID) {return db.fixtureDao().selectFixtureFromID(ID); }
    public List<Fixture> getWeeksFixtureFromLeague(Date date, int league) { return Arrays.asList(db.fixtureDao().selectAllFixturesInLeagueFromDate(date, league)); }
    public List<Fixture> getFixturesForTeam(int teamID) { return Arrays.asList(db.fixtureDao().selectAllFixturesFromTeam(teamID)); }
    public Fixture getTeamsFixtureForWeek(int week, int teamID) { return db.fixtureDao().selectFixtureForWeekWithTeam(week, teamID); }
    public int getNumRowsFromFixtureTable() { return db.fixtureDao().getRowCount(); }

    public void resetDB() {
        db.clearAllTables();
    }






}
