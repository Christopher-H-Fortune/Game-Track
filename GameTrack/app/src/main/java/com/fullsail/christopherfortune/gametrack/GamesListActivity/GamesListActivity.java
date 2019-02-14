package com.fullsail.christopherfortune.gametrack.GamesListActivity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fullsail.christopherfortune.gametrack.AddGameActivity.AddGameActivity;
import com.fullsail.christopherfortune.gametrack.GameClass.Games;
import com.fullsail.christopherfortune.gametrack.GameListFragment.GamesListFragment;
import com.fullsail.christopherfortune.gametrack.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class GamesListActivity extends AppCompatActivity implements GamesListFragment.GamesListFragmentInterface {

    public FirebaseAuth mAuth;
    public DatabaseReference mDatabaseReference;
    public FirebaseDatabase mFirebaseDatabase;
    public ArrayList<String> gamesArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games_list);
        setTitle("Welcome!");

        // Set the instance of the Firebase auth and database
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();

        // Get the current user signed in
        final FirebaseUser user = mAuth.getCurrentUser();

        // Make sure the user ins't null
        if(user != null){

            // Get the users ID
            final String uId = user.getUid();

            // Set the database reference using the mFirebaseDatabase
            mDatabaseReference = mFirebaseDatabase.getReference("/users/"+ uId + "/games");

            // Set the database to have a ValueEventListener to display the games data to the user
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    // Loop through the data snapshot
                    for(DataSnapshot data : dataSnapshot.getChildren()){

                        // Get the games data
                        Games games = data.getValue(Games.class);

                        // Make sure games isn't null
                        if (games != null) {

                            // Get the game title
                            String gameTitle = games.getGameTitle();

                            // Add the game title to the gamesArrayList
                            gamesArrayList.add(gameTitle);
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
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
