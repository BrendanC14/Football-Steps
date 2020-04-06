package com.cutlerdevelopment.footballsteps.ActivitiesFragments;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cutlerdevelopment.footballsteps.Constants.Colour;
import com.cutlerdevelopment.footballsteps.Models.TeamCareer.OfflineTeamSavedData;
import com.cutlerdevelopment.footballsteps.Models.TeamCareer.UserTeam;
import com.cutlerdevelopment.footballsteps.R;

public class ActTeamMainMenu extends AppCompatActivity {

    private ImageView teamColourView;
    private Button teamBadgeButton;
    private TextView teamNameText;
    private TextView teamPositionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_main_menu);

        teamColourView = findViewById(R.id.teamMainMenuBadgeColour);
        teamBadgeButton = findViewById(R.id.teamMainMenuBadgeButton);
        teamNameText = findViewById(R.id.teamMainMenuTeamName);
        teamPositionText = findViewById(R.id.teamMainMenuPosition);

        UserTeam team = OfflineTeamSavedData.getInstance().getOfflineTeam();

        teamColourView.setBackgroundColor(getResources().getColor(Colour.getBackgroundColour(team.getColour())));
        teamNameText.setText(team.getName());
        teamPositionText.setText("1st in Premier League");
    }
}
