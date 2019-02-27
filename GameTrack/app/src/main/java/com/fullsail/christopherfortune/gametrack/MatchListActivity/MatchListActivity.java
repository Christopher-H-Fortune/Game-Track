package com.fullsail.christopherfortune.gametrack.MatchListActivity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.fullsail.christopherfortune.gametrack.AddMatchActivity.AddMatchActivity;
import com.fullsail.christopherfortune.gametrack.CareerActivity.CareerActivity;
import com.fullsail.christopherfortune.gametrack.CompareMatchesActivity.CompareMatchesActivity;
import com.fullsail.christopherfortune.gametrack.GamesListActivity.GamesListActivity;
import com.fullsail.christopherfortune.gametrack.MatchClass.Matches;
import com.fullsail.christopherfortune.gametrack.MatchListClass.MatchesListClass;
import com.fullsail.christopherfortune.gametrack.MatchListFragment.MatchListAdapter;
import com.fullsail.christopherfortune.gametrack.MatchListFragment.MatchListFragment;
import com.fullsail.christopherfortune.gametrack.R;
import com.fullsail.christopherfortune.gametrack.SelectedMatchActivity.SelectedMatchActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.perf.metrics.AddTrace;

import java.util.ArrayList;

public class MatchListActivity extends AppCompatActivity implements MatchListFragment.MatchListFragmentInterface {

    private String gameChosen;
    private final ArrayList<MatchesListClass> matchesArrayList = new ArrayList<>();
    private ListView matchesListView;

    @Override
    @AddTrace(name = "onCreateMatchListTrace")
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
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();

        // Get the current user signed in
        final FirebaseUser user = mAuth.getCurrentUser();

        // Make sure the user ins't null
        if(user != null) {

            // Get the users ID
            final String uId = user.getUid();

            // Set the database reference using the mFirebaseDatabase
            DatabaseReference mDatabaseReference = mFirebaseDatabase.getReference("/users/" + uId + "/games/" + gameChosen + "/matches");

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

            ImageButton compareMatchesImageButton = findViewById(R.id.compare_image_button);
            compareMatchesImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent compareMatchesIntent = new Intent(MatchListActivity.this, CompareMatchesActivity.class);
                    compareMatchesIntent.putExtra("gameChosen", gameChosen);
                    startActivity(compareMatchesIntent);
                }
            });

            ImageButton careerImageButton = findViewById(R.id.career_image_button);
            careerImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent careerIntent = new Intent(MatchListActivity.this, CareerActivity.class);
                    careerIntent.putExtra("gameChosen", gameChosen);
                    startActivity(careerIntent);
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

    @Override
    public void viewMatch(int matchChosen) {

        Intent selectedMatchIntent = new Intent(this, SelectedMatchActivity.class);
        selectedMatchIntent.putExtra("gameChosen", gameChosen);
        selectedMatchIntent.putExtra("matchChosenDate", matchesArrayList.get(matchChosen).getDate());
        startActivity(selectedMatchIntent);
    }

    @Override
    public void goBack() {

        Intent goBackIntent = new Intent(this, GamesListActivity.class);
        startActivity(goBackIntent);
    }

    private void updateMatchesList(){
        MatchListAdapter matchListAdapter = new MatchListAdapter(this, R.layout.matches_list_row, matchesArrayList);

        matchesListView.setAdapter(matchListAdapter);
    }
}
