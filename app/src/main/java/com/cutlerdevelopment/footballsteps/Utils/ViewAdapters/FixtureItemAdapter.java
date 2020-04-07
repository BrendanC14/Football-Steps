package com.cutlerdevelopment.footballsteps.Utils.ViewAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.cutlerdevelopment.footballsteps.Constants.Colour;
import com.cutlerdevelopment.footballsteps.Models.SharedModels.AppSavedData;
import com.cutlerdevelopment.footballsteps.Models.SharedModels.UserActivity;
import com.cutlerdevelopment.footballsteps.Models.TeamMode.TMFixture;
import com.cutlerdevelopment.footballsteps.Models.TeamMode.TMSavedData;
import com.cutlerdevelopment.footballsteps.R;
import com.cutlerdevelopment.footballsteps.Utils.DateHelper;
import com.cutlerdevelopment.footballsteps.Utils.ViewItems.FixtureItem;

import java.util.ArrayList;
import java.util.Date;

import static androidx.core.content.ContextCompat.getColor;

public class FixtureItemAdapter extends BaseAdapter {

    private ArrayList<FixtureItem> singleRow;
    private LayoutInflater thisInflater;
    private Context context;

    ConstraintLayout background;

    public FixtureItemAdapter(Context context, ArrayList<FixtureItem> aRow, TMPlayMatchListener listener) {
        this.singleRow = aRow;
        this.context = context;
        this.listener = listener;
        thisInflater = (thisInflater.from(context));
    }

    public interface TMPlayMatchListener {
        public void playMatch(int matchID);
    }

    TMPlayMatchListener listener;

    @Override
    public int getCount() {
        return singleRow.size();
    }

    @Override
    public Object getItem(int i) {
        return singleRow.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = thisInflater.inflate(R.layout.fixture_item, parent, false);
        }

        background = convertView.findViewById(R.id.fixtureFragmentItemBground);
        TextView dateField = convertView.findViewById(R.id.fixtureItemDateField);
        TextView awayTeam = convertView.findViewById(R.id.fixtureItemOpponent);
        TextView stepNumber = convertView.findViewById(R.id.fixtureItemStepNumber);
        Button playButton = convertView.findViewById(R.id.fixtureItemPlayButton);

        final FixtureItem currentItem = (FixtureItem) getItem(i);


        dateField.setText(currentItem.getMatchDate());
        awayTeam.setText(currentItem.getOpponentTeam());
        stepNumber.setText(currentItem.getNumSteps());

        if (currentItem.getSelected()) {
            background.setBackgroundColor(getColor(context, Colour.GREEN_COLOUR));
        }
        else {
            background.setBackgroundColor(getColor(context, Colour.WHITE_COLOUR));
        }

        playButton.setVisibility(View.GONE);
        UserActivity activity = AppSavedData.getInstance().getLastAddedActivity();
        if (activity != null) {

            TMFixture f = TMSavedData.getInstance().getFixtureFromID(currentItem.getMatchID());
            boolean activityExists = DateHelper.getDaysBetween(activity.getDate(), f.getDate()) > 0;
            if (activityExists) {
                playButton.setVisibility(View.VISIBLE);
                playButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.playMatch(currentItem.getMatchID());
                    }
                });
            }
        }

        return convertView;

    }
}