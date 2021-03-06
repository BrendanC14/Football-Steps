package com.cutlerdevelopment.footballsteps.ActivitiesFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.cutlerdevelopment.footballsteps.Constants.Position;
import com.cutlerdevelopment.footballsteps.Constants.Words;
import com.cutlerdevelopment.footballsteps.Models.ProCareer.ProGame;
import com.cutlerdevelopment.footballsteps.Models.ProCareer.ProUsersPlayer;
import com.cutlerdevelopment.footballsteps.Models.ProCareer.ProSettings;
import com.cutlerdevelopment.footballsteps.Models.SharedModels.SavedData;
import com.cutlerdevelopment.footballsteps.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class FragCreateProPlayer extends Fragment {

    public FragCreateProPlayer() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle  savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    Button newOfflineGame;
    private Button continueButton;
    private TextInputLayout firstNameField;
    private TextInputLayout surnameField;
    private Spinner teamSpinner;
    private Spinner positionSpinner;
    private Button submitButton;


    public interface onFinishedListener {
        void onFinishedListener();
    }
    onFinishedListener finishCallback;
    public void setOnSubmittedListener(onFinishedListener callback) {
        this.finishCallback = callback;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_create_pro_player, container, false);

        newOfflineGame = this.getActivity().findViewById(R.id.newOfflineGame);
        continueButton = rootView.findViewById(R.id.continueButton);
        firstNameField = rootView.findViewById(R.id.firstNameField);
        surnameField = rootView.findViewById(R.id.surnameField);
        teamSpinner = rootView.findViewById(R.id.teamSpinner);
        positionSpinner = rootView.findViewById(R.id.positionSpinner);
        submitButton = rootView.findViewById(R.id.submitOfflinePlayer);

        ProUsersPlayer player = SavedData.getInstance().getOfflinePlayer();
        if (player != null) {
            continueButton.setVisibility(View.VISIBLE);
            continueButton.setText(getString(R.string.continue_offline_player_button, player.getFirstName(), player.getSurname()));
            continueButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    continueCareer();
                }
            });
        }
        else { continueButton.setVisibility(View.GONE); }

        List<String> teamList = Words.teamNames;
        Collections.sort(teamList);
        ArrayAdapter<String> teamAdapter = new ArrayAdapter<>(
                this.getActivity(), R.layout.spinner_item, teamList);
        teamSpinner.setAdapter(teamAdapter);

        List<String> positionList = new ArrayList<>();
        for (int i = 1; i <= Position.NUMPOSITIONS; i++) {
            positionList.add(Position.getPositionShortName(i));
        }
        ArrayAdapter<String> positionAdapter = new ArrayAdapter<>(
                this.getActivity(), R.layout.spinner_item, positionList);
        positionSpinner.setAdapter(positionAdapter);

        newOfflineGame.setEnabled(false);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SubmitPlayer();
            }
        });

        return rootView;
    }

    public void SubmitPlayer() {

        String firstName;
        String surname;
        String favTeam;
        String position;


        firstName = firstNameField.getEditText().getText().toString();
        surname = surnameField.getEditText().getText().toString();


        position = positionSpinner.getSelectedItem().toString();
        favTeam = teamSpinner.getSelectedItem().toString();

        SavedData.getInstance().resetDB();


        ProSettings settings = new ProSettings();
        settings.assignDefaultSettings();
        ProGame game = new ProGame();
        game.startNewGame();
        new ProUsersPlayer(firstName, surname, Position.getPositionFromShortString(position),
                SavedData.getInstance().getIDFromName(favTeam));
        game.deleteAIPlayerFromPlayersTeam();

        finishCallback.onFinishedListener();
        getFragmentManager().popBackStack();

    }

    public void continueCareer() {

        SavedData.getInstance().getOfflinePlayer();
        SavedData.getInstance().getOfflineGame();
        SavedData.getInstance().getOfflineSettings();

        finishCallback.onFinishedListener();
        getFragmentManager().popBackStack();
    }
}
