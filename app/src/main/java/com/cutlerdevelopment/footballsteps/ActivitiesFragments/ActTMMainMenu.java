package com.cutlerdevelopment.footballsteps.ActivitiesFragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cutlerdevelopment.footballsteps.Constants.Colour;
import com.cutlerdevelopment.footballsteps.Constants.Words;
import com.cutlerdevelopment.footballsteps.Models.TeamMode.TMSavedData;
import com.cutlerdevelopment.footballsteps.Models.TeamMode.TMTeam;
import com.cutlerdevelopment.footballsteps.Models.TeamMode.TMUserTeam;
import com.cutlerdevelopment.footballsteps.R;
import com.cutlerdevelopment.footballsteps.Utils.Dialogs.TMFixturesDialogFragment;
import com.cutlerdevelopment.footballsteps.Utils.LeagueTableHelper;

import java.util.List;

public class ActTMMainMenu extends AppCompatActivity {

    private ImageView teamColourView;
    private Button teamBadgeButton;
    private TextView teamNameText;
    private TextView teamPositionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tm_main_menu);

        teamColourView = findViewById(R.id.pmMenuBadgeButton);
        teamBadgeButton = findViewById(R.id.pmMenuBadgeColour);
        teamNameText = findViewById(R.id.pmMenuTeamName);
        teamPositionText = findViewById(R.id.pmMenuLeaguePosition);

        TMUserTeam team = TMSavedData.getInstance().getOfflineTeam();
        teamColourView.setBackgroundColor(getResources().getColor(Colour.getBackgroundColour(team.getColour())));
        teamNameText.setText(team.getName());

        String positionWithSuffix = Words.getNumberWithDateSuffix(LeagueTableHelper.getPositionInLeague(team.getTeamID(), 1));
        teamPositionText.setText(getString(R.string.position_in_league, positionWithSuffix, Words.getLeagueName(team.getLeague())));
    }

    public void openTMFixtures(View view) {

        DialogFragment careerDialog = new TMFixturesDialogFragment();
        careerDialog.show(getSupportFragmentManager(), "TMFixturesDialogFragment");
    }
}
