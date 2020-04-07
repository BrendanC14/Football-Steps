package com.cutlerdevelopment.footballsteps.Utils.Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import androidx.fragment.app.DialogFragment;

import com.cutlerdevelopment.footballsteps.Constants.Colour;
import com.cutlerdevelopment.footballsteps.Models.TeamMode.TMFixture;
import com.cutlerdevelopment.footballsteps.Models.TeamMode.TMMatchEngine;
import com.cutlerdevelopment.footballsteps.Models.TeamMode.TMSavedData;
import com.cutlerdevelopment.footballsteps.Models.TeamMode.TMSettings;
import com.cutlerdevelopment.footballsteps.Models.TeamMode.TMUserTeam;
import com.cutlerdevelopment.footballsteps.R;
import com.cutlerdevelopment.footballsteps.Utils.DateHelper;
import com.cutlerdevelopment.footballsteps.Utils.ViewAdapters.FixtureItemAdapter;
import com.cutlerdevelopment.footballsteps.Utils.ViewItems.FixtureItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TMFixturesDialogFragment extends DialogFragment implements TMFixtureDetailDialogFragment.TMFixtureChangedListener,
        FixtureItemAdapter.TMPlayMatchListener {

    private GridView fixtureGridView;
    private Button viewFixtureButton;
    private Button confirmButton;
    private Button playMatchButton;

    private FixtureItem currentSelectedItem;

    private static final long DOUBLE_PRESS_INTERVAL = 250;
    private long lastPressTime;

    FixtureItemAdapter adapter;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View tmFixturesView = inflater.inflate(R.layout.dialog_tm_fixtures, null);

        fixtureGridView = tmFixturesView.findViewById(R.id.tmFixturesGridView);
        viewFixtureButton = tmFixturesView.findViewById(R.id.tmFixturesViewFixtureButton);
        confirmButton = tmFixturesView.findViewById(R.id.tmFixturesDoneButton);
        playMatchButton = tmFixturesView.findViewById(R.id.fixtureItemPlayButton);

        viewFixtureButton.setEnabled(false);

        final ArrayList<FixtureItem> myFixtureItems = new ArrayList<>();
        List<TMFixture> allFixtures = TMSavedData.getInstance().getAllUpcomingFixturesForTeam(TMUserTeam.getInstance().getTeamID());
        Collections.sort(allFixtures);
        for (TMFixture f : allFixtures) {
            FixtureItem item = new FixtureItem();
            item.setMatchID(f.getID());
            item.setMatchDate(DateHelper.formatDate(f.getDate()));

            int userID = TMUserTeam.getInstance().getTeamID();
            int opponentID;
            if (f.getHomeTeamID() == userID) {
                opponentID = f.getAwayTeamID();
            } else {
                opponentID = f.getHomeTeamID();
            }

            item.setOpponentTeam(TMSavedData.getInstance().getTeamFromID(opponentID).getName());

            item.setNumSteps(String.valueOf(f.getStepTarget()));

            myFixtureItems.add(item);

        }

        adapter = new FixtureItemAdapter(getActivity().getApplicationContext(), myFixtureItems, this);
        fixtureGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                FixtureItem fixItem = (FixtureItem) adapterView.getItemAtPosition(i);
                fixItem.setSelected(true);
                adapter.notifyDataSetChanged();
                viewFixtureButton.setEnabled(true);

                if (currentSelectedItem != null && currentSelectedItem != fixItem) {
                    currentSelectedItem.setSelected(false);
                }

                currentSelectedItem = fixItem;

                long pressTime = System.currentTimeMillis();

                if (pressTime - lastPressTime <= DOUBLE_PRESS_INTERVAL) {
                    openFixtureDetails();
                }

                lastPressTime = pressTime;

            }
        });
        fixtureGridView.setAdapter(adapter);
        fixtureGridView.setNumColumns(1);

        viewFixtureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFixtureDetails();
            }
        });
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        builder.setView(tmFixturesView);
        return builder.create();
    }


    public void openFixtureDetails() {

        DialogFragment teamDialog = new TMFixtureDetailDialogFragment();
        Bundle args = new Bundle();
        args.putInt("matchID", currentSelectedItem.getMatchID());
        teamDialog.setArguments(args);

        teamDialog.setTargetFragment(this, 0);
        teamDialog.show(this.getFragmentManager(), "TMFixtureDetailDialogFragment");
    }

    public void tmFixtureChanged(int newSteps) {
        currentSelectedItem.setNumSteps(String.valueOf(newSteps));
        adapter.notifyDataSetChanged();
    }

    @Override
    public void playMatch(int matchID) {
        TMMatchEngine.playUserMatch(TMSavedData.getInstance().getFixtureFromID(matchID));
    }
}
