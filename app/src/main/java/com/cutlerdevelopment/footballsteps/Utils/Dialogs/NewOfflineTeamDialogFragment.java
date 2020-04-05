package com.cutlerdevelopment.footballsteps.Utils.Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.cutlerdevelopment.footballsteps.Constants.Colour;
import com.cutlerdevelopment.footballsteps.Constants.Numbers;
import com.cutlerdevelopment.footballsteps.Constants.Position;
import com.cutlerdevelopment.footballsteps.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class NewOfflineTeamDialogFragment extends DialogFragment {

    private String teamName;
    private String teamColour;
    private int stepTarget;

    private TextInputLayout teamNameTextView;
    private Spinner teamColourSpinner;
    private TextView stepTargetHeader;
    private Spinner stepTargetSpinner;
    private TextView step000s;
    private Button confirmButton;

    private boolean targetModeSelected;

    public interface NewOfflineTeamDialogListener {
        public void teamCreated(DialogFragment dialog, String teamName, String teamColour, int stepTarget);
    }
    NewOfflineTeamDialogListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (NewOfflineTeamDialogListener) context;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        targetModeSelected = getArguments().getBoolean("targetMode");
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View newOfflineTeamView = inflater.inflate(R.layout.dialog_new_offline_team, null);

        teamNameTextView = newOfflineTeamView.findViewById(R.id.newOfflineTeamClubName);
        teamColourSpinner = newOfflineTeamView.findViewById(R.id.newOfflineTeamColourSpinner);
        stepTargetHeader = newOfflineTeamView.findViewById(R.id.newOfflineTeamNumStepsHeader);
        stepTargetSpinner = newOfflineTeamView.findViewById(R.id.newOfflineTeamNumStepsSpinner);
        step000s = newOfflineTeamView.findViewById(R.id.thousandsText);
        confirmButton = newOfflineTeamView.findViewById(R.id.newOfflineTeamConfirmButton);

        stepTargetHeader.setVisibility(View.GONE);
        stepTargetSpinner.setVisibility(View.GONE);
        step000s.setVisibility(View.GONE);

        List<String> colourList = new ArrayList<>();
        for (int i = 1; i <= Colour.NUM_TEAM_COLOURS; i++) {
            colourList.add(Colour.getColourName(i));
        }
        ArrayAdapter<String> colourAdapter = new ArrayAdapter<>(
                getActivity(), R.layout.spinner_item, colourList);
        teamColourSpinner.setAdapter(colourAdapter);

        teamColourSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                confirmButton.setBackgroundColor(getResources().getColor(Colour.getBackgroundColour(i + 1)));
                confirmButton.setTextColor(getResources().getColor(Colour.getTextColour(i + 1)));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        if (targetModeSelected) {

            stepTargetSpinner.setVisibility(View.VISIBLE);
            stepTargetHeader.setVisibility(View.VISIBLE);
            step000s.setVisibility(View.VISIBLE);

            List<String> stepList = new ArrayList<>();
            for (int i = 1; i <= Numbers.MAX_NUM_STEPS_TARGET; i++) {
                stepList.add(String.valueOf(i));
            }
            ArrayAdapter<String> stepAdapter = new ArrayAdapter<>(
                    getActivity(), R.layout.spinner_item, stepList);
            stepTargetSpinner.setAdapter(stepAdapter);
        }

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                teamName = teamNameTextView.getEditText().getText().toString();
                teamColour = teamColourSpinner.getSelectedItem().toString();
                stepTarget = stepTargetSpinner.getSelectedItemPosition() + 1;

                dismiss();
                listener.teamCreated(NewOfflineTeamDialogFragment.this, teamName, teamColour, stepTarget);
            }
        });

        builder.setView(newOfflineTeamView);

        return builder.create();
    }

}
