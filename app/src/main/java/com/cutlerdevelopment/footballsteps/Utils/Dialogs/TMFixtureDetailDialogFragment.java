package com.cutlerdevelopment.footballsteps.Utils.Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.provider.Telephony;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.cutlerdevelopment.footballsteps.Constants.Numbers;
import com.cutlerdevelopment.footballsteps.Models.TeamMode.TMFixture;
import com.cutlerdevelopment.footballsteps.Models.TeamMode.TMSavedData;
import com.cutlerdevelopment.footballsteps.Models.TeamMode.TMSavedDataOfflineTeamSettingsDao_Impl;
import com.cutlerdevelopment.footballsteps.Models.TeamMode.TMSettings;
import com.cutlerdevelopment.footballsteps.Models.TeamMode.TMTeam;
import com.cutlerdevelopment.footballsteps.R;
import com.cutlerdevelopment.footballsteps.Utils.LeagueTableHelper;
import com.cutlerdevelopment.footballsteps.Utils.ViewAdapters.SmallTableItemAdapter;
import com.cutlerdevelopment.footballsteps.Utils.ViewItems.SmallTableItem;

import java.util.ArrayList;
import java.util.List;

public class TMFixtureDetailDialogFragment extends DialogFragment {

    private TextView homeTeamField;
    private TextView awayTeamField;
    private ListView homeTeamLeagueList;
    private ListView awayTeamLeagueList;
    private TextView stepNumberField;
    private Button decreaseStepsButton;
    private Button increaseStepsButton;
    private Button confirmButton;
    int matchID;
    int newStepTarget;

    SmallTableItemAdapter homeAdapter;
    SmallTableItemAdapter awayAdapter;

    public interface TMFixtureChangedListener {
        public void tmFixtureChanged(int newSteps);
    }

    public TMFixtureChangedListener listener;

    @Override public void onAttach(Context context) {
        super.onAttach(context);
        listener = (TMFixtureChangedListener) getTargetFragment();
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View tmFixtureDetailView = inflater.inflate(R.layout.dialog_tm_fixture_detail, null);
        matchID = getArguments().getInt("matchID");

        homeTeamField = tmFixtureDetailView.findViewById(R.id.fixture_detail_home_team);
        awayTeamField = tmFixtureDetailView.findViewById(R.id.fixture_detail_away_team);
        homeTeamLeagueList = tmFixtureDetailView.findViewById(R.id.fixtureDetailHomeLeagueList);
        awayTeamLeagueList = tmFixtureDetailView.findViewById(R.id.fixtureDetailAwayLeagueList);
        stepNumberField = tmFixtureDetailView.findViewById(R.id.fixtureDetailStepNumber);
        decreaseStepsButton = tmFixtureDetailView.findViewById(R.id.fixtureDetailDecreaseSteps);
        increaseStepsButton = tmFixtureDetailView.findViewById(R.id.fixtureDetailIncreaseSteps);
        confirmButton = tmFixtureDetailView.findViewById(R.id.fixtureDetailConfirmButton);

        TMFixture fix = TMSavedData.getInstance().getFixtureFromID(matchID);
        TMTeam homeTeam = TMSavedData.getInstance().getTeamFromID(fix.getHomeTeamID());
        TMTeam awayTeam = TMSavedData.getInstance().getTeamFromID(fix.getAwayTeamID());

        homeTeamField.setText(homeTeam.getName());
        awayTeamField.setText(awayTeam.getName());
        stepNumberField.setText(String.valueOf(fix.getStepTarget()));
        newStepTarget = fix.getStepTarget();

        increaseStepsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                increaseSteps();;
            }
        });

        decreaseStepsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decreaseSteps();;
            }
        });
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveFixture();
            }
        });

        final ArrayList<SmallTableItem> homeTableItems = new ArrayList<>();
        final ArrayList<SmallTableItem> awayTableItems = new ArrayList<>();

        final List<TMTeam> homeTeamTableNeighbours = LeagueTableHelper.getLeagueNeighbours(homeTeam, 4);
        final List<TMTeam> awayTeamTableNeighbours = LeagueTableHelper.getLeagueNeighbours(awayTeam, 4);

        SmallTableItem header = new SmallTableItem();
        header.setPosition("Pos");
        header.setTeamName("Team Name");
        header.setGamesPlayed("GP");
        header.setDifference("GD");
        header.setPoints("Pts");

        homeTableItems.add(header);
        awayTableItems.add(header);
        for (TMTeam team : homeTeamTableNeighbours) {
            SmallTableItem item = new SmallTableItem();
            item.setPosition(String.valueOf(LeagueTableHelper.getPositionInLeague(team.getID(), team.getLeague())));
            item.setTeamName(team.getName());
            item.setGamesPlayed(String.valueOf(team.getGamesPlayed()));
            item.setDifference(String.valueOf(team.getGoalDifference()));
            item.setPoints(String.valueOf(team.getPoints()));

            homeTableItems.add(item);
        }

        for (TMTeam team : awayTeamTableNeighbours) {
            SmallTableItem item = new SmallTableItem();
            item.setPosition(String.valueOf(LeagueTableHelper.getPositionInLeague(team.getID(), team.getLeague())));
            item.setTeamName(team.getName());
            item.setGamesPlayed(String.valueOf(team.getGamesPlayed()));
            item.setDifference(String.valueOf(team.getGoalDifference()));
            item.setPoints(String.valueOf(team.getPoints()));

            awayTableItems.add(item);
        }

        homeAdapter = new SmallTableItemAdapter(getActivity().getApplicationContext(), homeTableItems);
        awayAdapter = new SmallTableItemAdapter(getActivity().getApplicationContext(), awayTableItems);

        homeTeamLeagueList.setAdapter(homeAdapter);
        awayTeamLeagueList.setAdapter(awayAdapter);

        builder.setView(tmFixtureDetailView);

        return builder.create();
    }

    void increaseSteps() {
        if (newStepTarget < (Numbers.MAX_NUM_STEPS_TARGET * 2000)) {
            newStepTarget += 1000;
            stepNumberField.setText(String.valueOf(newStepTarget));
        }
    }

    void decreaseSteps() {

        if (newStepTarget > 1000) {
            newStepTarget -= 1000;
            stepNumberField.setText(String.valueOf(newStepTarget));
        }
    }

    void saveFixture() {
        TMSavedData.getInstance().getFixtureFromID(matchID).changeStepTarget(newStepTarget);
        listener.tmFixtureChanged(newStepTarget);
        dismiss();
    }


}
