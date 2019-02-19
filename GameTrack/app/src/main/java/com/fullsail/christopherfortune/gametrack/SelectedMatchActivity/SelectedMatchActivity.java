package com.fullsail.christopherfortune.gametrack.SelectedMatchActivity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.fullsail.christopherfortune.gametrack.MatchClass.Matches;
import com.fullsail.christopherfortune.gametrack.MatchListClass.MatchesListClass;
import com.fullsail.christopherfortune.gametrack.R;
import com.fullsail.christopherfortune.gametrack.SelectedMatchFragment.SelectedMatchFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SelectedMatchActivity extends AppCompatActivity implements SelectedMatchFragment.SelectedMatchFragmentInterface {

    public String gameChosen;
    public String matchDate;
    public FirebaseAuth mAuth;
    public DatabaseReference mDatabaseReference;
    public FirebaseDatabase mFirebaseDatabase;
    public Matches matchChosen;
    public TextView mapNameTextView;
    public TextView matchDateTextView;
    public TextView matchAssistTextView;
    public TextView matchKillsTextView;
    public TextView matchScoreTextView;
    public EditText mainWeaponEditText;
    public EditText secondaryEditText;
    public EditText grenadesEditText;
    public EditText winLossEditText;
    public EditText matchNotesEditText;

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

        }

        // If the intent has a string extra of matchChosenDate
        if (startingIntent.hasExtra("matchChosenDate")){

            // Get the match date chosen String
            matchDate = startingIntent.getStringExtra("matchChosenDate");
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

                            // Get the match chosen
                            if(matchDate.equals(matches.getMatchDate())){

                                // Store the match chosen to a Matches class object
                                matchChosen = new Matches();
                                matchChosen.setMatchDate(matches.getMatchDate());
                                matchChosen.setGameWon(matches.isGameWon());
                                matchChosen.setMapName(matches.getMapName());
                                matchChosen.setMatchLength(matches.getMatchLength());
                                matchChosen.setKills(matches.getKills());
                                matchChosen.setDeaths(matches.getDeaths());
                                matchChosen.setMainWeapon(matches.getMainWeapon());
                                matchChosen.setSecondaryWeapon(matches.getSecondaryWeapon());
                                matchChosen.setGrenades(matches.getGrenades());
                                matchChosen.setMatchScore(matches.getMatchScore());
                                matchChosen.setMatchAssists(matches.getMatchAssists());
                                matchChosen.setMatchNotes(matches.getMatchNotes());
                            }
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

        // Display the SelectedMatchFragment to the selected_match_frame_layout
        getSupportFragmentManager().beginTransaction().replace(R.id.selected_match_frame_layout, SelectedMatchFragment.newInstance(), SelectedMatchFragment.TAG).commit();
    }

    @Override
    public void passDataViews(TextView mapNameTextView, TextView matchDateTextView, TextView matchAssistTextView, TextView matchKillsTextView, TextView matchScoreTextView, EditText mainWeaponEditText, EditText secondaryEditText, EditText grenadesEditText, EditText winLossEditText, EditText matchNotesEditText) {

        // Store the Views to display the data
        this.mapNameTextView = mapNameTextView;
        this.matchDateTextView = matchDateTextView;
        this.matchAssistTextView = matchAssistTextView;
        this.matchKillsTextView = matchKillsTextView;
        this.matchScoreTextView = matchScoreTextView;
        this.mainWeaponEditText = mainWeaponEditText;
        this.secondaryEditText = secondaryEditText;
        this.grenadesEditText = grenadesEditText;
        this.winLossEditText = winLossEditText;
        this.matchNotesEditText = matchNotesEditText;
    }
}
