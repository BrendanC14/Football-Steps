package com.cutlerdevelopment.footballsteps.Activities;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.cutlerdevelopment.footballsteps.Constants.Words;
import com.cutlerdevelopment.footballsteps.Models.Fixture;
import com.cutlerdevelopment.footballsteps.Models.OfflineGame;
import com.cutlerdevelopment.footballsteps.Models.OfflinePlayer;
import com.cutlerdevelopment.footballsteps.Models.PlayerActivity;
import com.cutlerdevelopment.footballsteps.Models.SavedData;
import com.cutlerdevelopment.footballsteps.R;
import com.cutlerdevelopment.footballsteps.Utils.DateHelper;
import com.cutlerdevelopment.footballsteps.Utils.MatchFragmentItem;
import com.cutlerdevelopment.footballsteps.Utils.MatchFragmentItemAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class OfflineCareerMatchesMenu extends Fragment {

    public OfflineCareerMatchesMenu() {
        // Required empty public constructor
    }

    private GridView matchGrid;
    private Button matchesButton;
    private Button playNextGameButton;

    public interface onBackPressed {
        void onBackPressed();
    }
    onBackPressed finishCallback;
    public void setOnBackPressedListener(onBackPressed callback) {
        this.finishCallback = callback;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_offline_career_matches_menu, container, false);

        matchesButton = this.getActivity().findViewById(R.id.playerMatchesButton);
        OfflinePlayer player = OfflinePlayer.getInstance();
        matchGrid = rootView.findViewById(R.id.matchGridLayout);
        playNextGameButton = this.getActivity().findViewById(R.id.playNextMatchButton);


        matchesButton.setEnabled(false);
        playNextGameButton.setEnabled(false);

        final ArrayList<MatchFragmentItem> myFixtureItems = new ArrayList<>();
        for (Fixture f : SavedData.getInstance().getFixturesForTeam(player.getCurrTeamID())) {
            MatchFragmentItem item = new MatchFragmentItem();
            item.setMatchDate(DateHelper.formatDate(f.getDate()));
            item.setHomeTeam(SavedData.getInstance().getTeamFromID(f.getHomeTeamID()).getName());
            item.setAwayTeam(SavedData.getInstance().getTeamFromID(f.getAwayTeamID()).getName());

            Map<Map<Integer, Integer>, Map<Integer, Integer>> statsMap = Words.getFirstHeaderAndStat();

            for (HashMap.Entry<Map<Integer, Integer>, Map<Integer, Integer>> pairOfMaps : statsMap.entrySet()) {
                for (HashMap.Entry<Integer, Integer> firstMap : pairOfMaps.getKey().entrySet()) {
                    item.setPerfHeader1(getText(firstMap.getKey()));
                    item.setPerfStat1(String.valueOf(firstMap.getValue()));
                }
                for (HashMap.Entry<Integer, Integer> secondMap : pairOfMaps.getValue().entrySet()) {
                    item.setPerfHeader2(getText(secondMap.getKey()));
                    item.setPerfStat2(String.valueOf(secondMap.getValue()));
                }
            }

            if (f.matchPlayed()) {
                item.setHomeScore(String.valueOf(f.getHomeScore()));
                item.setAwayScore(String.valueOf(f.getAwayScore()));
                item.setResult(f.getMatchResultForTeam(player.getCurrTeamID()));
            }

            Date today = new Date();
            PlayerActivity thisDatesActivity = SavedData.getInstance().getPlayerActivityOnDate(f.getDate());
            if (DateHelper.getDaysBetween(f.getDate(), today) < 0 && thisDatesActivity != null) {
                item.setNumSteps(Words.getNumberWithCommas(thisDatesActivity.getSteps()));
            }
            myFixtureItems.add(item);
        }


        MatchFragmentItemAdapter adapter = new MatchFragmentItemAdapter(getActivity().getApplicationContext(), myFixtureItems);
        matchGrid.setAdapter(adapter);
        matchGrid.setNumColumns(2);

        return rootView;

   }


}
