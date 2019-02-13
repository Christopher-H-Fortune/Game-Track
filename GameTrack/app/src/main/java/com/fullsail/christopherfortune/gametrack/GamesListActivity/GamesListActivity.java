package com.fullsail.christopherfortune.gametrack.GamesListActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fullsail.christopherfortune.gametrack.AddGameActivity.AddGameActivity;
import com.fullsail.christopherfortune.gametrack.GameListFragment.GamesListFragment;
import com.fullsail.christopherfortune.gametrack.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class GamesListActivity extends AppCompatActivity implements GamesListFragment.GamesListFragmentInterface {

    public FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games_list);
        setTitle("Welcome!");

        // Set the instance of the Firebase auth and database
        mAuth = FirebaseAuth.getInstance();

        // Get the current user signed in
        FirebaseUser user = mAuth.getCurrentUser();

        // Make sure the user ins't null
        if(user != null){

            // Get the users ID
            String uId = user.getUid();
        }

        // Display the gamesListFragment
        getSupportFragmentManager().beginTransaction().replace(R.id.game_list_frame_layout, GamesListFragment.newInstance(), GamesListFragment.TAG).commit();
    }

    @Override
    public void addGame() {

        // Intent to send the user to the add game activity
        Intent addGameIntent = new Intent(GamesListActivity.this, AddGameActivity.class);

        // Start the AddGameActivity with the intent created above
        startActivity(addGameIntent);
    }
}
