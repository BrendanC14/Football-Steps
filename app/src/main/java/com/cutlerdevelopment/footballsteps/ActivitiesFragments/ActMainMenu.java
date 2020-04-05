package com.cutlerdevelopment.footballsteps.ActivitiesFragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.view.View;

import com.cutlerdevelopment.footballsteps.R;
import com.cutlerdevelopment.footballsteps.Utils.Dialogs.NewOfflineCareerDialogFragment;
import com.cutlerdevelopment.footballsteps.Utils.Dialogs.NewOfflineTeamDialogFragment;

public class ActMainMenu extends AppCompatActivity implements NewOfflineCareerDialogFragment.NewOfflineCareerDialogListener,
        NewOfflineTeamDialogFragment.NewOfflineTeamDialogListener {

    boolean teamModeSelected;
    boolean targetModeSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    public void showNewOfflineCareerDialog(View view) {
        DialogFragment careerDialog = new NewOfflineCareerDialogFragment();
        careerDialog.show(getSupportFragmentManager(), "NewOfflineCareerDialogFragment");
    }

    @Override
    public void confirmCareerSettings(DialogFragment dialog, boolean teamMode, boolean targetMode) {

        teamModeSelected = teamMode;
        targetModeSelected = targetMode;

        if (teamModeSelected) {
            DialogFragment teamDialog = new NewOfflineTeamDialogFragment();
            Bundle args = new Bundle();
            args.putBoolean("targetMode", targetMode);
            teamDialog.setArguments(args);
            teamDialog.show(getSupportFragmentManager(), "NewOfflineTeamDialogFragment");

        }
    }

    @Override
    public void teamCreated(DialogFragment dialog, String name, String colour, int steps) {

    }

}
