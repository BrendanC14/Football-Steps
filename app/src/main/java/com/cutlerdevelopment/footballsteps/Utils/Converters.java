package com.cutlerdevelopment.footballsteps.Utils;

import androidx.room.TypeConverter;

import com.cutlerdevelopment.footballsteps.Models.OfflineGame;
import com.cutlerdevelopment.footballsteps.Models.Team;

public class Converters {

    @TypeConverter
    public static Team fromTeamID(int ID) {
        return OfflineGame.getInstance().getTeamFromID(ID);
    }

    @TypeConverter
    public static int fromTeam(Team t) {
        return t.getID();
    }
}
