package com.cutlerdevelopment.footballsteps.Models;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Index;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.Update;
import androidx.viewpager2.widget.ViewPager2;

import com.cutlerdevelopment.footballsteps.Utils.Converters;

/**
 * SavedData class is responsible for taking to the SharedPreferences and getting responses.
 * Contains methods responsible for retrieving and saving Strings, ints and booleans.
 */
public class SavedData {

    @Database(entities = {OfflinePlayer.class, Team.class}, version = 1)
    @TypeConverters({Converters.class})
    public static abstract class AppDatabase extends RoomDatabase {
        public abstract MyDao myDao();
    }

    private static AppDatabase db;
    public AppDatabase getDb() { return db; }

    @Dao
    public interface MyDao {

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        void insertOfflinePlayer(OfflinePlayer player);

        @Query("SELECT * FROM offline_player")
        OfflinePlayer[] selectOfflinePlayer();

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        void insertTeams(Team teams);
        @Update
        void updateTeam(Team teams);


    }


    public void saveOfflinePlayer(OfflinePlayer player) {
        db.myDao().insertOfflinePlayer(player);
    }
    public OfflinePlayer loadOfflinePlayer() {
        OfflinePlayer[] player = db.myDao().selectOfflinePlayer();
        if (player.length > 0) {
            return player[0];
        }
        return null;
    }






    private static SavedData instance = null;

    private static SharedPreferences sharedPreferences;
    static final String PREFS_NAME = "OfflineData";

    public static SavedData getInstance() {
        if (instance != null) {
            return instance;
        }
        return null;
    }

    private SavedData(Context context) {
        instance = this;
        db = Room.databaseBuilder(context, AppDatabase.class, "OfflinePlayerDB").allowMainThreadQueries().build();
    }


    public String getSavedString(String key, String defaultValue) {
        if (sharedPreferences == null) {
            //TODO throw an error
            return defaultValue;
        }
        return sharedPreferences.getString(key, defaultValue);
    }
    public void saveString(String key, String value) {
        if (sharedPreferences == null) {
            //TODO throw an error
            return;
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public int getSavedInt(String key, int defaultValue) {
        if (sharedPreferences == null) {
            //TODO throw an error
            return defaultValue;
        }
        return sharedPreferences.getInt(key, defaultValue);
    }
    public void saveInt(String key, int value) {
        if (sharedPreferences == null) {
            //TODO throw an error
            return;
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public boolean getSavedBoolean(String key, boolean defaultValue) {
        if (sharedPreferences == null) {
            //TODO throw an error
            return defaultValue;
        }
        return sharedPreferences.getBoolean(key, defaultValue);
    }
    public void saveBoolean(String key, boolean value) {
        if (sharedPreferences == null) {
            //TODO throw an error
            return;
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static void createSavedDataInstance(Context c) {
        new SavedData(c);
    }

}
