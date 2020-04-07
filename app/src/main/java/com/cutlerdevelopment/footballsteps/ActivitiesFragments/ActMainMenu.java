package com.cutlerdevelopment.footballsteps.ActivitiesFragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.cutlerdevelopment.footballsteps.Models.SharedModels.AppSavedData;
import com.cutlerdevelopment.footballsteps.Models.TeamMode.TMGame;
import com.cutlerdevelopment.footballsteps.Models.TeamMode.TMSavedData;
import com.cutlerdevelopment.footballsteps.Models.TeamMode.TMSettings;
import com.cutlerdevelopment.footballsteps.Models.TeamMode.TMUserTeam;
import com.cutlerdevelopment.footballsteps.R;
import com.cutlerdevelopment.footballsteps.Utils.Dialogs.CreateCareerDialogFragment;
import com.cutlerdevelopment.footballsteps.Utils.Dialogs.CreateTeamDialogFragment;

public class ActMainMenu extends AppCompatActivity implements CreateCareerDialogFragment.CreateCareerDialogListener,
        CreateTeamDialogFragment.CreateTeamDialogListener {

    boolean teamModeSelected;
    boolean targetModeSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    public void openCreateCareerDialog(View view) {
        DialogFragment careerDialog = new CreateCareerDialogFragment();
        careerDialog.show(getSupportFragmentManager(), "CreateCareerDialogFragment");
    }

    @Override
    public void confirmCareerSettings(DialogFragment dialog, boolean teamMode, boolean targetMode) {

        teamModeSelected = teamMode;
        targetModeSelected = targetMode;

        if (teamModeSelected) {
            DialogFragment teamDialog = new CreateTeamDialogFragment();
            Bundle args = new Bundle();
            args.putBoolean("targetMode", targetMode);
            teamDialog.setArguments(args);
            teamDialog.show(getSupportFragmentManager(), "CreateTeamDialogFragment");

        }
    }

    @Override
    public void teamCreated(DialogFragment dialog, String name, int colour, int steps) {

        AppSavedData.createSavedDataInstance(this);
        //TODO: ABSOLUTELY REMOVE
        AppSavedData.getInstance().resetDB();

        TMSavedData.createOfflineTeamSavedData(this);
        TMSavedData.getInstance().resetDB();

        TMSettings settings = new TMSettings();
        settings.assignDefaultSettings(steps);

        TMGame game = new TMGame();
        game.startNewGame();

        new TMUserTeam(name, colour);

        Intent intent = new Intent(this, ActTMMainMenu.class);
        startActivity(intent);

    }

}
