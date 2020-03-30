package com.cutlerdevelopment.footballsteps.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.cutlerdevelopment.footballsteps.Constants.Position;
import com.cutlerdevelopment.footballsteps.Models.Fixture;
import com.cutlerdevelopment.footballsteps.Models.OfflineGame;
import com.cutlerdevelopment.footballsteps.Models.OfflinePlayer;
import com.cutlerdevelopment.footballsteps.Models.SavedData;
import com.cutlerdevelopment.footballsteps.R;

import java.util.ArrayList;
import java.util.List;

public class OfflineCareerMainMenu extends AppCompatActivity {

    TextView firstNameField;
    TextView surnameField;
    TextView positionField;
    TextView favNameField;
    ListView fixturesField;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_career_main_menu);

        firstNameField = findViewById(R.id.mainMenuFirstName);
        surnameField = findViewById(R.id.mainMenuSurname);
        positionField = findViewById(R.id.mainMenuPosition);
        favNameField = findViewById(R.id.mainMenuFavTeam);
        fixturesField = findViewById(R.id.fixturesList);

        OfflinePlayer player = OfflinePlayer.getInstance();

        firstNameField.setText(player.getFirstName());
        surnameField.setText(player.getSurname());
        positionField.setText(Position.getPositionLongName(player.getPosition()));
        favNameField.setText(player.getFavTeam().getName());

        List<Fixture> fixtures = SavedData.getInstance().getFixturesForTeam(1);
        List<String> items = new ArrayList<>();
        for (Fixture f : fixtures) {
            items.add(String.valueOf(f.getWeek()) + " " +
                    SavedData.getInstance().getTeamFromID(f.getHomeTeamID()).getName() + " vs " +
                    SavedData.getInstance().getTeamFromID(f.getAwayTeamID()).getName());
        }
        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        fixturesField.setAdapter(itemsAdapter);

    }
}
