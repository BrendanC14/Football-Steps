package com.cutlerdevelopment.footballsteps.Constants;

import com.cutlerdevelopment.footballsteps.Models.OfflinePlayer;
import com.cutlerdevelopment.footballsteps.R;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This is a constant class responsible for holding the default words, and also the key's to communicate with SharedPreferences
 */
public class Words {


    public static final List<String> TeamNames = Arrays.asList("Arsenal", "Liverpool", "Manchester City", "Leicester City", "Chelsea",
            "Manchester United", "Wolves", "Sheffield United", "Tottenham Hotspur", "Burnley", "Crystal Palace", "Everton",
            "Newcastle United", "Southampton", "Brighton", "West Ham", "Watford", "Bournemouth", "Aston Villa", "Norwich");

    public static Map<Map<Integer, Integer>, Map<Integer, Integer>> getFirstHeaderAndStat() {
        OfflinePlayer player = OfflinePlayer.getInstance();
        HashMap<Integer, Integer> map1 = new HashMap<>();
        HashMap<Integer, Integer> map2 = new HashMap<>();

        switch (player.getPosition()) {
            case Position.GOALKEEPER:
                map1.put(R.string.main_menu_saves_header, player.getSaves());
                map2.put(R.string.main_menu_conceded_header, player.getConceded());
                Map<Map<Integer, Integer>, Map<Integer, Integer>> gkMap = new HashMap<>();
                gkMap.put(map1, map2);
                return gkMap;
            case Position.DEFENDER:
                map1.put(R.string.main_menu_tackles_header, player.getTackles());
                map2.put(R.string.main_menu_conceded_header, player.getConceded());
                Map<Map<Integer, Integer>, Map<Integer, Integer>> defMap = new HashMap<>();
                defMap.put(map1, map2);
                return defMap;
            case Position.MIDFIELDER:
                map1.put(R.string.main_menu_passes_header, player.getPasses());
                map2.put(R.string.main_menu_assists_header, player.getAssists());
                Map<Map<Integer, Integer>, Map<Integer, Integer>> midMap = new HashMap<>();
                midMap.put(map1, map2);
                return midMap;
            case Position.ATTACKER:
                map1.put(R.string.main_menu_goals_header, player.getGoals());
                map2.put(R.string.main_menu_offsides_header, player.getOffsides());
                Map<Map<Integer, Integer>, Map<Integer, Integer>> atkMap = new HashMap<>();
                atkMap.put(map1, map2);
                return atkMap;

        }


        return new HashMap<>();
    }

    public static String getNumberWithCommas(int num) {
        DecimalFormat df = new DecimalFormat();
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setGroupingSeparator(',');
        df.setDecimalFormatSymbols(dfs);
        return df.format(num);
    }


}
