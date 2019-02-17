package com.fullsail.christopherfortune.gametrack.MatchListActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.fullsail.christopherfortune.gametrack.AddGameActivity.AddGameActivity;
import com.fullsail.christopherfortune.gametrack.AddMatchActivity.AddMatchActivity;
import com.fullsail.christopherfortune.gametrack.AddMatchFragment.AddMatchFragment;
import com.fullsail.christopherfortune.gametrack.MatchListFragment.MatchListFragment;
import com.fullsail.christopherfortune.gametrack.R;

public class MatchListActivity extends AppCompatActivity implements MatchListFragment.MatchListFragmentInterface {

    String gameChosen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_list);

        // Get the starting intent
        Intent startingIntent = getIntent();

        if(startingIntent.hasExtra("gameChosen")){
            gameChosen = startingIntent.getStringExtra("gameChosen");
            setTitle(gameChosen);
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.match_list_frame_layout, MatchListFragment.newInstance(), MatchListFragment.TAG).commit();
    }

    @Override
    public void passListView(ListView matchListView) {

    }

    @Override
    public void addMatch() {

        // Intent to send the user to the add match screen
        Intent addMatchIntent = new Intent(this, AddMatchActivity.class);

        // Put the game title as an extra
        addMatchIntent.putExtra("gameName", gameChosen);

        // Start the activity with the intent created above
        startActivity(addMatchIntent);
    }
}
