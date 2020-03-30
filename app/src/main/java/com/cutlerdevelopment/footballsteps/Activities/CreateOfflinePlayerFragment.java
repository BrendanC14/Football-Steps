package com.cutlerdevelopment.footballsteps.Activities;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.cutlerdevelopment.footballsteps.Constants.Colour;
import com.cutlerdevelopment.footballsteps.Constants.Position;
import com.cutlerdevelopment.footballsteps.Constants.Words;
import com.cutlerdevelopment.footballsteps.Models.OfflineGame;
import com.cutlerdevelopment.footballsteps.Models.OfflinePlayer;
import com.cutlerdevelopment.footballsteps.Models.SavedData;
import com.cutlerdevelopment.footballsteps.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class CreateOfflinePlayerFragment extends Fragment {

    public CreateOfflinePlayerFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle  savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private Button continueButton;
    private TextInputLayout firstNameField;
    private TextInputLayout surnameField;
    private Spinner teamSpinner;
    private Spinner positionSpinner;
    private TextView errorField;
    private Button submitButton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_create_offline_player, container, false);

        continueButton = rootView.findViewById(R.id.continueButton);
        OfflinePlayer player = SavedData.getInstance().loadOfflinePlayer();
        if (player != null) {
            continueButton.setVisibility(View.VISIBLE);
            continueButton.setText(getString(R.string.continue_offline_player_button, player.getFirstName(), player.getSurname()));
        }


        firstNameField = rootView.findViewById(R.id.firstNameField);
        surnameField = rootView.findViewById(R.id.surnameField);
        List<String> teamList = Words.TeamNames;
        Collections.sort(teamList);
        ArrayAdapter<String> teamAdapter = new ArrayAdapter<>(
                this.getActivity(), R.layout.spinner_item, teamList);
        teamSpinner = rootView.findViewById(R.id.teamSpinner);
        teamSpinner.setAdapter(teamAdapter);

        List<String> positionList = new ArrayList<>();
        for (int i = 1; i <= Position.NUMPOSITIONS; i++) {
            positionList.add(Position.getPositionShortName(i));
        }
        ArrayAdapter<String> positionAdapter = new ArrayAdapter<>(
                this.getActivity(), R.layout.spinner_item, positionList);
        positionSpinner = rootView.findViewById(R.id.positionSpinner);
        positionSpinner.setAdapter(positionAdapter);

        Button newOfflineGame = this.getActivity().findViewById(R.id.newOfflineGame);
        newOfflineGame.setEnabled(false);

        errorField = rootView.findViewById(R.id.errorField);
        submitButton = rootView.findViewById(R.id.submitOfflinePlayer);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SubmitPlayer();
            }
        });

        return rootView;
    }

    public interface OnSubmittedListener {
        void onOfflinePlayerSubmitted();
    }
    OnSubmittedListener callback;
    public void setOnSubmittedListener(OnSubmittedListener callback) {
        this.callback = callback;
    }


    public void SubmitPlayer() {

        String firstName;
        String surname;
        String favTeam;
        String position;

        try {
            firstName = firstNameField.getEditText().getText().toString();
        } catch (Exception e) {
            errorField.setVisibility(View.VISIBLE);
            errorField.setText(e.getMessage());
            return;
        }

        try {
            surname = surnameField.getEditText().getText().toString();
        } catch (Exception e) {
            errorField.setVisibility(View.VISIBLE);
            errorField.setText(e.getMessage());
            return;
        }

        errorField.setVisibility(View.GONE);
        position = teamSpinner.getSelectedItem().toString();
        favTeam = teamSpinner.getSelectedItem().toString();

        OfflinePlayer.CreateOfflinePlayer(firstName, surname, Position.getPositionFromShortString(position),
                OfflineGame.getInstance().getTeamFromName(favTeam));

        getFragmentManager().popBackStack();
        callback.onOfflinePlayerSubmitted();

    }
}
