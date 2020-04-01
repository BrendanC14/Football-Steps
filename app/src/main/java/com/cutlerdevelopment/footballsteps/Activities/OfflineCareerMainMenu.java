package com.cutlerdevelopment.footballsteps.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cutlerdevelopment.footballsteps.Constants.Colour;
import com.cutlerdevelopment.footballsteps.Constants.Position;
import com.cutlerdevelopment.footballsteps.Constants.Words;
import com.cutlerdevelopment.footballsteps.Models.OfflineGame;
import com.cutlerdevelopment.footballsteps.Models.OfflinePlayer;
import com.cutlerdevelopment.footballsteps.Models.SavedData;
import com.cutlerdevelopment.footballsteps.R;
import com.cutlerdevelopment.footballsteps.Utils.DateHelper;

import java.util.HashMap;
import java.util.Map;

public class OfflineCareerMainMenu extends AppCompatActivity implements OfflineCareerMatchesMenu.onBackPressed {

    LinearLayout shirtColour;
    TextView nameField;
    TextView positionField;
    TextView clubField;

    TextView appearancesField;
    TextView statsHeader1;
    TextView statsField1;
    TextView statsHeader2;
    TextView statsField2;

    Button matchesButton;
    Button playNextMatchButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_career_main_menu);



        shirtColour = findViewById(R.id.mainMenuShirtColour);
        nameField = findViewById(R.id.mainMenuName);
        positionField = findViewById(R.id.mainMenuPosition);
        clubField = findViewById(R.id.mainMenuClub);
        matchesButton = findViewById(R.id.playerMatchesButton);
        playNextMatchButton = findViewById(R.id.playNextMatchButton);

        OfflinePlayer player = OfflinePlayer.getInstance();
        int teamColour = player.getCurrTeam().getColour();

        shirtColour.setBackgroundColor(ContextCompat.getColor(this, Colour.getBackgroundColour(teamColour)));
        nameField.setText(getString(R.string.main_menu_name, player.getFirstName(), player.getSurname()));
        positionField.setText(Position.getPositionLongName(player.getPosition()));
        clubField.setText(player.getCurrTeam().getName());
        checkPlayNextMatchButton();

        appearancesField = findViewById(R.id.mainMenuAppsField);
        statsHeader1 = findViewById(R.id.mainMenuStatsHeader1);
        statsField1 = findViewById(R.id.mainMenuStatsField1);
        statsHeader2 = findViewById(R.id.mainMenuStatsHeader2);
        statsField2 = findViewById(R.id.mainMenuStatsField2);

        appearancesField.setText(String.valueOf(player.getAppearances()));


        Map<Map<Integer, Integer>, Map<Integer, Integer>> statsMap = Words.getFirstHeaderAndStat();

        for (HashMap.Entry<Map<Integer, Integer>, Map<Integer, Integer>> pairOfMaps : statsMap.entrySet()) {
            for (HashMap.Entry<Integer, Integer> firstMap : pairOfMaps.getKey().entrySet()) {
                statsHeader1.setText(getText(firstMap.getKey()));
                statsField1.setText(String.valueOf(firstMap.getValue()));
            }
            for (HashMap.Entry<Integer, Integer> secondMap : pairOfMaps.getValue().entrySet()) {
                statsHeader2.setText(getText(secondMap.getKey()));
                statsField2.setText(String.valueOf(secondMap.getValue()));
            }
        }

    }

    public void openMatchesFragment(View view) {
        OfflineCareerMatchesMenu fragment = new OfflineCareerMatchesMenu();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.matchFragmentContainer, fragment, "fragmentTag")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onBackPressed() {
        matchesButton.setEnabled(true);
        this.getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        checkPlayNextMatchButton();
    }

    public void playNextMatch(View view) {

    }

    void checkPlayNextMatchButton() {
        if (SavedData.getInstance().getLastAddedActivity() != null) {
            int daysBetween = DateHelper.getDaysBetween(OfflinePlayer.getInstance().getDateLastMatchPlayed(), SavedData.getInstance().getLastAddedActivity().getDate());


            if (daysBetween < 0) {
                playNextMatchButton.setEnabled(true);
            }
        }
    }

    public void onRefreshSteps(View view) {

        OfflineGame.getInstance().refreshPlayerActivity();
        checkPlayNextMatchButton();

    }
}
