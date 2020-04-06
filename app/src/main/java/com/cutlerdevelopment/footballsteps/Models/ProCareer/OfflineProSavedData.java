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
public class OfflineProSavedData {

    public static void createSavedDataInstance(Context c) {
        new OfflineProSavedData(c);
    }
    private static OfflineProSavedData instance = null;

    public static OfflineProSavedData getInstance() {
        if (instance != null) {
            return instance;
        }
        return null;
    }

    private OfflineProSavedData(Context context) {
        instance = this;
        //TODO: stop allowing Main Thread Queries and figure out a better way to load a game
        db = Room.databaseBuilder(context, AppDatabase.class, Words.OFFLINE_PRO_CAREER_ROOM_DATABASE_NAME).allowMainThreadQueries().build();
    }

    /**
     * App Database class is the Room Database that contains the instances of the Dao interfaces.
     */
    @Database(entities = {UserPlayer.class, ProAITeam.class, ProSettings.class,
            ProGame.class, ProFixture.class, ProAIPlayer.class}, version = 1)
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
        void insertOfflinePlayer(UserPlayer player);

        @Update
        void updateOfflinePlayer(UserPlayer player);

        @Delete
        void deleteOfflinePlayer(UserPlayer player);

        @Query("SELECT * FROM UserPlayer")
        UserPlayer[] selectOfflinePlayer();

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

    /**
     * The Dao interface responsible for the team table
     */
    @Dao
    public interface TeamDao {

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        void insertTeams(ProAITeam teams);
        @Update
        void updateTeam(ProAITeam teams);
        @Delete
        void deleteTeam(ProAITeam teams);

        @Query("SELECT * FROM ProAITeam")
        ProAITeam[] selectAllTeams();
        @Query("SELECT * FROM ProAITeam WHERE league = :league")
        ProAITeam[] selectAllTeamsInLeague(int league);
        @Query("SELECT * FROM ProAITeam WHERE id = :teamID")
        ProAITeam selectTeamFromID(int teamID);
        @Query("SELECT * FROM ProAITeam WHERE name = :teamName")
        ProAITeam selectTeamFromName(String teamName);
        @Query("SELECT COUNT(id) FROM ProAITeam")
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
        void insertFixtures(ProFixture fixture);
        @Update
        void updateFixture(ProFixture fixture);
        @Delete
        void deleteFixture(ProFixture fixture);

        @Query("SELECT * FROM ProFixture")
        ProFixture[] selectAllFixtures();
        @Query("SELECT * FROM ProFixture WHERE id = :fixtureID")
        ProFixture selectFixtureFromID(int fixtureID);
        @Query("SELECT * FROM ProFixture WHERE date = :date AND league = :league")
        ProFixture[] selectAllFixturesInLeagueFromDate(Date date, int league);
        @Query("SELECT * FROM ProFixture WHERE homeTeamID = :teamID OR awayTeamID = :teamID")
        ProFixture[] selectAllFixturesFromTeam(int teamID);
        @Query("SELECT * FROM ProFixture WHERE week = :week AND (homeTeamID = :teamID OR awayTeamID = :teamID)")
        ProFixture selectFixtureForWeekWithTeam(int week, int teamID);
        @Query("SELECT COUNT(id) FROM ProFixture")
        int getRowCount();
    }
    /**
     * A 'setter' method for inserting an object into the private db. Takes an object, validates the type and calls the appropriate dao.
     * @param obj the obj to be saved
     */
    public void saveObject(Object obj) {
        if (obj instanceof UserPlayer) {
            UserPlayer player = (UserPlayer) obj;
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
        else if (obj instanceof ProAITeam) {
            ProAITeam team = (ProAITeam) obj;
            db.teamDao().insertTeams(team);
        }
        else if (obj instanceof ProAIPlayer) {
            ProAIPlayer player = (ProAIPlayer) obj;
            db.offlineAIPlayerDao().insertOfflineAIPlayer(player);
        }
        else if (obj instanceof ProFixture) {
            ProFixture fix = (ProFixture) obj;
            db.fixtureDao().insertFixtures(fix);
        }
    }

    /**
     * A 'setter' method for updating an object row into the private db. Takes an object, validates the type and calls the appropriate dao.
     * @param obj the object to be updated
     */
    public void updateObject(Object obj) {
        if (obj instanceof UserPlayer) {
            UserPlayer player = (UserPlayer) obj;
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
        else if (obj instanceof ProAITeam) {
            ProAITeam team = (ProAITeam) obj;
            db.teamDao().updateTeam(team);
        }
        else if (obj instanceof ProAIPlayer) {
            ProAIPlayer player = (ProAIPlayer) obj;
            db.offlineAIPlayerDao().updateOfflineAIPlayer(player);
        }
        else if (obj instanceof ProFixture) {
            ProFixture fix = (ProFixture) obj;
            db.fixtureDao().updateFixture(fix);
        }
    }
    public void deleteObject(Object obj) {
        if (obj instanceof UserPlayer) {
            UserPlayer player = (UserPlayer) obj;
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
        else if (obj instanceof ProAITeam) {
            ProAITeam team = (ProAITeam) obj;
            db.teamDao().deleteTeam(team);
        }
        else if (obj instanceof ProAIPlayer) {
            ProAIPlayer player = (ProAIPlayer) obj;
            db.offlineAIPlayerDao().deleteOfflineAIPlayer(player);
        }
        else if (obj instanceof ProFixture) {
            ProFixture fix = (ProFixture) obj;
            db.fixtureDao().deleteFixture(fix);
        }
    }

    public UserPlayer getOfflinePlayer() {
        UserPlayer[] player = db.offlinePlayerDao().selectOfflinePlayer();
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



    public List<ProAITeam> getAllTeams() {
        return Arrays.asList(db.teamDao().selectAllTeams());
    }
    public List<ProAITeam> getAllTeamsInLeague(int league) { return Arrays.asList(db.teamDao().selectAllTeamsInLeague(league));}
    public ProAITeam getTeamFromID(int ID) {
        return db.teamDao().selectTeamFromID(ID);
    }
    public ProAITeam getTeamFromName(String name) {
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

    public List<ProFixture> getAllFixtures() { return Arrays.asList(db.fixtureDao().selectAllFixtures());}
    public ProFixture getFixtureFromID(int ID) {return db.fixtureDao().selectFixtureFromID(ID); }
    public List<ProFixture> getWeeksFixtureFromLeague(Date date, int league) { return Arrays.asList(db.fixtureDao().selectAllFixturesInLeagueFromDate(date, league)); }
    public List<ProFixture> getFixturesForTeam(int teamID) { return Arrays.asList(db.fixtureDao().selectAllFixturesFromTeam(teamID)); }
    public ProFixture getTeamsFixtureForWeek(int week, int teamID) { return db.fixtureDao().selectFixtureForWeekWithTeam(week, teamID); }
    public int getNumRowsFromFixtureTable() { return db.fixtureDao().getRowCount(); }

    public void resetDB() {
        db.clearAllTables();
    }






}
