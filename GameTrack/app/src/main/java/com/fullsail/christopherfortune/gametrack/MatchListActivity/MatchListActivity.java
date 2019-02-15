package com.fullsail.christopherfortune.gametrack.MatchListActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fullsail.christopherfortune.gametrack.R;

public class MatchListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_list);

        // Get the starting intent
        Intent startingIntent = getIntent();
        String gameChosen = startingIntent.getStringExtra("gameChosen");

    }
}
