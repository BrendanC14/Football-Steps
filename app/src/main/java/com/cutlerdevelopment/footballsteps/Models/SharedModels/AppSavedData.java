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
    @Database(entities = {UserActivity.class}, version = 1)
    @TypeConverters({Converters.class})
    public static abstract class AppDatabase extends RoomDatabase {
        public abstract PlayerActivityDao playerActivityDao();
    }

    private static AppDatabase db;

    @Dao
    public interface PlayerActivityDao {

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        void insertPlayerActivity(UserActivity activity);
        @Update
        void updatePlayerActivity(UserActivity activity);
        @Delete
        void deletePlayerActivity(UserActivity activity);

        @Query("SELECT * FROM UserActivity")
        UserActivity[] selectAllPlayerActivity();
        @Query("SELECT * FROM UserActivity WHERE date BETWEEN :date AND :date")
        UserActivity selectPlayerActivityWithDate(Date date);
        @Query("SELECT * FROM UserActivity ORDER BY date DESC LIMIT 1")
        UserActivity selectLastAddedActivity();

    }

    public List<UserActivity> getAllPlayerActivities() {
        return Arrays.asList(db.playerActivityDao().selectAllPlayerActivity());
    }
    public UserActivity getPlayerActivityOnDate(Date date) {
        return db.playerActivityDao().selectPlayerActivityWithDate(date);
    }
    public UserActivity getLastAddedActivity() {
        return db.playerActivityDao().selectLastAddedActivity();
    }
}
