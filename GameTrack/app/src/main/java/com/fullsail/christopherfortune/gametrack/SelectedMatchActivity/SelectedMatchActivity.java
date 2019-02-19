package com.fullsail.christopherfortune.gametrack.SelectedMatchActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fullsail.christopherfortune.gametrack.R;
import com.fullsail.christopherfortune.gametrack.SelectedMatchFragment.SelectedMatchFragment;

public class SelectedMatchActivity extends AppCompatActivity {

    public String gameChosen;
    public String matchDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_match);

        // Get the intent that started the activity
        Intent startingIntent = getIntent();

        // If the intent has a string extra of gameChosen
        if(startingIntent.hasExtra("gameChosen")){

            // Get the game chosen String
            gameChosen = startingIntent.getStringExtra("gameChosen");

        // If the intent has a string extra of matchChosenDate
        } else if (startingIntent.hasExtra("matchChosenDate")){

            // Get the match date chosen String
            matchDate = startingIntent.getStringExtra("matchChosenDate");
        }

        // Display the SelectedMatchFragment to the selected_match_frame_layout
        getSupportFragmentManager().beginTransaction().replace(R.id.selected_match_frame_layout, SelectedMatchFragment.newInstance(), SelectedMatchFragment.TAG).commit();
    }
}
