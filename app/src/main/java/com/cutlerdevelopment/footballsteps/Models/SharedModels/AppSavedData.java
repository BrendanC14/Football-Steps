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

import com.cutlerdevelopment.footballsteps.Constants.Words;
import com.cutlerdevelopment.footballsteps.Utils.Converters;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class AppSavedData {


    public static void createSavedDataInstance(Context c) {
        new AppSavedData(c);
    }
    private static AppSavedData instance = null;

    public static AppSavedData getInstance() {
        if (instance != null) {
            return instance;
        }
        return null;
    }

    private AppSavedData(Context context) {
        instance = this;
        //TODO: stop allowing Main Thread Queries and figure out a better way to load a game
        db = Room.databaseBuilder(context, AppDatabase.class, Words.OFFLINE_PRO_CAREER_ROOM_DATABASE_NAME).allowMainThreadQueries().build();
    }

    /**
     * App Database class is the Room Database that contains the instances of the Dao interfaces.
     */
    @Database(entities = {PlayerActivity.class}, version = 1)
    @TypeConverters({Converters.class})
    public static abstract class AppDatabase extends RoomDatabase {
        public abstract PlayerActivityDao playerActivityDao();
    }

    private static AppDatabase db;

    @Dao
    public interface PlayerActivityDao {

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

    public List<PlayerActivity> getAllPlayerActivities() {
        return Arrays.asList(db.playerActivityDao().selectAllPlayerActivity());
    }
    public PlayerActivity getPlayerActivityOnDate(Date date) {
        return db.playerActivityDao().selectPlayerActivityWithDate(date);
    }
    public PlayerActivity getLastAddedActivity() {
        return db.playerActivityDao().selectLastAddedActivity();
    }
}
