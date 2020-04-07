package com.cutlerdevelopment.footballsteps.Utils.ViewAdapters;

import android.content.Context;
import android.provider.Telephony;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cutlerdevelopment.footballsteps.R;
import com.cutlerdevelopment.footballsteps.Utils.ViewItems.SmallTableItem;

import java.util.ArrayList;

public class SmallTableItemAdapter extends BaseAdapter {

    private ArrayList<SmallTableItem> singleRow;
    private LayoutInflater thisInflater;
    private Context context;


    public SmallTableItemAdapter(Context context, ArrayList<SmallTableItem> aRow) {
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
            convertView = thisInflater.inflate(R.layout.small_table_item, parent, false);
        }

        TextView teamPosField = convertView.findViewById(R.id.smallTableItemPosition);
        TextView teamNameField = convertView.findViewById(R.id.smallTableItemTeamName);
        TextView gamesPlayedField = convertView.findViewById(R.id.smallTableItemGamesPlayer);
        TextView goalsScoredField = convertView.findViewById(R.id.smallTableItemGoalDifference);
        TextView pointsField = convertView.findViewById(R.id.smallTableItemPoints);

        final SmallTableItem currentItem = (SmallTableItem) getItem(i);

        teamPosField.setText(currentItem.getPosition());
        teamNameField.setText(currentItem.getTeamName());
        gamesPlayedField.setText(currentItem.getGamesPlayed());
        goalsScoredField.setText(currentItem.getGoalDifference());
        pointsField.setText(currentItem.getPoints());


        return convertView;

    }
}
