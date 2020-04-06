package com.cutlerdevelopment.footballsteps.ActivitiesFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;

import com.cutlerdevelopment.footballsteps.Constants.Words;
import com.cutlerdevelopment.footballsteps.Models.ProCareer.UserPlayer;
import com.cutlerdevelopment.footballsteps.Models.SharedModels.AppSavedData;
import com.cutlerdevelopment.footballsteps.Models.ProCareer.ProFixture;
import com.cutlerdevelopment.footballsteps.Models.SharedModels.PlayerActivity;
import com.cutlerdevelopment.footballsteps.Models.ProCareer.OfflineProSavedData;
import com.cutlerdevelopment.footballsteps.R;
import com.cutlerdevelopment.footballsteps.Utils.DateHelper;
import com.cutlerdevelopment.footballsteps.Utils.ViewItems.ProMatchItem;
import com.cutlerdevelopment.footballsteps.Utils.ViewAdapters.ProMatchItemAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FragProMatchesMenu extends Fragment {

    public FragProMatchesMenu() {
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
        View rootView = inflater.inflate(R.layout.fragment_pro_matches_menu, container, false);

        matchesButton = this.getActivity().findViewById(R.id.playerMatchesButton);
        UserPlayer player = UserPlayer.getInstance();
        matchGrid = rootView.findViewById(R.id.matchGridLayout);
        playNextGameButton = this.getActivity().findViewById(R.id.playNextMatchButton);


        matchesButton.setEnabled(false);
        playNextGameButton.setEnabled(false);

        final ArrayList<ProMatchItem> myFixtureItems = new ArrayList<>();
        List<ProFixture> allFixtures = OfflineProSavedData.getInstance().getAllFixtures();
        Collections.sort(allFixtures);
        for (ProFixture f : allFixtures) {
            ProMatchItem item = new ProMatchItem();
            item.setMatchDate(DateHelper.formatDate(f.getDate()));
            item.setHomeTeam(OfflineProSavedData.getInstance().getTeamFromID(f.getHomeTeamID()).getName());
            item.setAwayTeam(OfflineProSavedData.getInstance().getTeamFromID(f.getAwayTeamID()).getName());

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
                if (f.getHomeTeamID() == player.getCurrTeamID() || f.getAwayTeamID() == player.getCurrTeamID()) {
                    //item.setResult(f.getMatchResultForTeam(player.getCurrTeamID()));
                }
            }

            Date today = new Date();
            PlayerActivity thisDatesActivity = AppSavedData.getInstance().getPlayerActivityOnDate(f.getDate());
            if (DateHelper.getDaysBetween(f.getDate(), today) < 0 && thisDatesActivity != null) {
                item.setNumSteps(Words.getNumberWithCommas(thisDatesActivity.getSteps()));
            }
            myFixtureItems.add(item);
        }


        ProMatchItemAdapter adapter = new ProMatchItemAdapter(getActivity().getApplicationContext(), myFixtureItems);
        matchGrid.setAdapter(adapter);
        matchGrid.setNumColumns(2);

        return rootView;

   }


}
