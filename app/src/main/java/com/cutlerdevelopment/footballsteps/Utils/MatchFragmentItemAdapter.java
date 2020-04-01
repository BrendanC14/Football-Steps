package com.cutlerdevelopment.footballsteps.Utils;

import android.content.Context;
import android.service.autofill.FieldClassification;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.cutlerdevelopment.footballsteps.Constants.Colour;
import com.cutlerdevelopment.footballsteps.Constants.MatchResult;
import com.cutlerdevelopment.footballsteps.R;

import java.util.ArrayList;

public class MatchFragmentItemAdapter extends BaseAdapter {

    private ArrayList<MatchFragmentItem> singleRow;
    private LayoutInflater thisInflater;
    private Context context;

    public MatchFragmentItemAdapter(Context context, ArrayList<MatchFragmentItem> aRow) {
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
            convertView = thisInflater.inflate(R.layout.match_fragment_item, parent, false);
        }

        ConstraintLayout background = convertView.findViewById(R.id.matchFragmentItemBground);
        TextView dateField = convertView.findViewById(R.id.matchItemDateField);
        TextView homeTeam = convertView.findViewById(R.id.matchHomeTeam);
        TextView homeScore = convertView.findViewById(R.id.matchHomeScore);
        TextView awayScore = convertView.findViewById(R.id.matchAwayScore);
        TextView awayTeam = convertView.findViewById(R.id.matchAwayTeam);

        MatchFragmentItem currentItem = (MatchFragmentItem) getItem(i);

        dateField.setText(currentItem.getMatchDate());
        homeTeam.setText(currentItem.getHomeTeam());
        homeScore.setText(currentItem.getHomeScore());
        awayScore.setText(currentItem.getAwayScore());
        awayTeam.setText(currentItem.getAwayTeam());

        if (currentItem.getResult() == MatchResult.WIN) {
            background.setBackgroundColor(ContextCompat.getColor(context, Colour.WIN_BGROUND_COLOUR));
        }
        else if (currentItem.getResult() == MatchResult.DRAW) {
            background.setBackgroundColor(ContextCompat.getColor(context, Colour.DRAW_BGROUND_COLOUR));
        }
        else if (currentItem.getResult() == MatchResult.LOSE) {
            background.setBackgroundColor(ContextCompat.getColor(context, Colour.LOSE_BGROUND_COLOUR));
        }

        return convertView;

    }
}