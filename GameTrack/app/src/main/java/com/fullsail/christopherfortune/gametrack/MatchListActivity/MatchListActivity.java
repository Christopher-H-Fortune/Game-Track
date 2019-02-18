package com.fullsail.christopherfortune.gametrack.MatchListActivity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.fullsail.christopherfortune.gametrack.AddGameActivity.AddGameActivity;
import com.fullsail.christopherfortune.gametrack.AddMatchActivity.AddMatchActivity;
import com.fullsail.christopherfortune.gametrack.AddMatchFragment.AddMatchFragment;
import com.fullsail.christopherfortune.gametrack.GameClass.Games;
import com.fullsail.christopherfortune.gametrack.MatchClass.Matches;
import com.fullsail.christopherfortune.gametrack.MatchListClass.MatchesListClass;
import com.fullsail.christopherfortune.gametrack.MatchListFragment.MatchListAdapter;
import com.fullsail.christopherfortune.gametrack.MatchListFragment.MatchListFragment;
import com.fullsail.christopherfortune.gametrack.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MatchListActivity extends AppCompatActivity implements MatchListFragment.MatchListFragmentInterface {

    public String gameChosen;
    public FirebaseAuth mAuth;
    public DatabaseReference mDatabaseReference;
    public FirebaseDatabase mFirebaseDatabase;
    public ArrayList<MatchesListClass> matchesArrayList = new ArrayList<>();
    public ListView matchesListView;

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

        // Set the instance of the Firebase auth and database
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();

        // Get the current user signed in
        final FirebaseUser user = mAuth.getCurrentUser();

        // Make sure the user ins't null
        if(user != null) {

            // Get the users ID
            final String uId = user.getUid();

            // Set the database reference using the mFirebaseDatabase
            mDatabaseReference = mFirebaseDatabase.getReference("/users/" + uId + "/games/" + gameChosen + "/matches");

            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    // Loop through the data snapshot
                    for(DataSnapshot data : dataSnapshot.getChildren()){

                        // Get the games data
                        Matches matches = data.getValue(Matches.class);

                        // Make sure games isn't null
                        if (matches != null) {

                            String mapName = matches.getMapName();

                            String matchDate = matches.getMatchDate();

                            // Get the match assists
                            int matchAssists = matches.getMatchAssists();

                            // Get the match kills
                            int matchKills = matches.getKills();

                            // Get the match score
                            int matchScore = matches.getMatchScore();

                            // Add the game title to the gamesArrayList
                            matchesArrayList.add(new MatchesListClass(mapName, matchDate,matchAssists, matchKills, matchScore));
                        }
                    }

                    updateMatchesList();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }

        getSupportFragmentManager().beginTransaction().replace(R.id.match_list_frame_layout, MatchListFragment.newInstance(), MatchListFragment.TAG).commit();
    }

    @Override
    public void passListView(ListView matchListView) {
        matchesListView = matchListView;
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

    private void updateMatchesList(){
        MatchListAdapter matchListAdapter = new MatchListAdapter(this, R.layout.matches_list_row, matchesArrayList);

        matchesListView.setAdapter(matchListAdapter);
    }
}
