package com.fullsail.christopherfortune.gametrack.CompareMatchesActivity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.fullsail.christopherfortune.gametrack.CompareMatchesFragment.CompareMatchesFragment;
import com.fullsail.christopherfortune.gametrack.MatchClass.Matches;
import com.fullsail.christopherfortune.gametrack.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CompareMatchesActivity extends AppCompatActivity implements CompareMatchesFragment.CompareMatchesFragmentInterface {

    public String gameChosen;
    public FirebaseAuth mAuth;
    public DatabaseReference mDatabaseReference;
    public FirebaseDatabase mFirebaseDatabase;
    public ArrayList<String> spinnerArrayList;
    public ArrayList<String> matchesDatesArrayList;
    public Spinner firstMatchSpinner;
    public Spinner secondMatchSpinner;
    public int firstMatchChosen;
    public int secondMatchChosen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare_matches);

        spinnerArrayList = new ArrayList<>();

        // Get the starting intent
        Intent startingIntent = getIntent();

        // If the starting intent has an extra called gameChosen
        if(startingIntent.hasExtra("gameChosen")){

            // Store the string extra passed from the intent
            gameChosen = startingIntent.getStringExtra("gameChosen");
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
        }

        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                // Loop through the data snapshot
                for(DataSnapshot data : dataSnapshot.getChildren()){

                    // Get the games data
                    Matches matches = data.getValue(Matches.class);

                    // Make sure games isn't null
                    if (matches != null) {

                        // Get the match map and date
                        String mapName = matches.getMapName();
                        String matchDate = matches.getMatchDate();

                        // Add the map name and date as a string to the spinnerArrayList
                        spinnerArrayList.add(mapName + ", " + matchDate);

                        // Add the date to the matchesDatesArrayList
                        matchesDatesArrayList.add(matchDate);
                    }
                }

                updateSpinners();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        // Display the CompareMatchesFragment in the compare_matches_frame_layout
        getSupportFragmentManager().beginTransaction().replace(R.id.compare_matches_frame_layout, CompareMatchesFragment.newInstance(), CompareMatchesFragment.TAG).commit();
    }

    private void updateSpinners(){
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, spinnerArrayList);

        if(firstMatchSpinner != null && secondMatchSpinner != null){

            firstMatchSpinner.setAdapter(spinnerAdapter);
            secondMatchSpinner.setAdapter(spinnerAdapter);

            firstMatchSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    // Get the position chosen from the spinner
                    firstMatchChosen = position;
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            secondMatchSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    // Get the position chosen from the spinner
                    secondMatchChosen = position;
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
    }

    @Override
    public void passSpinners(Spinner firstMatchSpinner, Spinner secondMatchSpinner) {
        this.firstMatchSpinner = firstMatchSpinner;
        this.secondMatchSpinner = secondMatchSpinner;
    }

    @Override
    public void compareGames() {

    }
}