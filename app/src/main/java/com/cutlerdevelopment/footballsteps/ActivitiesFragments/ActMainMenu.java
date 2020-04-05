package com.cutlerdevelopment.footballsteps.ActivitiesFragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.view.View;

import com.cutlerdevelopment.footballsteps.R;
import com.cutlerdevelopment.footballsteps.Utils.Dialogs.NewOfflineCareerDialogFragment;

public class ActMainMenu extends AppCompatActivity implements NewOfflineCareerDialogFragment.NewOfflineCareerDialogListener {

    boolean teamModeSelected;
    boolean targetModeSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    public void showNewOfflineCareerDialog(View view) {
        DialogFragment dialog = new NewOfflineCareerDialogFragment();
        dialog.show(getSupportFragmentManager(), "NewOfflineCareerDialogFragment");
    }

    @Override
    public void confirmCareerSettings(DialogFragment dialog, boolean teamMode, boolean targetMode) {

        teamModeSelected = teamMode;
        targetModeSelected = targetMode;

        if (teamModeSelected) {

        }
    }

}
