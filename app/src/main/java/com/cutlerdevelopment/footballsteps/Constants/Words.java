package com.cutlerdevelopment.footballsteps.Constants;

import com.cutlerdevelopment.footballsteps.Models.ProCareer.UserPlayer;
import com.cutlerdevelopment.footballsteps.R;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This is a constant class responsible for holding the default words, and also the key's to communicate with SharedPreferences
 */
public class Words {

    public static final String OFFLINE_PRO_CAREER_ROOM_DATABASE_NAME = "OfflineProCareer";
    public static final String OFFLINE_TEAM_CAREER_ROOM_DATABASE_NAME = "OfflineTeamCareer";

    public static final List<String> teamNames = Arrays.asList("Arsenal", "Liverpool", "Manchester City", "Leicester City", "Chelsea",
            "Manchester United", "Wolves", "Sheffield United", "Tottenham Hotspur", "Burnley", "Crystal Palace", "Everton",
            "Newcastle United", "Southampton", "Brighton", "West Ham", "Watford", "Bournemouth", "Aston Villa", "Norwich");


    public static final List<String> firstNames = Arrays.asList("James", "John", "James", "Robert", "Michael", "William", "David",
            "Richard", "Joseph", "Thomas", "Charles", "Christopher", "Daniel", "Matthew", "Anthony", "Donald", "Mark", "Paul", "Steven", "Andrew",
            "Kenneth", "Joshua", "George", "Kevin", "Brian", "Edward", "Ronald", "Timothy", "Jason", "Jeffrey", "Ryan", "Jacob", "Gary",
            "Nicholas", "Eric", "Stephen", "Jonathan", "Larry", "Justin", "Scott", "Brandon", "Frank", "Benjamin", "Gregory", "Samuel",
            "Raymond", "Patrick", "Alexander", "Jack", "Dennis", "Jerry", "Tyler", "Aaron", "Jose", "Henry", "Douglas", "Adam",
            "Peter", "Nathan", "Zachary", "Walter", "Kyle", "Harold", "Carl", "Jeremy", "Keith", "Gerald", "Ethan", "Arthur",
            "Terry", "Christian", "Sean", "Lawrence", "Austin", "Joe", "Noah", "Jesse", "Albert", "Bryan", "Billy", "Bruce",
            "Jordan", "Dylan", "Alan", "Ralph", "Gabriel", "Roy", "Wayne", "Logan", "Randy", "Louis", "Russell", "Vincent",
            "Philip", "Bobby", "Johnny", "Bradley");
    public static final List<String> surnames = Arrays.asList("Smith", "Jones", "Williams", "Taylor", "Brown", "Davies", "Evans", "Wilson",
            "Thomas", "Johnson", "Roberts", "Robinson", "Thompson", "Wright", "Walker", "White", "Edwards", "Hughes", "Green", "Hall", "Lewis",
            "Harris", "Clarke", "Patel", "Griffiths", "Jackson", "Wood", "Turner", "Martin", "Cooper", "Hill", "Ward", "Morris", "Moore",
            "Clark", "Allen", "Davis", "Mitchell", "Carter", "Shaw", "Jones", "Ellis", "Rogers", "Gray", "Holmes", "Mills", "Jenkins",
            "Lee", "James", "Parker", "Kelly", "Richardson", "Murphy", "Simpson", "Adams", "Webb", "Mason", "Palmer", "Barnes", "Stevens",
            "King", "Scott", "Price", "Cook", "Bailey", "Miller", "Anderson", "Singh", "Powell", "Ali", "Owen", "Knight", "Fisher",
            "Baker", "Phillips", "Bennett", "Collins", "Cox", "Marshall", "Begum", "Chapman", "Hunt", "Matthews", "Lloyd", "Barker", "Murray",
            "Harrison", "Watson", "Young", "Bell", "Richards", "Khan", "Wilkinson", "Foster", "Hussain", "Campbell", "Butler", "Russell", "Dixon",
            "Morgan", "Harvey");

    public static String getRandomFirstName() {
        Collections.shuffle(firstNames);
        return firstNames.get(0);
    }
    public static String getRandomSurname() {

        Collections.shuffle(surnames);
        return surnames.get(0);
    }
    public static Map<Map<Integer, Integer>, Map<Integer, Integer>> getFirstHeaderAndStat() {
        UserPlayer player = UserPlayer.getInstance();
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
