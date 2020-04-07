package com.cutlerdevelopment.footballsteps.Utils.ViewAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.cutlerdevelopment.footballsteps.Constants.Colour;
import com.cutlerdevelopment.footballsteps.Constants.MatchResult;
import com.cutlerdevelopment.footballsteps.R;
import com.cutlerdevelopment.footballsteps.Utils.ViewItems.FixtureItem;

import java.util.ArrayList;

import static androidx.core.content.ContextCompat.getColor;

public class FixtureItemAdapter extends BaseAdapter {

    private ArrayList<FixtureItem> singleRow;
    private LayoutInflater thisInflater;
    private Context context;

    ConstraintLayout background;

    public FixtureItemAdapter(Context context, ArrayList<FixtureItem> aRow) {
        this.singleRow = aRow;
        this.context = context;
        thisInflater = (thisInflater.from(context));
    }

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



        if (currentItem.getResult() == MatchResult.WIN) {
            background.setBackgroundColor(getColor(context, Colour.WIN_BGROUND_COLOUR));
        }
        else if (currentItem.getResult() == MatchResult.DRAW) {
            background.setBackgroundColor(getColor(context, Colour.DRAW_BGROUND_COLOUR));
        }
        else if (currentItem.getResult() == MatchResult.LOSE) {
            background.setBackgroundColor(getColor(context, Colour.LOSE_BGROUND_COLOUR));
        }

        return convertView;

    }
}