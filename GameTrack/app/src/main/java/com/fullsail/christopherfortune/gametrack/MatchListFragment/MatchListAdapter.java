package com.fullsail.christopherfortune.gametrack.MatchListFragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.fullsail.christopherfortune.gametrack.MatchListClass.MatchesListClass;
import com.fullsail.christopherfortune.gametrack.R;

import java.util.ArrayList;

public class MatchListAdapter extends ArrayAdapter<MatchesListClass> {

    private final Context context;
    private final int resource;
    private final ArrayList<MatchesListClass> matchesArrayList;

    public MatchListAdapter(Context context, int resource, ArrayList<MatchesListClass> matchesArrayList){
        super(context, resource, matchesArrayList);
        this.context = context;
        this.resource = resource;
        this.matchesArrayList = matchesArrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View listRowView = layoutInflater.inflate(R.layout.matches_list_row, null);

        TextView mapNameTextView = listRowView.findViewById(R.id.map_name_text_view);
        TextView matchDateTextView = listRowView.findViewById(R.id.match_date_text_view);
        TextView assistsTextView = listRowView.findViewById(R.id.assists_text_view);
        TextView killsTextView = listRowView.findViewById(R.id.kills_text_view);
        TextView scoreTextView = listRowView.findViewById(R.id.score_text_view);

        mapNameTextView.setText(matchesArrayList.get(position).getMapName());
        matchDateTextView.setText(matchesArrayList.get(position).getDate());
        assistsTextView.setText(context.getResources().getString(R.string.assist_text_view, matchesArrayList.get(position).getAssists()));
        killsTextView.setText(context.getResources().getString(R.string.kills_text_view, matchesArrayList.get(position).getKills()));
        scoreTextView.setText(context.getResources().getString(R.string.score_text_view, matchesArrayList.get(position).getScore()));

        return listRowView;
    }
}
