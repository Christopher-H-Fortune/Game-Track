package com.fullsail.christopherfortune.gametrack.MatchListActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.fullsail.christopherfortune.gametrack.AddMatchFragment.AddMatchFragment;
import com.fullsail.christopherfortune.gametrack.MatchListFragment.MatchListFragment;
import com.fullsail.christopherfortune.gametrack.R;

public class MatchListActivity extends AppCompatActivity implements MatchListFragment.MatchListFragmentInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_list);

        // Get the starting intent
        Intent startingIntent = getIntent();
        String gameChosen = startingIntent.getStringExtra("gameChosen");

        setTitle(gameChosen);

        getSupportFragmentManager().beginTransaction().replace(R.id.match_list_frame_layout, MatchListFragment.newInstance(), MatchListFragment.TAG).commit();
    }

    @Override
    public void passListView(ListView matchListView) {

    }

    @Override
    public void addMatch() {
        getSupportFragmentManager().beginTransaction().replace(R.id.match_list_frame_layout, AddMatchFragment.newInstance(), AddMatchFragment.TAG).addToBackStack(null).commit();
    }
}
