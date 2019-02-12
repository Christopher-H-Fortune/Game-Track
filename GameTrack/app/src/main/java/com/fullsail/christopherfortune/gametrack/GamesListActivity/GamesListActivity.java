package com.fullsail.christopherfortune.gametrack.GamesListActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fullsail.christopherfortune.gametrack.GameListFragment.GamesListFragment;
import com.fullsail.christopherfortune.gametrack.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class GamesListActivity extends AppCompatActivity {

    public FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games_list);

        // Get the intent used to send the user to this activity
        Intent sendingIntent = getIntent();

        // Get the String passed from the intent
        String userID = sendingIntent.getStringExtra("usersId");

        // Set the instance of the Firebase auth and database
        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();

        String uId = user.getUid();

        // Display the gamesListFragment
        getSupportFragmentManager().beginTransaction().replace(R.id.game_list_frame_layout, GamesListFragment.newInstance(), GamesListFragment.TAG).commit();
    }
}
