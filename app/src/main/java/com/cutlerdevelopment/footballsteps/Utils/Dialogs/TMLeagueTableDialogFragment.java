package com.cutlerdevelopment.footballsteps.Utils.Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import androidx.fragment.app.DialogFragment;

import com.cutlerdevelopment.footballsteps.Models.TeamMode.TMSavedData;
import com.cutlerdevelopment.footballsteps.Models.TeamMode.TMTeam;
import com.cutlerdevelopment.footballsteps.R;
import com.cutlerdevelopment.footballsteps.Utils.LeagueTableHelper;
import com.cutlerdevelopment.footballsteps.Utils.ViewAdapters.SmallTableItemAdapter;
import com.cutlerdevelopment.footballsteps.Utils.ViewItems.SmallTableItem;

import java.util.ArrayList;
import java.util.List;

public class TMLeagueTableDialogFragment extends DialogFragment {

    private ListView leagueTableList;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View tmLeagueTableView = inflater.inflate(R.layout.dialog_tm_league_table, null);

        leagueTableList = tmLeagueTableView.findViewById(R.id.tmLeagueTableListView);

        final ArrayList<SmallTableItem> leagueTableItems = new ArrayList<>();


        SmallTableItem header = new SmallTableItem();
        header.setPosition("Pos");
        header.setTeamName("Team Name");
        header.setGamesPlayed("GP");
        header.setDifference("GD");
        header.setPoints("Pts");

        leagueTableItems.add(header);
        for (TMTeam team : LeagueTableHelper.getTMLeagueList(1)) {
            SmallTableItem item = new SmallTableItem();
            item.setPosition(String.valueOf(LeagueTableHelper.getPositionInLeague(team.getID(), team.getLeague())));
            item.setTeamName(team.getName());
            item.setGamesPlayed(String.valueOf(team.getGamesPlayed()));
            item.setDifference(String.valueOf(team.getGoalDifference()));
            item.setPoints(String.valueOf(team.getPoints()));

            leagueTableItems.add(item);
        }


        SmallTableItemAdapter adapter = new SmallTableItemAdapter(getActivity().getApplicationContext(), leagueTableItems);

        leagueTableList.setAdapter(adapter);

        builder.setView(tmLeagueTableView);

        return builder.create();
    }
}
